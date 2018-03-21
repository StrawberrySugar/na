package com.zizaihome.api.utils;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Pbkdf2Sha256Utils {
    public final Integer DEFAULT_ITERATIONS = 24000;
    public final String algorithm = "pbkdf2_sha256";
    public Pbkdf2Sha256Utils() {}

    public String getEncodedHash(String password, String salt, int iterations) {
        // Returns only the last part of whole encoded password
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could NOT retrieve PBKDF2WithHmacSHA256 algorithm");
            System.exit(1);
        }
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(Charset.forName("UTF-8")), iterations, 256);
        SecretKey secret = null;
        try {
            secret = keyFactory.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            System.out.println("Could NOT generate secret key");
            e.printStackTrace();
        }

        byte[] rawHash = secret.getEncoded();
        byte[] hashBase64 = Base64.getEncoder().encode(rawHash);

        return new String(hashBase64);
    }
    /**
     * make salt 
     * @return String
     */
    private static String getsalt(){
        int length = 12;
        Random rand = new Random();
                char[] rs = new char[length];
                    for(int i = 0; i < length; i++){
                    int t = rand.nextInt(3);
                    if (t == 0) {
                        rs[i] = (char)(rand.nextInt(10)+48);
                    } else if (t == 1) {
                         rs[i] = (char)(rand.nextInt(26)+65);
                    } else {
                        rs[i] = (char)(rand.nextInt(26)+97);
                    }
                }
                return new String(rs);
    }
    /**
     * rand salt
     * iterations is default 20000
     * @param password
     * @return
     */
    public String encode(String password) {
        return this.encode(password, getsalt());
    }
    /**
     * rand salt
     * @param password
     * @return
     */
    public String encode(String password,int iterations) {
        return this.encode(password, getsalt(),iterations);
    }
    /**
     * iterations is default 20000
     * @param password
     * @param salt
     * @return
     */
    public String encode(String password, String salt) {
        return this.encode(password, salt, this.DEFAULT_ITERATIONS);
    }
    /**
     * 
     * @param password
     * @param salt
     * @param iterations
     * @return
     */
    public String encode(String password, String salt, int iterations) {
        // returns hashed password, along with algorithm, number of iterations and salt
        String hash = getEncodedHash(password, salt, iterations);
        return String.format("%s$%d$%s$%s", algorithm, iterations, salt, hash);
    }

    public boolean checkPassword(String password, String hashedPassword) {
        // hashedPassword consist of: ALGORITHM, ITERATIONS_NUMBER, SALT and
        // HASH; parts are joined with dollar character ("$")
        String[] parts = hashedPassword.split("\\$");
        if (parts.length != 4) {
            // wrong hash format
            return false;
        }
        Integer iterations = Integer.parseInt(parts[1]);
        String salt = parts[2];
        String hash = encode(password, salt, iterations);

        return hash.equals(hashedPassword);
    }


    public static void main(String[] args) {
        runTests();
    }


    // Following examples can be generated at any Django project:
    //
    //  >>> from django.contrib.auth.hashers import make_password
    //  >>> make_password('mystery', hasher='pbkdf2_sha256')  # salt would be randomly generated
    //  'pbkdf2_sha256$10000$HqxvKtloKLwx$HdmdWrgv5NEuaM4S6uMvj8/s+5Yj+I/d1ay6zQyHxdg='
    //  >>> make_password('mystery', salt='mysalt', hasher='pbkdf2_sha256')
    //  'pbkdf2_sha256$10000$mysalt$KjUU5KrwyUbKTGYkHqBo1IwUbFBzKXrGQgwA1p2AuY0='
    //
    //
    // mystery
    // pbkdf2_sha256$10000$qx1ec0f4lu4l$3G81rAm/4ng0tCCPTrx2aWohq7ztDBfFYczGNoUtiKQ=
    //
    // s3cr3t
    // pbkdf2_sha256$10000$BjDHOELBk7fR$xkh1Xf6ooTqwkflS3rAiz5Z4qOV1Jd5Lwd8P+xGtW+I=
    //
    // puzzle
    // pbkdf2_sha256$10000$IFYFG7hiiKYP$rf8vHYFD7K4q2N3DQYfgvkiqpFPGCTYn6ZoenLE3jLc=
    //
    // riddle
    // pbkdf2_sha256$10000$A0S5o3pNIEq4$Rk2sxXr8bonIDOGj6SU4H/xpjKHhHAKpFXfmNZ0dnEY=
    private static void runTests() {
        System.out.println(new Pbkdf2Sha256Utils().encode("123456", 24000));
        System.out.println("===========================");
        System.out.println("= Testing password hasher =");
        System.out.println("===========================");
        System.out.println();
        String result = (new Pbkdf2Sha256Utils()).encode("123456", "sdSn1014Ipgx", 24000);
        System.out.println(result);
        System.out.println();
        passwordShouldMatch("123456", "pbkdf2_sha256$20000$2uuTxG6587YA$xL2ygkbvlW63xQW7SSvlHkNl6xvipByGiuFTJzp0N28=");
        passwordShouldMatch("123456", "pbkdf2_sha256$20000$sdSn1014Ipgx$DM5NiPLH2UN1LgV/0QPsSYLuekWi8KfdV1pDYCK3zfU=");
        passwordShouldMatch("mystery", "pbkdf2_sha256$10000$qx1ec0f4lu4l$3G81rAm/4ng0tCCPTrx2aWohq7ztDBfFYczGNoUtiKQ=");
        passwordShouldMatch("mystery", "pbkdf2_sha256$10000$mysalt$KjUU5KrwyUbKTGYkHqBo1IwUbFBzKXrGQgwA1p2AuY0=");  // custom salt
        passwordShouldMatch("s3cr3t", "pbkdf2_sha256$10000$BjDHOELBk7fR$xkh1Xf6ooTqwkflS3rAiz5Z4qOV1Jd5Lwd8P+xGtW+I=");
        passwordShouldMatch("puzzle", "pbkdf2_sha256$10000$IFYFG7hiiKYP$rf8vHYFD7K4q2N3DQYfgvkiqpFPGCTYn6ZoenLE3jLc=");
        passwordShouldMatch("riddle", "pbkdf2_sha256$10000$A0S5o3pNIEq4$Rk2sxXr8bonIDOGj6SU4H/xpjKHhHAKpFXfmNZ0dnEY=");
        System.out.println(new Pbkdf2Sha256Utils().checkPassword("my123456", "pbkdf2_sha256$24000$rpQl0DS7IIQA$x04U+XbkS2B8pdEtanH2qrm/THKS7znEQlHw9zVYfmo="));
    }

    private static void passwordShouldMatch(String password, String expectedHash) {
        Pbkdf2Sha256Utils hasher = new Pbkdf2Sha256Utils();

        if (hasher.checkPassword(password, expectedHash)) {
            System.out.println(" => OK");
        } else {
            String[] parts = expectedHash.split("\\$");
            if (parts.length != 4) {
                System.out.printf(" => Wrong hash provided: '%s'\n", expectedHash);
                return;
            }
            String salt = parts[2];
            String resultHash = hasher.encode(password, salt);
            String msg = " => Wrong! Password '%s' hash expected to be '%s' but is '%s'\n";
            System.out.printf(msg, password, expectedHash, resultHash);
        }
    }
}

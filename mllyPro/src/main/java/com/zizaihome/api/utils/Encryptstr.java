package com.zizaihome.api.utils;



public class Encryptstr {

	static String sWrong;

	static String sSkey = "207806515039";

	public Encryptstr() {
		sWrong = "";
	}

	public static String encryptkey(String s, int i) {
		String s1 = s;
		int j = s.length();
		if (i <= j)
			s1 = s.substring(0, i);
		else
			for (; j < i; j = s1.length())
				if (j + s.length() >= i)
					s1 = s1 + s.substring(0, i - j);
				else
					s1 = s1 + s;

		return s1;
	}

	public static String encrypt(String s, String s1) {
		String s2 = "";
		if (s1.equals("")) {
			sWrong = "字符串为空！";
			return s2;
		}
		if (s.equals("")) {
			sWrong = "请指定密钥！";
			return s2;
		}
		int i = s1.length();
		String s3 = encryptkey(s, i);
		for (int k = 0; k < i; k++) {
			char c = s1.charAt(k);
			char c1 = s3.charAt(k);
			char c2 = c;
			char c3 = c1;
			int j = c2 ^ c3;
			j += 29;
			j = 1000 + ((j / 10) % 10) * 100 + (j / 100) * 10 + j % 10;
			s2 = s2 + Integer.toString(j).substring(1);
		}

		return s2;
	}

	public static String encrypt(String s) {
		return encrypt(sSkey, s);
	}

	public static String decode(String s, String s1) {
		String s2 = "";
		if (s1 == "") {
			sWrong = "字符串为空！";
			return s2;
		}
		if (s == "") {
			sWrong = "请指定密钥！";
			return s2;
		}
		int i = s1.length() / 3;
		String s3 = encryptkey(s, i);
		for (int l = 0; l < i; l++) {
			String s4 = s1.substring(l * 3, (l + 1) * 3);
			char c1 = s3.charAt(l);
			int k = Integer.parseInt(s4);
			k = ((k / 10) % 10) * 100 + (k / 100) * 10 + k % 10;
			char c2 = c1;
			int j = k - 29 ^ c2;
			char c = (char) j;
			s2 = s2 + c;
		}

		return s2;
	}

	public static String decode(String s) {
		return decode(sSkey, s);
	}

	public static void main(String args[]) throws Exception {
		Encryptstr encrypt = new Encryptstr();
		LogUtils.log.info(encrypt.encrypt("10"));//加密   
//		LogUtils.log.info(new Encryptstr().decode("302301303307303302302"));
	}

}

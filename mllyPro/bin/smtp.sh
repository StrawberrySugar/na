#!/usr/bin/expect -f

spawn telnet smtp.digu.com 25
expect "])"
send "helo 163\r"
expect "250 OK"
send "auth login\r"
expect "334*"
send "a2VuZGVyQGRpZ3UuY29t\r" 
#if you don't how to get your base64 encode,reference note 1 on the bottom.  
expect "334*"
send "a2VuZGVy\r"
expect "235*"
send "mail from:<kender@digu.com>\r" 
expect "250*"
send "rcpt to:<[lindex $argv 0]>\r"
expect "250*"
send "data\r"
expect "354*"
send "from:kender@digu.com\r"
send "to:[lindex $argv 0]\r"
send "subject: [lindex $argv 1] \r"
send "MIME-Version:1.0\r"
send "content-type:text/plain\r"

set count 0
set f [open [lindex $argv 2] r]
while {[gets $f line]>=0} {
  incr count
  #puts "line $count of file: $line"
  send "$line\r"
}
close $f

send ".\r"
expect "250*"
send "quit\r"

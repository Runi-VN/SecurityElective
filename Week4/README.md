# Week-04 A3 and GDPR

### Exercise 1
*The first thing we will do is to look at Cesarean rotation. I am not sure it was ever used, but it is good for making text which can't be read by the named eye, but is easy to crack.*

*In the sample code, find out how many letters to rotate the hidden message. Notice, the rotator can rotate both forwards and backwards (though that is not really necessary - why?)*


**The given offset (rotation) is 9.** You can change it to -17 to get the same output as the given offset.  
The reason is that the alphabet used is the standard latin 26-letter alphabet (A-Z). -17 is just the difference (9) from the alphabet length (26).  
The same happens if you give it an offset of 35, which is 26+9  

**I tested the rotations with the following code:**
```java
for (int i = 0; i < 52 ;i++) {
    System.out.println("Current value: "+ i);
    System.out.println(rot(STR2,i));
}
```
of which I got the following interesting results:
```
Current value: 9
Cesar thought Cleopatra had a most charming nose
...
Current value: 35
Cesar thought Cleopatra had a most charming nose
```
Cesar used rot4, and the most common seems to be rot13 (for the most far away letters, therefore most confusing?)

### Exercise 2
*This exercise uses an "incredible dumb and stupid" password scheme, as the actual input is a number between 1000 and 9999. You must find out which one. Your attack form here is brute force (trying them all).*

*Hint: If you want to automate validation, the word "everything" occurs in the original text.*

The start code provides us with the following hints and variables:
```java
int k = 0; // which k yields the right result
String key = "passwordabcd"+k; // has to be 128 bit/16 bytes
```
which throws the following exception:
```java
java.security.InvalidKeyException: Invalid AES key length: 13 bytes
```
However, using the instructions of bruteforcing numbers 1000-9999 I came up with this solution:
```java
for (int i = 1000; i <= 10000; i++) {
    String key = "passwordabcd" + i; // has to be 128 bit/16 bytes
    String dec = "";
    try {
		dec = decrypt(CIFER, key);
    } catch (Exception e) {
		//we don't care
		continue;
    }
    if (dec != null && dec.contains("everything")) {
		System.out.println("Decrypted: " + dec);
		System.out.println("Password value: " + i);
		break;
		}
    }
```
which gave the result:
```java
Decrypted: Mobile was Internet 2.0. It changed everything. Crypto is Internet 3.0.
Password value: 7345
```
meaning that the password **is equal to passwordabc7345**.
The first decrpyted value occurs at *1072* but returns gibberish.

### Exercise 3
This exercise is about writing a small toy editor which allow you to store small texts in encrypted format. The save function is there, but you have to write the load.


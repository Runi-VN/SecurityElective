package dk.cphbusiness.security.encryption;

import java.util.Base64;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64.Encoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* 
A number of corners have been cut here. Do not build on this one
Use the one named Exercise 3 for real use.
 */
public class Exercise2 {

    private static final String ALGORITHM = "AES";

    private static final String CIFER
            = "NmxhSedpfrayg4OsgKNWSjVp68E0xa76H5bOa+LEgt3fvVWPM/QHX48ySecVpyEPO/xVRaa2URbzEglWugmPpji8Q6ClwoMYHmX6qtimZ7I=";

    public static void main(String[] args) throws NoSuchAlgorithmException {

        for (int i = 1000; i <= 10000; i++) {
            String key = "passwordabcd" + i; // has to be 128 bit/16 bytes
            String dec;
            try {
                dec = decrypt(CIFER, key);
            } catch (Exception e) {
                //we don't care
                continue;
            }
            if (dec != null && dec.contains("everything")) {
                System.out.println("Decrypted: " + dec);
                System.out.println("Password value: " + i);
                System.out.println("---------\nEncryption example");
                String toEncrypt = "This is my secret data abcd1234$€";
                String myKey = generateSafeToken();
                String encrypted = encrypt(toEncrypt, myKey);
                String decrypted = decrypt(encrypted, myKey);
                System.out.println("Data to encrypt: " + toEncrypt);
                System.out.println("Key to encrypt with: " + myKey);
                System.out.println("Encrypted result: " + encrypted);
                System.out.println("Decrypted result: " + decrypted);
                break;
            }

        }
    }

    private static String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[18];
        random.nextBytes(bytes);
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }

    public static String encrypt(final String valueEnc, final String secKey) {
        String encryptedVal = null;
        try {
            final Key key = generateKeyFromString(secKey);
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            final byte[] encValue = c.doFinal(valueEnc.getBytes());
            encryptedVal = new String(Base64.getEncoder().encode(encValue));
        } catch (Exception ex) {
            System.out.println("The Exception is=" + ex);
        }
        return encryptedVal;
    }

    public static String decrypt(final String encryptedValue, final String secretKey) {
        String decryptedValue = null;
        try {
            final Key key = generateKeyFromString(secretKey);
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            final byte[] decorVal = Base64.getDecoder().decode(encryptedValue);
            final byte[] decValue = cipher.doFinal(decorVal);
            decryptedValue = new String(decValue);
        } catch (Exception ex) {
            System.out.println("The Exception is=" + ex);
        }
        return decryptedValue;
    }

    private static Key generateKeyFromString(final String secKey) throws Exception {
        final byte[] keyVal = secKey.getBytes();
        return new SecretKeySpec(keyVal, ALGORITHM); //generates an AES key from a byte array
    }
}

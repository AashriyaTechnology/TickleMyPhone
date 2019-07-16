package com.jalsalabs.ticklemyphonefull;

import java.security.InvalidKeyException;
import java.security.Key;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

public class LocalEncrypter {
    private static String algorithm = "DESede";
    private static Cipher cipher = null;
    private static Key key = null;

    private static void setUp() throws Exception {
        key = KeyGenerator.getInstance(algorithm).generateKey();
        cipher = Cipher.getInstance(algorithm);
    }

    public static void main(String[] args) throws Exception {
        setUp();
        if (args.length != 1) {
            System.out.println("USAGE: java LocalEncrypter [String]");
            System.exit(1);
        }
        byte[] encryptionBytes = null;
        String input = args[0];
        System.out.println("Entered: " + input);
        System.out.println("Recovered: " + decrypt(encrypt(input)));
    }

    private static byte[] encrypt(String input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(1, key);
        return cipher.doFinal(input.getBytes());
    }

    private static String decrypt(byte[] encryptionBytes) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(2, key);
        return new String(cipher.doFinal(encryptionBytes));
    }
}

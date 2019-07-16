package com.jalsalabs.ticklemyphonefull;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesWithCbcExample {
    public static void mainprogram() throws Exception {
        String message = "This string contains a secret message.";
        System.out.println("Plaintext: " + message + "\n");
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(128);
        SecretKeySpec skeySpec = new SecretKeySpec(keygen.generateKey().getEncoded(), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(new byte[16]);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, skeySpec, ivspec);
        byte[] encrypted = cipher.doFinal(message.getBytes());
        System.out.println("Ciphertext: " + hexEncode(encrypted) + "\n");
        cipher.init(2, skeySpec, ivspec);
        System.out.println("Plaintext: " + new String(cipher.doFinal(encrypted)) + "\n");
        System.out.println("Srinath Plaintext: " + new String(cipher.doFinal("827bdbe0c2b456383bf9f1f7b966f9a393eeec854bc4730bbe2d02266396d7acd01ee0cc62d5086d3bb07ac6c5c5a682".getBytes())) + "\n");
    }

    public static String hexEncode(byte[] input) {
        if (input == null || input.length == 0) {
            return "";
        }
        StringBuilder output = new StringBuilder(input.length * 2);
        for (byte b : input) {
            int next = b & 255;
            if (next < 16) {
                output.append("0");
            }
            output.append(Integer.toHexString(next));
        }
        return output.toString();
    }
}

package com.jalsalabs.ticklemyphonefull;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {
    private static final String CIPHER_ALGORITHM = "AES";
    private static final String RANDOM_GENERATOR_ALGORITHM = "SHA1PRNG";
    private static final int RANDOM_KEY_SIZE = 128;

    public static String encrypt(String password, String data) throws Exception {
        byte[] secretKey = generateKey(password.getBytes());
        byte[] clear = data.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, CIPHER_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(1, secretKeySpec);
        return Base64.encodeBytes(cipher.doFinal(clear));
    }

    public static String decrypt(String password, String encryptedData) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(generateKey(password.getBytes()), CIPHER_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(2, secretKeySpec);
        return new String(cipher.doFinal(Base64.decode(encryptedData)));
    }

    public static byte[] generateKey(byte[] seed) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CIPHER_ALGORITHM);
        SecureRandom.getInstance(RANDOM_GENERATOR_ALGORITHM).setSeed(seed);
        return keyGenerator.generateKey().getEncoded();
    }
}

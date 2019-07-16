package com.jalsalabs.ticklemyphonefull;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class SimpleCrypto {
    private static final String HEX = "0123456789ABCDEF";
    private static final byte[] seed_enc_array = new byte[]{(byte) 84, (byte) 77, (byte) 76, (byte) 74, (byte) 73, (byte) 78, (byte) 71, (byte) 85, (byte) 76, (byte) 65, (byte) 76, (byte) 65, (byte) 116, (byte) 109, (byte) 108, (byte) 103, (byte) 105, (byte) 110, (byte) 103, (byte) 117, (byte) 108, (byte) 97, (byte) 108, (byte) 97};

    public static String showme() {
        return new String(seed_enc_array);
    }

    public static String encrypt(String seed, String cleartext) throws Exception {
        return toHex(encrypt(getRawKey(seed.getBytes()), cleartext.getBytes()));
    }

    public static String decrypt(String seed, String encrypted) throws Exception {
        return new String(decrypt(getRawKey(seed.getBytes()), toByte(encrypted)));
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        sr.setSeed(seed);
        kgen.init(128, sr);
        return kgen.generateKey().getEncoded();
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, skeySpec);
        return cipher.doFinal(clear);
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, skeySpec);
        return cipher.doFinal(encrypted);
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(buf.length * 2);
        for (byte appendHex : buf) {
            appendHex(result, appendHex);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 15)).append(HEX.charAt(b & 15));
    }
}

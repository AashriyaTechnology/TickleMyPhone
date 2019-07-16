package com.jalsalabs.ticklemyphonefull;

public class ChoomMantraGaali {
    private static final String base64code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final int splitLinesAt = 76;

    public static byte[] zeroPad(int length, byte[] bytes) {
        byte[] padded = new byte[length];
        System.arraycopy(bytes, 0, padded, 0, bytes.length);
        return padded;
    }

    public static String encode(String string) {
        byte[] stringArray;
        String encoded = "";
        try {
            stringArray = string.getBytes("UTF-8");
        } catch (Exception e) {
            stringArray = string.getBytes();
        }
        int paddingCount = (3 - (stringArray.length % 3)) % 3;
        stringArray = zeroPad(stringArray.length + paddingCount, stringArray);
        for (int i = 0; i < stringArray.length; i += 3) {
            int j = (((stringArray[i] & 255) << 16) + ((stringArray[i + 1] & 255) << 8)) + (stringArray[i + 2] & 255);
            encoded = new StringBuilder(String.valueOf(encoded)).append(base64code.charAt((j >> 18) & 63)).append(base64code.charAt((j >> 12) & 63)).append(base64code.charAt((j >> 6) & 63)).append(base64code.charAt(j & 63)).toString();
        }
        return splitLines(encoded.substring(0, encoded.length() - paddingCount) + "==".substring(0, paddingCount));
    }

    public static String splitLines(String string) {
        String lines = "";
        for (int i = 0; i < string.length(); i += splitLinesAt) {
            lines = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(lines)).append(string.substring(i, Math.min(string.length(), i + splitLinesAt))).toString())).append("\r\n").toString();
        }
        return lines;
    }
}

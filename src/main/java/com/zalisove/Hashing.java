package com.zalisove;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashing
 * @author O.S.Kyrychenko
 */
public class Hashing {

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes();

    private Hashing(){}
    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(input.getBytes());
        return bytesToHex(md.digest());
    }


    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}

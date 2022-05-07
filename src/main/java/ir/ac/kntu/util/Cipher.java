package ir.ac.kntu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Cipher {

    private static Cipher instance = new Cipher();

    private Cipher() {
    }

    public static Cipher getInstance() {
        return instance;
    }

    public String sha256(String passwordToHash) {
        String generatedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(passwordToHash.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    }

}

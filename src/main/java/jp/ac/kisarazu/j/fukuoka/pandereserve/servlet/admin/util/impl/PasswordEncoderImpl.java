package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.PasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordEncoderImpl implements PasswordEncoder {
    public PasswordEncoderImpl() {

    }
    @Override
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public String hashPassword(String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        int iterations = 10000;
        int keyLength = 256;
        char[] passwordChars = password.toCharArray();

        PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, iterations, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = keyFactory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }
}

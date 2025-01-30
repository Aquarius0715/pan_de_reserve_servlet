package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface PasswordEncoder {
    byte[] generateSalt();
    String hashPassword(String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException;
}

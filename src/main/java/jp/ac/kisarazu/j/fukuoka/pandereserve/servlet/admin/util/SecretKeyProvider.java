package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util;

import javax.crypto.SecretKey;

public interface SecretKeyProvider {
    SecretKey getSecretKey();
}

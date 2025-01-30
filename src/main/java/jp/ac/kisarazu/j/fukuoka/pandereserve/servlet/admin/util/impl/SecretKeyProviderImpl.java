package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.SecretKeyProvider;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

public class SecretKeyProviderImpl implements SecretKeyProvider {
    private final String SECRET_KEY_ENV;

    public SecretKeyProviderImpl() {
        SECRET_KEY_ENV = System.getenv("PAN_DE_RESERVE_SECRET_KEY");
    }

    @Override
    public SecretKey getSecretKey() {
        if (SECRET_KEY_ENV == null) {
            throw new IllegalStateException("PAN_DE_RESERVE_SECRET_KEY is not set in environment variables");
        }
        return Keys.hmacShaKeyFor(SECRET_KEY_ENV.getBytes());
    }
}

package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.UserDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.UserDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.PageManager;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.SecretKeyProvider;

import javax.crypto.SecretKey;

public class PageManagerImpl implements PageManager {
    private final SecretKey SECRET_KEY;

    public PageManagerImpl() {
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        SECRET_KEY = secretKeyProvider.getSecretKey();
    }

    @Override
    public boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (token == null) {
            return false;
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isFirstVisit() {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.countUsers() == 0;
    }
}

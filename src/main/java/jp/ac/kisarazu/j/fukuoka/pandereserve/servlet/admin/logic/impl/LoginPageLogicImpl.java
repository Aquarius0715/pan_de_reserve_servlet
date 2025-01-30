package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.UserDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.UserDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.UserDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.exception.LoginDataNotFoundException;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.exception.LoginPageException;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.LoginPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.LoginPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.PasswordEncoder;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.SecretKeyProvider;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PageManagerImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PasswordEncoderImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.SecretKeyProviderImpl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Date;

public class LoginPageLogicImpl extends PageManagerImpl implements LoginPageLogic {
    @Override
    public boolean validatePassword(LoginPageModel loginPageModel) throws LoginPageException, LoginDataNotFoundException {
        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO = userDAO.loadUserByUsername(loginPageModel.getUsername());
        if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
            throw new LoginDataNotFoundException();
        }
        try {
            PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
            String inputHash = passwordEncoder.hashPassword(loginPageModel.getPassword(), Base64.getDecoder().decode(userDTO.getSalt()));
            return inputHash.equals(userDTO.getPassword());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new LoginPageException();
        }
    }

    @Override
    public void storeUserdataToCookie(LoginPageModel loginPageModel, HttpServletRequest request, HttpServletResponse response) {
        SecretKeyProvider keyProvider = new SecretKeyProviderImpl();
        String jwt = Jwts.builder()
                .setSubject(loginPageModel.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30分
                .signWith(keyProvider.getSecretKey())
                .compact();

        // クッキーにJWTを保存
        Cookie jwtCookie = new Cookie("token", jwt);
        jwtCookie.setHttpOnly(true); // クライアントスクリプトからアクセス不可
        jwtCookie.setMaxAge(30 * 60); // 30分
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
    }
}

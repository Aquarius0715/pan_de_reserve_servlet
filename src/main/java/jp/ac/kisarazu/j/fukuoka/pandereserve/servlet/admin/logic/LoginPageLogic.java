package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.exception.LoginDataNotFoundException;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.exception.LoginPageException;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.LoginPageModel;

public interface LoginPageLogic {
    boolean validatePassword(LoginPageModel loginPageModel)  throws LoginPageException, LoginDataNotFoundException;
    void storeUserdataToCookie(LoginPageModel loginPageModel, HttpServletRequest request, HttpServletResponse response);
}

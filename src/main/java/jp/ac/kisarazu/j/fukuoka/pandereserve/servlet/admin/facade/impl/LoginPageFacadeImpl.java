package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.exception.LoginDataNotFoundException;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.exception.LoginPageException;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.LoginPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl.LoginPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.LoginPageModel;

import java.io.IOException;

@WebServlet(name = "login", value = "/admin/login")
public class LoginPageFacadeImpl extends HttpServlet implements LoginPageFacade {
    private static final long serialVersionUID = 1L;
    private final LoginPageLogicImpl loginPageLogicImpl;

    public LoginPageFacadeImpl() {
        loginPageLogicImpl = new LoginPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (loginPageLogicImpl.isLogin(request, response)) {
            response.sendRedirect(request.getContextPath() + "/admin/reserveList");
        } else if (loginPageLogicImpl.isFirstVisit()) {
            response.sendRedirect(request.getContextPath() + "/admin/userResister");
        } else {
            request.getRequestDispatcher("/WEB-INF/admin/loginPage.jsp").forward(request, response);
        }
        request.getSession().invalidate();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginPageModel loginPageModel = new LoginPageModel();
        loginPageModel.setUsername(request.getParameter("username"));
        loginPageModel.setPassword(request.getParameter("password"));

        try {
            if (loginPageLogicImpl.validatePassword(loginPageModel)) {
                loginPageLogicImpl.storeUserdataToCookie(loginPageModel, request, response);
                response.sendRedirect(request.getContextPath() + "/admin/reserveList");
            } else {
                request.setAttribute("errorMessage", "ユーザーIDまたはパスワードが間違っています。");
                request.getRequestDispatcher("/WEB-INF/admin/loginPage.jsp").forward(request, response);
            }
        } catch (LoginDataNotFoundException e) {
            request.setAttribute("errorMessage", "ログインデータが見つかりません。新規登録してください。");
            request.getRequestDispatcher("/WEB-INF/admin/loginPage.jsp").forward(request, response);
        } catch (LoginPageException e) {
            request.setAttribute("errorMessage", "パスワードハッシュエラー。開発元に報告してください");
            request.getRequestDispatcher("/WEB-INF/admin/loginPage.jsp").forward(request, response);
        }
    }
}

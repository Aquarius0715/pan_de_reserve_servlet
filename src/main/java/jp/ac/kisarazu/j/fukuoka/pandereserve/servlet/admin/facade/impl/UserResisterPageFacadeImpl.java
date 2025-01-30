package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.UserResisterPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl.UserResisterPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.UserResisterPageModel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "userResister", value = "/admin/userResister")
public class UserResisterPageFacadeImpl extends HttpServlet implements UserResisterPageFacade {
    private static final long serialVersionUID = 1L;
    private final UserResisterPageLogicImpl userResisterPageLogic;

    public UserResisterPageFacadeImpl() {
        userResisterPageLogic = new UserResisterPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userResisterPageLogic.isLogin(request, response)) {
            response.sendRedirect(request.getContextPath() + "/admin/reserveList");
        } else if (userResisterPageLogic.isFirstVisit()) {
            request.getRequestDispatcher("/WEB-INF/admin/userResisterPage.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserResisterPageModel userResisterPageModel = new UserResisterPageModel();
        userResisterPageModel.setUsername(request.getParameter("username"));
        userResisterPageModel.setPassword(request.getParameter("password"));
        userResisterPageModel.setConfirmPassword(request.getParameter("confirmPassword"));

        if (userResisterPageModel.getUsername() == null
                || userResisterPageModel.getPassword() == null
                || userResisterPageModel.getConfirmPassword() == null) {
            request.setAttribute("errorMessage", "未入力のフィールドがあります。");
            request.getRequestDispatcher("/WEB-INF/admin/userResisterPage.jsp").forward(request, response);
            return;
        } else if (!userResisterPageModel.getPassword().equals(userResisterPageModel.getConfirmPassword())) {
            request.setAttribute("errorMessage", "パスワードが一致しません。");
            request.getRequestDispatcher("/WEB-INF/admin/userResisterPage.jsp").forward(request, response);
            return;
        }
        try {
            if (userResisterPageLogic.resisterUser(userResisterPageModel)) {
                response.sendRedirect(request.getContextPath() + "/admin/login");
                request.getSession().setAttribute("message", "新規登録完了しました。");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            request.setAttribute("errorMessage", "パスワードハッシュエラー。開発元に報告してください。");
            request.getRequestDispatcher("/WEB-INF/admin/userResisterPage.jsp").forward(request, response);
        }
    }
}

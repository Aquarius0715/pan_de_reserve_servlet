package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.IndexPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl.IndexPageLogicImpl;

import java.io.IOException;

@WebServlet(name = "admin", value = "/admin")
public class IndexPageFacadeImpl extends HttpServlet implements IndexPageFacade {
    private static final long serialVersionUID = 1L;
    private final IndexPageLogicImpl indexPageLogic;
    public IndexPageFacadeImpl() {
        indexPageLogic = new IndexPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (indexPageLogic.isLogin(request, response)) {
            response.sendRedirect(request.getContextPath() + "/admin/reserveList");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

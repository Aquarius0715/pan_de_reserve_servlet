package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.facade.BreadOrderCompletePageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic.impl.BreadOrderCompletePageLogicImpl;

import java.io.IOException;

@WebServlet(name = "orderComplete", value = "/orderComplete")
public class BreadOrderCompletePageFacadeImpl extends HttpServlet implements BreadOrderCompletePageFacade {
    private static final long serialVersionUID = 1L;
    private BreadOrderCompletePageLogicImpl breadOrderCompletePageLogic;
    public BreadOrderCompletePageFacadeImpl() {
        breadOrderCompletePageLogic = new BreadOrderCompletePageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("receiveTime") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/client/breadOrderCompletePage.jsp").forward(request, response);
    }
}

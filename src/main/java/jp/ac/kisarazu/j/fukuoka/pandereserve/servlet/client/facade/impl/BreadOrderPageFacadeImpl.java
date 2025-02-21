package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.facade.BreadOrderPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic.impl.BreadOrderPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.BreadOrderPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.Order;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "index", value = "/")
public class BreadOrderPageFacadeImpl extends HttpServlet implements BreadOrderPageFacade {
    private static final long serialVersionUID = 1L;
    private final BreadOrderPageLogicImpl breadOrderPageLogic;

    public BreadOrderPageFacadeImpl() {
        breadOrderPageLogic = new BreadOrderPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<BreadOrderPageModel> breadOrderPageModels = breadOrderPageLogic.getAvailableBreadItems();
        request.setAttribute("breadOrderPageModels", breadOrderPageModels);
        request.getRequestDispatcher("/WEB-INF/client/breadOrderPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderDataJson = request.getParameter("orderData");
        ArrayList<Order> orders = breadOrderPageLogic.getOrdersFromJson(orderDataJson);
        request.getSession().setAttribute("orders", orders);
        request.getSession().setAttribute("orderJson", orderDataJson);
        response.sendRedirect(request.getContextPath() + "/orderConfirm");
    }
}

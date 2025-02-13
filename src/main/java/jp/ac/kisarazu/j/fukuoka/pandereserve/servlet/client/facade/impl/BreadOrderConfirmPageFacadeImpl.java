package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.facade.BreadOrderConfirmPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic.impl.BreadOrderConfirmPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.BreadOrderConfirmPageModel;

import java.io.IOException;

@WebServlet(name = "orderConfirm", value = "/orderConfirm")
public class BreadOrderConfirmPageFacadeImpl extends HttpServlet implements BreadOrderConfirmPageFacade {
    private static final long serialVersionUID = 1L;
    private final BreadOrderConfirmPageLogicImpl breadOrderConfirmPageLogic;
    public BreadOrderConfirmPageFacadeImpl() {
        breadOrderConfirmPageLogic = new BreadOrderConfirmPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("orders") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/client/breadOrderConfirmPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        String phoneNumber = request.getParameter("phoneNumber"); // 追加
        String visitDate = request.getParameter("visitDate");
        String orderJson = (String) request.getSession().getAttribute("orderJson");
        BreadOrderConfirmPageModel model = new BreadOrderConfirmPageModel();
        model.setCustomerName(customerName);
        model.setReceiveTime(visitDate);
        model.setOrderJson(orderJson);
        model.setCustomerPhoneNumber(phoneNumber);

        breadOrderConfirmPageLogic.storeReservation(model);
        request.getSession().setAttribute("receiveTime", visitDate);
        response.sendRedirect(request.getContextPath() + "/orderComplete");
    }
}

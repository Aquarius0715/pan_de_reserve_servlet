package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.ReserveDetailPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl.ReserveDetailPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveDetailPageModel;

import java.io.IOException;

@WebServlet(name = "reserveDetail", value = "/admin/reserveList/reserveDetail")
public class ReserveDetailPageFacadeImpl extends HttpServlet implements ReserveDetailPageFacade {
    private static final long serialVersionUID = 1L;
    private final ReserveDetailPageLogicImpl reserveDetailPageLogic;
    public ReserveDetailPageFacadeImpl() {
        reserveDetailPageLogic = new ReserveDetailPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (reserveDetailPageLogic.isLogin(request, response)) {
            String reserveId = request.getParameter("id");
            String tableId = request.getParameter("tableId");
            ReserveDetailPageModel model = reserveDetailPageLogic.getReserveDetail(reserveId);
            request.setAttribute("reserveDetailPageModel", model);
            request.setAttribute("tableId", tableId);
            request.getRequestDispatcher("/WEB-INF/admin/reserveDetailPage.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }
}

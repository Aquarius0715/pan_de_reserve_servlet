package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.ReserveListPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl.ReserveListPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveListPageModel;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "reserveList", value = {"/admin/reserveList", "/admin/reserveList/setToReceived", "/admin/reserveList/setToUnReceived"})
public class ReserveListPageFacadeImpl extends HttpServlet implements ReserveListPageFacade {
    private static final long serialVersionUID = 1L;
    private final ReserveListPageLogicImpl reserveListPageLogic;

    public ReserveListPageFacadeImpl() {
        reserveListPageLogic = new ReserveListPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (reserveListPageLogic.isLogin(request, response)) {
            ArrayList<ReserveListPageModel> reserveList = reserveListPageLogic.getReserveList();
            request.setAttribute("reserveList", reserveList);
            request.getRequestDispatcher("/WEB-INF/admin/reserveListPage.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/admin/reserveList/setToReceived")) {
            doSetToReceived(request, response);
        } else if (request.getServletPath().equals("/admin/reserveList/setToUnReceived")) {
            doSetToUnReceived(request, response);
        }
    }

    private void doSetToReceived(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reserveId = request.getParameter("reserveId");
        if (reserveListPageLogic.setReceiveStatus(ReceiveStatus.RECEIVED, reserveId)) {
            response.sendRedirect(request.getContextPath() + "/admin/reserveList");
        }
    }

    private void doSetToUnReceived(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reserveId = request.getParameter("reserveId");
        if (reserveListPageLogic.setReceiveStatus(ReceiveStatus.UNRECEIVED, reserveId)) {
            response.sendRedirect(request.getContextPath() + "/admin/reserveList");
        }
    }
}

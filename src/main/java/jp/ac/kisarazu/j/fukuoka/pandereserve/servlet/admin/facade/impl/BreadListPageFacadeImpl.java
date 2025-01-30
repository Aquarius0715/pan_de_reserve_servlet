package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.BreadListPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl.BreadListPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadListPageModel;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "breadList", value = {"/admin/breadList", "/admin/breadList/deleteBread", "/admin/breadList/toggleStatus"})
public class BreadListPageFacadeImpl extends HttpServlet implements BreadListPageFacade {
    private static final long serialVersionUID = 1L;
    private final BreadListPageLogicImpl breadListPageLogic;
    public BreadListPageFacadeImpl() {
        breadListPageLogic = new BreadListPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (breadListPageLogic.isLogin(request, response)) {
            ArrayList<BreadListPageModel> breadListPageModels = breadListPageLogic.getBreadListPageModel();
            request.setAttribute("breadListPageModels", breadListPageModels);
            request.getRequestDispatcher("/WEB-INF/admin/breadListPage.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/admin/breadList/deleteBread")) {
            doDeleteBread(request, response);
        } else if (request.getServletPath().equals("/admin/breadList/toggleStatus")) {
            doToggleStatus(request, response);
        }
    }

    private void doDeleteBread(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int breadId = Integer.parseInt(request.getParameter("id"));
        breadListPageLogic.deleteBreadItem(breadId);
        response.sendRedirect(request.getContextPath() + "/admin/breadList");
    }

    private void doToggleStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int breadId = Integer.parseInt(request.getParameter("id"));
        SalesStatus salesStatus = SalesStatus.valueOf(request.getParameter("salesStatus"));
        breadListPageLogic.toggleSalesStatus(breadId, salesStatus);
        response.sendRedirect(request.getContextPath() + "/admin/breadList");
    }
}

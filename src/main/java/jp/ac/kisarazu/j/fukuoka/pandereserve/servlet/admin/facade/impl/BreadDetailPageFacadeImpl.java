package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.BreadDetailPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl.BreadDetailPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadDetailPageModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@WebServlet(name = "breadDetail", value = "/admin/breadList/breadDetail")
public class BreadDetailPageFacadeImpl extends HttpServlet implements BreadDetailPageFacade {
    private static final long serialVersionUID = 1L;
    private final BreadDetailPageLogicImpl breadDetailPageLogic;

    public BreadDetailPageFacadeImpl() {
        breadDetailPageLogic = new BreadDetailPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (breadDetailPageLogic.isLogin(request, response)) {
            int breadId = Integer.parseInt(request.getParameter("id"));
            BreadDetailPageModel model = breadDetailPageLogic.getBreadDetail(breadId);
            request.setAttribute("breadDetailPageModel", model);
            request.getRequestDispatcher("/WEB-INF/admin/breadDetailPage.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BreadDetailPageModel model = new BreadDetailPageModel();
        int breadId = Integer.parseInt(request.getParameter("breadId"));
        String breadName = request.getParameter("breadName");
        int breadPrice = Integer.parseInt(request.getParameter("breadPrice"));
        String breadDescription = request.getParameter("breadDescription");
        String encodedImage = request.getParameter("currentBreadImage");
        if (breadName == null ||
            breadDescription == null) {
            request.setAttribute("errorMessage", "未入力のフィールドがあります。");
            response.sendRedirect(request.getContextPath() + "/admin/breadList/breadDetail?id=" + request.getParameter("breadId"));
            return;
        }
        if (breadPrice < 0) {
            request.setAttribute("errorMessage", "価格は0以上にしてください。");
            response.sendRedirect(request.getContextPath() + "/admin/breadList/breadDetail?id=" + request.getParameter("breadId"));
            return;
        }
        model.setId(breadId);
        model.setBreadName(breadName);
        model.setBreadPrice(breadPrice);
        model.setBreadDescription(breadDescription);
        model.setBreadImage(encodedImage);
        breadDetailPageLogic.updateBreadDetail(model);
        response.sendRedirect(request.getContextPath() + "/admin/breadList");
    }
}

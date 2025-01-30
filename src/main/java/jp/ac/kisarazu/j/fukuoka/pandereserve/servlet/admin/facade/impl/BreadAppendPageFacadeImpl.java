package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.facade.BreadAppendPageFacade;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl.BreadAppendPageLogicImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadAppendPageModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@WebServlet(name = "breadAppend", value = "/admin/breadAppend")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // 最大10MBのファイルサイズ
public class BreadAppendPageFacadeImpl extends HttpServlet implements BreadAppendPageFacade {
    private static final long serialVersionUID = 1L;
    private final BreadAppendPageLogicImpl breadAppendPageLogic;
    public BreadAppendPageFacadeImpl() {
        breadAppendPageLogic = new BreadAppendPageLogicImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (breadAppendPageLogic.isLogin(request, response)) {
            request.getRequestDispatcher("/WEB-INF/admin/breadAppendPage.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
        request.getSession().invalidate();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BreadAppendPageModel model = new BreadAppendPageModel();
        String breadName = request.getParameter("breadName");
        int breadPrice = Integer.parseInt(request.getParameter("breadPrice"));
        String breadDescription = request.getParameter("breadDescription");
        Part breadImage = request.getPart("breadImage");
        if (breadName.isEmpty() || breadDescription.isEmpty()) {
            request.setAttribute("errorMessage", "未入力のフィールドがあります。");
            request.getRequestDispatcher("/WEB-INF/admin/breadAppendPage.jsp").forward(request, response);
            return;
        }
        if (breadPrice < 0) {
            request.setAttribute("errorMessage", "価格は0以上にしてください。");
            request.getRequestDispatcher("/WEB-INF/admin/breadAppendPage.jsp").forward(request, response);
            return;
        }
        if (breadImage == null) {
            request.setAttribute("errorMessage", "ファイルが選択されていません。");
            request.getRequestDispatcher("/WEB-INF/admin/breadAppendPage.jsp").forward(request, response);
            return;
        }
        if (!breadImage.getSubmittedFileName().endsWith(".png")) {
            request.setAttribute("errorMessage", "ファイル形式はpngにしてください");
            request.getRequestDispatcher("/WEB-INF/admin/breadAppendPage.jsp").forward(request, response);
            return;
        }
        InputStream inputStream = breadImage.getInputStream();
        byte[] fileBytes = inputStream.readAllBytes();
        String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
        model.setBreadName(breadName);
        model.setBreadPrice(breadPrice);
        model.setBreadDescription(breadDescription);
        model.setBreadImage(encodedImage);
        breadAppendPageLogic.appendBread(model);
        response.sendRedirect(request.getContextPath() + "/admin/breadList");
    }
}

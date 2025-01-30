package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface PageManager {
    boolean isLogin(HttpServletRequest request, HttpServletResponse response);
    boolean isFirstVisit();
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import java.util.*;

/**
 *
 * @author quang
 */
@WebServlet(name = "ProductsListServlet", urlPatterns = {"/productslist"})
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String search = request.getParameter("search");
        String active = request.getParameter("active");
        String categoryID = request.getParameter("category");
        int page = 1;
        int productPerPage = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("productPerPage") != null && !request.getParameter("productPerPage").isEmpty()) {
            productPerPage = Integer.parseInt(request.getParameter("productPerPage"));
        }

        MarketingProductDao mProductDao = new MarketingProductDao();
        CategoryDAO cDao = new CategoryDAO();
        List<Category> listCategoy = cDao.getCategoryAll();
        List<Product> lProductDetail = mProductDao.getAllProducts();

        if (categoryID != null && !categoryID.isEmpty()) {
            lProductDetail = mProductDao.filterByCategory(lProductDetail, Integer.parseInt(categoryID));
        }
        if (active != null && !active.isEmpty()) {
            lProductDetail = mProductDao.filterByActiveInList(lProductDetail, Integer.parseInt(active));
        }
        if (search != null && !search.isEmpty()) {
            lProductDetail = mProductDao.searchProduct(lProductDetail, search);
        }

        int numPages = (int) Math.ceil((double) lProductDetail.size() / productPerPage);
        if (page > numPages) {
            page = numPages;
        }
        lProductDetail = mProductDao.pagingBlog(lProductDetail, page, productPerPage);
        request.setAttribute("lProductDetail", lProductDetail);
        request.setAttribute("categories", listCategoy);
        request.setAttribute("currentPage", page);
        request.setAttribute("numPages", numPages);
        request.setAttribute("productPerPage", productPerPage);
        request.getRequestDispatcher("views/marketing/item-page/productlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int productID = Integer.parseInt(request.getParameter("productID"));

        MarketingProductDao mProductDao = new MarketingProductDao();
        mProductDao.updateStatus(productID);

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

}

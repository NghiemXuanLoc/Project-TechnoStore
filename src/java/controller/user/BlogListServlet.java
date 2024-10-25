/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import model.*;
import model.BlogDetail;
import dal.*;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.*;

/**
 *
 * @author quang
 */
@WebServlet(name = "BlogListServlet", urlPatterns = {"/bloglist"})
public class BlogListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String bid = request.getParameter("bid");
        String sBlog = request.getParameter("search");
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int iPage = Integer.parseInt(index);

        BlogDetailDAO bd = new BlogDetailDAO();
        BlogCategoryDAO bcatedao = new BlogCategoryDAO();

        // liệt kê ra danh sách tên các thể loại blog bên sider
        List<BlogCategory> bCategory = bcatedao.getListBlogCategory();
        // In ra 3 bài viết gần đây nhất bên sider right
        List<BlogDetail> lastBlog = bd.lastestBLog();
        List<BlogDetail> blogDetail;
        int numPages = 0;

        if (sBlog != null && !sBlog.isEmpty()) {
            // hiển thị ra các bài viết có chứa các kí tự sau khi nhập vào từ search bên sider 
            blogDetail = bd.pagingSearch(sBlog, iPage);
            numPages = bd.cPageSearch(sBlog);
        } else if (bid != null && !bid.isEmpty()) {
            //liệt kê ra danh sách các bài viết ( cụ thể tile, thumnail, author, time ) của mỗi bài đã được phân trang
            blogDetail = bd.pagingCategory(bid, iPage);
            numPages = bd.cPageCategory(bid);
        } else {
            blogDetail = bd.pagingBlog(iPage);
            numPages = bd.countNumPage();
        }

        request.setAttribute("bCategory", bCategory);
        request.setAttribute("bDetail", blogDetail);
        request.setAttribute("lastBlog", lastBlog);
        request.setAttribute("index", index);
        request.setAttribute("page", numPages);
        request.getRequestDispatcher("views/user/item-page/bloglist.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.*;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.*;

/**
 *
 * @author quang
 */
@WebServlet(name = "PostListServlet", urlPatterns = {"/postlist"})
public class PostListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String search = request.getParameter("search");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String active = request.getParameter("active");
        int page = 1;
        int postPerPage = 5;
        /*Phần hiển thị theo phân trang*/
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("postPerPage") != null && !request.getParameter("postPerPage").isEmpty()) {
            postPerPage = Integer.parseInt(request.getParameter("postPerPage"));
        }

        PostListDAO pd = new PostListDAO();
        List<BlogDetail> ld = pd.listAllPost();
        List<String> uAuthor = pd.getUniqueAuthor(ld);
        List<String> uCategory = pd.getUniqueCategories(ld);
        /* Phần filter */
        if (author != null && !author.isEmpty()) {
            ld = pd.filterByAuthorInList(ld, author);
        }

        if (category != null && !category.isEmpty()) {
            ld = pd.filterByBlogCateInList(ld, category);
        }

        if (active != null && !active.isEmpty()) {
            ld = pd.filterByActiveInList(ld, Integer.parseInt(active));
        }

        /*Phần search*/
        if (search != null && !search.isEmpty()) {
            ld = pd.searchByTitleInList(ld, search);
        }
        /*Đếm số lượng trang sau khi đếm số lượng bài viết mỗi trang*/
        int numPages = (int) Math.ceil((double) ld.size() / postPerPage);
        if (page > numPages) {
            page = numPages;
        }

        ld = pd.pagingBlog(ld, page, postPerPage);

        request.setAttribute("postlist", ld);
        request.setAttribute("uAuthor", uAuthor);
        request.setAttribute("uCategory", uCategory);
        request.setAttribute("numPages", numPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("postPerPage", postPerPage);
        request.getRequestDispatcher("views/marketing/item-page/postlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int blogID = Integer.parseInt(request.getParameter("blogID"));

        PostListDAO pd = new PostListDAO();
        pd.updateStatus(blogID);

        /*Gửi trực tiếp sau khi update trạng thái về trang hiện tại*/
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

}

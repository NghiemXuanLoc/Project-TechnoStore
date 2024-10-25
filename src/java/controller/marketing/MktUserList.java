/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.MarketingUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.User;

/**
 *
 * @author quang
 */
@WebServlet(name = "MktUserList", urlPatterns = {"/mktuserlist"})
public class MktUserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String search = request.getParameter("search");
        String active = request.getParameter("active");
        int page = 1;
        int userPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("userPerPage") != null && !request.getParameter("userPerPage").isEmpty()) {
            userPerPage = Integer.parseInt(request.getParameter("userPerPage"));
        }
        
        MarketingUserDAO sDao = new MarketingUserDAO();
        List<User> listU = sDao.getUserAll();
        if (active != null && !active.isEmpty()) {
            listU = sDao.filterByActive(listU, Integer.parseInt(active));
        }
        if (search != null && !search.isEmpty()) {
            listU = sDao.searchByTitle(listU, search);
        }
        
         int numPages = (int) Math.ceil((double) listU.size() / userPerPage);
        if (page > numPages) {
            page = numPages;
        }
        
        listU = sDao.pagingUser(listU, page, userPerPage);
        
        request.setAttribute("listU", listU);
        request.setAttribute("numPages", numPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("userPerPage", userPerPage);
        request.getRequestDispatcher("views/marketing/item-page/userlist.jsp").forward(request, response);

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int uId = Integer.parseInt(request.getParameter("uId"));
        MarketingUserDAO sDao = new MarketingUserDAO();
        sDao.updateStatus(uId);

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

}

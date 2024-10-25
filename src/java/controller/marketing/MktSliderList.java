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
@WebServlet(name = "MktSliderList", urlPatterns = {"/mktslider"})
public class MktSliderList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String search = request.getParameter("search");
        String active = request.getParameter("active");
        int page = 1;
        int sliderPerPage = 5;
        /*Phần hiển thị theo phân trang*/
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sliderPerPage") != null && !request.getParameter("sliderPerPage").isEmpty()) {
            sliderPerPage = Integer.parseInt(request.getParameter("sliderPerPage"));
        }

        MarketingSliderDao sDao = new MarketingSliderDao();
        List<Slider> listSlide = sDao.getSliderAll();

        if (active != null && !active.isEmpty()) {
            listSlide = sDao.filterByActive(listSlide, Integer.parseInt(active));
        }

        if (search != null && !search.isEmpty()) {
            listSlide = sDao.searchByTitle(listSlide, search);
        }

        int numPages = (int) Math.ceil((double) listSlide.size() / sliderPerPage);
        if (page > numPages) {
            page = numPages;
        }

        listSlide = sDao.pagingSlider(listSlide, page, sliderPerPage);

        request.setAttribute("sliderList", listSlide);
        request.setAttribute("numPages", numPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("postPerPage", sliderPerPage);
        request.getRequestDispatcher("views/marketing/item-page/sliderlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int sId = Integer.parseInt(request.getParameter("sId"));

        MarketingSliderDao sDao = new MarketingSliderDao();
        sDao.updateStatus(sId);

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.marketing;

import dal.MarketingSliderDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Slider;

/**
 *
 * @author quang
 */
@WebServlet(name="MktSliderDetail", urlPatterns={"/mktsliderdetail"})
public class MktSliderDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("sId");
        if (id != null && !id.isEmpty()) {
            int pID = Integer.parseInt(id);
            MarketingSliderDao bd = new MarketingSliderDao();
            Slider sdetail = bd.getSliderById(pID);

            request.setAttribute("sdetail", sdetail);
            request.getRequestDispatcher("views/marketing/item-page/sliderdetail.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("views/marketing/item-page/sliderdetail.jsp").forward(request, response);
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("sId");
        if (id != null && !id.isEmpty()) {
            int postID = Integer.parseInt(id);
            MarketingSliderDao bd = new MarketingSliderDao();
            bd.updateStatus(postID);
            response.sendRedirect(request.getContextPath() + "/mktsliderdetail");
        } else {
             request.getRequestDispatcher("views/marketing/item-page/mktsliderdetail.jsp").forward(request, response);
        }
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import dal.FeedbackProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FeedbackProduct;

/**
 *
 * @author PC
 */
@WebServlet(name="ViewFeedbackServlet", urlPatterns={"/viewfeedback"})
public class ViewFeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FeedbackProductDAO fpd = new FeedbackProductDAO();

        String feedbackId_raw = request.getParameter("feedbackId");

        try {
            int feedbackId = Integer.parseInt(feedbackId_raw);

            FeedbackProduct fp = fpd.getFeedbackByFeedbackId(feedbackId);

            request.setAttribute("fp", fp);
            request.setAttribute("view", "viewFeedback");

            request.setAttribute("productDetail", fp.getProductDetail());
            request.setAttribute("color", fp.getProductDetail().getColor());
            request.setAttribute("memory", fp.getProductDetail().getMemory());
            request.setAttribute("quantity", request.getParameter("quantity"));
            request.setAttribute("order", fp.getOrder());
            request.setAttribute("productDetailId", fp.getProductDetail().getProductDetailId());

            request.getRequestDispatcher("views/user/item-page/feedback.jsp").forward(request, response);

        } catch (Exception e) {
        }
    }
   
    
}

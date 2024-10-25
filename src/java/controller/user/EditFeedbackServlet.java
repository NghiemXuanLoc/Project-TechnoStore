/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.FeedbackProductDAO;
import dal.OrdersDAO;
import dal.ProductDetailDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.FeedbackProduct;
import model.Orders;
import model.ProductDetail;

/**
 *
 * @author PC
 */
@WebServlet(name = "EditFeedbackServlet", urlPatterns = {"/editfeedback"})
public class EditFeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        String fullName = request.getParameter("fullName");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String description = request.getParameter("description");

        String orderId = request.getParameter("orderId");
        String productDetailId = request.getParameter("productDetailId");

        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        UserDAO ud = new UserDAO();
        FeedbackProductDAO fpd = new FeedbackProductDAO();

        HttpSession session = request.getSession();

        Orders order = od.getOrderById(Integer.parseInt(orderId));
        ProductDetail productDetail = pdd.getProductDetailById(Integer.parseInt(productDetailId));

        FeedbackProduct fp = new FeedbackProduct(feedbackId, null, order, productDetail, null, null, rating, 1, description, fullName, gender, email, phoneNumber, 0);

        fpd.updateFeedback(fp);
        
        request.setAttribute("fp", fp); 

        request.setAttribute("productDetail", fp.getProductDetail());
        request.setAttribute("color", fp.getProductDetail().getColor());
        request.setAttribute("memory", fp.getProductDetail().getMemory());
        request.setAttribute("quantity", request.getParameter("quantity"));
        request.setAttribute("order", fp.getOrder());
        request.setAttribute("productDetailId", fp.getProductDetail().getProductDetailId());

        request.getRequestDispatcher("views/user/item-page/feedback.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FeedbackProductDAO fpd = new FeedbackProductDAO();

        String feedbackId_raw = request.getParameter("feedbackId");

        try {
            int feedbackId = Integer.parseInt(feedbackId_raw);

            FeedbackProduct fp = fpd.getFeedbackByFeedbackId(feedbackId);

            request.setAttribute("fp", fp);

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

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
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name="AddFeedbackServlet", urlPatterns={"/addfeedback"})
public class AddFeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        
        User account = (User)session.getAttribute("account");
        
        if(account == null){
            if(ud.getUserIdAnonymous() == -1){
                ud.insertUserAnonymous("anonymous", "anonyumous@gmail.com");
            }
            account = ud.getUserById(ud.getUserIdAnonymous());
        }
        
        
        FeedbackProduct fp = new FeedbackProduct(-1, account, order, productDetail, null, null, rating, 0, description, fullName, gender, email, phoneNumber, 0);
        
        fpd.addFeedbackProduct(fp);
        
        request.setAttribute("addSuccess", "feedback has been recorded");
        
        request.getRequestDispatcher("views/user/item-page/feedback.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
   
    
}

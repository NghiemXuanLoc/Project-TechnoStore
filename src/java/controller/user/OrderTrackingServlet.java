/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.OrderDetailDAO;
import dal.OrdersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Orders;
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name = "OrderTrackingServlet", urlPatterns = {"/ordertracking"})
public class OrderTrackingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        OrdersDAO od = new OrdersDAO();
        OrderDetailDAO odd = new OrderDetailDAO();

        String orderId_raw = request.getParameter("order-id").trim();

        System.out.println("don hang can tim: " + orderId_raw);

        try {
            int orderId = Integer.parseInt(orderId_raw);

            Orders order = od.getOrderById(orderId);

            // tim thay don hang
            if (order != null) {
                System.out.println("tim thay don hang");
                // lay ve chi tiet don hang do
                Cart cart = odd.getOrderDetailAllByOrderId(orderId);

                request.setAttribute("orderTracking", order);
                request.setAttribute("listOrderDetailTracking", cart);

                User account = (User) session.getAttribute("account");

                if (account == null) {
                    if(order.getUser().getRole().getRoleId() != 5){
                        request.setAttribute("error", "You can't view order details " + order.getOrderId() + " because you are not the buyer");
                    }
                } else {
                    if (order.getUser().getUserId() != account.getUserId()) {
                        request.setAttribute("error", "You need to log in to the account that purchased the order " + order.getOrderId() + " to see details");
                    }
                }

            } else {
                request.setAttribute("notFound", "Order not found with OrderId: " + orderId_raw);
                System.out.println("khong tim thay don hang");
            }

            request.getRequestDispatcher("views/user/item-page/ordertracking.jsp").forward(request, response);
        } catch (Exception e) {

            request.setAttribute("notFound", "Order not found with OrderId: #" + orderId_raw);
            request.getRequestDispatcher("views/user/item-page/ordertracking.jsp").forward(request, response);
        }
    }

}

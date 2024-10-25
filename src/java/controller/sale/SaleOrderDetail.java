/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sale;

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
import java.util.List;
import java.util.Map;
import model.OrderDetail;
import model.Orders;
import model.User;

@WebServlet(name = "SaleOrderDetail", urlPatterns = {"/saleorderdetail"})
public class SaleOrderDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDetailDAO od = new OrderDetailDAO();
        int orderId = od.getLatestOrderId();
        if (request.getParameter("orderId") != null) {
            orderId = Integer.parseInt(request.getParameter("orderId"));
        }
        
        OrdersDAO ood = new OrdersDAO();
        
        Orders order = ood.getOrderById(orderId);
        
        request.setAttribute("statusOrder", order.getStatus());
        request.setAttribute("saleId", order.getUserSale().getUserId());
        
        List<Map<String, Object>> list = od.getOrderDetailByOrderIdRight(orderId);
        List<Map<String, Object>> list2 = od.getOrderDetailByOrderIdLeft(orderId);
        List<Map<String, Object>> sumProduct = od.getOrderDetailForProductSumByOrderId(orderId);
        request.setAttribute("orderdetailist", sumProduct);
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("account");
        
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        List<User> salespersons = orderDetailDAO.getSalespersons();
        
        if (!list.isEmpty()) {
            request.setAttribute("orderdetail", list.get(0)); // Lấy ra đối tượng đầu tiên trong danh sách        
        }
        if (!list2.isEmpty()) {
            request.setAttribute("orderdetail2", list2.get(0)); // Lấy ra đối tượng đầu tiên trong danh sách        
        }
        
        User saleManger= orderDetailDAO.getManagerSale();
        request.setAttribute("salemanager",saleManger);
        request.setAttribute("salespersons", salespersons); 
        
        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("views/sale/item-page/orderdetail.jsp").forward(request, response);
    }

}

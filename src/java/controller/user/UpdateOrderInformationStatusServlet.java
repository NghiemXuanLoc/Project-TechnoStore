/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import dal.OrdersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name="UpdateOrderInformationStatusServlet", urlPatterns={"/updateorderinformationstatus"})
public class UpdateOrderInformationStatusServlet extends HttpServlet {
   
   
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession();
        // Gọi DAO để cập nhật trạng thái đơn hàng
        OrdersDAO ordersDAO = new OrdersDAO();
        boolean success = ordersDAO.updateOrderStatus(orderId, status);

        if (success) {
            
            session.setAttribute("successUpdateStatus", "Update Successfully");
            response.sendRedirect(request.getContextPath() + "/myorderdetail?orderId=" + orderId);
            

        } else {
           
            session.setAttribute("errUpdateStatus", "Failed update status");
            response.sendRedirect(request.getContextPath() + "/myorderdetail?orderId=" + orderId);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

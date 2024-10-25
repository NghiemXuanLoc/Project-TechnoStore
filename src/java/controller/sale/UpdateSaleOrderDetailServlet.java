/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sale;

import dal.OrderDetailDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.User;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "UpdateSaleOrderDetailServlet", urlPatterns = {"/updatesaleorderdetail"})
public class UpdateSaleOrderDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateSaleOrderDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSaleOrderDetailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int status = Integer.parseInt(request.getParameter("status"));
        String note = request.getParameter("note");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("account");
        System.out.println(currentUser.getUserId());
        OrderDetailDAO orderDAO = new OrderDetailDAO();
        boolean success = false;

        User saleManger = orderDAO.getManagerSale();

        if (currentUser != null && currentUser.getUserId() == saleManger.getUserId()) {
            int saleId = Integer.parseInt(request.getParameter("assignToSaleId"));
            success = orderDAO.updateOrderAll(orderId, status, note, saleId);
        } else {
            success = orderDAO.updateOrderStatusAndNote(orderId, status, note);
        }

        if (success) {
            session.setAttribute("successupdatestasusandnote", "Update Successfully");

        } else {
            session.setAttribute("errupdatestasusandnote", "Fail to update");

        }
        request.setAttribute("salemanager", saleManger);

        response.sendRedirect(request.getContextPath() + "/saleorderdetail?orderId=" + orderId);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

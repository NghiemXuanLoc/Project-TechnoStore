/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AdminDashboadDAO;
import dal.UserForAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.ChartItem;
import model.OrderGetTotal;
import model.Orders;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "AdminDashboard", urlPatterns = {"/adminDashboard"})
public class AdminDashboard extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminDashboard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashboard at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(7);
        AdminDashboadDAO dao = new AdminDashboadDAO();
        int success = dao.getSuccessOrderByDay(startDate.toString(), currentDate.toString());
        int pedding = dao.getPenddingOrderByDay(startDate.toString(), currentDate.toString());
        int cancel = dao.getCancelOrderByDay(startDate.toString(), currentDate.toString());
        int allOrder = success + pedding + cancel;
        int userRegisterToDay = dao.getUserRegisterToDay();
        List<OrderGetTotal> lchart = dao.getTotalByDay(startDate.toString(), currentDate.toString());
        List<ChartItem> listNewUserRegister = dao.getNewUserRegisterPerDay(startDate.toString(), currentDate.toString());
        List<ChartItem> listNewUserOrder = dao.getNewUserOrdersPerDay(startDate.toString(), currentDate.toString());
        List<ChartItem> listFeedBack = dao.getFeedBackPerDay(startDate.toString(), currentDate.toString());
        List<ChartItem> listSuccesOrderPerTotal = dao.getSuccesOrderPerTotal(startDate.toString(), currentDate.toString());
        
        BigDecimal totalOrder = BigDecimal.ZERO;
        
        for (OrderGetTotal orderGetTotal : lchart) {
            totalOrder = totalOrder.add(orderGetTotal.getTotalMoney());
        }
        
        request.setAttribute("successOrder", success);
        request.setAttribute("penddingOrder", pedding);
        request.setAttribute("cancelOrder", cancel);
        request.setAttribute("userRegisterToDay", userRegisterToDay);
        request.setAttribute("allOrder", allOrder);
        request.setAttribute("totalOrderByDay", totalOrder);
        
        request.setAttribute("allTotalOrder", lchart);
        request.setAttribute("allTotalNewUser", listNewUserRegister);
        request.setAttribute("allTotalNewOrder", listNewUserOrder);
        request.setAttribute("allFeedback", listFeedBack);
        request.setAttribute("listSuccesOrderPerTotal", listSuccesOrderPerTotal);
        request.setAttribute("startDate", startDate.toString());
        request.setAttribute("endDate", currentDate.toString());
        request.getRequestDispatcher("views/admin/home-page/adminhomepage.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    
        String startDate = request.getParameter("startDate");
        String currentDate = request.getParameter("endDate");
        HttpSession session = request.getSession();
        LocalDate currentDateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        try {
            LocalDate parsedDate = LocalDate.parse(currentDate, formatter);
            LocalDate parseStarDate = LocalDate.parse(startDate, formatter);
            if (parsedDate.isAfter(currentDateNow)) {
                session.setAttribute("errDate", "The end date must be before today");
                response.sendRedirect("adminDashboard");
                return;
            } 
            if(parseStarDate.isAfter(currentDateNow)){
                session.setAttribute("errDate", "The start date must be before today");
                response.sendRedirect("adminDashboard");
                return;
            }
            
            if(parsedDate.isBefore(parseStarDate)){
                session.setAttribute("errDate", "The start date must be before end date");
                response.sendRedirect("adminDashboard");
                return;
            }
            
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + currentDate);
        }
        AdminDashboadDAO dao = new AdminDashboadDAO();
        int success = dao.getSuccessOrderByDay(startDate, currentDate);
        int pedding = dao.getPenddingOrderByDay(startDate.toString(), currentDate.toString());
        int cancel = dao.getCancelOrderByDay(startDate.toString(), currentDate.toString());
        int allOrder = success + pedding + cancel;
        int userRegisterToDay = dao.getUserRegisterToDay();
        
        List<OrderGetTotal> lchart = dao.getTotalByDay(startDate.toString(), currentDate.toString());
        List<ChartItem> listNewUserRegister = dao.getNewUserRegisterPerDay(startDate.toString(), currentDate.toString());
        List<ChartItem> listNewUserOrder = dao.getNewUserOrdersPerDay(startDate.toString(), currentDate.toString());
        List<ChartItem> listFeedBack = dao.getFeedBackPerDay(startDate.toString(), currentDate.toString());
        List<ChartItem> listSuccesOrderPerTotal = dao.getSuccesOrderPerTotal(startDate.toString(), currentDate.toString());
        BigDecimal totalOrder = BigDecimal.ZERO;
        
        for (OrderGetTotal orderGetTotal : lchart) {
            totalOrder = totalOrder.add(orderGetTotal.getTotalMoney());
        }
        
        
        
        request.setAttribute("successOrder", success);
        request.setAttribute("penddingOrder", pedding);
        request.setAttribute("cancelOrder", cancel);
        request.setAttribute("userRegisterToDay", userRegisterToDay);
        request.setAttribute("allOrder", allOrder);
        request.setAttribute("allTotalOrder", lchart);
        request.setAttribute("totalOrderByDay", totalOrder);
        request.setAttribute("allTotalNewUser", listNewUserRegister);
        request.setAttribute("allTotalNewOrder", listNewUserOrder);
        request.setAttribute("allFeedback", listFeedBack);
        request.setAttribute("listSuccesOrderPerTotal", listSuccesOrderPerTotal);
        request.setAttribute("startDate", startDate.toString());
        request.setAttribute("endDate", currentDate.toString());
        request.getRequestDispatcher("views/admin/home-page/adminhomepage.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

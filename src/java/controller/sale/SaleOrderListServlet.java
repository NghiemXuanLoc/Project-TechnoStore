/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sale;

import dal.OrdersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "SaleOrderListServlet", urlPatterns = {"/saleorderlist"})
public class SaleOrderListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaleOrderListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleOrderListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrdersDAO od = new OrdersDAO();

        int page = 1;
        int pageSize = 5;
        String listType = "all"; // Default list type
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        if (request.getParameter("listType") != null) {
            listType = request.getParameter("listType");
        }
        String startdateStringStr = request.getParameter("startDate");
        String enddateStringStr = request.getParameter("endDate");
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startdateStringStr != null && !startdateStringStr.isEmpty()) {
                startDate = parseDate.parse(startdateStringStr);
            }
            if (enddateStringStr != null && !enddateStringStr.isEmpty()) {
                endDate = parseDate.parse(enddateStringStr);
            }
            // Nếu cả hai ngày đều null, thiết lập end date là ngày hiện tại và start date là 7 ngày trước đó
            if (startDate == null && endDate == null) {
                startDate = od.getEarliestOrderDate();
                endDate = new Date();
            }
            // Nếu chỉ có startDate, thiết lập endDate thành ngày hiện tại
            if (startDate != null && endDate == null) {
                endDate = new Date();
            }
            // Nếu chỉ có endDate, thiết lập startDate thành một ngày trước endDate
            if (startDate == null && endDate != null) {
                startDate = new Date(endDate.getTime() - (7 * 24 * 60 * 60 * 1000)); // 7 days before endDate
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startDate == null) {
            startDate = new Date();
        }
        if (endDate == null) {
            endDate = new Date();
        }
        int status = -1;
// Get status filter
        if (request.getParameter("status") != null && !request.getParameter("status").isEmpty()) {
            status = Integer.parseInt(request.getParameter("status"));
        }
        System.out.println(status);
        // Get product name filter
        String productName = request.getParameter("productname");
        System.out.println(productName);
        // Get search keyword
        String searchKeyword = request.getParameter("searchKeyword");

        List<Map<String, Object>> orderProductDetails = od.getOrderListAll(startDate, endDate, page, pageSize, productName, status, searchKeyword);

        System.out.println("jnsfnasdasdosjdoj");
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("listType", listType);

        int totalRecords = od.getTotalOrdersDaybyDay(startDate, endDate, productName, status, searchKeyword);
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("orderlist", orderProductDetails);
        request.setAttribute("startDate", startdateStringStr);
        request.setAttribute("endDate", enddateStringStr);
        request.setAttribute("productname", productName);
        request.setAttribute("keysearch", searchKeyword);
        request.getRequestDispatcher("views/sale/item-page/orderslist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

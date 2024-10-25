/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sale;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.OrderDetailDAO;
import dal.OrdersDAO;
import dal.SaleDashBoardDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChartItem;
import model.User;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "SaleDashboardServlet", urlPatterns = {"/saledashboard"})
public class SaleDashboardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaleDashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleDashboardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Lấy dữ liệu từ các DAO
        UserDAO usersDAO = new UserDAO();
        
        User account = (User)session.getAttribute("account");
        OrdersDAO ordersDAO = new OrdersDAO();
        OrderDetailDAO odd = new OrderDetailDAO();
        int totalOrders = ordersDAO.getTotalOrders();
        int successfulOrders = ordersDAO.getSuccessfulOrders();
        int totalUsers = usersDAO.getTotalUsers();
        String totalMoneySold = ordersDAO.getTotalMoneySold();
        // Lấy start date và end date từ request

        String startDateStr = request.getParameter("startDate");
        String startDateStr2 = request.getParameter("startDate2");

        String endDateStr = request.getParameter("endDate");
        String endDateStr2 = request.getParameter("endDate2");
        // Tiến hành tính toán success ratios
        Date startDate = null;
        Date startDate2 = null;
        Date endDate = null;
        Date endDate2 = null;

        List<ChartItem> successRatios = new ArrayList<>();
        List<ChartItem> countStatus = new ArrayList<>();
        List<ChartItem> dailyRevenueList = new ArrayList<>();
//        List<Map<String, Object>> orderProductDetails = odd.getOrderProductDetails();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String defaultStartDate = null;
        String defaultStartDate1 = null;
        String defaultEndDate = null;
        String defaultEndDate1 = null;
        try {
            if (startDateStr2 != null && !startDateStr2.isEmpty()) {
                startDate2 = dateFormat.parse(startDateStr2);
            }
            if (endDateStr2 != null && !endDateStr2.isEmpty()) {
                endDate2 = dateFormat.parse(endDateStr2);
            }
            // Nếu cả hai ngày đều null, thiết lập end date là ngày hiện tại và start date là 7 ngày trước đó
            if (startDate2 == null && endDate2 == null) {
                endDate2 = new Date();
                startDate2 = new Date(endDate2.getTime() - (8 * 24 * 60 * 60 * 1000)); // 7 days before endDate
            }
            // Nếu chỉ có startDate, thiết lập endDate thành ngày hiện tại
            if (startDate2 != null && endDate2 == null) {
                endDate2 = new Date();
            }
            // Nếu chỉ có endDate, thiết lập startDate thành một ngày trước endDate
            if (startDate2 == null && endDate2 != null) {
                startDate2 = new Date(endDate2.getTime() - (8 * 24 * 60 * 60 * 1000)); // 7 days before endDate
            }
            defaultStartDate = dateFormat.format(startDate2);
            defaultEndDate = dateFormat.format(endDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = dateFormat.parse(startDateStr);
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = dateFormat.parse(endDateStr);
            }
            // Nếu cả hai ngày đều null, thiết lập end date là ngày hiện tại và start date là 7 ngày trước đó
            if (startDate == null && endDate == null) {
                endDate = new Date();
                startDate = new Date(endDate.getTime() - (8 * 24 * 60 * 60 * 1000)); // 7 days before endDate
            }
            // Nếu chỉ có startDate, thiết lập endDate thành ngày hiện tại
            if (startDate != null && endDate == null) {
                endDate = new Date();
            }
            // Nếu chỉ có endDate, thiết lập startDate thành một ngày trước endDate
            if (startDate == null && endDate != null) {
                startDate = new Date(endDate.getTime() - (8 * 24 * 60 * 60 * 1000)); // 7 days before endDate
            }
            defaultStartDate1 = dateFormat.format(startDate);
            defaultEndDate1 = dateFormat.format(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateRange = request.getParameter("dateRange");
        Date startDateRecently = null;
        Date endDateRecently = null;
//        if (dateRange != null) {
//            if ("today".equals(dateRange)) {
//                startDateRecently = new Date(System.currentTimeMillis());
//                endDateRecently = new Date(System.currentTimeMillis());
//            } else if ("yesterday".equals(dateRange)) {
//                startDateRecently = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
//                endDateRecently = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
//            } else if ("lastmonth".equals(dateRange)) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.add(Calendar.MONTH, -1);
//                calendar.set(Calendar.DAY_OF_MONTH, 1);
//                startDateRecently = calendar.getTime();
//
//                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//                endDateRecently = calendar.getTime();
//            } else if ("custom".equals(dateRange)) {
//                String startDateStr3 = request.getParameter("startDate3");
//                String endDateStr3 = request.getParameter("endDate3");
//                try {
//                    if (startDateStr3 != null && !startDateStr3.isEmpty()) {
//                        startDateRecently = dateFormat.parse(startDateStr3);
//                    }
//                    if (endDateStr3 != null && !endDateStr3.isEmpty()) {
//                        endDateRecently = dateFormat.parse(endDateStr3);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
// Adjusting the start and end dates to cover the full day
        if (dateRange != null) {
            if ("today".equals(dateRange)) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startDateRecently = calendar.getTime();

                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                endDateRecently = calendar.getTime();
            } else if ("yesterday".equals(dateRange)) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startDateRecently = calendar.getTime();

                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                endDateRecently = calendar.getTime();
            } else if ("custom".equals(dateRange)) {
                String startDateStr3 = request.getParameter("startDate3");
                String endDateStr3 = request.getParameter("endDate3");
                try {
                    if (startDateStr3 != null && !startDateStr3.isEmpty()) {
                        startDateRecently = dateFormat.parse(startDateStr3 + " 00:00:00");
                    }
                    if (endDateStr3 != null && !endDateStr3.isEmpty()) {
                        endDateRecently = dateFormat.parse(endDateStr3 + " 23:59:59");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        // Ensure startDateRecently and endDateRecently are not null before using them
        if (startDateRecently == null) {
            startDateRecently = new Date(System.currentTimeMillis() - 6 * 24 * 60 * 60 * 1000);
        }
        if (endDateRecently == null) {
            endDateRecently = new Date(System.currentTimeMillis());
        }
        System.out.println("Start DateRecent: " + startDateRecently);
        System.out.println("End DateRecent: " + endDateRecently);
        List<Map<String, Object>> orderProductDetails = ordersDAO.getOrderProductDetailsByDateRange(startDateRecently, endDateRecently);
        if (orderProductDetails.isEmpty()) {
            System.out.println("No orders found for the given date range.");
        } else {
            System.out.println("Orders found: " + orderProductDetails.size());
        }
        System.out.println(orderProductDetails);
        SaleDashBoardDAO saleDashBoardDAO = new SaleDashBoardDAO();
        successRatios = saleDashBoardDAO.getOrderSuccessRatio(startDate, endDate);
        countStatus = saleDashBoardDAO.getOrderStatusCounts();
        dailyRevenueList = saleDashBoardDAO.getDailyRevenue(startDate2, endDate2);

        request.setAttribute("startDate", startDateStr);
        request.setAttribute("startDate2", startDateStr2);
        request.setAttribute("endDate", endDateStr);
        request.setAttribute("endDate2", endDateStr2);
        request.setAttribute("totalMoneySold", totalMoneySold);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalUsers", totalUsers);

        request.setAttribute("chart", successRatios);
        request.setAttribute("countStatus", countStatus);
        request.setAttribute("revenue", dailyRevenueList);
        request.setAttribute("recentorders", orderProductDetails);
        request.setAttribute("defaultStartDate", defaultStartDate);
        request.setAttribute("defaultStartDate1", defaultStartDate1);
        request.setAttribute("defaultEndDate", defaultEndDate);
        request.setAttribute("defaultEndDate1", defaultEndDate1);
        request.setAttribute("userId", account.getUserId());
        request.getRequestDispatcher("views/sale/item-page/saledashboard.jsp").forward(request, response);
//kcoder
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

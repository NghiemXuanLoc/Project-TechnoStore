/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.MarketingDashBoardDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static org.apache.tomcat.util.http.FastHttpDateFormat.getCurrentDate;

/**
 *
 * @author quang
 */
@WebServlet(name = "MktDashBoard", urlPatterns = {"/mktdashboard"})
public class MktDashBoard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        MarketingDashBoardDAO mDashBoard = new MarketingDashBoardDAO();
        int numPost = mDashBoard.getNumberPost();
        int numProduct = mDashBoard.getNumberProduct();
        int numCustomer = mDashBoard.getNumberCustomer();
        int numFeedback = mDashBoard.getNumberFeedback();
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(7);

        List<Map<String, Object>> lineChart = mDashBoard.getLineChart(startDate.toString(), currentDate.toString());
        Map<String, Double> pieChart = mDashBoard.getPieChart(startDate.toString(), currentDate.toString());
        List<Map<String, Object>> lineChart2 = mDashBoard.getLineChart2(startDate.toString(), currentDate.toString());
        request.setAttribute("numPost", numPost);
        request.setAttribute("numProduct", numProduct);
        request.setAttribute("numCustomer", numCustomer);
        request.setAttribute("numFeedback", numFeedback);
        request.setAttribute("currentDate", currentDate);
        request.setAttribute("startDate", startDate);
        request.setAttribute("customerTrend", lineChart);
        request.setAttribute("lineChart2", lineChart2);
        request.setAttribute("pieChart", pieChart);
        request.getRequestDispatcher("views/marketing/item-page/marketingdashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        MarketingDashBoardDAO mDashBoard = new MarketingDashBoardDAO();

        int numPost = mDashBoard.getNumberPost();
        int numProduct = mDashBoard.getNumberProduct();
        int numCustomer = mDashBoard.getNumberCustomer();
        int numFeedback = mDashBoard.getNumberFeedback();

        List<Map<String, Object>> lineChart = mDashBoard.getLineChart(startDate, endDate);
        Map<String, Double> pieChart = mDashBoard.getPieChart(startDate, endDate);
        List<Map<String, Object>> lineChart2 = mDashBoard.getLineChart2(startDate, endDate);

        request.setAttribute("numPost", numPost);
        request.setAttribute("numProduct", numProduct);
        request.setAttribute("numCustomer", numCustomer);
        request.setAttribute("numFeedback", numFeedback);
        request.setAttribute("customerTrend", lineChart);
        request.setAttribute("lineChart2", lineChart2);
        request.setAttribute("pieChart", pieChart);
        request.getRequestDispatcher("views/marketing/item-page/marketingdashboard.jsp").forward(request, response);
    }

}

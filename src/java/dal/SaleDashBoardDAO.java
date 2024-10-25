/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.ChartItem;
import model.Orders;

/**
 *
 * @author BUI TUAN DAT
 */
public class SaleDashBoardDAO extends DBContext {

    
    public List<ChartItem> getOrderSuccessRatio(Date startDate, Date endDate) {
        List<ChartItem> successRatios = new ArrayList<>();
        String sql = "SELECT CONVERT(DATE, Order_date) AS Order_date, COUNT(*) AS TotalOrders, SUM(CASE WHEN Status = 2 THEN 1 ELSE 0 END) AS SuccessfulOrders \n"
                + "FROM Orders \n"
                + "WHERE CONVERT(DATE, Order_date) BETWEEN ? AND ? \n"
                + "GROUP BY CONVERT(DATE, Order_date) \n"
                + "ORDER BY CONVERT(DATE, Order_date) ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(startDate.getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date orderDate = rs.getDate("Order_date"); // Lấy chỉ ngày tháng từ cột Order_date
                int totalOrders = rs.getInt("TotalOrders");
                int successfulOrders = rs.getInt("SuccessfulOrders");
                
                // Tính tỷ lệ thành công
                float successRatio = totalOrders == 0 ? 0 : (float) successfulOrders / totalOrders;

                // Format tỷ lệ thành công làm tròn đến hai chữ số thập phân
                DecimalFormat df = new DecimalFormat("#.##");
                successRatio = Float.parseFloat(df.format(successRatio));

                // Tạo đối tượng ChartItem và thêm vào danh sách
                ChartItem item = new ChartItem(totalOrders, successfulOrders, successRatio, orderDate);
                successRatios.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return successRatios;
    }

    public List<ChartItem> getOrderStatusCounts() {
        List<ChartItem> statusCounts = new ArrayList<>();
        String sql = "SELECT "
                + "SUM(CASE WHEN Status = 0 THEN 1 ELSE 0 END) AS Unpaid, "
                + "SUM(CASE WHEN Status = 1 THEN 1 ELSE 0 END) AS Cancelled, "
                + "SUM(CASE WHEN Status = 2 THEN 1 ELSE 0 END) AS Completed "
                + "FROM Orders";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cancelledCount = rs.getInt("Cancelled");
                int completedCount = rs.getInt("Completed");
                int unpaidCount = rs.getInt("Unpaid");

                // Create ChartItem objects for each status count
                ChartItem i = new ChartItem();
                i.setCancelledCount(cancelledCount);
                i.setCompletedCount(completedCount);
                i.setUnpaidCount(unpaidCount);
                statusCounts.add(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return statusCounts;
    }

    public List<ChartItem> getDailyRevenue(Date startDate, Date endDate) {
        List<ChartItem> dailyRevenueList = new ArrayList<>();
        String sql = "SELECT  CONVERT(DATE, Order_date) AS OrderDate,SUM(Total_money) AS DailyRevenue FROM Orders \n"
                + "WHERE CONVERT(DATE, Order_date) BETWEEN ? AND ?\n"
                + "GROUP BY CONVERT(DATE, Order_date)\n"
                + "ORDER BY CONVERT(DATE, Order_date);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(startDate.getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Retrieve data
                Date orderDate = rs.getDate("OrderDate"); // Lấy dữ liệu kiểu Date từ cột OrderDate
                double dailyRevenue = rs.getDouble("DailyRevenue");
                ChartItem item = new ChartItem();
                item.setOrderDate(orderDate);
                item.setDailyRevenue((float) dailyRevenue);
                dailyRevenueList.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Return the list of daily revenue items
        return dailyRevenueList;
    }

    public static void main(String[] args) {
        SaleDashBoardDAO sdb = new SaleDashBoardDAO();
        Date startDate = new Date();
        List<ChartItem> osr = sdb.getOrderStatusCounts();

        System.out.println(osr.get(0));
    }
}

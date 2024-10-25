/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ChartItem;
import model.OrderGetTotal;
import model.Orders;
import model.User;

/**
 *
 * @author admin
 */
public class AdminDashboadDAO extends DBContext {

//    public int getCountOrder(String startDate, String endDate) {
//        int success = 0;
//        String sql = "SELECT \n"
//                + "    CAST(order_date AS DATE) AS OrderDate, \n"
//                + "    Status, \n"
//                + "    COUNT(*) AS OrderCount \n"
//                + "FROM Orders\n"
//                + "WHERE CAST(order_date AS DATE) BETWEEN @StartDate AND @EndDate \n"
//                + "  AND deleted = 0 \n"
//                + "GROUP BY CAST(order_date AS DATE), Status \n"
//                + "ORDER BY OrderDate, Status;";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                return success = rs.getInt(1);
//            }
//        } catch (Exception e) {
//        }
//        return success;
//    }
    public int getUserRegisterToDay() {
        int sum = 0;
        String sql = "select count(User_id) from Users\n"
                + "	where created_at = CAST(GETDATE() AS DATE) and deleted = 0 ;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return sum = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return sum;
    }

    public int getSuccessOrderByDay(String startDate, String endDate) {
        int success = 0;
        String sql = "SELECT \n"
                + "    COUNT([Status]) AS CancelledOrders\n"
                + "FROM Orders\n"
                + "WHERE [Status] = 2 \n"
                + "  AND [Order_date] >= ?\n"
                + "  AND [Order_date] <= DATEADD(day, 1, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return success = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return success;
    }

    public int getPenddingOrderByDay(String startDate, String endDate) {
        int success = 0;
        String sql = "SELECT \n"
                + "    COUNT([Status]) AS CancelledOrders\n"
                + "FROM Orders\n"
                + "WHERE [Status] = 0 \n"
                + "  AND [Order_date] >= ?\n"
                + "  AND [Order_date] <= DATEADD(day, 1, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return success = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return success;
    }

    public int getCancelOrderByDay(String startDate, String endDate) {
        int success = 0;
        String sql = "SELECT \n"
                + "    COUNT([Status]) AS CancelledOrders\n"
                + "FROM Orders\n"
                + "WHERE [Status] = 1 \n"
                + "  AND [Order_date] >= ?\n"
                + "  AND [Order_date] <= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return success = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return success;
    }

    public List<OrderGetTotal> getTotalByDay(String startDate, String endDate) {
        String sql = "SELECT \n"
                + "    CAST(Order_date AS DATE) AS OrderDate,\n"
                + "    SUM(Total_money) AS TotalRevenue\n"
                + "FROM \n"
                + "    Orders\n"
                + "WHERE \n"
                + "    Order_date >= ? \n"
                + "    AND Order_date <= DATEADD(day, 1, ?) \n"
                + "    AND deleted = 0\n"
                + "	AND Status = 2\n"
                + "GROUP BY \n"
                + "    CAST(Order_date AS DATE)\n"
                + "ORDER BY \n"
                + "    OrderDate;";
        List<OrderGetTotal> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal dailyRevenue = rs.getBigDecimal(2).setScale(2, RoundingMode.CEILING.HALF_UP);
                java.sql.Date orderDate = rs.getDate(1);

                OrderGetTotal order = new OrderGetTotal(dailyRevenue, orderDate, 0);
                list.add(order);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ChartItem> getNewUserRegisterPerDay(String startDate, String endDate) {
        String sql = "SELECT \n"
                + "    CAST(created_at AS DATE) AS RegistrationDate, \n"
                + "    COUNT(User_id) AS NumberOfNewUsers\n"
                + "FROM Users\n"
                + "WHERE created_at BETWEEN ? AND ?\n"
                + "GROUP BY CAST(created_at AS DATE)\n"
                + "ORDER BY RegistrationDate;";
        List<ChartItem> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChartItem chart = new ChartItem(rs.getInt(2), 0, 0, (java.sql.Date) rs.getDate(1));
                list.add(chart);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ChartItem> getNewUserOrdersPerDay(String startDate, String endDate) {
        String sql = "SELECT CAST(Order_date AS DATE) AS PurchaseDate,   COUNT(DISTINCT User_id) AS UniqueCustomers\n"
                + "	FROM Orders\n"
                + "	Where\n"
                + "	Order_date >= ? \n"
                + "    AND Order_date <  DATEADD(day, 1, ?)\n"
                + "	GROUP BY CAST(Order_date AS DATE)\n"
                + "	ORDER BY PurchaseDate;";
        List<ChartItem> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChartItem chart = new ChartItem(rs.getInt(2), 0, 0, (java.sql.Date) rs.getDate(1));
                list.add(chart);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ChartItem> getFeedBackPerDay(String startDate, String endDate) {
        String sql = "SELECT \n"
                + "    CAST(Feedback_date AS DATE) AS FeedbackDate, \n"
                + "    COUNT(Feedback_id) AS TotalFeedbacks,        \n"
                + "    AVG(CAST(Rating AS FLOAT)) AS AverageRating  \n"
                + "FROM Feedback_product\n"
                + "WHERE Feedback_date >= ? AND Feedback_date < DATEADD(day, 1, ?)\n"
                + "  AND deleted = 0                              \n"
                + "GROUP BY CAST(Feedback_date AS DATE)\n"
                + "ORDER BY FeedbackDate;";
        List<ChartItem> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql); // ly do khong lay duoc ngay hien tai la do date time
            ps.setString(1, startDate);
            LocalDate endDateConverted = LocalDate.parse(endDate).plusDays(1); // cong them 1 ngay de co the lay duoc ngay hien tai
            ps.setString(2, endDateConverted.toString()); // chuyen ve String de co the dua vao query 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChartItem chart = new ChartItem(rs.getInt(3), 0, 0, (java.sql.Date) rs.getDate(1));
                list.add(chart);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ChartItem> getSuccesOrderPerTotal(String startDate, String endDate) {
        String sql = "SELECT \n"
                + "    CAST(Order_date AS DATE) AS OrderDate,\n"
                + "    COUNT(Order_id) AS TotalOrders,\n"
                + "    SUM(CASE WHEN Status = 2 THEN 1 ELSE 0 END) AS SuccessfulOrders \n"
                + "FROM \n"
                + "    Orders\n"
                + "WHERE \n"
                + "    Order_date >= ? \n"
                + "    AND Order_date <  DATEADD(day, 1, ?)\n"
                + "    AND deleted = 0  \n"
                + "GROUP BY \n"
                + "    CAST(Order_date AS DATE)\n"
                + "ORDER BY \n"
                + "    OrderDate;";
        List<ChartItem> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChartItem chart = new ChartItem(rs.getInt(2), rs.getInt(3), 0, (java.sql.Date) rs.getDate(1));
                list.add(chart);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        AdminDashboadDAO dao = new AdminDashboadDAO();
        List<ChartItem> a = dao.getFeedBackPerDay("2024-06-25", "2024-07-11");
        List<ChartItem> n = dao.getSuccesOrderPerTotal("2024-06-25", "2024-07-11");
        List<ChartItem> listNewUserRegister = dao.getNewUserOrdersPerDay("2024-06-25", "2024-07-08");
        for (ChartItem chartItem : a) {
            System.out.println("A: " + chartItem.getOrderDate());
        }

        for (ChartItem chartItem : listNewUserRegister) {
            System.out.println("C: " + chartItem.getOrderDate());
        }

        for (ChartItem chartItem : n) {
            System.out.println("B: " + chartItem.getSuccessOrders());
        }

    }
}

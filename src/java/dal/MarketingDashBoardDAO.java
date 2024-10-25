/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.*;
import model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author quang
 */
public class MarketingDashBoardDAO extends DBContext {

    public int getNumberPost() {
        String sql = "SELECT COUNT(*) FROM Blog_detail";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNumberProduct() {
        String sql = "SELECT COUNT(*) FROM Product";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNumberCustomer() {
        String sql = "SELECT COUNT(*) FROM USERS WHERE Role_id = 4";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNumberFeedback() {
        String sql = "SELECT COUNT(*) FROM Feedback_product WHERE deleted = 0";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //biểu đồ đường theo 3 nhóm tuổi
    public List<Map<String, Object>> getLineChart(String startDate, String endDate) {
        String sql = """
                     SELECT 
                       CONVERT(date, u.created_at) AS Date, 
                       SUM(CASE WHEN DATEDIFF(YEAR, u.Date_of_birth, GETDATE()) <= 18 THEN 1 ELSE 0 END) AS Group18, 
                       SUM(CASE WHEN DATEDIFF(YEAR, u.Date_of_birth, GETDATE()) BETWEEN 19 AND 30 THEN 1 ELSE 0 END) AS Group19_30, 
                       SUM(CASE WHEN DATEDIFF(YEAR, u.Date_of_birth, GETDATE()) > 30 THEN 1 ELSE 0 END) AS Group31 
                     FROM 
                       Users u 
                     WHERE 
                       u.Role_id = 4 
                       AND u.created_at >= ?
                       AND u.created_at <= ?
                     GROUP BY 
                       CONVERT(date, u.created_at) 
                     ORDER BY 
                       CONVERT(date, u.created_at)""";
        List<Map<String, Object>> result = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Date", rs.getDate(1));
                row.put("18", rs.getInt(2));
                row.put("19-30", rs.getInt(3));
                row.put("31+", rs.getInt(4));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //biểu đồ tròn theo giới tính
    public Map<String, Double> getPieChart(String startDate, String endDate) {
        String sql = "SELECT "
                + "SUM(CASE WHEN Gender = 1 THEN 1 ELSE 0 END) AS MaleCount, "
                + "SUM(CASE WHEN Gender = 0 THEN 1 ELSE 0 END) AS FemaleCount, "
                + "COUNT(User_id) AS TotalCount "
                + "FROM Users "
                + "WHERE Role_id = 4 "
                + "AND created_at >= ? "
                + "AND created_at <= ?";
        Map<String, Double> result = new HashMap<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int maleCount = rs.getInt("MaleCount");
                int femaleCount = rs.getInt("FemaleCount");
                int totalCount = rs.getInt("TotalCount");

                double malePercentage = (double) maleCount / totalCount * 100;
                double femalePercentage = (double) femaleCount / totalCount * 100;

                result.put("MalePercentage", malePercentage);
                result.put("FemalePercentage", femalePercentage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Map<String, Object>> getLineChart2(String startDate, String endDate) {
        String sql = "SELECT "
                + "CAST(u.created_at AS DATE) AS Date, "
                + "SUM(CASE WHEN u.Gender = 1 THEN 1 ELSE 0 END) AS MaleCount, "
                + "SUM(CASE WHEN u.Gender = 0 THEN 1 ELSE 0 END) AS FemaleCount "
                + "FROM Users u "
                + "WHERE u.Role_id = 4 " // Assuming Role_id 4 represents users
                + "AND u.created_at >= ? "
                + "AND u.created_at <= ? "
                + "GROUP BY CAST(u.created_at AS DATE) "
                + "ORDER BY Date";

        List<Map<String, Object>> result = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Date", rs.getDate("Date"));
                row.put("MaleCount", rs.getInt("MaleCount"));
                row.put("FemaleCount", rs.getInt("FemaleCount"));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        MarketingDashBoardDAO mdash = new MarketingDashBoardDAO();
        String startDate = "2024-07-08"; // Replace with your desired start date
        String endDate = "2024-07-16";   // Replace with your desired end date

        List<Map<String, Object>> results = mdash.getLineChart(startDate, endDate);
        for (Map<String, Object> row : results) {
            System.out.println("Date: " + row.get("Date"));
            System.out.println("18 and under: " + row.get("18"));
            System.out.println("19-30: " + row.get("19-30"));
            System.out.println("31+: " + row.get("31+"));
        }
        // Call the getBarChart method to retrieve data
        List<Map<String, Object>> barChartData = mdash.getLineChart2(startDate, endDate);
        // Print the results (modify this section to suit your needs)
        System.out.println("Bar Chart Data:");
        for (Map<String, Object> row : barChartData) {
            System.out.println("Date: " + row.get("Date"));
            System.out.println("Male Count: " + row.get("MaleCount"));
            System.out.println("Female Count: " + row.get("FemaleCount"));
            System.out.println("-------");
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Category;

/**
 *
 * @author BUI TUAN DAT
 */
public class MyOrderDAO extends DBContext {

//    public List<Map<String, Object>> getOrderByOrderId(int page, int pageSize, String searchKeyword, int category, int userId) {
//        List<Map<String, Object>> orderProductDetails = new ArrayList<>();
//        StringBuilder sql = new StringBuilder(
//                "SELECT Category.Category_id AS Category_id, Category.Category_name, Order_detail.Order_id, Product.Product_name, Orders.Order_date, Order_detail.Quantity, Order_detail.Price, Orders.Status "
//                + "FROM Orders "
//                + "INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id "
//                + "INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id "
//                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
//                + "INNER JOIN Category ON Product.Category_id = Category.Category_id "
//                + "WHERE Orders.User_id = ? "
//        );
//
//        if (searchKeyword != null && !searchKeyword.isEmpty()) {
//            sql.append("AND (Category.Category_name LIKE ? OR Product.Product_name LIKE ?) ");
//        }
//
//        if (category != -1) {
//            sql.append("AND Category.Category_id = ? ");
//        }
//
//        sql.append("ORDER BY Orders.Order_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
//
//        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
//            int parameterIndex = 1;
//            ps.setInt(parameterIndex++, userId);
//
//            if (searchKeyword != null && !searchKeyword.isEmpty()) {
//                String keywordPattern = "%" + searchKeyword + "%";
//                ps.setString(parameterIndex++, keywordPattern);
//                ps.setString(parameterIndex++, keywordPattern);
//            }
//
//            if (category != -1) {
//                ps.setInt(parameterIndex++, category);
//            }
//
//            ps.setInt(parameterIndex++, (page - 1) * pageSize);
//            ps.setInt(parameterIndex++, pageSize);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Map<String, Object> row = new HashMap<>();
//                row.put("Category_id", rs.getInt("Category_id"));
//                row.put("Category_name", rs.getString("Category_name"));
//                row.put("Order_id", rs.getInt("Order_id"));
//                row.put("Product_name", rs.getString("Product_name"));
//                row.put("Order_date", rs.getDate("Order_date"));
//                row.put("Quantity", rs.getInt("Quantity"));
//                float price = rs.getFloat("Price");
//                DecimalFormat df = new DecimalFormat("#,###.##");
//                String formattedPrice = df.format(price);
//                row.put("Price", formattedPrice);
//                row.put("Status", rs.getInt("Status"));
//                orderProductDetails.add(row);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return orderProductDetails;
//    }
    public int getStatusByOrderId(int orderId) {
        String sql = "select Orders.Status from Orders\n"
                + "where Order_id=?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                return rs.getInt("Status");
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public List<Map<String, Object>> getOrderByUserId(int page, int pageSize, String searchKeyword, int category, int userId) {
        List<Map<String, Object>> orderProductDetails = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT Orders.ShippingStatus,Orders.Order_id, Orders.Order_date, Orders.Status, Orders.Total_money, "
                + "STRING_AGG(CONCAT(Product.Product_name, '<br>', 'Quantity: ', Order_detail.Quantity), '<br><br>') AS Product_details "
                + "FROM Orders "
                + "INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id "
                + "INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id "
                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
                + "WHERE Orders.User_id = ? "
        );

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append("AND Product.Product_name LIKE ? ");
        }

        if (category != -1) {
            sql.append("AND Product.Category_id = ? ");
        }

        sql.append("GROUP BY Orders.ShippingStatus,Orders.Order_id, Orders.Order_date, Orders.Status, Orders.Total_money "
                + "ORDER BY Orders.Order_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int parameterIndex = 1;
            ps.setInt(parameterIndex++, userId);

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                String keywordPattern = "%" + searchKeyword + "%";
                ps.setString(parameterIndex++, keywordPattern);
            }

            if (category != -1) {
                ps.setInt(parameterIndex++, category);
            }

            ps.setInt(parameterIndex++, (page - 1) * pageSize);
            ps.setInt(parameterIndex++, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("ShippingStatus", rs.getInt("ShippingStatus"));
                row.put("Order_id", rs.getInt("Order_id"));

                java.sql.Timestamp orderDateTimestamp = rs.getTimestamp("Order_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedOrderDate = sdf.format(orderDateTimestamp);
                row.put("Order_date", formattedOrderDate);

                row.put("Status", rs.getInt("Status"));
                row.put("Product_details", rs.getString("Product_details"));

                // Format Total_money
                double totalMoney = rs.getDouble("Total_money");
                DecimalFormat df = new DecimalFormat("#,###.##");
                String formattedPrice = df.format(totalMoney);
                row.put("Total_money", formattedPrice);

                orderProductDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderProductDetails;
    }

    public List<Map<String, Object>> getAllCategories(int userId) {
        List<Map<String, Object>> categories = new ArrayList<>();
        String sql = "	SELECT distinct Category.Category_id ,Category.Category_name FROM  Orders 				\n"
                + "					INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id \n"
                + "					INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id \n"
                + "					INNER JOIN Product ON Product_detail.Product_id = Product.Product_id\n"
                + "					Inner join Category on Product.Category_id=Category.Category_id\n"
                + "					 where Orders.User_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Category_id", rs.getInt("Category_id"));
                row.put("Category_name", rs.getString("Category_name"));

                categories.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

//    public int getTotalRecords(String searchKeyword, int category, int userId) {
//        int totalRecords = 0;
//        StringBuilder sql = new StringBuilder(
//                "SELECT COUNT(*) as totalRows "
//                + "FROM Orders "
//                + "INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id "
//                + "INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id "
//                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
//                + "INNER JOIN Category ON Product.Category_id = Category.Category_id "
//                + "WHERE Orders.User_id = ? "
//        );
//
//        if (searchKeyword != null && !searchKeyword.isEmpty()) {
//            sql.append("AND (Category.Category_name LIKE ? OR Product.Product_name LIKE ?) ");
//        }
//
//        if (category != -1) {
//            sql.append("AND Category.Category_id = ? ");
//        }
//
//        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
//            int parameterIndex = 1;
//            ps.setInt(parameterIndex++, userId);
//
//            if (searchKeyword != null && !searchKeyword.isEmpty()) {
//                String keywordPattern = "%" + searchKeyword + "%";
//                ps.setString(parameterIndex++, keywordPattern);
//                ps.setString(parameterIndex++, keywordPattern);
//            }
//
//            if (category != -1) {
//                ps.setInt(parameterIndex++, category);
//            }
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                totalRecords = rs.getInt("totalRows");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return totalRecords;
//    }
    public int getTotalRecords(int userId) {
        int totalRecords = 0;
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(Orders.Order_id) as totalRows\n"
                + "FROM Orders\n"
                + "WHERE Orders.User_id = ?"
        );

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalRecords = rs.getInt("totalRows");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecords;
    }

    public List<Integer> totalOrderandMoney(int userId) {
        List<Integer> result = new ArrayList<>();
        int totalOrder = 0;
        int totalMoney = 0;
        String sql = "SELECT count(*) AS totalOrder, sum(Orders.Total_money) AS totalMoney FROM Orders WHERE Orders.User_id=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalOrder = rs.getInt("totalOrder");
                totalMoney = rs.getInt("totalMoney");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.add(totalOrder);
        result.add(totalMoney);

        return result;
    }

    public static void main(String[] args) {
        MyOrderDAO md = new MyOrderDAO();
        int page = 1;
        int pageSize = 5;
        String searchKeyword = "samsung";
        int userId = 8;
        int totoalORder = md.getTotalRecords(8);
        System.out.println(totoalORder);
//        int totalPages = md.getTotalPages(pageSize, searchKeyword, userId);
//        System.out.println("Total Pages: " + totalPages);
        List<Map<String, Object>> orderList = md.getOrderByUserId(page, pageSize, searchKeyword, pageSize, userId);
        for (Map<String, Object> order : orderList) {
            System.out.println(order);
        }
    }
}

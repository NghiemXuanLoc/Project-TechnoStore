/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.BlogCategory;
import model.BlogDetail;
import model.FeedbackProduct;
import model.Orders;
import model.ProductDetail;
import model.User;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

/**
 *
 * @author BUI TUAN DAT
 */
public class FeedbackListDAO extends DBContext {

//    public List<FeedbackProduct> listAllFeedback() {
//        List<FeedbackProduct> list = new ArrayList<>();
//        String sql = "SELECT * FROM Feedback_product INNER JOIN\n"
//                + "          Orders ON Feedback_product.Order_id = Orders.Order_id INNER JOIN\n"
//                + "           Users ON Feedback_product.User_id = Users.User_id  INNER JOIN\n"
//                + "            Product_detail ON Feedback_product.Product_detail_id = Product_detail.Product_detail_id";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                FeedbackProduct fd = new FeedbackProduct();
//                User u = new User();
//                Orders o = new Orders();
//                ProductDetail pd = new ProductDetail();
//                fd.setFeedbackId(rs.getInt("Feedback_id"));
//                u.setUserId(rs.getInt("User_id"));
//                fd.setUser(u);
//                o.setOrderId(rs.getInt("Order_id"));
//                pd.setProductDetailId(rs.getInt("Product_detail_id"));
//                fd.setFeedbackDate(rs.getDate("Feedback_date"));
//                fd.setFeedbackUpdate(rs.getDate("Feedback_update"));
//                fd.setRating(rs.getInt("Rating"));
//                fd.setEditNumber(rs.getInt("Edit_nubmer"));
//                fd.setContent(rs.getString("Content"));
//                fd.setFullName(rs.getString("Full_Name"));
//                fd.setGender(rs.getInt("Gender"));
//                fd.setEmail(rs.getString("Email"));
//                fd.setPhoneNumber(rs.getString("Phone_Number"));
//                fd.setDeleted(rs.getInt("deleted"));
//
//                list.add(fd);
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
//    public List<Map<String, Object>> getListAllFeedBack(int page, int pageSize, String searchKeyword, int rating, int status) {
//        List<Map<String, Object>> feedbackDetails = new ArrayList<>();
//        StringBuilder sql = new StringBuilder(
//                "SELECT Product.Product_name, Feedback_product.Full_Name, Users.Full_name as UserName, "
//                + "Feedback_product.Gender, Users.Gender as UserGender, Feedback_product.Phone_Number, "
//                + "Users.Phone_number as UserPhoneNumber, Feedback_product.Feedback_date, Feedback_product.Feedback_update, "
//                + "Feedback_product.Rating, Feedback_product.Content,Orders.Status "
//                + "FROM Product_detail "
//                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
//                + "INNER JOIN Feedback_product ON Product_detail.Product_detail_id = Feedback_product.Product_detail_id "
//                + "INNER JOIN Orders ON Feedback_product.Order_id = Orders.Order_id "
//                + "INNER JOIN Users ON Feedback_product.User_id = Users.User_id "
//                + "WHERE 1=1 "
//        );
//
//        // Adding filters to the query
//        if (searchKeyword != null && !searchKeyword.isEmpty()) {
//            sql.append("AND (Feedback_product.Full_Name LIKE ? OR Feedback_product.Content LIKE ?) ");
//        }
//        if (rating > 0) {
//            sql.append("AND Feedback_product.Rating = ? ");
//        }
//        if (status >= 0) {
//            sql.append("AND Orders.Status = ? ");
//        }
//
//        // Pagination
//        sql.append("ORDER BY Feedback_product.Feedback_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
//
//        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
//            int parameterIndex = 1;
//
//            if (searchKeyword != null && !searchKeyword.isEmpty()) {
//                String keywordPattern = "%" + searchKeyword + "%";
//                ps.setString(parameterIndex++, keywordPattern); // Đây là điều kiện cho Feedback_product.Full_Name
//
//                // Nếu bạn muốn thêm điều kiện cho Content của Feedback_product
//                ps.setString(parameterIndex++, keywordPattern); // Đây là điều kiện cho Feedback_product.Content
//            }
//            if (rating > 0) {
//                ps.setInt(parameterIndex++, rating);
//            }
//
//            if (status >= 0) {
//                ps.setInt(parameterIndex++, status);
//            }
//
//            ps.setInt(parameterIndex++, (page - 1) * pageSize);
//            ps.setInt(parameterIndex++, pageSize);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Map<String, Object> row = new HashMap<>();
//                row.put("Product_name", rs.getString("Product_name"));
//                row.put("Full_Name", rs.getString("Full_Name"));
////                row.put("UserName", rs.getString("UserName"));
//                row.put("Gender", rs.getInt("Gender"));
////                row.put("UserGender", rs.getInt("UserGender"));
//                row.put("Phone_Number", rs.getString("Phone_Number"));
////                row.put("UserPhoneNumber", rs.getString("UserPhoneNumber"));
//
//                row.put("Feedback_date", formatTimestamp(rs.getTimestamp("Feedback_date")));
//                row.put("Feedback_update", formatTimestamp(rs.getTimestamp("Feedback_update")));
//
//                row.put("Rating", rs.getInt("Rating"));
//                row.put("Content", rs.getString("Content"));
//                row.put("Status", rs.getInt("Status"));
//                feedbackDetails.add(row);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Consider proper error handling
//        }
//
//        return feedbackDetails;
//    }
    public List<Map<String, Object>> getListAllFeedBack(int page, int pageSize, String searchKeyword, int rating, int status) {
        List<Map<String, Object>> feedbackDetails = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT Orders.Order_id,Feedback_product.deleted,Feedback_product.Feedback_id,Product.Product_name, Feedback_product.Full_Name, Users.Full_name as UserName, "
                + "Feedback_product.Gender, Users.Gender as UserGender, Feedback_product.Phone_Number, "
                + "Users.Phone_number as UserPhoneNumber, Feedback_product.Feedback_date, Feedback_product.Feedback_update, "
                + "Feedback_product.Rating, Feedback_product.Content, Orders.Status "
                + "FROM Product_detail "
                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
                + "INNER JOIN Feedback_product ON Product_detail.Product_detail_id = Feedback_product.Product_detail_id "
                + "INNER JOIN Orders ON Feedback_product.Order_id = Orders.Order_id "
                + "INNER JOIN Users ON Feedback_product.User_id = Users.User_id "
                + "WHERE 1=1 "
        );

//        if (searchKeyword != null && !searchKeyword.isEmpty()) {
//            sql.append("AND (Feedback_product.Full_Name LIKE '%" + searchKeyword + "%' OR Feedback_product.Content LIKE '%" + searchKeyword + "%') ");
//        }
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append("AND (Feedback_product.Full_Name LIKE ? OR Feedback_product.Content LIKE ?) ");
        }
        if (rating > 0) {
            sql.append("AND Feedback_product.Rating = ? ");
        }
        if (status >= 0) {
            sql.append("AND Feedback_product.deleted = ? ");
        }

        sql.append("ORDER BY Feedback_product.Feedback_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int parameterIndex = 1;
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
            }
            if (rating > 0) {
                ps.setInt(parameterIndex++, rating);
            }
            if (status >= 0) {
                ps.setInt(parameterIndex++, status);
            }

            ps.setInt(parameterIndex++, (page - 1) * pageSize);
            ps.setInt(parameterIndex++, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Product_name", rs.getString("Product_name"));
                row.put("Full_Name", rs.getString("Full_Name"));
                row.put("Gender", rs.getInt("Gender"));
                row.put("Phone_Number", rs.getString("Phone_Number"));
                row.put("Feedback_date", rs.getTimestamp("Feedback_date"));
                row.put("Feedback_update", rs.getTimestamp("Feedback_update"));
                row.put("Rating", rs.getInt("Rating"));
                row.put("Content", rs.getString("Content"));
                row.put("Status", rs.getInt("Status"));
                row.put("Feedback_id", rs.getInt("Feedback_id"));
                row.put("deleted", rs.getInt("deleted"));
                row.put("Order_id", rs.getInt("Order_id"));

                feedbackDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackDetails;
    }

    public int getTotalPages(String searchKeyword, int rating, int status, int pageSize) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) AS totalRows "
                + "FROM Product_detail "
                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
                + "INNER JOIN Feedback_product ON Product_detail.Product_detail_id = Feedback_product.Product_detail_id "
                + "INNER JOIN Orders ON Feedback_product.Order_id = Orders.Order_id "
                + "INNER JOIN Users ON Feedback_product.User_id = Users.User_id "
                + "WHERE 1=1 "
        );

        // Adding filters to the query
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append("AND (Feedback_product.Full_Name LIKE ? OR Feedback_product.Content LIKE ?) ");
        }
        if (rating > 0) {
            sql.append("AND Feedback_product.Rating = ? ");
        }
        if (status >= 0) {
            sql.append("AND Feedback_product.deleted = ? ");
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                String keywordPattern = "%" + searchKeyword + "%";
                ps.setString(parameterIndex++, keywordPattern);
                ps.setString(parameterIndex++, keywordPattern);
                ps.setString(parameterIndex++, keywordPattern);
            }

            if (rating > 0) {
                ps.setInt(parameterIndex++, rating);
            }

            if (status >= 0) {
                ps.setInt(parameterIndex++, status);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalRows = rs.getInt("totalRows");
                return (int) Math.ceil((double) totalRows / pageSize);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider proper error handling
        }

        return 0; // Default to 0 if there's an error or no results
    }

    private String formatTimestamp(Timestamp timestamp) {
        if (timestamp != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(timestamp);
        }
        return null; // Or return an empty string or another default value as needed
    }

    public boolean updateFeedbackStatus(int feedbackId, int deleted) {
        String sql = "UPDATE Feedback_product SET deleted = ? WHERE Feedback_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, deleted);
            ps.setInt(2, feedbackId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        FeedbackListDAO fd = new FeedbackListDAO();
        List<Map<String, Object>> feedbackList = fd.getListAllFeedBack(1, 5, "d", 5, 2);

        for (Map<String, Object> feedback : feedbackList) {
            System.out.println(feedback);
        }
    }
}

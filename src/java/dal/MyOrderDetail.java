/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;
import model.FeedbackProduct;
import model.ProductDetail;

public class MyOrderDetail extends DBContext {

//    public List<Map<String, Object>> getOrderDetailByOrderId(int orderId) {
//        List<Map<String, Object>> orderProductDetails = new ArrayList<>();
//        StringBuilder sql = new StringBuilder(
//                "SELECT Users.Full_name AS CustomerName,Orders.User_id,Orders.Total_money,Product_detail.Product_id,Product_detail.Color_id,Product_detail.memory_id,"
//                + "Product_detail.Product_detail_id,Product_detail.Price_origin,"
//                + "Orders.Address,Gallery.Thumbnail,Orders.Full_name,Users.Gender,"
//                + "Orders.Email,Orders.Phone_number,Category.Category_name,Order_detail.Order_id,"
//                + "Product.Product_name,Orders.Order_date,Order_detail.Quantity,Order_detail.Price,Orders.Status\n"
//                + "			FROM  Orders INNER JOIN\n"
//                + "                  Order_detail ON Orders.Order_id = Order_detail.Order_id INNER JOIN\n"
//                + "                  Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id INNER JOIN\n"
//                + "                  Product ON Product_detail.Product_id = Product.Product_id\n"
//                + "				  Inner join Category on Product.Category_id=Category.Category_id\n"
//                + "				  Inner join Users on Orders.User_id=Users.User_id\n"
//                + "				  Inner join Gallery on Product.Product_id = Gallery.Product_id\n"
//                + "				  where Orders.Order_id=?"
//        );
//
//        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
//            ps.setInt(1, orderId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Map<String, Object> row = new HashMap<>();
//                                row.put("CustomerName", rs.getString("CustomerName"));
//
//                row.put("User_id", rs.getInt("User_id"));
//
//                row.put("Product_id", rs.getInt("Product_id"));
//                row.put("Color_id", rs.getInt("Color_id"));
//                row.put("memory_id", rs.getInt("memory_id"));
//
//                row.put("Product_detail_id", rs.getInt("Product_detail_id"));
//                row.put("Price_origin", rs.getInt("Price_origin"));
//                row.put("Address", rs.getString("Address"));
//                row.put("Thumbnail", rs.getString("Thumbnail"));
//                row.put("Full_name", rs.getString("Full_name"));
//                row.put("Gender", rs.getInt("Gender"));
//                row.put("Email", rs.getString("Email"));
//                row.put("Phone_number", rs.getString("Phone_number"));
//                row.put("Category_name", rs.getString("Category_name"));
//                row.put("Order_id", rs.getInt("Order_id"));
//                row.put("Product_name", rs.getString("Product_name"));
//                
//                 java.sql.Timestamp orderDateTimestamp = rs.getTimestamp("Order_date");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String formattedOrderDate = sdf.format(orderDateTimestamp);
//            row.put("Order_date", formattedOrderDate);
//                row.put("Quantity", rs.getInt("Quantity"));
//                float price = rs.getFloat("Price");
//                DecimalFormat df = new DecimalFormat("#,###.##");
//                String formattedPrice = df.format(price);
//                row.put("Price", formattedPrice);
//
//                float total = rs.getFloat("Total_money");
//                String formatTotal = df.format(total);
//                row.put("Total_money", formatTotal);
//                row.put("Status", rs.getInt("Status"));
//
//                orderProductDetails.add(row);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return orderProductDetails;
//    }
    public List<Map<String, Object>> getOrderDetailByOrderId(int orderId) {
        ProductDetailDAO pdd = new ProductDetailDAO();
        FeedbackProductDAO fpd = new FeedbackProductDAO();
        
        List<Map<String, Object>> orderProductDetails = new ArrayList<>();
        String sql = "SELECT Orders.Status,Orders.ShippingStatus,Product_detail.Price_sale,Users.Full_name AS CustomerName, Orders.User_id, Orders.Total_money, "
                + "Product_detail.Product_id, Product_detail.Color_id, Product_detail.memory_id, "
                + "Product_detail.Product_detail_id, Product_detail.Price_origin, Orders.Address, "
                + "Gallery.Thumbnail, Orders.Full_name, Users.Gender, Orders.Email, Orders.Phone_number, "
                + "Category.Category_name, Order_detail.Order_id, Product.Product_name, Orders.Order_date, "
                + "Order_detail.Quantity, Order_detail.Price, Orders.Status "
                + "FROM Orders "
                + "INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id "
                + "INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id "
                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
                + "INNER JOIN Category ON Product.Category_id = Category.Category_id "
                + "INNER JOIN Users ON Orders.User_id = Users.User_id "
                + "INNER JOIN (SELECT Product_id, MIN(Thumbnail) as Thumbnail FROM Gallery GROUP BY Product_id) Gallery "
                + "ON Product.Product_id = Gallery.Product_id "
                + "WHERE Orders.Order_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Status", rs.getInt("Status"));
                row.put("ShippingStatus", rs.getInt("ShippingStatus"));

                row.put("Price_sale", rs.getInt("Price_sale"));

                row.put("CustomerName", rs.getString("CustomerName"));
                row.put("User_id", rs.getInt("User_id"));
                row.put("Product_id", rs.getInt("Product_id"));
                row.put("Color_id", rs.getInt("Color_id"));
                row.put("memory_id", rs.getInt("memory_id"));
                row.put("Product_detail_id", rs.getInt("Product_detail_id"));
                row.put("Price_origin", rs.getInt("Price_origin"));
                row.put("Address", rs.getString("Address"));
                row.put("Thumbnail", rs.getString("Thumbnail"));
                row.put("Full_name", rs.getString("Full_name"));
                row.put("Gender", rs.getInt("Gender"));
                row.put("Email", rs.getString("Email"));
                row.put("Phone_number", rs.getString("Phone_number"));
                row.put("Category_name", rs.getString("Category_name"));
                row.put("Order_id", rs.getInt("Order_id"));
                row.put("Product_name", rs.getString("Product_name"));

                java.sql.Timestamp orderDateTimestamp = rs.getTimestamp("Order_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedOrderDate = sdf.format(orderDateTimestamp);
                row.put("Order_date", formattedOrderDate);

                row.put("Quantity", rs.getInt("Quantity"));
                float price = rs.getFloat("Price");
                DecimalFormat df = new DecimalFormat("#,###.##");
                String formattedPrice = df.format(price);
                row.put("Price", formattedPrice);

                float total = rs.getFloat("Total_money");

                String formatTotal = df.format(total);
                row.put("Total_money", formatTotal);
                row.put("Status", rs.getInt("Status"));
                
                // xem don hang nay da duoc feedback hay chua
                int productId = rs.getInt("Product_id");
                int colorId = rs.getInt("Color_id");
                int memoryId = rs.getInt("memory_id");
                
                ProductDetail productDetail = pdd.getProductDetailByProductIdColorIdMemoryId(productId, colorId, memoryId);
                FeedbackProduct exitFeedback = fpd.checkExitFeedbackProductByUser(orderId, productDetail.getProductDetailId(), rs.getInt("User_id"));
                
                row.put("exitFeedback", exitFeedback);

                orderProductDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderProductDetails;
    }

    public int getQuantityOfProductDetailById(int productDetailId) {
        int quantity = 0;
        String sql = "  select Product_detail.Quantity from Product_detail\n"
                + "				  where Product_detail.Product_detail_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, productDetailId);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                quantity= rs.getInt("Quantity");
            }
        } catch (Exception e) {
        }
        
        return quantity;
    }

    public static void main(String[] args) {
        MyOrderDetail od = new MyOrderDetail();
//        List<Map<String, Object>> orderList = od.getOrderDetailByOrderId(36);
//        for (Map<String, Object> order : orderList) {
//            System.out.println(order);
//        }
    int id=759;
    int total = od.getQuantityOfProductDetailById(id);
        System.out.println(total);
    }
}

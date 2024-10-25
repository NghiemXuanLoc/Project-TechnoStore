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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.OrderDetail;
import model.Orders;
import model.ProductDetail;
import model.User;

/**
 *
 * @author PC
 */
public class OrderDetailDAO extends DBContext {
    
    public List<Map<String, Object>> getOrderProductDetails() {
        List<Map<String, Object>> orderProductDetails = new ArrayList<>();
        String sql = "SELECT Order_detail.Order_id,Order_detail.Product_detail_id,\n"
                + " Product.Product_name,Orders.Order_date,Order_detail.Quantity,\n"
                + " Order_detail.Price,Orders.Status\n"
                + " FROM  Orders INNER JOIN\n"
                + "                  Order_detail ON Orders.Order_id = Order_detail.Order_id INNER JOIN\n"
                + "                  Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id INNER JOIN\n"
                + "                  Product ON Product_detail.Product_id = Product.Product_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Order_id", rs.getInt("Order_id"));
                row.put("Product_detail_id", rs.getInt("Product_detail_id"));
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
                row.put("Status", rs.getInt("Status"));
                
                orderProductDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderProductDetails;
    }
    
    public List<Map<String, Object>> getOrderProductDetailsByDateRange(Date startDate, Date endDate) {
        List<Map<String, Object>> orderProductDetails = new ArrayList<>();
        String sql = "SELECT Order_detail.Order_id, Order_detail.Product_detail_id,\n"
                + "       Product.Product_name, Orders.Order_date, Order_detail.Quantity,\n"
                + "       Order_detail.Price, Orders.Status\n"
                + "FROM Orders\n"
                + "INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id\n"
                + "INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id\n"
                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id\n"
                + "WHERE Orders.Order_date BETWEEN ? AND ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setTimestamp(1, new java.sql.Timestamp(startDate.getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Order_id", rs.getInt("Order_id"));
                row.put("Product_detail_id", rs.getInt("Product_detail_id"));
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
                row.put("Status", rs.getInt("Status"));
                
                orderProductDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderProductDetails;
    }

    //table 1 ben phai salorderdetail
    public List<Map<String, Object>> getOrderDetailByOrderIdRight(int orderId) {
        List<Map<String, Object>> orderDetail = new ArrayList<>();
        String sql = "SELECT Users.Full_name AS UserName,Orders.Sale_id,Orders.Note,Users.Gender,Orders.Address,Orders.Order_id, Orders.Full_name AS CustomerFull_name, Orders.Email, "
                + "Orders.Phone_number, Orders.Order_date, Orders.Total_money,"
                + "Users.Full_name AS SaleFull_name, Orders.Status "
                + "FROM Orders "
                + "INNER JOIN Users ON Orders.Sale_id = Users.User_id "
                + "WHERE Orders.Order_id = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            System.out.println(orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Sale_id", rs.getInt("Sale_id"));
                row.put("Note", rs.getString("Note"));
                row.put("Gender", rs.getInt("Gender"));
                row.put("Address", rs.getString("Address"));
                row.put("Order_id", rs.getInt("Order_id"));
                row.put("CustomerFull_name", rs.getString("CustomerFull_name"));
                row.put("Email", rs.getString("Email"));
                row.put("Phone_number", rs.getString("Phone_number"));
                java.sql.Timestamp orderDateTimestamp = rs.getTimestamp("Order_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedOrderDate = sdf.format(orderDateTimestamp);
                row.put("Order_date", formattedOrderDate);
                
                float price = rs.getFloat("Total_money");
                DecimalFormat df = new DecimalFormat("#,###.##");
                String formattedPrice = df.format(price);
                row.put("Total_money", formattedPrice);
                row.put("SaleFull_name", rs.getString("SaleFull_name"));
                row.put("Status", rs.getString("Status")); // Sửa đổi kiểu dữ liệu cho phù hợp

                orderDetail.add(row);
            }
        } catch (Exception e) {
        }
        
        return orderDetail;
    }
//    table ben trai salorderdetail

    public List<Map<String, Object>> getOrderDetailByOrderIdLeft(int orderId) {
        List<Map<String, Object>> orderDetail = new ArrayList<>();
        String sql = "SELECT Payment.Payment_name,Users.Full_name AS UserName,Orders.Sale_id,Orders.Note,"
                + "Users.Gender,Orders.Address,Orders.Order_id, Orders.Full_name AS CustomerFull_name,"
                + " Users.Email, "
                + "Users.Phone_number, Orders.Order_date, Orders.Total_money,"
                + "Users.Full_name AS SaleFull_name, Orders.Status "
                + "FROM Orders "
                + "INNER JOIN Users ON Orders.User_id = Users.User_id "
                + "INNER JOIN Payment ON Orders.Payment_id = Payment.Payment_id "
                + "WHERE Orders.Order_id = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            System.out.println(orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("UserName", rs.getString("UserName"));
                row.put("Payment_name", rs.getString("Payment_name"));
                row.put("Sale_id", rs.getInt("Sale_id"));
                row.put("Note", rs.getString("Note"));
                row.put("Gender", rs.getInt("Gender"));
                row.put("Address", rs.getString("Address"));
                row.put("Order_id", rs.getInt("Order_id"));
                row.put("CustomerFull_name", rs.getString("CustomerFull_name"));
                row.put("Email", rs.getString("Email"));
                row.put("Phone_number", rs.getString("Phone_number"));
                java.sql.Timestamp orderDateTimestamp = rs.getTimestamp("Order_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedOrderDate = sdf.format(orderDateTimestamp);
                row.put("Order_date", formattedOrderDate);
                
                float price = rs.getFloat("Total_money");
                DecimalFormat df = new DecimalFormat("#,###.##");
                String formattedPrice = df.format(price);
                row.put("Total_money", formattedPrice);
                
                row.put("SaleFull_name", rs.getString("SaleFull_name"));
                row.put("Status", rs.getString("Status")); // Sửa đổi kiểu dữ liệu cho phù hợp

                orderDetail.add(row);
            }
        } catch (Exception e) {
        }
        
        return orderDetail;
    }

    //Product SUMMARY
    public List<Map<String, Object>> getOrderDetailForProductSumByOrderId(int orderId) {
        List<Map<String, Object>> orderDetail = new ArrayList<>();
        String sql = "SELECT \n"
                + "	Payment.Payment_name,\n"
                + "    Gallery.Thumbnail,\n"
                + "    Product.Product_name,\n"
                + "    Category.Category_name,\n"
                + "    Product_detail.Price_origin,\n"
                + "    Product_detail.Price_sale,\n"
                + "    Order_detail.Quantity,\n"
                + "    Orders.Total_money\n"
                + "FROM Orders\n"
                + "    INNER JOIN Payment ON Orders.Payment_id = Payment.Payment_id\n"
                + "    INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id\n"
                + "    INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id\n"
                + "    INNER JOIN Product ON Product_detail.Product_id = Product.Product_id\n"
                + "    INNER JOIN (SELECT Product_id, MIN(Thumbnail) AS Thumbnail FROM Gallery GROUP BY Product_id) Gallery ON Product.Product_id = Gallery.Product_id\n"
                + "    INNER JOIN Category ON Product.Category_id = Category.Category_id\n"
                + "WHERE Orders.Order_id = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            System.out.println(orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                
                row.put("Payment_name", rs.getString("Payment_name"));
                row.put("Thumbnail", rs.getString("Thumbnail"));
                row.put("Product_name", rs.getString("Product_name"));
                row.put("Category_name", rs.getString("Category_name"));
                row.put("Price_origin", rs.getInt("Price_origin"));
                row.put("Price_sale", rs.getInt("Price_sale"));
                row.put("Quantity", rs.getInt("Quantity"));
                float price = rs.getFloat("Total_money");
                DecimalFormat df = new DecimalFormat("#,###.##");
                String formattedPrice = df.format(price);
                row.put("Total_money", formattedPrice);
                
                orderDetail.add(row);
            }
        } catch (Exception e) {
        }
        
        return orderDetail;
    }
    
    public int getLatestOrderId() {
        int latestOrderId = -1; // Giá trị mặc định nếu không tìm thấy đơn hàng nào
        String sql = "SELECT TOP 1 Order_id\n"
                + "FROM Orders\n"
                + "ORDER BY Order_date DESC";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                latestOrderId = rs.getInt("Order_id");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi và in ra console
        }
        
        return latestOrderId;
    }
    
    public boolean updateOrderAll(int orderId, int status, String note, int saleId) {
        String sql = "UPDATE Orders SET Status = ?, Note = ?, Sale_id = ? WHERE Order_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, note);
            ps.setInt(3, saleId);
            ps.setInt(4, orderId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateOrderStatusAndNote(int orderId, int status, String note) {
        String sql = "UPDATE Orders SET Status = ?, Note = ? WHERE Order_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, note);
            ps.setInt(3, orderId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<User> getSalespersons() {
        List<User> salespersons = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Users\n"
                + "WHERE Role_id = 3\n"
                + "AND User_id NOT IN (SELECT User_id FROM Sale_Manager);";  // Assuming Role_id 2 is for salespersons

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("User_id"));
                user.setFullName(rs.getString("Full_name"));
                // Set other User fields as needed
                salespersons.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return salespersons;
    }
    
    public User getManagerSale() {
        String sql = "Select Users.Full_name,Users.User_id from Users\n"
                + "join Sale_Manager\n"
                + "on Users.User_id=Sale_Manager.User_id";
        
        try {
            
            PreparedStatement st = connection.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setFullName(rs.getString("Full_name"));
                u.setUserId(rs.getInt("User_id"));
                return u;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        
        OrderDetailDAO odd = new OrderDetailDAO();
        
        System.out.println(odd.checkPurchaseProduct(378, 7020775, 8));
    }
    
    public void insertOrderDetail(Cart cart, int orderId) {
        String sqlResult = "";
        
        List<ProductDetail> listProductDetail = cart.getListProductDetails();
        List<Integer> quantity = cart.getSoLuong();
        
        for (int i = 0; i < listProductDetail.size(); i++) {
            String sql = "insert into Order_detail(Product_detail_id, Order_id, Price, Quantity, deleted) "
                    + "values ";
            sql += "( " + listProductDetail.get(i).getProductDetailId() + ", " + orderId + ", " + listProductDetail.get(i).getPriceSale() + ", " + quantity.get(i) + ", 0)";
            
            sqlResult += sql;
            if (i != listProductDetail.size() - 1) {
                sqlResult += "\n";
            }
        }
        System.out.println(sqlResult);
        
        try {
            PreparedStatement ps = connection.prepareStatement(sqlResult);
            // chen cac cau lenh ps.set vao day

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update: " + e);
        }
    }
    
    public Cart getOrderDetailAllByOrderId(int orderId) {
        List<ProductDetail> listProductDetail = new ArrayList<>();
        List<Integer> listQuantity = new ArrayList<>();
        
        ProductDetailDAO pdd = new ProductDetailDAO();
        
        String sql = "select * from Order_detail\n"
                + "where Order_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productDetailId = rs.getInt("Product_detail_id");
                int quantity = rs.getInt("quantity");
                
                listProductDetail.add(pdd.getProductDetailById(productDetailId));
                listQuantity.add(quantity);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        
        Cart cart = new Cart(listProductDetail, listQuantity);
        cart.setTotalPriceBeforeDiscount();
        cart.setTotalPriceAfterDiscount();
        return cart;
    }
    
    public boolean checkPurchaseProduct(int productDetailId, int orderId, int userId) {
        String sql = "select * from Orders as o\n"
                + "inner join Order_detail as od on o.Order_id = od.Order_id\n"
                + "where od.Product_detail_id = ? and od.Order_id = ? and o.User_id = ? and o.deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productDetailId);
            ps.setInt(2, orderId);
            ps.setInt(3, userId);
            ps.setInt(4, 0);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        
        return false;
    }
}

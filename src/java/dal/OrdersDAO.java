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
import javax.lang.model.util.Types;
import model.Orders;
import model.Payment;
import model.User;

public class OrdersDAO extends DBContext {

    public int getTotalOrdersDaybyDay(Date startDate, Date endDate, String productName, int status, String searchKeyword) {
        int totalOrders = 0;
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(DISTINCT Orders.Order_id) AS totalOrder\n"
                + "FROM Orders\n"
                + "INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id\n"
                + "INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id\n"
                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id\n"
                + "WHERE CONVERT(DATE, Orders.Order_date) BETWEEN ? AND ?"
        );

        if (productName != null && !productName.isEmpty()) {
            sql.append("AND Product.Product_name LIKE ? ");
        }
        if (status != -1) {
            sql.append("AND Orders.Status = ? ");
        }
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            try {
                Integer.parseInt(searchKeyword);
                sql.append("AND Orders.Order_id = ? ");
            } catch (NumberFormatException e) {
                sql.append("AND Orders.Full_name LIKE ? ");
            }
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int parameterIndex = 1;
            ps.setTimestamp(parameterIndex++, new java.sql.Timestamp(startDate.getTime()));
            ps.setTimestamp(parameterIndex++, new java.sql.Timestamp(endDate.getTime()));

            if (productName != null && !productName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + productName + "%");
            }
            if (status != -1) {
                ps.setInt(parameterIndex++, status);
            }
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                try {
                    int orderId = Integer.parseInt(searchKeyword);
                    ps.setInt(parameterIndex++, orderId);
                } catch (NumberFormatException e) {
                    ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                }
            }

            System.out.println("SQL Query: " + sql);
            System.out.println("Parameters:");
            System.out.println("Start Date: " + new java.sql.Date(startDate.getTime()));
            System.out.println("End Date: " + new java.sql.Date(endDate.getTime()));
            System.out.println("Product Name: " + productName);
            System.out.println("Status: " + status);
            System.out.println("Search Keyword: " + searchKeyword);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("totalOrder");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalOrders;
    }

    public int getTotalOrders() {
        int totalOrders = 0;
        String sql = "SELECT COUNT(*) AS totalOrder FROM Orders";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("totalOrder");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalOrders;
    }

    // Hàm lấy số đơn hàng thành công
    public int getSuccessfulOrders() {
        int successfulOrders = 0;
        String sql = "SELECT COUNT(*) AS successOrder FROM Orders WHERE Status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                successfulOrders = rs.getInt("successOrder");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return successfulOrders;
    }

    public String getTotalMoneySold() {
        String sql = "SELECT SUM(Total_money) AS TotalAmount FROM Orders";
        String formattedPrice = "";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Retrieve total amount
//                totalAmount = rs.getDouble("TotalAmount");

                double totalAmount = rs.getDouble("TotalAmount");
                DecimalFormat df = new DecimalFormat("#,###.##");
                formattedPrice = df.format(totalAmount);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Return total amount
        return formattedPrice;
    }

    public boolean updateOrderStatus(int orderId, int status) {
        String sql = "UPDATE Orders SET Status = ? WHERE Order_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, orderId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> getOrderProductDetailsByDateRange(Date startDate, Date endDate) {
        List<Map<String, Object>> orderProductDetails = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT top 5 Orders.Order_id, Orders.Order_date, Orders.Status, Orders.Total_money, "
                + "STRING_AGG(CONCAT(Product.Product_name, '<br>', 'Quantity: ', Order_detail.Quantity), '<br><br>') AS Product_details "
                + ", Orders.Sale_id FROM Orders "
                + "INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id "
                + "INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id "
                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
                + "WHERE 1=1 ");
        if (startDate != null && endDate != null) {
            sql.append("AND CONVERT(DATE, Orders.Order_date) BETWEEN ? AND ? ");
        }
        sql.append("GROUP BY Orders.Order_id, Orders.Order_date, Orders.Status, Orders.Total_money, Orders.Sale_id "
                + "ORDER BY Orders.Order_date desc, Orders.Order_id");

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());

            // Debugging the SQL statement and parameters
            System.out.println("SQL Query: " + sql.toString());
            System.out.println("Start Date Parameter: " + new java.sql.Timestamp(startDate.getTime()));
            System.out.println("End Date Parameter: " + new java.sql.Timestamp(endDate.getTime()));

            ps.setTimestamp(1, new java.sql.Timestamp(startDate.getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Order_id", rs.getInt("Order_id"));
                row.put("Product_details", rs.getString("Product_details"));

                java.sql.Timestamp orderDateTimestamp = rs.getTimestamp("Order_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedOrderDate = sdf.format(orderDateTimestamp);
                row.put("Order_date", formattedOrderDate);

                double totalMoney = rs.getDouble("Total_money");
                DecimalFormat df = new DecimalFormat("#,###.##");
                String formattedPrice = df.format(totalMoney);
                row.put("Total_money", formattedPrice);
                row.put("Status", rs.getInt("Status"));
                row.put("saleId", rs.getInt("Sale_id"));

                orderProductDetails.add(row);
            }

            if (orderProductDetails.isEmpty()) {
                System.out.println("No order details found for the given date range.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderProductDetails;
    }

    public List<Orders> getOrdersShorted() {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT Orders.Full_name,Orders.Order_date,Order_detail.Price,Status\n"
                + " FROM  Orders INNER JOIN\n"
                + "                  Order_detail ON Orders.Order_id = Order_detail.Order_id INNER JOIN\n"
                + "                  Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id INNER JOIN\n"
                + "                  Product ON Product_detail.Product_id = Product.Product_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            // Process result set
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderDate(rs.getDate("Order_date"));
                order.setFullName(rs.getString("Full_name"));
                order.setTotalMoney(rs.getFloat("Total_money"));
                order.setStatus(rs.getInt("Status"));

                orders.add(order);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Map<String, Object>> getOrderListAll(Date startDate, Date endDate, int page, int pageSize, String productName, int status, String searchKeyword) {
        List<Map<String, Object>> orderProductDetails = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT Orders.Order_id, Orders.Full_name, Orders.Order_date, Orders.Status,Orders.Total_money, "
                + "STRING_AGG(CONCAT(Product.Product_name, '<br>', 'Quantity: ', Order_detail.Quantity), '<br><br>') AS Product_details "
                + "FROM Orders "
                + "INNER JOIN Order_detail ON Orders.Order_id = Order_detail.Order_id "
                + "INNER JOIN Product_detail ON Order_detail.Product_detail_id = Product_detail.Product_detail_id "
                + "INNER JOIN Product ON Product_detail.Product_id = Product.Product_id "
                + "WHERE 1 = 1 "
        );

        sql.append("AND CONVERT(DATE, Orders.Order_date) BETWEEN ? AND ? ");

        if (productName != null && !productName.isEmpty()) {
            sql.append("AND Product.Product_name LIKE ? ");
        }
        if (status != -1) {
            sql.append("AND Orders.Status = ? ");
        }
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            try {
                Integer.parseInt(searchKeyword);
                sql.append("AND Orders.Order_id = ? ");
            } catch (NumberFormatException e) {
                sql.append("AND Orders.Full_name LIKE ? ");
            }
        }
        sql.append("GROUP BY Orders.Order_id, Orders.Full_name, Orders.Order_date, Orders.Status, Orders.Total_money "
                + "ORDER BY Orders.Order_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

//        System.out.println("SQL Query: " + sql);
        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            if (startDate != null && endDate != null) {

                ps.setTimestamp(parameterIndex++, new java.sql.Timestamp(startDate.getTime()));
                ps.setTimestamp(parameterIndex++, new java.sql.Timestamp(endDate.getTime()));
            }
            if (productName != null && !productName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + productName + "%");
            }
            if (status != -1) {
                ps.setInt(parameterIndex++, status);
            }
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                try {
                    int orderId = Integer.parseInt(searchKeyword);
                    ps.setInt(parameterIndex++, orderId);
                } catch (NumberFormatException e) {
                    ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                }
            }

            ps.setInt(parameterIndex++, (page - 1) * pageSize);
            ps.setInt(parameterIndex++, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Order_id", rs.getInt("Order_id"));
                row.put("Full_name", rs.getString("Full_name"));
//                row.put("Product_name", rs.getString("Product_name"));
                row.put("Product_details", rs.getString("Product_details"));

                java.sql.Timestamp orderDateTimestamp = rs.getTimestamp("Order_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedOrderDate = sdf.format(orderDateTimestamp);
                row.put("Order_date", formattedOrderDate);

//                row.put("Quantity", rs.getInt("Quantity"));
                // Định dạng lại giá trị Price
                double totalMoney = rs.getDouble("Total_money");
                DecimalFormat df = new DecimalFormat("#,###.##");
                String formattedPrice = df.format(totalMoney);
                row.put("Total_money", formattedPrice);
                row.put("Status", rs.getInt("Status"));
                orderProductDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderProductDetails;
    }

    public Date getEarliestOrderDate() {
        String sql = "SELECT MIN(Order_date) AS EarliestDate FROM Orders";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("EarliestDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return null if no earliest date is found
        return null;
    }

    public static void main(String[] args) {
        OrdersDAO od = new OrdersDAO();

        //Tạo ví dụ về ngày bắt đầu và kết thúc
        Date startDate = new Date();
        Date endDate = new Date();
        List<Map<String, Object>> order = od.getOrderProductDetailsByDateRange(startDate, endDate);
        System.out.println(order);
        for (Map<String, Object> map : order) {
            System.out.println(map);
        }

        // Lấy danh sách đơn hàng từ DAO với phân trang, trang thứ 2, mỗi trang 5 đơn hàng
//        List<Map<String, Object>> orderProductDetails = od.getOrderListAll(startDate, endDate, 1, 3, "Iphone", 1, "7");
//        System.out.println(orderProductDetails);
//        // In ra thông tin từng đơn hàng
//        for (Map<String, Object> row : orderProductDetails) {
//            int orderId = (int) row.get("Order_id");
//            String productName = (String) row.get("Product_name");
//            Date orderDate = (Date) row.get("Order_date");
//            int quantity = (int) row.get("Quantity");
//            float price = (float) row.get("Price");
//            int status = (int) row.get("Status");
//            String fullName = (String) row.get("Full_name");
//
//            // In ra thông tin sản phẩm
//            System.out.println("Order ID: " + orderId);
//            System.out.println("Product Name: " + productName);
//            System.out.println("Order Date: " + orderDate);
//            System.out.println("Quantity: " + quantity);
//            System.out.println("Price: " + price);
//            System.out.println("Status: " + status);
//            System.out.println("Full Name: " + fullName);
//            System.out.println("-----------------------------");
//        }
//        System.out.println(od.getOrderById(74159732));
    }

    public boolean isOrder(String orderId) {
        String sql = "select * from Orders\n"
                + "where Order_id = ? and deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, orderId);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("loi check ton tai order  by Id: " + e);
        }
        return false;
    }

    public void insertOrder(int orderId, int userId, int saleId, int paymentId, int status, String email, String fullName, String address, String note, String phoneNumber, double totalMoney, int shippingStatus) {
        // khoi tao sql
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([Order_id]\n"
                + "           ,[User_id]\n"
                + "           ,[Sale_id]\n"
                + "           ,[Payment_id]\n"
                + "           ,[Status]\n"
                + "           ,[Email]\n"
                + "           ,[Full_name]\n"
                + "           ,[Address]\n"
                + "           ,[Note]\n"
                + "           ,[Phone_number]\n"
                + "           ,[Total_money]\n"
                + "           ,[Order_date]\n"
                + "           ,[Discount]\n"
                + "           ,[ShippingStatus]\n"
                + "           ,[deleted])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,0\n"
                + "           ,?\n"
                + "           ,0)\n";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // chen cac cau lenh ps.set vao day
            ps.setInt(1, orderId);
            ps.setInt(2, userId);
            ps.setInt(3, saleId);
            ps.setInt(4, paymentId);
            ps.setInt(5, status);
            ps.setString(6, email);
            ps.setString(7, fullName);
            ps.setString(8, address);
            ps.setString(9, note);
            ps.setString(10, phoneNumber);
            ps.setDouble(11, totalMoney);
            ps.setInt(12, shippingStatus);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update: " + e);
        }
    }

    public int assignSale() {

        String sql = "with sale_and_number_order as (\n"
                + "select Sale_id, COUNT(Order_id) as numberOrder from Orders\n"
                + "where Order_date >= GETDATE() - 30\n"
                + "group by Sale_id\n"
                + ")\n"
                + "\n"
                + "select top 1 User_id, \n"
                + "case when numberOrder is null then 0\n"
                + "else numberOrder\n"
                + "end\n"
                + "as number_order\n"
                + "from Users as u\n"
                + "left join sale_and_number_order as s on u.User_id = s.Sale_id \n"
                + "where Role_id = 3 and deleted = 0\n"
                + "order by number_order, User_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("User_id");
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return -1;
    }

    public void updateStatusThanhToanOrder(int orderId) {
        String sql = "update Orders\n"
                + "set\n"
                + "Status = 2\n"
                + "where Order_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // cau lenh ps.set cac update can thiet
            ps.setInt(1, orderId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update: " + e);
        }
    }

    public boolean checkTrangThaiGiaoDichOrder(String orderId) {
        String sql = "select status from Orders\n"
                + "where Order_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("status") == 0 ? true : false;
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return false;
    }

    public Orders getOrderById(int oderId) {

        UserDAO ud = new UserDAO();
        PaymentDAO pd = new PaymentDAO();

        String sql = "select * from Orders\n"
                + "where Order_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, oderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = ud.getUserById(rs.getInt("User_id"));
                User sale = ud.getUserById(rs.getInt("Sale_id"));
                Payment payment = pd.getPaymentById(rs.getInt("Payment_id"));
                int status = rs.getInt("status");
                String email = rs.getString("email");
                String fullName = rs.getString("Full_name");
                String address = rs.getString("address");
                String note = rs.getString("note");
                String phoneNumber = rs.getString("Phone_number");
                float totalMoney = rs.getFloat("Total_money");
                Date orderDate = rs.getTimestamp("Order_date");
                int discount = rs.getInt("Discount");
                String shippingStatus = rs.getString("ShippingStatus");
                int deleted = rs.getInt("deleted");

                return new Orders(oderId, user, sale, payment, status, email, fullName, address, note, phoneNumber, totalMoney, orderDate, discount, deleted, shippingStatus);
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return null;
    }

    public List<Orders> getDonHangChuaThanhToan() {

        UserDAO ud = new UserDAO();
        PaymentDAO pd = new PaymentDAO();

        List<Orders> list = new ArrayList<>();

        String sql = "select * from Orders\n"
                + "where deleted = 0 and Status = 0";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("Order_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                User sale = ud.getUserById(rs.getInt("Sale_id"));
                Payment payment = pd.getPaymentById(rs.getInt("Payment_id"));
                int status = rs.getInt("status");
                String email = rs.getString("email");
                String fullName = rs.getString("Full_name");
                String address = rs.getString("address");
                String note = rs.getString("note");
                String phoneNumber = rs.getString("Phone_number");
                float totalMoney = rs.getFloat("Total_money");
                Date orderDate = rs.getTimestamp("Order_date");
                int discount = rs.getInt("Discount");
                String shippingStatus = rs.getString("ShippingStatus");
                int deleted = rs.getInt("deleted");

                list.add(new Orders(orderId, user, sale, payment, status, email, fullName, address, note, phoneNumber, totalMoney, orderDate, discount, deleted, shippingStatus));
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public void updateStatusOrder(int orderId) {
        // cau lenh khoi tao update
        String sql = "update Orders\n"
                + "set\n"
                + "Status = 1\n"
                + "where Order_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // cau lenh ps.set cac update can thiet
            ps.setInt(1, orderId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update: " + e);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.FeedbackProduct;
import model.Orders;
import model.ProductDetail;
import model.User;

/**
 *
 * @author PC
 */
public class FeedbackProductDAO extends DBContext {

    //        [Feedback_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NOT NULL,
//	[Order_id] [int] NOT NULL,
//	[Product_detail_id] [int] NOT NULL,
//	[Feedback_date] [datetime] NOT NULL,
//	[Feedback_update] [datetime] NULL,
//	[Rating] [int] NOT NULL,
//	[Edit_nubmer] [int] NOT NULL,
//	[Content] [nvarchar](max) NULL,
//	[Full_Name] [nvarchar](max) NULL,
//	[Gender] [bit] NULL,
//	[Email] [nvarchar](50) NULL,
//	[Phone_Number] [nvarchar](10) NULL,
//	[deleted] [bit] NOT NULL,
    public List<FeedbackProduct> getFeedbackProductAllByProductId(int productId) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();

        ProductDetailDAO pdd = new ProductDetailDAO();
        List<FeedbackProduct> list = new ArrayList<>();
        String sql = "select * from Product_detail as pd \n"
                + "inner join Feedback_product as fp on pd.Product_detail_id = fp.Product_detail_id\n"
                + "where fp.deleted = ? and pd.deleted = ? and pd.Product_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, 0);
            ps.setInt(3, productId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);
                list.add(fp);
            }
        } catch (SQLException e) {
            System.out.println("loi get feedback product all by product id: " + e);
        }
        return list;
    }

    public List<FeedbackProduct> getFeedbackProductAllByProductIdPagination(int productId, int numberPage) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        List<FeedbackProduct> list = new ArrayList<>();
        String sql = "select * from Product_detail as pd \n"
                + "inner join Feedback_product as fp on pd.Product_detail_id = fp.Product_detail_id\n"
                + "where fp.deleted = ? and pd.deleted = ? and pd.Product_id = ?\n"
                + "order by fp.Feedback_date desc, pd.Product_detail_id\n"
                + "OFFSET (5 * ?) ROWS FETCH NEXT (5) ROWS ONLY";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, 0);
            ps.setInt(3, productId);
            ps.setInt(4, numberPage - 1);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);
                list.add(fp);
            }
        } catch (SQLException e) {
            System.out.println("loi get feedback product all by product id pagination: " + e);
        }
        return list;
    }

    public List<FeedbackProduct> getStarFeedback(int productId, int star) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        List<FeedbackProduct> list = new ArrayList<>();
        String sql = "select fp.Feedback_id, fp.User_id, fp.Order_id, fp.Product_detail_id, fp.Feedback_date, fp.Feedback_update, fp.Rating, fp.Edit_nubmer, fp.Content, fp.Full_Name, fp.Gender, fp.Email, fp.Phone_Number, fp.deleted from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "inner join Feedback_product as fp on pd.Product_detail_id = fp.Product_detail_id\n"
                + "where p.Deleted = ? and fp.deleted = ? and p.Product_id = ? and fp.Rating = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, 0);
            ps.setInt(3, productId);
            ps.setInt(4, star);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);
                list.add(fp);
            }
        } catch (SQLException e) {
            System.out.println("loi get getStarFeedback: " + e);
        }
        return list;
    }

    public List<FeedbackProduct> getStarFeedbackPagination(int productId, int star, int numberPage) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        List<FeedbackProduct> list = new ArrayList<>();
        String sql = "select fp.Feedback_id, fp.User_id, fp.Order_id, fp.Product_detail_id, fp.Feedback_date, fp.Feedback_update, fp.Rating, fp.Edit_nubmer, fp.Content, fp.Full_Name, fp.Gender, fp.Email, fp.Phone_Number, fp.deleted from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "inner join Feedback_product as fp on pd.Product_detail_id = fp.Product_detail_id\n"
                + "where p.Deleted = ? and fp.deleted = ? and p.Product_id = ? and fp.Rating = ?\n"
                + "order by fp.Feedback_date desc, pd.Product_detail_id\n"
                + "OFFSET (5 * ?) ROWS FETCH NEXT (5) ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, 0);
            ps.setInt(3, productId);
            ps.setInt(4, star);
            ps.setInt(5, numberPage - 1);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);
                list.add(fp);
            }
        } catch (SQLException e) {
            System.out.println("loi get getStarFeedbackPagination: " + e);
        }
        return list;
    }

    public List<FeedbackProduct> getFeedbackProductImageByProductId(int productId) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        List<FeedbackProduct> list = new ArrayList<>();
        String sql = "select fp.Feedback_id, fp.User_id, fp.Order_id, fp.Product_detail_id, fp.Feedback_date, fp.Feedback_update, fp.Rating, fp.Edit_nubmer, fp.Content, fp.Full_Name, fp.Gender, fp.Email, fp.Phone_Number, fp.deleted from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "inner join Feedback_product as fp on pd.Product_detail_id = fp.Product_detail_id\n"
                + "where p.Deleted = ? and fp.deleted = ? and p.Product_id = ? and fp.Content like '%<img%src%'";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, 0);
            ps.setInt(3, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);
                list.add(fp);
            }
        } catch (SQLException e) {
            System.out.println("loi get getFeedbackProductImageByProductId: " + e);
        }
        return list;
    }

    public List<FeedbackProduct> getFeedbackProductImageByProductIdPagination(int productId, int numberPage) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        List<FeedbackProduct> list = new ArrayList<>();
        String sql = "select fp.Feedback_id, fp.User_id, fp.Order_id, fp.Product_detail_id, fp.Feedback_date, fp.Feedback_update, fp.Rating, fp.Edit_nubmer, fp.Content, fp.Full_Name, fp.Gender, fp.Email, fp.Phone_Number, fp.deleted from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "inner join Feedback_product as fp on pd.Product_detail_id = fp.Product_detail_id\n"
                + "where p.Deleted = ? and fp.deleted = ? and p.Product_id = ? and fp.Content like '%<img%src%'\n"
                + "order by fp.Feedback_date desc, pd.Product_detail_id\n"
                + "OFFSET (5 * ?) ROWS FETCH NEXT (5) ROWS ONLY";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, 0);
            ps.setInt(3, productId);
            ps.setInt(4, numberPage - 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);
                list.add(fp);
            }
        } catch (SQLException e) {
            System.out.println("loi get getFeedbackProductImageByProductIdPagination: " + e);
        }
        return list;
    }

    public static void main(String[] args) {
        FeedbackProductDAO fpd = new FeedbackProductDAO();
//        for (FeedbackProduct fp : fpd.getStarFeedbackPagination(30, 5, 1)) {
//            System.out.println(fp);
//        }
        System.out.println(fpd.getFeedbackByFeedbackId(4));
    }

    public void addFeedbackProduct(FeedbackProduct fp) {
        // khoi tao sql
        String sql = "INSERT INTO [dbo].[Feedback_product]\n"
                + "           ([User_id]\n"
                + "           ,[Order_id]\n"
                + "           ,[Product_detail_id]\n"
                + "           ,[Feedback_date]\n"
                + "           ,[Feedback_update]\n"
                + "           ,[Rating]\n"
                + "           ,[Edit_nubmer]\n"
                + "           ,[Content]\n"
                + "           ,[Full_Name]\n"
                + "           ,[Gender]\n"
                + "           ,[Email]\n"
                + "           ,[Phone_Number]\n"
                + "           ,[deleted])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,null\n"
                + "           ,?\n"
                + "           ,0\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,0)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // chen cac cau lenh ps.set vao day

            ps.setInt(1, fp.getUser().getUserId());
            ps.setInt(2, fp.getOrder().getOrderId());
            ps.setInt(3, fp.getProductDetail().getProductDetailId());
            ps.setInt(4, fp.getRating());
            ps.setString(5, fp.getContent());
            ps.setString(6, fp.getFullName());
            ps.setInt(7, fp.getGender());
            ps.setString(8, fp.getEmail());
            ps.setString(9, fp.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi add feedback: " + e);
        }
    }

    public FeedbackProduct checkExitFeedbackProductByUser(int orderId, int productDetailId, int userId) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();

        String sql = "select * from Feedback_product\n"
                + "where User_id = ? and Order_id = ? and Product_detail_id = ? and deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, orderId);
            ps.setInt(3, productDetailId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);

                return fp;
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return null;
    }

    public FeedbackProduct getFeedbackByFeedbackId(int feedbackId) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();

        String sql = "select * from Feedback_product\n"
                + "where Feedback_id = ? and deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);

                return fp;
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return null;
    }

    public void updateFeedback(FeedbackProduct fp) {
        // cau lenh khoi tao update
        String sql = "UPDATE [dbo].[Feedback_product]\n"
                + "   SET \n"
                + "      [Feedback_update] = GETDATE()\n"
                + "      ,[Rating] = ?\n"
                + "      ,[Edit_nubmer] = ?\n"
                + "      ,[Content] = ?\n"
                + "      ,[Full_Name] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Phone_Number] = ?\n"
                + "      ,[deleted] = 0\n"
                + "where Feedback_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // cau lenh ps.set cac update can thiet
            ps.setInt(1, fp.getRating());
            ps.setInt(2, fp.getEditNumber());
            ps.setString(3, fp.getContent());
            ps.setString(4, fp.getFullName());
            ps.setInt(5, fp.getGender());
            ps.setString(6, fp.getEmail());
            ps.setString(7, fp.getPhoneNumber());
            ps.setInt(8, fp.getFeedbackId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update feedback: " + e);
        }
    }

    public FeedbackProduct getFeedbackByOrderIdAndProductDetailId(int orderId, int productDetailId) {
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();

        String sql = "select * from Feedback_product\n"
                + "where Order_id = ? and Product_detail_id = ? and deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, productDetailId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                Orders order = od.getOrderById(rs.getInt("Order_id"));
                ProductDetail productDetail = pdd.getProductDetailById(rs.getInt("Product_detail_id"));
                Date feedbackDate = rs.getTimestamp("Feedback_date");
                Date feedbackUpdate = rs.getTimestamp("Feedback_update");
                int rating = rs.getInt("Rating");
                int editNumber = rs.getInt("Edit_nubmer");
                String content = rs.getString("Content");
                String fullName = rs.getString("Full_Name");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                int deleted = rs.getInt("Deleted");

                FeedbackProduct fp = new FeedbackProduct(feedbackId, user, order, productDetail, feedbackDate, feedbackUpdate, rating, editNumber, content, fullName, gender, email, phoneNumber, deleted);

                return fp;
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return null;
    }
}

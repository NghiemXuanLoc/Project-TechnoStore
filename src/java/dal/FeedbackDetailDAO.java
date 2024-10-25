/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.FeedbackDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class FeedbackDetailDAO extends DBContext {

    public FeedbackDetail getFeedbackById(String id) {
        String sql = "select f.Feedback_id, f.Full_Name, f.Email, f.Phone_Number, p.Product_name, f.Rating, f.Content, f.deleted\n"
                + "from Feedback_product f  join Order_detail o on f.Order_id = o.Order_id\n"
                + "join Product_detail pd on o.Product_detail_id = pd.Product_detail_id\n"
                + "join Product p on pd.Product_id = p.Product_id\n"
                + "where f.Feedback_id = ?";
        FeedbackDetail fbp = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fbp = new FeedbackDetail(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8));
            }

        } catch (Exception e) {
            System.out.println(e + "Loi feedbackdetail by id");
        }
        return fbp;
    }

    public void updateFeedbackById(String status, String id ) {
        String sql = "update Feedback_product\n"
                + "set deleted = ?\n"
                + "where Feedback_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
           int deletedStatus = Integer.parseInt(status);
            int feedbackId = Integer.parseInt(id);
            ps.setInt(1, deletedStatus);
            ps.setInt(2, feedbackId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e +"loi o update feedback");
        }
    }

    public static void main(String[] args) {
        FeedbackDetailDAO dao = new FeedbackDetailDAO();
        FeedbackDetail fb = dao.getFeedbackById("3");
        dao.updateFeedbackById("0", "4");
        System.out.println("A: " + fb.getProduct());
    }

}

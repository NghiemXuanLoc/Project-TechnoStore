/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Payment;

/**
 *
 * @author PC
 */
public class PaymentDAO extends DBContext {

    public List<Payment> getPaymentAll() {
        List<Payment> list = new ArrayList<>();
        String sql = "select * from Payment";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int paymentId = rs.getInt("Payment_id");
                String paymentName = rs.getString("Payment_name");
                int deleted = rs.getInt("deleted");

                list.add(new Payment(paymentId, paymentName, deleted));
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public Payment getPaymentById(int id) {
        String sql = "select * from Payment\n"
                + "where Payment_id = ? and deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int paymentId = rs.getInt("Payment_id");
                String paymentName = rs.getString("Payment_name");
                int deleted = rs.getInt("deleted");
                return new Payment(paymentId, paymentName, deleted);
            }
        } catch (SQLException e) {
            System.out.println("loi get payment by Id: " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        PaymentDAO pd = new PaymentDAO();

//        for (Payment p : pd.getPaymentAll()) {
//            System.out.println(p);
//        }

        System.out.println(pd.getPaymentById(1));
    }
}

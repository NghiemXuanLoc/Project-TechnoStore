/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.CartAccount;
import model.ProductDetail;
import model.User;

/**
 *
 * @author PC
 */
public class CartAccountDAO extends DBContext {

    public Map<Integer, Integer> getCarAccountByUserId(int userId) {

        Map<Integer, Integer> map = new LinkedHashMap<>();

        UserDAO ud = new UserDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();

        String sql = "select * from Cart\n"
                + "where User_id = ?\n"
                + "order by Date";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productDetailId = rs.getInt("Product_Detail_id");
                int quantity = rs.getInt("Quantity");

                map.put(productDetailId, quantity);
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return map;
    }

    public void insertCartAccountByUserId(int userId, int productDetailId, int quantity) {
        String sql = "insert into Cart(User_id, Product_Detail_id, Quantity, Date) values\n"
                + "(?, ?, ?, GETDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // chen cac cau lenh ps.set vao day
            ps.setInt(1, userId);
            ps.setInt(2, productDetailId);
            ps.setInt(3, quantity);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi insert cartAccount: " + e);
        }
    }

    public void updateCartAccountByUserId(int userId, int productDetailId, int quantity) {
        // cau lenh khoi tao update
        String sql = "update cart\n"
                + "set\n"
                + "Quantity = ?,\n"
                + "Date = GETDATE()\n"
                + "where Product_detail_id = ? and User_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // cau lenh ps.set cac update can thiet

            ps.setInt(1, quantity);
            ps.setInt(2, productDetailId);
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update cartAccount: " + e);
        }
    }

    public void deleteProductFromCartAccountByUserId(int userId, int productDetailId) {
        String sql = "delete from Cart\n"
                + "where User_id = ? and Product_Detail_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            // cau lenh set ma cua thang can xoa
            ps.setInt(1, userId);
            ps.setInt(2, productDetailId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi delete product from cartAccount: " + e);
        }
    }

    public static void main(String[] args) {
        CartAccountDAO cad = new CartAccountDAO();

        for (Map.Entry<Integer, Integer> cart : cad.getCarAccountByUserId(1).entrySet()) {
            System.out.println(cart.getKey());
            System.out.println("Quantity: " + cart.getValue());
            System.out.println("-------------");
        }
    }
}

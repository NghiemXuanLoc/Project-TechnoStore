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

/**
 *
 * @author PC
 */
public class GalleryDAO extends DBContext {

    public List<String> getGalleryByProductId(int productId) {
        ProductDAO pd = new ProductDAO();
        List<String> list = new ArrayList<>();
        String sql = "select * from Gallery\n"
                + "where Product_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String thumbnail = rs.getString("thumbnail");
                
                list.add(thumbnail);
            }
        } catch (SQLException e) {
            System.out.println("loi gallery by product Id: " + e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        GalleryDAO gd = new GalleryDAO();
        
        for(String x : gd.getGalleryByProductId(1)){
            System.out.println(x);
        }
    }
}

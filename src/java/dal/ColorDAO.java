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
import model.Color;

/**
 *
 * @author PC
 */
public class ColorDAO extends DBContext {

    public Color getColorById(int colorId) {
        String sql = "select * from Colors as c\n"
                + "where c.deleted = ? and c.Color_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, colorId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String colorName = rs.getString("Color_name");
                int deleted = rs.getInt("deleted");

                return new Color(colorId, colorName, deleted);
            }
        } catch (SQLException e) {
            System.out.println("loi get color by Id: " + e);
        }
        return null;
    }

    public List<Color> getColorAll() {
        List<Color> list = new ArrayList<>();
        String sql = "select * from Colors\n"
                + "where deleted = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int colorId = rs.getInt("Color_id");
                String colorName = rs.getString("Color_name");
                int deleted = rs.getInt("deleted");

                Color c = new Color(colorId, colorName, deleted);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("loi Color all: " + e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        ColorDAO cd = new ColorDAO()    ;
        for(Color x : cd.getColorAll()){
            System.out.println(x);
        }
    }

}

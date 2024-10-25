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
import model.Slider;
import model.User;

/**
 *
 * @author PC
 */
public class SliderDAO extends DBContext {

    public List<Slider> getSliderAll() {
        UserDAO ud = new UserDAO();
        List<Slider> list = new ArrayList<>();
        String sql = "select * from Slider where deleted = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int sliderId = rs.getInt("Slider_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                User updateByUser = ud.getUserById(rs.getInt("Update_by"));
                String title = rs.getString("Title");
                String image = rs.getString("Image");
                String backLink = rs.getString("Back_link");
                int deleted = rs.getInt("deleted");

                Slider slider = new Slider(sliderId, user, updateByUser, title, image, backLink, deleted);

                list.add(slider);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public Slider getSliderById(int sliderId) {
        UserDAO ud = new UserDAO();
        String sql = "select * from Slider where deleted = ? and Slider_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, sliderId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = ud.getUserById(rs.getInt("User_id"));
                User updateByUser = ud.getUserById(rs.getInt("Update_by"));
                String title = rs.getString("Title");
                String image = rs.getString("Image");
                String backLink = rs.getString("Back_link");
                int deleted = rs.getInt("deleted");

                Slider slider = new Slider(sliderId, user, updateByUser, title, image, backLink, deleted);
                return slider;
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        SliderDAO sd = new SliderDAO();
//
//        for (Slider x : sd.getSliderAll()) {
//            System.out.println(x);
//        }

        System.out.println(sd.getSliderById(1));
    }
}

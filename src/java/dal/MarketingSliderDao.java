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
 * @author quang
 */
public class MarketingSliderDao extends DBContext {

    public List<Slider> getSliderAll() {
        UserDAO ud = new UserDAO();
        List<Slider> list = new ArrayList<>();
        String sql = "select * from Slider";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
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

    public List<Slider> pagingSlider(List<Slider> sliderList, int iPre, int sliderPerPage) {
        List<Slider> pageSlider = new ArrayList<>();
        int start = (iPre - 1) * sliderPerPage;
        int end = Math.min(start + sliderPerPage, sliderList.size());
        if (start < 0 || start >= sliderList.size()) {
            return pageSlider;
        }
        for (int i = start; i < end; i++) {
            pageSlider.add(sliderList.get(i));
        }
        return pageSlider;
    }

    public Slider getSliderById(int sliderId) {
        UserDAO ud = new UserDAO();
        String sql = "select * from Slider where  Slider_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sliderId);
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

    public List<Slider> filterByActive(List<Slider> list, int active) {
        List<Slider> result = new ArrayList<>();
        for (Slider slider : list) {
            if (slider.getDeleted() == active) {
                result.add(slider);
            }
        }
        return result;
    }

    private String sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        input = input.trim();
        return input.replaceAll("\\s+", " ");
    }

    public List<Slider> searchByTitle(List<Slider> list, String search) {
        List<Slider> result = new ArrayList<>();
        search = sanitizeInput(search);
        for (Slider s : list) {
            if (s.getTitle().toLowerCase().contains(search.toLowerCase()) || s.getBackLink().toLowerCase().contains(search.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public void updateStatus(int sId) {
        String sql = "SELECT deleted FROM Slider WHERE Slider_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean isDeleted = rs.getInt("deleted") == 1;
                // Toggle the status
                isDeleted = !isDeleted;
                // Update the status
                sql = "UPDATE Slider SET deleted = ? WHERE Slider_id = ?";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, isDeleted ? 1 : 0);
                ps.setInt(2, sId);
                ps.executeUpdate();
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSlider(int sliderID, int userID, String title, String image, String backlink, int active) {
        String sql = "UPDATE Slider SET User_id= ?, Title = ?, Image = ?, Back_link = ?,deleted = ? WHERE Slider_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, title);
            ps.setString(3, image);
            ps.setString(4, backlink);
            ps.setInt(5, active);
            ps.setInt(6, sliderID);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void swapSliderId(int currentId, int newId) {
        try {
            // Update the slider ID to a temporary value to avoid conflicts
            String sql = "UPDATE sliders SET id = -1 WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, newId);
            pstmt.executeUpdate();
            pstmt.close();

            // Update the new ID with the current ID
            String updateNewIdQuery = "UPDATE sliders SET id = ? WHERE id = ?";
            pstmt = connection.prepareStatement(updateNewIdQuery);
            pstmt.setInt(1, newId);
            pstmt.setInt(2, currentId);
            pstmt.executeUpdate();
            pstmt.close();

            // Update the temporary ID with the new ID
            String updateCurrentIdQuery = "UPDATE sliders SET id = ? WHERE id = -1";
            pstmt = connection.prepareStatement(updateCurrentIdQuery);
            pstmt.setInt(1, currentId);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MarketingSliderDao pdao = new MarketingSliderDao();
        List<Slider> listS = pdao.getSliderAll();
        for (Slider slider : listS) {
            System.out.println(slider);
        }
    }

}

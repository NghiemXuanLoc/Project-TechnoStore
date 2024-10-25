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
import model.Role;
import model.Slider;
import model.User;

/**
 *
 * @author quang
 */
public class MarketingUserDAO extends DBContext {

    public List<User> getUserAll() {
        String sql = "SELECT * FROM Users";
        List<User> userList = new ArrayList<>();
        RoleDAO rd = new RoleDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("User_id");
                Role role = rd.getRoleById(rs.getByte("Role_id"));
                String fullName = rs.getString("Full_name");
                String password = rs.getString("Password");
                String image = rs.getString("Image");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_number");
                String address = rs.getString("address");
                Date dateOfBirth = rs.getDate("Date_of_birth");
                Date createAt = rs.getDate("created_at");
                Date updateAt = rs.getDate("updated_at");
                int deleted = rs.getInt("deleted");
                String resetPasswordToken = rs.getString("Reset_Password_Token");
                String resetPasswordExpiry = rs.getString("Reset_Password_Expiry");

                User user = new User(userID, role, fullName, password, image, gender, email, phoneNumber, address, dateOfBirth, createAt, updateAt, deleted, resetPasswordToken, createAt);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("loi get object by Id: " + e);
        }
        return userList;
    }

    public List<User> pagingUser(List<User> userList, int iPre, int userPerPage) {
        List<User> pageUser = new ArrayList<>();
        int start = iPre * userPerPage;
        int end = Math.min(start + userPerPage, userList.size());
        if (start < 0 || start >= userList.size()) {
            return pageUser;
        }
        for (int i = start; i < end; i++) {
            pageUser.add(userList.get(i));
        }
        return pageUser;
    }

    public List<User> filterByActive(List<User> list, int active) {
        List<User> result = new ArrayList<>();
        for (User user : list) {
            if (user.getDeleted() == active) {
                result.add(user);
            }
        }
        return result.isEmpty() ? list : result;
    }

    private String sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        input = input.trim();
        return input.replaceAll("\\s+", " ");
    }

    public List<User> searchByTitle(List<User> list, String search) {
        List<User> result = new ArrayList<>();
        search = sanitizeInput(search);
        if (search.isEmpty()) {
            return result;
        }
        for (User s : list) {
            if (s.getFullName().toLowerCase().contains(search.toLowerCase())
                    || s.getEmail().toLowerCase().contains(search.toLowerCase())
                    || s.getPhoneNumber().contains(search)) {
                result.add(s);
            }
        }
        return result;
    }

    public void updateStatus(int sId) {
        String sql = "SELECT deleted FROM Users WHERE User_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean isDeleted = rs.getInt("deleted") == 1;
                isDeleted = !isDeleted;
                sql = "UPDATE Users SET deleted = ? WHERE User_id = ?";
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

    public static void main(String[] args) {
        MarketingUserDAO pud = new MarketingUserDAO();
        List<User> lu = pud.getUserAll();
        for (User user : lu) {
            System.out.println(user);
        }
    }

}

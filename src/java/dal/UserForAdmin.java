/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Role;
import java.sql.Date; 
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
/**import java.sql.Date; 
 *
 * @author admin
 */
public class UserForAdmin extends DBContext{
    
    
    public List<User> getAllUser (){
        List<User> listUser = new ArrayList<>();
        
        String sql = "select * from Users join Roles on Users.Role_id = Roles.Role_id\n" +
                    "where Users.deleted = 0";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15))  );
            }
            
            
        } catch (Exception e) {
        }
        return listUser;
    }
    
    public List<Role> getListRole() {
        List<Role> lr = new ArrayList<>();
        String sql = "select * from Roles";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lr.add(new Role(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (Exception e) {
        }
        return lr;
        
}
    
    
    public List<User> filterUserByGender(int gender){
        List<User> listUser = new ArrayList<>();
        String sql = "select * from Users join Roles on Users.Role_id = Roles.Role_id\n" +
                "where Gender = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gender);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15)) );
            }
            
            
        } catch (Exception e) {
        }
        return listUser;       
    }
    
    
    public List<User> filterUserByRole(String roleName){
        List<User> listUser = new ArrayList<>();
        String sql = "select * from Users join Roles on Users.Role_id = Roles.Role_id\n" +
                    "where Roles.Role_name = ?\n";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, roleName);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15)));
            }
            
            
        } catch (Exception e) {
        }
        return listUser;       
    }
    
    public List<User> filterUserByStatus(int deleted){
        List<User> listUser = new ArrayList<>();
        String sql = "select * from Users join Roles on Users.Role_id = Roles.Role_id\n" +
                            "where Users.deleted = ?\n" ;
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, deleted);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15)) );
            }
            
            
        } catch (Exception e) {
        }
        return listUser;       
    }
    
    
    public List<User> sortBySomeThing(String attribute) {
    List<User> listUser = new ArrayList<>();

    // Danh sách các cột hợp lệ để ngăn chặn SQL injection
    List<String> validAttributes = Arrays.asList(
        "User_id", "Full_name", "Password", "Image", "Gender", 
        "Email", "Phone_number", "Address", "Date_of_birth", 
        "created_at", "updated_at", "deleted", 
        "Reset_Password_Token", "Reset_Password_Expiry", "Role_id"
    );

    if (!validAttributes.contains(attribute)) {
        throw new IllegalArgumentException("Thuộc tính không hợp lệ cho việc sắp xếp");
    }

    String sql = "SELECT Users.*, Roles.Role_id, Roles.Role_name, Roles.deleted AS Role_deleted " +
                 "FROM Users JOIN Roles ON Users.Role_id = Roles.Role_id " +
                 "ORDER BY Users." + attribute + " DESC";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Role role = new Role(
                rs.getInt("Role_id"), 
                rs.getString("Role_name"), 
                rs.getInt("Role_deleted")
            );

            listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15)) );
        }

    } catch (Exception e) {
        e.printStackTrace(); // Tùy chọn: ghi lại lỗi
    }
    return listUser;
}
    
    
    
    public List<User> searchByNameEmailMobile(String NameEmailMobile){
        List<User> listUser = new ArrayList<>();
        String sql = "select * from Users join Roles on Users.Role_id = Roles.Role_id\n"
                + "where Full_name like ? or Email like ?  or Phone_number like ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + NameEmailMobile + "%");
            ps.setString(2, "%" + NameEmailMobile + "%");
            ps.setString(3, "%" + NameEmailMobile + "%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15)) );
            }
            
            
        } catch (Exception e) {
        }
        return listUser;       
    }
    
    public List<User> get10UserCreateNewInThisMonth(){
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT TOP 10 *\n"
                + "FROM Users join Roles on Users.Role_id = Roles.Role_id\n"
                + "WHERE MONTH(created_at) = MONTH(GETDATE())\n"
                + "  AND YEAR(created_at) = YEAR(GETDATE())\n"
                + "ORDER BY created_at DESC;";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15))  );
            }
            
            
        } catch (Exception e) {
        }
        return listUser;       
    }
    
    
    public List<User> get10UserCreateNewInThisMonthButInactive(){
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT TOP 10 *\n"
                + "FROM Users join Roles on Users.Role_id = Roles.Role_id\n"
                + "WHERE MONTH(created_at) = MONTH(GETDATE())\n"
                + "  AND YEAR(created_at) = YEAR(GETDATE())\n"
                + "AND Users.deleted = 1"
                + "ORDER BY created_at DESC;";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15)) );
            }
            
            
        } catch (Exception e) {
        }
        return listUser;       
    }
    
    
    public List<User> get10UserNewUpdate(){
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT TOP 10 *FROM Users join Roles on Users.Role_id = Roles.Role_id\n"
                + "WHERE MONTH(updated_at) = MONTH(GETDATE())\n"
                + "AND YEAR(updated_at) = YEAR(GETDATE())\n"
                + "ORDER BY updated_at DESC;";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                listUser.add(new User(rs.getInt(1), 
                        role, 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7), 
                        rs.getString(8), 
                        rs.getString(9),
                        rs.getDate(10), 
                        rs.getDate(11), 
                        rs.getDate(12), 
                        rs.getInt(13), 
                        rs.getString(14),
                        rs.getDate(15))  );
            }
            
            
        } catch (Exception e) {
        }
        return listUser;       
    }
    
    
    public List<User> getListByPage (List<User> listUser, int start, int end){
        ArrayList<User> arrayUser = new ArrayList<>();
        start = Math.max(0, start); // Start from index 0 if start is negative
        end = Math.min(listUser.size(), end); // Ensure end doesn't exceed list size
        for (int i = start; i < end; i++) {
            arrayUser.add(listUser.get(i));
        }
        return arrayUser;
    }
    public static void main(String[] args) {
        System.out.println("sdfsdfsdf");
    }
    
    
    public void addUserByAdmin(int role, String fullName, String password, int gender, String email, String phone, String address, int status,String resetPasswordToken, java.util.Date resetPasswordExpiry ) {
    String sql = "INSERT INTO [dbo].[Users] " +
                 "(Role_id, Full_name, Password, Gender, Email, Phone_number, Address, created_at, deleted, Reset_Password_Token, Reset_Password_Expiry) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

    try ( // Assuming you have a method to get the DB connection
         PreparedStatement st = connection.prepareStatement(sql)) {

        st.setInt(1, role);
        st.setString(2, fullName);
        st.setString(3, password); // This should be hashed password
        st.setInt(4, gender);
        st.setString(5, email);
        st.setString(6, phone);
        st.setString(7, address);
         st.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
        st.setInt(9, status);
        st.setString(10, resetPasswordToken);
        st.setTimestamp(11, new java.sql.Timestamp(resetPasswordExpiry.getTime()));
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace(); // Use a logging framework in production
        throw new RuntimeException("Failed to add user: " + e.getMessage());
    }
}
    
    public User getUserById(int userId) {
        RoleDAO rd = new RoleDAO();
        String sql = "select * from Users\n"
                + "where User_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Role role = rd.getRoleById(rs.getByte("Role_id"));
                String fullName = rs.getString("Full_name");
                String password = rs.getString("Password");
                String image = rs.getString("Image");
                int gender = rs.getInt("Gender");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_number");
                String address = rs.getString("address");
                java.util.Date dateOfBirth = rs.getDate("Date_of_birth");
                java.util.Date createAt = rs.getDate("created_at");
                java.util.Date updateAt = rs.getDate("updated_at");
                int deleted = rs.getInt("deleted");
                String resetPasswordToken = rs.getString("Reset_Password_Token");
                String resetPasswordExpiry = rs.getString("Reset_Password_Expiry");
                
                User user = new User(userId, role, fullName, password, image, gender, email, phoneNumber, address, dateOfBirth, createAt, updateAt, deleted, resetPasswordToken, createAt);
                return user;

            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return null;
    }

    public void updateUserById(int role, int status, int userId){
        String sql = "update Users\n"
                + "set Role_id = ?,\n"
                + "	deleted = ?,\n"
                + "	updated_at = GETDATE()\n"
                + "	where User_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, role);
            ps.setInt(2, status);
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        
    }
    
    public void updateVerifyUser(String email){
        String sql = "update Users\n"
                + "set deleted = 0\n"
                + "where Email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
     
    
}
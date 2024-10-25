/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author PC
 */
public class UserDAO extends DBContext {

    public User getUserById(int userId) {
        RoleDAO rd = new RoleDAO();
        String sql = "select * from Users\n"
                + "where deleted = ? and User_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, userId);
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
                Date dateOfBirth = rs.getDate("Date_of_birth");
                Date createAt = rs.getDate("created_at");
                Date updateAt = rs.getDate("updated_at");
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

    public User getAccountByEmail(String email) {
        CartAccountDAO cad = new CartAccountDAO();
        String sql = "SELECT * FROM Users WHERE email = ? AND deleted = 0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("User_id"));
                Role r = new Role();
                r.setRoleId(rs.getInt("Role_id"));
                u.setRole(r);
                u.setFullName(rs.getString("Full_name"));
                u.setImage(rs.getString("Image"));
                u.setPassword(rs.getString("Password"));
                u.setGender(rs.getInt("Gender"));
                u.setEmail(rs.getString("Email"));
                u.setPhoneNumber(rs.getString("Phone_number"));
                u.setAddress(rs.getString("Address"));
                u.setDateOfBirth(rs.getDate("Date_of_birth"));
                u.setCreatedAt(rs.getDate("created_at"));
                u.setUpdatedAt(rs.getDate("updated_at"));
                u.setResetPasswordToken(rs.getString("Reset_Password_Token"));
                u.setResetPasswordExpiry(rs.getTimestamp("Reset_Password_Expiry"));
                u.setDeleted(rs.getInt("deleted"));
                u.setCart(cad.getCarAccountByUserId(rs.getInt("User_id")));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertUser(User u) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Role_id]\n"
                + "           ,[Full_name]\n"
                + "           ,[Password]\n"
                + "           ,[Image]\n"
                + "           ,[Gender]\n"
                + "           ,[Email]\n"
                + "           ,[Phone_number]\n"
                + "           ,[Address]\n"
                + "           ,[Date_of_birth]\n"
                + "           ,[created_at]\n"
                + "           ,[updated_at]\n"
                + "           ,[deleted]\n"
                + "           ,[Reset_Password_Token]\n"
                + "           ,[Reset_Password_Expiry])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getRole().getRoleId());
            st.setString(2, u.getFullName());
            st.setString(3, u.getPassword());
            st.setString(4, u.getImage());
            st.setInt(5, u.getGender());
            st.setString(6, u.getEmail());
            st.setString(7, u.getPhoneNumber());
            st.setString(8, u.getAddress());
            st.setDate(9, new java.sql.Date(u.getDateOfBirth().getTime()));
            st.setDate(10, new java.sql.Date(u.getCreatedAt().getTime()));
            st.setDate(11, null);
            st.setInt(12, 0);
            st.setString(13, u.getResetPasswordToken());
            st.setTimestamp(14, new Timestamp(u.getResetPasswordExpiry().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void updateVerifiedEmail(String email) {
//        String sql = "UPDATE [dbo].[Users] SET Veryfied_email = 1 WHERE Email = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, email);
//            st.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public User getAccountByEmailPassword(String email, String password) {
        CartAccountDAO cad = new CartAccountDAO();
        String sql = "SELECT * FROM Users WHERE email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("Password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    User u = new User();
                    u.setUserId(rs.getInt("User_id"));
                    Role r = new Role();
                    r.setRoleId(rs.getInt("Role_id"));
                    u.setRole(r);
                    u.setFullName(rs.getString("Full_name"));
                    u.setPassword(hashedPassword);
                    u.setGender(rs.getInt("Gender"));
                    u.setEmail(rs.getString("Email"));
                    u.setPhoneNumber(rs.getString("Phone_number"));
                    u.setAddress(rs.getString("Address"));
                    u.setDateOfBirth(rs.getDate("Date_of_birth"));
                    u.setCreatedAt(rs.getDate("created_at"));
                    u.setUpdatedAt(rs.getDate("updated_at"));
                    u.setImage("Image");
                    u.setDeleted(rs.getInt("deleted"));
                    u.setResetPasswordToken(rs.getString("Reset_Password_Token"));
                    u.setResetPasswordExpiry(rs.getTimestamp("Reset_Password_Expiry"));
                    u.setCart(cad.getCarAccountByUserId(u.getUserId()));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//toàn bộ phương thức dùng cho resetpassword
    public boolean updateResetPasswordToken(String email, String token, Date expiryDate) {
        String sql = "UPDATE Users SET Reset_Password_Token = ?, Reset_Password_Expiry = ? WHERE Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, token);
            st.setTimestamp(2, new Timestamp(expiryDate.getTime()));
            st.setString(3, email);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTokenValid(String token) {
        String sql = "SELECT * FROM Users WHERE Reset_Password_Token = ? AND Reset_Password_Expiry > ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, token);
            st.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePasswordByToken(String token, String newPassword) {
        String sql = "UPDATE Users SET Password = ?, Reset_Password_Token = NULL, Reset_Password_Expiry = NULL, updated_at = ? WHERE Reset_Password_Token = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword); // Đảm bảo mật khẩu được mã hóa trước khi lưu
            st.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Cập nhật thời gian updated_at
            st.setString(3, token);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UserDAO u = new UserDAO();

        User s = u.getAccountByEmail("locnxhe170939@fpt.edu.vn");

//        System.out.println(s);
    }

    public List<User> getAllUserProfile() {
        String sql = "select * from Users join Roles on Users.Role_id = Roles.Role_id ";
        List<User> listAll = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Role role = new Role(rs.getInt(14), rs.getString(15), rs.getInt(16));
                listAll.add(new User(rs.getInt(1),
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

        return listAll;
    }

    public User getUserProfile(int idUser) {
        String sql = "select * from Users join Roles on Users.Role_id = Roles.Role_id\n"
                + "where [User_id] = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(16), rs.getString(17), rs.getInt(18));
                return new User(rs.getInt(1),
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
                        rs.getDate(15));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean updatePassword(String pass, int userId) {// Test, khi nao login se co id
        String sql = "UPDATE [dbo].[Users]\n"
                + "SET [Password] = ?,\n"
                + "[updated_at] =  GETDATE()\n"
                + "WHERE [User_id] = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return true;
    }

    public void updateUserProfile(String fullName, int gender, String email, String phone, String address, Date dateofbirth, String img, int id) {
        String sql = "UPDATE [dbo].[Users] "
                + "SET [Full_name] = ?, "
                + "[Gender] = ?, "
                + "[Email] = ?, "
                + "[Phone_number] = ?, "
                + "[Address] = ?, "
                + "[Date_of_birth] = ?, "
                + "[Image] = ?, "
                + "[updated_at] = GETDATE() "
                + "WHERE [User_id] = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setInt(2, gender);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setDate(6, (java.sql.Date) dateofbirth);
            ps.setString(7, img);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
    }

    public int getRandom() {
        return 1;
    }

    public boolean updatePasswordOfThong(String newPassword, int userId) {
        String sql = "UPDATE Users SET Password = ?, updated_at = ? WHERE User_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword); // Đảm bảo mật khẩu được mã hóa trước khi lưu
            st.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Cập nhật thời gian updated_at
            st.setInt(3, userId);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTotalUsers() {
        int totalUsers = 0;
        String sql = "SELECT COUNT(*) AS totalUsers FROM Users";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalUsers = rs.getInt("totalUsers");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalUsers;
    }

    public void insertUserAnonymous(String fullName, String email) {
        // khoi tao sql
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Role_id]\n"
                + "           ,[Full_name]\n"
                + "           ,[Email]\n"
                + "           ,[created_at]\n"
                + "           ,[deleted])\n"
                + "     VALUES\n"
                + "           (5\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,0)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // chen cac cau lenh ps.set vao day

            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update: " + e);
        }
    }

    public int getUserIdAnonymous() {
        String sql = "select top 1 * from Users\n"
                + "where Role_id = 5";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("User_id");
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return -1;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.AuthorizationSetting;
import model.Role;
import model.User;

/**
 *
 * @author admin
 */
public class SettingFilterDAO extends DBContext {

    public List<AuthorizationSetting> getAllAuthor() {
        String sql = "select * from Authorization_setting a join Roles r\n"
                + "on a.Role_id = r.Role_id\n where a.status_setting = 0"
                + "order by a.Role_id";
        List<AuthorizationSetting> la = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Role role = new Role(rs.getInt(6), rs.getString(7), rs.getInt(8));
                la.add(new AuthorizationSetting(rs.getInt(1),
                        role,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return la;
    }

    public List<AuthorizationSetting> getListByPage(List<AuthorizationSetting> listAuthor, int start, int end) {
        ArrayList<AuthorizationSetting> arrayAuthor = new ArrayList<>();
        start = Math.max(0, start); // Start from index 0 if start is negative
        end = Math.min(listAuthor.size(), end); // Ensure end doesn't exceed list size
        for (int i = start; i < end; i++) {
            arrayAuthor.add(listAuthor.get(i));
        }
        return arrayAuthor;
    }

    public boolean authorCheck(int role_id, String url) {
        if (url.contains("/assets") || url.contains("/favicon")
                || url.contains("/blogs") || url.contains(".png")
                || url.contains("/reloadreview")
                || url.contains(".css")
                || url.contains(".js")
                || url.contains("/fonts")
                || url.contains("/images")
                || url.contains("/Image")
                || url.contains("/.jpg")
                || url.contains("/tinymce")) {
            return true;
        }
        String sql = "select * from  Authorization_setting a \n"
                + "where Role_id = ? and url_author = ?\n"
                + "and status_setting = 0 order by a.Role_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, role_id);
            ps.setString(2, url);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public List<AuthorizationSetting> getAllRoleById(int id) {
        String sql = "select * from  Authorization_setting a  join Roles r\n"
                + "on a.Role_id = r.Role_id\n"
                + "where a.Role_id = ? \n"
                + "and status_setting = 0 order by a.Role_id";
        List<AuthorizationSetting> la = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(6), rs.getString(7), rs.getInt(8));
                la.add(new AuthorizationSetting(rs.getInt(1),
                        role,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return la;
    }

    public List<AuthorizationSetting> getAllRoleByStatus(int status) {
        String sql = "select * from  Authorization_setting a  join Roles r\n"
                + "on a.Role_id = r.Role_id \n"
                + "where a.status_setting = ? order by a.Role_id";
        List<AuthorizationSetting> la = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(6), rs.getString(7), rs.getInt(8));
                la.add(new AuthorizationSetting(rs.getInt(1),
                        role,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return la;
    }

    public List<AuthorizationSetting> getAllRoleBySearch(String url) {
        String sql = "select * from  Authorization_setting a  join Roles r\n"
                + "on a.Role_id = r.Role_id \n"
                + "where a.url_author like ? order by a.Role_id";
        List<AuthorizationSetting> la = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + url + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(6), rs.getString(7), rs.getInt(8));
                la.add(new AuthorizationSetting(rs.getInt(1),
                        role,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return la;
    }

    public void insertIntoAuthor(int role, String url, String feature, int status) {
        String sql = "INSERT INTO [dbo].[Authorization_setting]\n"
                + "           ([Role_id]\n"
                + "           ,[url_author]\n"
                + "           ,[feature]\n"
                + "           ,[status_setting])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, role);
            ps.setString(2, url);
            ps.setString(3, feature);
            ps.setInt(4, status);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public AuthorizationSetting getAuthorById(int id) {
        String sql = "select * from  Authorization_setting a  join Roles r\n"
                + "on a.Role_id = r.Role_id\n"
                + "where a.Author_id = ?\n";
        AuthorizationSetting a = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(6), rs.getString(7), rs.getInt(8));
                a = new AuthorizationSetting(rs.getInt(1),
                        role,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return a;
    }

    public void updateAuthorById(int roleId, String url, String feature, int status, int authorId) {
        String sql = "UPDATE [dbo].[Authorization_setting]\n"
                + "   SET [Role_id] = ?\n"
                + "      ,[url_author] = ?\n"
                + "      ,[feature] = ?\n"
                + "      ,[status_setting] = ?\n"
                + " WHERE Author_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, roleId);
            ps.setString(2, url);
            ps.setString(3, feature);
            ps.setInt(4, status);
            ps.setInt(5, authorId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        SettingFilterDAO dao = new SettingFilterDAO();
//        List<AuthorizationSetting> lA = dao.getAllRoleBySearch("/");
//        if (!dao.authorCheck(1, "/adminDashboard")) {
//            System.out.println("True");
//        }
//        dao.updateAuthorById(1, "/adminDashboard", "Bảng điều khiển của admin", 0, 2);
        String input = "Chào mừng bạn 2023"; // Chuỗi cần kiểm tra
        String regex = "^[\\p{L}\\p{N}\\s]*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            System.out.println("Chuỗi hợp lệ.");
        } else {
            System.out.println("Chuỗi không hợp lệ.");
        }
        
        if(dao.authorCheck(1, "/adminDashboard")){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
//        for (AuthorizationSetting authorizationSetting : lA) {
//            System.out.println("A: " + authorizationSetting.getRole().getRoleName());
//        }
    }
}

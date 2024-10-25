/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Role;

/**
 *
 * @author PC
 */
public class RoleDAO extends DBContext {

    public Role getRoleById(int id) {
        String sql = "select * from Roles\n"
                + "where Role_id = ? and deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int roleId = rs.getInt("Role_id");
                String roleName = rs.getString("Role_name");
                int deleted = rs.getInt("deleted");
                return new Role(roleId, roleName, deleted);
            }
        } catch (SQLException e) {
            System.out.println("loi get role by Id: " + e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        RoleDAO rd = new RoleDAO();
        System.out.println(rd.getRoleById(1));
    }
}

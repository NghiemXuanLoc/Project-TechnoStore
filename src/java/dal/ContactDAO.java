/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Contact;

/**
 *
 * @author PC
 */
public class ContactDAO extends DBContext {

    public void insert(Contact contact) {
        // khoi tao sql
        String sql = "INSERT INTO [dbo].[Contact]\n"
                + "           ([User_id]\n"
                + "           ,[Full_name]\n"
                + "           ,[Email]\n"
                + "           ,[Gender]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[Rating]\n"
                + "           ,[Subject]\n"
                + "           ,[Content]\n"
                + "           ,[CreatedAt]\n"
                + "           ,[UpdatedAt])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,null)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // chen cac cau lenh ps.set vao day

            ps.setInt(1, contact.getUser().getUserId());
            ps.setString(2, contact.getFullName());
            ps.setString(3, contact.getEmail());
            ps.setInt(4, contact.getGender());
            ps.setString(5, contact.getPhoneNumber());
            ps.setInt(6, contact.getRating());
            ps.setString(7, contact.getSubject());
            ps.setString(8, contact.getContent());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi insert contact: " + e);
        }
    }
}

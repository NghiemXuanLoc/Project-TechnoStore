/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class ProductContributionDAO extends DBContext {

    public void insert(int userId, int productId, String content) {
        // khoi tao sql
        String sql = "INSERT INTO [dbo].[Product_Contributions]\n"
                + "           ([User_id]\n"
                + "           ,[Product_id]\n"
                + "           ,[Content]\n"
                + "           ,[Date])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // chen cac cau lenh ps.set vao day
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setString(3, content);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update: " + e);
        }
    }

}

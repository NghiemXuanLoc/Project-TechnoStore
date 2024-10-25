/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Supplier;

/**
 *
 * @author PC
 */
public class SupplierDAO extends DBContext {

    public Supplier getSupplierById(int supplierId) {
        String sql = "select * from Supplier as s\n"
                + "where s.Supplier_id = ? and s.Deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, supplierId);
            ps.setInt(2, 0);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String companyName = rs.getString("Company_name");
                String phoneNumber = rs.getString("Phone_number");
                String country = rs.getString("Country");
                int deleted = rs.getInt("Deleted");

                Supplier s = new Supplier(supplierId, companyName, phoneNumber, country, deleted);
                return s;
            }
        } catch (SQLException e) {
            System.out.println("loi get supplier by Id: " + e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        SupplierDAO sd = new SupplierDAO();
        
        System.out.println(sd.getSupplierById(1));
    }
}

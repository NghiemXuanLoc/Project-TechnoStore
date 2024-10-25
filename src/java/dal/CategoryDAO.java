/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Product;

/**
 *
 * @author PC
 */
public class CategoryDAO extends DBContext {

    public List<Category> getCategoryAll() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category\n"
                + "where Deleted = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("Category_id");
                String categoryName = rs.getString("Category_name");
                String shortDescription = rs.getString("Short_description");
                String description = rs.getString("Description");
                int deleted = rs.getInt("Deleted");

                Category c = new Category(categoryId, categoryName, shortDescription, description, deleted);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public Category getCategoryById(int id) {
        String sql = "select * from Category\n"
                + "where Category_id = ? and Deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int categoryId = rs.getInt("Category_id");
                String categoryName = rs.getString("Category_name");
                String shortDescription = rs.getString("Short_description");
                String description = rs.getString("Description");
                int deleted = rs.getInt("Deleted");

                Category c = new Category(categoryId, categoryName, shortDescription, description, deleted);
                return c;
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return null;
    }

   

    public static void main(String[] args) {
        CategoryDAO cd = new CategoryDAO();
//        for (Category c : cd.getCategoryAll()) {
//            System.out.println(c);
//        }

        System.out.println(cd.getCategoryById(1));
    }
}

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
import model.BlogCategory;

/**
 *
 * @author PC
 */
public class BlogCategoryDAO extends DBContext {

    public BlogCategory getBlogCategoryById(int id) {
        String sql = "select * from Blog_category\n"
                + "where Blog_category_id = ? and deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int blogCategoryId = rs.getInt("Blog_category_id");
                String blogCategoryName = rs.getString("Blog_Category_name");
                int deleted = rs.getInt("Deleted");

                BlogCategory bc = new BlogCategory(blogCategoryId, blogCategoryName, deleted);
                return bc;
            }
        } catch (SQLException e) {
            System.out.println("loi get blog category by Id: " + e);
        }
        return null;
    }

    public List<BlogCategory> getBlogCategoryAll() {
        List<BlogCategory> list = new ArrayList<>();
        String sql = "select * from Blog_category\n"
                + "where Deleted = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blogCategoryId = rs.getInt("Blog_category_id");
                String blogCategoryName = rs.getString("Blog_Category_name");
                int deleted = rs.getInt("Deleted");

                BlogCategory bc = new BlogCategory(blogCategoryId, blogCategoryName, deleted);
                list.add(bc);
            }
        } catch (SQLException e) {
            System.out.println("loi blog category all: " + e);
        }
        return list;
    }

    public static void main(String[] args) {
        BlogCategoryDAO bcd = new BlogCategoryDAO();
//        System.out.println(bcd.getBlogCategoryById(1));
        for (BlogCategory x : bcd.getBlogCategoryAll()) {
            System.out.println(x);
        }
    }

    public BlogCategory getBlogName(int id) {
        BlogCategory bc = null;
        String sql = "select * from Blog_category where Blog_category_id = ? and Deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bc = new BlogCategory(rs.getInt(1),
                        rs.getString(2),
                        rs.getByte(3));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bc;
    }

    // lấy tất cả thể loại blog kèm ID của chúng
    public List<BlogCategory> getListBlogCategory() {
        List<BlogCategory> bc = new ArrayList<>();
        String sql = "select*from Blog_category  where  Deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bc.add(new BlogCategory(rs.getInt(1),
                        rs.getString(2),
                        rs.getByte(3)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bc;
    }
    
        }

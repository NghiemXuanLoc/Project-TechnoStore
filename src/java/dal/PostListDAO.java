/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import model.*;

/**
 *
 * @author quang
 */
public class PostListDAO extends DBContext {

    /*Lấy ra toàn bộ danh sách các bài viết chưa xóa deleted = 0*/
    public List<BlogDetail> listAllPost() {
        List<BlogDetail> listBlog = new ArrayList<>();
        String sql = "select * from Blog_detail bd inner join Blog_category bc on bd.Blog_category_id = bc.Blog_category_id";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date date = null;
                Date date2 = null;
                if (rs.getString(8) != null) {
                    date = format.parse(rs.getString(8));
                }
                if (rs.getString(9) != null) {
                    date2 = format.parse(rs.getString(9));
                }
                BlogCategory bC = new BlogCategory(rs.getInt(1), rs.getString("Blog_Category_name"), rs.getInt(3));
                User u = new User(rs.getInt(3));
                listBlog.add(new BlogDetail(rs.getInt(1),
                        bC,
                        u,
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        date,
                        date2,
                        rs.getString(10),
                        rs.getByte(11)
                ));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBlog;
    }

    /*Lấy ra các tác giả, thể loại bài viết với hash set, không trùng lặp*/
    public List<String> getUniqueAuthor(List<BlogDetail> ld) {
        Set<String> filterSet = new HashSet<>();
        for (BlogDetail p : ld) {
            filterSet.add(p.getAuthor());
        }
        return new ArrayList<>(filterSet);
    }

    public List<String> getUniqueCategories(List<BlogDetail> ld) {
        Set<String> filterSet = new HashSet<>();
        for (BlogDetail p : ld) {
            filterSet.add(p.getBlogCategory().getBlogCategoryName());
        }
        return new ArrayList<>(filterSet);
    }

    /*Phân trang hiển thị số lượng bài viết mỗi trang do người dùng chọn*/
    public List<BlogDetail> pagingBlog(List<BlogDetail> listBlog, int iPre, int postsPerPage) {
        List<BlogDetail> pagedListBlog = new ArrayList<>();
        int start = (iPre - 1) * postsPerPage;
        int end = Math.min(start + postsPerPage, listBlog.size());
        if (start < 0 || start >= listBlog.size()) {
            return pagedListBlog;
        }
        for (int i = start; i < end; i++) {
            pagedListBlog.add(listBlog.get(i));
        }
        return pagedListBlog;
    }

    private String sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        input = input.trim();
        return input.replaceAll("\\s+", " ");
    }

    /*Tìm kiếm bài viết theo title nhập vào bởi người dùng*/
    public List<BlogDetail> searchByTitleInList(List<BlogDetail> list, String search) {
        List<BlogDetail> result = new ArrayList<>();
        search = sanitizeInput(search);
        for (BlogDetail post : list) {
            if (post.getTitle().toLowerCase().contains(search.toLowerCase())) {
                result.add(post);
            }
        }
        return result;
    }

    /*Filter the tác giả */
    public List<BlogDetail> filterByAuthorInList(List<BlogDetail> list, String author) {
        List<BlogDetail> result = new ArrayList<>();
        for (BlogDetail post : list) {
            if (post.getAuthor().equals(author)) {
                result.add(post);
            }
        }
        return result;
    }

    /*Fliter by blog category*/
    public List<BlogDetail> filterByBlogCateInList(List<BlogDetail> list, String category) {
        List<BlogDetail> result = new ArrayList<>();
        for (BlogDetail post : list) {
            if (post.getBlogCategory().getBlogCategoryName().equalsIgnoreCase(category)) {
                result.add(post);
            }
        }
        return result;
    }

    /*Filter by active deleted = 0 là active hoặc deleted = 1 là hiden để hiển thị trạng thái*/
    public List<BlogDetail> filterByActiveInList(List<BlogDetail> list, int active) {
        List<BlogDetail> result = new ArrayList<>();
        for (BlogDetail post : list) {
            if (post.getDeleted() == active) {
                result.add(post);
            }
        }
        return result;
    }

    /*Update  Status set deleted = 1 - 0 sau mỗi lần người dùng click vào */
    public void updateStatus(int blogID) {
        String sql = "SELECT deleted FROM Blog_detail WHERE Blog_detail_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean isDeleted = rs.getInt("deleted") == 1;
                // Toggle the status
                isDeleted = !isDeleted;
                // Update the status
                sql = "UPDATE Blog_detail SET deleted = ? WHERE Blog_detail_id = ?";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, isDeleted ? 1 : 0);
                ps.setInt(2, blogID);
                ps.executeUpdate();
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BlogDetail getBlogDetailById(int id) {
        BlogCategoryDAO bcd = new BlogCategoryDAO();
        UserDAO ud = new UserDAO();
        String sql = "select * from Blog_detail\n"
                + "where Blog_detail_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int blogDetailId = rs.getInt("Blog_detail_id");
                BlogCategory blogCategory = bcd.getBlogCategoryById(rs.getInt("Blog_category_id"));
                User user = ud.getUserById(rs.getInt("User_id"));
                String thumbnail = rs.getString("thumbnail");
                String title = rs.getString("Title");
                String shortDescription = rs.getString("Short_description");
                String content = rs.getString("Content");
                Date blogDate = rs.getDate("Blog_date");
                Date blogDateUpdate = rs.getDate("Blog_date_update");
                String author = rs.getString("Author");
                int deleted = rs.getInt("Deleted");

                BlogDetail bd = new BlogDetail(blogDetailId, blogCategory, user, thumbnail, title, shortDescription, content, blogDate, blogDateUpdate, author, deleted);

                return bd;
            }
        } catch (SQLException e) {
            System.out.println("loi get blog detail by Id: " + e);
        }
        return null;
    }

    /*Add new Post*/
    public void addPost(int blogCategoryId, int userId, String thumbnail, String title, String shortDescription, String content, Date blogDate, String author) {
        String sql = "INSERT INTO Blog_detail (Blog_category_id, User_id, thumbnail, Title, Short_Description, Content, blog_date, Blog_date_update, Author, Deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blogCategoryId);
            ps.setInt(2, userId);
            ps.setString(3, thumbnail);
            ps.setString(4, title);
            ps.setString(5, shortDescription);
            ps.setString(6, content);
            ps.setDate(7, (java.sql.Date) blogDate);
            ps.setDate(8, null);
            ps.setString(9, author);
            ps.setBoolean(10, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*Add new Post Category*/
    public int addCategory(String categoryName) {
        int categoryId = 0;
        String sql = "INSERT INTO dbo.Blog_category (Blog_Category_name, Deleted) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, categoryName);
            ps.setBoolean(2, false); // Assuming new categories are not deleted by default
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    categoryId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

    /*Update post */
    public void updatePost(int blogID, int blogCategoryId, String thumbnail, String title, String shortDescription, String content, Date blogCreated, Date blogDateUpdate, String author) {
        String sql = "UPDATE Blog_detail SET Blog_category_id = ?, thumbnail = ?, Title = ?, Short_Description = ?, Content = ?,blog_date = ?, Blog_date_update = ?, Author = ? WHERE Blog_detail_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blogCategoryId);
            ps.setString(2, thumbnail);
            ps.setString(3, title);
            ps.setString(4, shortDescription);
            ps.setString(5, content);
            ps.setDate(6, (java.sql.Date) blogCreated);
            ps.setDate(7, (java.sql.Date) blogDateUpdate);
            ps.setString(8, author);
            ps.setInt(9, blogID);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

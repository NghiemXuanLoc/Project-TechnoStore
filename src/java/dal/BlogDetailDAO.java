/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.BlogCategory;
import model.BlogDetail;
import model.User;

/**
 *
 * @author PC
 */
public class BlogDetailDAO extends DBContext {

    public BlogDetail getBlogDetailById(int id) {
        BlogCategoryDAO bcd = new BlogCategoryDAO();
        UserDAO ud = new UserDAO();
        String sql = "select * from Blog_detail\n"
                + "where Blog_detail_id = ? and Deleted = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, 0);
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

    public List<BlogDetail> getTop4LastBlogDetail() {
        BlogCategoryDAO bcd = new BlogCategoryDAO();
        UserDAO ud = new UserDAO();
        List<BlogDetail> list = new ArrayList<>();
        String sql = "select top 4 * from Blog_detail as bd\n"
                + "where bd.Deleted = ?\n"
                + "order by bd.blog_date desc, bd.Title";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                list.add(bd);
            }
        } catch (SQLException e) {
            System.out.println("loi blog detail all: " + e);
        }
        return list;
    }

    public static void main(String[] args) {
        BlogDetailDAO bdd = new BlogDetailDAO();
//        System.out.println(bdd.getBlogDetailById(1));
        for (BlogDetail x : bdd.getTop4LastBlogDetail()) {
            System.out.println(x);
        }
    }
    
    /* Lấy ra phần nội dung của 1 bài viết để hiển thị ra bên trang chi 
    tiết của bài viết, theo tham số là ID của bài viết đó được truyền vào*/
    public BlogDetail getContentByID(String blogID) {
        BlogDetail bd = null;
        String sql = "select * from Blog_detail bd inner join Blog_category bc on bd.Blog_category_id = bc.Blog_category_id where bd.Blog_detail_id like ? and bc.Deleted = 0 and bd.Deleted = 0";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, blogID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                bd = new BlogDetail(rs.getInt(1),
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
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bd;
    }

    // In ra danh sách các bài viết sau khi người dùng nhập vào ô search sider
    public List<BlogDetail> searchBlogByString(String title) {
        List<BlogDetail> listBlog = new ArrayList<>();
        String sql = "select*from Blog_detail where Title like ? and Deleted = 0 order by Blog_date_update DESC";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
           PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
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
                BlogCategory bC = new BlogCategory(rs.getInt(1), title, rs.getInt(3));
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

    /*Hàm dùng để phân trang theo các tham số trang trước và trang sau truyền vào*/
    public List<BlogDetail> pagingBlog(int iPre) {
        List<BlogDetail> listBlog = new ArrayList<>();
        String sql = "select *from Blog_detail order by Blog_date_update desc offset ? rows fetch first 4 rows only";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
           PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (iPre - 1) * 4);
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
                BlogCategory bC = new BlogCategory(rs.getInt(1), rs.getString(2), rs.getInt(3));
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

    /*Hàm dùng để phân trang theo các tham số chuỗi title người dùng cần tìm kiếm và trang*/
    public List<BlogDetail> pagingSearch(String sBlog, int iPre) {
        List<BlogDetail> listBlog = new ArrayList<>();
        String sql = "select *from Blog_detail where Title like ? and Deleted = 0  order by Blog_date_update desc offset ? rows fetch first 4 rows only";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + sBlog + "%");
            ps.setInt(2, (iPre - 1) * 4);
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
                BlogCategory bC = new BlogCategory(rs.getInt(1), rs.getString(2), rs.getInt(3));
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
    
    /*Hàm đếm số trang dựa vào chuỗi yêu cầu người dùng nhập vào*/
    public int cPageSearch(String title) {
        String sql = "select count(*) from Blog_detail where Title like ? and Deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int cPage = 0;
                cPage = total / 4;
                if (total % 4 != 0) {
                    cPage++;
                }
                return cPage;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return 0;
    }
    
    /*Hàm dùng để phân trang theo thể loại bài viết mà  người dùng click bên left sider bar*/
     public List<BlogDetail> pagingCategory(String bid, int iPre) {
        List<BlogDetail> listBlog = new ArrayList<>();
        String sql = "select *from Blog_detail where Blog_category_id=? and Deleted = 0  order by Blog_date_update desc offset ? rows fetch first 4 rows only";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(bid));
            ps.setInt(2, (iPre - 1) * 4);
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
                BlogCategory bC = new BlogCategory(rs.getInt(1), rs.getString(2), rs.getInt(3));
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
    
      /*Hàm đếm số trang dựa vào chuỗi yêu cầu người dùng nhập vào*/
    public int cPageCategory(String bid) {
        String sql = "select count(*) from Blog_detail where Blog_category_id=? and Deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(bid));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int cPage = 0;
                cPage = total / 4;
                if (total % 4 != 0) {
                    cPage++;
                }
                return cPage;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
     
    /*Đếm xem trong db có bao bài viết
    nếu nhiều hơn so với số bài cần hiển thị thì + 1 trang nữa*/
    public int countNumPage() {
        String sql = "select count(*) from Blog_detail where Deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int cPage = 0;
                cPage = total / 4;
                if (total % 4 != 0) {
                    cPage++;
                }
                return cPage;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return 0;
    }

    /*Liệt kê ra 3 bài viết gần đây nhất bên sider right*/
    public List<BlogDetail> lastestBLog() {
        List<BlogDetail> listBlog = new ArrayList<>();
        String sql = "SELECT TOP 3 * FROM Blog_detail  where Deleted = 0 ORDER BY blog_date DESC";
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
                BlogCategory bC = new BlogCategory(rs.getInt(1), rs.getString(2), rs.getInt(3));
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
}

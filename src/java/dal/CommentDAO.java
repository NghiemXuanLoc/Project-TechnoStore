/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.BlogDetail;
import model.Comment;
import model.User;

/**
 *
 * @author PC
 */
public class CommentDAO extends DBContext {

    //    	[Comment_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NOT NULL,
//	[Blog_detail_id] [int] NOT NULL,
//	[Rating] [int] NOT NULL,
//	[comment_date] [datetime] NOT NULL,
//	[Comment_update] [datetime] NULL,
//	[Content] [nvarchar](max) NOT NULL,
//	[Likes] [int] NULL,
//	[Report] [bit] NULL,
//	[deleted] [bit] NOT NULL,
    public List<Comment> getCommentAllByBlogDetailId(int blogDetailId) {
        UserDAO ud = new UserDAO();
        BlogDetailDAO bdd = new BlogDetailDAO();

        List<Comment> list = new ArrayList<>();
        String sql = "select top 3 * from Comment\n"
                + "where Blog_detail_id = ? and deleted != ?\n"
                + "order by Comment_update desc, comment_date desc, Comment_id desc";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, blogDetailId);
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int commentId = rs.getInt("Comment_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                BlogDetail bd = bdd.getBlogDetailById(rs.getInt("Blog_detail_id"));
                int rating = rs.getInt("Rating");
                Date commentDate = rs.getDate("comment_date");
                Date commentUpdate = rs.getDate("Comment_update");
                String content = rs.getString("Content");
                int likes = rs.getInt("Likes");
                int report = rs.getInt("Report");
                int deleted = rs.getInt("deleted");

                Comment comment = new Comment(commentId, user, bd, rating, commentDate, commentUpdate, content, likes, report, deleted);
                list.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public static void main(String[] args) {
        CommentDAO cd = new CommentDAO();

        for (Comment c : cd.getCommentAll()) {
            System.out.println(c);
        }
    }

    public void insert(int userId, int blogDetailId, int rating, String content) {
        // khoi tao sql
        String sql = "INSERT INTO [dbo].[Comment] ([User_id], [Blog_detail_id], [Rating], [comment_date], [Comment_update], [Content], [Likes], [Report], [deleted])\n"
                + "VALUES \n"
                + "(?, ?, ?, GETDATE(), NULL, ?, 0, 0, 0)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // chen cac cau lenh ps.set vao day

            ps.setInt(1, userId);
            ps.setInt(2, blogDetailId);
            ps.setInt(3, rating);
            ps.setString(4, content);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi insert comment blog detail: " + e);
        }
    }

    public void update(int commentId, String content) {
        // cau lenh khoi tao update
        String sql = "update Comment\n"
                + "set\n"
                + "Content = ?,\n"
                + "Comment_update = GETDATE()\n"
                + "where Comment_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // cau lenh ps.set cac update can thiet
            ps.setString(1, content);
            ps.setInt(2, commentId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi update comment: " + e);
        }
    }

    public void updateForDelete(int commentId) {
        // cau lenh khoi tao update
        String sql = "update Comment\n"
                + "set\n"
                + "deleted = ?\n"
                + "where Comment_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // cau lenh ps.set cac update can thiet
            ps.setInt(1, 1);
            ps.setInt(2, commentId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi delete comment: " + e);
        }
    }

    public List<Comment> loadMoreComments(int blogDetailId, int countComment) {
        UserDAO ud = new UserDAO();
        BlogDetailDAO bdd = new BlogDetailDAO();

        List<Comment> list = new ArrayList<>();
        String sql = "select * from Comment\n"
                + "where Blog_detail_id = ? and deleted != ?\n"
                + "order by Comment_update desc, comment_date desc, Comment_id desc\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, blogDetailId);
            ps.setInt(2, 1);
            ps.setInt(3, countComment);
            ps.setInt(4, 3);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int commentId = rs.getInt("Comment_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                BlogDetail bd = bdd.getBlogDetailById(rs.getInt("Blog_detail_id"));
                int rating = rs.getInt("Rating");
                Date commentDate = rs.getDate("comment_date");
                Date commentUpdate = rs.getDate("Comment_update");
                String content = rs.getString("Content");
                int likes = rs.getInt("Likes");
                int report = rs.getInt("Report");
                int deleted = rs.getInt("deleted");

                Comment comment = new Comment(commentId, user, bd, rating, commentDate, commentUpdate, content, likes, report, deleted);
                list.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public List<Comment> getCountAllCommentByBlogDetailId(int blogDetailId) {
        UserDAO ud = new UserDAO();
        BlogDetailDAO bdd = new BlogDetailDAO();

        List<Comment> list = new ArrayList<>();
        String sql = "select * from Comment\n"
                + "where Blog_detail_id = ? and deleted != ?\n"
                + "order by Comment_update desc, comment_date desc, Comment_id desc";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, blogDetailId);
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int commentId = rs.getInt("Comment_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                BlogDetail bd = bdd.getBlogDetailById(rs.getInt("Blog_detail_id"));
                int rating = rs.getInt("Rating");
                Date commentDate = rs.getDate("comment_date");
                Date commentUpdate = rs.getDate("Comment_update");
                String content = rs.getString("Content");
                int likes = rs.getInt("Likes");
                int report = rs.getInt("Report");
                int deleted = rs.getInt("deleted");

                Comment comment = new Comment(commentId, user, bd, rating, commentDate, commentUpdate, content, likes, report, deleted);
                list.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public List<Comment> getCommentAll() {
        UserDAO ud = new UserDAO();
        BlogDetailDAO bdd = new BlogDetailDAO();

        List<Comment> list = new ArrayList<>();
        String sql = "select * from Comment\n";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int commentId = rs.getInt("Comment_id");
                User user = ud.getUserById(rs.getInt("User_id"));
                BlogDetail bd = bdd.getBlogDetailById(rs.getInt("Blog_detail_id"));
                int rating = rs.getInt("Rating");
                Date commentDate = rs.getDate("comment_date");
                Date commentUpdate = rs.getDate("Comment_update");
                String content = rs.getString("Content");
                int likes = rs.getInt("Likes");
                int report = rs.getInt("Report");
                int deleted = rs.getInt("deleted");

                Comment comment = new Comment(commentId, user, bd, rating, commentDate, commentUpdate, content, likes, report, deleted);
                list.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("loi get all comment: " + e);
        }
        return list;
    }
    
  
}

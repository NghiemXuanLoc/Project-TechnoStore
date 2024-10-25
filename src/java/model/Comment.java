/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Comment {
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
    
    private int commentId;
    private User user;
    private BlogDetail blogDetail;
    private int rating;
    private Date commentDate;
    private Date commentUpdate;
    private String content;
    private int likes;
    private int report;
    private int deleted;

    public Comment() {
    }

    public Comment(int commentId, User user, BlogDetail blogDetail, int rating, Date commentDate, Date commentUpdate, String content, int likes, int report, int deleted) {
        this.commentId = commentId;
        this.user = user;
        this.blogDetail = blogDetail;
        this.rating = rating;
        this.commentDate = commentDate;
        this.commentUpdate = commentUpdate;
        this.content = content;
        this.likes = likes;
        this.report = report;
        this.deleted = deleted;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BlogDetail getBlogDetail() {
        return blogDetail;
    }

    public void setBlogDetail(BlogDetail blogDetail) {
        this.blogDetail = blogDetail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Date getCommentUpdate() {
        return commentUpdate;
    }

    public void setCommentUpdate(Date commentUpdate) {
        this.commentUpdate = commentUpdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", user=" + user + ", blogDetail=" + blogDetail + ", rating=" + rating + ", commentDate=" + commentDate + ", commentUpdate=" + commentUpdate + ", content=" + content + ", likes=" + likes + ", report=" + report + ", deleted=" + deleted + '}';
    }
}

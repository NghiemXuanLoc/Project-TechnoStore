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
public class ReplyComment {
//    	[Reply_comment_id] [int] IDENTITY(1,1) NOT NULL,
//	[Comment_id] [int] NOT NULL,
//	[User_id] [int] NOT NULL,
//	[Content] [nvarchar](max) NOT NULL,
//	[Reply_date] [datetime] NOT NULL,
//	[Reply_update] [datetime] NULL,
//	[likes] [int] NULL,
//	[report] [int] NULL,
//	[deleted] [bit] NOT NULL,
    
    private int relyCommentId;
    private Comment comment;
    private User user;
    private String content;
    private Date replyDate;
    private Date relyUpdate;
    private int likes;
    private int report;
    private int deleted;

    public ReplyComment() {
    }

    public ReplyComment(int relyCommentId, Comment comment, User user, String content, Date replyDate, Date relyUpdate, int likes, int report, int deleted) {
        this.relyCommentId = relyCommentId;
        this.comment = comment;
        this.user = user;
        this.content = content;
        this.replyDate = replyDate;
        this.relyUpdate = relyUpdate;
        this.likes = likes;
        this.report = report;
        this.deleted = deleted;
    }

    public int getRelyCommentId() {
        return relyCommentId;
    }

    public void setRelyCommentId(int relyCommentId) {
        this.relyCommentId = relyCommentId;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public Date getRelyUpdate() {
        return relyUpdate;
    }

    public void setRelyUpdate(Date relyUpdate) {
        this.relyUpdate = relyUpdate;
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
        return "ReplyComment{" + "relyCommentId=" + relyCommentId + ", comment=" + comment + ", user=" + user + ", content=" + content + ", replyDate=" + replyDate + ", relyUpdate=" + relyUpdate + ", likes=" + likes + ", report=" + report + ", deleted=" + deleted + '}';
    }
}

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
public class BlogDetail {
//    [Blog_detail_id] [int] IDENTITY(1,1) NOT NULL,
//	[Blog_category_id] [int] NOT NULL,
//	[User_id] [int] NOT NULL,
//	[thumbnail] [nvarchar](max) NOT NULL,
//	[Title] [nvarchar](200) NOT NULL,
//	[Short_Description] [nvarchar](200) NULL,
//	[Content] [nvarchar](max) NOT NULL,
//	[blog_date] [date] NOT NULL,
//	[Blog_date_update] [date] NULL,
//	[Author] [nvarchar](max) NULL,
//	[Deleted] [bit] NOT NULL,
    
    private int blogDetailId;
    private BlogCategory blogCategory;
    private User user;
    private String thumbnail;
    private String title;
    private String shortDescription;
    private String content;
    private Date blogDate;
    private Date blogDateUpdate;
    private String author;
    private int deleted;

    public BlogDetail() {
    }

    public BlogDetail(int blogDetailId, BlogCategory blogCategory, User user, String thumbnail, String title, String shortDescription, String content, Date blogDate, Date blogDateUpdate, String author, int deleted) {
        this.blogDetailId = blogDetailId;
        this.blogCategory = blogCategory;
        this.user = user;
        this.thumbnail = thumbnail;
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.blogDate = blogDate;
        this.blogDateUpdate = blogDateUpdate;
        this.author = author;
        this.deleted = deleted;
    }

    public int getBlogDetailId() {
        return blogDetailId;
    }

    public void setBlogDetailId(int blogDetailId) {
        this.blogDetailId = blogDetailId;
    }

    public BlogCategory getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(BlogCategory blogCategory) {
        this.blogCategory = blogCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(Date blogDate) {
        this.blogDate = blogDate;
    }

    public Date getBlogDateUpdate() {
        return blogDateUpdate;
    }

    public void setBlogDateUpdate(Date blogDateUpdate) {
        this.blogDateUpdate = blogDateUpdate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "BlogDetail{" + "blogDetailId=" + blogDetailId + ", blogCategory=" + blogCategory + ", user=" + user + ", thumbnail=" + thumbnail + ", title=" + title + ", shortDescription=" + shortDescription + ", content=" + content + ", blogDate=" + blogDate + ", blogDateUpdate=" + blogDateUpdate + ", author=" + author + ", deleted=" + deleted + '}';
    }
    
}

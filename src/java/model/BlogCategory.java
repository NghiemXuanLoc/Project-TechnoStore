/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class BlogCategory {
//    [Blog_category_id] [int] IDENTITY(1,1) NOT NULL,
//	[Blog_Category_name] [nvarchar](200) NOT NULL,
//	[Deleted] [bit] NOT NULL,
    private int blogCategoryId;
    private String blogCategoryName;
    private int deleted;

    public BlogCategory() {
    }

    public BlogCategory(int blogCategoryId, String blogCategoryName, int deleted) {
        this.blogCategoryId = blogCategoryId;
        this.blogCategoryName = blogCategoryName;
        this.deleted = deleted;
    }

    public int getBlogCategoryId() {
        return blogCategoryId;
    }

    public void setBlogCategoryId(int blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }

    public String getBlogCategoryName() {
        return blogCategoryName;
    }

    public void setBlogCategoryName(String blogCategoryName) {
        this.blogCategoryName = blogCategoryName;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "BlogCategory{" + "blogCategoryId=" + blogCategoryId + ", blogCategoryName=" + blogCategoryName + ", deleted=" + deleted + '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Category {
//    [Category_id] [int] IDENTITY(1,1) NOT NULL,
//	[Category_name] [nvarchar](200) NOT NULL,
//	[Short_description] [nvarchar](200) NULL,
//	[Description] [nvarchar](max) NULL,
//	[Deleted] [bit] NOT NULL,
    
    private int categoryId;
    private String categoryName;
    private String shortDescription;
    private String description;
    private int deleted;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String shortDescription, String description, int deleted) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.shortDescription = shortDescription;
        this.description = description;
        this.deleted = deleted;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + ", shortDescription=" + shortDescription + ", description=" + description + ", deleted=" + deleted + '}';
    }
    
}

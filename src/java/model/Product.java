/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class Product {
//[Product_id] [int] IDENTITY(1,1) NOT NULL,
//	[Category_id] [int] NOT NULL,
//	[Supplier_id] [int] NOT NULL,
//	[Product_name] [nvarchar](200) NOT NULL,
//	[Avg_rating] [float] NULL,
//	[Brief_info] [nvarchar](max) NULL,
//	[Description] [nvarchar](max) NULL,
//	[Create_at] [date] NOT NULL,
//	[Update_at] [date] NULL,
//	[Deleted] [bit] NOT NULL,

    private int productId;
    private Category category;
    private Supplier supplier;
    private String productName;
    private float avgRating;
    private String briefInfo;
    private String description;
    private Date createAt;
    private Date updateAt;
    private int deleted;

    private List<String> gallery;

    private double minPrice;
    private double maxPrice;
    
    private double minPriceOrigin;
    private double maxPriceOrigin;
    
    public Product() {
    }

    public Product(int productId, Category category, Supplier supplier, String productName, float avgRating, String briefInfo, String description, Date createAt, Date updateAt, int deleted, List<String> gallery, double minPrice, double maxPrice, double minPriceOrigin, double maxPriceOrigin) {
        this.productId = productId;
        this.category = category;
        this.supplier = supplier;
        this.productName = productName;
        this.avgRating = avgRating;
        this.briefInfo = briefInfo;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleted = deleted;
        this.gallery = gallery;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minPriceOrigin = minPriceOrigin;
        this.maxPriceOrigin = maxPriceOrigin;
    }
    
    
    

    public Product(int productId, Category category, Supplier supplier, String productName, float avgRating, String briefInfo, String description, Date createAt, Date updateAt, int deleted, List<String> gallery) {
        this.productId = productId;
        this.category = category;
        this.supplier = supplier;
        this.productName = productName;
        this.avgRating = avgRating;
        this.briefInfo = briefInfo;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleted = deleted;
        this.gallery = gallery;
    }
    
    

    public Product(int productId, Category category, Supplier supplier, String productName, float avgRating, String briefInfo, String description, Date createAt, Date updateAt, int deleted, List<String> gallery, double minPrice, double maxPrice) {
        this.productId = productId;
        this.category = category;
        this.supplier = supplier;
        this.productName = productName;
        this.avgRating = avgRating;
        this.briefInfo = briefInfo;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleted = deleted;
        this.gallery = gallery;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPriceOrigin() {
        return minPriceOrigin;
    }

    public void setMinPriceOrigin(double minPriceOrigin) {
        this.minPriceOrigin = minPriceOrigin;
    }

    public double getMaxPriceOrigin() {
        return maxPriceOrigin;
    }

    public void setMaxPriceOrigin(double maxPriceOrigin) {
        this.maxPriceOrigin = maxPriceOrigin;
    }
    

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", category=" + category + ", supplier=" + supplier + ", productName=" + productName + ", avgRating=" + avgRating + ", briefInfo=" + briefInfo + ", description=" + description + ", createAt=" + createAt + ", updateAt=" + updateAt + ", deleted=" + deleted + ", gallery=" + gallery + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + '}';
    }
}

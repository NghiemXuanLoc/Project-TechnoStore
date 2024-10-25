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
public class ProductContribution {
//    	[Prouduct_Contribution_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NULL,
//	[Product_id] [int] NULL,
//	[Content] [nvarchar](max) NULL,
//	[Date] [date] NULL
    
    private int productContributionId;
    private User user;
    private Product product;
    private String content;
    private Date date;

    public ProductContribution() {
    }

    public ProductContribution(int productContributionId, User user, Product product, String content, Date date) {
        this.productContributionId = productContributionId;
        this.user = user;
        this.product = product;
        this.content = content;
        this.date = date;
    }

    public int getProductContributionId() {
        return productContributionId;
    }

    public void setProductContributionId(int productContributionId) {
        this.productContributionId = productContributionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ProductContributions{" + "productContributionId=" + productContributionId + ", user=" + user + ", product=" + product + ", content=" + content + ", date=" + date + '}';
    }
}

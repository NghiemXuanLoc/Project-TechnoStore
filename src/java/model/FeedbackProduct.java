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
public class FeedbackProduct {
//    [Feedback_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NOT NULL,
//	[Product_detail_id] [int] NOT NULL,
//	[Feedback_date] [datetime] NOT NULL,
//	[Feedback_update] [datetime] NULL,
//	[Rating] [int] NOT NULL,
//	[Edit_nubmer] [int] NOT NULL,
//	[Content] [nvarchar](max) NULL,
//	[Likes] [int] NULL,
//	[Report] [int] NULL,
//	[deleted] [bit] NOT NULL,
    
    
//        [Feedback_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NOT NULL,
//	[Order_id] [int] NOT NULL,
//	[Product_detail_id] [int] NOT NULL,
//	[Feedback_date] [datetime] NOT NULL,
//	[Feedback_update] [datetime] NULL,
//	[Rating] [int] NOT NULL,
//	[Edit_nubmer] [int] NOT NULL,
//	[Content] [nvarchar](max) NULL,
//	[Full_Name] [nvarchar](max) NULL,
//	[Gender] [bit] NULL,
//	[Email] [nvarchar](50) NULL,
//	[Phone_Number] [nvarchar](10) NULL,
//	[deleted] [bit] NOT NULL,
    
    private int feedbackId;
    private User user;
    private Orders order;
    private ProductDetail productDetail;
    private Date feedbackDate;
    private Date feedbackUpdate;
    private int rating;
    private int editNumber;
    private String content;
    private String fullName;
    private int gender;
    private String email;
    private String phoneNumber;
    private int deleted;

    public FeedbackProduct() {
    }

    public FeedbackProduct(int feedbackId, User user, Orders order, ProductDetail productDetail, Date feedbackDate, Date feedbackUpdate, int rating, int editNumber, String content, String fullName, int gender, String email, String phoneNumber, int deleted) {
        this.feedbackId = feedbackId;
        this.user = user;
        this.order = order;
        this.productDetail = productDetail;
        this.feedbackDate = feedbackDate;
        this.feedbackUpdate = feedbackUpdate;
        this.rating = rating;
        this.editNumber = editNumber;
        this.content = content;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.deleted = deleted;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public Date getFeedbackUpdate() {
        return feedbackUpdate;
    }

    public void setFeedbackUpdate(Date feedbackUpdate) {
        this.feedbackUpdate = feedbackUpdate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getEditNumber() {
        return editNumber;
    }

    public void setEditNumber(int editNumber) {
        this.editNumber = editNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "FeedbackProduct{" + "feedbackId=" + feedbackId + ", user=" + user + ", order=" + order + ", productDetail=" + productDetail + ", feedbackDate=" + feedbackDate + ", feedbackUpdate=" + feedbackUpdate + ", rating=" + rating + ", editNumber=" + editNumber + ", content=" + content + ", fullName=" + fullName + ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber + ", deleted=" + deleted + '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class FeedbackDetail {
    private int feedbackId;
    private String fullName;
    private String email;
    private String mobile;
    private String product;
    private int ratedStar;
    private String contentFeedback;
    private int status;

    public FeedbackDetail() {
    }

    public FeedbackDetail(int feedbackId, String fullName, String email, String mobile, String product, int ratedStar, String contentFeedback, int status) {
        this.feedbackId = feedbackId;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.product = product;
        this.ratedStar = ratedStar;
        this.contentFeedback = contentFeedback;
        this.status = status;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getRatedStar() {
        return ratedStar;
    }

    public void setRatedStar(int ratedStar) {
        this.ratedStar = ratedStar;
    }

    public String getContentFeedback() {
        return contentFeedback;
    }

    public void setContentFeedback(String contentFeedback) {
        this.contentFeedback = contentFeedback;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}

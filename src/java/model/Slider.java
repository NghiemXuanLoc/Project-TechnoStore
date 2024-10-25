/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Slider {
//      [Slider_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NOT NULL,
//	[Update_by] [int] NULL,
//	[Title] [nvarchar](max) NULL,
//	[Image] [nvarchar](max) NOT NULL,
//	[Back_link] [nvarchar](max) NOT NULL,
//	[deleted] [bit] NOT NULL,
    
    private int sliderId;
    private User user;
    private User updateByUser;
    private String title;
    private String image;
    private String backLink;
    private int deleted;

    public Slider() {
    }

    public Slider(int sliderId, User user, User updateByUser, String title, String image, String backLink, int deleted) {
        this.sliderId = sliderId;
        this.user = user;
        this.updateByUser = updateByUser;
        this.title = title;
        this.image = image;
        this.backLink = backLink;
        this.deleted = deleted;
    }

    public int getSliderId() {
        return sliderId;
    }

    public void setSliderId(int sliderId) {
        this.sliderId = sliderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUpdateByUser() {
        return updateByUser;
    }

    public void setUpdateByUser(User updateByUser) {
        this.updateByUser = updateByUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackLink() {
        return backLink;
    }

    public void setBackLink(String backLink) {
        this.backLink = backLink;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Slider{" + "sliderId=" + sliderId + ", user=" + user + ", updateByUser=" + updateByUser + ", title=" + title + ", image=" + image + ", backLink=" + backLink + ", deleted=" + deleted + '}';
    }
}

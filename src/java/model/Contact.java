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
public class Contact {
//[Contact_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NULL,
//	[Full_name] [nvarchar](50) NULL,
//	[Email] [nvarchar](50) NULL,
//	[Gender] [bit] NULL,
//	[PhoneNumber] [nvarchar](10) NULL,
//	[Rating] [int] NULL,
//	[Subject] [nvarchar](200) NULL,
//	[Content] [nvarchar](max) NULL,
//	[CreatedAt] [datetime] NULL,
//	[UpdatedAt] [datetime] NULL
    
    private int contactId;
    private User user;
    private String fullName;
    private String email;
    private int gender;
    private String phoneNumber;
    private int rating;
    private String subject;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    public Contact() {
    }

    public Contact(int contactId, User user, String fullName, String email, int gender, String phoneNumber, int rating, String subject, String content, Date createdAt, Date updatedAt) {
        this.contactId = contactId;
        this.user = user;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.subject = subject;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Contact{" + "contactId=" + contactId + ", user=" + user + ", fullName=" + fullName + ", email=" + email + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", rating=" + rating + ", subject=" + subject + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;
import java.util.Map;

//    [User_id] [int] IDENTITY(1,1) NOT NULL,
//	[Role_id] [int] NOT NULL,
//	[Full_name] [nvarchar](50) NULL,
//	[Password] [nvarchar](50) NOT NULL,
//	[Image] [nvarchar](max) NULL,
//	[Gender] [bit] NOT NULL,
//	[Email] [nvarchar](50) NOT NULL,
//	[Phone_number] [nvarchar](10) NULL,
//	[Address] [nvarchar](max) NULL,
//	[Date_of_birth] [date] NULL,
//	[created_at] [date] NOT NULL,
//	[updated_at] [date] NULL,
//	[Veryfied_email] [bit] NULL,
//	[deleted] [bit] NOT NULL,
public class User {
    private int userId;
    private Role role;
    private String fullName;
    private String password;
    private String image;
    private int gender;
    private String email;
    private String phoneNumber;
    private String address;
    private Date dateOfBirth;
    private Date createdAt;
    private Date updatedAt;
    private int deleted;
    private String code;
    private String resetPasswordToken;
    private Date resetPasswordExpiry;

    public User() {
    }

    public User(int userId, Role role, String fullName, String password, String image, int gender, String email, String phoneNumber, String address, Date dateOfBirth, Date createdAt, Date updatedAt, int deleted, String resetPasswordToken, Date resetPasswordExpiry) {
        this.userId = userId;
        this.role = role;
        this.fullName = fullName;
        this.password = password;
        this.image = image;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.resetPasswordToken = resetPasswordToken;
        this.resetPasswordExpiry = resetPasswordExpiry;
    }
    
    

    public User(int userId, Role role, String fullName, String password, String image, int gender, String email, String phoneNumber, String address, Date dateOfBirth, Date createdAt, Date updatedAt, int deleted, String code, String resetPasswordToken, Date resetPasswordExpiry) {
        this.userId = userId;
        this.role = role;
        this.fullName = fullName;
        this.password = password;
        this.image = image;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.code = code;
        this.resetPasswordToken = resetPasswordToken;
        this.resetPasswordExpiry = resetPasswordExpiry;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Date getResetPasswordExpiry() {
        return resetPasswordExpiry;
    }

    public void setResetPasswordExpiry(Date resetPasswordExpiry) {
        this.resetPasswordExpiry = resetPasswordExpiry;
    }

//    @Override
//    public String toString() {
//        return "User{" + "userId=" + userId + ", role=" + role + ", fullName=" + fullName + ", password=" + password + ", image=" + image + ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleted=" + deleted + ", code=" + code + ", resetPasswordToken=" + resetPasswordToken + ", resetPasswordExpiry=" + resetPasswordExpiry + '}';
//    }
    public User(int userId) {
        this.userId = userId;
    }
    
    private Map<Integer, Integer> cart;

    public User(int userId, Role role, String fullName, String password, String image, int gender, String email, String phoneNumber, String address, Date dateOfBirth, Date createdAt, Date updatedAt, int deleted, String code, String resetPasswordToken, Date resetPasswordExpiry, Map<Integer, Integer> cart) {
        this.userId = userId;
        this.role = role;
        this.fullName = fullName;
        this.password = password;
        this.image = image;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.code = code;
        this.resetPasswordToken = resetPasswordToken;
        this.resetPasswordExpiry = resetPasswordExpiry;
        this.cart = cart;
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", role=" + role + ", fullName=" + fullName + ", password=" + password + ", image=" + image + ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleted=" + deleted + ", code=" + code + ", resetPasswordToken=" + resetPasswordToken + ", resetPasswordExpiry=" + resetPasswordExpiry + ", cart=" + cart + '}';
    }
}

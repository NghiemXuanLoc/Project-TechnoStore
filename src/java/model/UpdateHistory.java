/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class UpdateHistory {
    int updateHistoryId;
    int userId;
    String email;
    String fullName;
    int gender;
    String phoneNumber;
    String address;
    String updateBy;
    Date updateDate;
    int status;

    public UpdateHistory() {
    }

    public UpdateHistory(int updateHistoryId, int userId, String email, String fullName, int gender, String phoneNumber, String address, String updateBy, Date updateDate, int status) {
        this.updateHistoryId = updateHistoryId;
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
    }
    public UpdateHistory( String email,int userId, String fullName, int gender, String phoneNumber, String address, String updateBy, Date updateDate) {
        this.email = email;
        this.userId = userId;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.updateBy = updateBy;
        this.updateDate = updateDate;   
    }
    public int getUpdateHistoryId() {
        return updateHistoryId;
    }

    public void setUpdateHistoryId(int updateHistoryId) {
        this.updateHistoryId = updateHistoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}

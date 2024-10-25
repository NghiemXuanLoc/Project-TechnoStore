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
public class Orders {
//    [Order_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NOT NULL,
//	[Sale_id] [int] NULL,
//	[Payment_id] [int] NOT NULL,
//	[Status] [int] NOT NULL,
//	[Email] [nvarchar](50) NOT NULL,
//	[Full_name] [nvarchar](100) NOT NULL,
//	[Address] [nvarchar](max) NOT NULL,
//	[Note] [nvarchar](200) NULL,
//	[Phone_number] [nvarchar](10) NOT NULL,
//	[Total_money] [float] NULL,
//	[Order_date] [date] NOT NULL,
//	[Discount] [int] NULL,
//	[deleted] [bit] NOT NULL,
    
    private int orderId;
    private User user;
    private User userSale;
    private Payment payment;
    private int status;
    private String email;
    private String fullName;
    private String address;
    private String note;
    private String phoneNumber;
    private float totalMoney;
    private Date orderDate;
    private int discount;
    private int deleted;
    private String shippingStatus;
    public Orders() {
    }

    public Orders(int orderId, User user, User userSale, Payment payment, int status, String email, String fullName, String address, String note, String phoneNumber, float totalMoney, Date orderDate, int discount, int deleted) {
        this.orderId = orderId;
        this.user = user;
        this.userSale = userSale;
        this.payment = payment;
        this.status = status;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.note = note;
        this.phoneNumber = phoneNumber;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.discount = discount;
        this.deleted = deleted;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserSale() {
        return userSale;
    }

    public void setUserSale(User userSale) {
        this.userSale = userSale;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", user=" + user + ", userSale=" + userSale + ", payment=" + payment + ", status=" + status + ", email=" + email + ", fullName=" + fullName + ", address=" + address + ", note=" + note + ", phoneNumber=" + phoneNumber + ", totalMoney=" + totalMoney + ", orderDate=" + orderDate + ", discount=" + discount + ", deleted=" + deleted + '}';
    }

    public Orders(int orderId, User user, User userSale, Payment payment, int status, String email, String fullName, String address, String note, String phoneNumber, float totalMoney, Date orderDate, int discount, int deleted, String shippingStatus) {
        this.orderId = orderId;
        this.user = user;
        this.userSale = userSale;
        this.payment = payment;
        this.status = status;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.note = note;
        this.phoneNumber = phoneNumber;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.discount = discount;
        this.deleted = deleted;
        this.shippingStatus = shippingStatus;
    }
    
    
}

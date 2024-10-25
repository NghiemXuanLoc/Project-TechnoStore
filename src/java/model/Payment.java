/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Payment {
//    [Payment_id] [int] IDENTITY(1,1) NOT NULL,
//	[Payment_name] [nvarchar](100) NOT NULL,
//	[deleted] [bit] NOT NULL,
    
    
    private int paymentId;
    private String paymentName;
    private int deleted;

    public Payment() {
    }

    public Payment(int paymentId, String paymentName, int deleted) {
        this.paymentId = paymentId;
        this.paymentName = paymentName;
        this.deleted = deleted;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", paymentName=" + paymentName + ", deleted=" + deleted + '}';
    }
}

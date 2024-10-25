/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author BUI TUAN DAT
 */
public class ChartItem {

    private int totalOrders;
    private int successOrders;
    private float successRatio;
    private Date orderDate;
    private int cancelledCount; // New attribute
    private int completedCount; // New attribute
    private int unpaidCount;    // New attribute
    private float DailyRevenue;
    public ChartItem() {
    }

    public float getDailyRevenue() {
        return DailyRevenue;
    }

    public void setDailyRevenue(float DailyRevenue) {
        this.DailyRevenue = DailyRevenue;
    }
    
    public int getCancelledCount() {
        return cancelledCount;
    }

    public void setCancelledCount(int cancelledCount) {
        this.cancelledCount = cancelledCount;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    public int getUnpaidCount() {
        return unpaidCount;
    }

    public void setUnpaidCount(int unpaidCount) {
        this.unpaidCount = unpaidCount;
    }

    public ChartItem(int totalOrders, int successOrders, float successRatio, Date orderDate, int cancelledCount, int completedCount, int unpaidCount) {
        this.totalOrders = totalOrders;
        this.successOrders = successOrders;
        this.successRatio = successRatio;
        this.orderDate = orderDate;
        this.cancelledCount = cancelledCount;
        this.completedCount = completedCount;
        this.unpaidCount = unpaidCount;
    }
    
    public ChartItem(int totalOrders, int successOrders, float successRatio, Date orderDate) {
        this.totalOrders = totalOrders;
        this.successOrders = successOrders;
        this.successRatio = successRatio;
        this.orderDate = orderDate;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getSuccessOrders() {
        return successOrders;
    }

    public void setSuccessOrders(int successOrders) {
        this.successOrders = successOrders;
    }

    public float getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(float successRatio) {
        this.successRatio = successRatio;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "ChartItem{" + "totalOrders=" + totalOrders + ", successOrders=" + successOrders + ", successRatio=" + successRatio + ", orderDate=" + orderDate + ", cancelledCount=" + cancelledCount + ", completedCount=" + completedCount + ", unpaidCount=" + unpaidCount + '}';
    }

    

}

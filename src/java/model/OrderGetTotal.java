/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class OrderGetTotal {
    private BigDecimal totalMoney;
    private Date orderDate;
    private int deleted;

    public OrderGetTotal() {
    }

    public OrderGetTotal(BigDecimal totalMoney, Date orderDate, int deleted) {
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.deleted = deleted;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    
    
}

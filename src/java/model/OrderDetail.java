/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class OrderDetail {
//    [Product_detail_id] [int] NOT NULL,
//	[Order_id] [int] NOT NULL,
//	[Price] [int] NOT NULL,
//	[Quantity] [int] NOT NULL,
//	[deleted] [bit] NOT NULL,
    
    private ProductDetail productDetail;
    private Orders orders;
    private float price;
    private int quantity;
    private int deleted;

    public OrderDetail() {
    }

    public OrderDetail(ProductDetail productDetail, Orders orders, float price, int quantity, int deleted) {
        this.productDetail = productDetail;
        this.orders = orders;
        this.price = price;
        this.quantity = quantity;
        this.deleted = deleted;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "productDetail=" + productDetail + ", orders=" + orders + ", price=" + price + ", quantity=" + quantity + ", deleted=" + deleted + '}';
    }
}

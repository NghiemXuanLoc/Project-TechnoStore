/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class CartAccount {
//    [Cart_id] [int] IDENTITY(1,1) NOT NULL,
//	[User_id] [int] NULL,
//	[Product_Detail_id] [int] NULL,
//	[Quantity] [int] NULL,
    
    private int cartId;
    private User user;
    private ProductDetail productDetail;
    private int quantity;

    public CartAccount() {
    }

    public CartAccount(int cartId, User user, ProductDetail productDetail, int quantity) {
        this.cartId = cartId;
        this.user = user;
        this.productDetail = productDetail;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartAccount{" + "cartId=" + cartId + ", user=" + user + ", productDetail=" + productDetail + ", quantity=" + quantity + '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class ProductDetail {
//      [Product_detail_id] [int] IDENTITY(1,1) NOT NULL,
//	[Product_id] [int] NOT NULL,
//	[Color_id] [int] NOT NULL,
//	[memory_id] [int] NOT NULL,
//	[Quantity] [int] NOT NULL,
//	[Price_sale] [float] NOT NULL,
//	[Price_origin] [float] NOT NULL,
//	[deleted] [bit] NOT NULL,

    private int productDetailId;
    private Product product;
    private Color color;
    private Memory memory;
    private int quantity;
    private float priceSale;
    private float priceOrigin;
    private int deleted;

    public ProductDetail() {
    }

    public ProductDetail(int productDetailId, Product product, Color color, Memory memory, int quantity, float priceSale, float priceOrigin, int deleted) {
        this.productDetailId = productDetailId;
        this.product = product;
        this.color = color;
        this.memory = memory;
        this.quantity = quantity;
        this.priceSale = priceSale;
        this.priceOrigin = priceOrigin;
        this.deleted = deleted;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(float priceSale) {
        this.priceSale = priceSale;
    }

    public float getPriceOrigin() {
        return priceOrigin;
    }

    public void setPriceOrigin(float priceOrigin) {
        this.priceOrigin = priceOrigin;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "productDetailId=" + productDetailId + ", color=" + color + ", memory=" + memory + ", quantity=" + quantity + ", priceSale=" + priceSale + ", priceOrigin=" + priceOrigin + ", deleted=" + deleted + '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Gallery {
//    [Gallery_id] [int] IDENTITY(1,1) NOT NULL,
//	[Product_id] [int] NOT NULL,
//	[Thumbnail] [nvarchar](max) NOT NULL,
    
    private int galleryId;
    private Product product;
    private String thumbnail;

    public Gallery() {
    }

    public Gallery(int galleryId, Product product, String thumbnail) {
        this.galleryId = galleryId;
        this.product = product;
        this.thumbnail = thumbnail;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Gallery{" + "galleryId=" + galleryId + ", product=" + product + ", thumbnail=" + thumbnail + '}';
    }
    
}

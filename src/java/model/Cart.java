/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author PC
 */
public class Cart {

    private List<ProductDetail> listProductDetails;
    private List<Integer> soLuong;
    private double totalPriceBeforeDiscount;
    private double totalPriceAfterDiscount;

    public Cart() {
    }

    public Cart(List<ProductDetail> listProductDetails, List<Integer> soLuong) {
        this.listProductDetails = listProductDetails;
        this.soLuong = soLuong;
    }

    public Cart(List<ProductDetail> listProductDetails, List<Integer> soLuong, double totalPriceBeforeDiscount, double totalPriceAfterDiscount) {
        this.listProductDetails = listProductDetails;
        this.soLuong = soLuong;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
    }

    public List<ProductDetail> getListProductDetails() {
        return listProductDetails;
    }

    public void setListProductDetails(List<ProductDetail> listProductDetails) {
        this.listProductDetails = listProductDetails;
    }

    public List<Integer> getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(List<Integer> soLuong) {
        this.soLuong = soLuong;
    }

    public double getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public void setTotalPriceBeforeDiscount() {
        double total = 0;
        for(int i = 0;i < listProductDetails.size();i++){
            total += listProductDetails.get(i).getPriceOrigin() * soLuong.get(i);
        }
        this.totalPriceBeforeDiscount = total;

    }

    public double getTotalPriceAfterDiscount() {
        return totalPriceAfterDiscount;
    }

    public void setTotalPriceAfterDiscount() {
        double total = 0;
        for(int i = 0;i < listProductDetails.size();i++){
            total += listProductDetails.get(i).getPriceSale() * soLuong.get(i);
        }
        this.totalPriceAfterDiscount = total;

    }

    @Override
    public String toString() {
        return "Cart{" + "listProductDetails=" + listProductDetails + ", soLuong=" + soLuong + ", totalPriceBeforeDiscount=" + totalPriceBeforeDiscount + ", totalPriceAfterDiscount=" + totalPriceAfterDiscount + '}';
    }
}

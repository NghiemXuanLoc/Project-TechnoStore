/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Color;
import model.Memory;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author PC
 */
public class ProductDetailDAO extends DBContext {

    public ProductDetail getProductDetailById(int productDetailId) {
        ProductDAO pd = new ProductDAO();
        ColorDAO cd = new ColorDAO();
        MemoryDAO md = new MemoryDAO();
        String sql = "select * from Product_detail as pd\n"
                + "where pd.deleted = ? and pd.Product_detail_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, productDetailId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product product = pd.getProductById(rs.getInt("Product_id"));
                Color color = cd.getColorById(rs.getInt("Color_id"));
                Memory memory = md.getMemoryById(rs.getInt("memory_id"));
                int quantity = rs.getInt("Quantity");
                float priceSale = rs.getFloat("Price_sale");
                float priceOrigin = rs.getFloat("Price_origin");
                int deleted = rs.getInt("deleted");

                ProductDetail productDetail = new ProductDetail(productDetailId, product, color, memory, quantity, priceSale, priceOrigin, deleted);

                return productDetail;
            }
        } catch (SQLException e) {
            System.out.println("loi get product detail by Id: " + e);
        }
        return null;
    }

    public int getQuantityStockByProductDetailId(int productId, int colorId, int memoryId) {
        String sql = "select pd.Quantity from Product as p\n"
                + "left join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "where p.Deleted = ? and p.Product_id = ? and pd.Color_id = ? and pd.memory_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, productId);
            ps.setInt(3, colorId);
            ps.setInt(4, memoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Quantity");
            }
        } catch (SQLException e) {
            System.out.println("loi get quantity stock by product detail Id: " + e);
        }
        return 0;
    }

    public int getQuantitySoldByProductDetailId(int productId, int colorId, int memoryId) {
        String sql = "select \n"
                + "case when SUM(od.Quantity) is null then 0\n"
                + "else SUM(od.Quantity)\n"
                + "end\n"
                + "as Quantity_sold from Product as p\n"
                + "left join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "left join Order_detail as od on pd.Product_detail_id = od.Product_detail_id\n"
                + "left join Orders as o on od.Order_id = o.Order_id\n"
                + "where p.Deleted = ? and o.Status = ? and p.Product_id = ? and pd.Color_id = ? and pd.memory_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, 2);
            ps.setInt(3, productId);
            ps.setInt(4, colorId);
            ps.setInt(5, memoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Quantity_sold");
            }
        } catch (SQLException e) {
            System.out.println("loi get quantity sold by product detail Id: " + e);
        }
        return 0;
    }

    public ProductDetail getProductDetailByProductIdColorIdMemoryId(int productId, int colorId, int memoryId) {
        ProductDAO pd = new ProductDAO();
        ColorDAO cd = new ColorDAO();
        MemoryDAO md = new MemoryDAO();

        String sql = "select * from Product_detail as pd\n"
                + "where pd.deleted = ? and pd.Product_id = ? and pd.Color_id = ? and pd.memory_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, productId);
            ps.setInt(3, colorId);
            ps.setInt(4, memoryId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int productDetailId = rs.getInt("Product_detail_id");
                Product product = pd.getProductById(rs.getInt("Product_id"));
                Color color = cd.getColorById(rs.getInt("Color_id"));
                Memory memory = md.getMemoryById(rs.getInt("memory_id"));
                int quantity = rs.getInt("Quantity");
                float priceSale = rs.getFloat("Price_sale");
                float priceOrigin = rs.getFloat("Price_origin");
                int deleted = rs.getInt("deleted");

                ProductDetail productDetail = new ProductDetail(productDetailId, product, color, memory, quantity, priceSale, priceOrigin, deleted);

                return productDetail;
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        ProductDetailDAO pdd = new ProductDetailDAO();

        System.out.println(pdd.getProductDetailById(1));
    }

    public int getQuantityStockByProductDetailId(int productDetailId) {
        String sql = "select Quantity from Product_detail\n"
                + "where Product_detail_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productDetailId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Quantity");
            }
        } catch (SQLException e) {
            System.out.println("loi get quantity stock by product detail Id: " + e);
        }
        return 0;
    }
}

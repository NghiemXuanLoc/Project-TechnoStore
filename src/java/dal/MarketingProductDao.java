/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.*;
import model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 *
 * @author quang
 */
public class MarketingProductDao extends DBContext {

    public List<Product> getAllProducts() {
        List<Product> lProduct = new ArrayList<>();
        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();
        GalleryDAO gd = new GalleryDAO();

        String sql = "select * from Product p inner join Product_detail pd on p.Product_id = pd.Product_detail_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {  // Use while loop instead of if
                int productId = rs.getInt("Product_id");
                Category category = cd.getCategoryById(rs.getInt("Category_id"));
                Supplier supplier = sd.getSupplierById(rs.getInt("Supplier_id"));
                String productName = rs.getString("Product_name");
                float avgRating = rs.getFloat("Avg_rating");
                String briefInfo = rs.getString("Brief_info");
                String description = rs.getString("Description");
                Date createAt = rs.getDate("Create_at");
                Date updateAt = rs.getDate("Update_at");
                int deleted = rs.getInt("Deleted");

                double minPrice = rs.getFloat("Price_sale");
                double maxPrice = rs.getFloat("Price_origin");

                List<String> gallery = gd.getGalleryByProductId(productId);

                Product p = new Product(productId, category, supplier, productName, avgRating, briefInfo, description, createAt, updateAt, deleted, gallery, minPrice, maxPrice);
                lProduct.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("loi get product by Id: " + e);
        }
        return lProduct;
    }

    public List<Product> pagingBlog(List<Product> listBlog, int iPre, int postsPerPage) {
        List<Product> pagedListBlog = new ArrayList<>();
        int start = (iPre - 1) * postsPerPage;
        int end = Math.min(start + postsPerPage, listBlog.size());
        if (start < 0 || start >= listBlog.size()) {
            return pagedListBlog;
        }
        for (int i = start; i < end; i++) {
            pagedListBlog.add(listBlog.get(i));
        }
        return pagedListBlog;
    }

    public Product getProductById(int id) {
        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();
        GalleryDAO gd = new GalleryDAO();

        String sql = "with tb1 as (\n"
                + "select p.Product_id as id, MIN(pd.Price_sale) as min_price, MAX(pd.Price_sale) as max_price, MIN(pd.Price_origin) as min_price_origin, MAX(pd.Price_origin) as max_price_origin from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "group by p.Product_id\n"
                + ")\n"
                + "\n"
                + "select * from Product as p\n"
                + "inner join tb1 on p.Product_id = tb1.id\n"
                + "where p.Product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("Product_id");
                Category category = cd.getCategoryById(rs.getInt("Category_id"));
                Supplier supplier = sd.getSupplierById(rs.getInt("Supplier_id"));
                String productName = rs.getString("Product_name");
                float avgRating = rs.getFloat("Avg_rating");
                String briefInfo = rs.getString("Brief_info");
                String description = rs.getString("Description");
                Date createAt = rs.getDate("Create_at");
                Date updateAt = rs.getDate("Update_at");
                int deleted = rs.getInt("Deleted");

                double minPrice = rs.getFloat("min_price");
                double maxPrice = rs.getFloat("max_price");

                double minPriceOrigin = rs.getFloat("min_price_origin");
                double maxPriceOrigin = rs.getFloat("max_price_origin");

                List<String> gallery = gd.getGalleryByProductId(productId);

                Product p = new Product(productId, category, supplier, productName, avgRating, briefInfo, description, createAt, updateAt, deleted, gallery, minPrice, maxPrice, minPriceOrigin, maxPriceOrigin);

                return p;
            }
        } catch (SQLException e) {
            System.out.println("loi get product by Id: " + e);
        }
        return null;
    }

    public List<Product> filterByCategory(List<Product> list, int categoryID) {
        List<Product> listC = new ArrayList<>();
        for (Product p : list) {
            if (p.getCategory().getCategoryId() == categoryID) {
                listC.add(p);
            }
        }
        return listC;
    }

    public void updateStatus(int productID) {
        String sql = "SELECT deleted FROM Product WHERE Product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean isDeleted = rs.getInt("deleted") == 1;
                // Toggle the status
                isDeleted = !isDeleted;
                // Update the status
                sql = "UPDATE Product SET deleted = ? WHERE Product_id = ?";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, isDeleted ? 1 : 0);
                ps.setInt(2, productID);
                ps.executeUpdate();
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> filterByActiveInList(List<Product> list, int active) {
        List<Product> result = new ArrayList<>();
        for (Product pDetail : list) {
            if (pDetail.getDeleted() == active) {
                result.add(pDetail);
            }
        }
        return result;
    }

    private String sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        input = input.trim();
        return input.replaceAll("\\s+", " ");
    }

    public List<Product> searchProduct(List<Product> list, String title) {
        List<Product> result = new ArrayList<>();
        title = sanitizeInput(title);
        for (Product pdetail : list) {
            if (pdetail.getProductName().toLowerCase().contains(title.toLowerCase())) {
                result.add(pdetail);
            }
        }
        return result;
    }
    
    public ProductDetail getProductDetailById(int productDetailId) {
        ProductDAO pd = new ProductDAO();
        ColorDAO cd = new ColorDAO();
        MemoryDAO md = new MemoryDAO();
        String sql = "select * from Product_detail as pd\n"
                + "where pd.Product_detail_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productDetailId);
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

    public static void main(String[] args) {
        MarketingProductDao md = new MarketingProductDao();
        ProductDetail p = md.getProductDetailById(1);
        System.out.println(p);
   
    }
}

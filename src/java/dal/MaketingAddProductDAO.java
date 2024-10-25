/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import model.*;

/**
 *
 * @author quang
 */
public class MaketingAddProductDAO extends DBContext {

    public List<Supplier> getSupplierAll() {
        List<Supplier> list = new ArrayList<>();
        String sql = "select * from Supplier\n"
                + "where Deleted = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int supplierId = rs.getInt("Supplier_id");
                String companyName = rs.getString("Company_name");
                String phoneNumber = rs.getString("Phone_number");
                String country = rs.getString("Country");
                int deleted = rs.getInt("Deleted");

                Supplier s = new Supplier(supplierId, companyName, phoneNumber, country, deleted);
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    /*Check exist Product*/
    public boolean isProductDetailExists(String productName, int colorId, int memoryId) {
        String sql = "SELECT COUNT(*) FROM Product_detail pd "
                + "JOIN Product p ON pd.Product_id = p.Product_id "
                + "WHERE p.Product_name = ? AND pd.Color_id = ? AND pd.memory_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, productName.toLowerCase());
            ps.setInt(2, colorId);
            ps.setInt(3, memoryId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking product detail existence: " + e);
        }
        return false;
    }

    /*Check product name is exists*/
    private boolean productExists(String productName) {
        String sql = "SELECT COUNT(*) FROM Product WHERE Product_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*Check exist Supplier*/
    public boolean isSupplierExists(String supplierName) {
        boolean exists = false;
        String sql = "SELECT * FROM Supplier WHERE Company_name =?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, supplierName);
            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    /*Check exist category*/
    public boolean isCategoryExists(String categoryName) {
        boolean exists = false;
        String sql = "SELECT * FROM Category WHERE Category_name =?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoryName);
            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    /*Add new Product Category*/
    public int addCategory(String categoryName) {
        int categoryId = 0;
        String sql = "INSERT INTO Category (Category_name, Short_description, Description, Deleted) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, categoryName);
            ps.setNull(2, java.sql.Types.VARCHAR); // Set short description to null
            ps.setNull(3, java.sql.Types.VARCHAR); // Set description to null
            ps.setBoolean(4, false); // Set deleted to 0 (not deleted)
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    categoryId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

    /*Add new Product Supplier*/
    public int addSupplier(String supplierName) {
        int supplierId = 0;
        String sql = "INSERT INTO Supplier (Company_name, Phone_number, Country, Deleted) VALUES  (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, supplierName);
            ps.setNull(2, java.sql.Types.VARCHAR); // Set short description to null
            ps.setNull(3, java.sql.Types.VARCHAR); // Set description to null
            ps.setBoolean(4, false); // Set deleted to 0 (not deleted)
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    supplierId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierId;
    }

    /*Excute add new product, add data into Gallery, productetail*/
    public void addNewProduct(int categoryId, int supplierId, String productName, String brief, String descript, Date dateCreated, int color, int memory, int quantity, float originPrice, float salePrice, String thumbnail) {
        if (productExists(productName)) {
            int productId = getProductId(productName);
            addProductDetail(productId, color, memory, quantity, originPrice, salePrice);
            addGalleryImage(productId, thumbnail);
        } else {
            int productId = addNewProduct(categoryId, supplierId, productName, brief, descript, dateCreated);
            addProductDetail(productId, color, memory, quantity, originPrice, salePrice);
            addGalleryImage(productId, thumbnail);
        }
    }

    private int getProductId(String productName) {
        String sql = "SELECT Product_id FROM Product WHERE Product_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Product_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*Excute add new Product*/
    private int addNewProduct(int categoryId, int supplierId, String productName, String brief, String descript, Date dateCreated) {
        String sql = "INSERT INTO Product (Category_id, Supplier_id, Product_name, Avg_rating, Brief_info, Description, Create_at, Update_at, Deleted) VALUES (?,?,?,?,?,?,getdate(),?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, supplierId);
            ps.setString(3, productName);
            ps.setFloat(4, 0);
            ps.setString(5, brief);
            ps.setString(6, descript);
//            ps.setDate(7, (java.sql.Date) dateCreated);
            ps.setDate(7, null);
            ps.setBoolean(8, false);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*Add data into table product detail with the same product id*/
    private void addProductDetail(int productId, int color, int memory, int quantity, float originPrice, float salePrice) {
        String sqlCheckProductDetail = "SELECT 1 FROM Product_detail WHERE Product_id = ? AND Color_id = ? AND memory_id = ?";
        try (PreparedStatement psCheckProductDetail = connection.prepareStatement(sqlCheckProductDetail)) {
            psCheckProductDetail.setInt(1, productId);
            psCheckProductDetail.setInt(2, color);
            psCheckProductDetail.setInt(3, memory);
            ResultSet rsCheckProductDetail = psCheckProductDetail.executeQuery();
            if (rsCheckProductDetail.next()) {
                // Record already exists, update the existing record
                String sqlUpdateProductDetail = "UPDATE Product_detail SET Quantity = ?, Price_sale = ?, Price_origin = ? WHERE Product_id = ? AND Color_id = ? AND memory_id = ?";
                try (PreparedStatement psUpdateProductDetail = connection.prepareStatement(sqlUpdateProductDetail)) {
                    psUpdateProductDetail.setInt(1, quantity);
                    psUpdateProductDetail.setFloat(2, salePrice);
                    psUpdateProductDetail.setFloat(3, originPrice);
                    psUpdateProductDetail.setInt(4, productId);
                    psUpdateProductDetail.setInt(5, color);
                    psUpdateProductDetail.setInt(6, memory);
                    psUpdateProductDetail.executeUpdate();
                }
            } else {
                // Record does not exist, insert a new one
                String sqlAddProductDetail = "INSERT INTO Product_detail (Product_id, Color_id, memory_id, Quantity, Price_sale, Price_origin, deleted) VALUES (?,?,?,?,?,?,?)";
                try (PreparedStatement psAddProductDetail = connection.prepareStatement(sqlAddProductDetail)) {
                    psAddProductDetail.setInt(1, productId);
                    psAddProductDetail.setInt(2, color);
                    psAddProductDetail.setInt(3, memory);
                    psAddProductDetail.setInt(4, quantity);
                    psAddProductDetail.setFloat(5, salePrice);
                    psAddProductDetail.setFloat(6, originPrice);
                    psAddProductDetail.setBoolean(7, false);
                    psAddProductDetail.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Add image into table Gallery*/
    private void addGalleryImage(int productId, String thumbnail) {
        String sql = "INSERT INTO Gallery (Product_id, Thumbnail) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.setString(2, thumbnail);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductDetail getProductDetailByProductIdColorIdMemoryId(int productId, int colorId, int memoryId) {
        ProductDAO pd = new ProductDAO();
        ColorDAO cd = new ColorDAO();
        MemoryDAO md = new MemoryDAO();
        String sql = "select * from Product_detail as pd\n"
                + "where pd.Product_id = ? and pd.Color_id = ? and pd.memory_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, colorId);
            ps.setInt(3, memoryId);

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

    public List<Category> getCategoryByProduct(int productID) {
        List<Category> list = new ArrayList<>();
        String sql = "select*\n"
                + "from Category c \n"
                + "inner join Product p on c.Category_id = p.Category_id\n"
                + "where c.Deleted  =? and p.Product_id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("Category_id");
                String categoryName = rs.getString("Category_name");
                String shortDescription = rs.getString("Short_description");
                String description = rs.getString("Description");
                int deleted = rs.getInt("Deleted");

                Category c = new Category(categoryId, categoryName, shortDescription, description, deleted);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public List<Supplier> getSupplierByProduct(int productID) {
        List<Supplier> list = new ArrayList<>();
        String sql = """
                 select s.Supplier_id, s.Company_name, s.Phone_number, s.Country, s.Deleted
                 from Supplier s
                 inner join Product p on p.Supplier_id = s.Supplier_id
                 where p.Product_id = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int supplierId = rs.getInt("Supplier_id");
                String companyName = rs.getString("Company_name");
                String phoneNumber = rs.getString("Phone_number");
                String country = rs.getString("Country");
                int deleted = rs.getInt("Deleted");

                Supplier s = new Supplier(supplierId, companyName, phoneNumber, country, deleted);
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error getting suppliers by product: " + e);
        }
        return list;
    }

    public void updateStatusProductDetail(int productID) {
        String sql = "SELECT deleted FROM Product_detail WHERE Product_detail_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean isDeleted = rs.getInt("deleted") == 1;
                // Toggle the status
                isDeleted = !isDeleted;
                // Update the status
                sql = "UPDATE Product_detail SET deleted = ? WHERE Product_detail_id = ?";
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

    public static void main(String[] args) {
        MaketingAddProductDAO pdao = new MaketingAddProductDAO();
        List<Supplier> lists = pdao.getSupplierByProduct(2);
        List<Category> listC = pdao.getCategoryByProduct(2);
        for (Category category : listC) {
            System.out.println(category);
        }
        for (Supplier list : lists) {
            System.out.println(list);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Product;
import model.Supplier;

/**
 *
 * @author PC
 */
public class ProductDAO extends DBContext {

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
                + "where p.Deleted = ? and p.Product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, id);
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

    public List<Product> getTop3BestSellingProductsByCategoryId(int categoryId) {
        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();

        Category category = cd.getCategoryById(categoryId);
        GalleryDAO gd = new GalleryDAO();

        List<Product> list = new ArrayList<>();
        String sql = "with order_success as (\n"
                + "\n"
                + "select od.Product_detail_id, od.Quantity from Orders as o\n"
                + "inner join Order_detail as od on o.Order_id = od.Order_id\n"
                + "where o.Status = 2\n"
                + "\n"
                + "), product_quantity_sold as (\n"
                + "\n"
                + "select pd.Product_id, \n"
                + "case when SUM(os.Quantity) is null then 0\n"
                + "else SUM(os.Quantity)\n"
                + "end as Quantity_sold\n"
                + "from Product_detail as pd\n"
                + "left join order_success as os on pd.Product_detail_id = os.Product_detail_id\n"
                + "group by pd.Product_id\n"
                + "\n"
                + ")\n"
                + "\n"
                + "select top 3 * from Product as p\n"
                + "inner join product_quantity_sold as pqs on p.Product_id = pqs.Product_id\n"
                + "where p.Category_id = ? and p.Deleted = ?\n"
                + "order by pqs.Quantity_sold desc, p.Product_name, p.Product_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("Product_id");

                Supplier supplier = sd.getSupplierById(rs.getInt("Supplier_id"));
                String productName = rs.getString("Product_name");
                float avgRating = rs.getFloat("Avg_rating");
                String briefInfo = rs.getString("Brief_info");
                String description = rs.getString("Description");
                Date createAt = rs.getDate("Create_at");
                Date updateAt = rs.getDate("Update_at");
                int deleted = rs.getInt("Deleted");

                List<String> gallery = gd.getGalleryByProductId(productId);

                Product p = new Product(productId, category, supplier, productName, avgRating, briefInfo, description, createAt, updateAt, deleted, gallery);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public Map<Category, List<Product>> getTop3ProductBestSellingByCategoryAll() {
        Map<Category, List<Product>> map = new LinkedHashMap<>();

        CategoryDAO cd = new CategoryDAO();
        GalleryDAO gd = new GalleryDAO();

        List<Category> listCategory = cd.getCategoryAll();

        for (Category c : listCategory) {
            List<Product> listTop3Product = getTop3BestSellingProductsByCategoryId(c.getCategoryId());
            map.put(c, listTop3Product);
        }
        return map;
    }

    public List<Product> getTop3ProductBestSelling() {
        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();
        GalleryDAO gd = new GalleryDAO();

        List<Product> list = new ArrayList<>();
        String sql = "with order_success as (\n"
                + "select od.Product_detail_id, od.Quantity from Orders as o\n"
                + "inner join Order_detail as od on o.Order_id = od.Order_id\n"
                + "where o.Order_date >= GETDATE() - 30 and o.Order_date <= GETDATE() and o.Status = 2\n"
                + "), product_quantity_sold as (\n"
                + "select pd.Product_id,\n"
                + "case when SUM(os.Quantity) is null then 0\n"
                + "else SUM(os.Quantity)\n"
                + "end as Quantity_sold\n"
                + "from Product_detail as pd\n"
                + "left join order_success as os on pd.Product_detail_id = os.Product_detail_id\n"
                + "group by pd.Product_id\n"
                + ")\n"
                + "select top 3 * from Product as p\n"
                + "inner join product_quantity_sold as pqs on p.Product_id = pqs.Product_id\n"
                + "where p.Deleted = ?\n"
                + "order by pqs.Quantity_sold desc, p.Product_name, p.Product_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("Product_id");
                Product p = getProductById(productId);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public List<Product> getTop3ProductHotSale() {
        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();
        GalleryDAO gd = new GalleryDAO();

        List<Product> list = new ArrayList<>();
        String sql = "with top_product_hot_sale as\n"
                + "(\n"
                + "\n"
                + "select p.Product_id, ROUND(MAX((1 - (pd.Price_sale / pd.Price_origin)) * 100), 0) as sale from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "group by p.Product_id\n"
                + "\n"
                + ")\n"
                + "\n"
                + "select top 3 * from top_product_hot_sale as tphs\n"
                + "inner join Product as p on tphs.Product_id = p.Product_id\n"
                + "where p.Deleted = ?\n"
                + "order by tphs.sale desc, p.Product_name";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("Product_id");
                Product p = getProductById(productId);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi get top 3 product hot sale: " + e);
        }
        return list;
    }

    public List<Product> getTop3ProductRating() {
        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();
        GalleryDAO gd = new GalleryDAO();

        List<Product> list = new ArrayList<>();
        String sql = "select top 3 * from Product as p\n"
                + "where p.Deleted = ?\n"
                + "order by p.Avg_rating desc, p.Product_name, p.Product_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("Product_id");
                Product p = getProductById(productId);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi top 3 product rating: " + e);
        }
        return list;
    }

    public List<Product> getTop9NewProductByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "select top 9 * from Product\n"
                + "where Category_id = ? and Deleted = ?\n"
                + "order by Create_at desc, Product_name, Product_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("Product_id");
                Product p = getProductById(productId);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi top 9 new product by categoryId: " + e);
        }
        return list;
    }

    public Map<Category, List<Product>> getTop9NewProductByCategoryAll() {
        CategoryDAO cd = new CategoryDAO();

        Map<Category, List<Product>> map = new LinkedHashMap<>();

        for (Category c : cd.getCategoryAll()) {
            List<Product> list = getTop9NewProductByCategoryId(c.getCategoryId());
            map.put(c, list);
        }
        return map;
    }

    public List<Product> getTop3LastProductByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "select top 3 * from Product as p\n"
                + "where p.Deleted = ? and p.Category_id = ?\n"
                + "order by p.Create_at desc, p.Product_name, p.Product_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Product p = getProductById(productId);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi get top 3 last product: " + e);
        }
        return list;
    }

    public List<Product> getTop9RecentProduct(int categoryId, int productId) {
        List<Product> list = new ArrayList<>();
        String sql = "select top 9 * from Product as p\n"
                + "where p.Deleted = ? and p.Category_id = ? and p.Product_id != ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, categoryId);
            ps.setInt(3, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pId = rs.getInt("Product_id");
                list.add(getProductById(pId));
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public int getQuantityStockByProductId(int productId) {
        String sql = "select SUM(pd.Quantity) as Quantity_Stock from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "where p.Deleted = ? and p.Product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Quantity_Stock");
            }
        } catch (SQLException e) {
            System.out.println("loi get quantity stock by product Id: " + e);
        }
        return 0;
    }

    public int getQuantitySoldByProductId(int productId) {
        String sql = "select case when SUM(od.Quantity) is null then 0\n"
                + "else SUM(od.Quantity)\n"
                + "end\n"
                + "as Quantity_sold from Product as p\n"
                + "left join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "left join Order_detail as od on pd.Product_detail_id = od.Product_detail_id\n"
                + "left join Orders as o on od.Order_id = o.Order_id\n"
                + "where p.Deleted = ? and p.Product_id = ? and o.Status = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, productId);
            ps.setInt(3, 2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Quantity_sold");
            }
        } catch (SQLException e) {
            System.out.println("loi get quantity sold by product Id: " + e);
        }
        return 0;
    }

    public static void main(String[] args) {
        ProductDAO pd = new ProductDAO();
        List<Integer> categorys = new ArrayList<>();
        List<String> operating = new ArrayList<>();

        List<Integer> colors = new ArrayList<>();

        List<Integer> memorys = new ArrayList<>();

        List<String> screen = new ArrayList<>();

        List<Integer> rams = new ArrayList<>();

//        for (Product p : pd.getListProductByFilter("", categorys, 5000000, 40000000, operating, screen, rams, colors, memorys, 0, 1, 5)) {
//            System.out.println(p);
//        }
        for (float x : pd.getMinAndMaxPriceByProductAll()) {
            System.out.println(x);
        }

////        System.out.println(pd.getProductById(1));
//        for (Map.Entry<Category, List<Product>> entry : pd.getTop9NewProductByCategoryAll().entrySet()) {
//            System.out.println(entry.getKey());
//            for (Product p : entry.getValue()) {
//                System.out.println(p);
//            }
//            System.out.println("--------------------------------------");
//        }
//        System.out.println(pd.getProductById(1));
    }

    public List<Product> getTop5SearchProduct() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Product> getSearchProduct(String textSearch) {

        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();
        GalleryDAO gd = new GalleryDAO();

        List<Product> list = new ArrayList<>();
        String sql = "with price_product as (\n"
                + "select pd.Product_id, MIN(pd.Price_sale) as min_price, MAX(pd.Price_sale) as max_price, MIN(pd.Price_origin) as min_price_origin, MAX(pd.Price_origin) as max_price_origin from Product_detail as pd\n"
                + "group by pd.Product_id\n"
                + ")\n"
                + "\n"
                + "select * from (select * from Product as p\n"
                + "where p.Deleted = ? and (p.Product_name like ? or p.Description like ?)) as result\n"
                + "inner join price_product as pp on result.Product_id = pp.Product_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setString(2, "%" + textSearch + "%");
            ps.setString(3, "%" + textSearch + "%");
            ResultSet rs = ps.executeQuery();
            if (!textSearch.equalsIgnoreCase("")) {
                while (rs.next()) {
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
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public int getRandomInt() {
        return 1;
    }

    public int getSumQuantityStock() {
        return 1;
    }

    public List<Product> getTop2LastProductByCategoryAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 2 * from Product as p\n"
                + "where p.Deleted = ?\n"
                + "order by p.Create_at desc, p.Product_name, p.Product_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Product p = getProductById(productId);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi get top 3 last product: " + e);
        }
        return list;
    }

    public List<Product> getListProductByFilter(String searchBox, List<Integer> categorys, double minValue, double maxValue, List<String> operatingSystem, List<String> screen, List<Integer> rams, List<Integer> colors, List<Integer> memorys, int sortByProduct, int pageNumber, int rowPerPage) {
        List<Product> list = new ArrayList<>();

        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();
        GalleryDAO gd = new GalleryDAO();

        StringBuilder sql = new StringBuilder("select p.Product_id, p.Category_id, p.Supplier_id, p.Product_name, p.Avg_rating, p.Brief_info, p.Description, p.Create_at,\n"
                + "p.Update_at, p.Deleted, table_quantity_sold.quantity_sold,\n"
                + "MIN(pd.Price_sale) as min_price, MAX(pd.Price_sale) as max_price, MIN(pd.Price_origin) as min_price_origin, MAX(pd.Price_origin) as max_price_origin\n"
                + "from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "left join (select pd.Product_id, SUM(od.Quantity) as quantity_sold from Orders as o\n"
                + "inner join Order_detail as od on o.Order_id = od.Order_id\n"
                + "inner join Product_detail as pd on pd.Product_detail_id = od.Product_detail_id\n"
                + "where o.Status = 2 and o.Order_date >= GETDATE() - 30\n"
                + "group by pd.Product_id) as table_quantity_sold on p.Product_id = table_quantity_sold.Product_id\n"
                + "where p.Deleted = 0 ");

        // filter theo search box
        if (searchBox != null && searchBox.length() != 0) {
            sql.append("\nand (p.Product_name like '%" + searchBox + "%' or p.Description like '%" + searchBox + "%') ");
        }

        // filter theo category
        if (categorys.size() >= 1) {
            if (categorys.get(0) != 0) {
                sql.append("\nand (");
                for (int i = 0; i < categorys.size(); i++) {
                    if (i != categorys.size() - 1) {
                        sql.append("p.Category_id = " + categorys.get(i) + " or ");
                    } else {
                        sql.append("p.Category_id = " + categorys.get(i));
                    }
                }
                sql.append(")");
            }
        }

        // filter theo price
        if (minValue != -1 && maxValue != -1) {
            sql.append("\nand (pd.Price_sale >= " + minValue + " and pd.Price_sale <= " + maxValue + ")");
        }

        // filter theo operating system
        if (operatingSystem.size() >= 1) {
            if (!operatingSystem.get(0).equalsIgnoreCase("all")) {
                sql.append("\nand (");
                for (int i = 0; i < operatingSystem.size(); i++) {
                    if (i != operatingSystem.size() - 1) {
                        sql.append("p.Description like '%" + operatingSystem.get(i) + "%' or ");
                    } else {
                        sql.append("p.Description like '%" + operatingSystem.get(i) + "%'");
                    }
                }
                sql.append(")");
            }

        }

        if (screen.size() >= 1) {
            if (!screen.get(0).equalsIgnoreCase("all")) {
                sql.append("\nand (");
                for (int i = 0; i < screen.size(); i++) {
                    if (i != screen.size() - 1) {
                        sql.append("p.Description like '%" + screen.get(i) + "%' or ");
                    } else {
                        sql.append("p.Description like '%" + screen.get(i) + "%'");
                    }
                }
                sql.append(")");
            }
        }

        if (rams.size() >= 1) {
            if (rams.get(0) != 0) {
                sql.append("\nand (");
                for (int i = 0; i < rams.size(); i++) {
                    if (i != rams.size() - 1) {
                        sql.append("p.Description like '%" + rams.get(i) + " gb%' or ");
                    } else {
                        sql.append("p.Description like '%" + rams.get(i) + " gb%'");
                    }
                }

                sql.append(")");
            }
        }

        // filter theo colors
        if (colors.size() >= 1) {
            if (colors.get(0) != 0) {
                sql.append("\nand (");
                for (int i = 0; i < colors.size(); i++) {
                    if (i != colors.size() - 1) {
                        sql.append("(pd.Color_id = " + colors.get(i) + " and pd.Quantity > 0) or ");
                    } else {
                        sql.append("(pd.Color_id = " + colors.get(i) + " and pd.Quantity > 0)");
                    }
                }
                sql.append(")");
            }
        }

        // filter theo memorys
        if (memorys.size() >= 1) {
            if (memorys.get(0) != 0) {
                sql.append("\n and (");
                for (int i = 0; i < memorys.size(); i++) {
                    if (i != memorys.size() - 1) {
                        sql.append("(pd.memory_id = " + memorys.get(i) + " and pd.Quantity > 0) or ");
                    } else {
                        sql.append("(pd.memory_id = " + memorys.get(i) + " and pd.Quantity > 0)");
                    }
                }
                sql.append(")");
            }
        }

        sql.append("\ngroup by p.Product_id, p.Category_id, p.Supplier_id, p.Product_name, p.Avg_rating, p.Brief_info, p.Description, p.Create_at,\n"
                + "p.Update_at, p.Deleted, table_quantity_sold.quantity_sold");

        // sort product
        sql.append("\norder by ");

        if (sortByProduct == 0) {
            sql.append(" p.Update_at desc ");
        } else if (sortByProduct == 1) {
            sql.append(" min_price_origin desc ");
        } else if (sortByProduct == 2) {
            sql.append(" min_price_origin ");
        } else if (sortByProduct == 3) {
            sql.append(" table_quantity_sold.quantity_sold desc  ");
        }

        sql.append(" , p.Create_at desc, p.Product_id desc");

        // phan trang va hien thi so san pham muon co tren man hinh
        sql.append("\nOFFSET (" + pageNumber + " - 1) * " + rowPerPage + " ROWS");
        sql.append("\nFETCH NEXT " + rowPerPage + " ROWS ONLY;");

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("loi get product filter all: " + e);
        }
        return list;
    }

    public int getTotalProductByFilter(String searchBox, List<Integer> categorys, double minValue, double maxValue, List<String> operatingSystem, List<String> screen, List<Integer> rams, List<Integer> colors, List<Integer> memorys, int sortByProduct, int pageNumber, int rowPerPage) {

        CategoryDAO cd = new CategoryDAO();
        SupplierDAO sd = new SupplierDAO();
        GalleryDAO gd = new GalleryDAO();

        StringBuilder sql = new StringBuilder("select p.Product_id, p.Category_id, p.Supplier_id, p.Product_name, p.Avg_rating, p.Brief_info, p.Description, p.Create_at,\n"
                + "p.Update_at, p.Deleted, table_quantity_sold.quantity_sold,\n"
                + "MIN(pd.Price_sale) as min_price, MAX(pd.Price_sale) as max_price, MIN(pd.Price_origin) as min_price_origin, MAX(pd.Price_origin) as max_price_origin\n"
                + "from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id\n"
                + "left join (select pd.Product_id, SUM(od.Quantity) as quantity_sold from Orders as o\n"
                + "inner join Order_detail as od on o.Order_id = od.Order_id\n"
                + "inner join Product_detail as pd on pd.Product_detail_id = od.Product_detail_id\n"
                + "where o.Status = 2 and o.Order_date >= GETDATE() - 30\n"
                + "group by pd.Product_id) as table_quantity_sold on p.Product_id = table_quantity_sold.Product_id\n"
                + "where p.Deleted = 0 ");

        // filter theo search box
        if (searchBox != null && searchBox.length() != 0) {
            sql.append("\nand (p.Product_name like '%" + searchBox + "%' or p.Description like '%" + searchBox + "%') ");
        }

        // filter theo category
        if (categorys.size() >= 1) {
            if (categorys.get(0) != 0) {
                sql.append("\nand (");
                for (int i = 0; i < categorys.size(); i++) {
                    if (i != categorys.size() - 1) {
                        sql.append("p.Category_id = " + categorys.get(i) + " or ");
                    } else {
                        sql.append("p.Category_id = " + categorys.get(i));
                    }
                }
                sql.append(")");
            }
        }

        // filter theo price
        if (minValue != -1 && maxValue != -1) {
            sql.append("\nand (pd.Price_sale >= " + minValue + " and pd.Price_sale <= " + maxValue + ")");
        }

        // filter theo operating system
        if (operatingSystem.size() >= 1) {
            if (!operatingSystem.get(0).equalsIgnoreCase("all")) {
                sql.append("\nand (");
                for (int i = 0; i < operatingSystem.size(); i++) {
                    if (i != operatingSystem.size() - 1) {
                        sql.append("p.Description like '%" + operatingSystem.get(i) + "%' or ");
                    } else {
                        sql.append("p.Description like '%" + operatingSystem.get(i) + "%'");
                    }
                }
                sql.append(")");
            }

        }

        if (screen.size() >= 1) {
            if (!screen.get(0).equalsIgnoreCase("all")) {
                sql.append("\nand (");
                for (int i = 0; i < screen.size(); i++) {
                    if (i != screen.size() - 1) {
                        sql.append("p.Description like '%" + screen.get(i) + "%' or ");
                    } else {
                        sql.append("p.Description like '%" + screen.get(i) + "%'");
                    }
                }
                sql.append(")");
            }
        }

        if (rams.size() >= 1) {
            if (rams.get(0) != 0) {
                sql.append("\nand (");
                for (int i = 0; i < rams.size(); i++) {
                    if (i != rams.size() - 1) {
                        sql.append("p.Description like '%" + rams.get(i) + " gb%' or ");
                    } else {
                        sql.append("p.Description like '%" + rams.get(i) + " gb%'");
                    }
                }

                sql.append(")");
            }
        }

        // filter theo colors
        if (colors.size() >= 1) {
            if (colors.get(0) != 0) {
                sql.append("\nand (");
                for (int i = 0; i < colors.size(); i++) {
                    if (i != colors.size() - 1) {
                        sql.append("(pd.Color_id = " + colors.get(i) + " and pd.Quantity > 0) or ");
                    } else {
                        sql.append("(pd.Color_id = " + colors.get(i) + " and pd.Quantity > 0)");
                    }
                }
                sql.append(")");
            }
        }

        // filter theo memorys
        if (memorys.size() >= 1) {
            if (memorys.get(0) != 0) {
                sql.append("\n and (");
                for (int i = 0; i < memorys.size(); i++) {
                    if (i != memorys.size() - 1) {
                        sql.append("(pd.memory_id = " + memorys.get(i) + " and pd.Quantity > 0) or ");
                    } else {
                        sql.append("(pd.memory_id = " + memorys.get(i) + " and pd.Quantity > 0)");
                    }
                }
                sql.append(")");
            }
        }

        sql.append("\ngroup by p.Product_id, p.Category_id, p.Supplier_id, p.Product_name, p.Avg_rating, p.Brief_info, p.Description, p.Create_at,\n"
                + "p.Update_at, p.Deleted, table_quantity_sold.quantity_sold");

        // sort product
        sql.append("\norder by ");

        if (sortByProduct == 0) {
            sql.append(" p.Update_at desc ");
        } else if (sortByProduct == 1) {
            sql.append(" min_price_origin desc ");
        } else if (sortByProduct == 2) {
            sql.append(" min_price_origin ");
        } else if (sortByProduct == 3) {
            sql.append(" table_quantity_sold.quantity_sold desc  ");
        }

        sql.append(" , p.Create_at desc, p.Product_id desc");

        int countProductByFilter = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                countProductByFilter++;
            }
        } catch (SQLException e) {
            System.out.println("loi get product filter all: " + e);
        }
        return countProductByFilter;
    }

    public List<Float> getMinAndMaxPriceByProductAll() {
        List<Float> minMaxPrice = new ArrayList<>();

        String sql = "select MIN(pd.Price_sale) as min_price, MAX(pd.Price_sale ) as max_price from Product as p\n"
                + "inner join Product_detail as pd on p.Product_id = pd.Product_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                minMaxPrice.add(rs.getFloat("min_price"));
                minMaxPrice.add(rs.getFloat("max_price"));
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return minMaxPrice;
    }

    public int getTotalProduct() {
        String sql = "select COUNT(Product_id) as total_product from Product";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total_product");
            }
        } catch (SQLException e) {
            System.out.println("loi get object by Id: " + e);
        }
        return 0;
    }
}

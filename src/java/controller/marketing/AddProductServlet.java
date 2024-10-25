/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import java.nio.file.Paths;
import java.util.*;
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author quang
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/addproduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        MaketingAddProductDAO addProductDao = new MaketingAddProductDAO();
        CategoryDAO cDao = new CategoryDAO();
        ColorDAO colDao = new ColorDAO();
        MemoryDAO memDao = new MemoryDAO();

        List<Category> listCategory = cDao.getCategoryAll();
        List<Supplier> listSupplier = addProductDao.getSupplierAll();
        List<Color> listColor = colDao.getColorAll();
        List<Memory> listMemory = memDao.getMemoryAll();

        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listSupplier", listSupplier);
        request.setAttribute("listColor", listColor);
        request.setAttribute("listMemory", listMemory);
        request.getRequestDispatcher("views/marketing/item-page/addproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Part thumbnail = request.getPart("thumbnail");
            String color = request.getParameter("colorId");
            String memory = request.getParameter("memoryId");
            String productName = request.getParameter("productname");
            String description = request.getParameter("description");
            String brief = request.getParameter("brief");
            String category = request.getParameter("category");
            String newCategory = request.getParameter("newCategory");
            String supplier = request.getParameter("supplier");
            String newSupplier = request.getParameter("newSupplier");
            String originPrice = request.getParameter("originprice");
            String salePrice = request.getParameter("saleprice");
            String quantity = request.getParameter("quantity");
//            String dateCreated = request.getParameter("datecreated");

            MaketingAddProductDAO mAddProductDao = new MaketingAddProductDAO();
            ColorDAO colDao = new ColorDAO();
            MemoryDAO memDao = new MemoryDAO();

            /*Kiểm tra xem sản phẩm với memory size, tên sản phẩm và mày đã tồn tại hay chưa*/
            boolean productExists = mAddProductDao.isProductDetailExists(productName, Integer.parseInt(color), Integer.parseInt(memory));
            String colName = colDao.getColorById(Integer.parseInt(color)).getColorName();
            int memSize = memDao.getMemoryById(Integer.parseInt(memory)).getMemorySize();

            if (productExists) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"error\": \"Sản phẩm " + productName + " với màu " + colName + " dung lượng " + memSize + " GB đã tồn tại!\"}");
                out.flush();
                return;
            }
            /* Kiểm tra xem thể loại sản phẩm đã tồn tại chưa nếu chưa thì thêm */
            int pCategory = 0;
            if (!newCategory.isEmpty()) {
                if (mAddProductDao.isCategoryExists(newCategory)) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print("{\"error\": \"Loại sản phẩm " + newCategory + " đã tồn tại!\"}");
                    out.flush();
                    return;
                } else {
                    newCategory = newCategory.trim().replaceAll("\\s+", " ");
                    pCategory = mAddProductDao.addCategory(newCategory);
                }
            } else {
                pCategory = Integer.parseInt(category);
            }

            /* Kiểm tra xem nhà cung cấp đã tồn tại chưa nếu chưa thì thêm */
            int supProduct = 0;
            if (!newSupplier.isEmpty()) {
                if (mAddProductDao.isSupplierExists(newSupplier)) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print("{\"error\": \"Nhà cung cấp " + newSupplier + " đã tồn tại!\"}");
                    out.flush();
                    return;
                } else {
                    newSupplier = newSupplier.trim().replaceAll("\\s+", " ");
                    supProduct = mAddProductDao.addSupplier(newSupplier);
                }
            } else {
                supProduct = Integer.parseInt(supplier);
            }
            
            /*Xử lí thêm ảnh sản phẩm*/
            String thumbnailFileName = null;
            if (thumbnail != null && thumbnail.getSize() > 0) {
                String uploadLink = "images/product/single/";
                thumbnailFileName = Paths.get(thumbnail.getSubmittedFileName()).getFileName().toString();
                String thumbnailPath = getServletContext().getRealPath("") + File.separator + uploadLink;

                File dir = new File(thumbnailPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filePath = thumbnailPath + File.separator + thumbnailFileName;
                try {
                    thumbnail.write(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                thumbnailFileName = "images/product/single/" + thumbnailFileName;
            }
//            java.sql.Date dateProduct = null;
//            if (dateCreated == null || dateCreated.isEmpty()) {
//                return;
//            } else {
//                try {
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    java.util.Date parsedDate = sdf.parse(dateCreated);
//                    dateProduct = new java.sql.Date(parsedDate.getTime());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }

            int pColor = Integer.parseInt(color);
            int pMemory = Integer.parseInt(memory);
            int pQuantity = Integer.parseInt(quantity);
            float priceOrigin = Float.parseFloat(originPrice);
            float priceSale = Float.parseFloat(salePrice);
            mAddProductDao.addNewProduct(
                    pCategory,
                    supProduct,
                    productName,
                    brief,
                    description,
                    null,
                    pColor,
                    pMemory,
                    pQuantity,
                    priceOrigin,
                    priceSale,
                    thumbnailFileName
            );
            request.getRequestDispatcher("views/marketing/item-page/addproduct.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Can't add Product");
            e.printStackTrace();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import java.util.*;

/**
 *
 * @author quang
 */
@WebServlet(name = "ProductsDetailServlet", urlPatterns = {"/productsdetail"})
public class ProductsDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productId_raw = request.getParameter("productId");
        HttpSession session = request.getSession();
        MaketingAddProductDAO proDetailDao = new MaketingAddProductDAO();
        ProductDAO pd = new ProductDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        ColorDAO cd = new ColorDAO();
        MemoryDAO md = new MemoryDAO();

        try {
            int productId = Integer.parseInt(productId_raw);
            // lay ve san pham muon xem chi tiet
            Product product = pd.getProductById(productId);
            session.setAttribute("product", product);

            // lay ve tat ca cac color
            List<Color> getColorAll = cd.getColorAll();
            session.setAttribute("colorAll", getColorAll);

            // lay ve tat ca cac memory
            List<Memory> getMemoryAll = md.getMemoryAll();
            session.setAttribute("memoryAll", getMemoryAll);
            int quantityStock;
            String colorId_raw = request.getParameter("colorId");
            String memoryId_raw = request.getParameter("memoryId");

            int colorId = Integer.parseInt(colorId_raw);
            int memoryId = Integer.parseInt(memoryId_raw);
            quantityStock = pdd.getQuantityStockByProductDetailId(productId, colorId, memoryId);

            // lay ve productdetail 
            ProductDetail productDetail = pdd.getProductDetailByProductIdColorIdMemoryId(productId, colorId, memoryId);
            request.setAttribute("productDetail", productDetail);

            session.setAttribute("quantityStock", quantityStock);
            List<Category> listCategory = proDetailDao.getCategoryByProduct(productId);
            List<Supplier> listSupplier = proDetailDao.getSupplierByProduct(productId);

            request.setAttribute("listCategory", listCategory);
            request.setAttribute("listSupplier", listSupplier);
            request.getRequestDispatcher("views/marketing/item-page/productdetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid product ID.");
            request.getRequestDispatcher("views/marketing/item-page/productdetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("postID");
        if (id != null && !id.isEmpty()) {
            int postID = Integer.parseInt(id);

            MaketingAddProductDAO bd = new MaketingAddProductDAO();
            bd.updateStatusProductDetail(postID);
            response.sendRedirect(request.getContextPath() + "/productsdetail");
        } else {
            request.getRequestDispatcher("views/marketing/item-page/productdetail.jsp").forward(request, response);
        }
    }

}

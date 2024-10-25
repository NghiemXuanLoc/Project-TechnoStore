/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CategoryDAO;
import dal.ColorDAO;
import dal.FeedbackProductDAO;
import dal.MemoryDAO;
import dal.OrderDetailDAO;
import dal.OrdersDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.Color;
import model.FeedbackProduct;
import model.Memory;
import model.Orders;
import model.Product;
import model.ProductDetail;
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name = "FeedbackServlet", urlPatterns = {"/feedback"})
public class FeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // add feedback
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        session.removeAttribute("account");

        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        ColorDAO colorD = new ColorDAO();
        MemoryDAO md = new MemoryDAO();
        OrderDetailDAO odd = new OrderDetailDAO();
        OrdersDAO od = new OrdersDAO();
        FeedbackProductDAO fpd = new FeedbackProductDAO();

        List<Category> getCategoryAll = cd.getCategoryAll();

        // lay ve top 2 san pham moi nhat
        List<Product> getTop2LastProduct = pd.getTop2LastProductByCategoryAll();
        session.setAttribute("top2LastProduct", getTop2LastProduct);
        session.setAttribute("categoryAll", getCategoryAll);

        String productId_raw = request.getParameter("productId");
        String colorId_raw = request.getParameter("colorId");
        String memoryId_raw = request.getParameter("memoryId");
        String quantity_raw = request.getParameter("quantity");
        String orderId_raw = request.getParameter("orderId");

        User account = (User) session.getAttribute("account");

        try {
            int productId = Integer.parseInt(productId_raw);
            int colorId = Integer.parseInt(colorId_raw);
            int memoryId = Integer.parseInt(memoryId_raw);
            int quantity = Integer.parseInt(quantity_raw);
            int orderId = Integer.parseInt(orderId_raw);

            // kiem tra xem user nay da mua san pham muon feedback hay chua
            ProductDetail productDetail = pdd.getProductDetailByProductIdColorIdMemoryId(productId, colorId, memoryId);

            Orders order = od.getOrderById(orderId);

            boolean checkPurchase = odd.checkPurchaseProduct(productDetail.getProductDetailId(), orderId, account != null ? account.getUserId() : -1);

            if (checkPurchase == false) {

                if (order == null) {
                    request.setAttribute("error", "The order does not exist, please check again!");
                } else if (order.getUser().getRole().getRoleId() != 5) {
                    request.setAttribute("error", "You are not the buyer of this product, please buy the product to be able to give feedback, or log in to the account that purchased this product");
                }
            }

            Color color = colorD.getColorById(colorId);
            Memory memory = md.getMemoryById(memoryId);

            request.setAttribute("productDetail", productDetail);
            request.setAttribute("color", color);
            request.setAttribute("memory", memory);
            request.setAttribute("quantity", quantity);
            request.setAttribute("order", order);
            request.setAttribute("productDetailId", productDetail.getProductDetailId());

            // tat ca dieu kien ben tren thoan man, tiep tuc kiem tra xem user da feedback cho san pham nay chua, neu feedback r thi ra se chuyen sang che do edit
            FeedbackProduct fp = fpd.getFeedbackByOrderIdAndProductDetailId(orderId, productDetail.getProductDetailId());
            
            request.setAttribute("fp", fp);
            
            if(fp != null && fp.getEditNumber() != 0){
                request.setAttribute("error", "You have reached the edit limit for this feedback");
            }

            request.getRequestDispatcher("views/user/item-page/feedback.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("error: " + e);
        }

    }

}

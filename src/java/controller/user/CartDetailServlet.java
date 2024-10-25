/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CartAccountDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Product;
import model.ProductDetail;
import model.User;

/**
 *
 * @author PC
 */
public class CartDetailServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ham nay xu ly lay ve so luong co san trong cart cua guest or account

        String productId_raw = request.getParameter("productId");
        String colorId_raw = request.getParameter("colorId");
        String memoryId_raw = request.getParameter("memoryId");
        
        HttpSession session = request.getSession();
        PrintWriter print = response.getWriter();
        
        User account = (User) session.getAttribute("account");
        
        ProductDetailDAO pdd = new ProductDetailDAO();
        
        int soLuong = -1;
        
        try {
            int productId = Integer.parseInt(productId_raw);
            int colorId = Integer.parseInt(colorId_raw);
            int memoryId = Integer.parseInt(memoryId_raw);
            
            ProductDetail productDetail = pdd.getProductDetailByProductIdColorIdMemoryId(productId, colorId, memoryId);
            
            if (account == null) {
                // xu ly lay ve so luong co san trong cart cua guest
                Cookie[] cookies = request.getCookies();
                
                Cookie cookieGuest = new Cookie("Guest", "");

                // danh dau su ton tai cua cookie guest
                boolean isCookieGuest = false;
                
                for (Cookie c : cookies) {
                    if (c.getName().equalsIgnoreCase("Guest")) {
                        cookieGuest = c;
                        isCookieGuest = true;
                        break;
                    }
                }

                // cookie guest da ton tai
                if (isCookieGuest) {
                    String[] productInCarts = cookieGuest.getValue().split("&");

                    // chuyen doi cookie luu tru gio hang thanh san pham cu the, luu chu vao model cart
                    for (String productInCart : productInCarts) {
                        String[] productDetailInCart = productInCart.split("-");
                        int productDetailIdInCart = Integer.parseInt(productDetailInCart[0]);
                        int soLuongInCart = Integer.parseInt(productDetailInCart[1]);
                        
                        if (productDetailIdInCart == productDetail.getProductDetailId()) {
                            // lay ve so luong co san cua san pham trong cart ban dau de kiem tra xem lieu san pham nay co the
                            // add them duoc khong
                            soLuong = soLuongInCart;
                        }
                    }
                }
            } else {
                CartAccountDAO cad = new CartAccountDAO();
                
                Map<Integer, Integer> cartAccount = account.getCart();

                // lay ve so luong co san cua san pham trong cart ban dau de kiem tra xem lieu san pham nay co the
                // add them duoc khong
                if (cartAccount.containsKey(productDetail.getProductDetailId())) {
                    soLuong = cartAccount.get(productDetail.getProductDetailId());
                }
            }
            
            print.write(soLuong + "");
            
        } catch (Exception e) {
            System.out.println("loi chuyen doi so cartdetail method post: " + e);
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        
        List<Category> getCategoryAll = cd.getCategoryAll();

        // lay ve top 2 san pham moi nhat
        List<Product> getTop2LastProduct = pd.getTop2LastProductByCategoryAll();
        session.setAttribute("top2LastProduct", getTop2LastProduct);
        session.setAttribute("categoryAll", getCategoryAll);
        
        request.getRequestDispatcher("views/user/item-page/cartdetail.jsp").forward(request, response);
    }
    
}

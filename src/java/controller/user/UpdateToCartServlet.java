/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CartAccountDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.ProductDetail;
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateToCartServlet", urlPatterns = {"/updatetocart"})
public class UpdateToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productDetailId_raw = request.getParameter("productDetailId");
        String quantity_raw = request.getParameter("quantity");
        String option = request.getParameter("option");
        
        
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        ProductDetailDAO pdd = new ProductDetailDAO();

        User account = (User) session.getAttribute("account");
        
        List<ProductDetail> listProductDetail = new ArrayList<>();
        List<Integer> listSoLuong = new ArrayList<>();

        try {
            int productDetailId = Integer.parseInt(productDetailId_raw);
            int quantity = Integer.parseInt(quantity_raw);

            // update for guest
            if (account == null) {
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

                        if (productDetailIdInCart == productDetailId) {
                            listProductDetail.add(pdd.getProductDetailById(productDetailIdInCart));
                            listSoLuong.add(quantity);
                        } else {
                            listProductDetail.add(pdd.getProductDetailById(productDetailIdInCart));
                            listSoLuong.add(soLuongInCart);
                        }
                    }

                    // chuyen doi model cart thanh value cookie de add vao trinh duyet
                    String value = "";

                    for (int i = 0; i < listProductDetail.size(); i++) {
                        value += listProductDetail.get(i).getProductDetailId() + "-" + listSoLuong.get(i) + "&";
                    }

                    value = value.substring(0, value.length() - 1);
                    cookieGuest.setValue(value);

                    cookieGuest.setMaxAge(60 * 60 * 24 * 30 * 2);
                    response.addCookie(cookieGuest);

                }
            } else {
                // update for account
                CartAccountDAO cad = new CartAccountDAO();

                Map<Integer, Integer> cartAccount = account.getCart();

                // thay doi so luong cua san pham trong cart
                if (cartAccount.containsKey(productDetailId)) {
                    System.out.println("update quantity productdetail in cart");
                    cad.updateCartAccountByUserId(account.getUserId(), productDetailId, quantity);
                }

                // cap nhat lai cartAccount(sort theo date tang dan, muc dich la hien thi san pham moi nhat len dau)
                cartAccount = cad.getCarAccountByUserId(account.getUserId());

                for (Map.Entry<Integer, Integer> x : cartAccount.entrySet()) {
                    listProductDetail.add(pdd.getProductDetailById(x.getKey()));
                    listSoLuong.add(x.getValue());
                }
                
                cartAccount = cad.getCarAccountByUserId(account.getUserId());
                account.setCart(cartAccount);
                
                session.setAttribute("account", account);
            }
            
            Cart cart = new Cart(listProductDetail, listSoLuong);
            cart.setTotalPriceBeforeDiscount();
            cart.setTotalPriceAfterDiscount();
            // cart cua guest
            session.setAttribute("cart", cart);
            
        } catch (NumberFormatException e) {
            System.out.println("loi chuyen doi so trong update to cart method post: " + e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productDetailId_raw = request.getParameter("productDetailId");

        ProductDetailDAO pdd = new ProductDetailDAO();
        PrintWriter print = response.getWriter();

        try {
            int productDetailId = Integer.parseInt(productDetailId_raw);
            ProductDetail productDetail = pdd.getProductDetailById(productDetailId);

            int productId = productDetail.getProduct().getProductId();
            int colorId = productDetail.getColor().getColorId();
            int memoryId = productDetail.getMemory().getMemoryId();

            int quantityStock = pdd.getQuantityStockByProductDetailId(productId, colorId, memoryId);

            print.write(quantityStock + "");

        } catch (Exception e) {
            System.out.println("loi chuyen doi so update to cart: " + e);
        }
    }
}

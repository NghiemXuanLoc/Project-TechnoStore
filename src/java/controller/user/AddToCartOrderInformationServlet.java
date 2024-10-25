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
 * @author BUI TUAN DAT
 */
@WebServlet(name="AddToCartOrderInformationServlet", urlPatterns={"/addorderinformation"})
public class AddToCartOrderInformationServlet extends HttpServlet {
   
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    ProductDetailDAO pdd = new ProductDetailDAO();
    Cookie[] cookies = request.getCookies();

    String[] productId_raw = request.getParameterValues("productId");
    String[] colorId_raw = request.getParameterValues("colorId");
    String[] memoryId_raw = request.getParameterValues("memoryId");
    String[] soLuong_raw = request.getParameterValues("soLuong");

    if (productId_raw != null && colorId_raw != null && memoryId_raw != null && soLuong_raw != null) {
        try {
            List<ProductDetail> listProductDetail = new ArrayList<>();
            List<Integer> listSoLuong = new ArrayList<>();
            
            for (int i = 0; i < productId_raw.length; i++) {
                int productId = Integer.parseInt(productId_raw[i]);
                int colorId = Integer.parseInt(colorId_raw[i]);
                int memoryId = Integer.parseInt(memoryId_raw[i]);
                int soLuong = Integer.parseInt(soLuong_raw[i]);

                ProductDetail productAddToCart = pdd.getProductDetailByProductIdColorIdMemoryId(productId, colorId, memoryId);
                listProductDetail.add(productAddToCart);
                listSoLuong.add(soLuong);
            }

            User account = (User) session.getAttribute("account");

            if (account == null) {
                Cookie cookieGuest = new Cookie("Guest", "");
                boolean isCookieGuest = false;

                for (Cookie c : cookies) {
                    if (c.getName().equalsIgnoreCase("Guest")) {
                        cookieGuest = c;
                        isCookieGuest = true;
                        break;
                    }
                }

                if (isCookieGuest) {
                    String[] productInCarts = cookieGuest.getValue().split("&");

                    for (String productInCart : productInCarts) {
                        String[] productDetailInCart = productInCart.split("-");
                        int productDetailIdInCart = Integer.parseInt(productDetailInCart[0]);
                        int soLuongInCart = Integer.parseInt(productDetailInCart[1]);

                        listProductDetail.add(pdd.getProductDetailById(productDetailIdInCart));
                        listSoLuong.add(soLuongInCart);
                    }
                }

                String value = "";
                for (int i = 0; i < listProductDetail.size(); i++) {
                    value += listProductDetail.get(i).getProductDetailId() + "-" + listSoLuong.get(i) + "&";
                }
                value = value.substring(0, value.length() - 1);
                cookieGuest.setValue(value);
                cookieGuest.setMaxAge(60 * 60 * 24 * 30 * 2);
                response.addCookie(cookieGuest);

                Cart cart = new Cart(listProductDetail, listSoLuong);
                cart.setTotalPriceBeforeDiscount();
                cart.setTotalPriceAfterDiscount();

                session.setAttribute("cart", cart);

            } else {
                CartAccountDAO cad = new CartAccountDAO();
                Map<Integer, Integer> cartAccount = account.getCart();

                for (int i = 0; i < listProductDetail.size(); i++) {
                    ProductDetail productAddToCart = listProductDetail.get(i);
                    int soLuong = listSoLuong.get(i);

                    if (cartAccount.containsKey(productAddToCart.getProductDetailId())) {
                        cad.updateCartAccountByUserId(account.getUserId(), productAddToCart.getProductDetailId(), cartAccount.get(productAddToCart.getProductDetailId()) + soLuong);
                    } else {
                        cad.insertCartAccountByUserId(account.getUserId(), productAddToCart.getProductDetailId(), soLuong);
                    }
                }

                cartAccount = cad.getCarAccountByUserId(account.getUserId());

                List<ProductDetail> newListProductDetail = new ArrayList<>();
                List<Integer> newListSoLuong = new ArrayList<>();

                for (Map.Entry<Integer, Integer> x : cartAccount.entrySet()) {
                    newListProductDetail.add(pdd.getProductDetailById(x.getKey()));
                    newListSoLuong.add(x.getValue());
                }

                Cart cart = new Cart(newListProductDetail, newListSoLuong);
                cart.setTotalPriceBeforeDiscount();
                cart.setTotalPriceAfterDiscount();

                account.setCart(cartAccount);

                session.setAttribute("account", account);
                session.setAttribute("cart", cart);
            }

            response.sendRedirect("cartdetail");
        } catch (Exception e) {
            System.out.println("Error adding to cart: " + e);
        }
    } else {
        response.sendRedirect("cartdetail");
    }
}



}

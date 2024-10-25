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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/addtocart"})
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ProductDetailDAO pdd = new ProductDetailDAO();

        Cookie[] cookies = request.getCookies();

        String productId_raw = request.getParameter("productId");
        String colorId_raw = request.getParameter("colorId");
        String memoryId_raw = request.getParameter("memoryId");
        String soLuong_raw = request.getParameter("soLuong");

        try {
            // lay ve cookie luu chu gio hang cua guest

            int productId = Integer.parseInt(productId_raw);
            int colorId = Integer.parseInt(colorId_raw);
            int memoryId = Integer.parseInt(memoryId_raw);
            int soLuong = Integer.parseInt(soLuong_raw);

            ProductDetail productAddToCart = pdd.getProductDetailByProductIdColorIdMemoryId(productId, colorId, memoryId);

            User account = (User) session.getAttribute("account");

            // add to cart for Guest
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

                List<ProductDetail> listProductDetail = new ArrayList<>();
                List<Integer> listSoLuong = new ArrayList<>();

                // cookie guest da ton tai
                if (isCookieGuest) {
                    String[] productInCarts = cookieGuest.getValue().split("&");

                    // chuyen doi cookie luu tru gio hang thanh san pham cu the, luu chu vao model cart
                    for (String productInCart : productInCarts) {
                        String[] productDetailInCart = productInCart.split("-");
                        int productDetailIdInCart = Integer.parseInt(productDetailInCart[0]);
                        int soLuongInCart = Integer.parseInt(productDetailInCart[1]);

                        listProductDetail.add(pdd.getProductDetailById(productDetailIdInCart));
                        listSoLuong.add(soLuongInCart);
                    }

                    // kiem tra xem san pham moi add vao cart da co trong model cart hay chua
                    boolean isExitProductDetail = false;

                    // danh dau xem product detail do xuat hien thu may trong cart
                    int indexProductDetailInCart = -1;

                    for (int i = 0; i < listProductDetail.size(); i++) {
                        if (listProductDetail.get(i).getProductDetailId() == productAddToCart.getProductDetailId()) {
                            isExitProductDetail = true;
                            indexProductDetailInCart = i;
                            break;
                        }
                    }

                    // neu nhu product detail chua ton tai trong cart thi ta add product detail vao model cart
                    if (isExitProductDetail == false) {
                        listProductDetail.add(productAddToCart);
                        listSoLuong.add(soLuong);
                    } else {
                        // product detail do da ton tai trong cart, thi ta se cap nhat so luong san pham co san trong model cart
                        // lay ve so luong san pham cu
                        int soLuongCu = listSoLuong.get(indexProductDetailInCart);

                        // xoa di san pham trong cart, de cap nhat san pham len dau cua trang
                        listProductDetail.remove(indexProductDetailInCart);
                        listSoLuong.remove(indexProductDetailInCart);

                        int soLuongMoi = soLuongCu + soLuong;

                        // add lai vao cart
                        listProductDetail.add(productAddToCart);
                        listSoLuong.add(soLuongMoi);
                    }

                    // chuyen doi model cart thanh value cookie de add vao trinh duyet
                    String value = "";

                    for (int i = 0; i < listProductDetail.size(); i++) {
                        value += listProductDetail.get(i).getProductDetailId() + "-" + listSoLuong.get(i) + "&";
                    }

                    value = value.substring(0, value.length() - 1);
                    cookieGuest.setValue(value);

                } else {
                    // cookie guest chua ton tai
                    String value = productAddToCart.getProductDetailId() + "-" + soLuong;
                    listProductDetail.add(productAddToCart);
                    listSoLuong.add(soLuong);
                    cookieGuest.setValue(value);
                }

                // gui model cart vao trong session de hien thi ro hon
                Cart cart = new Cart(listProductDetail, listSoLuong);
                cart.setTotalPriceBeforeDiscount();
                cart.setTotalPriceAfterDiscount();

                session.setAttribute("cart", cart);

                // luu chu cookie trong 2 thang
                cookieGuest.setMaxAge(60 * 60 * 24 * 30 * 2);
                response.addCookie(cookieGuest);
            } else {
                // add to cart for Account

                CartAccountDAO cad = new CartAccountDAO();

                Map<Integer, Integer> cartAccount = account.getCart();

                // kiem tra xem san pham muon add vao cart account da co trong database chua
                // neu co ro thi udpate so luong
                if (cartAccount.containsKey(productAddToCart.getProductDetailId())) {
                    cad.updateCartAccountByUserId(account.getUserId(), productAddToCart.getProductDetailId(), cartAccount.get(productAddToCart.getProductDetailId()) + soLuong);
                } else {
                    // neu chua co thi add moi
                    cad.insertCartAccountByUserId(account.getUserId(), productAddToCart.getProductDetailId(), soLuong);
                }

                // cap nhat lai cartAccount(sort theo date tang dan, muc dich la hien thi san pham moi nhat len dau)
                cartAccount = cad.getCarAccountByUserId(account.getUserId());

                List<ProductDetail> listProductDetail = new ArrayList<>();
                List<Integer> listSoLuong = new ArrayList<>();

                for (Map.Entry<Integer, Integer> x : cartAccount.entrySet()) {
                    listProductDetail.add(pdd.getProductDetailById(x.getKey()));
                    listSoLuong.add(x.getValue());
                }

                // tao ra cart de gui vao session
                Cart cart = new Cart(listProductDetail, listSoLuong);
                cart.setTotalPriceBeforeDiscount();
                cart.setTotalPriceAfterDiscount();

                account.setCart(cartAccount);

                session.setAttribute("account", account);
                session.setAttribute("cart", cart);
            }

            response.sendRedirect("cartdetail");
        } catch (Exception e) {
            System.out.println("loi chuyen doi so addToCartServlet: " + e);
        }
    }

}

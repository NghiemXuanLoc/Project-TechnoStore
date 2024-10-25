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
@WebServlet(name = "DeleteToCartServlet", urlPatterns = {"/deletetocart"})
public class DeleteToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ProductDetailDAO pdd = new ProductDetailDAO();
        PrintWriter print = response.getWriter();
        CartAccountDAO cad = new CartAccountDAO();

        String productDetailId_raw = request.getParameter("productDetailId");

        List<ProductDetail> listProductDetail = new ArrayList<>();
        List<Integer> listSoLuong = new ArrayList<>();

        try {
            int productDetailId = Integer.parseInt(productDetailId_raw);

            User account = (User) session.getAttribute("account");

            // delete cart for guest
            if (account == null) {
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

                if (isCookieGuest) {

                    String[] productInCarts = cookieGuest.getValue().split("&");

                    // chuyen doi cookie luu tru gio hang thanh san pham cu the, luu chu vao model cart
                    if (productInCarts.length > 0) {
                        for (String productInCart : productInCarts) {
                            String[] productDetailInCart = productInCart.split("-");

                            if (productDetailInCart.length > 0) {
                                int productDetailIdInCart = Integer.parseInt(productDetailInCart[0]);
                                int soLuongInCart = Integer.parseInt(productDetailInCart[1]);

                                if (productDetailIdInCart != productDetailId) {
                                    listProductDetail.add(pdd.getProductDetailById(productDetailIdInCart));
                                    listSoLuong.add(soLuongInCart);
                                }
                            }

                        }
                    }

                    String value = "";

                    for (int i = 0; i < listProductDetail.size(); i++) {
                        value += listProductDetail.get(i).getProductDetailId() + "-" + listSoLuong.get(i) + "&";
                    }

                    if (!value.equalsIgnoreCase("")) {
                        value = value.substring(0, value.length() - 1);
                        cookieGuest.setValue(value);
                        cookieGuest.setMaxAge(60 * 60 * 24 * 60);
                    } else {
                        cookieGuest.setMaxAge(0);
                    }

                    response.addCookie(cookieGuest);
                }
            } else {
                System.out.println("delete product detail");
                // delete product from cart (account)
                Map<Integer, Integer> cartAccount = account.getCart();

                for (Map.Entry<Integer, Integer> x : cartAccount.entrySet()) {
                    // chi lay ra nhung thang nao khac thang muon xoa
                    if (x.getKey() != productDetailId) {
                        listProductDetail.add(pdd.getProductDetailById(x.getKey()));
                        listSoLuong.add(x.getValue());
                    }
                }
                // delete product muon xoa khoi database
                cad.deleteProductFromCartAccountByUserId(account.getUserId(), productDetailId);

                // cap nhat lai cart trong user sau khi delete
                cartAccount = cad.getCarAccountByUserId(account.getUserId());

                account.setCart(cartAccount);

                session.setAttribute("account", account);
            }

            Cart cart = new Cart(listProductDetail, listSoLuong);
            cart.setTotalPriceBeforeDiscount();
            cart.setTotalPriceAfterDiscount();

            session.setAttribute("cart", cart);

            String htmls = convertCartToStringHtmls(cart);

            print.write(htmls);

        } catch (Exception e) {
            System.out.println("loi chuyen doi so deleteToCartServlet: " + e);
        }
    }

    private String convertCartToStringHtmls(Cart cart) {
        String result = "";
        for (int i = cart.getListProductDetails().size() - 1; i >= 0; i--) {
            result
                    += "<tr id=\"table-data-productdetail-" + cart.getListProductDetails().get(i).getProductDetailId() + "\">\n"
                    + "\n"
                    + "                                                                                <td style=\"text-align: center\">\n"
                    + "                                                                                    <input name=\"checked\" value=\"" + cart.getListProductDetails().get(i).getProductDetailId() + "\" onchange=\"selectProductPayment()\" style=\"opacity: 1\" type=\"checkbox\" width=\"50px\"/>\n"
                    + "                                                                                    #" + cart.getListProductDetails().get(i).getProductDetailId() +""
                    + "                                                                                    <input type=\"hidden\" value=\"" + cart.getListProductDetails().get(i).getProductDetailId() + "\"/>"
                    + "                                                                                </td>\n"
                    + "\n"
                    + "                                                                                <td>\n"
                    + "                                                                                    <div class=\"img-product\">\n"
                    + "                                                                                        <a href=\"productdetail?option=common&productId=" + cart.getListProductDetails().get(i).getProduct().getProductId() + "\"><img src=\"" + cart.getListProductDetails().get(i).getProduct().getGallery().get(0) + "\" alt=\"\"></a>\n"
                    + "                                                                                    </div>\n"
                    + "                                                                                </td>\n"
                    + "                                                                                <td>\n"
                    + "                                                                                    <div class=\"name-product\">\n"
                    + "                                                                                        <a href=\"productdetail?option=common&productId=" + cart.getListProductDetails().get(i).getProduct().getProductId() + "\">" + cart.getListProductDetails().get(i).getProduct().getProductName() + "</a>\n"
                    + "                                                                                    </div>\n"
                    + "                                                                                </td>\n"
                    + "\n"
                    + "                                                                                <td>\n"
                    + "                                                                                    <p>" + cart.getListProductDetails().get(i).getColor().getColorName() + "</p>\n"
                    + "                                                                                </td>\n"
                    + "\n"
                    + "                                                                                <td style=\"text-align: left\">\n"
                    + "                                                                                    <p>" + cart.getListProductDetails().get(i).getMemory().getMemorySize() + "</p>\n"
                    + "                                                                                </td>\n"
                    + "\n"
                    + "                                                                                <td>\n"
                    + "                                                                                    <div class=\"price\">\n"
                    + "                                                                                        <span>" + (cart.getListProductDetails().get(i).getPriceSale() / 1000000) + "</span>tr\n"
                    + "                                                                                        <span id=\"price-origin-" + cart.getListProductDetails().get(i).getProductDetailId() + "\" style=\"text-decoration: line-through; font-size: 14px\">" + (cart.getListProductDetails().get(i).getPriceOrigin() / 1000000) + " </span>tr\n"
                    + "                                                                                    </div>\n"
                    + "                                                                                </td>\n"
                    + "\n"
                    + "\n"
                    + "                                                                                <td>\n"
                    + "                                                                                    <div class=\"quanlity\">\n"
                    + "                                                                                        <span style=\"left: 10px\" onclick=\"upDowQuantityCartDetail('giam', " + i + ", " + cart.getListProductDetails().get(i).getProductDetailId() + ")\" class=\"btn-down\"></span>\n"
                    + "                                                                                        <input style=\"width: 100px\" onchange=\"changeQuantityInCart(" + i + ", " + cart.getListProductDetails().get(i).getProductDetailId() + ")\" id=\"quantity" + i + "\" type=\"number\" name=\"number\" value=\"" + cart.getSoLuong().get(i) + "\" min=\"1\" max=\"100\" placeholder=\"Quanlity\">\n"
                    + "                                                                                            <span style=\"right: 30px\" onclick=\"upDowQuantityCartDetail('tang', " + i + ", " + cart.getListProductDetails().get(i).getProductDetailId() + ")\" class=\"btn-up\"></span>\n"
                    + "                                                                                    </div>\n"
                    + "                                                                                </td>\n"
                    + "\n"
                    + "                                                                                <td>\n"
                    + "                                                                                    <div class=\"total\">\n"
                    + "                                                                                        <span>" + (cart.getListProductDetails().get(i).getPriceSale() * cart.getSoLuong().get(i) / 1000000) + "</span> tr\n"
                    + "                                                                                    </div>\n"
                    + "                                                                                </td>\n"
                    + "                                                                                <td>\n"
                    + "                                                                                    <a onclick=\"deleteProductFromCart(" + cart.getListProductDetails().get(i).getProductDetailId() + ")\" href=\"javascript:void(0)\" title=\"\">\n"
                    + "                                                                                        <img src=\"images/icons/delete.png\" alt=\"\">\n"
                    + "                                                                                    </a>\n"
                    + "                                                                                </td>\n"
                    + "                                                                            </tr>\n";
        }

        return result;
    }

}

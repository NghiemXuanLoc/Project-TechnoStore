/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import constant.Iconstant;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateQuickViewCartServlet", urlPatterns = {"/updatequickviewcart"})
public class UpdateQuickViewCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        PrintWriter print = response.getWriter();

        Cart cart = (Cart) session.getAttribute("cart");

        int size = cart.getListProductDetails().size();

        String htmlsLI = "";

        for (int i = size - 1; i >= size - 3 && i >= 0; i--) {
            htmlsLI
                    += "<li>\n"
                    + "                                        <div class=\"img-product\">\n"
                    + "                                            <img src=\"" + cart.getListProductDetails().get(i).getProduct().getGallery().get(0) + "\" alt=\"\">\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"info-product\">\n"
                    + "                                            <div class=\"name\">\n"
                    + "                                                " + cart.getListProductDetails().get(i).getProduct().getProductName() + "\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"price\">\n"
                    + "                                                <span>" + cart.getSoLuong().get(i) + " x</span>\n"
                    + "                                                <span>" + Iconstant.formatCurrency(cart.getListProductDetails().get(i).getPriceSale()) + "</span>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"clearfix\"></div>\n"
                    + "                                    </li>\n";

        }

        String htmlsResult
                = "<ul >\n"
                + htmlsLI + "\n"
                + "                                </ul>\n"
                + "\n"
                + "\n"
                + "                                <div class=\"total\">\n"
                + "                                    <span>Subtotal:</span>\n"
                + "                                    <span class=\"price\">" + Iconstant.formatCurrency(cart.getTotalPriceAfterDiscount()) + "</span>\n"
                + "                                </div>\n"
                + "\n"
                + "                                <div class=\"btn-cart\">\n"
                + "                                    <a href=\"shop-cart.html\" class=\"view-cart\" title=\"\">View Cart</a>\n"
                + "                                    <a href=\"shop-checkout.html\" class=\"check-out\" title=\"\">Checkout</a>\n"
                + "                                </div>";

        print.write(htmlsResult);
    }

}

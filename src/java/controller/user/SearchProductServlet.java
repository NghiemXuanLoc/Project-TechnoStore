/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import model.Product;

/**
 *
 * @author PC
 */
public class SearchProductServlet extends HttpServlet {

    public static String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        return currencyFormatter.format(amount);
    }

    // Hàm chuyển đổi số thành định dạng tiền tệ theo một locale cụ thể
    public static String formatCurrency(double amount, Locale locale) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(amount);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String textSeach = request.getParameter("textSearch");
        String action = request.getParameter("action");

        // Định dạng số theo định dạng tiền tệ của Việt Nam
        Locale vietnamLocale = new Locale("vi", "VN");

        ProductDAO pd = new ProductDAO();

        List<Product> getSearchProduct = pd.getSearchProduct(textSeach);

        String result = "";

        if (action.equalsIgnoreCase("SearchProduct")) {
            for (Product product : getSearchProduct) {
                result
                        += "<li>\n"
                        + "                                                <div class=\"image\">\n"
                        + "<a href=\"productdetail?option=common&productId=" + product.getProductId() + "\" title=\"\">\n"
                        + "<img src=\"" + product.getGallery().get(0) + "\" alt=\"\">"
                        + "                                            </a>     "
                        + "                                                </div>\n"
                        + "                                                <div class=\"info-product\">\n"
                        + "                                                    <div class=\"name\">\n"
                        + "                                                        <a href=\"productdetail?option=common&productId=" + product.getProductId() + "\" title=\"\">" + product.getProductName() + "</a>\n"
                        + "                                                    </div>\n"
                        + "                                                    <div class=\"price\">\n"
                        + "                                                        <span class=\"sale\">\n"
                        + "                                                            " + formatCurrency(product.getMinPrice(), vietnamLocale) + "\n"
                        + "                                                        </span>\n"
                        + "                                                        <span class=\"regular\">\n"
                        + "                                                            " + formatCurrency(product.getMinPriceOrigin(), vietnamLocale) + "\n"
                        + "                                                        </span>\n"
                        + "                                                    </div>\n"
                        + "                                                </div>\n"
                        + "                                            </li>\n";
            }
        } else if (action.equalsIgnoreCase("AddCompare")) {
            for (Product product : getSearchProduct) {
                result
                        += "<li>\n"
                        + "                                                <div class=\"image\">\n"
                        + "<a onclick=\"selectProductCompare('" + product.getGallery().get(0) + "', '"+ product.getProductName() +"', '" + product.getProductId() +"')\" href=\"javascript:void(0)\" title=\"\">\n"
                        + "<img src=\"" + product.getGallery().get(0) + "\" alt=\"\">"
                        + "                                            </a>     "
                        + "                                                </div>\n"
                        + "                                                <div class=\"info-product\">\n"
                        + "                                                    <div class=\"name\">\n"
                        + "                                                        <a onclick=\"selectProductCompare('" + product.getGallery().get(0) + "', '"+ product.getProductName() +"', '" + product.getProductId() +"')\" href=\"javascript:void(0)" + "\" title=\"\">" + product.getProductName() + "</a>\n"
                        + "                                                    </div>\n"
                        + "                                                    <div class=\"price\">\n"
                        + "                                                        <span class=\"sale\">\n"
                        + "                                                            " + formatCurrency(product.getMinPrice(), vietnamLocale) + "\n"
                        + "                                                        </span>\n"
                        + "                                                        <span class=\"regular\">\n"
                        + "                                                            " + formatCurrency(product.getMinPriceOrigin(), vietnamLocale) + "\n"
                        + "                                                        </span>\n"
                        + "                                                    </div>\n"
                        + "                                                </div>\n"
                        + "                                            </li>\n";
            }
        }

        System.out.println("------------------");
        System.out.println(result);
        System.out.println("------------------");

        PrintWriter print = response.getWriter();
        print.write(result);
    }

}

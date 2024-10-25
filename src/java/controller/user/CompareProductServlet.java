/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import static controller.user.SearchProductServlet.formatCurrency;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.Locale;
import model.Product;
/**
 * 
 *
 * @author PC
 */

public class CompareProductServlet extends HttpServlet {

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
        String productId1_raw = request.getParameter("productId1");
        String productId2_raw = request.getParameter("productId2");

        PrintWriter print = response.getWriter();
        ProductDAO pd = new ProductDAO();
        Locale vietnamLocale = new Locale("vi", "VN");

        try {
            int productId1 = Integer.parseInt(productId1_raw);
            int productId2 = Integer.parseInt(productId2_raw);

            Product product1 = pd.getProductById(productId1);
            Product product2 = pd.getProductById(productId2);

            int quantityStockProduct1 = pd.getQuantityStockByProductId(productId1);
            int quantityStockProduct2 = pd.getQuantityStockByProductId(productId2);

            String tableDateProduct1 = "";
            String tableDateProduct2 = "";

            String desProduct1 = product1.getDescription();
            String desProduct2 = product2.getDescription();

            String[] thongSoKiThuatProduct1 = desProduct1.split("\\*");
            for (String item : thongSoKiThuatProduct1) {
                String[] detail = item.split("\\:");
                String detail0 = "", detail1 = "";

                if (detail.length == 1) {
                    detail0 = detail[0];
                } else if (detail.length >= 2) {
                    detail0 = detail[0];
                    detail1 = detail[1];
                }
                tableDateProduct1 += "<tr>\n"
                        + "                                                                            <td>" + detail0 + "</td>\n"
                        + "                                                                            <td>" + detail1 + "</td>\n"
                        + "                                                                        </tr>\n";
            }

            String[] thongSoKiThuatProduct2 = desProduct2.split("\\*");
            for (String item : thongSoKiThuatProduct2) {
                String[] detail = item.split("\\:");

                String detail0 = "", detail1 = "";

                if (detail.length == 1) {
                    detail0 = detail[0];
                } else if (detail.length >= 2) {
                    detail0 = detail[0];
                    detail1 = detail[1];
                }
                tableDateProduct2 += "<tr>\n"
                        + "                                                                            <td>" + detail0 + "</td>\n"
                        + "                                                                            <td>" + detail1 + "</td>\n"
                        + "                                                                        </tr>\n";
            }

            String rowDescription = "<tr>\n"
                    + "                                                                            <th>Description</th>\n"
                    + "                                                                            <td class=\"description\">\n"
                    + "                                                                                <table border=\"1px\">\n"
                    + "\n" + tableDateProduct1
                    + "                                                                                </table>\n"
                    + "                                                                            </td><!-- /.description -->\n"
                    + "\n"
                    + "                                                                            <td class=\"description\">\n"
                    + "                                                                                <table border=\"1px\">\n"
                    + "\n" + tableDateProduct2
                    + "                                                                                </table>\n"
                    + "                                                                            </td><!-- /.description -->\n"
                    + "                                                                        </tr>";

//            request.getRequestDispatcher("views/user/item-page/compareproduct.jsp").forward(request, response);
            print.write("                                        <section class=\"flat-compare\">\n"
                    + "\n"
                    + "                                            <div style=\"position: relative;\" class=\"container\">\n"
                    + "\n" + "<i onclick=\"closeCompare()\" style=\"    top: -35px;\n"
                    + "    position: absolute;\n"
                    + "    right: 16px;\n"
                    + "    font-size: 25px\" class=\"fa-solid fa-xmark\"></i>"
                    + "                                                <div class=\"row\">\n"
                    + "                                                    <div class=\"col-md-12\">\n"
                    + "                                                        <div class=\"wrap-compare\">\n"
                    + "                                                            <div class=\"compare-content\">\n"
                    + "                                                                <table class=\"table-compare\">\n"
                    + "                                                                    <thead>\n"
                    + "                                                                        <tr>\n"
                    + "                                                                            <th></th>\n"
                    + "                                                                        </tr>\n"
                    + "                                                                    </thead>\n"
                    + "                                                                    <tbody>\n"
                    + "                                                                        <tr>\n"
                    + "                                                                            <th>Product</th>\n"
                    + "\n"
                    + "                                                                            <td class=\"product\">\n"
                    + "                                                                                <div class=\"image\">\n"
                    + "                                                                                    <img style=\"max-width: 200px; height: auto\" src=\"" + product1.getGallery().get(0) + "\" alt=\"\">\n"
                    + "                                                                                </div>\n"
                    + "                                                                                <div class=\"name\">\n"
                    + "                                                                                    " + product1.getProductName() + "\n"
                    + "                                                                                </div>\n"
                    + "                                                                            </td><!-- /.product -->\n"
                    + "\n"
                    + "                                                                            <td class=\"product\">\n"
                    + "                                                                                <div class=\"image\">\n"
                    + "                                                                                    <img style=\"max-width: 200px; height: auto\" src=\"" + product2.getGallery().get(0) + "\" alt=\"\">\n"
                    + "                                                                                </div>\n"
                    + "                                                                                <div class=\"name\">\n"
                    + "                                                                                    " + product2.getProductName() + "\n"
                    + "                                                                                </div>\n"
                    + "                                                                            </td><!-- /.product -->\n"
                    + "\n"
                    + "                                                                        </tr>\n"
                    + "\n"
                    + "                                                                        <tr>\n"
                    + "                                                                            <th>Price</th>\n"
                    + "                                                                            <td class=\"price\">\n"
                    + "                                                                                " + formatCurrency(product1.getMinPrice(), vietnamLocale) + " - " + formatCurrency(product1.getMaxPrice(), vietnamLocale) + "\n"
                    + "                                                                            </td>\n"
                    + "                                                                            <td class=\"price\">\n"
                    + "                                                                                " + formatCurrency(product2.getMinPrice(), vietnamLocale) + " - " + formatCurrency(product2.getMaxPrice(), vietnamLocale) + "\n"
                    + "                                                                            </td>\n"
                    + "                                                                        </tr>\n"
                    + "\n"
                    + rowDescription
                    + "\n"
                    + "                                                                        <tr>\n"
                    + "                                                                            <th>Stock</th>\n"
                    + "                                                                            <td class=\"stock\">\n"
                    + "                                                                                <p>\n"
                    + "                                                                                    " + quantityStockProduct1 + " items\n"
                    + "                                                                                </p>\n"
                    + "                                                                            </td><!-- /.stock -->\n"
                    + "                                                                            <td class=\"stock\">\n"
                    + "                                                                                <p>\n"
                    + "                                                                                    " + quantityStockProduct2 + " items\n"
                    + "                                                                                </p>\n"
                    + "                                                                            </td><!-- /.stock -->\n"
                    + "\n"
                    + "                                                                        </tr>\n"
                    + "\n"
                    + "                                                                        <tr>\n"
                    + "                                                                            <th>Add to Cart</th>\n"
                    + "                                                                            <td class=\"add-cart\">\n"
                    + "                                                                                <a href=\"productdetail?option=common&productId=" + product1.getProductId() + "\" title=\"\"><img src=\"images/icons/add-cart.png\" alt=\"\">Add to Cart</a>\n"
                    + "\n"
                    + "                                                                            </td><!-- /.add-cart -->\n"
                    + "                                                                            <td class=\"add-cart\">\n"
                    + "                                                                                <a href=\"productdetail?option=common&productId=" + product2.getProductId() + "\" title=\"\"><img src=\"images/icons/add-cart.png\" alt=\"\">Add to Cart</a>\n"
                    + "\n"
                    + "                                                                            </td><!-- /.add-cart -->\n"
                    + "                                                                        </tr>\n"
                    + "                                                                    </tbody>\n"
                    + "                                                                </table><!-- /.table-compare -->\n"
                    + "                                                            </div><!-- /.compare-content -->\n"
                    + "                                                        </div><!-- /.wrap-compare -->\n"
                    + "                                                    </div><!-- /.col-md-12 -->\n"
                    + "                                                </div><!-- /.row -->\n"
                    + "                                            </div><!-- /.container -->\n"
                    + "                                        </section><!-- /.flat-compare -->");
        } catch (NumberFormatException e) {
            System.out.println("loi chuyen doi so trong compare product: " + e);
        }
    }

}

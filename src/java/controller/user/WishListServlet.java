///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller.user;
//
//import dal.ProductDAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import model.Product;
//import model.User;
//
///**
// *
// * @author PC
// */
//public class WishListServlet extends HttpServlet {
//
//    public static String formatCurrency(double amount) {
//        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
//        return currencyFormatter.format(amount);
//    }
//
//    // Hàm chuyển đổi số thành định dạng tiền tệ theo một locale cụ thể
//    public static String formatCurrency(double amount, Locale locale) {
//        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
//        return currencyFormatter.format(amount);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie[] cookies = request.getCookies();
//        HttpSession session = request.getSession();
//        ProductDAO pd = new ProductDAO();
//
//        PrintWriter print = response.getWriter();
//
//        User account = (User) session.getAttribute("account");
//
//        if (account != null) {
//            // lay ve hanh dong ma minh muon thao tac voi wishlist
//            String action = request.getParameter("action");
//
//            // hanh dong them san pham vao danh sach yeu thich
//            if (action != null && action.equalsIgnoreCase("addwishlist")) {
//                String productId_raw = request.getParameter("productId");
//                try {
//                    int productId = Integer.parseInt(productId_raw);
//                    Cookie cookie = new Cookie("WishList", "");
//
//                    boolean danhDauTonTaiCookie = false;
//                    for (Cookie c : cookies) {
//                        if (c.getName().equals("WishList")) {
//                            cookie = c;
//                            danhDauTonTaiCookie = true;
//                            break;
//                        }
//                    }
//
//                    int soLuongSanPhamWishList = 0;
//
//                    if (danhDauTonTaiCookie) {
//                        String[] listProductWishListByAccounts = cookie.getValue().split("\\&");
////                        System.out.println(cookie.getValue());
//
//                        // userId va danh sach wish list cua user do
//                        Map<String, String> mapProductListByAccount = new LinkedHashMap<>();
//
//                        // bien nay dung de luu tru list product Id theo account
//                        String[] id_raw = "".split("\\*");
//
//                        boolean danhDauTonTaiAccountTrongCookie = false;
//
//                        // loc qua mang cac account id tuong ung kem theo danh sach product id
//                        for (String item : listProductWishListByAccounts) {
//                            String[] data = item.split("\\-");
//                            String accountId = data[0];
//                            String listProductId = data[1];
//
//                            if (account.getUserId() == Integer.parseInt(accountId)) {
//                                id_raw = listProductId.split("\\*");
//                                danhDauTonTaiAccountTrongCookie = true;
//                            }
//                            mapProductListByAccount.put(accountId, listProductId);
//                        }
//
//                        if (danhDauTonTaiAccountTrongCookie == true) {
//                            boolean danhDauTonTaiProduct = false;
//
//                            for (String id : id_raw) {
//                                if (id.equalsIgnoreCase(productId_raw)) {
//                                    danhDauTonTaiProduct = true;
//                                }
//                            }
//
//                            // san pham chua co trong wish list thi them moi
//                            if (danhDauTonTaiProduct == false) {
//                                if (mapProductListByAccount.containsKey(account.getUserId() + "")) {
//                                    String listProductId_moi = mapProductListByAccount.get(account.getUserId() + "");
//                                    listProductId_moi += "*" + productId_raw;
//                                    mapProductListByAccount.replace(account.getUserId() + "", listProductId_moi);
//                                }
//                            }
//
//                            soLuongSanPhamWishList = mapProductListByAccount.get(account.getUserId() + "").split("\\*").length;
//                        }
//
//                        // hop nhat map thanh cookie
//                        String result = "";
//                        for (Map.Entry<String, String> entry : mapProductListByAccount.entrySet()) {
//                            result += entry.getKey() + "-" + entry.getValue() + "&";
//                        }
//
//                        if (danhDauTonTaiAccountTrongCookie == false) {
//                            result += account.getUserId() + "-" + productId_raw + "&";
//                            soLuongSanPhamWishList = 1;
//                        }
//                        
//                        result = result.substring(0, result.length() - 1);
//
//                        System.out.println("result: " + result);
//
//                        cookie.setValue(result);
//                        // chua ton tai cookie thi tao moi
//                    } else {
//                        cookie.setValue(account.getUserId() + "-" + productId_raw);
//                        // chua ton tai cookie thi so luong san pham trong wishlist se la 1
//                        soLuongSanPhamWishList = 1;
//                    }
//
//                    cookie.setMaxAge(60 * 60 * 24 * 30);
//                    response.addCookie(cookie);
//
//                    // dua ra ket qua la so luong san pham co san trong wishlist de hien thi
//                    print.write(soLuongSanPhamWishList + "");
//
//                } catch (Exception e) {
//                    System.out.println("loi chuyen doi so: wishlistservlet: " + e);
//                }
//            } else {
//                Cookie cookie = new Cookie("WishList", "");
//
//                boolean danhDauTonTaiCookie = false;
//                for (Cookie c : cookies) {
//                    if (c.getName().equals("WishList")) {
//                        cookie = c;
//                        danhDauTonTaiCookie = true;
//                        break;
//                    }
//                }
//                if (action != null && action.equalsIgnoreCase("deleteWishList")) {
//                    String productIdDelete = request.getParameter("productId");
//                    Locale vietnamLocale = new Locale("vi", "VN");
//
//                    String[] listProductWishListByAccounts = cookie.getValue().split("\\&");
//
//                    // userId va danh sach wish list cua user do
//                    Map<String, String> mapProductListByAccount = new LinkedHashMap<>();
//
//                    // bien nay dung de luu tru list product Id theo account cu the
//                    String ids_raw = "";
//
//                    // loc qua mang cac account id tuong ung kem theo danh sach product id
//                    for (String item : listProductWishListByAccounts) {
//                        String[] data = item.split("\\-");
//                        String accountId = data[0];
//                        String listProductId = data[1];
//
//                        if (account.getUserId() == Integer.parseInt(accountId)) {
//                            ids_raw = listProductId;
//                        }
//                        mapProductListByAccount.put(accountId, listProductId);
//                    }
//
//                    String[] productIds_raw = ids_raw.split("\\*");
//
//                    String cookieValueSauKhiDelete = "";
//
//                    String resultHtml = "";
//                    for (String id : productIds_raw) {
//                        if (!id.equalsIgnoreCase(productIdDelete)) {
//                            cookieValueSauKhiDelete += id + "*";
//
//                            Product product = pd.getProductById(Integer.parseInt(id));
//                            String status = "";
//                            if (pd.getQuantityStockByProductId(Integer.parseInt(id)) > 0) {
//                                status = "In stock";
//                            } else {
//                                status = "Sold out";
//                            }
//
//                            resultHtml += "<tr>\n"
//                                    + "                                                                                        <td>\n"
//                                    + "                                                                                            <div onclick=\"confirmDeleteWishList(" + product.getProductId() + ")\" class=\"delete\">\n"
//                                    + "                                                                                                <a href=\"javascript:void(0)\" title=\"\"><img src=\"images/icons/delete.png\" alt=\"\"></a>\n"
//                                    + "                                                                                            </div>\n"
//                                    + "                                                                                            <div class=\"product\">\n"
//                                    + "                                                                                                <div class=\"image\">\n"
//                                    + "                                                                                                    <img src=\"" + product.getGallery().get(0) + "\" alt=\"\">\n"
//                                    + "                                                                                                </div>\n"
//                                    + "                                                                                                <div class=\"name\">\n"
//                                    + "                                                                                                    " + product.getProductName() + "\n"
//                                    + "                                                                                                </div>\n"
//                                    + "                                                                                            </div>\n"
//                                    + "                                                                                        </td>\n"
//                                    + "                                                                                        <td>\n"
//                                    + "                                                                                            <span class=\"sale\">" + formatCurrency(product.getMinPrice(), vietnamLocale) + "</span>\n"
//                                    + "                                                                                        </td>\n"
//                                    + "                                                                                        <td>\n"
//                                    + "                                                                                            <div class=\"status-product\">\n"
//                                    + "                                                                                                    <span>" + status + "</span>\n"
//                                    + "                                                                                            </div>\n"
//                                    + "                                                                                        </td>\n"
//                                    + "                                                                                        <td>\n"
//                                    + "                                                                                            <div class=\"add-cart\">\n"
//                                    + "                                                                                                <a href=\"productdetail?option=common&productId=" + product.getProductId() + "\" title=\"\">\n"
//                                    + "                                                                                                    <img src=\"images/icons/add-cart.png\" alt=\"\">Add to Cart\n"
//                                    + "                                                                                                </a>\n"
//                                    + "                                                                                            </div>\n"
//                                    + "                                                                                        </td>\n"
//                                    + "                                                                                    </tr>\n";
//                        }
//                    }
//
//                    cookieValueSauKhiDelete = cookieValueSauKhiDelete.substring(0, cookieValueSauKhiDelete.length() - 1);
//
//                    mapProductListByAccount.replace(account.getUserId() + "", cookieValueSauKhiDelete);
//
//                    // hop nhat map thanh cookie
//                    String result = "";
//                    for (Map.Entry<String, String> entry : mapProductListByAccount.entrySet()) {
//                        result += entry.getKey() + "-" + entry.getValue() + "&";
//                    }
//                    result = result.substring(0, result.length() - 1);
//
//                    cookie.setValue(result);
//
//                    cookie.setValue(result);
//
//                    cookie.setMaxAge(60 * 60 * 24 * 30);
//                    response.addCookie(cookie);
//
//                    int quantityProductWishList = (int) session.getAttribute("quantityProductWishList") - 1;
//                    session.setAttribute("quantityProductWishList", quantityProductWishList);
//
//                    print.write(resultHtml);
//
//                } else {
//                    Map<Product, Integer> map = new LinkedHashMap<>();
//
//                    int quantityProductWishList = 0;
//                    if (danhDauTonTaiCookie) {
//
//                        String[] listProductWishListByAccounts = cookie.getValue().split("\\&");
//
//                        // bien nay dung de luu tru list product Id theo account
//                        String productsIdByAccount = "";
//
//                        // loc qua mang cac account id tuong ung kem theo danh sach product id
//                        for (String item : listProductWishListByAccounts) {
//                            String[] data = item.split("\\-");
//                            String accountId = data[0];
//                            String listProductId = data[1];
//
//                            if (account.getUserId() == Integer.parseInt(accountId)) {
//                                productsIdByAccount = listProductId;
//                                break;
//                            }
//                        }
//
//                        String[] productId_raw = productsIdByAccount.split("\\*");
//
//                        quantityProductWishList = productId_raw.length;
//
//                        try {
//                            for (String id_raw : productId_raw) {
//                                int productId = Integer.parseInt(id_raw);
//                                Product product = pd.getProductById(productId);
//                                int quantityStock = pd.getQuantityStockByProductId(productId);
//                                map.put(product, quantityStock);
//                            }
//
//                        } catch (NumberFormatException e) {
//                            System.out.println("loi chuyen doi so product wish list: " + e);
//                        }
//                    }
//                    session.setAttribute("quantityProductWishList", quantityProductWishList);
//                    session.setAttribute("listWishList", map);
//                    request.getRequestDispatcher("views/user/item-page/wishlist.jsp").forward(request, response);
//                }
//            }
//        } else {
//            // nguoi dung chua dang nhap tai khoan, len se khong the xu dung duoc wishlist
//            print.write("false");
//        }
//
//    }
//}
//


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import model.Product;
import model.User;


/**
 *
 * @author PC
 */
public class WishListServlet extends HttpServlet {


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
        HttpSession session = request.getSession();

        session.setAttribute("abc", "abc");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String nameCookieForWishList = "WishList";
        String nameCookieForQuantityProductWishList = "quantityProductWishList";

        PrintWriter print = response.getWriter();

        User account = (User) session.getAttribute("account");

        if (account != null) {
            // lay ve hanh dong ma minh muon thao tac voi wishlist
            String action = request.getParameter("action");

            // hanh dong them san pham vao danh sach yeu thich
            if (action != null && action.equalsIgnoreCase("addwishlist")) {

                addWishList(request, response, account, nameCookieForWishList);

            } else {

                if (action != null && action.equalsIgnoreCase("deleteWishList")) {

                    deleteWishList(request, response, account, nameCookieForQuantityProductWishList, nameCookieForWishList);

                } else {

                    viewWishList(request, response, account, nameCookieForQuantityProductWishList, nameCookieForWishList);
                }

            }
        } else {
            // nguoi dung chua dang nhap tai khoan, len se khong the xu dung duoc wishlist
            print.write("false");
        }
    }

    private static void addWishListDaTonTai(HttpServletResponse response, User account, Cookie cookie, String productIdRaw) {

        int soLuongSanPhamWishList = 0;

        String[] listProductWishListByAccounts = cookie.getValue().split("\\&");

        // userId va danh sach wish list cua user do
        Map<String, String> mapProductListByAccount = new LinkedHashMap<>();

        // bien nay dung de luu tru list product Id theo account
        String[] idRaw = "".split("\\*");

        boolean danhDauTonTaiAccountTrongCookie = false;

        // loc qua mang cac account id tuong ung kem theo danh sach product id
        for (String item : listProductWishListByAccounts) {
            String[] data = item.split("\\-");
            String accountId = data[0];
            String listProductId = data[1];

            if (account.getUserId() == Integer.parseInt(accountId)) {
                idRaw = listProductId.split("\\*");
                danhDauTonTaiAccountTrongCookie = true;
            }
            mapProductListByAccount.put(accountId, listProductId);
        }

        if (danhDauTonTaiAccountTrongCookie) {
            boolean danhDauTonTaiProduct = false;

            for (String id : idRaw) {
                if (id.equalsIgnoreCase(productIdRaw)) {
                    danhDauTonTaiProduct = true;
                }
            }

            // san pham chua co trong wish list thi them moi
            if (!danhDauTonTaiProduct && mapProductListByAccount.containsKey(account.getUserId() + "")) {
                String listProductIdMoi = mapProductListByAccount.get(account.getUserId() + "");
                listProductIdMoi += "*" + productIdRaw;
                mapProductListByAccount.replace(account.getUserId() + "", listProductIdMoi);
            }

            soLuongSanPhamWishList = mapProductListByAccount.get(account.getUserId() + "").split("\\*").length;
        }

        // hop nhat map thanh cookie
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : mapProductListByAccount.entrySet()) {
            result.append(entry.getKey() + "-" + entry.getValue() + "&");
        }

        if (!danhDauTonTaiAccountTrongCookie) {
            result.append(account.getUserId() + "-" + productIdRaw + "&");
            soLuongSanPhamWishList = 1;
        }

        result = new StringBuilder(result.toString().substring(0, result.length() - 1));

        System.out.println("result: " + result.toString());
        

        cookie.setValue(result.toString());

        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);

        // dua ra ket qua la so luong san pham co san trong wishlist de hien thi
        try {
            PrintWriter print = response.getWriter();
            print.write(soLuongSanPhamWishList + "");
        } catch (Exception e) {
            System.out.println("print loi: " + e);

        }

    }

    private static void addWishList(HttpServletRequest request, HttpServletResponse response, User account, String nameCookieForWishList) {

        String productIdRaw = request.getParameter("productId");
        try {

            Cookie[] cookies = request.getCookies();
            Cookie cookie = new Cookie(nameCookieForWishList, "");

            boolean danhDauTonTaiCookie = false;
            for (Cookie c : cookies) {
                if (c.getName().equals(nameCookieForWishList)) {
                    cookie = c;
                    danhDauTonTaiCookie = true;
                    break;
                }
            }

            int soLuongSanPhamWishList = 0;

            if (danhDauTonTaiCookie) {

                addWishListDaTonTai(response, account, cookie, productIdRaw);

            } else {
                cookie.setValue(account.getUserId() + "-" + productIdRaw);
                // chua ton tai cookie thi so luong san pham trong wishlist se la 1
                soLuongSanPhamWishList = 1;

                cookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(cookie);

                // dua ra ket qua la so luong san pham co san trong wishlist de hien thi
                PrintWriter print = response.getWriter();
                print.write(soLuongSanPhamWishList + "");
            }

        } catch (Exception e) {
            System.out.println("loi chuyen doi so: wishlistservlet: " + e);
        }
    }

    private static void deleteWishList(HttpServletRequest request, HttpServletResponse response, User account, String nameCookieForQuantityProductWishList, String nameCookieForWishList) {

        HttpSession session = request.getSession();

        String productIdDelete = request.getParameter("productId");
        Locale vietnamLocale = new Locale("vi", "VN");

        ProductDAO pd = new ProductDAO();

        Cookie[] cookies = request.getCookies();

        Cookie cookie = new Cookie(nameCookieForWishList, "");

        for (Cookie c : cookies) {
            if (c.getName().equals(nameCookieForWishList)) {
                cookie = c;
                break;
            }
        }

        String[] listProductWishListByAccounts = cookie.getValue().split("\\&");

        // userId va danh sach wish list cua user do
        Map<String, String> mapProductListByAccount = new LinkedHashMap<>();

        // bien nay dung de luu tru list product Id theo account cu the
        String idsRaw = "";

        // loc qua mang cac account id tuong ung kem theo danh sach product id
        for (String item : listProductWishListByAccounts) {
            String[] data = item.split("\\-");
            String accountId = data[0];
            String listProductId = data[1];

            if (account.getUserId() == Integer.parseInt(accountId)) {
                idsRaw = listProductId;
            }
            mapProductListByAccount.put(accountId, listProductId);
        }

        String[] productIdsRaw = idsRaw.split("\\*");

        StringBuilder cookieValueSauKhiDelete = new StringBuilder();

        String resultHtml = "";
        for (String id : productIdsRaw) {
            if (!id.equalsIgnoreCase(productIdDelete)) {
                cookieValueSauKhiDelete.append(id + "*");

                Product product = pd.getProductById(Integer.parseInt(id));
                String status = "";
                if (pd.getQuantityStockByProductId(Integer.parseInt(id)) > 0) {
                    status = "In stock";
                } else {
                    status = "Sold out";
                }

                resultHtml += "<tr>\n"
                        + "<td>\n"
                        + "                                                                                            <div onclick=\"confirmDeleteWishList(" + product.getProductId() + ")\" class=\"delete\">\n"
                        + "                                                                                                <a href=\"javascript:void(0)\" title=\"\"><img src=\"images/icons/delete.png\" alt=\"\"></a>\n"
                        + "</div>\n"
                        + "                                                                                            <div class=\"product\">\n"
                        + "                                                                                                <div class=\"image\">\n"
                        + "                                                                                                    <img src=\"" + product.getGallery().get(0) + "\" alt=\"\">\n"
                        + "                                                                                                </div>\n"
                        + "                                                                                                <div class=\"name\">\n"
                        + "                                                                                                    " + product.getProductName() + "\n"
                        + "                                                                                                </div>\n"
                        + "</div>\n"
                        + "</td>\n"
                        + "<td>\n"
                        + "                                                                                            <span class=\"sale\">" + formatCurrency(product.getMinPrice(), vietnamLocale) + "</span>\n"
                        + "</td>\n"
                        + "                                                                                        <td>\n"
                        + "                                                                                            <div class=\"status-product\">\n"
                        + "                                                                                                    <span>" + status + "</span>\n"
                        + "                                                                                            </div>\n"
                        + "                                                                                        </td>\n"
                        + "                                                                                        <td>\n"
                        + "                                                                                            <div class=\"add-cart\">\n"
                        + "                                                                                                <a href=\"productdetail?option=common&productId=" + product.getProductId() + "\" title=\"\">\n"
                        + "                                                                                                    <img src=\"images/icons/add-cart.png\" alt=\"\">Add to Cart\n"
                        + "                                                                                                </a>\n"
                        + "                                                                                            </div>\n"
                        + "                                                                                        </td>\n"
                        + "                                                                                    </tr>\n";
            }
        }

        cookieValueSauKhiDelete = new StringBuilder(cookieValueSauKhiDelete.substring(0, cookieValueSauKhiDelete.length() - 1));

        mapProductListByAccount.replace(account.getUserId() + "", cookieValueSauKhiDelete.toString());

        // hop nhat map thanh cookie
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : mapProductListByAccount.entrySet()) {
            result.append(entry.getKey() + "-" + entry.getValue() + "&");
        }
        result = new StringBuilder(result.substring(0, result.length() - 1));

        cookie.setValue(result.toString());

        cookie.setValue(result.toString());

        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);

        int quantityProductWishList = (int) session.getAttribute(nameCookieForQuantityProductWishList) - 1;
        session.setAttribute(nameCookieForQuantityProductWishList, quantityProductWishList);

        try {
            PrintWriter print = response.getWriter();
            print.write(resultHtml);
        } catch (Exception e) {
            System.out.println("print loi: " + e);

        }
    }

    private static void viewWishList(HttpServletRequest request, HttpServletResponse response, User account, String nameCookieForQuantityProductWishList, String nameCookieForWishList) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ProductDAO pd = new ProductDAO();

        Cookie[] cookies = request.getCookies();

        Map<Product, Integer> map = new LinkedHashMap<>();

        Cookie cookie = new Cookie(nameCookieForWishList, "");

        boolean danhDauTonTaiCookie = false;
        for (Cookie c : cookies) {
            if (c.getName().equals(nameCookieForWishList)) {
                cookie = c;
                danhDauTonTaiCookie = true;
                break;
            }
        }

        int quantityProductWishList = 0;
        if (danhDauTonTaiCookie) {

            String[] listProductWishListByAccounts = cookie.getValue().split("\\&");

            // bien nay dung de luu tru list product Id theo account
            String productsIdByAccount = "";

            // loc qua mang cac account id tuong ung kem theo danh sach product id
            for (String item : listProductWishListByAccounts) {
                String[] data = item.split("\\-");
                String accountId = data[0];
                String listProductId = data[1];

                if (account.getUserId() == Integer.parseInt(accountId)) {
                    productsIdByAccount = listProductId;
                    break;
                }
            }

            String[] productIdRaw = productsIdByAccount.split("\\*");

            quantityProductWishList = productIdRaw.length;

            try {
                for (String idRaw : productIdRaw) {
                    int productId = Integer.parseInt(idRaw);
                    Product product = pd.getProductById(productId);
                    int quantityStock = pd.getQuantityStockByProductId(productId);
                    map.put(product, quantityStock);
                }

            } catch (NumberFormatException e) {
                System.out.println("loi chuyen doi so product wish list: " + e);
            }
        }
        session.setAttribute(nameCookieForQuantityProductWishList, quantityProductWishList);
        session.setAttribute("listWishList", map);
        request.getRequestDispatcher("views/user/item-page/wishlist.jsp").forward(request, response);
    }
}

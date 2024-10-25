/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CartAccountDAO;
import dal.OrderDetailDAO;
import dal.OrdersDAO;
import dal.PaymentDAO;
import dal.ProductDetailDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import model.Cart;
import model.Orders;
import model.Payment;
import model.ProductDetail;
import model.User;
/**
 *
 * @author PC
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        OrdersDAO od = new OrdersDAO();
        OrderDetailDAO odd = new OrderDetailDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();

        // thong tin dia chi nguoi nhan hang
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String note = request.getParameter("note");

        String paymentId = request.getParameter("paymentId");

//        String amount = String.format("%.0f", request.getParameter("amount"));
        // gennerate ra ma don hang ngau nhien gom 8 so
        String orderId_raw = getRandomNumber(8);
        while (od.isOrder(orderId_raw)) {
            orderId_raw = getRandomNumber(8);
        }

        int orderId = Integer.parseInt(orderId_raw);
        
        request.setAttribute("orderId", orderId);
        
        // tu dong gan order danh cho 1 sale bat ki
        // business: tu dong gan cho sale co ti le ra don thap nhat trong 7 ngay gan day
        int assignSaleId = od.assignSale();

//         insert order vao database voi trang thai submited
        User account = (User) session.getAttribute("account");

        // kiem tra xem nguoi dung mua hang da dang nhap hay chua, neu da dang nhap thi mua bang account
        // chua dang nhap thi mua bang role anonymous
        if (account == null) {
            int anonymousId = ud.getUserIdAnonymous();

            if (anonymousId == -1) {
                ud.insertUserAnonymous("anonymous", "anonymous@gmai.com");
            }

            account = ud.getUserById(anonymousId);
        }

        User userSale = ud.getUserById(assignSaleId);

        PaymentDAO pd = new PaymentDAO();
//        
        Payment payment = pd.getPaymentById(Integer.parseInt(paymentId));

        Cart cartContact = (Cart) session.getAttribute("cartContact");

        // kiem tra xem cac san pham muon mua trong gio hang con stock hay khong
        boolean isError = false;

        Map<Integer, String> mapError = new LinkedHashMap<>();

        for (int i = 0; i < cartContact.getListProductDetails().size(); i++) {
            int productDetailId = cartContact.getListProductDetails().get(i).getProductDetailId();

            int quantityStock = pdd.getQuantityStockByProductDetailId(productDetailId);

            int soLuongMuonMua = cartContact.getSoLuong().get(i);

            System.out.println("productDetailId: " + productDetailId + "soLuongMuonMua: " + soLuongMuonMua + " quantityStock: " + quantityStock);
            
            if (soLuongMuonMua > quantityStock) {
                isError = true;
                String noteError = "Invalid quantity(available quantity: " + quantityStock + " )";
                mapError.put(productDetailId, noteError);
            }
        }

        // tat ca deu con stock
        if (!isError) {
            System.out.println("aaaaaaaaaaaaaa");
            
//            // khoi tao new order voi trang thai submited
            od.insertOrder(orderId, account.getUserId(), userSale.getUserId(), payment.getPaymentId(), 0, email, fullName, address, note, phoneNumber, cartContact.getTotalPriceAfterDiscount(), 0);

            // insert order detail
            odd.insertOrderDetail(cartContact, orderId);

            // xoa nhung san pham vua mua khoi cart
            for (int i = 0; i < cartContact.getListProductDetails().size(); i++) {
                deleteFromCart(request, response, cartContact.getListProductDetails().get(i).getProductDetailId());
            }
            
            // xy li khau thanh toan
            
            if(payment.getPaymentName().equalsIgnoreCase("VNPay")){
                request.getRequestDispatcher("vnpayrequest").forward(request, response);
            }else{
                
            }
        } else {
            // ton tai it nhat 1 thang kh du so luong
            session.setAttribute("mapError", mapError);
            response.sendRedirect("cartdetail");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void deleteFromCart(HttpServletRequest request, HttpServletResponse response, int productDetailId) {
        HttpSession session = request.getSession();
        ProductDetailDAO pdd = new ProductDetailDAO();
        CartAccountDAO cad = new CartAccountDAO();

        List<ProductDetail> listProductDetail = new ArrayList<>();
        List<Integer> listSoLuong = new ArrayList<>();

        try {

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
        } catch (Exception e) {
            System.out.println("loi chuyen doi so paymentServlet: " + e);
        }
    }
}

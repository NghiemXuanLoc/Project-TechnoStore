/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.PaymentDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Payment;
import model.ProductDetail;

/**
 *
 * @author PC
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDetailDAO pdd = new ProductDetailDAO();

        Cart listCart = (Cart) session.getAttribute("cart");

        List<ProductDetail> listProductDetail = listCart.getListProductDetails();
        List<Integer> listQuantity = listCart.getSoLuong();

        Map<Integer, Integer> mapCart = new LinkedHashMap<>();

        for (int i = 0; i < listQuantity.size(); i++) {
            mapCart.put(listProductDetail.get(i).getProductDetailId(), listQuantity.get(i));
        }

        // lay ve nhung thang productDetailId da duoc chon
        String[] checked = request.getParameterValues("checked");

        // danh dau xem co san pham nao co so luong vuot qua so luong co san khong
        String error = null;

        Map<Integer, String> mapError = new LinkedHashMap<>();

        List<ProductDetail> listCartContact = new ArrayList<>();
        List<Integer> listQuantityContact = new ArrayList<>();

        for (String nhungThangDaChon : checked) {
            int id = Integer.parseInt(nhungThangDaChon);

            int quantityStock = pdd.getQuantityStockByProductDetailId(id);

            if (mapCart.get(id) <= 0 || quantityStock < mapCart.get(id)) {
                error = "Invalid quantity(available quantity: " + quantityStock + " )";
                mapError.put(id, error);
            } else {
                listCartContact.add(pdd.getProductDetailById(id));
                listQuantityContact.add(mapCart.get(id));
            }
        }

        Set<Integer> setChecked = new HashSet<>();

        for (String x : checked) {
            int id = Integer.parseInt(x);
            setChecked.add(id);
        }

        if (error == null) {
            Cart cartContact = new Cart(listCartContact, listQuantityContact);
            
            cartContact.setTotalPriceBeforeDiscount();
            cartContact.setTotalPriceAfterDiscount();

            session.setAttribute("cartContact", cartContact);

            session.setAttribute("setChecked", setChecked);

            session.setAttribute("mapError", mapError);

            response.sendRedirect("checkout");

//            request.getRequestDispatcher("views/user/item-page/checkout.jsp").forward(request, response);
        } else {
            // co san pham khong du so luong, quay lai trang  cart detail cho nguoi dung chon lai

            session.setAttribute("mapError", mapError);
            session.setAttribute("setChecked", setChecked);
            response.sendRedirect("cartdetail");
        }

        System.out.println("------------------");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        PaymentDAO pd = new PaymentDAO();
        
        List<Payment> listPayment = pd.getPaymentAll();
        
        session.setAttribute("listPayment", listPayment);
        
        
        request.getRequestDispatcher("views/user/item-page/checkout.jsp").forward(request, response);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import apivnpay.VnpayConfig;
import constant.Iconstant;
import dal.OrderDetailDAO;
import dal.OrdersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import model.Cart;
import model.Orders;
import model.ProductDetail;
import model.SendEmail;
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name = "CartCompletionServlet", urlPatterns = {"/cartcompletion"})
public class CartCompletionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrdersDAO od = new OrdersDAO();
        OrderDetailDAO odd = new OrderDetailDAO();
        HttpSession session = request.getSession();
        SendEmail se = new SendEmail();

        Cart cartContact = odd.getOrderDetailAllByOrderId(Integer.parseInt(request.getParameter("vnp_TxnRef")));

        User account = (User) session.getAttribute("account");

        Orders order = od.getOrderById(Integer.parseInt(request.getParameter("vnp_TxnRef")));

        System.out.println(order);

        String toEmail = "";

        if (account != null) {
            toEmail = account.getEmail();
        } else {
            account = order.getUser();
            toEmail = order.getEmail();
        }

        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = VnpayConfig.hashAllFields(fields);

        // kiem tra tinh toan ven cua du lieu he thong
        if (signValue.equals(vnp_SecureHash)) {
            boolean checkOrderId = od.isOrder(request.getParameter("vnp_TxnRef")); // Giá trị của vnp_TxnRef tồn tại trong CSDL của merchant
            boolean checkAmount = true; //Kiểm tra số tiền thanh toán do VNPAY phản hồi(vnp_Amount/100) với số tiền của đơn hàng merchant tạo thanh toán: giả sử số tiền kiểm tra là đúng.
            boolean checkOrderStatus = od.checkTrangThaiGiaoDichOrder(request.getParameter("vnp_TxnRef")); // Giả sử PaymnentStatus = 0 (pending) là trạng thái thanh toán của giao dịch khởi tạo chưa có IPN.

//            boolean checkOrderId = true; // Giá trị của vnp_TxnRef tồn tại trong CSDL của merchant
//            boolean checkAmount = true; //Kiểm tra số tiền thanh toán do VNPAY phản hồi(vnp_Amount/100) với số tiền của đơn hàng merchant tạo thanh toán: giả sử số tiền kiểm tra là đúng.
//            boolean checkOrderStatus = true; // Giả sử PaymnentStatus = 0 (pending) là trạng thái thanh toán của giao dịch khởi tạo chưa có IPN.
            if (checkOrderId) {
                if (checkAmount) {
                    if (checkOrderStatus) {
                        if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
                            //Xử lý/Cập nhật tình trạng giao dịch thanh toán "Thành công"
                            od.updateStatusThanhToanOrder(Integer.parseInt(request.getParameter("vnp_TxnRef")));
                            request.setAttribute("mess", "GD Thanh cong");
                            se.sendEmailOrder(toEmail, "Order confirmation", convertContentSendMail(cartContact, order, account, "Thanh toán thành công"));

                        } else {
                            //Xử lý/Cập nhật tình trạng giao dịch thanh toán "Không thành công"
                            request.setAttribute("orderId", order.getOrderId());
                            request.setAttribute("mess", "Chưa thanh toán/Thanh toán không thành công");

//                            od.updateOrderStatus(Integer.parseInt(request.getParameter("vnp_TxnRef")), 1);
                            response.sendRedirect("home");
                            return;
                        }
                    } else {
                        //Trạng thái giao dịch đã được cập nhật trước đó
                        request.setAttribute("mess", "Order already confirmed");
                    }
                } else {
                    //Số tiền không trùng khớp
                    request.setAttribute("mess", "Invalid Amount");
                }
            } else {
                //Mã giao dịch không tồn tại
                request.setAttribute("mess", "Order not Found");
            }

            request.getRequestDispatcher("views/user/item-page/cartcompletion.jsp").forward(request, response);
        } else {
            // Sai checksum
            request.setAttribute("mess", "Invalid Checksum");
            request.getRequestDispatcher("views/user/item-page/page404.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private String convertContentSendMail(Cart cart, Orders order, User nguoiDatHang, String trangThaiThanhToan) {

        String htmlNguoiDat
                = "<p><strong>Tên khách hàng:</strong> " + (nguoiDatHang.getRole().getRoleId() != 5 ? nguoiDatHang.getFullName() : "") + "</p>\n"
                + "        <p><strong>Email:</strong> " + (nguoiDatHang.getRole().getRoleId() != 5 ? nguoiDatHang.getEmail() : "") + "\n"
                + "        <p><strong>Số điện thoại:</strong> " + (nguoiDatHang.getRole().getRoleId() != 5 ? nguoiDatHang.getPhoneNumber() : "") + "</p>";

        String htmlNguoiNhan
                = "<p><strong>Tên người nhận hàng:</strong> " + order.getFullName() + "</p>\n"
                + "        <p>\n"
                + "          <strong>Email:</strong> " + order.getEmail() + "\n"
                + "        </p>\n"
                + "        <p><strong>Số điện thoại người nhận:</strong> " + order.getPhoneNumber() + "</p>";

        String htmlThanhToan
                = "<p><strong>Phương thức thanh toán:</strong> " + order.getPayment().getPaymentName() + "</p>\n"
                + "        <p><strong>Tình trạng thanh toán:</strong> " + trangThaiThanhToan + "</p>\n";

        String htmlProducts = "";

        for (int i = 0; i < cart.getListProductDetails().size(); i++) {
            ProductDetail pd = cart.getListProductDetails().get(i);
            htmlProducts
                    += "          <tr>\n"
                    + "              <td>" + pd.getProduct().getProductName() + "</td>\n"
                    + "              <td>" + pd.getColor().getColorName() + "</td>\n"
                    + "              <td>" + pd.getMemory().getMemorySize() + " GB</td>\n"
                    + "              <td>" + cart.getSoLuong().get(i) + "</td>\n"
                    + "              <td>" + Iconstant.formatCurrency(pd.getPriceSale()) + "</td>\n"
                    + "              <td>" + Iconstant.formatCurrency(pd.getPriceSale() * cart.getSoLuong().get(i)) + "</td>\n"
                    + "              <td>"
                    + "                 <form action=\"http://localhost:9999/TechStore/feedback\" method=\"get\">\n"
                    + "                 <input type=\"hidden\" name=\"productId\" value=\"" + pd.getProduct().getProductId() + "\" />\n"
                    + "     <input type=\"hidden\" name=\"colorId\" value=\"" + pd.getColor().getColorId() + "\">\n"
                    + "    <input type=\"hidden\" name=\"memoryId\" value=\"" + pd.getMemory().getMemoryId() + "\">\n"
                    + "    <input type=\"hidden\" name=\"quantity\" value=\"" + cart.getSoLuong().get(i) + "\">"
                    + "     <input type=\"hidden\" name=\"orderId\" value=\"" + order.getOrderId() + "\">"
                    + "                 <button>Click to Feedback</button>\n"
                    + "                 </form>"
                    + "             </td>\n"
                    + "            </tr>\n";
        }

        String htmls = "<!DOCTYPE html>\n"
                + "<html lang=\"vi\">\n"
                + "  <head>\n"
                + "    <meta charset=\"UTF-8\" />\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n"
                + "    <title>Ghi chú hoàn tất giỏ hàng</title>\n"
                + "    <style>\n"
                + "      body {\n"
                + "        font-family: Arial, sans-serif;\n"
                + "        margin: 20px;\n"
                + "      }\n"
                + "      .container {\n"
                + "        max-width: 800px;\n"
                + "        margin: auto;\n"
                + "        border: 1px solid #ddd;\n"
                + "        padding: 20px;\n"
                + "        border-radius: 5px;\n"
                + "      }\n"
                + "      .section {\n"
                + "        margin-bottom: 20px;\n"
                + "      }\n"
                + "      .section h2 {\n"
                + "        border-bottom: 2px solid #333;\n"
                + "        padding-bottom: 5px;\n"
                + "        margin-bottom: 10px;\n"
                + "      }\n"
                + "      .section p {\n"
                + "        margin: 5px 0;\n"
                + "      }\n"
                + "      .product-list {\n"
                + "        width: 100%;\n"
                + "        border-collapse: collapse;\n"
                + "      }\n"
                + "      .product-list th,\n"
                + "      .product-list td {\n"
                + "        border: 1px solid #ddd;\n"
                + "        padding: 8px;\n"
                + "        text-align: left;\n"
                + "      }\n"
                + "      .product-list th {\n"
                + "        background-color: #f4f4f4;\n"
                + "      }\n"
                + "      .total {\n"
                + "        text-align: right;\n"
                + "        font-weight: bold;\n"
                + "      }\n"
                + "      .store-name {\n"
                + "        text-align: center;\n"
                + "        margin-bottom: 20px;\n"
                + "        font-size: 24px;\n"
                + "        font-weight: bold;\n"
                + "      }\n"
                + "    </style>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <div class=\"container\">\n"
                + "      <div class=\"store-name\"><h2>TechnoStore</h2></div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Xác nhận đơn hàng</h2>\n"
                + "        <p><strong>Số đơn hàng:</strong> #" + order.getOrderId() + "</p>\n"
                + "        <p><strong>Ngày đặt hàng:</strong> " + order.getOrderDate() + "</p>\n"
                + "      </div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Chi tiết khách hàng</h2>\n"
                + htmlNguoiDat
                + "      </div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Chi tiết người nhận hàng</h2>\n"
                + htmlNguoiNhan
                + "      </div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Chi tiết vận chuyển</h2>\n"
                + "        <p>\n"
                + "          <strong>Địa chỉ giao hàng:</strong> " + order.getAddress() + "\n"
                + "        </p>\n"
                + "      </div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Chi tiết thanh toán</h2>\n"
                + htmlThanhToan
                + "      </div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Danh sách sản phẩm</h2>\n"
                + "        <table class=\"product-list\">\n"
                + "          <thead>\n"
                + "            <tr>\n"
                + "              <th>Sản phẩm</th>\n"
                + "              <th>Màu</th>\n"
                + "              <th>Bộ nhớ</th>\n"
                + "              <th>Số lượng</th>\n"
                + "              <th>Giá mỗi sản phẩm</th>\n"
                + "              <th>Tổng giá</th>"
                + "              <th>Feedback</th>"
                + "            </tr>\n"
                + "          </thead>\n"
                + "          <tbody>\n"
                + htmlProducts
                + "            \n"
                + "          </tbody>\n"
                + "          <tfoot>\n"
                + "            <tr>\n"
                + "              <td colspan=\"6\" class=\"total\">Tổng giá trị đơn hàng</td>\n"
                + "              <td>" + Iconstant.formatCurrency(cart.getTotalPriceAfterDiscount()) + "</td>\n"
                + "            </tr>\n"
                + "          </tfoot>\n"
                + "        </table>\n"
                + "      </div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Ghi chú đặc biệt</h2>\n"
                + "        <p>\n"
                + "          <strong>Ghi chú của khách hàng:</strong> " + order.getNote() + "\n"
                + "        </p>\n"
                + "        <p>\n"
                + "          <strong>Ghi chú của cửa hàng:</strong> Chúng tôi sẽ cố gắng giao hàng\n"
                + "          đúng yêu cầu của quý khách.\n"
                + "        </p>\n"
                + "      </div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Hỗ trợ khách hàng</h2>\n"
                + "        <p><strong>Liên hệ hỗ trợ:</strong> 0337783926 - nghiemxuanloc02@gmail.com</p>\n"
                + "        <p>\n"
                + "          <strong>Chính sách hoàn trả:</strong> Xem chi tiết tại website của\n"
                + "          chúng tôi.\n"
                + "        </p>\n"
                + "      </div>\n"
                + "\n"
                + "      <div class=\"section\">\n"
                + "        <h2>Cảm ơn</h2>\n"
                + "        <p>Cảm ơn quý khách đã mua sắm tại cửa hàng chúng tôi!</p>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "  </body>\n"
                + "</html>";

        return htmls;
    }
}

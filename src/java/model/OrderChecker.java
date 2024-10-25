/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import constant.Iconstant;
import dal.OrdersDAO;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OrderChecker {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Set<Integer> setDonHangBiHuy = new HashSet<>();
    private Set<Integer> setNhacNhoThanhToan = new HashSet<>();

    public void start() {
        // Lên lịch nhiệm vụ kiểm tra đơn hàng mỗi giờ(neu don hang qua 24h chua thanh toan thi don hang se bi huy)
        scheduler.scheduleAtFixedRate(this::checkOrders, 0, 1, TimeUnit.MINUTES);
    }
    

    public void stop() {
        // Tắt ScheduledExecutorService khi không cần thiết
        scheduler.shutdown();
    }

    private void checkOrders() {
        // Lấy danh sách các đơn hàng chưa thanh toán từ cơ sở dữ liệu
        List<Orders> unpaidOrders = getUnpaidOrders();

        LocalDateTime now = LocalDateTime.now();

        for (Orders order : unpaidOrders) {
            LocalDateTime orderDateTime = convertDateToLocalDateTime(order.getOrderDate());

            if (orderDateTime.plusHours(24).isBefore(now)) {
                // Hủy đơn hàng nếu quá 24 giờ mà chưa thanh toán
                cancelOrder(order);
                System.out.println("don hang chua thanh toan: " + order.getOrderId() + " Date: " + orderDateTime);
            } else {
                if (order.getUser().getRole().getRoleId() == 5) {
                    if (!setNhacNhoThanhToan.contains(order.getOrderId())) {
                        nhacNhoThanhToan(order);
                    }
                    setNhacNhoThanhToan.add(order.getOrderId());
                }
            }
        }
    }

    private List<Orders> getUnpaidOrders() {
        // Thực hiện truy vấn cơ sở dữ liệu để lấy danh sách các đơn hàng chưa thanh toán

        List<Orders> chuaThanhToan = new ArrayList<>();
        OrdersDAO od = new OrdersDAO();

        chuaThanhToan = od.getDonHangChuaThanhToan();

        // Trả về danh sách các đối tượng Order chưa thanh toán
        return chuaThanhToan; // Thay thế bằng logic truy vấn thực tế
    }

    private void cancelOrder(Orders order) {
        OrdersDAO od = new OrdersDAO();
        SendEmail se = new SendEmail();

        String htmlContent = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Order Cancelled</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f0f0f0;\n"
                + "            display: flex;\n"
                + "            justify-content: center;\n"
                + "            align-items: center;\n"
                + "            height: 100vh;\n"
                + "            margin: 0;\n"
                + "        }\n"
                + "        .container {\n"
                + "            background-color: #fff;\n"
                + "            padding: 20px;\n"
                + "            border-radius: 5px;\n"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                + "            width: 80%;\n"
                + "            max-width: 600px;\n"
                + "        }\n"
                + "        h1, h2 {\n"
                + "            color: #f44336; /* Màu đỏ */\n"
                + "        }\n"
                + "        p {\n"
                + "            font-size: 18px;\n"
                + "            line-height: 1.6;\n"
                + "        }\n"
                + "        .store-header {\n"
                + "            text-align: center;\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "        .order-info {\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "        .order-info h2 {\n"
                + "            margin-bottom: 10px;\n"
                + "        }\n"
                + "        .order-info p {\n"
                + "            margin: 0;\n"
                + "        }\n"
                + "        .contact-info {\n"
                + "            margin-top: 20px;\n"
                + "            border-top: 1px solid #ccc;\n"
                + "            padding-top: 10px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"store-header\">\n"
                + "            <h1>Techno Store</h1>\n"
                + "        </div>\n"
                + "        <p>Xin chào " + (order.getUser().getRole().getRoleId() != 5 ? order.getUser().getFullName() : order.getFullName()) + ",</p>\n"
                + "\n"
                + "        <p>Chúng tôi rất tiếc thông báo rằng đơn hàng của bạn đã <span style=\"color: red;\">tự động bị hủy</span> do không thanh toán trong vòng 24 giờ.</p>\n"
                + "        \n"
                + "        <div class=\"order-info\">\n"
                + "            <h3>Thông tin đơn hàng</h3>\n"
                + "            <p><strong>Mã đơn hàng:</strong> #" + order.getOrderId() + "</p>\n"
                + "            <p><strong>Ngày đặt hàng:</strong> " + order.getOrderDate() + "</p>\n"
                + "            <p><strong>Tổng tiền:</strong> " + Iconstant.formatCurrency(order.getTotalMoney()) + "</p>\n"
                + "        </div>\n"
                + "\n"
                + "\n"
                + "        <div class=\"contact-info\">\n"
                + "            <p>Nếu bạn có bất kỳ câu hỏi hoặc cần hỗ trợ gì, vui lòng liên hệ với chúng tôi qua địa chỉ email <a href=\"mailto:nghiemxuanloc02@gmail.com\">nghiemxuanloc02@gmail.com</a> hoặc số điện thoại <a href=\"tel:0337783926\">0337783926</a>.</p>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";

        String toEmail = order.getUser().getRole().getRoleId() != 5 ? order.getUser().getEmail() : order.getEmail();

        if (!setDonHangBiHuy.contains(order.getOrderId())) {
            se.sendEmailOrder(toEmail, "NOTICE OF ORDER CANCELLATION", htmlContent);
        }

        setDonHangBiHuy.add(order.getOrderId());

        // Thực hiện cập nhật trong cơ sở dữ liệu
        od.updateOrderStatus(order.getOrderId(), 1);
    }

    private void nhacNhoThanhToan(Orders order) {
        SendEmail se = new SendEmail();
        System.out.println(order.getOrderId());

        String html = "<!DOCTYPE html>\n"
                + "<html lang=\"vi\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            line-height: 1.6;\n"
                + "            margin: 20px;\n"
                + "        }\n"
                + "        .container {\n"
                + "            max-width: 600px;\n"
                + "            margin: auto;\n"
                + "            padding: 20px;\n"
                + "            background: #f9f9f9;\n"
                + "            border: 1px solid #ddd;\n"
                + "            border-radius: 5px;\n"
                + "        }\n"
                + "        .header {\n"
                + "            background-color: orange;\n"
                + "            color: black;\n"
                + "            text-align: center;\n"
                + "            padding: 10px;\n"
                + "            border-radius: 5px 5px 0 0;\n"
                + "        }\n"
                + "        .content {\n"
                + "            margin-top: 20px;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            margin-top: 20px;\n"
                + "            text-align: end;\n"
                + "            font-size: 0.9em;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"header\">\n"
                + "            <h1>Techno Store</h1>\n"
                + "            <h2>Thông báo: Đơn hàng chưa thanh toán</h2>\n"
                + "        </div>\n"
                + "        <div class=\"content\">\n"
                + "            <p>Xin chào " + (order.getUser().getRole().getRoleId() != 5 ? order.getUser().getFullName() : order.getFullName()) + ",</p>\n"
                + "            <p>Chúng tôi gửi đến Quý khách lời nhắc nhở về việc đơn hàng của Quý khách vẫn chưa được thanh toán. Xin vui lòng tham khảo chi tiết đơn hàng dưới đây:</p>\n"
                + "            <ul>\n"
                + "                <li><strong>Mã đơn hàng:</strong> #" + order.getOrderId() + "</li>\n"
                + "                <li><strong>Ngày đặt hàng:</strong> " + Iconstant.formatDate(order.getOrderDate()) + "</li>\n"
                + "                <li><strong>Tổng số tiền cần thanh toán:</strong> " + Iconstant.formatCurrency(order.getTotalMoney()) + "</li>\n"
                + "            </ul>\n"
                + "            <p><strong>Hướng dẫn thanh toán:</strong></p>\n"
                + "            <p>Vui lòng thanh toán số tiền trên trước ngày " + (Iconstant.formatDate(addHoursToDate(order.getOrderDate(), 24))) + " để đảm bảo việc đơn hàng không bị hủy.</p>\n"
                + "            <ul>\n"
                + "                <li><strong>Chuyển khoản ngân hàng:</strong> \n"
                + "                  <form action=\"http://localhost:9999/TechStore/vnpayrequest\" method=\"post\">\n"
                + "                    <input type=\"hidden\" name=\"amount\" value=\"" + String.format("%.0f", order.getTotalMoney()) + "\">\n"
                + "                    <input type=\"hidden\" name=\"bankCode\" value=\"VNBANK\">\n"
                + "                    <input type=\"hidden\" name=\"orderId\" value=\"" + order.getOrderId() + "\">\n"
                + "                    <button>Click vào đây để thanh toán</button>\n"
                + "                  </form>\n"
                + "                 </li>\n"
                + "\n"
                + "            </ul>\n"
                + "            <p><strong>Thông tin liên hệ:</strong></p>\n"
                + "            <p>Nếu Quý khách có bất kỳ câu hỏi nào về đơn hàng hoặc cần hỗ trợ, xin vui lòng liên hệ với chúng tôi qua địa chỉ email: <a href=\"mailto:nghiemxuanloc02@gmail.com\">nghiemxuanloc02@gmail.com</a> hoặc số điện thoại: 0337783926.</p>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            <p>Trân trọng,</p>\n"
                + "            <p>Techno Store</p>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";

        String toEmail = order.getUser().getRole().getRoleId() != 5 ? order.getUser().getEmail() : order.getEmail();
        se.sendEmailOrder(toEmail, "ORDER PAYMENT REMINDER", html);

    }

    private LocalDateTime convertDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private static Date addHoursToDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}

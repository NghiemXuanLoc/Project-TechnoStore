/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

import dal.OrdersDAO;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import model.Orders;

public class Iconstant {

    public static final String GOOGLE_CLIENT_ID = "Your_google_client_id";

    public static final String GOOGLE_CLIENT_SECRET = "Your_client_secret";

    public static final String GOOGLE_REDIRECT_URI = "http://localhost:9999/TechStore/login";

    public static final String GOOGLE_GRANT_TYPE = "authorization_code";

    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    // Hàm chuyển đổi số thành định dạng tiền tệ theo một locale cụ thể
    public static String formatCurrency(double amount) {

        Locale vietnamLocale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnamLocale);
        return currencyFormatter.format(amount);
    }

    public static String convertDateTimeFormat(String dateTimeString) {
        // Định dạng ban đầu để phân tích chuỗi
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, inputFormatter);

        // Định dạng đầu ra để chuyển đổi thành giờ phút giây, năm tháng ngày
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return dateTime.format(outputFormatter);
    }
    
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return formatter.format(date);
    }
    
    public static Date addHoursToDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
    
    public static String getEmailNofication(String orderId){
        OrdersDAO od = new OrdersDAO();
        
        Orders order = od.getOrderById(Integer.parseInt(orderId));
        
        if(order.getUser().getRole().getRoleId() != 5){
            return order.getUser().getEmail();
        }else{
            return order.getEmail();
        }
    }

}

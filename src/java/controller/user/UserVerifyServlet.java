package controller.user;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import model.Role;
import model.SendEmail;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "UserVerifyServlet", urlPatterns = {"/UserVerify"})

public class UserVerifyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String fullName = request.getParameter("fullname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            SendEmail sm = new SendEmail();
            String code = sm.getRandom();
            User user = new User();
            user.setFullName(fullName);
            user.setEmail(email);

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            user.setGender(gender.equals("Male") ? 1 : 0);//1 là nam 0 la nữ
            user.setPhoneNumber(mobile);
            user.setAddress(address);
            user.setDateOfBirth(new java.util.Date());
            user.setCreatedAt(new java.util.Date());
            user.setCode(code);
            user.setImage("");
            Role r = new Role();
            r.setRoleId(4);  // Giá trị role mặc định, nên thiết lập phù hợp
            user.setRole(r);
            // Lưu token vào cơ sở dữ liệu
            String resetToken = generateResetToken(); // Tạo token reset password
            Date expiryDate = calculateExpiryDate();
            user.setResetPasswordToken(resetToken);
            user.setResetPasswordExpiry(expiryDate);
            UserDAO ud = new UserDAO();
            User us = ud.getAccountByEmail(email);
            HttpSession session = request.getSession();
            if (us == null) {
                boolean test = sm.sendEmail(user);
                if (test) {

                    Map<Integer, Integer> cart = new LinkedHashMap<>();
                    user.setCart(cart);
                    
                    
                    session.setAttribute("account", user);  // Lưu trữ thông tin người dùng trong session
                    response.sendRedirect("views/user/item-page/verify.jsp");  // Chuyển hướng tới trang verify.jsp
                    long currentTime = System.currentTimeMillis();
                    session.setAttribute("verificationTime", currentTime);
                } else {
                    out.println("<html><body>");
                    out.println("<h1>Failed to send verification email.</h1>");
                    out.println("</body></html>");
                }
            } else {
//                request.setAttribute("error", "Tài khoản email đã tồn tại");
//                request.getRequestDispatcher("views/user/home-page/homepage.jsp").forward(request, response);
                session.setAttribute("errorMessage", "Tài khoản email đã tồn tại");
                response.sendRedirect("views/user/home-page/homepage.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            try (PrintWriter out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<h1>Internal server error occurred.</h1>");
                out.println("</body></html>");
            }
        }
    }

    private String generateResetToken() {
        // Tạo một chuỗi token reset mật khẩu duy nhất
        String token = UUID.randomUUID().toString(); // Sử dụng UUID để tạo một chuỗi ngẫu nhiên
        return token;
    }

    private Date calculateExpiryDate() {
        // Tính toán thời gian hết hạn của token
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 24); // Thêm 24 giờ vào thời gian hiện tại
        return calendar.getTime();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

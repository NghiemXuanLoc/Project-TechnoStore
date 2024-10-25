package controller.user;

import dal.UserDAO;
import dal.UserForAdmin;
import model.GoogleAccount;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import model.PasswordRandom;
import model.ProductDetail;
import model.Role;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        LoginGoogle gg = new LoginGoogle();
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        UserForAdmin userForAdminDao = new UserForAdmin();//code cua ha userProfile
        User userForAdmin;//code cua ha userProfile
        try {
            if (code != null && !code.isEmpty()) { // Kiểm tra nếu đăng nhập bằng Google
                String accToken = gg.getToken(code);
                GoogleAccount acc = gg.getUserInfo(accToken);
                if (acc != null) {
                    UserDAO ud = new UserDAO();
                    HttpSession session = request.getSession();
                    User u = ud.getAccountByEmail(acc.getEmail());
                    if (u == null) { // Nếu tài khoản chưa tồn tại, thêm vào cơ sở dữ liệu
                        User user = new User();
                        user.setFullName(acc.getName());
                        user.setEmail(acc.getEmail());

                        // Tạo và lưu token vào cơ sở dữ liệu
                        String randomPassword = PasswordRandom.generateRandomPassword(6, 14);
                        String hashedPassword = BCrypt.hashpw(randomPassword, BCrypt.gensalt());
                        user.setPassword(hashedPassword);
                        user.setGender(1);  // Giới tính mặc định
                        user.setImage("");
                        user.setPhoneNumber("");
                        user.setAddress("");
                        user.setDateOfBirth(new java.util.Date());
                        user.setCreatedAt(new java.util.Date());
                        Role r = new Role();
                        r.setRoleId(4);  // Vai trò mặc định
                        user.setRole(r);

                        // Lưu token vào cơ sở dữ liệu
                        String resetToken = generateResetToken(); // Tạo token reset password
                        Date expiryDate = calculateExpiryDate();
                        user.setResetPasswordToken(resetToken);
                        user.setResetPasswordExpiry(expiryDate);

                        Map<Integer, Integer> cart = new LinkedHashMap<>();
                        user.setCart(cart);

                        ud.insertUser(user);

                        session.setAttribute("account", ud.getAccountByEmail(acc.getEmail()));

                        System.out.println("session account: " + user);
                        u = user;
                    } else {
                        userForAdmin = userForAdminDao.getUserById(u.getUserId());//code cua ha userProfile
                        session.setAttribute("userProfileForAdmin", userForAdmin);//code cua ha userProfile
                        session.setAttribute("account", u);
                    }

                    if (u.getRole().getRoleId() == 1) {//admin
                        session.setAttribute("userprofile", u);
                        response.sendRedirect("adminDashboard");
                    } else if (u.getRole().getRoleId() == 2) { // Marketing
                        session.setAttribute("userprofile", u);
                        response.sendRedirect("mktdashboard");
                    } else if (u.getRole().getRoleId() == 3) { // Sale
                        session.setAttribute("userprofile", u);

                        response.sendRedirect("saledashboard");
                    } else if (u.getRole().getRoleId() == 4) { // customer
                        response.sendRedirect("home");
                    }
                    return; // Kết thúc xử lý
                }
            } else if (email != null && password != null) { // Kiểm tra nếu đăng nhập bằng email và password
                UserDAO ud = new UserDAO();
                User userLogin = ud.getAccountByEmailPassword(email, password);
                if (userLogin != null && userLogin.getDeleted() == 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", userLogin);
                    userForAdmin = userForAdminDao.getUserById(userLogin.getUserId());//code cua ha userProfile
                    session.setAttribute("userProfileForAdmin", userForAdmin);//code cua ha userProfile
                    if (userLogin.getRole().getRoleId() == 1) {//admin
                        session.setAttribute("userprofile", userLogin);

                        response.sendRedirect("adminDashboard");
                    } else if (userLogin.getRole().getRoleId() == 2) { // Marketing
                        session.setAttribute("userprofile", userLogin);
                        response.sendRedirect("mktdashboard");
                    } else if (userLogin.getRole().getRoleId() == 3) { // Sale
                        session.setAttribute("userprofile", userLogin);
                        response.sendRedirect("saledashboard");

                    } else if (userLogin.getRole().getRoleId() == 4) { // customer
                        response.sendRedirect("home");
                    }
                } else if (userLogin != null && userLogin.getDeleted() == 1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("errorMessage1", "Tài khoản đã bị vô hiệu hóa, vui lòng liên hệ admin để giải quyết!");
                    response.sendRedirect("home");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("errorMessage1", "Tài khoản hoặc mật khẩu không chính xác");
                    response.sendRedirect("home");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Logging should be used instead
            response.getWriter().write("An error occurred during login.");
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

}

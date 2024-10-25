/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import java.util.List;
import java.util.UUID;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/changepassword"})
public class ChangePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword atsdsdsd " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");
        User user = dao.getUserProfile(account.getUserId());

        String currentPassword = request.getParameter("current_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");

        // Mật khẩu hiện tại sai
        if (!BCrypt.checkpw(currentPassword, user.getPassword())) {
            session.setAttribute("errCurrentPassword", "Current password is wrong, change password fail");
            response.sendRedirect("home");
            return;
        }

        // Mật khẩu mới quá ngắn, ngắn hơn 8
        if (newPassword.length() < 8) {
            session.setAttribute("errNewPasswordLength", "New password is too short, must be at least 8 characters.");
            response.sendRedirect("home");
            return;
        }

        // Mật khẩu mới quá dài, dài hơn 16
        if (newPassword.length() > 16) {
            session.setAttribute("errNewPasswordLength", "New password is too long, must be at most 16 characters.");
            response.sendRedirect("home");
            return;
        }

        // Mật khẩu mới và xác nhận mật khẩu không khớp
        if (!newPassword.equals(confirmPassword)) {
            session.setAttribute("errConfirmPass", "New password and confirm password do not match.");
            response.sendRedirect("home");
            return;
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        if (dao.updatePasswordOfThong(hashedPassword, user.getUserId())) {
            session.setAttribute("mess", "Change password success");
        } else {
            session.setAttribute("mess", "Change password fail");
        }

        response.sendRedirect("home");
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

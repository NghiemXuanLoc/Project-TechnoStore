package controller.user;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.UUID;
import model.SendEmail;
import model.User;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/resetpassword"})
public class ResetPasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        SendEmail sm = new SendEmail();
        UserDAO u = new UserDAO();
        User user = u.getAccountByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            Timestamp expiryDate = new Timestamp(System.currentTimeMillis() + 3600L * 1000);

            // Sửa thành updateResetPasswordToken
            boolean updated = u.updateResetPasswordToken(email, token, expiryDate);

            if (updated) {
                boolean emailSent = sm.sendVerificationEmail(user, token); // Send email with token
                if (emailSent) {
                    response.sendRedirect("views/user/item-page/resetpasswordlinksent.jsp");
                } else {
                    request.setAttribute("errorMessage", "Error sending email.");
                    request.getRequestDispatcher("views/user/item-page/resetpassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Error updating reset password token.");
                request.getRequestDispatcher("views/user/item-page/resetpassword.jsp").forward(request, response);
            }
        } else {
//            request.setAttribute("errorMessage2", "Invalid email.");
//            request.getRequestDispatcher("views/user/home-page/homepage.jsp").forward(request, response);

            HttpSession session = request.getSession();
            session.setAttribute("errorMessage2", "Invalid Email");
            response.sendRedirect("home");
        }
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

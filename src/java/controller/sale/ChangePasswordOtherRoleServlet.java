/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sale;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "ChangePasswordOtherRoleServlet", urlPatterns = {"/changepasswordotherrole"})
public class ChangePasswordOtherRoleServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePasswordOtherRoleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordOtherRoleServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        String errorMsg = null;
        // Validate current password
        if (!BCrypt.checkpw(currentPassword, user.getPassword())) {
            errorMsg = "Current password is wrong, change password failed.";
            session.setAttribute("errCurrentPassword", errorMsg);
        } else if (newPassword.length() < 8 || newPassword.length() > 16) {
            // Validate new password length
            errorMsg = newPassword.length() < 8 ? 
                "New password is too short, must be at least 8 characters." : 
                "New password is too long, must be at most 16 characters.";
            session.setAttribute("errNewPasswordLength", errorMsg);
        } else if (!newPassword.equals(confirmPassword)) {
            // Validate new password and confirm password match
            errorMsg = "New password and confirm password do not match.";
            session.setAttribute("errConfirmPass", errorMsg);
        } else {
            // Update password
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            if (dao.updatePasswordOfThong(hashedPassword, user.getUserId())) {
                session.setAttribute("mess", "Change password success");
            } else {
                session.setAttribute("mess", "Change password failed");
            }
        }

        // Redirect based on user role and presence of error message
        session.setAttribute("userprofile", user);
        String redirectUrl;
        switch (user.getRole().getRoleId()) {
            case 1: // admin
                redirectUrl = "userList";
                break;
            case 2: // Marketing
                redirectUrl = "postlist";
                break;
            case 3: // Sale
                redirectUrl = "saledashboard";
                break;
            default:
                redirectUrl = "home";
                break;
        }
        response.sendRedirect(redirectUrl);
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

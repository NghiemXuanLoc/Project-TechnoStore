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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "UpdatePasswordServlet", urlPatterns = {"/updatepassword"})
public class UpdatePasswordServlet extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("views/user/item-page/resetpassword.jsp").forward(request, response);
            return;
        }

        // Log token for debugging
        System.out.println("Received token: " + token);

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        UserDAO u = new UserDAO();
        boolean tokenValid = u.isTokenValid(token);

        if (tokenValid) {
            boolean passwordUpdated = u.updatePasswordByToken(token, hashedPassword);
            if (passwordUpdated) {
                request.setAttribute("successMessage", "Your password has been successfully updated.");
                response.sendRedirect("home");
            } else {
                request.setAttribute("errorMessage", "Error updating password.");
                request.getRequestDispatcher("views/user/item-page/resetpassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid or expired token.");
            request.getRequestDispatcher("views/user/item-page/resetpassword.jsp").forward(request, response);
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

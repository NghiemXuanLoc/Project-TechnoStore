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
import model.GoogleAccount;
import model.Role;
import model.User;

@WebServlet(name = "VerifyCodeServlet", urlPatterns = {"/VerifyCode"})
public class VerifyCodeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");

            long verificationTime = (long) session.getAttribute("verificationTime");

// Lấy thời gian hiện tại
            long currentTime = System.currentTimeMillis();

// Kiểm tra nếu đã qua 1 phút
            if (currentTime - verificationTime > 30000) { // 60000 milliseconds = 1 phút
                // Thông báo lỗi cho người dùng và yêu cầu gửi lại mã xác nhận mới
                out.println("<html><body>");
                out.println("<h1>Error: Verification code expired. Please request a new one.</h1>");
                out.println("</body></html>");
            } else {
                // Kiểm tra mã xác nhận
                String code = request.getParameter("authcode");
                if (code.equals(user.getCode())) {
                    UserDAO us = new UserDAO();
                    us.insertUser(user);
                    
                    session.setAttribute("account", us.getAccountByEmail(user.getEmail()));
                    
                    System.out.println("Account regitster: " + session.getAttribute("account"));

                    response.sendRedirect("home");
                } else {
                    // Mã xác nhận không hợp lệ, thông báo lỗi cho người dùng
                    request.setAttribute("errorMessage", "Incorrect verification code");
                    request.getRequestDispatcher("views/user/item-page/verify.jsp").forward(request, response);
                }
            }
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

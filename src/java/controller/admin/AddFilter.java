/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.SettingFilterDAO;
import dal.UserForAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.AuthorizationSetting;
import model.Role;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddFilter", urlPatterns = {"/addFilter"})
public class AddFilter extends HttpServlet {

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
            out.println("<title>Servlet AddFilter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddFilter at " + request.getContextPath() + "</h1>");
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
        UserForAdmin dao = new UserForAdmin();
        List<Role> listRole = dao.getListRole();

        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("views/admin/item-page/addfillter.jsp").forward(request, response);
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
//        processRequest(request, response);

        SettingFilterDAO dao = new SettingFilterDAO();
        String feature = request.getParameter("feature");
        String url = request.getParameter("url");
        String role_raw = request.getParameter("role");
        String status_raw = request.getParameter("status");
        UserForAdmin daoUF = new UserForAdmin();
        List<Role> listRole = daoUF.getListRole();
        List<AuthorizationSetting> liA = dao.getAllAuthor();

        int role = 0, status;

        try {
            role = Integer.parseInt(role_raw);
            status = Integer.parseInt(status_raw);
            if (url.trim().equals("")) {
                request.setAttribute("Err", "The path cannot empty and start with '/'");
                request.setAttribute("feature", feature);
                request.setAttribute("url", url);
                request.setAttribute("role", role_raw);
                request.setAttribute("status", status_raw);
                request.setAttribute("listRole", listRole);
                request.getRequestDispatcher("views/admin/item-page/addfillter.jsp").forward(request, response);
                return;
            }
            
            if (!url.matches("^[\\p{L}\\p{N}\\s/]*$")) {
                request.setAttribute("Err", "The path cannot contain special characters and start with '/'");
                request.setAttribute("feature", feature);
                request.setAttribute("url", url);
                request.setAttribute("role", role_raw);
                request.setAttribute("status", status_raw);
                request.setAttribute("listRole", listRole);
                request.getRequestDispatcher("views/admin/item-page/addfillter.jsp").forward(request, response);
                return;
            }
            
            if (feature.trim().equals("")) {
                request.setAttribute("Err", "Feature cannot empty");
                request.setAttribute("feature", feature);
                request.setAttribute("url", url);
                request.setAttribute("role", role_raw);
                request.setAttribute("status", status_raw);
                request.setAttribute("listRole", listRole);
                request.getRequestDispatcher("views/admin/item-page/addfillter.jsp").forward(request, response);
                return;
            }
            
            if (!feature.matches("^[\\p{L}\\p{N}\\s]*$")) {
                request.setAttribute("Err", "Feature cannot contain special characters");
                request.setAttribute("feature", feature);
                request.setAttribute("url", url);
                request.setAttribute("role", role_raw);
                request.setAttribute("status", status_raw);
                request.setAttribute("listRole", listRole);
                request.getRequestDispatcher("views/admin/item-page/addfillter.jsp").forward(request, response);
                return;
            }
            if (url != null) {
                if (dao.authorCheck(role, url)) {
                    request.setAttribute("Err", "The path already exists");
                    request.setAttribute("feature", feature);
                    request.setAttribute("url", url);
                    request.setAttribute("role", role_raw);
                    request.setAttribute("status", status_raw);
                    request.setAttribute("listRole", listRole);
                    request.getRequestDispatcher("views/admin/item-page/addfillter.jsp").forward(request, response);
                    return;
                } else {
                    dao.insertIntoAuthor(role, url, feature, status);
                    request.setAttribute("Success", "Add successfully");
                }

            }
        } catch (Exception e) {
        }

        request.setAttribute("feature", feature);
        request.setAttribute("url", url);
        request.setAttribute("role", role_raw);
        request.setAttribute("status", status_raw);
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("views/admin/item-page/addfillter.jsp").forward(request, response);

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

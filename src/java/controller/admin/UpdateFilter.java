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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.AuthorizationSetting;
import model.Role;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateFilter", urlPatterns = {"/updateFilter"})
public class UpdateFilter extends HttpServlet {
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
            out.println("<title>Servlet UpdateFilter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateFilter at " + request.getContextPath() + "</h1>");
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
        SettingFilterDAO daoFilter = new SettingFilterDAO();
        String idAuthor = request.getParameter("id");

        try {
            int id = Integer.parseInt(idAuthor);
            AuthorizationSetting author = daoFilter.getAuthorById(id);
            request.setAttribute("authorRes", author);
        } catch (Exception e) {
        }

        request.setAttribute("listRole", listRole);

        request.getRequestDispatcher("views/admin/item-page/updatefilter.jsp").forward(request, response);
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
        String authorId = request.getParameter("idAuthor");
        String url = request.getParameter("url");
        String feature = request.getParameter("feature");
        String role_raw = request.getParameter("role");
        String status_raw = request.getParameter("status");
        SettingFilterDAO daoFilter = new SettingFilterDAO();
        HttpSession session = request.getSession();
        UserForAdmin dao = new UserForAdmin();
        List<Role> listRole = dao.getListRole();
        try {
            int auId = Integer.parseInt(authorId);
            int roleId = Integer.parseInt(role_raw);
            int status = Integer.parseInt(status_raw);
            if (url.trim().equals("")) {
                AuthorizationSetting author = daoFilter.getAuthorById(auId);
                request.setAttribute("authorRes", author);
                request.setAttribute("listRole", listRole);
                request.setAttribute("Err", "Url cannot empt");
                request.getRequestDispatcher("views/admin/item-page/updatefilter.jsp").forward(request, response);
                return;
            }
            if (!url.matches("^[\\p{L}\\p{N}\\s/]*$")) {
                AuthorizationSetting author = daoFilter.getAuthorById(auId);
                request.setAttribute("authorRes", author);
                request.setAttribute("listRole", listRole);
                request.setAttribute("Err", "Feature cannot contain special characters");
                request.getRequestDispatcher("views/admin/item-page/updatefilter.jsp").forward(request, response);
                return;
            }
            
            if (feature.trim().equals("")) {
                AuthorizationSetting author = daoFilter.getAuthorById(auId);
                request.setAttribute("authorRes", author);
                request.setAttribute("listRole", listRole);
                request.setAttribute("Err", "Url cannot empt");
                request.getRequestDispatcher("views/admin/item-page/updatefilter.jsp").forward(request, response);
                return;
            }
            if (!feature.matches("^[\\p{L}\\p{N}\\s]*$")) {
                AuthorizationSetting author = daoFilter.getAuthorById(auId);
                request.setAttribute("authorRes", author);
                request.setAttribute("listRole", listRole);
                request.setAttribute("Err", "Feature cannot contain special characters");
                request.getRequestDispatcher("views/admin/item-page/updatefilter.jsp").forward(request, response);
                return;
            }
//            AuthorizationSetting author = daoFilter.getAuthorById(auId);
            daoFilter.updateAuthorById(roleId, url, feature, status, auId);

            AuthorizationSetting author = daoFilter.getAuthorById(auId);
//            request.setAttribute("urlRes", url);
//            request.setAttribute("featureRes", feature);
//            request.setAttribute("roleRes", role_raw);
//            request.setAttribute("statusRes", status_raw);
//            session.setAttribute("Success", "Cập nhật đường dẫn " + "'"+ url + "'" + " cho " + author.getRole().getRoleName() + "  thành công!!! " );
            request.setAttribute("authorRes", author);
            request.setAttribute("listRole", listRole);
            request.setAttribute("updateSuccess", "Update successfully!!!");
            request.getRequestDispatcher("views/admin/item-page/updatefilter.jsp").forward(request, response);

        } catch (Exception e) {
        }
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

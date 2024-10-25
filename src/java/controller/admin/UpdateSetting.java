/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.SettingListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.SettingListt;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateSetting", urlPatterns = {"/updateSetting"})
public class UpdateSetting extends HttpServlet {

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
            out.println("<title>Servlet UpdateSetting</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSetting at " + request.getContextPath() + "</h1>");
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
        SettingListDAO dao = new SettingListDAO();
        String id = request.getParameter("id");
        List<SettingListt> listType = dao.getAllType();
        request.setAttribute("listType", listType);
        SettingListt st = dao.getSettingById(id);
        request.setAttribute("settingById", st);
        request.getRequestDispatcher("views/admin/item-page/updatesetting.jsp").forward(request, response);
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
        SettingListDAO dao = new SettingListDAO();
        String id_raw = request.getParameter("idSetting");

        String type = request.getParameter("type");

        String value_raw = request.getParameter("value");

        String description = request.getParameter("description");
        String status_raw = request.getParameter("status");
        HttpSession session = request.getSession();
        int value = 0, status, id;
        
        if(description.trim().equals("")){
            SettingListt st = dao.getSettingById(id_raw);
            request.setAttribute("settingById", st);
            request.setAttribute("Err", "Description cannot empty");
            request.getRequestDispatcher("views/admin/item-page/updatesetting.jsp").forward(request, response);
            return;
        }
        if (!description.matches("^[\\p{L}\\p{N}\\s]*$")) {
            SettingListt st = dao.getSettingById(id_raw);
            request.setAttribute("settingById", st);
            request.setAttribute("Err", "Description cannot contain special characters");
            request.getRequestDispatcher("views/admin/item-page/updatesetting.jsp").forward(request, response);
            return;
        }
        try {
            value = Integer.parseInt(value_raw);
            status = Integer.parseInt(status_raw);
            id = Integer.parseInt(id_raw);

            dao.updateSetting(id, type, value, description, status);
            SettingListt st = dao.getSettingById(id_raw);
            request.setAttribute("settingById", st);
        } catch (Exception e) {
        }
        
//        session.setAttribute("updateSuccess", "Update thành công setting Type: " + "' " + type + " '" + " với Value = " + value);
        request.setAttribute("updateSuccess", "Update successful!!!");
        request.getRequestDispatcher("views/admin/item-page/updatesetting.jsp").forward(request, response);
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

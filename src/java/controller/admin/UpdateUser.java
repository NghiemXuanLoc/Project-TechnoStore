/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.UserDAO;
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
import model.Role;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name="UpdateUser", urlPatterns={"/updateUser"})
public class UpdateUser extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet UpdateUser</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateUser at " + request.getContextPath () + "ITER2</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        String id_raw = request.getParameter("id");
        int id = 0;
        try {
           id = Integer.parseInt(id_raw);
            
        } catch (Exception e) {
        }
        User u = dao.getUserById(id);
        
        
        request.setAttribute("listRole", listRole);
        request.setAttribute("User", u);
        request.getRequestDispatcher("views/admin/item-page/updateuser.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        UserForAdmin dao = new UserForAdmin();
        List<Role> listRole = dao.getListRole();
        String role_raw = request.getParameter("role");
        String status_raw = request.getParameter("status");
        String idUser_raw = request.getParameter("idUser");
        User u = null;
        HttpSession session = request.getSession();
        
        try {
            int role = Integer.parseInt(role_raw);
            int status = Integer.parseInt(status_raw);
            int id = Integer.parseInt(idUser_raw);
                      
            dao.updateUserById(role, status, id);
            u = dao.getUserById(id); 
        } catch (Exception e) {
        }
//        session.setAttribute("updateSuccess", "Update user name: " + u.getFullName() +" success!!!!" );
        request.setAttribute("updateSuccess", "Update successful!!!");
        request.setAttribute("User", u);
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("views/admin/item-page/updateuser.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

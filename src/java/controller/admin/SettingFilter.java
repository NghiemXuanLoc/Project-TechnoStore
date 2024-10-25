/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.SettingFilterDAO;
import dal.SettingListDAO;
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
@WebServlet(name = "SettingFilter", urlPatterns = {"/settingFilter"})
public class SettingFilter extends HttpServlet {

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
            out.println("<title>Servlet SettingFilter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingFilter at " + request.getContextPath() + "</h1>");
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
        SettingFilterDAO dao = new SettingFilterDAO();
        List<AuthorizationSetting> listAuthorAll = dao.getAllAuthor();
        UserForAdmin ufa = new UserForAdmin();
        List<Role> lr = ufa.getListRole();
        SettingListDAO slDao = new SettingListDAO();
        int page, numPerPage = slDao.getValueOfPaging();

        int size = listAuthorAll.size();
        int num = (size % numPerPage == 0 ? (size / numPerPage) : (size / numPerPage) + 1);

        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }

        int start, end;
        start = (page - 1) * numPerPage;
        end = Math.min(page * numPerPage, size);
        List<AuthorizationSetting> listAuthorPaging = dao.getListByPage(listAuthorAll, start, end);
        request.setAttribute("listAllAuthor", listAuthorPaging);
        request.setAttribute("num", num);
        request.setAttribute("page", size);
        request.setAttribute("currenPage", page);
        request.setAttribute("endPage", end);
        request.setAttribute("listRole", lr);
        request.getRequestDispatcher("views/admin/item-page/settingfilter.jsp").forward(request, response);
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

        String role_raw = request.getParameter("filterByRole");
        String fillterByStatus = request.getParameter("filterByStatus");
        String searchByUrl = request.getParameter("search");
        SettingFilterDAO dao = new SettingFilterDAO();
        List<AuthorizationSetting> listAuthorAll = dao.getAllAuthor();
        UserForAdmin ufa = new UserForAdmin();
        List<Role> lr = ufa.getListRole();
        SettingListDAO slDao = new SettingListDAO();
        int page, numPerPage = slDao.getValueOfPaging();

        int size = listAuthorAll.size();
        int num = (size % numPerPage == 0 ? (size / numPerPage) : (size / numPerPage) + 1);

        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }

        int start, end;
        start = (page - 1) * numPerPage;
        end = Math.min(page * numPerPage, size);

        request.setAttribute("num", num);
        request.setAttribute("page", size);
        request.setAttribute("currenPage", page);
        request.setAttribute("endPage", end);
        int role = 0, status;
        try {
            role = Integer.parseInt(role_raw);

        } catch (Exception e) {
        }
        List<AuthorizationSetting> listAuthorFilterByRole = dao.getAllRoleById(role);

        List<AuthorizationSetting> listAuthorPaging = null;

        if (role_raw != null) {
            if (role != 9) { // chi lay theo role
                listAuthorPaging = dao.getListByPage(listAuthorFilterByRole, start, end);
                request.setAttribute("listRole", lr);
                request.setAttribute("roleRes", role);
                request.setAttribute("listAllAuthor", listAuthorPaging);
                request.getRequestDispatcher("views/admin/item-page/settingfilter.jsp").forward(request, response);
                return;
            } else { //lay tat ca
                listAuthorPaging = dao.getListByPage(listAuthorAll, start, end);
                request.setAttribute("listRole", lr);
                request.setAttribute("roleRes", role);
                request.setAttribute("listAllAuthor", listAuthorPaging);
                request.getRequestDispatcher("views/admin/item-page/settingfilter.jsp").forward(request, response);
                return;
            }
        }

        if (fillterByStatus != null) {
            try {
                status = Integer.parseInt(fillterByStatus);
                List<AuthorizationSetting> listAuthorFilterByStatus = dao.getAllRoleByStatus(status);
                if (status != 2) { //lay theo active or inactive
                    listAuthorPaging = dao.getListByPage(listAuthorFilterByStatus, start, end);
                    request.setAttribute("listRole", lr);
                    request.setAttribute("statusRes", fillterByStatus);
                    request.setAttribute("listAllAuthor", listAuthorPaging);
                    request.getRequestDispatcher("views/admin/item-page/settingfilter.jsp").forward(request, response);
                } else {//lay tat ca
                    listAuthorPaging = dao.getListByPage(listAuthorAll, start, end);
                    request.setAttribute("listRole", lr);
                    request.setAttribute("statusRes", fillterByStatus);
                    request.setAttribute("listAllAuthor", listAuthorPaging);
                    request.getRequestDispatcher("views/admin/item-page/settingfilter.jsp").forward(request, response);
                }
            } catch (Exception e) {
            }
        }

        if (searchByUrl != null) {
            List<AuthorizationSetting> listAuthorFilterBySearch = dao.getAllRoleBySearch(searchByUrl);
            listAuthorPaging = dao.getListByPage(listAuthorFilterBySearch, start, end);
            request.setAttribute("listRole", lr);
            request.setAttribute("statusRes", fillterByStatus);
            request.setAttribute("listAllAuthor", listAuthorPaging);
            request.getRequestDispatcher("views/admin/item-page/settingfilter.jsp").forward(request, response);
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

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
import model.SettingListt;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "SettingList", urlPatterns = {"/settingList"})
public class SettingList extends HttpServlet {

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
            out.println("<title>Servlet SettingList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingList at " + request.getContextPath() + "</h1>");
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
        SettingListDAO slDao = new SettingListDAO();
        List<SettingListt> lst = slDao.getAllSetting();
        int page, numPerPage = slDao.getValueOfPaging();
        List<SettingListt> listType = slDao.getAllType();
        int size = lst.size();
        System.out.println("Size: " + size);
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
        List<SettingListt> listAllSettingPaging = slDao.getListByPage(lst, start, end);

        request.setAttribute("listSetting", listAllSettingPaging);
        request.setAttribute("listType", listType);
        request.setAttribute("num", num);
        request.setAttribute("page", size);
        request.setAttribute("currenPage", page);
        request.setAttribute("endPage", end);
        request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
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
        String type = request.getParameter("filterByType");

        String status_raw = request.getParameter("filterByStatus");

        String filterBySomeThing = request.getParameter("filterBySomeThing");

        String search = request.getParameter("search");

        List<SettingListt> listAllSetting = dao.getAllSetting();
        int page, numPerPage = dao.getValueOfPaging();
        List<SettingListt> listType = dao.getAllType();
        int size = listAllSetting.size();
        System.out.println("Size: " + size);
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
        List<SettingListt> listAllSettingPaging = dao.getListByPage(listAllSetting, start, end);
        request.setAttribute("listType", listType);
        request.setAttribute("num", num);
        request.setAttribute("page", size);
        request.setAttribute("currenPage", page);
        request.setAttribute("endPage", end);

        if (type != null) {
            if (!type.equals("All")) { // filter by type
                List<SettingListt> listAll = dao.filterByType(type);
                List<SettingListt> listTypePaging = dao.getListByPage(listAll, start, end);
                request.setAttribute("listSetting", listTypePaging);
                request.setAttribute("typeRes", type);
                request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("typeRes", type);
                request.setAttribute("listSetting", listAllSettingPaging);
                request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
                return;
            }
        }

        if (status_raw != null) {
            int status = 2;
            try {
                status = Integer.parseInt(status_raw);

            } catch (Exception e) {
                System.out.println("Err: " + e);
            }
            if (!status_raw.equals("2")) {
                List<SettingListt> listAll = dao.filterByStatus(status);
                List<SettingListt> listStatusPaging = dao.getListByPage(listAll, start, end);
                request.setAttribute("listSetting", listStatusPaging);
                request.setAttribute("typeRes", type);
                request.setAttribute("statusResponse", status_raw);
                request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
            } else {
                request.setAttribute("typeRes", type);
                request.setAttribute("listSetting", listAllSettingPaging);
                request.setAttribute("statusResponse", status_raw);
                request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
            }
        }

        if (filterBySomeThing != null) {
            List<SettingListt> listAll;
            switch (filterBySomeThing) {
                case "Setting_id":
                    listAll = dao.orderBySomeThing(filterBySomeThing);
                    List<SettingListt> listPaging = dao.getListByPage(listAll, start, end);
                    request.setAttribute("listSetting", listPaging);
                    request.setAttribute("filterRes", filterBySomeThing);
                    request.setAttribute("typeRes", type);
                    request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
                    break;

                case "Type_setting":
                    listAll = dao.orderBySomeThing(filterBySomeThing);
                    List<SettingListt> listPagingType = dao.getListByPage(listAll, start, end);
                    request.setAttribute("listSetting", listPagingType);
                    request.setAttribute("filterRes", filterBySomeThing);
                    request.setAttribute("typeRes", type);
                    request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
                    break;

                case "Value_setting":
                    listAll = dao.orderBySomeThing(filterBySomeThing);
                    List<SettingListt> listPagingValue = dao.getListByPage(listAll, start, end);
                    request.setAttribute("listSetting", listPagingValue);
                    request.setAttribute("filterRes", filterBySomeThing);
                    request.setAttribute("typeRes", type);
                    request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
                    break;
                case "Order_setting":
                    listAll = dao.orderBySomeThing(filterBySomeThing);
                    List<SettingListt> listPagingOrder = dao.getListByPage(listAll, start, end);
                    request.setAttribute("listSetting", listPagingOrder);
                    request.setAttribute("filterRes", filterBySomeThing);
                    request.setAttribute("typeRes", type);
                    request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
                    break;
                case "Status_setting":
                    listAll = dao.orderBySomeThing(filterBySomeThing);
                    List<SettingListt> listPagingStatus = dao.getListByPage(listAll, start, end);
                    request.setAttribute("listSetting", listPagingStatus);
                    request.setAttribute("filterRes", filterBySomeThing);
                    request.setAttribute("typeRes", type);
                    request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
                    break;
                case "All":
                    listAll = dao.getAllSetting();
                    List<SettingListt> listPagingSetting = dao.getListByPage(listAll, start, end);
                    request.setAttribute("listSetting", listPagingSetting);
                    request.setAttribute("filterRes", filterBySomeThing);
                    request.setAttribute("typeRes", type);
                    request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
                    break;
            }
        }

        if (search != null) {
            List<SettingListt> listAll = dao.searchSettingByTypeAndValue(search);
            List<SettingListt> listPagingSearch = dao.getListByPage(listAll, start, end);
            request.setAttribute("listSetting", listPagingSearch);
            request.setAttribute("filterRes", filterBySomeThing);
            request.setAttribute("typeRes", type);
            request.getRequestDispatcher("views/admin/item-page/settinglist.jsp").forward(request, response);
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

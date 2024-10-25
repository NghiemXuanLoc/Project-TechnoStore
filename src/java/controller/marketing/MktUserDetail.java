/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.MarketingUpdateUserHistoryDAO;
import dal.SettingListDAO;
import dal.UserDAO;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.UpdateHistory;

/**
 *
 * @author quang
 */
@WebServlet(name = "MktUserDetail", urlPatterns = {"/mktuserdetail"})
public class MktUserDetail extends HttpServlet {

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
            out.println("<title>Servlet MktUserDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktUserDetail at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("sId");
        request.setAttribute("idUser", id_raw);
        UserDAO dao = new UserDAO();
        MarketingUpdateUserHistoryDAO daoMkt = new MarketingUpdateUserHistoryDAO();
        SettingListDAO slDao = new SettingListDAO();
        List<UpdateHistory> list = daoMkt.getAllUpdateByUserId(id_raw);
        int page, numPerPage = slDao.getValueOfPaging();

        int size = list.size();
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
        List<UpdateHistory> listAllUser = daoMkt.getListByPage(list, start, end);

//        request.setAttribute("listUser", listAllUser);
        request.setAttribute("num", num);
        request.setAttribute("page", size);
        request.setAttribute("currenPage", page);
        request.setAttribute("endPage", end);
        try {
            int id = Integer.parseInt(id_raw);
            User u = dao.getUserById(id);

            request.setAttribute("User", u);
            request.setAttribute("listMkt", listAllUser);
        } catch (Exception e) {
        }

        request.getRequestDispatcher("views/marketing/item-page/edituser.jsp").forward(request, response);
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
        SettingListDAO slDao = new SettingListDAO();
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String id_raw = request.getParameter("idUser");
        String address = request.getParameter("address");
        String id_rawPage = request.getParameter("sId");
        request.setAttribute("idUser", id_rawPage);
        UserDAO dao = new UserDAO();
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");
        User updateBy = dao.getUserById(account.getUserId());
        MarketingUpdateUserHistoryDAO daoMkt = new MarketingUpdateUserHistoryDAO();
        List<UpdateHistory> list = daoMkt.getAllUpdateByUserId(id_raw);
        int page, numPerPage = slDao.getValueOfPaging();

        int size = list.size();
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
        

//        request.setAttribute("listUser", listAllUser);
        request.setAttribute("num", num);
        request.setAttribute("page", size);
        request.setAttribute("currenPage", page);
        request.setAttribute("endPage", end);
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
            User u = dao.getUserById(id);

        } catch (Exception e) {
        }
        
        if(fullName.trim().equals("")){
            List<UpdateHistory> listAllUser = daoMkt.getListByPage(list, start, end);
            User u = dao.getUserById(id);
            request.setAttribute("User", u);
            request.setAttribute("listMkt", listAllUser);
            request.setAttribute("Err", "The name cannot empty");
//            check = false;
            request.getRequestDispatcher("views/marketing/item-page/edituser.jsp").forward(request, response);
            return;
        }
        
        if (!fullName.matches("^[\\p{L}\\p{N}\\s]*$")) {
            List<UpdateHistory> listAllUser = daoMkt.getListByPage(list, start, end);
            User u = dao.getUserById(id);
            request.setAttribute("User", u);
            request.setAttribute("listMkt", listAllUser);
            request.setAttribute("Err", "The name cannot contain special characters");
//            check = false;
            request.getRequestDispatcher("views/marketing/item-page/edituser.jsp").forward(request, response);
            return;
        }

        if (!phone.isEmpty()) {
            if (!phone.matches("^0\\d{9}$")) {
                List<UpdateHistory> listAllUser = daoMkt.getListByPage(list, start, end);
                User u = dao.getUserById(id);
                request.setAttribute("User", u);
                request.setAttribute("listMkt", listAllUser);
                request.setAttribute("Err", "Please input phone length = 10, start with 0");
                request.setAttribute("User", u);
//                check = false;
                request.getRequestDispatcher("views/marketing/item-page/edituser.jsp").forward(request, response);
                return;
            }
            
        }
        
        if (!address.isEmpty() && address != null) {
            if (!address.matches("^[\\p{L}\\p{N}\\s\\-]*$")) {
                List<UpdateHistory> listAllUser = daoMkt.getListByPage(list, start, end);
                User u = dao.getUserById(id);
                request.setAttribute("listMkt", listAllUser);
                request.setAttribute("Err", "The address cannot contain special characters");
                request.setAttribute("User", u);
                request.getRequestDispatcher("views/marketing/item-page/edituser.jsp").forward(request, response);
                return;
            }
        }

        daoMkt.updateUserById(fullName, gender, address, email, phone, id_raw);
        daoMkt.addToUpdateHistory(email, id_raw, fullName, gender, phone, address, updateBy.getFullName());
        User u = dao.getUserById(id);
        List<UpdateHistory> listAfter = daoMkt.getAllUpdateByUserId(id_raw); //lay lai danh sach moi sau khi update
        List<UpdateHistory> listAllUser = daoMkt.getListByPage(listAfter, start, end); //lay lai danh sach page moi sau khi update
        List<UpdateHistory> a = null;
        if(listAfter.size() <= numPerPage){
             a = listAfter;     
        }else{
            a =listAllUser;       
        }
        
        request.setAttribute("listMkt", a);
        request.setAttribute("User", u);
        request.setAttribute("updateSuccess", "Update successfully!!!");
        request.getRequestDispatcher("views/marketing/item-page/edituser.jsp").forward(request, response);
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

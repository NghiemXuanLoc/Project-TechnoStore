/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

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
import model.Role;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name="UserList", urlPatterns={"/userList"})
public class UserList extends HttpServlet {
   
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
            out.println("<title>Servlet UserList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserList at " + request.getContextPath () + "</h1>");
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
        SettingListDAO slDao = new SettingListDAO();
        UserForAdmin ufa = new UserForAdmin();
        List<User> lu = ufa.getAllUser();
        List<Role> lr = ufa.getListRole();
        
        int page, numPerPage = slDao.getValueOfPaging();
        
        int size = lu.size();
        int num = (size % numPerPage ==0? (size/numPerPage):(size/numPerPage) + 1);
        
        String xpage = request.getParameter("page");
        if(xpage == null){
            page = 1;
        }else{
            page = Integer.parseInt(xpage);
        }
        
        int start, end;
        start = (page -1 ) * numPerPage;
        end = Math.min(page*numPerPage,size);
        List<User> listAllUser = ufa.getListByPage(lu, start, end);
       
        
        request.setAttribute("listUser", listAllUser);
        request.setAttribute("num", num);
        request.setAttribute("page", size);
        request.setAttribute("currenPage", page);
        request.setAttribute("endPage", end);
        request.setAttribute("listRole", lr);
        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);


        // tat ca list gui di deu la list phan trang 
        
        String gender_raw = request.getParameter("filterByGender");
        
        String roleName = request.getParameter("filterByRole");
        
        String status_raw = request.getParameter("filterByStatus");  
        
        String sortBySomeThing = request.getParameter("sort");
        
        String search = request.getParameter("search");
        
        String filterMore = request.getParameter("filterMore");
        SettingListDAO slDao = new SettingListDAO();
        UserForAdmin ufa = new UserForAdmin();
        List<User> listAllUser1 = ufa.getAllUser();
        List<Role> lr = ufa.getListRole();       
        List<User> filterByRole = ufa.filterUserByRole(roleName);
        
        
        // phan trang
        int page, numPerPage = slDao.getValueOfPaging();
        
        int size = listAllUser1.size();
        int num = (size % numPerPage ==0? (size/numPerPage):(size/numPerPage) + 1);
        
        String xpage = request.getParameter("page");
        if(xpage == null){
            page = 1;
        }else{
            page = Integer.parseInt(xpage);
        }
        
        
        
        int start, end;
        start = (page -1 ) * numPerPage;
        end = Math.min(page*numPerPage,size);
        List<User> listAllUser = ufa.getListByPage(listAllUser1, start, end);
        request.setAttribute("num", num);
        request.setAttribute("page", size);
        request.setAttribute("currenPage", page);
        // ket thuc phan trang
        
        System.out.println(request.getContextPath());
        
       int gender;
        // filter by gender
        if(gender_raw != null){
            try {
            gender = Integer.parseInt(gender_raw);
            List<User> lu = ufa.filterUserByGender(gender);
            if(gender == 2){
            request.setAttribute("listUser", listAllUser); // tat ca user
            request.setAttribute("listRole", lr);
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            return;
            }
            if(gender == 1 ){
                listAllUser = ufa.getListByPage(lu, start, end);
            request.setAttribute("listUser", listAllUser);
            request.setAttribute("Nam", 1); // user nam
            request.setAttribute("listRole", lr); // du lieu cho role
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            return;
            }
            if(gender == 0 ){
                listAllUser = ufa.getListByPage(lu, start, end);
            request.setAttribute("listUser", listAllUser);
            request.setAttribute("Nu", 0); // user Nu
            request.setAttribute("listRole", lr);     // du lieu cho role   
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            return;
            }
        } catch (Exception e) {
        }           
        }
        
        //filter by role 
        
        
        if(roleName != null){ 
            if(!roleName.equalsIgnoreCase("ALL")){
                listAllUser = ufa.getListByPage(filterByRole, start, end);
                request.setAttribute("roleNameResponse", roleName);
                request.setAttribute("listUser", listAllUser);   
                request.setAttribute("listRole", lr);
                request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                return;
            }else{  
                
                request.setAttribute("listUser", listAllUser); // tat ca user
                request.setAttribute("listRole", lr);
                request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                return;
            
            }                         
        }
        
        
        //filter by status
        int status;
        if(status_raw != null){
            try {
            status = Integer.parseInt(status_raw);
            List<User> filterByStatus = ufa.filterUserByStatus(status);
            if(status == 2){
                request.setAttribute("listUser", listAllUser); // tat ca user
                request.setAttribute("listRole", lr);
                request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                return;
            }else{
                listAllUser = ufa.getListByPage(filterByStatus, start, end);
            request.setAttribute("listUser", listAllUser); // tat ca user
            request.setAttribute("statusResponse", status_raw); // Dua ve status
            request.setAttribute("listRole", lr); 
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            return;
            }
        } catch (Exception e){

            }
        }
        
        
        //sort by something
        
        if(sortBySomeThing != null){
            List<User> listUserSort;
            switch (sortBySomeThing) {
                case "idSort":
                        listUserSort = ufa.sortBySomeThing("User_id");
                         listAllUser = ufa.getListByPage(listUserSort, start, end);
                        request.setAttribute("listUser", listAllUser);
                        request.setAttribute("sortResponse", 1); // gui qua giao dien de selected
                        request.setAttribute("listRole", lr);
                        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                    break;
                case "fullnameSort":
                        listUserSort = ufa.sortBySomeThing("Full_name");
                        listAllUser = ufa.getListByPage(listUserSort, start, end);
                        request.setAttribute("listUser", listAllUser);
                        request.setAttribute("sortResponse", 2);// gui qua giao dien de selected
                        request.setAttribute("listRole", lr);
                        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                    break;
                case "genderSort":
                        listUserSort = ufa.sortBySomeThing("Gender");
                        listAllUser = ufa.getListByPage(listUserSort, start, end);
                        request.setAttribute("listUser", listAllUser);
                        request.setAttribute("sortResponse", 3);// gui qua giao dien de selected
                        request.setAttribute("listRole", lr);
                        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                    break;   
                case "emailSort":
                        listUserSort = ufa.sortBySomeThing("Email");
                        listAllUser = ufa.getListByPage(listUserSort, start, end);
                        request.setAttribute("listUser", listAllUser);
                        request.setAttribute("sortResponse", 4);// gui qua giao dien de selected
                        request.setAttribute("listRole", lr);
                        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                    break;
                case "mobileSort":
                        listUserSort = ufa.sortBySomeThing("Phone_number");
                        listAllUser = ufa.getListByPage(listUserSort, start, end);
                        request.setAttribute("listUser", listAllUser);
                        request.setAttribute("sortResponse", 5);// gui qua giao dien de selected
                        request.setAttribute("listRole", lr);
                        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                    break;
                case "roleSort":
                        listUserSort = ufa.sortBySomeThing("Role_id");
                        listAllUser = ufa.getListByPage(listUserSort, start, end);
                        request.setAttribute("listUser", listAllUser);
                        request.setAttribute("sortResponse", 6);// gui qua giao dien de selected
                        request.setAttribute("listRole", lr);
                        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                    break;
                case "statusSort":
                        listUserSort = ufa.sortBySomeThing("deleted");
                        listAllUser = ufa.getListByPage(listUserSort, start, end);
                        request.setAttribute("listUser", listAllUser);
                        request.setAttribute("sortResponse", 7);// gui qua giao dien de selected
                        request.setAttribute("listRole", lr);
                        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                    break;
                case "allsort":
                        request.setAttribute("listUser", listAllUser); // tat ca user
                        request.setAttribute("listRole", lr);
                        request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
                    break;
    
            }
        }
        //search by something
        
        
        if(search != null){
            List<User> listSearch = ufa.searchByNameEmailMobile(search);
            listAllUser = ufa.getListByPage(listSearch, start, end);
            request.setAttribute("listUser", listAllUser); // tat ca user
            request.setAttribute("listRole", lr);
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            return;
        }
        
        
        //filter more
        if(filterMore != null){ // 10 user moi tao
            if(filterMore.equals("allNewUser")){
                request.setAttribute("listUser", listAllUser); // tat ca user
                request.setAttribute("listRole", lr);
                request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            }
            
            if(filterMore.equals("newUserThisMoth")){
                
                List<User> listNewUser = ufa.get10UserCreateNewInThisMonth();
            request.setAttribute("listUser", listNewUser); 
            request.setAttribute("newUserResponse", "newUserResponse"); // select value
            request.setAttribute("listRole", lr);
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            return;
            
            }
            
            if(filterMore.equals("newUserInactive")){
                List<User> listNewUserInactive = ufa.get10UserCreateNewInThisMonthButInactive();
            request.setAttribute("listUser", listNewUserInactive); 
            request.setAttribute("newUserResponse", "newUserInactiveResponse"); // select value
            request.setAttribute("listRole", lr);
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            return;
            }
            
            if(filterMore.equals("newUserNewUpdate")){
                List<User> newUserNewUpdate = ufa.get10UserNewUpdate();
            request.setAttribute("listUser", newUserNewUpdate); 
            request.setAttribute("newUserResponse", "newUserUpdate"); // select value
            request.setAttribute("listRole", lr);
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
            return;
            }
            
            
        }
            request.getRequestDispatcher("views/admin/item-page/userlist.jsp").forward(request, response);
      
        
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

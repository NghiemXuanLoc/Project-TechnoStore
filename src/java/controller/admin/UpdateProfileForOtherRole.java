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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateProfileForOtherRole", urlPatterns = {"/updateProfileForOtherRole"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UpdateProfileForOtherRole extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfileForOtherRole</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileForOtherRole at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý khi yêu cầu GET tới /userProfile

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("account");

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("Phone");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String dob = request.getParameter("dateofbirth");
        Part filePart = request.getPart("imageProfile");
        boolean check = true;
        String redirectUrl;
        User updatedUser = dao.getUserProfile(u.getUserId());
        switch (updatedUser.getRole().getRoleId()) {
            case 1: // admin
                redirectUrl = "/adminDashboard";
                break;
            case 2: // Marketing
                redirectUrl = "/postlist";
                break;
            case 3: // Sale
                redirectUrl = "/saledashboard";
                break;
            default:
                redirectUrl = "/home";
                break;
        }
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            String UPLOAD_DIRECTORY = "images/users";
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Thêm timestamp vào tên file để tránh trùng lặp
            fileName = System.currentTimeMillis() + "_" + fileName;
            String filePath = uploadPath + File.separator + fileName;

            try {
                // Lưu file vào đường dẫn đã tạo
                filePart.write(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File upload failed due to permission issues.");
                return;
            }

            // Đảm bảo file đã được lưu thành công trước khi set vào session
            fileName = "images/users/" + fileName;
        } else {
            // Nếu không có file upload mới, sử dụng hình ảnh cũ
            fileName = updatedUser.getImage();
        }

        // Validate và cập nhật thông tin người dùng
        Date dateOfBirth = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateOfBirth = new Date(sdf.parse(dob).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dateOfBirth != null) {
            Calendar tenYearsAgo = Calendar.getInstance();
            tenYearsAgo.add(Calendar.YEAR, -10);
            if (dateOfBirth.after(tenYearsAgo.getTime())) {
                session.setAttribute("errDob", "Date of birth must be at least 10 years ago.");
                check = false;
                response.sendRedirect(request.getContextPath() + redirectUrl);
                return;
            }
        }

        int gender_raw = "male".equals(gender) ? 1 : 0;
        
        if (fullName.trim().equals("")) {
            session.setAttribute("errName", "The name cannot empty");
            check = false;
            response.sendRedirect(request.getContextPath() + redirectUrl);
        }
        
        if (!fullName.matches("^[\\p{L}\\p{N}\\s]*$")) {
            session.setAttribute("errName", "The name cannot contain special characters");
            check = false;
            response.sendRedirect(request.getContextPath() + redirectUrl);
        }

        if (!phone.isEmpty()) {
            if (!phone.matches("^0\\d{9}$")) {
                session.setAttribute("errPhone", "Please input phone length = 10, start with 0");
                request.setAttribute("User", u);
                check = false;
                response.sendRedirect(request.getContextPath() + redirectUrl);
            }
        } else {
            phone = null;
        }

        if (!address.isEmpty()) {
            if (!address.matches("^[\\p{L}\\p{N}\\s\\-]*$")) {
                session.setAttribute("errAddress", "The address cannot contain special characters");
                request.setAttribute("User", u);
                check = false;
                response.sendRedirect(request.getContextPath() + redirectUrl);
            }
        } else {
            address = null;
        }
        
        if (check == true) {

            session.setAttribute("account", updatedUser);
            session.setAttribute("Sucess", "Update success!!!");
            dao.updateUserProfile(fullName, gender_raw, email, phone, address, dateOfBirth, fileName, u.getUserId());
        }

        
        UserForAdmin userForAdminDAO = new UserForAdmin();//code user profile cua ha
        User account = (User) session.getAttribute("account");
        if (account != null) {
            session.setAttribute("userProfileForAdmin", userForAdminDAO.getUserById(account.getUserId())); //code user profile cua ha
        }
        
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }

}

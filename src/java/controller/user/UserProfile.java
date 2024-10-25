package controller.user;

import dal.UserDAO;
import dal.UserForAdmin;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.Calendar;
import model.User;

@WebServlet(name = "UserProfile", urlPatterns = {"/userProfile"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UserProfile extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//            request.getRequestDispatcher("userProfile").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("account");
        UserForAdmin userForAdminDAO = new UserForAdmin();
        User u = userForAdminDAO.getUserById(userSession.getUserId());
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("Phone");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String dob = request.getParameter("dateofbirth");
        Part filePart = request.getPart("imageProfile");

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
            fileName = u.getImage();
        }

        // Validate và cập nhật thông tin người dùng
        Date dateOfBirth = null;

        if (dob != null && !dob.trim().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateOfBirth = new Date(sdf.parse(dob).getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Calendar tenYearsAgo = Calendar.getInstance();
            tenYearsAgo.add(Calendar.YEAR, -10);
            if (dateOfBirth.after(tenYearsAgo.getTime())) {
                session.setAttribute("errDob", "Date of birth must be at least 10 years ago.");
                request.setAttribute("User", u);
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
        }

        int gender_raw = "male".equals(gender) ? 1 : 0;
        
        if (fullName.trim().equals("")) {
            session.setAttribute("errName", "The name cannot empty");
            request.setAttribute("User", u);
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        
        if (!fullName.matches("^[\\p{L}\\p{N}\\s]*$")) {
            session.setAttribute("errName", "The name cannot contain special characters");
            request.setAttribute("User", u);
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        if (!phone.isEmpty() && phone != null) {
            if (!phone.matches("^0\\d{9}$")) {
                session.setAttribute("errPhone", "Please input phone length = 10, start with 0");
                request.setAttribute("User", u);
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
        } else {
            phone = null;
        }

        if (!address.isEmpty() && address != null) {
            if (!address.matches("^[\\p{L}\\p{N}\\s\\-]*$")) {
                session.setAttribute("errAddress", "The address cannot contain special characters");
                request.setAttribute("User", u);
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
        } else {
            address = null;
        }

        dao.updateUserProfile(fullName, gender_raw, email, phone, address, dateOfBirth, fileName, u.getUserId());
        User updatedUser = dao.getUserProfile(u.getUserId());
        session.setAttribute("account", updatedUser);
        session.setAttribute("Sucess", "Update success!!!");
        response.sendRedirect(request.getContextPath() + "/home");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.SettingListDAO;
import dal.UserDAO;
import dal.UserForAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Role;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddUser", urlPatterns = {"/addUser"})
public class AddUser extends HttpServlet {

    private static final String SMTP_USERNAME = "hanbhe171364@fpt.edu.vn";
    private static final String SMTP_PASSWORD = "mhkk rsvl dqbq vfes";
    private static final String SMTP_HOST = "smtp.example.com";
    private static final String SMTP_PORT = "587";

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
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUser at " + request.getContextPath() + "for ITER2 </h1>");
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
        request.getRequestDispatcher("views/admin/item-page/adduser.jsp").forward(request, response);

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

        // Assuming UserForAdmin is a class to manage admin actions for users
        UserForAdmin dao = new UserForAdmin();
        List<Role> listRole = dao.getListRole();
        List<User> listUser = dao.getAllUser();
        request.setAttribute("listRole", listRole);

        // Extract parameters from request
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String genderRaw = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String roleRaw = request.getParameter("role");
        String statusRaw = request.getParameter("status");
        String resetToken = generateResetToken(); // Tạo token reset password
        java.util.Date expiryDate = calculateExpiryDate();
        // Declare and initialize variables

        request.setAttribute("nameRes", fullName);
        request.setAttribute("passRes", password);
        request.setAttribute("emailRes", email);
        request.setAttribute("genRes", genderRaw);
        request.setAttribute("phoneRes", phone);
        request.setAttribute("addRes", address);
        request.setAttribute("roleRes", roleRaw);
        request.setAttribute("statusRes", statusRaw);

        for (User user : listUser) {
            if (user.getEmail().equals(email)) {
                request.setAttribute("Err", "Email already exists");
                request.getRequestDispatcher("views/admin/item-page/adduser.jsp").forward(request, response);
                return;
            }
        }
        
        if (fullName.trim().equals("")) {
            request.setAttribute("Err", "Username cannot empty");
            request.getRequestDispatcher("views/admin/item-page/adduser.jsp").forward(request, response);
            return;
        }
        
        if(password.trim().equals("")){
            request.setAttribute("Err", "Password cannot empty");
            request.getRequestDispatcher("views/admin/item-page/adduser.jsp").forward(request, response);
            return;
        }

        if (!fullName.matches("^[\\p{L}\\p{N}\\s]*$")) {
            request.setAttribute("Err", "Username cannot contain special characters");
            request.getRequestDispatcher("views/admin/item-page/adduser.jsp").forward(request, response);
            return;
        }

        if (!phone.isEmpty()) {
            if (!phone.matches("^0\\d{9}$")) {
                request.setAttribute("Err", "The phone number must consist of 10 numbers and start with 0");
                request.getRequestDispatcher("views/admin/item-page/adduser.jsp").forward(request, response);
                return;
            }
        } else {
            phone = null;
        }

        if (!address.isEmpty()) {
            if (!address.matches("^[\\p{L}\\p{N}\\s\\-]*$")) {
                request.setAttribute("Err", "The address cannot contain special characters");
                request.getRequestDispatcher("views/admin/item-page/adduser.jsp").forward(request, response);
                return;
            }
        } else {
            address = null;
        }
        int role, status, gender;
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try {
            role = Integer.parseInt(roleRaw);
            status = Integer.parseInt(statusRaw);
            gender = Integer.parseInt(genderRaw);

            // Add user using the admin method
            dao.addUserByAdmin(role, fullName, hashedPassword, gender, email, phone, address, status, resetToken, expiryDate);

            // Send email with plaintext password
            sendEmail(email, password);

            // Provide feedback to the user
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error during registration: " + e.getMessage());
        }

        // Redirect to add user page or another relevant page
        request.setAttribute("Success", "Send Email Add User Successfully!!!");
        request.getRequestDispatcher("views/admin/item-page/adduser.jsp").forward(request, response);
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

    public boolean sendEmail(String email, String password) {

        boolean test = false;
        String fromEmail = "hanbhe171364@fpt.edu.vn";
        String fromPass = "mhkk rsvl dqbq vfes";
        SettingListDAO dao = new SettingListDAO();
        try {
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, fromPass);
                }
            });
            //ma hoa email
            String encodedString = Base64.getEncoder().encodeToString(email.getBytes());
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mess.setSubject("Registered successfully");
//            Thread.sleep(dao.getValueOfTimeToSendEmail());
            mess.setText("Registered successfully! Your password: " + password );
//            + ". Click here to login: http://localhost:9999/TechStore/verifyAddUserMail?email=" + encodedString

            Transport.send(mess);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

    private String generateResetToken() {
        // Tạo một chuỗi token reset mật khẩu duy nhất
        String token = UUID.randomUUID().toString(); // Sử dụng UUID để tạo một chuỗi ngẫu nhiên
        return token;
    }

    private java.util.Date calculateExpiryDate() {
        // Tính toán thời gian hết hạn của token
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.HOUR, 24); // Thêm 24 giờ vào thời gian hiện tại
        return calendar.getTime();
    }
}

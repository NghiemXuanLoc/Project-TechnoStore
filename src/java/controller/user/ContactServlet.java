/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.ContactDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Contact;
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name = "ContactServlet", urlPatterns = {"/contact"})
public class ContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String rating = request.getParameter("rating");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        ContactDAO cd = new ContactDAO();

        try {
            User account = (User) session.getAttribute("account");

            if (account == null) {
                int id = ud.getUserIdAnonymous();

                if (id == -1) {
                    ud.insertUserAnonymous("anonymous", "anonymous@gmail.com");
                    account = ud.getUserById(ud.getUserIdAnonymous());
                }

                account = ud.getUserById(id);
            }

            Contact contact = new Contact(-1, account, fullName, email, Integer.parseInt(gender), phoneNumber, Integer.parseInt(rating), subject, content, null, null);

            cd.insert(contact);

            request.setAttribute("success", "Thank you for giving feedback about the website, we will use it as motivation to improve in the future");

            request.setAttribute("subject", subject);
            
            request.setAttribute("content", content);
            
            request.setAttribute("rating", rating);
            
            
            request.getRequestDispatcher("views/user/item-page/contact.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("loi o contactservlet method post: " + e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("views/user/item-page/contact.jsp").forward(request, response);
    }

}

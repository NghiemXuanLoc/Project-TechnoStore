/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.ProductContributionDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name = "ProductContributionServlet", urlPatterns = {"/productcontribution"})
public class ProductContributionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO ud = new UserDAO();
        ProductContributionDAO pcd = new ProductContributionDAO();
        PrintWriter print = response.getWriter();

        String name = request.getParameter("nameUserContribution");
        String email = request.getParameter("emailUserContribution");
        String feedback = request.getParameter("feedbackContribution");
        String action = request.getParameter("action");
        String productId = request.getParameter("productId");

//        User account = ud.getAccountByEmail(email);
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account != null) {
//                print.write("Email already exists, please login or enter another email!");
            pcd.insert(account.getUserId(), Integer.parseInt(productId), feedback);
            print.write("Your response has been noted");
        } else {
//                ud.insertUserAnonymous(name, email);
//                User accountAnonymous = ud.getAccountByEmail(email);
//                pcd.insert(accountAnonymous.getUserId(), Integer.parseInt(productId), feedback);
            print.write("Please login to use this feature!");
        }

//        if (action.equalsIgnoreCase("account")) {
//            pcd.insert(account.getUserId(), Integer.parseInt(productId), feedback);
//            print.write("Your response has been noted");
//        } else {
//            if (account != null) {
////                print.write("Email already exists, please login or enter another email!");
//                pcd.insert(account.getUserId(), Integer.parseInt(productId), feedback);
//                print.write("Your response has been noted");
//            } else {
////                ud.insertUserAnonymous(name, email);
////                User accountAnonymous = ud.getAccountByEmail(email);
////                pcd.insert(accountAnonymous.getUserId(), Integer.parseInt(productId), feedback);
//                print.write("Please login to use this feature!");
//            }
//        }
    }

}

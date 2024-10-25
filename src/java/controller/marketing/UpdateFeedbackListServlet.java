/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.OrdersDAO;
import dal.FeedbackListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "UpdateFeedbackListServlet", urlPatterns = {"/updatefeedbackstatus"})
public class UpdateFeedbackListServlet extends HttpServlet {

   
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String feedbackIdParam = request.getParameter("feedbackId");
    String deletedParam = request.getParameter("deleted");
    HttpSession session = request.getSession();

    if (feedbackIdParam == null || deletedParam == null) {
        session.setAttribute("errUpdateStatus", "Invalid parameters");
        response.sendRedirect(request.getContextPath() + "/feedbacklist");
        return;
    }

    try {
        int feedbackId = Integer.parseInt(feedbackIdParam);
        int deleted = Integer.parseInt(deletedParam);
        System.out.println(feedbackId);
        System.out.println(deleted);
        FeedbackListDAO feedbackDAO = new FeedbackListDAO();
        boolean success = feedbackDAO.updateFeedbackStatus(feedbackId, deleted);

        if (success) {
            session.setAttribute("successUpdateStatus", "Update Successfully");
        } else {
            session.setAttribute("errUpdateStatus", "Failed update status");
        }
    } catch (NumberFormatException e) {
        session.setAttribute("errUpdateStatus", "Invalid number format");
    }

    response.sendRedirect(request.getContextPath() + "/feedbacklist");
}

}


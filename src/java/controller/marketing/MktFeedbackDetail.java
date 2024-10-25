/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.marketing;

import dal.FeedbackDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FeedbackDetail;

/**
 *
 * @author admin
 */
@WebServlet(name="MktFeedbackDetail", urlPatterns={"/mktFeedbackDetail"})
public class MktFeedbackDetail extends HttpServlet {
   
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
            out.println("<title>Servlet MktFeedbackDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktFeedbackDetail at " + request.getContextPath () + "</h1>");
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
        FeedbackDetailDAO dao = new FeedbackDetailDAO();
        
        String idFeedback = request.getParameter("idFeedback");
        FeedbackDetail fd = dao.getFeedbackById(idFeedback);
        int star = fd.getRatedStar();
        request.setAttribute("Star", star);
        request.setAttribute("FeedbackDetail", fd);
        request.getRequestDispatcher("views/marketing/item-page/feedbackdetail.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        String feedbackId = request.getParameter("feedbackId");
        String feedbackStatus = request.getParameter("feedbackStatus");
        FeedbackDetailDAO dao = new FeedbackDetailDAO();
        dao.updateFeedbackById(feedbackStatus, feedbackId);
        FeedbackDetail fd = dao.getFeedbackById(feedbackId);
        int star = fd.getRatedStar();
        request.setAttribute("Star", star);
        request.setAttribute("FeedbackDetail", fd);
        request.setAttribute("updateSuccess", "Update Successfully");
        request.getRequestDispatcher("views/marketing/item-page/feedbackdetail.jsp").forward(request, response);
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

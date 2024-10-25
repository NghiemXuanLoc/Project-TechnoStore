/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.BlogDetailDAO;
import dal.PostListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BlogDetail;

/**
 *
 * @author quang
 */
@WebServlet(name = "PostDetailServlet", urlPatterns = {"/postdetail"})
public class PostDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("postID");
        if (id != null && !id.isEmpty()) {
            int pID = Integer.parseInt(id);
            PostListDAO bd = new PostListDAO();
            BlogDetail bdetail = bd.getBlogDetailById(pID);

            request.setAttribute("postdetail", bdetail);
            request.getRequestDispatcher("views/marketing/item-page/postdetail.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("views/marketing/item-page/postdetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("postID");
        if (id != null && !id.isEmpty()) {
            int postID = Integer.parseInt(id);

            PostListDAO bd = new PostListDAO();
            bd.updateStatus(postID);  // Gọi phương thức để cập nhật trạng thái
            // Gửi trực tiếp sau khi update trạng thái về trang hiện tại
            response.sendRedirect(request.getContextPath() + "/postdetail");
        } else {
             request.getRequestDispatcher("views/marketing/item-page/postdetail.jsp").forward(request, response);
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

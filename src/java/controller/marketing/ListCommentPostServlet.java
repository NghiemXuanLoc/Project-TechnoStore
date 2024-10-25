package controller.marketing;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Comment;

/**
 *
 * @author PC
 */
@WebServlet(urlPatterns = {"/listcommentpost"})
public class ListCommentPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentDAO cd = new CommentDAO();

        HttpSession session = request.getSession();
        
        List<Comment> listCommentAll = cd.getCommentAll();

        session.setAttribute("listAll", listCommentAll);
        request.getRequestDispatcher("views/marketing/item-page/listcommentpost.jsp").forward(request, response);

    }

}

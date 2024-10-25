/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

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
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoadMoreCommentBlogDetailServlet", urlPatterns = {"/loadmorecommentblogdetail"})
public class LoadMoreCommentBlogDetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String blogDetailId_raw = request.getParameter("blogDetailId");
        String countComment_raw = request.getParameter("countComment");

        HttpSession session = request.getSession();

        CommentDAO cd = new CommentDAO();

        User account = (User) session.getAttribute("account");

        PrintWriter print = response.getWriter();

        try {
            List<Comment> listComment = cd.loadMoreComments(Integer.parseInt(blogDetailId_raw), Integer.parseInt(countComment_raw));

            String htmls = "";

            for (Comment comment : listComment) {
                String choPheDuyet = comment.getDeleted() == 0 ? "choPheDuyet" : "";
                String edit = "";
                String delete = "";
                String queue = "";
                String display = comment.getDeleted() == 0 ? "block" : "none";

                for (int i = 1; i <= 5; i++) {
                    if (i <= comment.getRating()) {
                        queue += "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>\n";
                    } else {
                        queue += "<i class=\"fa fa-star-o\" aria-hidden=\"true\"></i>\n";
                    }
                }

                if (account != null && account.getUserId() == comment.getUser().getUserId()) {
                    edit = "<span onclick=\"selectEditComment(" + comment.getCommentId() + ", `" + comment.getContent() + "`, " + Integer.parseInt(blogDetailId_raw) + ")\"\n"
                            + "                                                        class=\"btn btn-sm btn-warning\"\n"
                            + "                                                        data-bs-toggle=\"modal\"\n"
                            + "                                                        data-bs-target=\"#exampleModal\">Edit<i style=\"margin-left: 10px;cursor: pointer;\" class=\"fa-solid fa-pen-to-square\"></i></span>\n";

                    delete = "<span onclick=\"deleteComment(" + comment.getCommentId() + ", " + Integer.parseInt(blogDetailId_raw) + ")\" style=\"color: white\" class=\"btn btn-sm btn-danger\">Delete <i class=\"fa-solid fa-trash\"></i></span>\n";
                }
                htmls += "<li style=\"position: relative\" class=\"comment\">\n"
                        + "                                    <div class=\"comment-author " + choPheDuyet + "\">\n"
                        + "                                        <img style=\"width: 75px;height: auto\" src=\"" + comment.getUser().getImage() + "\" alt=\"\">\n"
                        + "                                    </div>\n"
                        + "                                    <div class=\"comment-text " + choPheDuyet + "\">\n"
                        + "                                        <div class=\"comment-metadata\">\n"
                        + "                                            <div class=\"name\">\n"
                        + "                                                " + comment.getUser().getFullName() + " : <span class=\"ms-2\">" + comment.getCommentDate() + " |</span>\n"
                        + "\n"
                        + edit
                        + delete
                        + "                                                <span class=\"mx-5\">" + comment.getLikes() + "<i style=\"margin-left: 10px;cursor: pointer;\" class=\"fa-solid fa-thumbs-up\"></i></span>\n"
                        + "                                                <span>" + comment.getReport() + "<i style=\"margin-left: 10px;cursor: pointer;\" class=\"fa-solid fa-flag\"></i></span>\n"
                        + "                                            </div>\n"
                        + "\n"
                        + "                                            <div class=\"queue\">\n"
                        + "\n"
                        + queue
                        + "\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                        <div class=\"comment-content\">\n"
                        + "                                            <p>\n"
                        + "                                                " + comment.getContent() + "\n"
                        + "                                            </p> \n"
                        + "                                        </div>\n"
                        + "                                        <div class=\"clearfix\"></div>\n"
                        + "                                    </div> \n"
                        + "\n"
                        + "                                    <span class=\"nofitication\" style=\"display: " + display + "\">Đang chờ phê duyệt</span>\n"
                        + "                                </li>\n";
            }

            print.write(htmls);
        } catch (Exception e) {
            System.out.println("loi chuyen doi so loadMoreCommentBlogDetailServlet: " + e);
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import model.*;
import java.util.*;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author quang
 */
@WebServlet(name = "AddPostServlet", urlPatterns = {"/addpost"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BlogCategoryDAO bd = new BlogCategoryDAO();
        List<BlogCategory> lb = bd.getListBlogCategory();
        request.setAttribute("lPostCategory", lb);
        request.getRequestDispatcher("views/marketing/item-page/addpost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String newCategory = request.getParameter("newCategory");
            String shortDescription = request.getParameter("shortdescription");
            String content = request.getParameter("content");
            String dateCreated = request.getParameter("datecreated");
            Part partThumbnail = request.getPart("thumbnail");
            String author = request.getParameter("author");

            PostListDAO pdao = new PostListDAO();
            int categoryPost = 0;
            if (category != null && !category.isEmpty()) {
                if (category.equals("new")) {
                    if (newCategory == null || newCategory.trim().isEmpty()) {
                        return;
                    }
                    newCategory = newCategory.trim().replaceAll("\\s+", " ");
                    if (!newCategory.matches("[a-zA-Z0-9 ]+")) {
                        return;
                    }
                    categoryPost = pdao.addCategory(newCategory);
                } else {
                    categoryPost = Integer.parseInt(category);
                }
            } else {
                return;
            }

            String thumbnailFileName = null;
            if (partThumbnail != null && partThumbnail.getSize() > 0) {
                String uploadLink = "images/blogs/news/";
                thumbnailFileName = Paths.get(partThumbnail.getSubmittedFileName()).getFileName().toString();
                String thumbnailPath = getServletContext().getRealPath("") + File.separator + uploadLink;

                File dir = new File(thumbnailPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filePath = thumbnailPath + File.separator + thumbnailFileName;
                try {
                    partThumbnail.write(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                thumbnailFileName = "images/blogs/news/" + thumbnailFileName;
            }
            if (title == null || title.isEmpty()) {
                return;
            }
            if (shortDescription == null || shortDescription.isEmpty()) {
                return;
            }
            if (content == null || content.isEmpty()) {
                return;
            }
           java.sql.Date dPost = new java.sql.Date(System.currentTimeMillis());
            pdao.addPost(categoryPost, 3, thumbnailFileName, title, shortDescription, content, dPost, author);
            request.getRequestDispatcher("views/marketing/item-page/addpost.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Can't add new post!");
            e.printStackTrace();
        }
    }
}

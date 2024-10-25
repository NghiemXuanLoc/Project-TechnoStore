/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.*;
import java.util.List;
import model.*;

/**
 *
 * @author quang
 */
@WebServlet(name = "EditPostServlet", urlPatterns = {"/editpost"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EditPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String eId = request.getParameter("postID");
        if (eId != null && !eId.isEmpty()) {
            BlogDetailDAO bd = new BlogDetailDAO();
            BlogDetail bdetail = bd.getContentByID(eId);
            BlogCategoryDAO bc = new BlogCategoryDAO();
            List<BlogCategory> lb = bc.getListBlogCategory();

            request.setAttribute("lPostCategory", lb);
            request.setAttribute("bdetail", bdetail);
            request.getRequestDispatcher("views/marketing/item-page/editpost.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("views/marketing/item-page/editpost.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String postID = request.getParameter("postID");
        String category = request.getParameter("category");
        Part thumbnail = request.getPart("thumbnail");
        String title = request.getParameter("title");
        String shortDes = request.getParameter("shortdes");
        String content = request.getParameter("content");
        String postCreated = request.getParameter("blogcreated");
        String postUpdatedDate = request.getParameter("blogdateupdate");
        String author = request.getParameter("author");
        String newCategoryName = request.getParameter("newCategory");
        PostListDAO pd = new PostListDAO();
        BlogDetailDAO checkPost = new BlogDetailDAO();
        BlogCategoryDAO cDAO = new BlogCategoryDAO();

        /*Xử lí nhận vào mã bài viết được chỉnh sửa*/
        int pid = 0;
        try {
            pid = Integer.parseInt(postID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        BlogDetail b = checkPost.getBlogDetailById(pid);
        String thumbnailFileName = b.getThumbnail();
        if (thumbnail != null && thumbnail.getSize() > 0) {
            String uploadLink = "images/blogs/news";
            thumbnailFileName = thumbnail.getSubmittedFileName();
            String thumbnailPath = getServletContext().getRealPath("") + File.separator + uploadLink;

            File dir = new File(thumbnailPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = thumbnailPath + File.separator + thumbnailFileName;
            try {
                thumbnail.write(filePath);
                b.setThumbnail(thumbnailFileName);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int categoryId = 0;
        if ("new".equals(category) && newCategoryName != null && !newCategoryName.isEmpty()) {
            BlogCategory newCategory = new BlogCategory();
            newCategory.setBlogCategoryName(newCategoryName);
            pd.addCategory(newCategoryName);
            newCategory = cDAO.getBlogCategoryById(categoryId);
            if (newCategory != null) {
                categoryId = newCategory.getBlogCategoryId();
            }
        } else {
            try {
                categoryId = Integer.parseInt(category);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }

            BlogCategory existingCategory = cDAO.getBlogCategoryById(categoryId);
            if (existingCategory == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID");
                return;
            }
            b.setBlogCategory(existingCategory);
        }
        if (title != null && !b.getTitle().equals(title)) {
            b.setTitle(title);
        }
        if (shortDes != null && !b.getShortDescription().equals(shortDes)) {
            b.setShortDescription(shortDes);
        }
        if (content != null && !b.getContent().equals(content)) {
            b.setContent(content);
        }
        java.sql.Date pCreated = null;
        if (postCreated != null && !postCreated.isEmpty()) {
            pCreated = java.sql.Date.valueOf(postCreated);
        }

        java.sql.Date updateDate;
        if (postUpdatedDate != null && !postUpdatedDate.isEmpty()) {
            updateDate = java.sql.Date.valueOf(postUpdatedDate);
        } else {
            updateDate = new java.sql.Date(System.currentTimeMillis());
        }

        if (author != null && !b.getAuthor().equals(author)) {
            b.setAuthor(author);
        }
        String imagePath = thumbnailFileName.startsWith("images/blogs/news/") ? thumbnailFileName : "images/blogs/news/" + thumbnailFileName;
        pd.updatePost(pid, categoryId, imagePath, title, shortDes, content, pCreated, updateDate, author);
        request.getRequestDispatcher("views/marketing/item-page/editpost.jsp").forward(request, response);
    }

}

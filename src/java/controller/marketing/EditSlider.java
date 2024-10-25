/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.MarketingSliderDao;
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
import model.Slider;
import java.text.*;
import java.util.List;

/**
 *
 * @author quang
 */
@WebServlet(name = "EditSlider", urlPatterns = {"/editslider"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EditSlider extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("sId");
        if (id != null && !id.isEmpty()) {
            int pID = Integer.parseInt(id);
            MarketingSliderDao bd = new MarketingSliderDao();
            Slider sdetail = bd.getSliderById(pID);
            List<Slider> sliderList = bd.getSliderAll();
            request.setAttribute("sliderList", sliderList);
            request.setAttribute("sdetail", sdetail);
            request.getRequestDispatcher("views/marketing/item-page/editslider.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("views/marketing/item-page/editslider.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sliderID = request.getParameter("sliderID");
        Part image = request.getPart("thumbnail");
        String title = request.getParameter("title");
        String backlink = request.getParameter("backlink");
        String active = request.getParameter("deleted");
        MarketingSliderDao mSliderDao = new MarketingSliderDao();

        int sID = 0;
        try {
            sID = Integer.parseInt(sliderID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        Slider s = mSliderDao.getSliderById(sID);
        String thumbnailFileName = s.getImage(); // Use the existing image name initially

        if (image != null && image.getSize() > 0) {
            String uploadLink = "images/slider";
            thumbnailFileName = image.getSubmittedFileName();
            String thumbnailPath = getServletContext().getRealPath("") + File.separator + uploadLink;

            File dir = new File(thumbnailPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = thumbnailPath + File.separator + thumbnailFileName;
            try {
                image.write(filePath);
                s.setImage(thumbnailFileName);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        if (title != null && !s.getTitle().equals(title)) {
            s.setTitle(title);
        }
        if (backlink != null && !s.getBackLink().equals(backlink)) {
            s.setBackLink(backlink);
        }
        int deleted = Integer.parseInt(active);
        if (s.getDeleted() != deleted) {
            s.setDeleted(deleted);
        }
        String newSliderId = request.getParameter("topos");
        if (newSliderId != null && !newSliderId.isEmpty()) {
            int newId = Integer.parseInt(newSliderId);
            mSliderDao.swapSliderId(sID, newId);
        }

        String imagePath = thumbnailFileName.startsWith("images/slider/") ? thumbnailFileName : "images/slider/" + thumbnailFileName;
        mSliderDao.updateSlider(sID, 2, title, imagePath, backlink, deleted);
        request.getRequestDispatcher("views/marketing/item-page/editslider.jsp").forward(request, response);
    }
}

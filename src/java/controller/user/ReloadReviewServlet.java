/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import constant.Iconstant;
import dal.FeedbackProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.FeedbackProduct;

/**
 *
 * @author PC
 */
public class ReloadReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String select = request.getParameter("select");
        String productId_raw = request.getParameter("productId");
        String numberPage_raw = request.getParameter("numberPage");

        FeedbackProductDAO fpd = new FeedbackProductDAO();
        try {
            int productId = Integer.parseInt(productId_raw);
            int numberPage = Integer.parseInt(numberPage_raw);
            System.out.println("number page: " + numberPage);
            
            List<FeedbackProduct> listFeedback;

            if (select.equalsIgnoreCase("all")) {
                listFeedback = fpd.getFeedbackProductAllByProductIdPagination(productId, numberPage);
            } else if (select.equalsIgnoreCase("image")) {
                listFeedback = fpd.getFeedbackProductImageByProductIdPagination(productId, numberPage);
            } else {
                int star = Integer.parseInt(select);
                listFeedback = fpd.getStarFeedbackPagination(productId, star, numberPage);
            }

            String result = "";
            for (FeedbackProduct review : listFeedback) {
                int star = review.getRating();
                String starHtml = "";
                for (int i = 1; i <= 5; i++) {
                    if (i <= star) {
                        starHtml += "<i style=\"color: #000\" class=\"fa fa-star\" aria-hidden=\"true\"></i>\n";
                    } else {
                        starHtml += "<i style=\"color: #000\" class=\"fa fa-star-o\" aria-hidden=\"true\"></i>\n";
                    }
                }

                result += "<li>\n"
                        + "                                                                                                                                    <div  class=\"review-metadata\">\n"
                        + "                                                                                                                                        <a href=\"javascrip:void(0)\" title=\"\">\n"
                        + "                                                                                                                                            <img style=\"width: 8%;height: auto;border-radius: 50%\" src=\"" + review.getUser().getImage() + "\" alt=\"\">\n"
                        + "                                                                                                                                        </a>\n"
                        + "                                                                                                                                        <div style=\"margin-left: 10px\" class=\"name\">\n"
                        + "                                                                                                                                            " + (review.getUser().getRole().getRoleId() != 5 ? review.getUser().getFullName() : review.getFullName()) + " : <span>" + Iconstant.formatDate(review.getFeedbackDate()) + " (" + review.getProductDetail().getColor().getColorName() + "|"  + review.getProductDetail().getMemory().getMemorySize() + ")</span>\n"                                                                                                                                          
                        + "<br>"
                        + "                                                                                                                                        </div>\n"
                        + "                                                                                                                                        <div  class=\"queue\">\n"
                        + "                                                                                                                                            " + starHtml
                        + "                                                                                                                                        </div>\n"
                        + "                                                                                                                                    </div><!-- /.review-metadata -->\n"
                        + "\n"
                        + "                                                                                                                                    <div class=\"review-content image-feedback\">\n"
                        + "                                                                                                                                        <p>\n"
                        + "                                                                                                                                            " + review.getContent() + "\n"
                        + "                                                                                                                                        </p> \n"
                        + "                                                                                                                                    </div><!-- /.review-content -->\n"
                        + "                                                                                                                                </li>\n";
            }

            PrintWriter print = response.getWriter();
            print.write(result);

        } catch (NumberFormatException e) {
            System.out.println("loi chuyen doi so trong reload review: " + e);
        }

        System.out.println(select);

    }

}

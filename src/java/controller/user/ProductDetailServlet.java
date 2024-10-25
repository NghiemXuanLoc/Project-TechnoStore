/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import constant.Iconstant;
import dal.ColorDAO;
import dal.FeedbackProductDAO;
import dal.MemoryDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Color;
import model.FeedbackProduct;
import model.Memory;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author PC
 */
public class ProductDetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String option = request.getParameter("option");
        String productId_raw = request.getParameter("productId");

        HttpSession session = request.getSession();
        PrintWriter print = response.getWriter();

        ProductDAO pd = new ProductDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        FeedbackProductDAO fpd = new FeedbackProductDAO();
        ColorDAO cd = new ColorDAO();
        MemoryDAO md = new MemoryDAO();

        try {
            int productId = Integer.parseInt(productId_raw);

            // lay ve san pham muon xem chi tiet
            Product product = pd.getProductById(productId);

            // lay ve tat ca cac color
            List<Color> getColorAll = cd.getColorAll();

            // lay ve tat ca cac memory
            List<Memory> getMemoryAll = md.getMemoryAll();

            List<FeedbackProduct> getFeedbackAll = fpd.getFeedbackProductAllByProductId(productId);

            // xu ly lay ve so don co san, so don da ban cho tung loai san pham hoac 1 loai san pham rieng 
            int quantityStock, quantitySold;

            String priceSale = "";

            String priceOrigin = "";

            if (option.equalsIgnoreCase("common")) {
                quantityStock = pd.getQuantityStockByProductId(productId);
                quantitySold = pd.getQuantitySoldByProductId(productId);

                priceSale = Iconstant.formatCurrency(product.getMinPrice()) + " - " + Iconstant.formatCurrency(product.getMaxPrice());
                priceOrigin = Iconstant.formatCurrency(product.getMinPriceOrigin()) + " - " + Iconstant.formatCurrency(product.getMaxPriceOrigin());
            } else {
                String colorId_raw = request.getParameter("colorId");
                String memoryId_raw = request.getParameter("memoryId");

                int colorId = Integer.parseInt(colorId_raw);
                int memoryId = Integer.parseInt(memoryId_raw);
                quantityStock = pdd.getQuantityStockByProductDetailId(productId, colorId, memoryId);
                quantitySold = pdd.getQuantitySoldByProductDetailId(productId, colorId, memoryId);

                // lay ve productdetail 
                ProductDetail productDetail = pdd.getProductDetailByProductIdColorIdMemoryId(productId, colorId, memoryId);

                priceSale = Iconstant.formatCurrency(productDetail.getPriceSale());
                priceOrigin = Iconstant.formatCurrency(productDetail.getPriceOrigin());
            }

            String image = "                                    <a href='#' id=\"zoom\" class='zoom'><img src=\"" + product.getGallery().get(0) + "\" alt='' width='400' height='300' /></a>\n";

            String reviewStar = "";

            for (int i = 1; i <= (int) product.getAvgRating(); i++) {
                reviewStar += "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>\n";
            }

            for (int i = (int) product.getAvgRating(); i < Math.ceil(product.getAvgRating()); i++) {
                reviewStar += "<i class=\"fa fa-star-half-empty\" aria-hidden=\"true\"></i>\n";
            }

            for (int i = (int) Math.ceil(product.getAvgRating()); i < 5; i++) {
                reviewStar += "<i class=\"fa fa-star-o\" aria-hidden=\"true\"></i>\n";
            }

            String memory = "";

            for (int i = 0; i < getMemoryAll.size(); i++) {
                memory += "<span onclick=\"optionChoosePopup(this, 'memory', " + product.getProductId() + ", " + getMemoryAll.get(i).getMemoryId() + ")\"  id=\"memoryyy" + getMemoryAll.get(i).getMemoryId() + "\">" + getMemoryAll.get(i).getMemorySize() + "&nbsp;GB</span>";
            }

            String color = "";

            for (int i = 0; i < getColorAll.size(); i++) {
                color += "<span onclick=\"optionChoosePopup(this, 'color', " + product.getProductId() + ", " + getColorAll.get(i).getColorId() + ")\" id=\"colorrr" + getColorAll.get(i).getColorId() + "\">" + getColorAll.get(i).getColorName() + "</span>";
            }

            System.out.println(image);

            String htmls = "<div class=\"row\">\n"
                    + "                <div class=\"box-flexslider col-lg-5 col-md-5\">\n"
                    + "\n"
                    + "                    <div class=\"flexslider \">\n"
                    + "                        <ul class=\"slides\">\n"
                    + image
                    + "                        </ul><!-- /.slides -->\n"
                    + "                    </div><!-- /.flexslider -->\n"
                    + "\n"
                    + "                </div><!-- /.box-flexslider -->\n"
                    + "                <div class=\"product-detail style5 col-lg-7 col-md-7\">\n"
                    + "                    <div class=\"header-detail\">\n"
                    + "                        <h4 class=\"name\">" + product.getProductName() + "</h4>\n"
                    + "                        <div class=\"category\">\n"
                    + "                            " + product.getCategory().getCategoryName() + "\n"
                    + "                        </div>\n"
                    + "                        <div class=\"reviewed\">\n"
                    + "\n"
                    + "                            <div class=\"review\">\n"
                    + "                                <div class=\"queue\">\n"
                    + reviewStar
                    + "                                    <span>" + product.getAvgRating() + "</span>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"text\">\n"
                    + "                                    <span>" + getFeedbackAll.size() + " Reviews</span>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "\n"
                    + "                            <div style=\"text-align: start;\" class=\"status-product\">\n"
                    + "                                <span style=\"background-color: #f28b00\">Availablity:&nbsp;&nbsp;&nbsp;&nbsp;" + quantityStock + "</span>\n"
                    + "                                <span style=\"background-color: #f28b00; margin-top: 5px\">Sold:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + quantitySold + "</span>\n"
                    + "                            </div>\n"
                    + "\n"
                    + "                        </div>\n"
                    + "                    </div><!-- /.header-detail -->\n"
                    + "                    <div class=\"content-detail\">\n"
                    + "                            <div class=\"price\">\n"
                    + "                                <div class=\"regular\">\n"
                    + priceOrigin
                    + "                                </div>\n"
                    + "                                <div style=\"font-size: 20px\" class=\"sale\">\n"
                    + priceSale
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "\n"
                    + "                        <div class=\"info-text\">\n"
                    + "                           " + product.getBriefInfo() + "\n"
                    + "                        </div>\n"
                    + "                    </div><!-- /.content-detail -->\n"
                    + "\n"
                    + "                    <span class=\"option-note\">Memory:</span>\n"
                    + "                    <div class=\"option\" id=\"memory\">\n"
                    + memory
                    + "                    </div>\n"
                    + "                    <span class=\"option-note\" >Color:</span>\n"
                    + "                    <div class=\"option\" id=\"color\">\n"
                    + "\n"
                    + color
                    + "\n"
                    + "                    </div>\n"
                    + "                    <div class=\"footer-detail\">\n"
                    + "\n"
                    + "                        <div class=\"quanlity-box\">\n"
                    + "                            <div class=\"quanlity\">\n"
                    + "                                <span onclick=\"upDowQuantity('giam', 0)\" class=\"btn-down\"></span>\n"
                    + "                                <input onchange=\"limit()\" id=\"quantity0\" type=\"number\" name=\"number\" value=\"\" min=\"1\" max=\"100\" placeholder=\"Quanlity\">\n"
                    + "                                <span onclick=\"upDowQuantity('tang', 0)\" class=\"btn-up\"></span>\n"
                    + "                            </div>\n"
                    + "\n"
                    + "                        </div>\n"
                    + "                        <div class=\"box-cart style2\">\n"
                    + "                            <div class=\"btn-add-cart\">\n"
                    + "                                <a id=\"load-data-button\" onclick=\"checkQuantityAddCart(" + quantityStock + ", " + product.getProductId() + ")\" href=\"javascript:void(0)\" title=\"\"><img src=\"images/icons/add-cart.png\" alt=\"Add to cart\">Add to Cart</a>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"compare-wishlist\">\n"
                    + "                                <a onclick=\"selectProductCompare('" + product.getGallery().get(0) + "', '" + product.getProductName() + "', '" + product.getProductId() + "')\" href=\"javascript:void(0)\" title=\"\"><img src=\"images/icons/compare.png\" alt=\"\">Compare</a>\n"
                    + "                                <a onclick=\"selectProductWishList(" + product.getProductId() + ")\" href=\"cjavascript:void(0)\" title=\"\"><img src=\"images/icons/wishlist.png\" alt=\"\">Wishlist</a>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "\n"
                    + "<button type=\"button\" class=\"btn btn-success ms-3\">\n"
                    + "            <a style=\"color: white\" href=\"productdetail?option=common&productId=" + product.getProductId() + "\">View detail</a>\n"
                    + "        </button>"
                    + "                    </div><!-- /.footer-detail -->\n"
                    + "                </div><!-- /.product-detail style5 -->\n"
                    + "                <div class=\"clearfix\"></div>\n"
                    + "            </div><!-- /.row -->\n";

            print.write(htmls);
        } catch (NumberFormatException e) {
            System.err.println("loi chuyen doi so product detail: " + e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        String productId_raw = request.getParameter("productId");

        HttpSession session = request.getSession();

        ProductDAO pd = new ProductDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        FeedbackProductDAO fpd = new FeedbackProductDAO();
        ColorDAO cd = new ColorDAO();
        MemoryDAO md = new MemoryDAO();

        try {
            int productId = Integer.parseInt(productId_raw);

            // lay ve san pham muon xem chi tiet
            Product product = pd.getProductById(productId);
            session.setAttribute("product", product);

            // lay ve tat ca cac color
            List<Color> getColorAll = cd.getColorAll();
            session.setAttribute("colorAll", getColorAll);

            // lay ve tat ca cac memory
            List<Memory> getMemoryAll = md.getMemoryAll();
            session.setAttribute("memoryAll", getMemoryAll);

            // xu ly lay ve so don co san, so don da ban cho tung loai san pham hoac 1 loai san pham rieng 
            int quantityStock, quantitySold;

            if (option.equalsIgnoreCase("common")) {
                quantityStock = pd.getQuantityStockByProductId(productId);
                quantitySold = pd.getQuantitySoldByProductId(productId);
            } else {
                String colorId_raw = request.getParameter("colorId");
                String memoryId_raw = request.getParameter("memoryId");

                int colorId = Integer.parseInt(colorId_raw);
                int memoryId = Integer.parseInt(memoryId_raw);
                quantityStock = pdd.getQuantityStockByProductDetailId(productId, colorId, memoryId);
                quantitySold = pdd.getQuantitySoldByProductDetailId(productId, colorId, memoryId);

                // lay ve productdetail 
                ProductDetail productDetail = pdd.getProductDetailByProductIdColorIdMemoryId(productId, colorId, memoryId);
                request.setAttribute("productDetail", productDetail);
            }
            session.setAttribute("quantityStock", quantityStock);
            session.setAttribute("quantitySold", quantitySold);

            // lay ve top 3 san pham moi nhat
            List<Product> getTop3LastProduct = pd.getTop3LastProductByCategoryId(product.getCategory().getCategoryId());
            session.setAttribute("top3LastProduct", getTop3LastProduct);

            // lay ve tat ca danh gia cho 1 san pham cu the
            List<FeedbackProduct> getFeedbackProductAll = fpd.getFeedbackProductAllByProductId(productId);
            session.setAttribute("feedbackAll", getFeedbackProductAll);

            //lay ve feedback product cho moi loai, 5 sao , 4 sao, 3 sao, 2 sao, 1 sao, all, co hinh anh;
            List<FeedbackProduct> getFeedbackProduct5Star = fpd.getStarFeedback(productId, 5);
            List<FeedbackProduct> getFeedbackProduct4Star = fpd.getStarFeedback(productId, 4);
            List<FeedbackProduct> getFeedbackProduct3Star = fpd.getStarFeedback(productId, 3);
            List<FeedbackProduct> getFeedbackProduct2Star = fpd.getStarFeedback(productId, 2);
            List<FeedbackProduct> getFeedbackProduct1Star = fpd.getStarFeedback(productId, 1);
            List<FeedbackProduct> getFeedbackImage = fpd.getFeedbackProductImageByProductId(productId);

            System.out.println("review 5 sao: " + getFeedbackProduct5Star.size());

            // lay ve tong luot sao moi loai
            session.setAttribute("feedback5star", getFeedbackProduct5Star);
            session.setAttribute("feedback4star", getFeedbackProduct4Star);
            session.setAttribute("feedback3star", getFeedbackProduct3Star);
            session.setAttribute("feedback2star", getFeedbackProduct2Star);
            session.setAttribute("feedback1star", getFeedbackProduct1Star);
            session.setAttribute("feedbackImage", getFeedbackImage);

            // xy ly truong hop lay ve tat ca cac product vua xem
            List<Product> listViewed;
            if (session.getAttribute("viewedProduct") == null) {
                listViewed = new ArrayList<>();
            } else {
                listViewed = (List<Product>) session.getAttribute("viewedProduct");
            }

            // danh dau xem san pham do da co trong danh dach da xem hay chua, neu ton tai thi se xoa di va add vao cuoi danh sach
            boolean isExist = false;
            int index = -1;
            for (int i = 0; i < listViewed.size(); i++) {
                if (listViewed.get(i).getProductId() == productId) {
                    isExist = true;
                    index = i;
                }
            }

            if (isExist == true) {
                listViewed.remove(index);
            }

            listViewed.add(product);
            session.setAttribute("viewedProduct", listViewed);

//            lay ve top 9 recent product
            List<Product> getTop9RecentProduct = pd.getTop9RecentProduct(product.getCategory().getCategoryId(), productId);
            session.setAttribute("top9RecentProduct", getTop9RecentProduct);

            request.getRequestDispatcher("views/user/item-page/productdetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.err.println("loi chuyen doi so product detail: " + e);
        }
    }

}

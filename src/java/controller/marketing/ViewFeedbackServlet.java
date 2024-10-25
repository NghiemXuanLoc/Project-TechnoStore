/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import dal.ColorDAO;
import dal.FeedbackProductDAO;
import dal.MemoryDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
 * @author BUI TUAN DAT
 */
@WebServlet(name = "ViewFeedbackServlet", urlPatterns = {"/viewfeedback"})
public class ViewFeedbackServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet ViewFeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewFeedbackServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("account");
        String option = request.getParameter("option");
        String productId_raw = request.getParameter("productId");

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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

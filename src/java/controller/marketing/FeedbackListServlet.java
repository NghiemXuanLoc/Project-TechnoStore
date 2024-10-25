
package controller.marketing;

import dal.FeedbackListDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import model.Product;

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "FeedbackListServlet", urlPatterns = {"/feedbacklist"})
public class FeedbackListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        HttpSession session = request.getSession();
        List<Product> getTop2LastProduct = pd.getTop2LastProductByCategoryAll();
        session.setAttribute("top2LastProduct", getTop2LastProduct.get(0));
        FeedbackListDAO feedbackListDAO = new FeedbackListDAO();
        String keyword = request.getParameter("searchKeyword");
        int page = 1;
        int pageSize = 5; // Default page size
        int rating = 0;
        int status = -1;

        // Parse page number
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // Handle the exception (e.g., set default page)
            page = 1;
        }

        // Parse page size
        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            // Handle the exception (e.g., set default page size)
            pageSize = 5;
        }

        // Parse rating
        try {
            rating = Integer.parseInt(request.getParameter("rating"));
        } catch (NumberFormatException e) {
            // Handle the exception (e.g., set default rating)
            rating = 0;
        }

        // Parse status
        try {
            status = Integer.parseInt(request.getParameter("deleted"));
        } catch (NumberFormatException e) {
            // Handle the exception (e.g., set default status)
            status = -1;
        }
        
        System.out.println("key: " + status);

        // Retrieve total pages and feedback list based on filters
        int totalPages = feedbackListDAO.getTotalPages(keyword, rating, status, pageSize);
        List<Map<String, Object>> list = feedbackListDAO.getListAllFeedBack(page, pageSize, keyword, rating, status);

        // Set attributes for JSP rendering
        request.setAttribute("listfeedback", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("currentRating", rating);
        request.setAttribute("currentStatus", status);
        request.setAttribute("keysearch", keyword);

        // Forward to JSP for rendering
        request.getRequestDispatcher("views/marketing/item-page/feedbacklist.jsp").forward(request, response);
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

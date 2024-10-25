/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CategoryDAO;
import dal.MyOrderDAO;
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
import model.Category;
import model.Product;
import model.User;

@WebServlet(name = "MyOrdersServlet", urlPatterns = {"/myorder"})
public class MyOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        
        List<Category> getCategoryAll = cd.getCategoryAll();

        // lay ve top 2 san pham moi nhat
        List<Product> getTop2LastProduct = pd.getTop2LastProductByCategoryAll();
        session.setAttribute("top2LastProduct", getTop2LastProduct);
        session.setAttribute("categoryAll", getCategoryAll);
        
        MyOrderDAO md = new MyOrderDAO();
        int page = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        String searchKeyword = request.getParameter("searchKeyword");
        int category = -1; // Default value for no category filter
        String categoryParam = request.getParameter("category");
        if (categoryParam != null && !categoryParam.isEmpty()) {
            try {
                category = Integer.parseInt(categoryParam);
            } catch (NumberFormatException e) {
                // Handle the exception if needed
                e.printStackTrace();
            }
        }
        
        User user = (User) session.getAttribute("account");

        List<Map<String, Object>> listMyOrder = md.getOrderByUserId(page, pageSize, searchKeyword, category, user.getUserId());
        List<Map<String, Object>> listCategory = md.getAllCategories(user.getUserId());

        int totalRecords = md.getTotalRecords(user.getUserId());
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        List<Integer> totalOrdered= md.totalOrderandMoney(user.getUserId());
        request.setAttribute("total", totalOrdered);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("keysearch", searchKeyword);
        request.setAttribute("category", category);
        request.setAttribute("orderlist", listMyOrder);
        request.setAttribute("categoryList", listCategory);

        request.getRequestDispatcher("/views/user/item-page/myorder.jsp").forward(request, response);
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

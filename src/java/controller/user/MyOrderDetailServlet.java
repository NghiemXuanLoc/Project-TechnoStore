/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import constant.Iconstant;
import dal.CategoryDAO;
import dal.MyOrderDAO;
import dal.MyOrderDetail;
import dal.OrdersDAO;
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

/**
 *
 * @author BUI TUAN DAT
 */
@WebServlet(name = "MyOrderDetailServlet", urlPatterns = {"/myorderdetail"})
public class MyOrderDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet MyOrderDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyOrderDetailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MyOrderDetail md = new MyOrderDetail();
        MyOrderDAO mod = new MyOrderDAO();
        OrdersDAO od = new OrdersDAO();
        
        HttpSession session = request.getSession();
        
        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        
        List<Category> getCategoryAll = cd.getCategoryAll();

        // lay ve top 2 san pham moi nhat
        List<Product> getTop2LastProduct = pd.getTop2LastProductByCategoryAll();
        session.setAttribute("top2LastProduct", getTop2LastProduct);
        session.setAttribute("categoryAll", getCategoryAll);
        
        User user = (User) session.getAttribute("account");
        String orderId = request.getParameter("orderId");
        
        int order;
        try {
            order = Integer.parseInt(orderId);
            List<Map<String, Object>> listMyOrder = md.getOrderDetailByOrderId(order);
            int status = mod.getStatusByOrderId(order);
            System.out.println(listMyOrder.get(0));
            request.setAttribute("orderCalcell", orderId);
            request.setAttribute("orderstatus", status);
            request.setAttribute("orderdetail", listMyOrder.get(0));
            request.setAttribute("orderdetailist", listMyOrder);
            request.setAttribute("orderId", orderId);
            request.setAttribute("amount", String.format("%.0f", od.getOrderById(Integer.parseInt(orderId)).getTotalMoney())) ;
            
            if(status == 0){
                request.setAttribute("DateCancel", Iconstant.formatDate(Iconstant.addHoursToDate(od.getOrderById(Integer.parseInt(orderId)).getOrderDate(), 24)));
            }
            

        } catch (NumberFormatException e) {
            System.out.println("khong tim thay orderdetail");
        }

        request.getRequestDispatcher("/views/user/item-page/myorderdetail.jsp").forward(request, response);

    }

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

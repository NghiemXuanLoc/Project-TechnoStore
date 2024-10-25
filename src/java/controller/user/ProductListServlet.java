/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CategoryDAO;
import dal.ColorDAO;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Category;
import model.Color;
import model.Memory;
import model.Product;

/**
 *
 * @author PC
 */
@WebServlet(name = "ProductListServlet", urlPatterns = {"/productlist"})
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        CategoryDAO cd = new CategoryDAO();
        ColorDAO colorD = new ColorDAO();
        MemoryDAO md = new MemoryDAO();
        ProductDAO pd = new ProductDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();

        List<Category> getListCategory = cd.getCategoryAll();
        List<Color> getListColor = colorD.getColorAll();
        List<Memory> getListMemory = md.getMemoryAll();

        session.setAttribute("listCategory", getListCategory);
        session.setAttribute("listColor", getListColor);
        session.setAttribute("listMemory", getListMemory);

        String searchBox = request.getParameter("searchBox");

        String[] categorys_raw = request.getParameterValues("category");

        String minValue = request.getParameter("minValue");
        String maxValue = request.getParameter("maxValue");

        String[] operatings_raw = request.getParameterValues("operating");

        String[] batterys = request.getParameterValues("battery");

        String[] screens_raw = request.getParameterValues("screen");

        String[] rams_raw = request.getParameterValues("ram");

        String[] colors_raw = request.getParameterValues("color");

        String[] memorys_raw = request.getParameterValues("memory");

        String showNumberProduct = request.getParameter("showNumberProduct");

        String sortByProduct = request.getParameter("sortByProduct");

        String numberPage_raw = request.getParameter("numberPage");

        if (searchBox == null) {
            searchBox = "";
        }

//        if (batterys == null) {
//            batterys = optionAll;
//        }
        if (showNumberProduct == null) {
            showNumberProduct = "9";
        }

        if (sortByProduct == null) {
            sortByProduct = "0";
        }

        if (numberPage_raw == null) {
            numberPage_raw = "1";
        }

        try {
            List<Integer> colors = new ArrayList<>();

            List<Integer> memorys = new ArrayList<>();

            List<Integer> rams = new ArrayList<>();

            List<String> screens = new ArrayList<>();

            List<String> operating = new ArrayList<>();

            List<Integer> categorys = new ArrayList<>();

            Set<Integer> selectedCategory = new LinkedHashSet<>();

            Set<String> selectedOperatingSystem = new LinkedHashSet<>();

            Set<String> selectedScreens = new LinkedHashSet<>();

            Set<Integer> selectedRams = new LinkedHashSet<>();

            Set<Integer> selectedColors = new LinkedHashSet<>();

            Set<Integer> selectedMemorys = new LinkedHashSet<>();

            if (categorys_raw != null) {
                for (String categoryId_raw : categorys_raw) {
                    int categoryId = Integer.parseInt(categoryId_raw);
                    categorys.add(categoryId);
                    selectedCategory.add(categoryId);
                }
            } else {
                selectedCategory.add(0);
            }

            for (Integer x : selectedCategory) {
                System.out.println("category: " + x);
            }

            System.out.println(selectedCategory.contains(1));

            if (minValue == null) {
                minValue = "-1";
                maxValue = "-1";
            }
            if (colors_raw != null) {
                for (String colorId_raw : colors_raw) {
                    int colorId = Integer.parseInt(colorId_raw);
                    colors.add(colorId);
                    selectedColors.add(colorId);
                }
            } else {
                selectedColors.add(0);
            }

            if (memorys_raw != null) {
                for (String memoryId_raw : memorys_raw) {
                    int memoryId = Integer.parseInt(memoryId_raw);
                    memorys.add(memoryId);
                    selectedMemorys.add(memoryId);
                }
            } else {
                selectedMemorys.add(0);
            }

            if (rams_raw != null) {
                for (String ramId_raw : rams_raw) {
                    int ramId = Integer.parseInt(ramId_raw);
                    rams.add(ramId);
                    selectedRams.add(ramId);
                }
            } else {
                selectedRams.add(0);
            }

            if (operatings_raw != null) {
                for (String operatingDetail : operatings_raw) {
                    operating.add(operatingDetail);
                    selectedOperatingSystem.add(operatingDetail);
                }
            } else {
                selectedOperatingSystem.add("all");
            }

            if (screens_raw != null) {
                for (String screensDetail : screens_raw) {
                    screens.add(screensDetail);
                    selectedScreens.add(screensDetail);
                }
            } else {
                selectedScreens.add("all");
            }

            int numberPage = Integer.parseInt(numberPage_raw);

            List<Product> listProductFilter = pd.getListProductByFilter(searchBox, categorys, Double.parseDouble(minValue), Double.parseDouble(maxValue), operating, screens, rams, colors, memorys, Integer.parseInt(sortByProduct), numberPage, Integer.parseInt(showNumberProduct));

            request.setAttribute("showNumberProduct", showNumberProduct);
            request.setAttribute("sortByProduct", sortByProduct);
            request.setAttribute("numberPage", numberPage);

            request.setAttribute("selectedCategory", selectedCategory);
            request.setAttribute("selectedOperatingSystem", selectedOperatingSystem);
            request.setAttribute("selectedScreens", selectedScreens);
            request.setAttribute("selectedRams", selectedRams);
            request.setAttribute("selectedColors", selectedColors);
            request.setAttribute("selectedMemorys", selectedMemorys);

            // lay ve tong so luong tat cac cac san pham co trong web site
            int totalProduct = pd.getTotalProductByFilter(searchBox, categorys, Double.parseDouble(minValue), Double.parseDouble(maxValue), operating, screens, rams, colors, memorys, Integer.parseInt(sortByProduct), numberPage, Integer.parseInt(showNumberProduct));;

            int totalPage = (int) Math.ceil((double) totalProduct / Integer.parseInt(showNumberProduct));

            int start = numberPage - 1;

            if (start < 1) {
                start = 1;
            }

            int end = start + 2;

            if (end > totalPage) {
                end = totalPage;
            }

            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("totalPage", totalPage);

            // lay ve top 2 san pham moi nhat
            List<Product> getTop2LastProduct = pd.getTop2LastProductByCategoryAll();
            session.setAttribute("top2LastProduct", getTop2LastProduct);

            session.setAttribute("totalProduct", totalProduct);
            session.setAttribute("listProductFilter", listProductFilter);
            request.getRequestDispatcher("views/user/item-page/productlist.jsp").forward(request, response);

        } catch (Exception e) {
        }

    }

}

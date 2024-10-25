/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.BlogCategoryDAO;
import dal.BlogDetailDAO;
import dal.CartAccountDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import dal.SliderDAO;
import dal.UserForAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.BlogCategory;
import model.BlogDetail;
import model.Cart;
import model.CartAccount;
import model.Category;
import model.Product;
import model.ProductDetail;
import model.Slider;
import model.User;

/**
 *
 * @author PC
 */
public class HomeServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ProductDAO pd = new ProductDAO();
        ProductDetailDAO pdd = new ProductDetailDAO();
        BlogDetailDAO bdd = new BlogDetailDAO();
        BlogCategoryDAO bcd = new BlogCategoryDAO();
        CategoryDAO cd = new CategoryDAO();
        SliderDAO sd = new SliderDAO();
        UserForAdmin userForAdminDAO = new UserForAdmin();//code user profile cua ha
        Map<Category, List<Product>> getTop3ProductBestSellingByCategoryAll = pd.getTop3ProductBestSellingByCategoryAll();
        
        List<Product> getTop3ProductBestSelling = pd.getTop3ProductBestSelling();
        List<Product> getTop3ProductRating = pd.getTop3ProductRating();
        List<Product> getTop3ProductSale = pd.getTop3ProductHotSale();
        
        Map<Category, List<Product>> getTop9NewProductByCategoryAll = pd.getTop9NewProductByCategoryAll();
        
        List<BlogDetail> getTop4LastBlogDetail = bdd.getTop4LastBlogDetail();
        List<BlogCategory> getBlogCategoryAll = bcd.getBlogCategoryAll();
        List<Category> getCategoryAll = cd.getCategoryAll();

        // lay ve tat ca cac slider de hien thi len trang home
        List<Slider> getSliderAll = sd.getSliderAll();
        session.setAttribute("sliderAll", getSliderAll);

        // lay ve so luong san pham trong wishlist
        Cookie[] cookies = request.getCookies();
        
        Cookie cookie = new Cookie("WishList", "");
        
        boolean danhDauTonTaiCookie = false;
        for (Cookie c : cookies) {
            if (c.getName().equals("WishList")) {
                cookie = c;
                danhDauTonTaiCookie = true;
                break;
            }
        }
        
        User account = (User) session.getAttribute("account");

        // xu ly wishlist for account
        if (account != null) {
            System.out.println(account.getCart());
            int quantityProductWishList = 0;
            if (danhDauTonTaiCookie) {
                
                String[] listProductWishListByAccounts = cookie.getValue().split("\\&");

                // bien nay dung de luu tru list product Id theo account
                String productsIdByAccount = "";

                // loc qua mang cac account id tuong ung kem theo danh sach product id
                for (String item : listProductWishListByAccounts) {
                    String[] data = item.split("\\-");
                    String accountId = data[0];
                    String listProductId = data[1];
                    
                    if (account.getUserId() == Integer.parseInt(accountId)) {
                        productsIdByAccount = listProductId;
                        break;
                    }
                }
                
                String[] productId_raw = productsIdByAccount.split("\\*");
                
                quantityProductWishList = productId_raw.length;
            }
            
            session.setAttribute("quantityProductWishList", quantityProductWishList);
        }
        
        Cookie cookieGuest = new Cookie("Guest", "");

        // danh dau su ton tai cua cookie guest
        boolean isCookieGuest = false;
        
        for (Cookie c : cookies) {
            if (c.getName().equalsIgnoreCase("Guest")) {
                cookieGuest = c;
                isCookieGuest = true;
                break;
            }
        }

        // xu ly cart for guest and account
        if (account == null && isCookieGuest) {
            List<ProductDetail> listProductDetail = new ArrayList<>();
            List<Integer> listSoLuong = new ArrayList<>();
            
            String[] productInCarts = cookieGuest.getValue().split("&");

            // chuyen doi cookie luu tru gio hang thanh san pham cu the, luu chu vao model cart
            for (String productInCart : productInCarts) {
                String[] productDetailInCart = productInCart.split("-");
                if (productDetailInCart.length > 1) {
                    int productDetailIdInCart = Integer.parseInt(productDetailInCart[0]);
                    int soLuongInCart = Integer.parseInt(productDetailInCart[1]);
                    
                    listProductDetail.add(pdd.getProductDetailById(productDetailIdInCart));
                    listSoLuong.add(soLuongInCart);
                }
            }
            
            Cart cart = new Cart(listProductDetail, listSoLuong);
            cart.setTotalPriceBeforeDiscount();
            cart.setTotalPriceAfterDiscount();
            // cart cua guest
            session.setAttribute("cart", cart);
        } else if (account != null) {

            // log vao he thong thanh cong, ta se hop nhat cart guest vao account, va xoa cookie guest
            CartAccountDAO cad = new CartAccountDAO();

            // nhung san pham co san, di theo account trong cart
            Map<Integer, Integer> cartAccount = account.getCart();

            // lay ve nhung thang ton tai trong guest, nhung chua ton tai trong cartAccount
            if (isCookieGuest) {
                String[] productInCarts = cookieGuest.getValue().split("&");

                // chuyen doi cookie luu tru gio hang thanh san pham cu the, dong bo hoa cookie voi cartAccount
                for (String productInCart : productInCarts) {
                    String[] productDetailInCart = productInCart.split("-");
                    int productDetailIdInCart = Integer.parseInt(productDetailInCart[0]);
                    int soLuongInCart = Integer.parseInt(productDetailInCart[1]);
//
//                    listProductDetail.add(pdd.getProductDetailById(productDetailIdInCart));
//                    listSoLuong.add(soLuongInCart);
                    // chua ton tai trong cartAccount
                    if (!cartAccount.containsKey(productDetailIdInCart)) {
                        cartAccount.put(productDetailIdInCart, soLuongInCart);
                        System.out.println("account homepage: " + account);
                        cad.insertCartAccountByUserId(account.getUserId(), productDetailIdInCart, soLuongInCart);
                    } else {
                        // da ton tai trong cartAccount(cap nhat lai so luong, so luong cu + so luong moi)
                        cartAccount.replace(productDetailIdInCart, soLuongInCart + cartAccount.get(productDetailIdInCart));
                        cad.updateCartAccountByUserId(account.getUserId(), productDetailIdInCart, cartAccount.get(productDetailIdInCart));
                    }
                }

                // xoa bo cookie guest
                cookieGuest.setMaxAge(0);
                response.addCookie(cookieGuest);
            }

            // sau khi dong bo hoa thanh cong, ta se chuyen doi thanh session de luu tru va hien thi
            List<ProductDetail> listProductDetail = new ArrayList<>();
            List<Integer> listSoLuong = new ArrayList<>();

            // cap nhat lai cartAccount from database(sort theo date tang dan, muc dich de hien thi san pham moi nhat len dau)
            cartAccount = cad.getCarAccountByUserId(account.getUserId());
            
            for (Map.Entry<Integer, Integer> x : cartAccount.entrySet()) {
                listProductDetail.add(pdd.getProductDetailById(x.getKey()));
                listSoLuong.add(x.getValue());
            }
            
            Cart cart = new Cart(listProductDetail, listSoLuong);
            cart.setTotalPriceBeforeDiscount();
            cart.setTotalPriceAfterDiscount();
            
            account.setCart(cartAccount);
            
            session.setAttribute("account", account);
            session.setAttribute("cart", cart);

            // xoa cookie guest
        }
        
        List<Float> minMaxPriceByProductAll = pd.getMinAndMaxPriceByProductAll();
        
        session.setAttribute("initMinValue", minMaxPriceByProductAll.get(0));
        session.setAttribute("initMaxValue", minMaxPriceByProductAll.get(1));
        
        session.setAttribute("top3ProductBestSellingByCategory", getTop3ProductBestSellingByCategoryAll);
        session.setAttribute("top3ProductBestSelling", getTop3ProductBestSelling);
        session.setAttribute("top3ProductRating", getTop3ProductRating);
        session.setAttribute("top3ProductSale", getTop3ProductSale);
        session.setAttribute("top9NewProductByCategory", getTop9NewProductByCategoryAll);
        session.setAttribute("top4LastBlogDetail", getTop4LastBlogDetail);
        session.setAttribute("BlogCategoryAll", getBlogCategoryAll);
        session.setAttribute("categoryAll", getCategoryAll);
        if(account!= null){
            session.setAttribute("userProfileForAdmin", userForAdminDAO.getUserById(account.getUserId())); //code user profile cua ha
        }
        request.getRequestDispatcher("views/user/home-page/homepage.jsp").forward(request, response);
    }
}

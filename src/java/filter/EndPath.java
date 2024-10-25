/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package filter;

/**
 *
 * @author admin
 */
public interface EndPath {
    //home
    public static final String HOME_PAGE = "/home";
    //admin
    public static final String ADMIN_DASHBOARD = "/adminDashboard";
    public static final String ADMIN_USERLIST = "/userList";
    public static final String ADMIN_ADDUSER = "/addUser";
    public static final String ADMIN_UPDATEUSER = "/updateUser";
    
    
    //sale
    public static final String SALE_DASHBOARD = "/saledashboard";
    public static final String SALE_ORDERLIST = "/saleorderlist";
    public static final String SALE_UPDATEORDERSTATUS = "/updateorderstatus";
    
    
    //marketing
    public static final String MARKETING_POSTLIST = "/postlist";
    public static final String MARKETING_POSTDETAIL = "/postdetail";
    public static final String MARKETING_ADDPOST = "/addpost";
    public static final String MARKETING_EDITPOST = "/editpost";
    
    //customer
    public static final String CUSTOMER_CARDETAIL = "/cartdetail";
    public static final String CUSTOMER_BLOGLIST = "/bloglist";
    public static final String CUSTOMER_DETAIL = "/blogdetail";
}

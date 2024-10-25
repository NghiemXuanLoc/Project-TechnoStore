<%-- 
    Document   : narvbar
    Created on : May 24, 2024, 10:18:45 AM
    Author     : BUI TUAN DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="ec-left-sidebar ec-bg-sidebar">
            <div id="sidebar" class="sidebar ec-sidebar-footer">

                <div class="ec-brand">
                    <a href="javascript:void(0)" title="">
                        <img class="ec-brand-icon" src="assets/img/logo/ec-site-logo.png" alt="" />
                        <span class="ec-brand-name text-truncate">Marketing</span>
                    </a>
                </div>

                <!-- begin sidebar scrollbar -->
                <div class="ec-navigation" data-simplebar>
                    <!-- sidebar menu -->
                    <ul class="nav sidebar-inner" id="sidebar-menu">
                        <li class="">
                            <a class="sidenav-item-link" href="/TechStore/mktdashboard">
                                <i class="mdi mdi-view-dashboard-outline"></i>
                                <span class="nav-text">Dashboard</span>
                            </a>
                            <hr>
                        </li>
                        <li class="has-sub">
                            <a class="sidenav-item-link" href="javascript:void(0)">
                                <i class="mdi mdi-post"></i>
                                <span class="nav-text">Posts List</span> <b class="caret"></b>
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                    <li>
                                        <a class="active sidenav-item-link" href="/TechStore/postlist">
                                            <span class="nav-text">Posts List</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="sidenav-item-link" href="/TechStore/postdetail">
                                            <span class="nav-text">Post Detail</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="sidenav-item-link" href="/TechStore/addpost">
                                            <span class="nav-text">Add Post</span>
                                        </a>
                                    </li>

<!--                                    <li>
                                        <a class="sidenav-item-link" href="/TechStore/listcommentpost">
                                            <span class="nav-text">List comment post</span>
                                        </a>
                                    </li>-->
                                </ul>
                            </div>
                            <hr>
                        </li>
                        <li class="has-sub">
                            <a class="sidenav-item-link" href="javascript:void(0)">
                                <i class="mdi mdi-package-variant-closed"></i>
                                <span class="nav-text">Products List</span> <b class="caret"></b>
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                    <li>
                                        <a class="active sidenav-item-link" href="/TechStore/productslist">
                                            <span class="nav-text">Products List</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="sidenav-item-link" href="/TechStore/productsdetail">
                                            <span class="nav-text">Product Detail</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="sidenav-item-link" href="/TechStore/addproduct">
                                            <span class="nav-text">Add Product</span>
                                        </a>
                                    </li>
<!--                                    <li>
                                        <a class="sidenav-item-link" href="javascript:void(0)">
                                            <span class="nav-text">List contribution</span>
                                        </a>
                                    </li>-->
                                </ul>
                            </div>
                            <hr>
                        </li>
                        <li class="has-sub">
                            <a class="sidenav-item-link" href="javascript:void(0)">
                                <i class="mdi mdi-post-outline"></i>
                                <span class="nav-text">Slider List</span> <b class="caret"></b>
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                    <li>
                                        <a class="active sidenav-item-link" href="/TechStore/mktslider">
                                            <span class="nav-text">Slider List</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="sidenav-item-link" href="/TechStore/mktsliderdetail">
                                            <span class="nav-text">Slider Detail</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <hr>
                        </li>
                        <li class="has-sub">
                            <a class="sidenav-item-link" href="javascript:void(0)">
                                <i class="mdi mdi-account-group"></i>
                                <span class="nav-text">Users</span> <b class="caret"></b>
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                    <li>
                                        <a class="sidenav-item-link" href="/TechStore/mktuserlist">
                                            <span class="nav-text">User List</span>
                                        </a>
                                    </li>
                                    <li class="">
                                        <a class="sidenav-item-link" href="/TechStore/mktuserdetail">
                                            <span class="nav-text">User Detail</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <hr>
                        </li>                   
                        <li class="has-sub">
                            <a class="sidenav-item-link" href="javascript:void(0)">
                                <i class="mdi mdi-account-group"></i>
                                <span class="nav-text">Feedback</span> <b class="caret"></b>
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                    <li>
                                        <a class="sidenav-item-link" href="/TechStore/feedbacklist">
                                            <span class="nav-text">Feedback List</span>
                                        </a>
                                    </li>
<!--                                    <li class="">
                                        <a class="sidenav-item-link" href="">
                                            <span class="nav-text">Feedback Detail</span>
                                        </a>
                                    </li>-->
                                </ul>
                            </div>
                            <hr>
                        </li>
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                       
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>

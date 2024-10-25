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
                    <a  title="">
                        <img class="ec-brand-icon" src="assets/img/logo/ec-site-logo.png" alt="" />
                        <span class="ec-brand-name text-truncate">Admin</span>
                    </a>
                </div>

                <!-- begin sidebar scrollbar -->
                <div class="ec-navigation" data-simplebar>
                    <!-- sidebar menu -->
                    <ul class="nav sidebar-inner" id="sidebar-menu">
                        <!--                         Dashboard 
                        -->                        <li class="">
                            <a class="sidenav-item-link" href="adminDashboard">
                                <i class="mdi mdi-view-dashboard-outline"></i>
                                <span class="nav-text">Dashboard</span>
                            </a>
                            <hr>
                        </li>
                        <!--thẻ hiển thị navbar left Postlist -->
                        <li class="has-sub active">


                            <a class="sidenav-item-link" href="javascript:void(0)">
                                <i class="mdi mdi-account-group"></i>
                                <span class="nav-text">Manager</span> 
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                    <li>
                                        <a class="active sidenav-item-link" href="/TechStore/userList">
                                            <span class="nav-text">User List</span>
                                        </a>
                                    </li>
                                    <!--
                                                                        <li class="">
                                                                            <a class="sidenav-item-link" href="user-list.html">
                                                                                <span class="nav-text">Post Detail</span>
                                                                            </a>
                                                                        </li>-->
                                </ul>
                            </div>
                            <hr>
                        </li>
                        <!--                        <li class="">
                                                    <a class="sidenav-item-link" href="settingFilter">
                                                        <i class="mdi mdi-account-key-outline"></i>
                                                        <span class="nav-text">Setting Filter</span>
                                                    </a>
                                                    <hr>
                                                </li>-->
                        <li class="has-sub active">                           
                            <a class="sidenav-item-link" style="cursor: pointer">
                                <i class="mdi mdi-image-filter-vintage"></i>
                                <span class="nav-text">Setting</span> 
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                    <li>
                                        <a class="active sidenav-item-link" href="settingFilter">
                                            <span class="nav-text">Setting Filter</span>
                                        </a>
                                        
                                        <a class="active sidenav-item-link" href="settingList">
                                            <span class="nav-text">Setting List</span>
                                        </a>
                                    </li>
                                    <!--
                                    --> 
                                </ul>
                            </div>
                            
                            
                            <hr>
                        </li>




                        <!--                         Users 
                                                <li class="has-sub">
                                                    <a class="sidenav-item-link" href="javascript:void(0)">
                                                        <i class="mdi mdi-account-group"></i>
                                                        <span class="nav-text">Users</span> <b class="caret"></b>
                                                    </a>
                                                    <div class="collapse">
                                                        <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                                            <li>
                                                                <a class="sidenav-item-link" href="usecardmarketing.">
                                                                    <span class="nav-text">User Grid</span>
                                                                </a>
                                                            </li>
                        
                                                            <li class="">
                                                                <a class="sidenav-item-link" href="user-list.html">
                                                                    <span class="nav-text">User List</span>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <hr>
                                                </li>-->
                    </ul>

                </div>
            </div>
        </div>
    </body>
</html>

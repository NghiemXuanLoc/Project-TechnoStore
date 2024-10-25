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
        <title>Narvbar Page</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light sidebar-minified" id="body">
        <div class="ec-left-sidebar ec-bg-sidebar">
            <div id="sidebar" class="sidebar ec-sidebar-footer">

                <div class="ec-brand">
                    <a href="javascript:void(0)" title="" >
                        <img class="ec-brand-icon" src="assets/img/logo/ec-site-logo.png" alt="" />
                        <span class="ec-brand-name text-truncate">Sale</span>
                    </a>
                </div>

                <!-- begin sidebar scrollbar -->
                <div class="ec-navigation" data-simplebar>
                    <!-- sidebar menu -->
                    <ul class="nav sidebar-inner" id="sidebar-menu">
                        <li class="has-sub active">
                            <a class="sidenav-item-link" href="javascript:void(0)">
                                <i class="mdi mdi-view-dashboard-outline"></i>
                                <span class="nav-text">Sale Dashboard</span> <b class="caret"></b>
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                                    <li>
                                        <a class="active sidenav-item-link" href="/TechStore/saledashboard">
                                            <span class="nav-text">Dashboard</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <hr>
                        </li>
                        <!-- Orders -->
                        <li class="has-sub">
                            <a class="sidenav-item-link" href="javascript:void(0)">
                                <i class="mdi mdi-cart"></i>
                                <span class="nav-text">Orders</span> <b class="caret"></b>
                            </a>
                            <div class="collapse">
                                <ul class="sub-menu" id="orders" data-parent="#sidebar-menu">

                                    <li class="">
                                        <a class="sidenav-item-link" href="/TechStore/saleorderlist">
                                            <span class="nav-text">Order List</span>
                                        </a>
                                    </li>
                                    <li class="">
                                        <a class="sidenav-item-link" href="/TechStore/saleorderdetail">
                                            <span class="nav-text">Order Detail</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
    </body>
</html>

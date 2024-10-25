<%-- 
    Document   : adduser
    Created on : Jun 6, 2024, 6:11:28 PM
    Author     : admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">

        <!--<link href='assets/plugins/data-tables/responsive.datatables.min.css' rel='stylesheet'>-->

        <!-- ekka CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/ekka.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/simplebar.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/userlist.css" />
        <!-- FAVICON -->
        <link href="assets/img/favicon.png" rel="shortcut icon" />
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-dark ec-header-light" id="body">
        <!--header-->
        <jsp:include page="../../common/admin/narvbar.jsp"></jsp:include>
        <jsp:include page="../../common/sale/header.jsp"></jsp:include>
            <div class="wrapper">
                <div class="ec-page-wrapper">
                <c:if test="${not empty sessionScope.failPath}">
                    <div class="alert alert-warning">
                        <strong>Warning!</strong> ${failPath}.
                    </div>
                    <% session.removeAttribute("failPath"); %>
                </c:if>
                <div class="content">
                    <!-- Top Statistics -->
                    <div class="row">
                        <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
                            <div class="card card-mini dash-card card-1">
                                <div class="card-body">
                                    <h2 class="mb-1">${userRegisterToDay}</h2>
                                    <p>User Register Today</p>
                                    <span class="mdi mdi-account-arrow-left"></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
<!--                            <div class="card card-mini dash-card card-2">
                                <div class="card-body">
                                    <h2 class="mb-1">79,503</h2>
                                    <p>Daily Visitors</p>
                                    <span class="mdi mdi-account-clock"></span>
                                </div>
                            </div>-->
                        </div>
                        <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
<!--                            <div class="card card-mini dash-card card-3">
                                <div class="card-body">
                                    <h2 class="mb-1">15,503</h2>
                                    <p>Daily Order</p>
                                    <span class="mdi mdi-package-variant"></span>
                                </div>
                            </div>-->
                        </div>
                        <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
                            <div class="card card-mini dash-card card-4">
                                <div class="card-body">
                                    <h2 class="mb-1"><fmt:formatNumber value="${totalOrderByDay}" type="number" pattern="#,###.00" /></h2>
                                    
                                    <p>Total Revenue</p>
                                    <span class="mdi mdi-currency-usd"></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="card-body">
                            <table>
                                <tr>
                                <form action="adminDashboard" method="post">
                                    <td>From: </td>
                                    <td ><input name="startDate" value="${startDate}" type="date"></td>
                                    <td>To: </td>
                                    <td><input name="endDate" value="${endDate}" type="date"></td>
                                    <td ><button style="margin-left: 5px" class="btn btn-submit btn-danger">Submit</button></td>
                                    <c:if test="${sessionScope.errDate ne null}">
                                        <td ><h2 style="color: red">${errDate}</h2></td>
                                        <% session.removeAttribute("errDate"); %>
                                    </c:if>
                                </form>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xl-8 col-md-12 p-b-15">
                            <!-- Sales Graph -->
                            <div id="user-acquisition" class="card card-default">
                                <div class="card-header">
                                    <h2>Total Report</h2>
                                </div>
                                <div class="card-body">
                                    <ul class="nav nav-tabs nav-style-border justify-content-between justify-content-lg-start border-bottom"
                                        role="tablist">

                                    </ul>
                                    <div class="tab-content pt-4" id="salesReport">
                                        <div class="tab-pane fade show active" id="source-medium" role="tabpanel">
                                            <div class="mb-6" style="max-height:247px">
                                                <canvas id="acquisition" class=""></canvas>
                                                <div id="acqLegend" class="customLegend mb-2"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-4 col-md-12 p-b-15">
                            <!-- Doughnut Chart -->
                            <div class="card card-default">
                                <div class="card-header justify-content-center">
                                    <h2>Total orders: ${allOrder}</h2>
                                </div>
                                <div class="card-body">
                                    <canvas id="doChart"></canvas>
                                </div>
                                <div class="card-footer d-flex flex-wrap bg-white p-0">
                                    <div class="col-6">
                                        <div class="p-20">
                                            <ul class="d-flex flex-column justify-content-between">
                                                <li class="mb-2"><i class="mdi mdi-checkbox-blank-circle-outline mr-2"
                                                                    style="color: #50d7ab"></i>Order Success</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-6 border-left">
                                        <div class="p-20">
                                            <ul class="d-flex flex-column justify-content-between">
                                                <li class="mb-2"><i class="mdi mdi-checkbox-blank-circle-outline mr-2"
                                                                    style="color: #f3d676"></i>Order Pending</li>
                                                <li class="mb-2"><i class="mdi mdi-checkbox-blank-circle-outline mr-2"
                                                                    style="color: #ed9090"></i>Order Cancel</li>

                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xl-6 col-md-12 p-b-15">
                            <!-- User activity statistics -->
                            <div class="card card-default" id="user-activity">
                                <div class="no-gutters">
                                    <div>
                                        <div class="card-header justify-content-between">
                                            <h2>User Statistics</h2>
                                            <div class="date-range-report ">
                                                <span></span>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="tab-content" id="userActivityContent"> 
                                                <div class="tab-pane fade show active" id="user" role="tabpanel">
                                                    <canvas id="activity" class="chartjs"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <!--                                        <div class="card-footer d-flex flex-wrap bg-white border-top">
                                                                                    <a href="#" class="text-uppercase py-3">In-Detail Overview</a>
                                                                                </div>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6 col-md-12 p-b-15">
                            <div class="card card-default">
                                <div class="card-header flex-column align-items-start">
                                    <h2>Feedback Statistics</h2>
                                </div>
                                <div class="card-body">
                                    <canvas id="currentUser" class="chartjs"></canvas>
                                </div>
                                <!--                                <div class="card-footer d-flex flex-wrap bg-white border-top">
                                                                    <a href="#" class="text-uppercase py-3">In-Detail Overview</a>
                                                                </div>-->
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xl-12 col-md-12 p-b-15">
                            <!-- User activity statistics -->
                            <div class="card card-default" id="">
                                <div class="no-gutters">
                                    <div>
                                        <div class="card-header justify-content-between">
                                            <h2>Statistics On Order Trends</h2>
                                            <div class="date-range-report ">
                                                <span></span>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="tab-content" id="userActivityContent"> 
                                                <div class="tab-pane fade show active" id="userr" role="tabpanel">
                                                    <canvas id="trendoforder" class="chartjs" style="height: 50vh"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <!--                                        <div class="card-footer d-flex flex-wrap bg-white border-top">
                                                                                    <a href="#" class="text-uppercase py-3">In-Detail Overview</a>
                                                                                </div>-->
                                    </div>
                                </div>
                            </div>
                        </div>
<!--                        <div class="col-xl-6 col-12 p-b-15">
                             Top Sell Table 
                            <div class="card card-default Sold-card-table">
                                <div class="card-header justify-content-between">
                                    <h2>Sold by Items</h2>
                                    <div class="tools">
                                        <button class="text-black-50 mr-2 font-size-20"><i
                                                class="mdi mdi-cached"></i></button>
                                        <div class="dropdown show d-inline-block widget-dropdown">
                                            <a class="dropdown-toggle icon-burger-mini" href="#" role="button"
                                               id="dropdown-units" data-bs-toggle="dropdown" aria-haspopup="true"
                                               aria-expanded="false" data-display="static"></a>
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li class="dropdown-item"><a href="#">Action</a></li>
                                                <li class="dropdown-item"><a href="#">Another action</a></li>
                                                <li class="dropdown-item"><a href="#">Something else here</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body py-0 compact-units" data-simplebar style="height: 534px;">
                                    <table class="table ">
                                        <tbody>
                                            <tr>
                                                <td class="text-dark">Backpack</td>
                                                <td class="text-center">9</td>
                                                <td class="text-right">33% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">T-Shirt</td>
                                                <td class="text-center">6</td>
                                                <td class="text-right">150% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">Coat</td>
                                                <td class="text-center">3</td>
                                                <td class="text-right">50% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">Necklace</td>
                                                <td class="text-center">7</td>
                                                <td class="text-right">150% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">Jeans Pant</td>
                                                <td class="text-center">10</td>
                                                <td class="text-right">300% <i
                                                        class="mdi mdi-arrow-down-bold text-danger pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">Shoes</td>
                                                <td class="text-center">5</td>
                                                <td class="text-right">100% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">T-Shirt</td>
                                                <td class="text-center">6</td>
                                                <td class="text-right">150% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">Watches</td>
                                                <td class="text-center">18</td>
                                                <td class="text-right">160% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">Inner</td>
                                                <td class="text-center">156</td>
                                                <td class="text-right">120% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-dark">T-Shirt</td>
                                                <td class="text-center">6</td>
                                                <td class="text-right">150% <i
                                                        class="mdi mdi-arrow-up-bold text-success pl-1 font-size-12"></i>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                                <div class="card-footer d-flex flex-wrap bg-white">
                                    <a href="#" class="text-uppercase py-3">View Report</a>
                                </div>
                            </div>
                        </div>-->
                    </div>

<!--                    <div class="row">
                        <div class="col-12 p-b-15">
                             Recent Order Table 
                            <div class="card card-table-border-none card-default recent-orders" id="recent-orders">
                                <div class="card-header justify-content-between">
                                    <h2>Recent Orders</h2>
                                    <div class="date-range-report">
                                        <span></span>
                                    </div>
                                </div>
                                <div class="card-body pt-0 pb-5">
                                    <table class="table card-table table-responsive table-responsive-large"
                                           style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>Order ID</th>
                                                <th>Product Name</th>
                                                <th class="d-none d-lg-table-cell">Units</th>
                                                <th class="d-none d-lg-table-cell">Order Date</th>
                                                <th class="d-none d-lg-table-cell">Order Cost</th>
                                                <th>Status</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>24541</td>
                                                <td>
                                                    <a class="text-dark" href="#"> Coach Swagger</a>
                                                </td>
                                                <td class="d-none d-lg-table-cell">1 Unit</td>
                                                <td class="d-none d-lg-table-cell">Oct 20, 2018</td>
                                                <td class="d-none d-lg-table-cell">$230</td>
                                                <td>
                                                    <span class="badge badge-success">Completed</span>
                                                </td>
                                                <td class="text-right">
                                                    <div class="dropdown show d-inline-block widget-dropdown">
                                                        <a class="dropdown-toggle icon-burger-mini" href="#"
                                                           role="button" id="dropdown-recent-order1"
                                                           data-bs-toggle="dropdown" aria-haspopup="true"
                                                           aria-expanded="false" data-display="static"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <li class="dropdown-item">
                                                                <a href="#">View</a>
                                                            </li>
                                                            <li class="dropdown-item">
                                                                <a href="#">Remove</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>24541</td>
                                                <td>
                                                    <a class="text-dark" href="#"> Toddler Shoes, Gucci Watch</a>
                                                </td>
                                                <td class="d-none d-lg-table-cell">2 Units</td>
                                                <td class="d-none d-lg-table-cell">Nov 15, 2018</td>
                                                <td class="d-none d-lg-table-cell">$550</td>
                                                <td>
                                                    <span class="badge badge-primary">Delayed</span>
                                                </td>
                                                <td class="text-right">
                                                    <div class="dropdown show d-inline-block widget-dropdown">
                                                        <a class="dropdown-toggle icon-burger-mini" href="#"
                                                           role="button" id="dropdown-recent-order2"
                                                           data-bs-toggle="dropdown" aria-haspopup="true"
                                                           aria-expanded="false" data-display="static"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <li class="dropdown-item">
                                                                <a href="#">View</a>
                                                            </li>
                                                            <li class="dropdown-item">
                                                                <a href="#">Remove</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>24541</td>
                                                <td>
                                                    <a class="text-dark" href="#"> Hat Black Suits</a>
                                                </td>
                                                <td class="d-none d-lg-table-cell">1 Unit</td>
                                                <td class="d-none d-lg-table-cell">Nov 18, 2018</td>
                                                <td class="d-none d-lg-table-cell">$325</td>
                                                <td>
                                                    <span class="badge badge-warning">On Hold</span>
                                                </td>
                                                <td class="text-right">
                                                    <div class="dropdown show d-inline-block widget-dropdown">
                                                        <a class="dropdown-toggle icon-burger-mini" href="#"
                                                           role="button" id="dropdown-recent-order3"
                                                           data-bs-toggle="dropdown" aria-haspopup="true"
                                                           aria-expanded="false" data-display="static"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <li class="dropdown-item">
                                                                <a href="#">View</a>
                                                            </li>
                                                            <li class="dropdown-item">
                                                                <a href="#">Remove</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>24541</td>
                                                <td>
                                                    <a class="text-dark" href="#"> Backpack Gents, Swimming Cap Slin</a>
                                                </td>
                                                <td class="d-none d-lg-table-cell">5 Units</td>
                                                <td class="d-none d-lg-table-cell">Dec 13, 2018</td>
                                                <td class="d-none d-lg-table-cell">$200</td>
                                                <td>
                                                    <span class="badge badge-success">Completed</span>
                                                </td>
                                                <td class="text-right">
                                                    <div class="dropdown show d-inline-block widget-dropdown">
                                                        <a class="dropdown-toggle icon-burger-mini" href="#"
                                                           role="button" id="dropdown-recent-order4"
                                                           data-bs-toggle="dropdown" aria-haspopup="true"
                                                           aria-expanded="false" data-display="static"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <li class="dropdown-item">
                                                                <a href="#">View</a>
                                                            </li>
                                                            <li class="dropdown-item">
                                                                <a href="#">Remove</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>24541</td>
                                                <td>
                                                    <a class="text-dark" href="#"> Speed 500 Ignite</a>
                                                </td>
                                                <td class="d-none d-lg-table-cell">1 Unit</td>
                                                <td class="d-none d-lg-table-cell">Dec 23, 2018</td>
                                                <td class="d-none d-lg-table-cell">$150</td>
                                                <td>
                                                    <span class="badge badge-danger">Cancelled</span>
                                                </td>
                                                <td class="text-right">
                                                    <div class="dropdown show d-inline-block widget-dropdown">
                                                        <a class="dropdown-toggle icon-burger-mini" href="#"
                                                           role="button" id="dropdown-recent-order5"
                                                           data-bs-toggle="dropdown" aria-haspopup="true"
                                                           aria-expanded="false" data-display="static"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <li class="dropdown-item">
                                                                <a href="#">View</a>
                                                            </li>
                                                            <li class="dropdown-item">
                                                                <a href="#">Remove</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>-->

<!--                    <div class="row">
                        <div class="col-xl-5">
                             New Customers 
                            <div class="card ec-cust-card card-table-border-none card-default">
                                <div class="card-header justify-content-between ">
                                    <h2>New Customers</h2>
                                    <div>
                                        <button class="text-black-50 mr-2 font-size-20">
                                            <i class="mdi mdi-cached"></i>
                                        </button>
                                        <div class="dropdown show d-inline-block widget-dropdown">
                                            <a class="dropdown-toggle icon-burger-mini" href="#" role="button"
                                               id="dropdown-customar" data-bs-toggle="dropdown" aria-haspopup="true"
                                               aria-expanded="false" data-display="static">
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li class="dropdown-item"><a href="#">Action</a></li>
                                                <li class="dropdown-item"><a href="#">Another action</a></li>
                                                <li class="dropdown-item"><a href="#">Something else here</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body pt-0 pb-15px">
                                    <table class="table ">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="media-image mr-3 rounded-circle">
                                                            <a href="profile.html"><img
                                                                    class="profile-img rounded-circle w-45"
                                                                    src="assets/img/user/u1.jpg"
                                                                    alt="customer image"></a>
                                                        </div>
                                                        <div class="media-body align-self-center">
                                                            <a href="profile.html">
                                                                <h6 class="mt-0 text-dark font-weight-medium">Selena
                                                                    Wagner</h6>
                                                            </a>
                                                            <small>@selena.oi</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>2 Orders</td>
                                                <td class="text-dark d-none d-md-block">$150</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="media-image mr-3 rounded-circle">
                                                            <a href="profile.html"><img
                                                                    class="profile-img rounded-circle w-45"
                                                                    src="assets/img/user/u2.jpg"
                                                                    alt="customer image"></a>
                                                        </div>
                                                        <div class="media-body align-self-center">
                                                            <a href="profile.html">
                                                                <h6 class="mt-0 text-dark font-weight-medium">Walter
                                                                    Reuter</h6>
                                                            </a>
                                                            <small>@walter.me</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>5 Orders</td>
                                                <td class="text-dark d-none d-md-block">$200</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="media-image mr-3 rounded-circle">
                                                            <a href="profile.html"><img
                                                                    class="profile-img rounded-circle w-45"
                                                                    src="assets/img/user/u3.jpg"
                                                                    alt="customer image"></a>
                                                        </div>
                                                        <div class="media-body align-self-center">
                                                            <a href="profile.html">
                                                                <h6 class="mt-0 text-dark font-weight-medium">Larissa
                                                                    Gebhardt</h6>
                                                            </a>
                                                            <small>@larissa.gb</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>1 Order</td>
                                                <td class="text-dark d-none d-md-block">$50</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="media-image mr-3 rounded-circle">
                                                            <a href="profile.html"><img
                                                                    class="profile-img rounded-circle w-45"
                                                                    src="assets/img/user/u4.jpg"
                                                                    alt="customer image"></a>
                                                        </div>
                                                        <div class="media-body align-self-center">
                                                            <a href="profile.html">
                                                                <h6 class="mt-0 text-dark font-weight-medium">Albrecht
                                                                    Straub</h6>
                                                            </a>
                                                            <small>@albrech.as</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>2 Orders</td>
                                                <td class="text-dark d-none d-md-block">$100</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="media-image mr-3 rounded-circle">
                                                            <a href="profile.html"><img
                                                                    class="profile-img rounded-circle w-45"
                                                                    src="assets/img/user/u5.jpg"
                                                                    alt="customer image"></a>
                                                        </div>
                                                        <div class="media-body align-self-center">
                                                            <a href="profile.html">
                                                                <h6 class="mt-0 text-dark font-weight-medium">Leopold
                                                                    Ebert</h6>
                                                            </a>
                                                            <small>@leopold.et</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>1 Order</td>
                                                <td class="text-dark d-none d-md-block">$60</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="media-image mr-3 rounded-circle">
                                                            <a href="profile.html"><img
                                                                    class="profile-img rounded-circle w-45"
                                                                    src="assets/img/user/u3.jpg"
                                                                    alt="customer image"></a>
                                                        </div>
                                                        <div class="media-body align-self-center">
                                                            <a href="profile.html">
                                                                <h6 class="mt-0 text-dark font-weight-medium">Larissa
                                                                    Gebhardt</h6>
                                                            </a>
                                                            <small>@larissa.gb</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>1 Order</td>
                                                <td class="text-dark d-none d-md-block">$50</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-7">
                             Top Products 
                            <div class="card card-default ec-card-top-prod">
                                <div class="card-header justify-content-between">
                                    <h2>Top Products</h2>
                                    <div>
                                        <button class="text-black-50 mr-2 font-size-20"><i
                                                class="mdi mdi-cached"></i></button>
                                        <div class="dropdown show d-inline-block widget-dropdown">
                                            <a class="dropdown-toggle icon-burger-mini" href="#" role="button"
                                               id="dropdown-product" data-bs-toggle="dropdown" aria-haspopup="true"
                                               aria-expanded="false" data-display="static">
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li class="dropdown-item"><a href="#">Update Data</a></li>
                                                <li class="dropdown-item"><a href="#">Detailed Log</a></li>
                                                <li class="dropdown-item"><a href="#">Statistics</a></li>
                                                <li class="dropdown-item"><a href="#">Clear Data</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body mt-10px mb-10px py-0">
                                    <div class="row media d-flex pt-15px pb-15px">
                                        <div
                                            class="col-lg-3 col-md-3 col-2 media-image align-self-center rounded">
                                            <a href="#"><img src="assets/img/products/p1.jpg" alt="customer image"></a>
                                        </div>
                                        <div class="col-lg-9 col-md-9 col-10 media-body align-self-center ec-pos">
                                            <a href="#">
                                                <h6 class="mb-10px text-dark font-weight-medium">Baby cotton shoes</h6>
                                            </a>
                                            <p class="float-md-right sale"><span class="mr-2">58</span>Sales</p>
                                            <p class="d-none d-md-block">Statement belting with double-turnlock hardware
                                                adds “swagger” to a simple.</p>
                                            <p class="mb-0 ec-price">
                                                <span class="text-dark">$520</span>
                                                <del>$580</del>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="row media d-flex pt-15px pb-15px">
                                        <div
                                            class="col-lg-3 col-md-3 col-2 media-image align-self-center rounded">
                                            <a href="#"><img src="assets/img/products/p2.jpg" alt="customer image"></a>
                                        </div>
                                        <div class="col-lg-9 col-md-9 col-10 media-body align-self-center ec-pos">
                                            <a href="#">
                                                <h6 class="mb-10px text-dark font-weight-medium">Hoodies for men</h6>
                                            </a>
                                            <p class="float-md-right sale"><span class="mr-2">20</span>Sales</p>
                                            <p class="d-none d-md-block">Statement belting with double-turnlock hardware
                                                adds “swagger” to a simple.</p>
                                            <p class="mb-0 ec-price">
                                                <span class="text-dark">$250</span>
                                                <del>$300</del>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="row media d-flex pt-15px pb-15px">
                                        <div
                                            class="col-lg-3 col-md-3 col-2 media-image align-self-center rounded">
                                            <a href="#"><img src="assets/img/products/p3.jpg" alt="customer image"></a>
                                        </div>
                                        <div class="col-lg-9 col-md-9 col-10 media-body align-self-center ec-pos">
                                            <a href="#">
                                                <h6 class="mb-10px text-dark font-weight-medium">Long slive t-shirt</h6>
                                            </a>
                                            <p class="float-md-right sale"><span class="mr-2">10</span>Sales</p>
                                            <p class="d-none d-md-block">Statement belting with double-turnlock hardware
                                                adds “swagger” to a simple.</p>
                                            <p class="mb-0 ec-price">
                                                <span class="text-dark">$480</span>
                                                <del>$654</del>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>-->
                </div> <!-- End Content -->
                <!--Footer-->
                <jsp:include page="../../common/sale/footer.jsp"></jsp:include>
                </div>
            </div>







            <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Chart.min.js"></script>

    </body>

    <script>
        //Chart order tron
        var doughnut = document.getElementById("doChart");
        if (doughnut !== null) {
        var myDoughnutChart = new Chart(doughnut, {
        type: "doughnut",
                data: {
                labels: ["completed", "pending", "canceled"],
                        datasets: [
                        {
                        label: ["Completed", "Pending", "Canceled"],
                                data: [${successOrder}, ${penddingOrder}, ${cancelOrder}],
                                backgroundColor: ["#50d7ab", "#f3d676", "#ed9090"],
                                borderWidth: 1
                                // borderColor: ['#88aaf3','#29cc97','#8061ef','#fec402']
                                // hoverBorderColor: ['#88aaf3', '#29cc97', '#8061ef', '#fec402']
                        }
                        ]
                },
                options: {
                responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                        display: false
                        },
                        cutoutPercentage: 75,
                        tooltips: {
                        callbacks: {
                        title: function (tooltipItem, data) {
                        return "Order : " + data["labels"][tooltipItem[0]["index"]];
                        },
                                label: function (tooltipItem, data) {
                                return data["datasets"][0]["data"][tooltipItem["index"]];
                                }
                        },
                                titleFontColor: "#888",
                                bodyFontColor: "#555",
                                titleFontSize: 12,
                                bodyFontSize: 14,
                                backgroundColor: "rgba(256,256,256,0.95)",
                                displayColors: true,
                                borderColor: "rgba(220, 220, 220, 0.9)",
                                borderWidth: 2
                        }
                }
        });
        }
    </script>

    <script>
        //chart bao cao doanh thu
        $(document).ready(function () {
        "use strict";
        var acquisition = document.getElementById("acquisition");
        if (acquisition !== null) {
// Lấy dữ liệu doanh thu hàng ngày
        var revenueData = [<c:forEach items="${allTotalOrder}" var="o">${o.totalMoney},</c:forEach>];
        var labels = [<c:forEach items="${allTotalOrder}" var="o">'${o.orderDate}',</c:forEach>];
        var configAcq = {
// Loại biểu đồ
        type: "line",
// Dữ liệu cho biểu đồ
                data: {
                labels: labels,
                        datasets: [
                        {
                        label: "Daily Revenue",
                                backgroundColor: "rgba(52, 116, 212, .2)",
                                borderColor: "rgba(52, 116, 212, .7)",
                                data: revenueData,
                                lineTension: 0.3,
                                pointBackgroundColor: "rgba(52, 116, 212,0)",
                                pointHoverBackgroundColor: "rgba(52, 116, 212,1)",
                                pointHoverRadius: 3,
                                pointHitRadius: 30,
                                pointBorderWidth: 2,
                                pointStyle: "rectRounded"
                        }
                        ]
                },
// Các tùy chọn cấu hình khác
                options: {
                responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                        display: false
                        },
                        scales: {
                        xAxes: [
                        {
                        gridLines: {
                        display: false
                        }
                        }
                        ],
                                yAxes: [
                                {
                                gridLines: {
                                display: true,
                                        color: "#eee",
                                        zeroLineColor: "#eee"
                                },
                                        ticks: {
                                        beginAtZero: true
                                        }
                                }
                                ]
                        },
                        tooltips: {
                        mode: "index",
                                titleFontColor: "#888",
                                bodyFontColor: "#555",
                                titleFontSize: 12,
                                bodyFontSize: 15,
                                backgroundColor: "rgba(256,256,256,0.95)",
                                displayColors: true,
                                xPadding: 20,
                                yPadding: 10,
                                borderColor: "rgba(220, 220, 220, 0.9)",
                                borderWidth: 2,
                                caretSize: 10,
                                caretPadding: 15
                        }
                }
        };
        var ctx = document.getElementById("acquisition").getContext("2d");
        var lineAcq = new Chart(ctx, configAcq);
        document.getElementById("acqLegend").innerHTML = lineAcq.generateLegend();
        }
        });
    </script>
    <script>
        // chart Nguoi dung
    var activity = document.getElementById("activity");
  if (activity !== null) {
    var activityData = [
      {
          first: [<c:forEach items="${allTotalNewUser}" var="o">${o.totalOrders},</c:forEach>],
        second: [<c:forEach items="${allTotalNewOrder}" var="o">${o.totalOrders},</c:forEach>]
      }
    ];

    var config = {
      // The type of chart we want to create
      type: "line",
      // The data for our dataset
      data: {
        labels: [ <c:forEach items="${allTotalNewUser}" var="o">'${o.orderDate}',</c:forEach>  ],
        datasets: [
          {
            label: "Newly Registered",
            backgroundColor: "transparent",
            borderColor: "rgba(82, 136, 255, .8)",
            data: activityData[0].first,
            lineTension: 0.4,
            pointRadius: 5,
            pointBackgroundColor: "rgba(255,255,255,1)",
            pointHoverBackgroundColor: "rgba(255,255,255,1)",
            pointBorderWidth: 2,
            pointHoverRadius: 7,
            pointHoverBorderWidth: 1
          },
          {
            label: "Newly Bought",
            backgroundColor: "transparent",
            borderColor: "rgba(255, 199, 15, .8)",
            data: activityData[0].second,
            lineTension: 0.4,
            borderDash: [10, 5],
            borderWidth: 1,
            pointRadius: 5,
            pointBackgroundColor: "rgba(255,255,255,1)",
            pointHoverBackgroundColor: "rgba(255,255,255,1)",
            pointBorderWidth: 2,
            pointHoverRadius: 7,
            pointHoverBorderWidth: 1
          }
        ]
      },
      // Configuration options go here
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          display: false
        },
        scales: {
          xAxes: [
            {
              gridLines: {
                display: false,
              },
              ticks: {
                fontColor: "#8a909d", // this here
              },
            }
          ],
          yAxes: [
            {
              gridLines: {
                fontColor: "#8a909d",
                fontFamily: "Roboto, sans-serif",
                display: true,
                color: "#eee",
                zeroLineColor: "#eee"
              },
              ticks: {
                // callback: function(tick, index, array) {
                //   return (index % 2) ? "" : tick;
                // }
                stepSize: 1,
                fontColor: "#8a909d",
                fontFamily: "Roboto, sans-serif"
              }
            }
          ]
        },
        tooltips: {
          mode: "index",
          intersect: false,
          titleFontColor: "#888",
          bodyFontColor: "#555",
          titleFontSize: 12,
          bodyFontSize: 15,
          backgroundColor: "rgba(256,256,256,0.95)",
          displayColors: true,
          xPadding: 10,
          yPadding: 7,
          borderColor: "rgba(220, 220, 220, 0.9)",
          borderWidth: 2,
          caretSize: 6,
          caretPadding: 5
        }
      }
    };

    var ctx = document.getElementById("activity").getContext("2d");
    var myLine = new Chart(ctx, config);

    var items = document.querySelectorAll("#user-activity .nav-tabs .nav-item");
    items.forEach(function(item, index){
      item.addEventListener("click", function() {
        config.data.datasets[0].data = activityData[index].first;
        config.data.datasets[1].data = activityData[index].second;
        myLine.update();
      });
    });
  }
</script>

<script>
    //chart feedback
    var cUser = document.getElementById("currentUser");
  if (cUser !== null) {
    var myUChart = new Chart(cUser, {
      type: "bar",
      data: {
        labels: [ <c:forEach items="${allFeedback}" var="o">'${o.orderDate}',</c:forEach> ],
        datasets: [
          {
            label: "Đánh giá",
            data: [<c:forEach items="${allFeedback}" var="o">${o.totalOrders},</c:forEach> ],
            // data: [2, 3.2, 1.8, 2.1, 1.5, 3.5, 4, 2.3, 2.9, 4.5, 1.8, 3.4, 2.8],
            backgroundColor: "#88aaf3"
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          display: false
        },
        scales: {
          xAxes: [
            {
              gridLines: {
                drawBorder: true,
                display: false,
              },
              ticks: {
                fontColor: "#8a909d",
                fontFamily: "Roboto, sans-serif",
                display: false, // hide main x-axis line
                beginAtZero: true,
                callback: function(tick, index, array) {
                  return index % 2 ? "" : tick;
                }
              },
              barPercentage: 1.8,
              categoryPercentage: 0.2
            }
          ],
          yAxes: [
            {
              gridLines: {
                drawBorder: true,
                display: true,
                color: "#eee",
                zeroLineColor: "#eee"
              },
              ticks: {
                fontColor: "#8a909d",
                fontFamily: "Roboto, sans-serif",
                display: true,
                beginAtZero: true
              }
            }
          ]
        },

        tooltips: {
          mode: "index",
          titleFontColor: "#888",
          bodyFontColor: "#555",
          titleFontSize: 12,
          bodyFontSize: 15,
          backgroundColor: "rgba(256,256,256,0.95)",
          displayColors: true,
          xPadding: 10,
          yPadding: 7,
          borderColor: "rgba(220, 220, 220, 0.9)",
          borderWidth: 2,
          caretSize: 6,
          caretPadding: 5
        }
      }
    });
  }
</script>

<script>
    var activity = document.getElementById("trendoforder");
  if (activity !== null) {
    var activityData = [
      {
          first: [<c:forEach items="${listSuccesOrderPerTotal}" var="o">${o.successOrders},</c:forEach>],
        second: [<c:forEach items="${listSuccesOrderPerTotal}" var="o">${o.totalOrders},</c:forEach>]
      }
    ];

    var config = {
      // The type of chart we want to create
      type: "line",
      // The data for our dataset
      data: {
        labels: [ <c:forEach items="${listSuccesOrderPerTotal}" var="o">'${o.orderDate}',</c:forEach> ],
        datasets: [
                        {
                            label: "Successful Order",
                            backgroundColor: "rgba(82, 136, 255, 0.2)",
                            borderColor: "rgba(82, 136, 255, 1)",
                            data: activityData[0].first,
                            lineTension: 0.4,
                            pointRadius: 6,
                            pointBackgroundColor: "rgba(82, 136, 255, 1)",
                            pointHoverBackgroundColor: "rgba(255, 255, 255, 1)",
                            pointBorderWidth: 3,
                            pointHoverRadius: 8,
                            pointHoverBorderWidth: 2,
                            borderWidth: 3,
                            fill: true
                        },
                        {
                            label: "Total Order",
                            backgroundColor: "rgba(255, 199, 15, 0.2)",
                            borderColor: "rgba(255, 199, 15, 1)",
                            data: activityData[0].second,
                            lineTension: 0.4,
                            borderDash: [5, 5],
                            pointRadius: 6,
                            pointBackgroundColor: "rgba(255, 199, 15, 1)",
                            pointHoverBackgroundColor: "rgba(255, 255, 255, 1)",
                            pointBorderWidth: 3,
                            pointHoverRadius: 8,
                            pointHoverBorderWidth: 2,
                            borderWidth: 3,
                            fill: true
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            display: true,
                            labels: {
                                color: "#333",
                                font: {
                                    size: 14,
                                    family: "Roboto, sans-serif"
                                }
                            }
                        },
                        tooltip: {
                            backgroundColor: "rgba(256,256,256,0.95)",
                            borderColor: "rgba(220, 220, 220, 0.9)",
                            borderWidth: 2,
                            titleColor: "#333",
                            titleFont: {
                                size: 14
                            },
                            bodyColor: "#555",
                            bodyFont: {
                                size: 14
                            },
                            padding: {
                                x: 10,
                                y: 10
                            },
                            displayColors: false,
                            caretSize: 6,
                            caretPadding: 10
                        }
                    },
                    scales: {
                        x: {
                            grid: {
                                display: false
                            },
                            ticks: {
                                color: "#8a909d",
                                font: {
                                    size: 12,
                                    family: "Roboto, sans-serif"
                                }
                            }
                        },
                        y: {
                            grid: {
                                color: "#eee",
                                zeroLineColor: "#eee"
                            },
                            ticks: {
                                stepSize: 1,
                                color: "#8a909d",
                                font: {
                                    size: 12,
                                    family: "Roboto, sans-serif"
                                }
                            }
                        }
                    },
                    elements: {
                        point: {
                            radius: 5,
                            backgroundColor: "#fff",
                            hoverRadius: 7,
                            hoverBorderWidth: 1
                        },
                        line: {
                            borderWidth: 2
                        }
                    }
                }
            };

    var ctx = document.getElementById("trendoforder").getContext("2d");
    var myLine = new Chart(ctx, config);

    var items = document.querySelectorAll("#userr-activity .nav-tabs .nav-item");
    items.forEach(function(item, index){
      item.addEventListener("click", function() {
        config.data.datasets[0].data = activityData[index].first;
        config.data.datasets[1].data = activityData[index].second;
        myLine.update();
      });
    });
  }
</script>
</html>

<%-- 
    Document   : cartcompletion
    Created on : Jul 1, 2024, 10:48:17 AM
    Author     : PC
--%>

<%@page import="model.Orders"%>
<%@page import="java.util.List"%>
<%@page import="model.ProductDetail"%>
<%@page import="model.Cart"%>
<%@page import="constant.Iconstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Order Tracking</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap.min.css">

        <!-- Theme style -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style.css">

        <!-- Reponsive -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/responsive.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-list.css">

        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.3/nouislider.min.css"
            rel="stylesheet"
            />

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>
    </head>
    <body>


        <jsp:include page="../../common/user/header.jsp"></jsp:include>



            <section class="flat-breadcrumb">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="breadcrumbs">
                                <li class="trail-item">
                                    <a href="${pageContext.request.contextPath}/home" title="">Home</a>
                                <span><img src="images/icons/arrow-right.png" alt=""></span>
                            </li>

                            <li class="trail-end">
                                <a href="javascript:void(0)" title="">Order Tracking</a>
                            </li>
                        </ul><!-- /.breacrumbs -->
                    </div><!-- /.col-md-12 -->
                </div><!-- /.row -->
            </div><!-- /.container -->
        </section><!-- /.flat-breadcrumb -->

        <section class="flat-tracking background">

            <div class="container">

                <div class="row mb-4">
                    <div class="col-md-12">
                        <div class="order-tracking">
                            <div class="title">
                                <h3>Track Your Order</h3>
                                <p class="subscibe">
                                    Enter order ID to check order status !
                                </p>
                            </div><!-- /.title -->
                            <div class="tracking-content">
                                <form action="${pageContext.request.contextPath}/ordertracking" method="get" accept-charset="utf-8">
                                    <div class="row">
                                        <div class="order-id col-md-8">
                                            <label for="order-id">Order ID</label>
                                            <input value="<%=request.getParameter("order-id") != null ? request.getParameter("order-id") : ""%>" type="text" id="order-id" name="order-id" placeholder="Enter order ID to check order status">
                                        </div><!-- /.one-half order-id -->

                                        <div class="btn-track col-md-4">
                                            <button type="submit">Track</button>
                                        </div><!-- /.container -->
                                    </div>
                                </form><!-- /.form -->
                            </div><!-- /.tracking-content -->
                        </div><!-- /.order-tracking -->
                    </div><!-- /.col-md-12 -->
                </div><!-- /.row -->

            </div><!-- /.container -->
        </section><!-- /.flat-tracking --> 


        <%
            Orders order = (Orders) request.getAttribute("orderTracking");

            if (request.getAttribute("error") != null && request.getAttribute("orderTracking") != null) {
        %>

        <div class="d-flex justify-content-center">
            <h3 style="color: red;padding: 20px 0;"><%= (String) request.getAttribute("error")%></h3>
        </div>

        <%
            }
        %>

        <%
            if (request.getAttribute("orderTracking") != null && request.getAttribute("error") == null) {


        %>


        <section class="mt-4 mb-4">

            <div class="ec-content-wrapper container">
                <div class="content">
                    <div class="row">
                        <div class="col-12">
                            <div class="ec-odr-dtl card card-default">
                                <div class="card-header card-header-border-bottom d-flex justify-content-between">
                                    <h2 class="ec-odr">Order Detail<br>
                                        <span class="small">Order ID: #${requestScope.orderTracking.orderId}</span>
                                    </h2>
                                </div>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-xl-3 col-lg-6">
                                            <address class="info-grid">
                                                <div class="info-title"><strong>Customer:</strong></div><br>
                                                <div class="info-content">
                                                    <%                                                        if (order.getUser().getRole().getRoleId() != 5) {
                                                    %>

                                                    <%= order.getUser().getFullName()%><br>
                                                    <%= order.getUser().getEmail()%><br>
                                                    <%= order.getUser().getAddress()%><br>
                                                    <abbr title="Phone">P:</abbr> <%= order.getUser().getPhoneNumber()%>

                                                    <%
                                                    } else {
                                                    %>

                                                    Anonymous

                                                    <%
                                                        }
                                                    %>
                                                </div>
                                            </address>
                                        </div>
                                        <div class="col-xl-3 col-lg-6">
                                            <address class="info-grid">
                                                <div class="info-title"><strong>Shipped To:</strong></div><br>
                                                <div class="info-content">
                                                    <%= order.getFullName()%><br>
                                                    <%= order.getEmail()%><br>
                                                    <%= order.getAddress()%><br>
                                                    <abbr title="Phone"></abbr> <%= order.getPhoneNumber()%>
                                                </div>
                                            </address>
                                        </div>
                                        <div class="col-xl-3 col-lg-6">
                                            <address class="info-grid">
                                                <div class="info-title"><strong>Payment Method:</strong></div><br>
                                                <div class="info-content">
                                                    <%= order.getPayment().getPaymentName()%>
                                                </div>
                                            </address>
                                        </div>
                                        <div class="col-xl-3 col-lg-6">
                                            <address class="info-grid">
                                                <div class="info-title"><strong>Order Date:</strong></div><br>
                                                <div class="info-content">
                                                    <%= Iconstant.formatDate(order.getOrderDate())%>
                                                </div>
                                            </address>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h3 class="tbl-title">PRODUCT SUMMARY</h3>
                                            <div class="table-responsive">
                                                <table class="table table-striped o-tbl">
                                                    <thead>
                                                        <tr class="line">
                                                            <td><strong>#</strong></td>
                                                            <td class="text-center"><strong>IMAGE</strong></td>
                                                            <td class="text-center"><strong>PRODUCT</strong></td>
                                                            <td class="text-center"><strong>PRICE/UNIT</strong></td>
                                                            <td class="text-right"><strong>QUANTITY</strong></td>
                                                            <td class="text-right"><strong>SUBTOTAL</strong></td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                        <%
                                                            Cart cart = (Cart) request.getAttribute("listOrderDetailTracking");

                                                            List<ProductDetail> listProductDetail = cart.getListProductDetails();
                                                            List<Integer> quantity = cart.getSoLuong();
                                                        %>

                                                        <%
                                                            for (int i = 0; i < listProductDetail.size(); i++) {
                                                                ProductDetail pd = listProductDetail.get(i);
                                                        %>

                                                        <tr>
                                                            <td><%= (i + 1)%></td>
                                                            <td><img style="width: 100px; height: auto" class="product-img"
                                                                     src="<%= pd.getProduct().getGallery().get(0)%>" alt="" /></td>
                                                            <td> <%= pd.getProduct().getProductName()%> </td>
                                                            <td class="text-center"><%= Iconstant.formatCurrency(pd.getPriceSale())%></td>
                                                            <td class="text-center"><%= quantity.get(i)%></td>
                                                            <td class="text-right"><%= Iconstant.formatCurrency(pd.getPriceSale() * quantity.get(i))%></td>
                                                        </tr>

                                                        <%
                                                            }
                                                        %>

                                                        <tr>
                                                            <td colspan="4">
                                                            </td>
                                                            <td class="text-right"><strong>Total</strong></td>
                                                            <td class="text-right"><strong><%= Iconstant.formatCurrency(cart.getTotalPriceAfterDiscount())%></strong></td>
                                                        </tr>

                                                        <tr>
                                                            <td colspan="4">
                                                            </td>
                                                            <td class="text-right"><strong>Payment Status</strong></td>
                                                            <td class="text-right"><strong><%= order.getStatus() == 2 ? "paid" : order.getStatus() == 0 ? "unpaid" : "order canceled"%></strong></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div> <!-- End Content -->
            </div> <!-- End Content Wrapper -->
        </section>

        <%
            }

            if (request.getAttribute("notFound") != null && request.getAttribute("orderTracking") == null) {
        %>

        <div class="d-flex justify-content-center">
            <h3 style="color: red;padding: 20px 0;"><%= (String) request.getAttribute("notFound")%></h3>
        </div>

        <%
            }


        %>





        <jsp:include page="../../common/user/footer.jsp"></jsp:include>


            <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/tether.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/waypoints.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.circlechart.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/easing.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.zoom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.flexslider-min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/owl.carousel.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/smoothscroll.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-ui.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.mCustomScrollbar.js"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBtRmXKclfDp20TvfQnpgXSDPjut14x5wk&amp;region=GB"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gmap3.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/waves.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.countdown.js"></script>

        <script src="https://kit.fontawesome.com/cbc13c02eb.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.3/nouislider.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/main.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-list.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/slider.js"></script>
    </body>
</html>

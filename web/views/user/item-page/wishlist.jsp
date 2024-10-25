<%-- 
    Document   : wishlist
    Created on : May 20, 2024, 12:33:20 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <head>
        <meta charset="UTF-8">
            <title>Techno Store - Wishlist</title>

            <meta name="author" content="CreativeLayers">

                <!-- Mobile Specific Metas -->
                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

                    <!-- Boostrap style -->
                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap.min.css">

                        <!-- Theme style -->
                        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style.css">

                            <!-- Reponsive -->
                            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/responsive.css">
                                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-wishlist.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>


                                    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">


                                        </head>
                                        <body class="header_sticky">
                                            <div class="boxed">

                                                <div class="overlay"></div>

                                                <!-- Preloader -->
                                                <div class="preloader">
                                                    <div class="clear-loading loading-effect-2">
                                                        <span></span>
                                                    </div>
                                                </div><!-- /.preloader -->

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
                                                                        <a href="javascript:void(0)" title="">Wish List</a>
                                                                    </li>
                                                                </ul><!-- /.breacrumbs -->
                                                            </div><!-- /.col-md-12 -->
                                                        </div><!-- /.row -->
                                                    </div><!-- /.container -->
                                                </section><!-- /.flat-breadcrumb -->


                                                <section class="flat-wishlist">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="wishlist">
                                                                    <div class="title">
                                                                        <h3>My wishlist</h3>
                                                                    </div>
                                                                    <div class="wishlist-content">
                                                                        <table class="table-wishlist">

                                                                            <thead>
                                                                                <tr>
                                                                                    <th>Product Name</th>
                                                                                    <th>Unit Price</th>
                                                                                    <th>Stock Status</th>
                                                                                    <th></th>
                                                                                </tr>
                                                                            </thead>

                                                                            <tbody id="item-productt">

                                                                                <c:forEach items="${sessionScope.listWishList}" var="map">
                                                                                    <tr>
                                                                                        <td>
                                                                                            <div onclick="confirmDeleteWishList(${map.key.productId})" class="delete">
                                                                                                <a href="javascript:void(0)" title=""><img src="images/icons/delete.png" alt=""></a>
                                                                                            </div>
                                                                                            <div class="product">
                                                                                                <div class="image">
                                                                                                    <img src="${map.key.gallery.get(0)}" alt="">
                                                                                                </div>
                                                                                                <div class="name">
                                                                                                    ${map.key.productName}
                                                                                                </div>
                                                                                            </div>
                                                                                        </td>
                                                                                        <td>
                                                                                            <span class="sale"><fmt:formatNumber value="${map.key.minPrice}" type="currency" /></span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <div class="status-product">
                                                                                                <c:if test="${map.value > 0}">
                                                                                                    <span>In stock</span>
                                                                                                </c:if>
                                                                                                <c:if test="${map.value == 0}">
                                                                                                    <span>Sold out</span>
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </td>
                                                                                        <td>
                                                                                            <div class="add-cart">
                                                                                                <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${map.key.productId}" title="">
                                                                                                    <img src="images/icons/add-cart.png" alt="">Add to Cart
                                                                                                </a>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                </c:forEach>

                                                                            </tbody>
                                                                        </table><!-- /.table-wishlist -->
                                                                    </div><!-- /.wishlist-content -->
                                                                </div><!-- /.wishlist -->
                                                            </div><!-- /.col-md-12 -->
                                                        </div><!-- /.row -->
                                                    </div><!-- /.container -->
                                                </section><!-- /.flat-wishlish -->


                                            </div><!-- /.boxed -->

                                            <jsp:include page="../../common/user/footer.jsp"></jsp:include>


                                                <!-- Javascript -->
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

                                            <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/main.js"></script>

                                            <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>




                                        </body>	

                                        </html>
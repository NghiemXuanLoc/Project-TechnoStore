<%-- 
    Document   : compareproduct
    Created on : May 19, 2024, 7:55:14 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <!-- Mirrored from creativelayers.net/themes/techno-html/compare.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:06:39 GMT -->
    <head>
        <!-- Basic Page Needs -->
        <meta charset="UTF-8">
            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>Techno Store - Compare</title>

            <meta name="author" content="CreativeLayers">

                <!-- Mobile Specific Metas -->
                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

                    <!-- Boostrap style -->
                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap.min.css">

                        <!-- Theme style -->
                        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style.css">

                            <!-- Reponsive -->
                            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/responsive.css">

                                <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">
                                        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>

                                    </head>
                                    <body class="header_sticky">


                                        <section class="flat-compare">

                                            <div class="container">

                                                <div class="row">
                                                    <div class="col-md-12">

                                                        <div class="wrap-compare">
                                                            <div class="title">
                                                                <h3>Compare</h3>
                                                            </div>

                                                            <div class="compare-content">
                                                                <table class="table-compare">
                                                                    <span style="margin-right: 3%" class="close" onclick="colseCompare()">&times;</span>
                                                                    <tbody>

                                                                        <tr>
                                                                            <th>Product</th>

                                                                            <td class="product">
                                                                                <div class="image">
                                                                                    <img style="max-width: 200px; height: auto" src="${pageContext.request.contextPath}/${product1.gallery.get(0)}" alt="">
                                                                                </div>
                                                                                <div class="name">
                                                                                    ${product1.productName}
                                                                                </div>
                                                                            </td><!-- /.product -->

                                                                            <td class="product">
                                                                                <div class="image">
                                                                                    <img style="max-width: 200px; height: auto" src="${pageContext.request.contextPath}/${product2.gallery.get(0)}" alt="">
                                                                                </div>
                                                                                <div class="name">
                                                                                    ${product2.productName}
                                                                                </div>
                                                                            </td><!-- /.product -->

                                                                        </tr>

                                                                        <tr>
                                                                            <th>Price</th>
                                                                            <td class="price">
                                                                                <fmt:formatNumber value="${product1.minPrice}" type="currency" /> - <fmt:formatNumber value="${product1.maxPrice}" type="currency" />
                                                                            </td>
                                                                            <td class="price">
                                                                                <fmt:formatNumber value="${product2.minPrice}" type="currency" /> - <fmt:formatNumber value="${product2.maxPrice}" type="currency" />
                                                                            </td>
                                                                        </tr>



                                                                        <tr>
                                                                            <th>Brief Info</th>
                                                                            <td class="description">
                                                                                <p>
                                                                                    ${product1.briefInfo}
                                                                                </p>
                                                                            </td><!-- /.description -->
                                                                            <td class="description">
                                                                                <p>
                                                                                    ${product2.briefInfo}
                                                                                </p>
                                                                            </td><!-- /.description -->

                                                                        </tr>

                                                                        <tr>
                                                                            <td></td>
                                                                            <td></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td></td>
                                                                            <td></td>
                                                                        </tr>

                                                                        <tr>
                                                                            <th>Description</th>
                                                                            <td class="description">
                                                                                <table style="margin-left: 30px" border="1px">

                                                                                </table>
                                                                            </td><!-- /.description -->

                                                                            <td class="description">
                                                                                <table style="margin-left: 30px" border="1px">

                                                                                </table>
                                                                            </td><!-- /.description -->
                                                                        </tr>

                                                                        <tr>
                                                                            <th>Stock</th>
                                                                            <td class="stock">
                                                                                <p>
                                                                                    ${quantityStockProduct1} items
                                                                                </p>
                                                                            </td><!-- /.stock -->
                                                                            <td class="stock">
                                                                                <p>
                                                                                    ${quantityStockProduct2} items
                                                                                </p>
                                                                            </td><!-- /.stock -->

                                                                        </tr>

                                                                        <tr>
                                                                            <th>Add to Cart</th>
                                                                            <td class="add-cart">
                                                                                <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product1.productId}" title=""><img src="images/icons/add-cart.png" alt="">Add to Cart</a>

                                                                            </td><!-- /.add-cart -->
                                                                            <td class="add-cart">
                                                                                <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product2.productId}" title=""><img src="images/icons/add-cart.png" alt="">Add to Cart</a>

                                                                            </td><!-- /.add-cart -->
                                                                        </tr>
                                                                    </tbody>
                                                                </table><!-- /.table-compare -->
                                                            </div><!-- /.compare-content -->
                                                        </div><!-- /.wrap-compare -->
                                                    </div><!-- /.col-md-12 -->
                                                </div><!-- /.row -->
                                            </div><!-- /.container -->
                                        </section><!-- /.flat-compare -->


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
                                        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gmap3.min.js"></script>
                                        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/waves.min.js"></script>
                                        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.countdown.js"></script>

                                        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/main.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>

                                    </body>	

                                    <!-- Mirrored from creativelayers.net/themes/techno-html/compare.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:06:39 GMT -->
                                    </html>
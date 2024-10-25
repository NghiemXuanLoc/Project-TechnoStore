<%-- 
    Document   : cartdetail.jsp
    Created on : May 24, 2024, 9:55:39 PM
    Author     : PC
--%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="model.ProductDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <!-- Mirrored from creativelayers.net/themes/techno-html/shop-cart.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:06:39 GMT -->
    <head>
        <!-- Basic Page Needs -->
        <meta charset="UTF-8">
            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>Techno Store - Shop Cart</title>

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

                                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">
                                        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>


                                        <style>
                                            .fixed {
                                                position: fixed;
                                                bottom: 0;
                                                left: 50%;
                                                transform: translateX(-50%);
                                                width: 90%;
                                                z-index: 1000;
                                                padding: 20px 50px;
                                                margin: auto;
                                                background-color: #fff;
                                                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                                            }

                                            .name-product {
                                                margin-bottom: 9px;
                                            }
                                        </style>
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


                                                    <!--thanh dieu huong-->
                                                    <section class="flat-breadcrumb" >
                                                        <div class="container">
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <ul class="breadcrumbs">
                                                                        <li class="trail-item">
                                                                            <a href="home" title="">Home</a>
                                                                            <span><img src="images/icons/arrow-right.png" alt=""></span>
                                                                        </li>
                                                                        <li class="trail-item">
                                                                            <a href="#" title="">Shop</a>
                                                                            <span><img src="images/icons/arrow-right.png" alt=""></span>
                                                                        </li>
                                                                        <li class="trail-end">
                                                                            <a href="javascript:void(0)" title="">Cart detail</a>
                                                                        </li>
                                                                    </ul><!-- /.breacrumbs -->
                                                                </div><!-- /.col-md-12 -->
                                                            </div><!-- /.row -->
                                                        </div><!-- /.container -->
                                                    </section><!-- /.flat-breadcrumb -->

                                                    <section class="flat-shop-cart">
                                                        <div class="container">
                                                            <div class="row">



                                                                <div class="col-lg-9">

                                                                    <div class="flat-row-title style1">
                                                                        <h3>Shopping Cart</h3>
                                                                    </div>
                                                                    <form id="formCartContact" action="checkout" method="post">
                                                                        <div class="table-cart">

                                                                            <table class="table table-striped table-hover">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th>Product ID</th>
                                                                                        <th>Image</th>
                                                                                        <th>Product Name</th>
                                                                                        <th>Color</th>
                                                                                        <th>Memory</th>
                                                                                        <th>Price Unit</th>
                                                                                        <th>Quantity</th>
                                                                                        <th>Total Price</th>
                                                                                        <th>Action</th>
                                                                                    </tr>
                                                                                </thead>

                                                                            <%
                                                                                Cart cart = (Cart) session.getAttribute("cart");

                                                                                Set<Integer> setChecked = (Set<Integer>) session.getAttribute("setChecked");

                                                                                Map<Integer, String> mapError = (Map<Integer, String>) session.getAttribute("mapError");

                                                                            %>


                                                                            <tbody id="table-data">

                                                                                <%                                                                                    if (cart != null) {
                                                                                        for (int i = cart.getListProductDetails().size() - 1; i >= 0; i--) {
                                                                                %>

                                                                                <tr style="position: relative" id="table-data-productdetail-<%= cart.getListProductDetails().get(i).getProductDetailId()%>">



                                                                                    <td style="text-align: center">
                                                                                        <input name="checked" <%= setChecked != null && setChecked.contains(cart.getListProductDetails().get(i).getProductDetailId()) == true ? "checked" :  "" %>  onchange="selectProductPayment()" value="<%= cart.getListProductDetails().get(i).getProductDetailId()%>" style="opacity: 1" type="checkbox" width="50px"/>
                                                                                        #<%= cart.getListProductDetails().get(i).getProductDetailId()%>
                                                                                        <input name="productDetailId" type="hidden" value="<%= cart.getListProductDetails().get(i).getProductDetailId()%>"/>
                                                                                    </td>

                                                                                    <td>
                                                                                        <div class="img-product">
                                                                                            <a href="productdetail?option=common&productId=<%= cart.getListProductDetails().get(i).getProduct().getProductId()%>"><img src="<%= cart.getListProductDetails().get(i).getProduct().getGallery().get(0)%>" alt=""></a>
                                                                                        </div>
                                                                                    </td>
                                                                                    <td>
                                                                                        <div class="name-product">
                                                                                            <a href="productdetail?option=common&productId=<%= cart.getListProductDetails().get(i).getProduct().getProductId()%>"><%= cart.getListProductDetails().get(i).getProduct().getProductName()%></a>

                                                                                            <p style="position: absolute; bottom: 5%; right:  2%" class="error text-danger">
                                                                                                <%= mapError != null && mapError.containsKey(cart.getListProductDetails().get(i).getProductDetailId()) ? mapError.get(cart.getListProductDetails().get(i).getProductDetailId()) : ""%>
                                                                                            </p>

                                                                                        </div>
                                                                                    </td>

                                                                                    <td>
                                                                                        <p><%= cart.getListProductDetails().get(i).getColor().getColorName()%></p>
                                                                                    </td>

                                                                                    <td style="text-align: left">
                                                                                        <p><%= cart.getListProductDetails().get(i).getMemory().getMemorySize()%></p>
                                                                                    </td>

                                                                                    <td>
                                                                                        <div class="price">
                                                                                            <span><%= (cart.getListProductDetails().get(i).getPriceSale() / 1000000)%></span>tr
                                                                                            <span id="price-origin-<%= cart.getListProductDetails().get(i).getProductDetailId()%>" style="text-decoration: line-through; font-size: 14px"><%= (cart.getListProductDetails().get(i).getPriceOrigin() / 1000000)%> </span> tr
                                                                                        </div>
                                                                                    </td>


                                                                                    <td>
                                                                                        <div class="quanlity">
                                                                                            <span style="left: 10px" onclick="upDowQuantityCartDetail('giam', <%= i%>, <%= cart.getListProductDetails().get(i).getProductDetailId()%>)" class="btn-down"></span>
                                                                                            <input style="width: 100px" onchange="changeQuantityInCart(<%= i%>, <%= cart.getListProductDetails().get(i).getProductDetailId()%>)" id="quantity<%= i%>" type="number" name="quantity" value="<%= cart.getSoLuong().get(i)%>" min="0" max="100" placeholder="Quanlity">
                                                                                                <span style="right: 30px" onclick="upDowQuantityCartDetail('tang', <%= i%>, <%= cart.getListProductDetails().get(i).getProductDetailId()%>)" class="btn-up"></span>
                                                                                        </div>


                                                                                    </td>



                                                                                    <td>
                                                                                        <div class="total">
                                                                                            <span><%= (cart.getListProductDetails().get(i).getPriceSale() * cart.getSoLuong().get(i) / 1000000)%></span> tr
                                                                                        </div>
                                                                                    </td>
                                                                                    <td>
                                                                                        <a onclick="deleteProductFromCart(<%= cart.getListProductDetails().get(i).getProductDetailId()%>)" href="javascript:void(0)" title="">
                                                                                            <img src="images/icons/delete.png" alt="">
                                                                                        </a>
                                                                                    </td>
                                                                                </tr>

                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>

                                                                            </tbody>
                                                                        </table>

                                                                    </div><!-- /.table-cart -->

                                                                    <div class="clearfix"></div>

                                                                    <div class="row" id="payment-div">
                                                                        <div class="col-md-4">
                                                                            <input id="selectAll" onchange="selectAllProductPayment()" style="opacity: 1" type="checkbox"/> <label for="selectAll">Select all</label>

                                                                            <button type="button" style="background-color: #F28B00; position: absolute; left: 0; bottom: 0;"><a style="color: white" href="productlist">Choose more product</a></button>
                                                                        </div>
                                                                        <div class="col-md-8">
                                                                            <div class="row" style="height: 200px;">
                                                                                <span class="col-md-4">Price Origin</span>
                                                                                <span id="totalPrice" class="col-md-8" style="text-align: right; font-size: 22px">0 đ</span>
                                                                                <br/>

                                                                                <span class="col-md-4">discounts</span>
                                                                                <span id="discounts" class="col-md-8" style="text-align: right; font-size: 20px">0 đ</span>
                                                                                <br/>

                                                                                <span class="col-md-4">Price sale</span>
                                                                                <span id="sub-total" class="sale col-md-8" style="text-align: right">0 đ</span>
                                                                                <br/>

                                                                                <span class="col-md-4">Product selected</span>
                                                                                <span id="quantity-product" class="sale col-md-8" style="text-align: right; margin-bottom: 40px">0</span>

                                                                                <button onclick="handleSubmitForm()" type="button" style="background-color: #F28B00; position: absolute; right: 0; bottom: 0;">Check out</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </form>








                                                            </div><!-- /.col-lg-8 -->


                                                            <div class="sidebar col-md-3">

                                                                <div class="widget widget-categories">
                                                                    <div class="widget-title">
                                                                        <h3>Contact</h3>

                                                                        <ul class="cat-list style1 widget-content">

                                                                            <li>
                                                                                <a href="mailto:nghiemxuanloc02@gmail.com?subject=Thắc mắc mua hàng by ${sessionScope.account == null ? 'anonymous' : sessionScope.account.fullName}"><i class="fa-solid fa-envelope"></i> nghiemxuanloc02@gmail.com</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="https://maps.app.goo.gl/drgj3LA8TMcdEmf8A" target="_blank"> <i class="fa-solid fa-globe"></i> Locate Our Shop</a>
                                                                            </li>

                                                                            <li>
                                                                                <a href="https://www.facebook.com/nghiemxuanloc211" target="_blank"> <i class="fa-brands fa-facebook"></i> Nghiem Xuan Loc</a>
                                                                            </li>

                                                                            <li>
                                                                                <a href="tel:0123456789"><i class="fa-solid fa-phone"></i> 0337783926</a>
                                                                            </li>
                                                                        </ul><!-- /.cat-list -->
                                                                    </div>

                                                                </div><!-- /.widget-categories -->

                                                                <div class="widget widget-categories">
                                                                    <form action="productlist">
                                                                        <div class="widget-title">
                                                                            <h3>Search box</h3>
                                                                            <input type="text" name="searchBox" value="${param.searchBox}" placeholder="Input your product search box!"/>
                                                                        </div>
                                                                    </form>
                                                                </div><!-- /.widget-categories -->

                                                                <div class="widget widget-categories">
                                                                    <div class="widget-title">
                                                                        <h3>Categories<span></span></h3>
                                                                    </div>

                                                                    <ul class="cat-list style1 widget-content">

                                                                        <c:forEach items="${sessionScope.categoryAll}" var="category">
                                                                            <li>
                                                                                <span><a href="${pageContext.request.contextPath}/productlist?category=${category.categoryId}">${category.categoryName}</a></span>
                                                                            </li>
                                                                        </c:forEach>
                                                                    </ul><!-- /.cat-list -->
                                                                </div><!-- /.widget-categories -->


                                                                <div class="widget widget-products">

                                                                    <div class="widget-title">
                                                                        <h3>Lastest Products<span></span></h3>
                                                                    </div>

                                                                    <ul class="product-list widget-content">

                                                                        <c:forEach items="${sessionScope.top2LastProduct}" var="product">
                                                                            <li>
                                                                                <div class="img-product">
                                                                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                                                                        <img style="width: 100px; height: auto"  src="${pageContext.request.contextPath}/${product.gallery.get(0)}" alt="">
                                                                                    </a>
                                                                                </div>
                                                                                <div class="info-product">
                                                                                    <div class="name">
                                                                                        <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">${product.productName}</a>
                                                                                        <img style="margin-left: 10px"  onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')"  src="images/icons/compare.png" alt="">
                                                                                            <img onclick="selectProductWishList(${product.productId})" src="images/icons/wishlist.png" alt="">
                                                                                                </div>

                                                                                                <div class="queue">
                                                                                                    <c:set value="${product.avgRating}" var="star"/>

                                                                                                    <c:set value="${product.avgRating % 1 > 0 ? product.avgRating -  product.avgRating % 1 : product.avgRating}" var="starFull"/>
                                                                                                    <c:set value="${product.avgRating % 1 > 0 ? 1 : 0}" var="starHaft"/>
                                                                                                    <c:set value="${5 - starFull - starHaft}" var="starEmpty"/>

                                                                                                    <c:forEach begin="1" end="${starFull}">
                                                                                                        <!--hien thi ngoi sao day du-->
                                                                                                        <i class="fa fa-star" aria-hidden="true"></i> 
                                                                                                    </c:forEach>

                                                                                                    <c:forEach begin="1" end="${starHaft}">
                                                                                                        <!--hien thi ngoi sao 1/2 rong-->
                                                                                                        <i class="fa fa-star-half-empty" aria-hidden="true"></i>
                                                                                                    </c:forEach>

                                                                                                    <c:forEach begin="1" end="${starEmpty}">
                                                                                                        <!--hien thi sao rong-->
                                                                                                        <i class="fa fa-star-o" aria-hidden="true"></i>
                                                                                                    </c:forEach>


                                                                                                </div>
                                                                                                <div class="price">
                                                                                                    <span class="sale"><fmt:formatNumber value="${product.minPrice}" type="currency" /></span>
                                                                                                    <span class="regular"><fmt:formatNumber value="${product.minPriceOrigin}" type="currency" /></span>
                                                                                                </div>
                                                                                                <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                                                                                <c:if test="${lengthBrief > 30}">
                                                                                                    <span>${product.briefInfo.substring(0, 30)}...</span>
                                                                                                </c:if>

                                                                                                <c:if test="${lengthBrief <= 30}">
                                                                                                    <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                                                                                </c:if>
                                                                                                </div>
                                                                                                </li>	 
                                                                                            </c:forEach>

                                                                                            </ul>
                                                                                            </div><!-- /.widget widget-products -->

                                                                                            </div><!-- /.sidebar -->

                                                                                            <div class="clearfix"></div>






                                                                                            </div><!-- /.row -->







                                                                                            </div><!-- /.container -->


                                                                                            </section><!-- /.flat-shop-cart -->
                                                                                            <div class="toast-container" id="toast-container"></div>

                                                                                            <jsp:include page="../../common/user/footer.jsp"></jsp:include>

                                                                                                </div><!-- /.boxed -->

                                                                                                <!-- Javascript -->

                                                                                                <script>
                                                                                                    window.addEventListener('scroll', function () {
                                                                                                        var paymentDiv = document.getElementById('payment-div');
                                                                                                        var paymentDivRect = paymentDiv.getBoundingClientRect();
                                                                                                        var scrollPosition = window.scrollY || window.pageYOffset;

                                                                                                        //                                                        console.log(scrollPosition + 500);
                                                                                                        if ((scrollPosition + 500) >= paymentDivRect.top) {
                                                                                                            paymentDiv.classList.remove('fixed');
                                                                                                        } else {
                                                                                                            paymentDiv.classList.add('fixed');
                                                                                                        }
                                                                                                    });

                                                                                                    //
                                                                                                    //                                                    // Lấy phần tử div cần thay đổi
                                                                                                    //                                                    var paymentDiv = document.getElementById("payment-div");
                                                                                                    //
                                                                                                    //// Lấy vị trí ban đầu của phần tử div
                                                                                                    //                                                    var paymentDivOffset = paymentDiv.offsetTop;
                                                                                                    //                                                    console.log(paymentDivOffset);
                                                                                                    //
                                                                                                    //// Kiểm tra vị trí cuộn của trang khi cuộn
                                                                                                    //                                                    window.onscroll = function () {
                                                                                                    //                                                        scrollFunction();
                                                                                                    //                                                    };
                                                                                                    //
                                                                                                    //                                                    function scrollFunction() {
                                                                                                    //                                                        // Lấy vị trí hiện tại của trang
                                                                                                    //                                                        var currentScrollPos = window.pageYOffset;
                                                                                                    //                                                        
                                                                                                    //                                                        console.log("vi tri man hinh: " + currentScrollPos);
                                                                                                    //                                                        console.log("vi tri div: " + paymentDivOffset);
                                                                                                    //
                                                                                                    //                                                        // Kiểm tra nếu vị trí cuộn lớn hơn hoặc bằng vị trí ban đầu của div
                                                                                                    //                                                        if (currentScrollPos >= paymentDivOffset) {
                                                                                                    //                                                            // Xóa bỏ trạng thái fixed
                                                                                                    //                                                            paymentDiv.classList.remove("fixed");
                                                                                                    //                                                        } else {
                                                                                                    //                                                            // Thêm trạng thái fixed
                                                                                                    //                                                            paymentDiv.classList.add("fixed");
                                                                                                    //                                                        }
                                                                                                    //                                                    }


                                                                                                </script>
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
                                                                                            <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/main.js"></script>
                                                                                            <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>
                                                                                            <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-cart-summary.js"></script>
                                                                                            <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>

                                                                                            </body>	

                                                                                            <!-- Mirrored from creativelayers.net/themes/techno-html/shop-cart.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:06:39 GMT -->
                                                                                            </html>
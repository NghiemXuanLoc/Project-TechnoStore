<%-- 
    Document   : cartcompletion
    Created on : Jul 1, 2024, 10:48:17 AM
    Author     : PC
--%>

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
        <title>Cart Completion</title>

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


            <!--Begin display -->
            <div class="container">
                <div class="row mt-5">


                    <div class="col-md-9">
                        <center>
                            <div class="header clearfix">
                                <h3 class="text-muted">COMPLETION</h3>
                            </div>

                            <div class=" mt-3">
                            <c:if test="${requestScope.mess == 'GD Thanh cong'}">
                                <h4>Your order has been confirmed, please check your email: <span class="text-success ms-2 me-2"><%= Iconstant.getEmailNofication(request.getParameter("vnp_TxnRef")) %></span> to see invoice details with product feedback instructions.</h4>
                            </c:if>
                        </div>

                        <div class="table-responsive">
                            <div class="form-group mt-3">
                                <label style="font-size: 20px">Order ID: <span class="text-success">#<%=request.getParameter("vnp_TxnRef")%></span></label>
                            </div>    
                            <div class="form-group">
                                <label style="font-size: 20px">Amount: <span class="text-success"><%= Iconstant.formatCurrency(Double.parseDouble(request.getParameter("vnp_Amount")) / 100) %></span></label>
                            </div>  


                            <div class="form-group">
                                <label style="font-size: 20px">Order Date: <span class="text-success"><%= Iconstant.convertDateTimeFormat(request.getParameter("vnp_PayDate")) %></span></label>
                            </div> 
                            <div class="form-group">
                                <label >Transaction status: ${requestScope.mess}</label>
                            </div> 
                        </div>

                        <c:if test="${requestScope.mess == 'GD Thanh cong' || requestScope.mess == 'Chưa thanh toán/Thanh toán không thành công'}">
                            <div class=" mt-3">

                                <form action="${pageContext.request.contextPath}/ordertracking" method="get" accept-charset="utf-8">
                                    <div class="row">
                                        <div class="order-id col-md-12">
                                            <input value="<%= request.getParameter("vnp_TxnRef") %>" type="hidden" id="order-id" name="order-id" placeholder="Enter order ID to check order status">
                                            <button type="submit" class="btn btn-success">check your order</button>
                                        </div><!-- /.one-half order-id -->

                                      
                                    </div>
                                </form><!-- /.form -->

                            </div>
                        </c:if>

                    </center>
                </div>


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
            </div>
        </div>  

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

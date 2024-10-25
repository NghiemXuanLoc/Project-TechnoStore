<%-- 
    Document   : productdetail.jsp
    Created on : May 18, 2024, 4:36:20 PM
    Author     : PC
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page import="dal.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <head>
        <!-- Basic Page Needs -->
        <meta charset="UTF-8">
            <title>Techno Store - Single Product 05</title>

            <meta name="author" content="CreativeLayers">

                <!-- Mobile Specific Metas -->
                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

                    <!-- Boostrap style -->
                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap.min.css">

                        <!-- Theme style -->
                        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style.css">

                            <!-- Reponsive -->
                            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/responsive.css">

                                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">

                                    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">

                                        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>

                                        <style>
                                            .image-feedback img{
                                                width: 150px;
                                                height: auto;
                                                border-radius: 5px;
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



                                                    <section class="flat-breadcrumb">
                                                        <div class="container">
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <ul class="breadcrumbs">
                                                                        <li class="trail-item">
                                                                            <a href="${pageContext.request.contextPath}/home" title="">Home</a>
                                                                        <span><img src="images/icons/arrow-right.png" alt=""></span>
                                                                    </li>
                                                                    <li class="trail-item">
                                                                        <a href="#" title="">Shop</a>
                                                                        <span><img src="images/icons/arrow-right.png" alt=""></span>
                                                                    </li>
                                                                    <li class="trail-end">
                                                                        <a href="#" title="">${sessionScope.product.productName}</a>
                                                                    </li>
                                                                </ul><!-- /.breacrumbs -->
                                                            </div><!-- /.col-md-12 -->
                                                        </div><!-- /.row -->
                                                    </div><!-- /.container -->
                                                </section><!-- /.flat-breadcrumb -->

                                                <main id="single-product">

                                                    <div class="container">
                                                        <div class="row">

                                                            <div class="col-lg-4 col-md-4">

                                                                <div class="sidebar ">


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

                                                                            <c:forEach items="${sessionScope.top3LastProduct}" var="product">
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

                                                                                                <div class="widget widget-categories">
                                                                                                    <form action="productlist">
                                                                                                        <div class="widget-title">
                                                                                                            <h3>Product search box</h3>
                                                                                                            <input type="text" name="searchBox" value="${param.searchBox}" placeholder="Input your product search box!"/>
                                                                                                        </div>
                                                                                                    </form>
                                                                                                </div><!-- /.widget-categories -->

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




                                                                                                </ul>
                                                                                                </div><!-- /.widget widget-products -->

                                                                                                </div><!-- /.sidebar -->
                                                                                                </div><!-- /.col-lg-3 col-md-4 -->


                                                                                                <div class="col-lg-8 col-md-8">

                                                                                                    <div class="flat-product-detail">
                                                                                                        <div class="row">
                                                                                                            <div class="box-flexslider">

                                                                                                                <div class="flexslider">
                                                                                                                    <ul class="slides">
                                                                                                                        <c:set var="index" value="${0}"/>
                                                                                                                        <c:forEach  begin="1" end="${sessionScope.product.gallery.size()}">
                                                                                                                            <li data-thumb="${pageContext.request.contextPath}/${sessionScope.product.gallery.get(index)}">
                                                                                                                                <a href='#' id="zoom" class='zoom'><img src="${pageContext.request.contextPath}/${sessionScope.product.gallery.get(index)}" alt='' width='400' height='300' /></a>
                                                                                                                                <c:set var="index" value="${index + 1}"/>
                                                                                                                            </li>
                                                                                                                        </c:forEach>


                                                                                                                    </ul><!-- /.slides -->
                                                                                                                </div><!-- /.flexslider -->

                                                                                                            </div><!-- /.box-flexslider -->
                                                                                                            <div class="product-detail style5">
                                                                                                                <div class="header-detail">
                                                                                                                    <h4 class="name">${sessionScope.product.productName}</h4>
                                                                                                                    <div class="category">
                                                                                                                        ${sessionScope.product.category.categoryName}
                                                                                                                    </div>
                                                                                                                    <div class="reviewed">

                                                                                                                        <div class="review">
                                                                                                                            <div class="queue">
                                                                                                                                <c:set value="${sessionScope.product.avgRating}" var="star"/>

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
                                                                                                                                <span>${sessionScope.product.avgRating}</span>
                                                                                                                            </div>
                                                                                                                            <div class="text">
                                                                                                                                <span>${sessionScope.feedbackAll.size()} Reviews</span>
                                                                                                                            </div>
                                                                                                                        </div>

                                                                                                                        <div style="text-align: start;" class="status-product">
                                                                                                                            <span style="background-color: #f28b00">Availablity:&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.quantityStock}</span>
                                                                                                                            <span style="background-color: #f28b00; margin-top: 5px">Sold:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.quantitySold}</span>
                                                                                                                        </div>

                                                                                                                    </div>
                                                                                                                </div><!-- /.header-detail -->
                                                                                                                <div class="content-detail">
                                                                                                                    <c:if test="${requestScope.productDetail == null}">
                                                                                                                        <div class="price">
                                                                                                                            <div class="regular">
                                                                                                                                <fmt:formatNumber value="${sessionScope.product.minPriceOrigin}" type="currency" /> - <fmt:formatNumber value="${sessionScope.product.maxPriceOrigin}" type="currency" />
                                                                                                                            </div>
                                                                                                                            <div style="font-size: 20px" class="sale">
                                                                                                                                <fmt:formatNumber value="${sessionScope.product.minPrice}" type="currency" /> - <fmt:formatNumber value="${sessionScope.product.maxPrice}" type="currency" />
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </c:if>

                                                                                                                    <c:if test="${requestScope.productDetail != null}">
                                                                                                                        <div class="price">
                                                                                                                            <div class="regular">
                                                                                                                                <fmt:formatNumber value="${requestScope.productDetail.priceOrigin}" type="currency" />
                                                                                                                            </div>
                                                                                                                            <div style="font-size: 30px;" class="sale">
                                                                                                                                <fmt:formatNumber value="${requestScope.productDetail.priceSale}" type="currency" />
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </c:if>
                                                                                                                    <div class="info-text">
                                                                                                                        ${sessionScope.product.briefInfo}
                                                                                                                    </div>
                                                                                                                </div><!-- /.content-detail -->

                                                                                                                <span class="option-note">Memory:</span>
                                                                                                                <div class="option" id="memory">
                                                                                                                    <c:forEach items="${sessionScope.memoryAll}" var="memory">


                                                                                                                        <c:if test="${memory.memorySize % 1024 == 0}">
                                                                                                                            <span onclick="optionChoose(this, 'memory', ${sessionScope.product.productId}, ${memory.memoryId})" id="memory${memory.memoryId}"><fmt:formatNumber value="${memory.memorySize/1024}" type="number" maxFractionDigits="0"/>&nbsp;TB</span>
                                                                                                                        </c:if>
                                                                                                                        <c:if test="${memory.memorySize % 1024 != 0}">
                                                                                                                            <span onclick="optionChoose(this, 'memory', ${sessionScope.product.productId}, ${memory.memoryId})" id="memory${memory.memoryId}">${memory.memorySize}&nbsp;GB</span>
                                                                                                                        </c:if>


                                                                                                                    </c:forEach>
                                                                                                                </div>
                                                                                                                <span class="option-note" >Color:</span>
                                                                                                                <div class="option" id="color">

                                                                                                                    <c:forEach items="${sessionScope.colorAll}" var="color">
                                                                                                                        <span onclick="optionChoose(this, 'color', ${sessionScope.product.productId}, ${color.colorId})" id="color${color.colorId}">${color.colorName}</span>
                                                                                                                    </c:forEach>

                                                                                                                </div>
                                                                                                                <div class="footer-detail">

                                                                                                                    <div class="quanlity-box">
                                                                                                                        <div class="quanlity">
                                                                                                                            <span onclick="upDowQuantity('giam', 0)" class="btn-down"></span>
                                                                                                                            <input onchange="limit()" id="quantity0" type="number" name="number" value="" min="1" max="100" placeholder="Quanlity">
                                                                                                                                <span onclick="upDowQuantity('tang', 0)" class="btn-up"></span>
                                                                                                                        </div>

                                                                                                                    </div>
                                                                                                                    <div class="box-cart style2">
                                                                                                                        <div class="btn-add-cart">
                                                                                                                            <a id="load-data-button" onclick="checkQuantityAddCart(${sessionScope.quantityStock}, ${param.productId})" href="javascript:void(0)" title=""><img src="images/icons/add-cart.png" alt="Add to cart">Add to Cart</a>
                                                                                                                        </div>
                                                                                                                        <div class="compare-wishlist">
                                                                                                                            <a onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')" href="javascript:void(0)" title=""><img src="images/icons/compare.png" alt="">Compare</a>
                                                                                                                            <a onclick="selectProductWishList(${product.productId})" href="cjavascript:void(0)" title=""><img src="images/icons/wishlist.png" alt="">Wishlist</a>
                                                                                                                        </div>
                                                                                                                    </div>

                                                                                                                </div><!-- /.footer-detail -->
                                                                                                            </div><!-- /.product-detail style5 -->
                                                                                                            <div class="clearfix"></div>
                                                                                                        </div><!-- /.row -->
                                                                                                    </div><!-- /.flat-product-detail -->

                                                                                                    <div class="flat-product-content style2">
                                                                                                        <%
                                                                                                            ProductDAO pd = new ProductDAO();
                                                                                                            Product product = (Product) session.getAttribute("product");
                                                                                                            String description_raw = product.getDescription();

                                                                                                            List<String> name = new ArrayList<>();
                                                                                                            List<String> value = new ArrayList<>();
                                                                                                            if (!description_raw.isEmpty()) {
                                                                                                                String[] description = description_raw.split("\\*");

                                                                                                                for (String item : description) {
                                                                                                                    String[] tachChuoi = item.split("\\:");
                                                                                                                    if (tachChuoi.length == 2) {
                                                                                                                        name.add(tachChuoi[0].trim());
                                                                                                                        value.add(tachChuoi[1].trim());
                                                                                                                    } else if (tachChuoi.length == 1) {
                                                                                                                        name.add(tachChuoi[0].trim());
                                                                                                                        value.add("");
                                                                                                                    }
                                                                                                                }
                                                                                                            }

                                                                                                            request.setAttribute("nameDescription", name);
                                                                                                            request.setAttribute("valueDescription", value);
                                                                                                        %>

                                                                                                        <div class="row">
                                                                                                            <ul class="product-detail-bar">
                                                                                                                <li>Tecnical Specs</li>
                                                                                                                <li>Reviews</li>
                                                                                                            </ul><!-- /.product-detail-bar -->

                                                                                                            <div class="col-md-12">
                                                                                                                <div class="row">
                                                                                                                    <div class="col-md-12">
                                                                                                                        <div class="tecnical-specs">
                                                                                                                            <h4 class="name">
                                                                                                                                ${sessionScope.product.productName}
                                                                                                                            </h4>
                                                                                                                            <table>
                                                                                                                                <tbody>
                                                                                                                                    <c:set var="index" value="${0}"/>
                                                                                                                                    <c:forEach begin="1" end="${requestScope.nameDescription.size()}">
                                                                                                                                        <tr>
                                                                                                                                            <td>${requestScope.nameDescription.get(index)}</td>
                                                                                                                                            <td>${requestScope.valueDescription.get(index)}</td>
                                                                                                                                            <c:set var="index" value="${index + 1}"/>
                                                                                                                                        </tr>
                                                                                                                                    </c:forEach>
                                                                                                                                </tbody>
                                                                                                                            </table>
                                                                                                                        </div><!-- /.tecnical-specs -->
                                                                                                                    </div><!-- /.col-md-12 -->
                                                                                                                </div><!-- /.row -->

                                                                                                                <div class="row">

                                                                                                                    <div class="col-lg-4">

                                                                                                                        <div class="rating style1">
                                                                                                                            <div class="title">
                                                                                                                                Based on ${sessionScope.feedbackAll.size()} reviews
                                                                                                                            </div>
                                                                                                                            <div class="score">
                                                                                                                                <div class="average-score">
                                                                                                                                    <p class="numb">${sessionScope.product.avgRating} / 5</p>
                                                                                                                                    <p class="text">Average score</p>
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
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                        </div><!-- /.rating style1 -->
                                                                                                                    </div><!-- /.col-lg-6 -->

                                                                                                                    <div class="col-lg-8 row reivewss" id="reivewss">
                                                                                                                        <span id="start" onclick="selectStar(this, 'all', ${sessionScope.product.productId}, ${sessionScope.feedbackAll.size()})">All(${sessionScope.feedbackAll.size()})</span>
                                                                                                                        <span id="5star" onclick="selectStar(this, 5, ${sessionScope.product.productId}, ${sessionScope.feedback5star.size()})">5 Star(${sessionScope.feedback5star.size()})</span>
                                                                                                                        <span onclick="selectStar(this, 4, ${sessionScope.product.productId}, ${sessionScope.feedback4star.size()})">4 Star(${sessionScope.feedback4star.size()})</span>
                                                                                                                        <span onclick="selectStar(this, 3, ${sessionScope.product.productId}, ${sessionScope.feedback3star.size()})">3 Star(${sessionScope.feedback3star.size()})</span>
                                                                                                                        <span onclick="selectStar(this, 2, ${sessionScope.product.productId}, ${sessionScope.feedback2star.size()})">2 Star(${sessionScope.feedback2star.size()})</span>
                                                                                                                        <span onclick="selectStar(this, 1, ${sessionScope.product.productId}, ${sessionScope.feedback1star.size()})">1 Star(${sessionScope.feedback1star.size()})</span>
                                                                                                                        <span onclick="selectStar(this, 'image', ${sessionScope.product.productId}, ${sessionScope.feedbackImage.size()})">Image(${sessionScope.feedbackImage.size()})</span>
                                                                                                                    </div><!-- /.col-lg-6 -->


                                                                                                                    <div class="col-lg-12">
                                                                                                                        <!-- hien thi danh sach review cua user -->
                                                                                                                        <ul class="review-list" id="review-list">

                                                                                                                        </ul><!-- /.review-list -->
                                                                                                                    </div><!-- /.col-lg-12 -->


                                                                                                                    <ul id="pagination" class="flat-pagination">

                                                                                                                        <!--                                                                                                                        <li class="prev">
                                                                                                                                                                                                        <a href="#" title="">
                                                                                                                                                                                                        <img src="images/icons/left-1.png" alt="">Prev Page
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        </li>
                                                                                                                                                                                                        
                                                                                                                                                                                                        <li class="next">
                                                                                                                                                                                                        <a href="#" title="">
                                                                                                                                                                                                        Next Page<img src="images/icons/right-1.png" alt="">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        </li>-->
                                                                                                                    </ul>

                                                                                                                    <!-- /.flat-pagination -->

                                                                                                                    <!--
                                                                                                                                                                                                        <div style="margin-top: 50px" class=" col-lg-12 form-review style2">
                                                                                                                                                                                                        <div class="title">
                                                                                                                                                                                                        Add a review 
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="your-rating queue">
                                                                                                                                                                                                        <span>Your Rating</span>
                                                                                                                                                                                                        <i class="fa fa-star" aria-hidden="true"></i>
                                                                                                                                                                                                        <i class="fa fa-star" aria-hidden="true"></i>
                                                                                                                                                                                                        <i class="fa fa-star" aria-hidden="true"></i>
                                                                                                                                                                                                        <i class="fa fa-star" aria-hidden="true"></i>
                                                                                                                                                                                                        <i class="fa fa-star" aria-hidden="true"></i>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <form action="#" method="get" accept-charset="utf-8">
                                                                                                                                                                                                        <div class="review-form-name">
                                                                                                                                                                                                        <input type="text" name="name-author" value="" placeholder="Name">
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="review-form-email">
                                                                                                                                                                                                        <input type="text" name="email-author" value="" placeholder="Email">
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="review-form-comment">
                                                                                                                                                                                                        <textarea name="review-text" placeholder="Your Name"></textarea>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="btn-submit">
                                                                                                                                                                                                        <button type="submit">Add Review</button>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        </form>
                                                                                                                                                                                                        </div> /.form-review style2 -->
                                                                                                                </div><!-- /.row -->
                                                                                                            </div><!-- /.col-md-12 -->
                                                                                                        </div><!-- /.row -->
                                                                                                    </div><!-- /.flat-product-content style2 -->
                                                                                                </div><!-- /.col-lg-9 col-md-8 -->
                                                                                                </div><!-- /.row -->
                                                                                                </div><!-- /.container -->
                                                                                                </main><!-- /#single-product -->


                                                                                                <section class="flat-imagebox style4">

                                                                                                    <div class="container">
                                                                                                        <%
                                                                                                            // xu ly de hien ra cac san pham da xem
                                                                                                            List<Product> listViewedProduct = (List<Product>) session.getAttribute("viewedProduct");
                                                                                                            String productId_raw = request.getParameter("productId");
                                                                                                            try {
                                                                                                                int productId = Integer.parseInt(productId_raw);
                                                                                                                List<Product> listViewedProductReverse = new ArrayList<>();

                                                                                                                if (listViewedProduct.size() != 0) {
                                                                                                                    for (int i = listViewedProduct.size() - 1; i >= 0; i--) {
                                                                                                                        if (listViewedProduct.get(i).getProductId() != productId) {
                                                                                                                            listViewedProductReverse.add(listViewedProduct.get(i));
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                                session.setAttribute("viewedProductReverse", listViewedProductReverse);
                                                                                                            } catch (Exception e) {
                                                                                                                System.out.println("loi chuyen doi so product id trong trang productdetail:" + e);
                                                                                                            }
                                                                                                        %>

                                                                                                        <div class="row">
                                                                                                            <div class="col-md-12">
                                                                                                                <div class="flat-row-title">
                                                                                                                    <h3>Viewed product</h3>
                                                                                                                </div>
                                                                                                            </div><!-- /.col-md-12 -->
                                                                                                        </div><!-- /.row -->

                                                                                                        <c:if test="${sessionScope.viewedProductReverse.size() != 0}">
                                                                                                            <div class="row">
                                                                                                                <div class="col-md-12">

                                                                                                                    <div class="owl-carousel-3">

                                                                                                                        <c:forEach items="${sessionScope.viewedProductReverse}" var="product">
                                                                                                                            <div class="imagebox style4 product">
                                                                                                                                <div class="box-image">
                                                                                                                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                                                                                                                        <img style="max-width: 120px; height: auto" src="${pageContext.request.contextPath}/${product.gallery.get(0)}" alt="">
                                                                                                                                    </a>
                                                                                                                                    <img class="compare" onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')"  src="images/icons/compare.png" alt="">
                                                                                                                                        <img onclick="selectProductWishList(${product.productId})" class="wishlist" src="images/icons/wishlist.png" alt="">


                                                                                                                                            </div><!-- /.box-image -->
                                                                                                                                            <div class="box-content">
                                                                                                                                                <div style="margin-top: 10px" class="cat-name">
                                                                                                                                                    <a href="" title="">${product.category.categoryName}</a>
                                                                                                                                                </div>
                                                                                                                                                <div style="min-height: 30px;" class="product-name">
                                                                                                                                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">${product.productName}</a>
                                                                                                                                                </div>
                                                                                                                                                <div class="queue" style="margin-bottom: 10px;">
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

                                                                                                                                                <c:if test="${lengthBrief > 40}">
                                                                                                                                                    <span>${product.briefInfo.substring(0, 40)}...</span>
                                                                                                                                                </c:if>

                                                                                                                                                <c:if test="${lengthBrief <= 40}">
                                                                                                                                                    <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                                                                                                                                </c:if>
                                                                                                                                                <br/>
                                                                                                                                            </div><!-- /.box-content -->
                                                                                                                                            </div><!-- /.imagebox style4 -->
                                                                                                                                        </c:forEach>

                                                                                                                                        </div><!-- /.owl-carousel-3 -->
                                                                                                                                        </div><!-- /.col-md-12 -->
                                                                                                                                        </div><!-- /.row -->

                                                                                                                                    </c:if>


                                                                                                                                    <c:if test="${sessionScope.viewedProductReverse.size() == 0}">

                                                                                                                                        <h3 style="text-align: center">There are no recently viewed products</h3>
                                                                                                                                    </c:if>


                                                                                                                                    </div><!-- /.container -->
                                                                                                                                    </section><!-- /.flat-imagebox style4 -->

                                                                                                                                    <section class="flat-imagebox style4">

                                                                                                                                        <div class="container">

                                                                                                                                            <div class="row">
                                                                                                                                                <div class="col-md-12">
                                                                                                                                                    <div class="flat-row-title">
                                                                                                                                                        <h3>Recent Products</h3>
                                                                                                                                                    </div>
                                                                                                                                                </div><!-- /.col-md-12 -->
                                                                                                                                            </div><!-- /.row -->

                                                                                                                                            <div class="row">
                                                                                                                                                <div class="col-md-12">

                                                                                                                                                    <div class="owl-carousel-3">
                                                                                                                                                        <c:forEach items="${sessionScope.top9RecentProduct}" var="product">
                                                                                                                                                            <div class="imagebox style4 product">
                                                                                                                                                                <div class="box-image ">
                                                                                                                                                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                                                                                                                                                        <img style="max-width: 120px; height: auto" src="${pageContext.request.contextPath}/${product.gallery.get(0)}" alt="">
                                                                                                                                                                    </a>
                                                                                                                                                                    <img class="compare" onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')"  src="images/icons/compare.png" alt="">
                                                                                                                                                                        <img onclick="selectProductWishList(${product.productId})" class="wishlist" src="images/icons/wishlist.png" alt="">


                                                                                                                                                                            </div><!-- /.box-image -->
                                                                                                                                                                            <div class="box-content">
                                                                                                                                                                                <div style="margin-top: 10px" class="cat-name">
                                                                                                                                                                                    <a href="" title="">${product.category.categoryName}</a>
                                                                                                                                                                                </div>
                                                                                                                                                                                <div style="min-height: 30px;" class="product-name">
                                                                                                                                                                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">${product.productName}</a>
                                                                                                                                                                                </div>
                                                                                                                                                                                <div class="queue" style="margin-bottom: 10px;">
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

                                                                                                                                                                                <c:if test="${lengthBrief > 40}">
                                                                                                                                                                                    <span>${product.briefInfo.substring(0, 40)}...</span>
                                                                                                                                                                                </c:if>

                                                                                                                                                                                <c:if test="${lengthBrief <= 40}">
                                                                                                                                                                                    <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                                                                                                                                                                </c:if>
                                                                                                                                                                                <br/>


                                                                                                                                                                            </div><!-- /.box-content -->
                                                                                                                                                                            </div><!-- /.imagebox style4 -->
                                                                                                                                                                        </c:forEach>


                                                                                                                                                                        </div><!-- /.owl-carousel-3 -->
                                                                                                                                                                        </div><!-- /.col-md-12 -->
                                                                                                                                                                        </div><!-- /.row -->
                                                                                                                                                                        </div><!-- /.container -->
                                                                                                                                                                        </section><!-- /.flat-imagebox style4 -->

                                                                                                                                                                        <div id="loading-popup" class="loading-popup">
                                                                                                                                                                            <div class="loading-content">
                                                                                                                                                                                <div class="spinner"></div>
                                                                                                                                                                            </div>
                                                                                                                                                                        </div>

                                                                                                                                                                        <div class="toast-container" id="toast-container"></div>
                                                                                                                                                                        <jsp:include page="../../common/user/popupaddproductforcompare.jsp"></jsp:include>

                                                                                                                                                                        <jsp:include page="../../common/user/popopcompare.jsp"></jsp:include>

                                                                                                                                                                        <jsp:include page="../../common/user/footer.jsp"></jsp:include>

                                                                                                                                                                            <div id="pop-compare-detail" class="pop-compare-detail">

                                                                                                                                                                            </div>

                                                                                                                                                                            </div><!-- /.boxed -->

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
                                                                                                                                                                        <script src="https://kit.fontawesome.com/cbc13c02eb.js" crossorigin="anonymous"></script>
                                                                                                                                                                        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/main.js"></script>
                                                                                                                                                                        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>
                                                                                                                                                                        <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>

                                                                                                                                                                        </body>	

                                                                                                                                                                        </html>

<%-- 
    Document   : productlist
    Created on : Jun 6, 2024, 7:53:42 AM
    Author     : PC
--%>

<%@page import="model.Memory"%>
<%@page import="model.Color"%>
<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <!-- Mirrored from creativelayers.net/themes/techno-html/shop.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:08:11 GMT -->
    <head>
        <!-- Basic Page Needs -->
        <meta charset="UTF-8">
            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>Techno Store - Shop Bar Left</title>

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

                                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-list.css">

                                        <link
                                            href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.3/nouislider.min.css"
                                            rel="stylesheet"
                                            />

                                        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>
                                            <style>

                                                .voHieu a {
                                                    pointer-events: none;
                                                    color: gray !important;
                                                    cursor: default;
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
                                                        <section class="flat-breadcrumb">
                                                            <div class="container">
                                                                <div class="row">
                                                                    <div class="col-md-12">
                                                                        <ul class="breadcrumbs">
                                                                            <li class="trail-item">
                                                                                <a href="#" title="">Home</a>
                                                                                <span><img src="images/icons/arrow-right.png" alt=""></span>
                                                                            </li>
                                                                            <li class="trail-item">
                                                                                <a href="productlist" title="">Shop</a>
                                                                                <span><img src="images/icons/arrow-right.png" alt=""></span>
                                                                            </li>
                                                                            <li class="trail-end">
                                                                                <a href="javascript:void(0)" title="">Detail</a>
                                                                            </li>
                                                                        </ul><!-- /.breacrumbs -->
                                                                    </div><!-- /.col-md-12 -->
                                                                </div><!-- /.row -->
                                                            </div><!-- /.container -->
                                                        </section><!-- /.flat-breadcrumb -->

                                                        <main id="shop">
                                                            <div class="container">
                                                                <div class="row">
                                                                    <div class="col-lg-3 col-md-4">
                                                                        <div class="sidebar ">

                                                                            <form id="form-filter" action="productlist" method="get">

                                                                                <!--sider contact-->
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

                                                                            <!--sider search box-->
                                                                            <div style="margin-bottom: 26px;" class="widget widget-categories">
                                                                                <div class="widget-title">
                                                                                    <h3>Search box</h3>
                                                                                    <input type="text" name="searchBox" placeholder="Input your product search box!" value="${param.searchBox}"/>
                                                                                    <input style="display: none" type="submit"/>
                                                                                </div>

                                                                            </div><!-- /.widget-categories -->



                                                                            <!--sider category-->
                                                                            <div class="widget widget-brands">

                                                                                <c:set value="${requestScope.selectedCategory}" var="selectedCategory"/>
                                                                                <div class="widget-title">
                                                                                    <h3>Categories<span></span></h3>
                                                                                </div>
                                                                                <div class="widget-content">

                                                                                    <%
                                                                                        Set<Integer> selectedCategory = (Set<Integer>) request.getAttribute("selectedCategory");

                                                                                        List<Category> category = (List<Category>) session.getAttribute("listCategory");
                                                                                    %>

                                                                                    <ul class="box-checkbox scroll" id="checkbox-category">


                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-category', 'category0')" type="checkbox" id="category0" <%= selectedCategory.contains(0) ? "checked" : ""%>  value="${0}" name="category">
                                                                                                <label for="category0">ALL</label>
                                                                                        </li>


                                                                                        <%
                                                                                            for (int i = 0; i < category.size(); i++) {
                                                                                        %>

                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-category', 'category0')" type="checkbox" id="category<%= category.get(i).getCategoryId()%>" <%= selectedCategory.contains(category.get(i).getCategoryId()) ? "checked" : ""%> value="<%= category.get(i).getCategoryId()%>" name="category">
                                                                                                <label for="category<%= category.get(i).getCategoryId()%>"><%= category.get(i).getCategoryName()%></label>
                                                                                        </li>

                                                                                        <%
                                                                                            }

                                                                                        %>

                                                                                    </ul>
                                                                                </div>
                                                                            </div><!-- /.widget widget-brands -->

                                                                            <!--sider price-->
                                                                            <div class="widget widget-price">
                                                                                <div class="widget-title">
                                                                                    <h3>Price<span></span></h3>
                                                                                </div>
                                                                                <div class="widget-content">

                                                                                    <div class="row justify-content-center">
                                                                                        <div class="col-md-8">
                                                                                            <div id="slider"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row justify-content-center mt-3">
                                                                                        <div class="col-md-8 text-center">
                                                                                            <p>
                                                                                                Selected Price Range: <span id="minPrice">200</span> - <span
                                                                                                    id="maxPrice"
                                                                                                    >800</span
                                                                                                >
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                    <input type="hidden" id="minValue" name="minValue" value="200" />
                                                                                    <input type="hidden" id="maxValue" name="maxValue" value="800" />
                                                                                </div>
                                                                            </div><!-- /.widget widget-price -->

                                                                            <!--sider operating system-->
                                                                            <div class="widget">
                                                                                <div class="widget-title">
                                                                                    <h3>Operating system<span></span></h3>
                                                                                    <div style="height: 2px"></div>
                                                                                </div>

                                                                                <div class="widget-content">
                                                                                    <ul class="box-checkbox scroll" id="checkbox-operating">

                                                                                        <%                                                                                        Set<String> operatingSystem = (Set<String>) request.getAttribute("selectedOperatingSystem");

                                                                                        %>

                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-operating', 'operating0')" type="checkbox" id="operating0" name="operating" <%= operatingSystem.contains("all") ? "checked" : ""%> value="all">
                                                                                                <label for="operating0">ALL</label>
                                                                                        </li>

                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-operating', 'operating0')" type="checkbox" id="operating1" name="operating" <%= operatingSystem.contains("ios") ? "checked" : ""%> value="ios">
                                                                                                <label for="operating1">IOS(Phone)</label>
                                                                                        </li>


                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-operating', 'operating0')" type="checkbox" id="operating2" name="operating" <%= operatingSystem.contains("android") ? "checked" : ""%> value="android">
                                                                                                <label for="operating2">Android</label>
                                                                                        </li>
                                                                                    </ul>
                                                                                </div>
                                                                            </div><!-- /.widget widget-color -->


                                                                            <div class="widget widget-color">
                                                                                <div class="widget-title">
                                                                                    <h3>Color<span></span></h3>
                                                                                    <div style="height: 2px"></div>
                                                                                </div>
                                                                                <div class="widget-content">

                                                                                    <%
                                                                                        Set<Integer> selectedColors = (Set<Integer>) request.getAttribute("selectedColors");

                                                                                        List<Color> color = (List<Color>) session.getAttribute("listColor");
                                                                                    %>

                                                                                    <ul class="box-checkbox scroll" id="checkbox-color">

                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-color', 'colorAll')" type="checkbox" id="colorAll" name="color" <%= selectedColors.contains(0) ? "checked" : ""%> value="0">
                                                                                                <label for="colorAll">ALL</label>
                                                                                        </li>

                                                                                        <%
                                                                                            for (int i = 0; i < color.size(); i++) {
                                                                                        %>

                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-color', 'colorAll')" type="checkbox" id="color<%= color.get(i).getColorId()%>" name="color" <%= selectedColors.contains(color.get(i).getColorId()) ? "checked" : ""%> value="<%= color.get(i).getColorId()%>">
                                                                                                <label for="color<%= color.get(i).getColorId()%>"><%= color.get(i).getColorName()%></label>
                                                                                        </li>

                                                                                        <%
                                                                                            }
                                                                                        %>

                                                                                    </ul>
                                                                                </div>
                                                                            </div><!-- /.widget widget-color -->

                                                                            <!--sider memory-->
                                                                            <div class="widget widget-color">
                                                                                <div class="widget-title">
                                                                                    <h3>Memory<span></span></h3>
                                                                                    <div style="height: 2px"></div>
                                                                                </div>
                                                                                <div class="widget-content">

                                                                                    <%                                                                                    Set<Integer> selectedMemorys = (Set<Integer>) request.getAttribute("selectedMemorys");

                                                                                        List<Memory> memory = (List<Memory>) session.getAttribute("listMemory");
                                                                                    %>
                                                                                    <ul class="box-checkbox scroll" id="checkbox-memory">

                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-memory', 'memoryAll')" type="checkbox" id="memoryAll" name="memory" <%= selectedMemorys.contains(0) ? "checked" : ""%> value="0">
                                                                                                <label for="memoryAll">ALL</label>
                                                                                        </li>


                                                                                        <%
                                                                                            for (int i = 0; i < memory.size(); i++) {
                                                                                        %>

                                                                                        <li class="check-box">
                                                                                            <input onclick="selectOption(this, 'checkbox-memory', 'memoryAll')" type="checkbox" id="memory<%= memory.get(i).getMemoryId()%>" name="memory" <%= selectedMemorys.contains(memory.get(i).getMemoryId()) ? "checked" : ""%> value="<%= memory.get(i).getMemoryId()%>">
                                                                                                <label for="memory<%= memory.get(i).getMemoryId()%>"><%= memory.get(i).getMemorySize()%> GB</label>
                                                                                        </li>

                                                                                        <%
                                                                                            }

                                                                                        %>
                                                                                    </ul>
                                                                                </div>
                                                                            </div><!-- /.widget widget-color -->

                                                                            <button id="buttion-show-more" type="button" onclick="showMoreSiderOption(this)" style="background-color: orange" class="mb-5">Show more</button>

                                                                            <div style="display: none" id="sider-option">

                                                                                <!--sider battery-->
<!--
                                                                                <div class="widget">
                                                                                    <div class="widget-title">
                                                                                        <h3>Battery capacity<span></span></h3>
                                                                                        <div style="height: 2px"></div>
                                                                                    </div>
                                                                                    <div class="widget-content">
                                                                                        <ul class="box-checkbox scroll" id="checkbox-battery">

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-battery', 'battery0')" type="checkbox" id="battery0" name="battery" value="all">
                                                                                                    <label for="battery0">ALL</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-battery', 'battery0')" type="checkbox" id="battery1" name="battery" value="1">
                                                                                                    <label for="battery1">Under 3000 mah</label>
                                                                                            </li>


                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-battery', 'battery0')" type="checkbox" id="battery2" name="battery" value="2">
                                                                                                    <label for="battery2">from 3000 to 4000mah</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-battery', 'battery0')" type="checkbox" id="battery4" name="battery" value="4">
                                                                                                    <label for="battery4">from 4000 to 5000mah</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-battery', 'battery0')" type="checkbox" id="battery5" name="check1" value="5">
                                                                                                    <label for="battery5">above 5000mah</label>
                                                                                            </li>
                                                                                        </ul>
                                                                                    </div>
                                                                                </div> /.widget widget-color -->

                                                                                <!--sider screen-->

                                                                                <div class="widget">
                                                                                    <div class="widget-title">
                                                                                        <h3>Screen<span></span></h3>
                                                                                        <div style="height: 2px"></div>
                                                                                    </div>
                                                                                    <div class="widget-content">

                                                                                        <%
                                                                                            Set<String> selectedScreens = (Set<String>) request.getAttribute("selectedScreens");

                                                                                        %>
                                                                                        <ul class="box-checkbox scroll" id="checkbox-screen">

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-screen', 'screen0')" type="checkbox" id="screen0" name="screen" <%= selectedScreens.contains("all") ? "checked" : ""%> value="all">
                                                                                                    <label for="screen0">ALL</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-screen', 'screen0')" type="checkbox" id="screen1" name="screen" <%= selectedScreens.contains("OLED") ? "checked" : ""%> value="OLED">
                                                                                                    <label for="screen1">OLED</label>
                                                                                            </li>


                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-screen', 'screen0')" type="checkbox" id="screen2" name="screen" <%= selectedScreens.contains("AMOLED") ? "checked" : ""%> value="AMOLED">
                                                                                                    <label for="screen2">AMOLED</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-screen', 'screen0')" type="checkbox" id="screen3" name="screen" <%= selectedScreens.contains("HD+") ? "checked" : ""%> value="HD+">
                                                                                                    <label for="screen3">HD+</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-screen', 'screen0')" type="checkbox" id="screen4" name="screen" <%= selectedScreens.contains("FHD+") ? "checked" : ""%> value="FHD+">
                                                                                                    <label for="screen4">FHD+</label>
                                                                                            </li>

                                                                                        </ul>
                                                                                    </div>
                                                                                </div><!-- /.widget widget-color -->

                                                                                <!--sider ram-->
                                                                                <div class="widget">
                                                                                    <div class="widget-title">
                                                                                        <h3>RAM<span></span></h3>
                                                                                        <div style="height: 2px"></div>
                                                                                    </div>
                                                                                    <div class="widget-content">

                                                                                        <%
                                                                                            Set<Integer> selectedRams = (Set<Integer>) request.getAttribute("selectedRams");

                                                                                        %>
                                                                                        <ul class="box-checkbox scroll" id="checkbox-ram">

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-ram', 'ram00')" type="checkbox" id="ram00" name="ram" <%= selectedRams.contains(0) ? "checked" : ""%> value="0">
                                                                                                    <label for="ram00">All</label>
                                                                                            </li>                                                                            


                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-ram', 'ram00')" type="checkbox" id="ram0" name="ram" <%= selectedRams.contains(2) ? "checked" : ""%> value="2">
                                                                                                    <label for="ram0">2 GB</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-ram', 'ram00')" type="checkbox" id="ram1" name="ram" <%= selectedRams.contains(3) ? "checked" : ""%> value="3">
                                                                                                    <label for="ram1">3 GB</label>
                                                                                            </li>


                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-ram', 'ram00')" type="checkbox" id="ram2" name="ram" <%= selectedRams.contains(4) ? "checked" : ""%> value="4">
                                                                                                    <label for="ram2">4 GB</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-ram', 'ram00')" type="checkbox" id="ram3" name="ram" <%= selectedRams.contains(6) ? "checked" : ""%> value="6">
                                                                                                    <label for="ram3">6 GB</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-ram', 'ram00')" type="checkbox" id="ram4" name="ram" <%= selectedRams.contains(8) ? "checked" : ""%> value="8">
                                                                                                    <label for="ram4">8 GB</label>
                                                                                            </li>

                                                                                            <li class="check-box">
                                                                                                <input onclick="selectOption(this, 'checkbox-ram', 'ram00')" type="checkbox" id="ram5" name="ram" <%= selectedRams.contains(12) ? "checked" : ""%> value="12">
                                                                                                    <label for="ram5">12 GB</label>
                                                                                            </li>
                                                                                        </ul>
                                                                                    </div>
                                                                                </div><!-- /.widget widget-color -->
                                                                                
                                                                                <button type="button" onclick="hiddenSiderOption(this)" style="background-color: orange" class="mb-5">Hidden</button>
                                                                            </div>


                                                                            <!--sider color-->



                                                                            <input type="hidden" id="showNumberProduct" name="showNumberProduct" value="${param.showNumberProduct != null ? param.showNumberProduct : '9'}"/>  
                                                                            <input type="hidden" id="sortByProduct" name="sortByProduct" value="${param.sortByProduct != null ? param.sortByProduct : '0'}"/>
                                                                            <input type="hidden" id="numberPage" name="numberPage" value="${param.numberPage != null ? param.numberPage : '1'}"/>

                                                                        </form>


                                                                        <!--sider last product-->

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
                                                                                            </div>

                                                                                            <div class="queue">
                                                                                                <c:set value="${product.avgRating}" var="star"/>

                                                                                                <c:set value="${product.avgRating % 1 > 0 ? product.avgRating -  product.avgRating % 1 : product.avgRating}" var="starFull"/>
                                                                                                <c:set value="${product.avgRating % 1 > 0 ? 1 : 0}" var="starHaft"/>
                                                                                                <c:set value="${5 - starFull - starHaft}" var="starEmpty"/>

                                                                                                <c:forEach begin="1" end="${starFull}">
                                                                                                    <!--hien thi ngoi sao day du-->
                                                                                                    <i style="font-size: 10px;" class="fa fa-star" aria-hidden="true"></i> 
                                                                                                </c:forEach>

                                                                                                <c:forEach begin="1" end="${starHaft}">
                                                                                                    <!--hien thi ngoi sao 1/2 rong-->
                                                                                                    <i style="font-size: 10px;" class="fa fa-star-half-empty" aria-hidden="true"></i>
                                                                                                </c:forEach>

                                                                                                <c:forEach begin="1" end="${starEmpty}">
                                                                                                    <!--hien thi sao rong-->
                                                                                                    <i style="font-size: 10px;" class="fa fa-star-o" aria-hidden="true"></i>
                                                                                                </c:forEach>


                                                                                            </div>
                                                                                            <div class="price">
                                                                                                <span class="sale"><fmt:formatNumber value="${product.minPrice}" type="currency" /></span>
                                                                                                <span class="regular"><fmt:formatNumber value="${product.minPriceOrigin}" type="currency" /></span>
                                                                                            </div>
                                                                                            <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                                                                            <c:if test="${lengthBrief > 10}">
                                                                                                <span>${product.briefInfo.substring(0, 10)}...</span>
                                                                                            </c:if>

                                                                                            <c:if test="${lengthBrief < 10}">
                                                                                                <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                                                                            </c:if>
                                                                                        </div>
                                                                                    </li>	 
                                                                                </c:forEach>

                                                                            </ul>
                                                                        </div><!-- /.widget widget-products -->



                                                                    </div><!-- /.sidebar -->
                                                                </div><!-- /.col-lg-3 col-md-4 -->


                                                                <div class="col-lg-9 col-md-8">
                                                                    <div class="main-shop">

                                                                        <div class="slider owl-carousel-16">
<!--                                                                            <div onclick="redirected('${item.backLink}')" class="slide">
                                                                                <div class="title">${item.title}</div>
                                                                                <img src="${item.image}" alt="Image 1">
                                                                            </div>-->

                                                                            <c:forEach items="${sessionScope.sliderAll}" var="item">

                                                                                <div onclick="redirected('${item.backLink}')" class="slider-item style9">
                                                                                    <div class="item-text">
                                                                                        <div class="header-item">
                                                                                            <p>${item.title}</p>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="item-image">
                                                                                        <img style="max-width: 200px" src="${pageContext.request.contextPath}/${item.image}" alt="">
                                                                                    </div>
                                                                                    <div class="clearfix"></div>
                                                                                </div><!-- /.slider-item style9 -->

                                                                            </c:forEach>


                                                                            <!--
                                                                            
                                                                                                                                                        <div class="slider-item style9">
                                                                                                                                                            <div class="item-text">
                                                                                                                                                                <div class="header-item">
                                                                                                                                                                    <p>You can build the banner for other category</p>
                                                                                                                                                                    <h2 class="name">Shop Banner</h2>
                                                                                                                                                                </div>
                                                                                                                                                            </div>
                                                                                                                                                            <div class="item-image">
                                                                                                                                                                <img src="images/banner_boxes/07.png" alt="">
                                                                                                                                                            </div>
                                                                                                                                                            <div class="clearfix"></div>
                                                                                                                                                        </div> /.slider-item style9 -->

                                                                        </div><!-- /.slider -->


                                                                        <div class="wrap-imagebox">

                                                                            <!--show ra cac thong tin nhu so luong san pham hien thi....-->
                                                                            <div class="flat-row-title">
                                                                                <h3>Mobile</h3>
                                                                                <span>
                                                                                    Showing ${requestScope.numberPage * requestScope.showNumberProduct - requestScope.showNumberProduct + 1} - ${requestScope.numberPage * requestScope.showNumberProduct <= sessionScope.totalProduct ? requestScope.numberPage * requestScope.showNumberProduct : sessionScope.totalProduct} of ${sessionScope.totalProduct} results
                                                                                </span>
                                                                                <div class="clearfix"></div>
                                                                            </div>

                                                                            <!--show ra cac thong tin sort, option chon so san pham hien thi tren 1 trang....-->

                                                                            <div class="sort-product">
                                                                                <ul class="icons">
                                                                                    <li>
                                                                                        <img src="images/icons/list-1.png" alt="">
                                                                                    </li>
                                                                                    <li>
                                                                                        <img src="images/icons/list-2.png" alt="">
                                                                                    </li>
                                                                                </ul>
                                                                                <div class="sort">
                                                                                    <div class="popularity">
                                                                                        <select name="popularity" onchange="selectOptionSortProduct(this)">
                                                                                            <option ${requestScope.sortByProduct == 0 ? 'selected' : ''} value="0">Sort by updated date</option>
                                                                                            <option ${requestScope.sortByProduct == 1 ? 'selected' : ''} value="1">Sort by price high</option>
                                                                                            <option ${requestScope.sortByProduct == 2 ? 'selected' : ''} value="2">Sort by price low</option>
                                                                                            <option ${requestScope.sortByProduct == 3 ? 'selected' : ''} value="3">Sort by popularity</option>
                                                                                        </select>
                                                                                    </div>
                                                                                    <div class="showed">
                                                                                        <select name="showed" onchange="selectOptionShowProduct(this)">
                                                                                            <option  value="6" ${requestScope.showNumberProduct == 6 ? 'selected' : ''}>Show 6</option>
                                                                                            <option  value="9" ${requestScope.showNumberProduct == 9 ? 'selected' : ''}>Show 9</option>
                                                                                            <option value="12" ${requestScope.showNumberProduct == 12 ? 'selected' : ''}>Show 12</option>
                                                                                            <option  value="15" ${requestScope.showNumberProduct == 15 ? 'selected' : ''}>Show 15</option>
                                                                                        </select>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="clearfix"></div>
                                                                            </div>


                                                                            <!--hien thi giao dien product cu the theo gird--> 
                                                                            <div class="tab-product">
                                                                                <div class="row sort-box">

                                                                                    <c:forEach items="${sessionScope.listProductFilter}" var="product">
                                                                                        <div class="col-lg-4 col-sm-6">
                                                                                            <div style="height: 400px" class="product-box">
                                                                                                <div class="imagebox">
                                                                                                    <div class="box-image">
                                                                                                        <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                                                                                            <img src="${product.gallery.get(0)}" alt="">
                                                                                                        </a>

                                                                                                    </div><!-- /.box-image -->
                                                                                                    <div class="box-content">
                                                                                                        <div class="cat-name">
                                                                                                            <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">${product.category.categoryName}</a>
                                                                                                        </div>
                                                                                                        <div class="product-name">
                                                                                                            <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">${product.productName}</a>

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

                                                                                                            <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                                                                                            <c:if test="${lengthBrief > 60}">
                                                                                                                <span>${product.briefInfo.substring(0, 60)}...</span>
                                                                                                            </c:if>

                                                                                                            <c:if test="${lengthBrief <= 60}">
                                                                                                                <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                                                                                            </c:if>
                                                                                                        </div>


                                                                                                        <div class="price">
                                                                                                            <span class="sale">
                                                                                                                <fmt:formatNumber value="${product.minPrice}" type="currency" />
                                                                                                            </span>
                                                                                                            <span class="regular">
                                                                                                                <fmt:formatNumber value="${product.minPriceOrigin}" type="currency" />
                                                                                                            </span>
                                                                                                        </div>
                                                                                                    </div><!-- /.box-content -->

                                                                                                    <div class="box-bottom">
                                                                                                        <div class="btn-add-cart">
                                                                                                            <a onclick="openPopup(${product.productId})" href="javascript:void(0)" title="">
                                                                                                                <img src="images/icons/add-cart.png" alt="">Add to Cart
                                                                                                            </a>
                                                                                                        </div>

                                                                                                        <div>
                                                                                                            <button onclick="showFeedbackPopup(${product.productId})" class="btn btn-success">

                                                                                                                <i class="fa-solid fa-comment"></i> Leave Feedback

                                                                                                            </button>
                                                                                                        </div>
                                                                                                        <div class="compare-wishlist">
                                                                                                            <a onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')" href="javascript:void(0)" class="comparee" title="">
                                                                                                                <img src="images/icons/compare.png" alt="">Compare
                                                                                                            </a>
                                                                                                            <a onclick="selectProductWishList(${product.productId})" href="javascript:void(0)" class="wishlistt" title="">
                                                                                                                <img src="images/icons/wishlist.png" alt="">Wishlist
                                                                                                            </a>
                                                                                                        </div>
                                                                                                    </div><!-- /.box-bottom -->
                                                                                                </div><!-- /.imagebox -->
                                                                                            </div>
                                                                                        </div><!-- /.col-lg-4 col-sm-6 -->

                                                                                    </c:forEach>





                                                                                </div>


                                                                                <div class="sort-box">

                                                                                    <c:forEach items="${sessionScope.listProductFilter}" var="product">

                                                                                        <div class="product-box style3">
                                                                                            <div class="imagebox style1 v3">
                                                                                                <div class="box-image">
                                                                                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                                                                                        <img src="${product.gallery.get(0)}" alt="">
                                                                                                    </a>
                                                                                                </div><!-- /.box-image -->
                                                                                                <div class="box-content">
                                                                                                    <div class="cat-name">
                                                                                                        <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">${product.category.categoryName}</a>
                                                                                                    </div>
                                                                                                    <div class="product-name">
                                                                                                        <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">${product.productName}</a>
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
                                                                                                        <span>${product.avgRating}</span>

                                                                                                    </div>


                                                                                                    <div class="info">
                                                                                                        <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                                                                                        <p>${product.briefInfo}</p>
                                                                                                    </div>
                                                                                                </div><!-- /.box-content -->
                                                                                                <div class="box-price">
                                                                                                    <div class="price">
                                                                                                        <span class="regular">
                                                                                                            <fmt:formatNumber value="${product.minPrice}" type="currency" />
                                                                                                        </span>
                                                                                                        <span class="sale">
                                                                                                            <fmt:formatNumber value="${product.minPriceOrigin}" type="currency" />
                                                                                                        </span>
                                                                                                    </div>
                                                                                                    <div class="btn-add-cart">
                                                                                                        <a href="javascript:void(0)" onclick="openPopup(${product.productId})" title="">
                                                                                                            <img src="images/icons/add-cart.png" alt="">Add to Cart
                                                                                                        </a>
                                                                                                    </div>

                                                                                                    <div>
                                                                                                        <button class="btn btn-success" onclick="showFeedbackPopup(${product.productId})">

                                                                                                            <i class="fa-solid fa-comment"></i> Leave Feedback

                                                                                                        </button>
                                                                                                    </div>

                                                                                                    <div class="compare-wishlist my-2">
                                                                                                        <a onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')" href="javascript:void(0)" class="comparee" title="">
                                                                                                            <img src="images/icons/compare.png" alt="">Compare
                                                                                                        </a>
                                                                                                        <a onclick="selectProductWishList(${product.productId})" href="javascript:void(0)" class="wishlistt" title="">
                                                                                                            <img src="images/icons/wishlist.png" alt="">Wishlist
                                                                                                        </a>
                                                                                                    </div>
                                                                                                </div><!-- /.box-price -->
                                                                                            </div><!-- /.imagebox -->
                                                                                        </div><!-- /.product-box -->

                                                                                    </c:forEach>

                                                                                    <div style="height: 9px;"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div><!-- /.wrap-imagebox -->

                                                                        <!--phan trang danh sach san pham-->


                                                                        <div class="blog-pagination">
                                                                            <span>
                                                                                Showing ${requestScope.numberPage * requestScope.showNumberProduct - requestScope.showNumberProduct + 1} - ${requestScope.numberPage * requestScope.showNumberProduct <= sessionScope.totalProduct ? requestScope.numberPage * requestScope.showNumberProduct : sessionScope.totalProduct} of ${sessionScope.totalProduct} results
                                                                            </span>


                                                                            <ul class="flat-pagination style1 pagination">
                                                                                <li class="first ${requestScope.numberPage == 1 ? 'voHieu': ''}">
                                                                                    <a onclick="selectOptionPage(1)" href="javascript:void(0)" title="">
                                                                                        First
                                                                                    </a>
                                                                                </li>
                                                                                <li class="${requestScope.numberPage == 1 ? 'voHieu': ''}">
                                                                                    <a onclick="selectOptionPage(${requestScope.numberPage - 1})" href="javascript:void(0)" title="">
                                                                                        Prev Page
                                                                                    </a>
                                                                                </li>

                                                                                <%                                                                                int start = (int) request.getAttribute("start");
                                                                                    int end = (int) request.getAttribute("end");

                                                                                    for (int i = start; i <= end; i++) {

                                                                                %>

                                                                                <li class="<%= (int)request.getAttribute("numberPage") == i ? "active" : "" %>">
                                                                                    <a onclick="selectOptionPage(<%= i %>)" href="javascript:void(0)" title="">
                                                                                        <%= i %>
                                                                                    </a>
                                                                                </li>

                                                                                <%                                                                                    }
                                                                                %>

                                                                                <li class="${requestScope.numberPage == requestScope.totalPage ? 'voHieu': ''}" >
                                                                                    <a onclick="selectOptionPage(${requestScope.numberPage + 1})" href="javascript:void(0)" title="">
                                                                                        Next Page
                                                                                    </a>
                                                                                </li>

                                                                                <li class="${requestScope.numberPage == requestScope.totalPage ? 'voHieu': ''}">
                                                                                    <a onclick="selectOptionPage(${requestScope.totalPage})" href="javascript:void(0)" title="">
                                                                                        End
                                                                                    </a>
                                                                                </li>
                                                                            </ul>
                                                                            <div class="clearfix"></div>
                                                                        </div><!-- /.blog-pagination -->
                                                                    </div><!-- /.main-shop -->
                                                                </div><!-- /.col-lg-9 col-md-8 -->
                                                            </div><!-- /.row -->
                                                        </div><!-- /.container -->
                                                    </main><!-- /#shop -->


                                                    <div id="loading-popup" class="loading-popup">
                                                        <div class="loading-content">
                                                            <div class="spinner"></div>
                                                        </div>
                                                    </div>

                                                    <jsp:include page="../../common/user/popupaddtocart.jsp"></jsp:include>

                                                    <jsp:include page="../../common/user/productcontribution.jsp"></jsp:include>


                                                    <jsp:include page="../../common/user/popupaddproductforcompare.jsp"></jsp:include>

                                                    <jsp:include page="../../common/user/popopcompare.jsp"></jsp:include>
                                                        <div class="toast-container" id="toast-container"></div>

                                                        <div id="pop-compare-detail" class="pop-compare-detail">

                                                        </div>

                                                    <jsp:include page="../../common/user/footer.jsp"></jsp:include>

                                                    </div><!-- /.boxed -->

                                                    <!-- Javascript -->

                                                    <script>

                                                        document.addEventListener("DOMContentLoaded", function () {
                                                            var slider = document.getElementById('slider');
                                                            var minPrice = document.getElementById('minPrice');
                                                            var maxPrice = document.getElementById('maxPrice');
                                                            var minValue = document.getElementById('minValue');
                                                            var maxValue = document.getElementById('maxValue');

                                                            noUiSlider.create(slider, {
                                                                start: [${param.minValue != null ? param.minValue : 1000000}, ${param.maxValue != null ? param.maxValue : 100000000}],
                                                                connect: true,
                                                                range: {
                                                                    'min': ${sessionScope.initMinValue},
                                                                    'max': ${sessionScope.initMaxValue}
                                                                },
                                                                step: 10,
                                                                format: {
                                                                    to: function (value) {
                                                                        return Math.round(value);
                                                                    },
                                                                    from: function (value) {
                                                                        return Math.round(value);
                                                                    }
                                                                }
                                                            });

                                                            slider.noUiSlider.on('update', function (values, handle) {
                                                                if (handle === 0) {
                                                                    minPrice.textContent = Number(values[0]).toLocaleString('vi-VN', {
                                                                        style: 'currency',
                                                                        currency: 'VND'
                                                                    });
                                                                    minValue.value = Number(values[0]);
                                                                } else {
                                                                    maxPrice.textContent = Number(values[1]).toLocaleString('vi-VN', {
                                                                        style: 'currency',
                                                                        currency: 'VND'
                                                                    });
                                                                    maxValue.value = Number(values[1]);
                                                                }
                                                            });
                                                        });

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
                                                <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.3/nouislider.min.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/main.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-list.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/slider.js"></script>
                                            </body>	

                                            <!-- Mirrored from creativelayers.net/themes/techno-html/shop.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:08:16 GMT -->
                                            </html>
<%-- 
    Document   : categoryproduct
    Created on : May 14, 2024, 11:37:05 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<section class="flat-imagebox style2 background">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-wrap">
                    <div class="product-tab style1">

                        <ul class="tab-list">
                            <!--<li class="active">Smartphones</li>-->

                            <c:forEach items="${sessionScope.top9NewProductByCategory}" var="category">
                                <li>${category.key.categoryName}</li>
                                </c:forEach>
                        </ul><!-- /.tab-list -->

                    </div><!-- /.product-tab style1 -->

                    <div class="tab-item">

                        <c:forEach items="${sessionScope.top9NewProductByCategory}" var="category">
                            <div class="row">

                                <c:forEach items="${category.value}" var="product">

                                    <div class="col-md-4 col-sm-6">
                                        <div class="product-box style2">

                                            <div class="imagebox style2">
                                                <div class="box-image">
                                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                                        <img style="width: 50%; height: auto" src="${pageContext.request.contextPath}/${product.gallery.get(0)}" alt="">
                                                    </a>
                                                </div><!-- /.box-image -->
                                                <div class="box-content">
                                                    <div style="min-height: 0px" class="product-name">
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

                                                    </div>
                                                    <div class="price">
                                                        <span class="sale"><fmt:formatNumber value="${product.minPrice}" type="currency" /></span>
                                                        <span class="regular"><fmt:formatNumber value="${product.minPriceOrigin}" type="currency" /></span>
                                                    </div>
                                                    <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                                    <c:if test="${lengthBrief > 60}">
                                                        <span>${product.briefInfo.substring(0, 60)}...</span>
                                                    </c:if>

                                                    <c:if test="${lengthBrief <= 60}">
                                                        <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                                    </c:if>
                                                </div><!-- /.box-content -->

                                                <div class="box-bottom">
                                                    <div class="btn-add-cart">
                                                        <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                                            <img src="images/icons/add-cart.png" alt="">Add to Cart
                                                        </a>
                                                    </div>
                                                    <div class="compare-wishlist">
                                                        <a onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')" href="javascript:void(0)" title="">
                                                            <img  src="images/icons/compare.png" alt="">Compare
                                                        </a>
                                                        <a onclick="selectProductWishList(${product.productId})" href="javascript:void(0)" title="">
                                                            <img src="images/icons/wishlist.png" alt="">Wishlist
                                                        </a>
                                                    </div>
                                                </div><!-- /.box-bottom -->
                                            </div><!-- /.imagebox style2 -->
                                        </div><!-- /.product-box -->
                                    </div><!-- /.col-md-3 col-sm-6 -->
                                </c:forEach>
                            </div><!-- /.row -->

                        </c:forEach>

                    </div><!-- /.tab-item -->
                </div><!-- /.product-wrap -->
            </div><!-- /.col-md-12 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</section><!-- /.flat-imagebox style2 -->
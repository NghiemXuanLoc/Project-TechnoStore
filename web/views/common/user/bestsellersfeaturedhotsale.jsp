<%-- 
    Document   : bestsellersfeaturedhotsale
    Created on : May 14, 2024, 11:40:27 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<section class="flat-highlights">
    <div class="container">
        <div class="row">

            <div class="col-md-4">

                <div class="flat-row-title">
                    <h3>Bestsellers</h3>
                </div>

                <ul class="product-list style1">
                    <c:forEach items="${sessionScope.top3ProductBestSelling}" var="product">
                        <li>
                            <div style="height: 140px; width: 110px" class="img-product product">
                                <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                    <img src="${pageContext.request.contextPath}/${product.gallery.get(0)}" alt="">
                                </a>

                                <img style="top: 80% !important" class="compare" onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')"  src="images/icons/compare.png" alt="">
                                <img style="top: 80% !important" class="wishlist" onclick="selectProductWishList(${product.productId})" src="images/icons/wishlist.png" alt="">
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
                                    <!--<br/>-->
                                    <span class="regular"><fmt:formatNumber value="${product.minPriceOrigin}" type="currency" /></span>
                                    <br/>
                                    <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                    <c:if test="${lengthBrief > 60}">
                                        <span>${product.briefInfo.substring(0, 60)}...</span>
                                    </c:if>

                                    <c:if test="${lengthBrief <= 60}">
                                        <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </li>
                    </c:forEach>


                </ul><!-- /.product-list style1 -->
            </div><!-- /.col-md-4 -->


            <div class="col-md-4">

                <div class="flat-row-title">
                    <h3>Featured</h3>
                </div>

                <ul class="product-list style1">
                    <c:forEach items="${sessionScope.top3ProductRating}" var="product">
                        <li>
                            <div style="height: 140px; width: 110px" class="img-product product">
                                <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                    <img src="${pageContext.request.contextPath}/${product.gallery.get(0)}" alt="">
                                </a>
                                <img style="top: 80% !important" class="compare" onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')"  src="images/icons/compare.png" alt="">
                                <img style="top: 80% !important" class="wishlist" onclick="selectProductWishList(${product.productId})" src="images/icons/wishlist.png" alt="">
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
                                    <!--<br/>-->
                                    <span class="regular"><fmt:formatNumber value="${product.minPriceOrigin}" type="currency" /></span>

                                    <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                    <br/>

                                    <c:if test="${lengthBrief > 60}">
                                        <span>${product.briefInfo.substring(0, 60)}...</span>
                                    </c:if>

                                    <c:if test="${lengthBrief <= 60}">
                                        <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </li>
                    </c:forEach>
                </ul>
            </div><!-- /.col-md-4 -->


            <div class="col-md-4">

                <div class="flat-row-title">
                    <h3>Hot Sale</h3>
                </div>

                <ul class="product-list style1">
                    <c:forEach items="${sessionScope.top3ProductSale}" var="product">
                        <li>
                            <div style="height: 140px; width: 110px" class="img-product product">
                                <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                    <img src="${pageContext.request.contextPath}/${product.gallery.get(0)}" alt="">
                                </a>
                                <img style="top: 80% !important" class="compare" onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')"  src="images/icons/compare.png" alt="">
                                <img style="top: 80% !important" class="wishlist" onclick="selectProductWishList(${product.productId})" src="images/icons/wishlist.png" alt="">
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
                                    <!--<br/>-->
                                    <span class="regular"><fmt:formatNumber value="${product.minPriceOrigin}" type="currency" /></span>

                                    <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                    <br/>

                                    <c:if test="${lengthBrief > 60}">
                                        <span>${product.briefInfo.substring(0, 60)}...</span>
                                    </c:if>

                                    <c:if test="${lengthBrief <= 60}">
                                        <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </li>
                    </c:forEach>
                </ul>
            </div><!-- /.col-md-4 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</section><!-- /.flat-highlights -->
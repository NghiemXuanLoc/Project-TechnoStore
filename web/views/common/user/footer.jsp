<%-- 
    Document   : footer
    Created on : May 14, 2024, 11:41:40 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="widget-ft widget-about">
                    <div class="logo logo-ft">
                        <a href="${pageContext.request.contextPath}/home" title="">
                            <img src="${pageContext.request.contextPath}/images/logos/logo-ft.png" alt="">
                        </a>
                    </div><!-- /.logo-ft -->
                    <div class="widget-content">
                        <div class="icon">
                            <img src="images/icons/call.png" alt="">
                        </div>
                        <div class="info">
                            <p class="questions">Got Questions ? Call us 24/7!</p>
                            <p class="phone">Call Us: (+84) 3377 83926</p>
                            <p class="address">
                                Bình Yên, Thạch Thất, Hà Nội<br />Việt Nam.
                            </p>
                        </div>
                    </div><!-- /.widget-content -->

                </div><!-- /.widget-about -->
            </div><!-- /.col-lg-3 col-md-6 -->
            
            
            <div class="col-lg-3 col-md-6">
                <div class="widget-ft widget-categories-ft">
                    <div class="widget-title">
                        <h3>Find By Categories</h3>
                    </div>
                    <ul class="cat-list-ft">
                        <c:forEach items="${sessionScope.categoryAll}" var="category">
                            <li>
                                <a href="#" title="">${category.categoryName}</a>
                            </li>
                        </c:forEach>
                    </ul><!-- /.cat-list-ft -->
                </div><!-- /.widget-categories-ft -->
            </div><!-- /.col-lg-3 col-md-6 -->
            
            <div class="col-lg-3 col-md-6">
                <div class="widget-ft widget-categories-ft">
                    <div class="widget-title">
                        <h3>Find By blog Categories</h3>
                    </div>
                    <ul class="cat-list-ft">
                        <c:forEach items="${sessionScope.BlogCategoryAll}" var="blogCategory">
                            <li>
                                <a href="bloglist?bid=${blogCategory.blogCategoryId}" title="">${blogCategory.blogCategoryName}</a>
                            </li>
                        </c:forEach>
                    </ul><!-- /.cat-list-ft -->
                </div><!-- /.widget-categories-ft -->
            </div><!-- /.col-lg-3 col-md-6 -->
            
            
            <div class="col-lg-2 col-md-6">
                <div class="widget-ft widget-menu">
                    <div class="widget-title">
                        <h3>Customer Care</h3>
                    </div>
                    <ul>
                        <li>
                            <a href="#" title="">
                                Contact us
                            </a>
                        </li>
                        <li>
                            <a href="https://maps.app.goo.gl/zKfNanyeXkdgaM9m8" target="_blank" title="">
                                Site Map
                            </a>
                        </li>
                        <li>
                            <a href="#" title="">
                                My Account
                            </a>
                        </li>
                        <li>
                            <a href="#" title="">
                                Wish List
                            </a>
                        </li>
                    </ul>
                </div><!-- /.widget-menu -->
            </div><!-- /.col-lg-2 col-md-6 -->
            
            
        </div><!-- /.row -->
    </div><!-- /.container -->

</footer><!-- /footer -->
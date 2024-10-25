<%-- 
    Document   : mostviewed
    Created on : May 14, 2024, 11:38:57 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="flat-imagebox style4">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="flat-row-title">
                    <h3>Last Post</h3>
                </div>
            </div><!-- /.col-md-12 -->
        </div><!-- /.row -->
        <div class="row">          
            <c:forEach items="${sessionScope.top4LastBlogDetail}" var="post">
                <div class="col-md-3">
                    <div style="margin: 0 5px;" class="imagebox style4">
                        <div class="box-image">
                            <a href="blogdetail?id=${post.blogDetailId}" title="">
                                <img src="${pageContext.request.contextPath}/${post.thumbnail}" alt="">
                            </a>
                        </div><!-- /.box-image -->
                        <div class="box-content">
                            <div class="cat-name" style="margin-top: 15px">
                                <a href="blogdetail?id=${post.blogDetailId}" title="">${post.blogCategory.blogCategoryName}</a>
                            </div>
                            <div style="min-height: 40px" class="product-name">

                                <c:set var="lengthBrief" value="${post.title.length()}"/>

                                <c:if test="${lengthBrief > 50}">
                                    
                                    <a href="blogdetail?id=${post.blogDetailId}" title=""><span class="sale"><span>${post.title.substring(0, 50)}...</span></span></a>
                                </c:if>

                                <c:if test="${lengthBrief <= 50}">
                                   
                                    <a href="blogdetail?id=${post.blogDetailId}" title=""><span class="sale"> <span>${post.title.substring(0, lengthBrief)}</span></span></a>
                                </c:if>
                                    
                                    
                                
                            </div>
                            <div style="min-height: 50px">
                                
                                <c:set var="lengthBrief" value="${post.shortDescription.length()}"/>

                                <c:if test="${lengthBrief > 100}">
                                    
                                    <span>${post.shortDescription.substring(0, 100)}...</span>
                                </c:if>

                                <c:if test="${lengthBrief <= 100}">
                                   
                                    ${post.shortDescription.substring(0, lengthBrief)}
                                </c:if>
                                
                            </div>
                            <div>
                                <span>Date: ${post.blogDate}</span>
                            </div>

                            <div>
                                <span>Author: ${post.user.fullName}</span>
                            </div>
                        </div><!-- /.box-content -->
                    </div><!-- /.imagebox style4 -->
                </div>
            </c:forEach>
        </div><!-- /.row -->
    </div><!-- /.container -->
</section><!-- /.flat-imagebox style4 -->
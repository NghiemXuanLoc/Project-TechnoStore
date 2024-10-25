<%-- 
    Document   : slider.jsp
    Created on : May 14, 2024, 11:28:50 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="show-slider">
    <div class="slider">
        <div class="slides">


            <c:forEach items="${sessionScope.sliderAll}" var="item">

                <div onclick="redirected('${item.backLink}')" class="slide">
                    <div class="title">${item.title}</div>
                    <img src="${item.image}" alt="Image 1">
                </div>
            </c:forEach>
        </div>
        <button class="prev" onclick="prevSlide()">&#10094;</button>
        <button class="next" onclick="nextSlide()">&#10095;</button>
    </div>
</section><!-- /.flat-slider -->
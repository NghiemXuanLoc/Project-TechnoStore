<%-- 
    Document   : popupaddtocart.jsp
    Created on : Jun 15, 2024, 8:30:37 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<div id="popup" class="popup">
    <div class="popup-content col-lg-10 col-md-10">
        <span class="close-btn" onclick="closePopup()">&times;</span>

        <div class="flat-product-detail" id="product-detail-popup">
        </div><!-- /.flat-product-detail -->
    </div>
</div>


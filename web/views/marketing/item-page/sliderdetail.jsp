<%-- 
    Document   : sliderdetail
    Created on : Jun 29, 2024, 8:49:36 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">
        <!-- PLUGINS CSS STYLE -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">
        <!-- Ekka CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <title>Techno Store - Slider Detail</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                    <c:set var="sdetail" value="${sdetail}"/>
                    <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                        <div><h1><a href="javascript:void(0)">Slider Detail</a></h1></div>
                        <div>
                            <a href="editslider?sId=${sdetail.sliderId}" class="btn btn-primary">Edit Slider</a>
                            <a href="/TechStore/mktslider" class="btn btn-primary">View Slider List</a>
                            <a href="javascript:void(0)" class="btn" id="toggleButton"> 
                                ${sdetail.deleted == 1 ? '🏴' : '🏳️'} 
                            </a>
                            <form id="statusForm" action="mktsliderdetail" method="post">
                                <input type="hidden" id="sId" name="sId" value="${sdetail.sliderId}">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card card-default">
                                <div class="card-body">
                                    <div class="row ec-vendor-uploads">
                                        <div class="col-md-6">
                                            <div class="ec-vendor-img-upload">
                                                <div class="ec-vendor-main-img">
                                                    <h5 class="text-center">Image</h5>
                                                    <div class="thumb-upload-set col-md-12">
                                                        <div class="thumb-upload">
                                                            <div class=" ec-preview">
                                                                <div class="">
                                                                    <img class="ec-image-preview" src="${not empty sdetail.image ? sdetail.image: 'images/logos/originimg.jpg'}" alt="" />
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="ec-vendor-upload-detail">
                                                <form class="row g-3">
                                                    <!-- Add post title in here -->
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Slider Title</strong></label>
                                                        <textarea class="form-control" readonly rows="1" >${sdetail.title}</textarea>
                                                    </div>
                                                    <!--Chose Post Categories here -->
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Slider Back-Link</strong></label>
                                                        <div class="form-control text-center" rows="1">${sdetail.backLink}</div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Status</strong></label> 
                                                        <div class="form-control text-center" rows="1">
                                                            <c:if test="${not empty sdetail}">
                                                                <c:choose>
                                                                    <c:when test="${sdetail.deleted == 0}">Active</c:when>
                                                                    <c:otherwise>Inactive</c:otherwise>
                                                                </c:choose>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Now Position</strong></label>
                                                        <input  class="form-control" readonly value=" ${sdetail.sliderId}">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../../common/sale/footer.jsp"/>
        <script>
            document.getElementById('toggleButton').addEventListener('click', function (event) {
                event.preventDefault();
                var flag = document.getElementById('toggleButton');
                var sId = document.getElementById('sId').value;

                if (confirm('Bạn có chắc chắn muốn chuyển đổi trạng thái không?')) {
                    fetch('mktsliderdetail', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: new URLSearchParams('sId=' + sId)
                    })
                            .then(response => {
                                if (response.ok) {
                                    flag.textContent = flag.textContent.trim() === '🏴' ? '🏳️' : '🏴';
                                } else {
                                    console.error('Error: Failed to update status. Server responded with status', response.status);
                                }
                            })
                            .catch(error => console.error('Error:', error));
                }
            });
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>
</html>

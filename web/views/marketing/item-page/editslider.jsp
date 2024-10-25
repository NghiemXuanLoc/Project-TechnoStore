<%-- 
    Document   : editslider
    Created on : Jul 1, 2024, 8:21:49 AM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <title>Techno Store - Edit Slider</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                            <div><h1><a href="javascript:void(0)">Edit Slider</a></h1></div>
                            <div><a href="/TechStore/mktslider" class="btn btn-primary">View Slider List</a></div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                            <c:set var="sdetail" value="${sdetail}"/>
                            <div class="card card-default">
                                <div class="card-body">
                                    <div class="row ec-vendor-uploads">
                                        <form class="row" action="editslider" method="post" enctype="multipart/form-data" id="editslider">
                                            <div class="col-md-6">
                                                <h5 class="text-center">Thumbnail</h5><br/>
                                                <div class="ec-vendor-img-upload">
                                                    <div class="ec-vendor-main-img">
                                                        <div class="thumb-upload-set col-md-12">
                                                            <div class="thumb-upload">
                                                                <div class="thumb-edit">
                                                                    <input type='file' id="thumbnail" name="thumbnail" class="ec-image-upload" accept=".png, .jpg, .jpeg"  />
                                                                    <label for="thumbUpload">✎</label>
                                                                </div>
                                                                <div class="thumb-preview ec-preview">
                                                                    <div class="image-thumb-preview">
                                                                        <img class=" image-thumb-preview  ec-image-preview" src="${not empty sdetail.image ? sdetail.image : 'images/logos/originimg.jpg'}"  alt="" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="ec-vendor-upload-detail">
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Slider Title</strong></label>
                                                        <input name="title" type="text" class="form-control slug-title" id="title" value="${sdetail.title}">
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Slider Back-Link</strong></label>
                                                        <input class="form-control text-center" name="backlink" type="" id="backlink"  value="${sdetail.backLink}">
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Status</strong></label> 
                                                        <select class="form-select" name="deleted" id="deleted">
                                                            <optgroup>
                                                                <option value="0" ${sdetail.deleted == 0 ? 'selected' : ''}>Active</option>
                                                                <option value="1" ${sdetail.deleted == 1 ? 'selected' : ''}>Inactive</option>
                                                            </optgroup>
                                                        </select>
                                                    </div>
                                                    <div>
                                                        <label class="form-label"><strong>Swap Position</strong></label>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <p>Now Position</p>
                                                                <input  class="form-control" readonly value="${sdetail.sliderId}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <p>To Position</p>
                                                                <select class="form-select" name="topos" id="topos"> 
                                                                    <optgroup>
                                                                        <c:forEach items="${sliderList}" var="sliderList">
                                                                            <c:if test="${sliderList.sliderId!= sdetail.sliderId}">
                                                                                <option value="${sliderList.sliderId}">${sliderList.sliderId}</option>
                                                                            </c:if>
                                                                        </c:forEach> 
                                                                    </optgroup>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <input type="hidden" name="sliderID" value="${sdetail.sliderId}" />
                                                        <button type="button" class="btn btn-primary" onclick="saveChanges()">Save</button>
                                                    </div>
                                                </div>
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
        <jsp:include page="../../common/sale/footer.jsp"/>
        <script>
            function saveChanges() {
                var formData = new FormData(document.getElementById("editslider"));
                var title = formData.get("title");
                var backlink = formData.get("backlink");

                if (title === "") {
                    alert("Please title of slider should not null!");
                    return;
                }
                if (backlink === "") {
                    alert("Please enter a backlink for the slider. It should not be null!");
                    return;
                }

                if (!/^(http|https):\/\/.+$/.test(backlink)) {
                    alert("Invalid backlink. It must start with http:// or https://");
                    return;
                }
                if (confirm("Are you sure to want update ?")) {
                    $.ajax({
                        type: "POST",
                        url: "editslider",
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            alert("Slider updated successfully!");
                            setTimeout(function () {
                                document.getElementById('successMessage').style.display = 'none';
                            }, 3000);
                        },
                        error: function (xhr, status, error) {
                            alert("Error updating slider: " + error);
                        }
                    });
                } else {
                    // User cancelled update
                }
            }
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>
</html>

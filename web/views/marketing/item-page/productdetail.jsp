<%-- 
    Document   : productdetail
    Created on : Jun 26, 2024, 8:45:45 PM
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/responsive.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <title>Techno Store - Product Detail</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <!-- Bắt đầu thanh narvbar -->
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
                <!-- Bắt đầu thanh header -->
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                    <c:set var="p" value="${requestScope.productdetail}"/>
                    <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                        <div><h1><a href="javascript:void(0)">Product Detail</a></h1></div>
                        <div>
                            <a href="addproduct" class="btn btn-primary">Add Product</a>
                            <a href="editproduct?pEditID=${sessionScope.product.productId}" class="btn btn-primary" id="editButton">Edit Product</a>
                            <a href="/TechStore/productslist" class="btn btn-primary">View Product List</a>
                            <a href="javascript:void(0)" class="btn" id="toggleButton"> 
                                ${p.deleted == 1 ? '🏴' : '🏳️'} 
                            </a>
                            <form id="statusForm" action="productsdetail" method="post">
                                <input type="hidden" id="postID" name="postID" value="${p.productDetailId}">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card card-default">
                                <div class="card-body">
                                    <div class="row ec-vendor-uploads">
                                        <div class="col-md-6">
                                            <div class="col-md-12">
                                                <div class="ec-vendor-img-upload">
                                                    <div class="ec-vendor-main-img">
                                                        <div class="thumb-upload-set col-md-12">
                                                            <div class="thumb-upload">
                                                                <div class="ec-preview">
                                                                    <div class="image-thumb-preview">
                                                                        <img class="image-thumb-preview ec-image-preview" src="${not empty sessionScope.product.gallery[0] ? sessionScope.product.gallery[0] : 'images/logos/originimg.jpg'}" href='javascript:void(0)' alt='' width='400' height='300' />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="ec-vendor-img-upload">
                                                    <div class="ec-vendor-main-img">
                                                        <div class="thumb-upload-set col-md-12">
                                                            <div class="thumb-upload">
                                                                <c:if test="${not empty sessionScope.product.gallery}">
                                                                    <c:forEach items="${sessionScope.product.gallery}" var="image" begin="0" end="${sessionScope.product.gallery.size() > 4? 3 :sessionScope.product.gallery.size() - 1}">
                                                                        <div class="thumb-upload">
                                                                            <div class="thumb-preview ec-preview" style="display: inline-block; margin-right: 10px;">
                                                                                <div class="image-thumb-preview" data-thumb="${image}">
                                                                                    <img class="image-thumb-preview ec-image-preview" src="${not empty image? image : 'images/logos/originimg.jpg'}" alt="Thumbnail of the product" />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:forEach>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="memory-options">
                                                <h5>Memmory</h5>
                                                <div class="option" id="memory">
                                                    <c:forEach items="${sessionScope.memoryAll}" var="memory">
                                                        <c:if test="${memory.memorySize % 1024 == 0}">
                                                            <span onclick="optionChoose(this, 'memory', ${sessionScope.product.productId}, ${memory.memoryId})"  id="memory${memory.memoryId}"><fmt:formatNumber value="${memory.memorySize/1024}" type="number" maxFractionDigits="0"/>&nbsp;TB</span>
                                                        </c:if>
                                                        <c:if test="${memory.memorySize % 1024 != 0}">
                                                            <span onclick="optionChoose(this, 'memory', ${sessionScope.product.productId}, ${memory.memoryId})"   id="memory${memory.memoryId}">${memory.memorySize}&nbsp;GB</span>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="color-options">
                                                <h5>Color</h5>
                                                <div class="option" id="color">
                                                    <c:forEach items="${sessionScope.colorAll}" var="color">
                                                        <span onclick="optionChoose(this, 'color', ${sessionScope.product.productId}, ${color.colorId})"  id="color${color.colorId}">${color.colorName}</span>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="ec-vendor-upload-detail">
                                                <div class="col-md-12">
                                                    <label class="form-label"><strong>Product Name</strong></label>
                                                    <textarea readonly class="form-control" rows="1" >${sessionScope.product.productName}</textarea>
                                                </div><br>
                                                <div class="col-md-12">
                                                    <label class="form-label"><strong>Brief Information</strong></label> 
                                                    <textarea readonly  row="7" class="form-control">  ${sessionScope.product.briefInfo}</textarea>
                                                </div><br><br>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label class="form-label">Product Categories</label>
                                                        <select  name="category" id="category"  class="form-select">
                                                            <option value="${product.category.categoryId}">${product.category.categoryName}</option>
                                                        </select>
                                                    </div>
                                                    <!-- Add supplier -->
                                                    <div class="col-md-6">
                                                        <label class="form-label">Supplier</label>
                                                        <select  name="supplier" id="supplier"  class="form-select"">
                                                            <optgroup>
                                                                <option value="${product.supplier.supplierId}">${product.supplier.companyName}</option>
                                                            </optgroup>
                                                        </select>
                                                    </div>
                                                </div><br><br>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <c:if test="${requestScope.productDetail == null}">
                                                            <label class="form-label"><strong>Price Max </strong></label>
                                                            <input  value="<fmt:formatNumber value="${sessionScope.product.minPrice}" />(VND)" class="form-control">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label"><strong>Price Min</strong></label>
                                                            <input   value="<fmt:formatNumber value="${sessionScope.product.maxPrice}" />(VND)"  class="form-control">
                                                        </c:if>
                                                    </div>
                                                    <div class="row">
                                                        <c:if test="${requestScope.productDetail != null}">
                                                            <div class="col-md-6">
                                                                <label class="form-label"><strong>Price Sale </strong></label>
                                                                <input  value="<fmt:formatNumber value="${requestScope.productDetail.priceOrigin}"/>(VND)" class="form-control">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label class="form-label"><strong>Price Origin</strong></label>
                                                                <input   value="<fmt:formatNumber value="${requestScope.productDetail.priceSale}"/>(VND)" class="form-control">
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </div><br><br>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label class="form-label"><strong>Quantity</strong></label>
                                                        <input readonly class="form-control" value="${requestScope.productDetail.quantity}" class="form-control">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label"><strong>Status</strong></label>
                                                        <c:choose>
                                                            <c:when test="${sessionScope.quantityStock > 0}">
                                                                <input readonly class="form-control" value="In Stock" class="form-control">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input readonly class="form-control" value="Sold Out" class="form-control">
                                                            </c:otherwise>
                                                        </c:choose>
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
            </div>
        </div>
    </div>
    <jsp:include page="../../common/sale/footer.jsp"/>
    <script>
        document.getElementById("editButton").href = "editproduct?productId=" + document.getElementById("productId").value +
                "&colorId=" + document.getElementById("colorId").value +
                "&memoryId=" + document.getElementById("memoryId").value;

        document.getElementById('toggleButton').addEventListener('click', function (event) {
            event.preventDefault();
            var flag = document.getElementById('toggleButton');
            var postID = document.getElementById('postID').value;

            if (confirm('Bạn có chắc chắn muốn chuyển đổi trạng thái không?')) {
                fetch('productsdetail', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams('postID=' + postID)
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
    <!-- Common Javascript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-dashboard.js"></script>
</body>
</html>

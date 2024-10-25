<%-- 
    Document   : productlist
    Created on : Jun 14, 2024, 10:29:00 AM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">
        <!-- PLUGINS CSS STYLE -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">
        <!-- Ekka CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <title>Techno Store - Product List</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <!-- Bắt đầu thanh narvbar -->
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
                <!-- Bắt đầu thanh header -->
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <!--xử lí logic load từ database-->
                <!--Phần nội dung hiển thị của Quang -->
                <div class="ec-page-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                            <div><h1><a href="productslist">Products List</a></h1></div>
                            <div><a href="addproduct" class="btn btn-primary"> Add Products</a></div>
                        </div>
                        <!-- bảng hiển thị ra danh sách các bài đăng -->
                        <div class="row">
                            <div class="col-12">
                                <div class="card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table">
                                                <!--filter chọn theo tác giả, thể loại bài viết, trạng thái,....-->
                                                <div class="row justify-content-between top-information">
                                                    <div class="col-2 dataTables_length" id="responsive-data-table_length">
                                                        <label>Show</label>
                                                        <select class="form-select form-select-sm" onchange="window.location.href = 'productslist?page=1&productPerPage=' + this.value;">
                                                        <option value="${productPerPage != null ? productPerPage : ''}">${productPerPage != null ? productPerPage : 'Select'}</option>
                                                        <option value="10">10</option>
                                                        <option value="20">20</option>
                                                        <option value="50">50</option>
                                                    </select> 
                                                </div>
                                                <!--filter theo thể loại bài viết-->
                                                <div class="col-2 dataTables_length">
                                                    <label>Filter by category</label>
                                                    <form action="productslist" method="get">
                                                        <select name="category" class="form-select form-select-sm" onchange="this.form.submit()">
                                                            <option value="">Select a category</option>
                                                            <c:forEach var="c" items="${categories}">
                                                                <option value="${c.categoryId}">${c.categoryName}</option>
                                                            </c:forEach>
                                                            <option value="">All</option>
                                                        </select>
                                                    </form>
                                                </div>
                                                <!--filter theo trạng thái bài viết-->
                                                <div class="col-2 dataTables_length">
                                                    <label>Filter by status</label>
                                                    <form action="productslist" method="get">
                                                        <select name="active" class="form-select form-select-sm" onchange="this.form.submit()">
                                                            <option value="">Select a status</option>
                                                            <option value="${0}">Active</option>
                                                            <option value="${1}">Inactive</option>
                                                            <option value="">Both</option>
                                                        </select>
                                                    </form>
                                                </div>
                                                <!-- thanh tìm kiếm bài đăng -->
                                                <div id="responsive-data-table_filter" class="col-3  dataTables_filter">
                                                    <label>Search</label>
                                                    <form action="productslist" method="get">
                                                        <input type="search" class="form-control form-control-sm" placeholder="search by title" name="search">
                                                    </form>
                                                </div>
                                            </div><br/>
                                            <!--Các trường dữ liệu cần hiển thị của mỗi bài viết-->
                                            <thead>
                                                <tr class="text-center">
                                                    <th><button onclick="sortTableByID()">ID↑↓</button></th>
                                                    <th><button>Thumbnail</button></th>
                                                    <th><button onclick="sortTableByColumn(2)">Title↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(3)">Category↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(4)">Price↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(5)">Sale Price↑↓</button></th>
                                                    <th><button onclick="sortTableByFeatured()">Featured↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(7)">Status↑↓</button></th>
                                                    <th><button>Action <button/></th>
                                                    <th><button>View<button/></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="p" items="${lProductDetail}">
                                                    <tr>
                                                        <td>${p.productId}</td>
                                                        <td><img class="tbl-thumb" src="${pageContext.request.contextPath}/${p.gallery[0]}" alt="Product Image" /></td>
                                                        <td>${p.productName}</td>
                                                        <td>${p.category.categoryName}</td>
                                                        <td>
                                                            <fmt:formatNumber value="${p.maxPrice }" pattern="###,###,###(VND)" />
                                                        </td>
                                                        <td>
                                                            <fmt:formatNumber value="${p.minPrice}" pattern="###,###,###(VND)" />
                                                        </td>
                                                        <td>
                                                            <c:if test="${bestSellingProducts.contains(p)}">Best Selling</c:if>
                                                            <c:if test="${hotSaleProducts.contains(p)}">Hot Sale</c:if>
                                                            <c:if test="${!bestSellingProducts.contains(p) && !hotSaleProducts.contains(p)}">In stock</c:if>
                                                            </td>
                                                            <td>
                                                            <c:choose>
                                                                <c:when test="${p.deleted == 0}">Active</c:when>
                                                                <c:otherwise>Inactive</c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <div class="btn-group mb-1">
                                                                <form action="productslist" method="post">
                                                                    <input type="hidden" name="productID" value="${p.productId}">
                                                                    <button type="button" onclick="toggleButton(this, ${p.productId})" class="${p.deleted == 1 ? 'btn btn-danger' : 'btn btn-success'}">${p.deleted == 0 ? 'Show' : 'Hide'}</button>
                                                                </form>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="btn-group mb-1">
                                                                <a href="productsdetail?productId=${p.productId}">
                                                                    <button type="button" class="btn btn-outline-success">View</button>
                                                                </a>
                                                                <button type="button" class="btn btn-outline-success dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown"></button>
                                                                <div class="dropdown-menu">
                                                                    <a class="dropdown-item" href="editproduct?pEditID=${p.productId}">Edit</a>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="pagination">
                                            <!-- Calculate the startPage and endPage -->
                                            <c:set var="pagesToShow" value="3" />
                                            <c:set var="halfPagesToShow" value="${pagesToShow / 2}" />
                                            <c:set var="startPage" value="${currentPage - halfPagesToShow}" />
                                            <c:set var="endPage" value="${currentPage + halfPagesToShow}" />

                                            <!-- Ensure startPage is at least 1 -->
                                            <c:if test="${startPage < 1}">
                                                <c:set var="startPage" value="1" />
                                                <c:set var="endPage" value="${pagesToShow}" />
                                            </c:if>
                                            <!-- Ensure endPage does not exceed numPages -->
                                            <c:if test="${endPage > numPages}">
                                                <c:set var="endPage" value="${numPages}" />
                                                <c:set var="startPage" value="${numPages - pagesToShow + 1}" />
                                            </c:if>
                                            <!-- Ensure startPage is at least 1 again after adjustment -->
                                            <c:if test="${startPage < 1}">
                                                <c:set var="startPage" value="1" />
                                            </c:if>
                                            <!-- Previous page link -->
                                            <c:if test="${currentPage > 1}">
                                                <a class="btn btn-outline-purple" href="productslist?page=${currentPage - 1}&productPerPage=${productPerPage}"><</a>    
                                            </c:if>
                                            <!-- Page number links -->
                                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                                <c:choose>
                                                    <c:when test="${currentPage == i}">
                                                        <span class="btn btn-outline-purple active">${i}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="btn btn-outline-purple" href="productslist?page=${i}&productPerPage=${productPerPage}">${i}</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <!-- Next page link -->
                                            <c:if test="${currentPage < numPages}">
                                                <a class="btn btn-outline-purple" href="productslist?page=${currentPage + 1}&productPerPage=${productPerPage}">></a>
                                            </c:if>
                                        </div>
                                        <!-- Other content -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../../common/sale/footer.jsp"/>

        <!-- Common Javascript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>
</html>

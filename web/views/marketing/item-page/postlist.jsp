<%-- 
    Document   : postlist
    Created on : May 24, 2024, 3:20:05 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="controller.user.*" %>
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
        <title>Techno Store - Post List</title>
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
                <c:if test="${not empty sessionScope.failPath}">
                    <div class="alert alert-warning">
                        <strong>Warning!</strong> ${failPath}.
                    </div>
                    <% session.removeAttribute("failPath"); %>
                </c:if>
                <div class="content">
                    <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                        <div><h1><a href="postlist">Posts List</a></h1></div>
                        <div><a href="addpost" class="btn btn-primary"> Add Posts</a></div>
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
                                                    <select class="form-select form-select-sm" onchange="window.location.href = 'postlist?page=1&postPerPage=' + this.value;">
                                                        <option value="${postPerPage != null ? postPerPage : ''}">${postPerPage != null ? postPerPage : 'Select'}</option>
                                                        <option value="5">5</option>
                                                        <option value="10">10</option>
                                                        <option value="20">20</option>
                                                        <option value="50">50</option>
                                                    </select> 
                                                </div>
                                                <!-- Filter theo tên của tác giả-->
                                                <div class="col-2 dataTables_length">
                                                    <label>Filter by author</label>
                                                    <form action="postlist" method="get">
                                                        <select name="author" class="form-select form-select-sm" onchange="this.form.submit()">
                                                            <option value="">Select a author</option>
                                                            <c:forEach var="p" items="${uAuthor}">
                                                                <option value="${p}">${p}</option>
                                                            </c:forEach>
                                                            <option value="">All</option>
                                                        </select>
                                                    </form>
                                                </div>
                                                <!--filter theo thể loại bài viết-->
                                                <div class="col-2 dataTables_length">
                                                    <label>Filter by category</label>
                                                    <form action="postlist" method="get">
                                                        <select name="category" class="form-select form-select-sm" onchange="this.form.submit()">
                                                            <option value="">Select a category</option>
                                                            <c:forEach var="p" items="${uCategory}">
                                                                <option value="${p}">${p}</option>
                                                            </c:forEach>
                                                            <option value="">All</option>
                                                        </select>
                                                    </form>
                                                </div>
                                                <!--filter theo trạng thái bài viết-->
                                                <div class="col-2 dataTables_length">
                                                    <label>Filter by status</label>
                                                    <form action="postlist" method="get">
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
                                                    <form action="postlist" method="get">
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
                                                    <th><button onclick="sortTableByColumn(4)">Author↑↓</button></th>
                                                    <th><button onclick="sortTableByFeatured()">Featured↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(7)">Status↑↓</button></th>
                                                    <th><button>Action <button/></th>
                                                    <th><button>View<button/></th>
                                                </tr>
                                            </thead>
                                            <!--Nội dung hiển thị của các bài viết-->
                                            <tbody>
                                                <c:forEach var="post" items="${postlist}">
                                                    <tr>
                                                        <td>${post.blogDetailId}</td>
                                                        <td><img class="tbl-thumb" src="${pageContext.request.contextPath}/${post.thumbnail}" alt="" /></td>
                                                        <td>${post.title}</td>
                                                        <td>${post.blogCategory.blogCategoryName}</td>
                                                        <td>${post.author}</td>
                                                        <td>
                                                            <c:set var="currentDate" value="<%= new java.util.Date() %>" />
                                                            <c:set var="diff" value="${(currentDate.time - post.blogDate.time) / (1000*60*60*24)}" />
                                                            <c:choose>
                                                                <c:when test="${diff < 7}">Latest</c:when>
                                                                <c:when test="${diff >= 7 && diff < 30}">Recently</c:when>
                                                                <c:otherwise>Oldest</c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td><c:choose>
                                                                <c:when test="${post.deleted == 0}">Active</c:when>
                                                                <c:otherwise>Inactive</c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <div class="btn-group mb-1">
                                                                <form action="postlist" method="post">
                                                                    <input type="hidden" name="blogID" value="${post.blogDetailId}">
                                                                    <button type="button" onclick="toggleButton(this, ${post.blogDetailId})" class="${post.deleted == 1 ? 'btn btn-danger' : 'btn btn-success'}">${post.deleted == 0 ? 'Show' : 'Hide'}</button>
                                                                </form>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="btn-group mb-1">
                                                                <a href="postdetail?postID=${post.blogDetailId}"><button type="button" class="btn btn-outline-success">View</button></a>
                                                                <button type="button"
                                                                        class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                                                        data-bs-toggle="dropdown">
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <a class="dropdown-item" href="editpost?postID=${post.blogDetailId}">Edit</a>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="pagination">
                                            <!--Gán giá trị các biến để hiển thị 3 trang gần nhau-->
                                            <c:set var="pagesToShow" value="3" />
                                            <c:set var="startPage" value="${currentPage > 1 ? currentPage - 1 : 1}" />
                                            <c:set var="endPage" value="${currentPage + 1 < numPages ? currentPage + 1 : numPages}" />
                                            <!--Nếu ở trang 1 thì hiển thị 1 - 2 - 3 - next-->
                                            <c:if test="${currentPage > 1}">
                                                <a class="btn btn-outline-purple" href="postlist?page=${currentPage - 1}&postPerPage=${param.postPerPage}&author=${param.author}&category=${param.category}&active=${param.active}"><</a>    
                                            </c:if>
                                            <!--Vòng lặp in ra tất cả số trang đang có-->
                                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <span class="btn btn-outline-purple active">${i}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="btn btn-outline-purple" href="postlist?page=${i}&postPerPage=${param.postPerPage}&author=${param.author}&category=${param.category}&active=${param.active}">${i}</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <!--Nếu đang ở trang cuối thì hiển thị 2 trang trước đó và Pre-->
                                            <c:if test="${currentPage < numPages}">
                                                <a class="btn btn-outline-purple" href="postlist?page=${currentPage + 1}&postPerPage=${param.postPerPage}&author=${param.author}&category=${param.category}&active=${param.active}">></a>
                                            </c:if>
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

        <!-- Common Javascript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>
</html>

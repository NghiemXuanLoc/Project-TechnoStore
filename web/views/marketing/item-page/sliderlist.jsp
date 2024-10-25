<%-- 
    Document   : sliderlist
    Created on : Jun 29, 2024, 8:49:26 PM
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
        <title>Techno Store - Slider List</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <!-- Bắt đầu thanh narvbar -->
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
                <!-- Bắt đầu thanh header -->
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                            <div><h1><a href="mktslider">Slider List</a></h1></div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table">
                                                <div class="row justify-content-between top-information">
                                                    <div class="col-2 dataTables_length">
                                                        <label>Filter by status</label>
                                                        <form action="mktslider" method="get">
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
                                                    <form action="mktslider" method="get">
                                                        <input type="search" class="form-control form-control-sm" placeholder="search by title" name="search">
                                                    </form>
                                                </div>
                                            </div><br/>
                                            <thead>
                                                <tr class="text-center">
                                                    <th><button onclick="sortTableByID()">ID↑↓</button></th>
                                                    <th><button>Thumbnail</button></th>
                                                    <th><button onclick="sortTableByColumn(2)">Title↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(3)">Back Link↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(4)">Status↑↓</button></th>
                                                    <th><button>Action <button/></th>
                                                    <th><button>View<button/></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="p" items="${sliderList}">
                                                    <tr  class="text-center">
                                                        <td>${p.sliderId}</td>
                                                        <td><img class="tbl-thumb" src="${pageContext.request.contextPath}/${p.image}" alt="Slider Image" /></td>
                                                        <td>${p.title}</td>
                                                        <td>${p.backLink}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${p.deleted == 0}">Active</c:when>
                                                                <c:otherwise>Inactive</c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <div class="btn-group mb-1">
                                                                <form action="mktslider" method="post">
                                                                    <input type="hidden" name="sId" value="${p.sliderId}">
                                                                    <button type="button" onclick="toggleButton(this, ${p.sliderId})" class="${p.deleted == 1 ? 'btn btn-danger' : 'btn btn-success'}">${p.deleted == 0 ? 'Show' : 'Hide'}</button>
                                                                </form>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="btn-group mb-1">
                                                                <a href="mktsliderdetail?sId=${p.sliderId}">
                                                                    <button type="button" class="btn btn-outline-success">View</button>
                                                                </a>
                                                                <button type="button" class="btn btn-outline-success dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown"></button>
                                                                <div class="dropdown-menu">
                                                                    <a class="dropdown-item" href="editslider?sId=${p.sliderId}">Edit</a>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="pagination">
                                            <c:set var="pagesToShow" value="3" />
                                            <c:set var="startPage" value="${currentPage > 1 ? currentPage - 1 : 1}" />
                                            <c:set var="endPage" value="${currentPage + 1 < numPages ? currentPage + 1 : numPages}" />
                                            <c:if test="${currentPage > 1}">
                                                <a class="btn btn-outline-purple" href="mktslider?page=${currentPage - 1}&sliderPerPage=${param.sliderPerPage}&backlink=${param.backLink}&active=${param.active}"><</a>    
                                            </c:if>
                                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <span class="btn btn-outline-purple active">${i}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="btn btn-outline-purple" href="mktslider?page=${i}&sliderPerPage=${param.sliderPerPage}&backlink=${param.backLink}&active=${param.active}">${i}</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${currentPage < numPages}">
                                                <a class="btn btn-outline-purple" href="mktslider?page=${currentPage + 1}&sliderPerPage=${param.sliderPerPage}&backlink=${param.backLink}&active=${param.active}">></a>
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

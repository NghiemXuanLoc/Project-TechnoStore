<%-- 
    Document   : userlist
    Created on : Jul 3, 2024, 8:32:06 PM
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
        <!-- PLUGINS CSS STYLE -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">
        <!-- Ekka CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <title>Techno Store - User List</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                            <div><h1><a href="mktuserlist">Slider List</a></h1></div>
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
                                                        <form action="mktuserlist" method="get">
                                                            <select name="active" class="form-select form-select-sm" onchange="this.form.submit()">
                                                                <option value="">Select a status</option>
                                                                <option value="${0}">Active</option>
                                                            <option value="${1}">Inactive</option>
                                                            <option value="">Both</option>
                                                        </select>
                                                    </form>
                                                </div>
                                                <div id="responsive-data-table_filter" class="col-3  dataTables_filter">
                                                    <label>Search</label>
                                                    <form action="mktuserlist" method="get">
                                                        <input type="search" class="form-control form-control-sm" placeholder="search by title" name="search">
                                                    </form>
                                                </div>
                                            </div><br/>
                                            <thead>
                                                <tr class="text-center">
                                                    <th><button onclick="sortTableByID()">ID↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(2)">Full Name↑↓</button></th>
                                                    <th><button>Gender</button></th>
                                                    <th><button onclick="sortTableByColumn(3)">Email↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(4)">Mobile↑↓</button></th>
                                                    <th><button onclick="sortTableByColumn(5)">Status↑↓</button></th>
                                                    <th><button>Action <button/></th>
                                                    <th><button>View<button/></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="p" items="${listU}">
                                                    <c:if test="${p.role.roleId != 5}">
                                                        <tr  class="text-center">
                                                            <td>${p.userId}</td>
                                                            <td>${p.fullName}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${p.gender == 0}">Female</c:when>
                                                                    <c:otherwise>Male</c:otherwise>
                                                                </c:choose></td>
                                                            <td>${p.email}</td>
                                                            <td>${p.phoneNumber}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${p.deleted == 0}">Active</c:when>
                                                                    <c:otherwise>Inactive</c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <div class="btn-group mb-1">
                                                                    <form action="mktuserlist" method="post">
                                                                        <input type="hidden" name="uId" value="${p.userId}">
                                                                        <button type="button" onclick="toggleButton(this, ${p.userId})" class="${p.deleted == 1 ? 'btn btn-danger' : 'btn btn-success'}">${p.deleted == 0 ? 'Show' : 'Hide'}</button>
                                                                    </form>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="btn-group mb-1">
                                                                    <a href="mktuserdetail?sId=${p.userId}">
                                                                        <button type="button" class="btn btn-outline-success">View</button>
                                                                    </a>
                                                                    
                                                                    
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="pagination">
                                            <c:set var="pagesToShow" value="3" />
                                            <c:set var="startPage" value="${currentPage > 1 ? currentPage - 1 : 1}" />
                                            <c:set var="endPage" value="${currentPage + 1 < numPages ? currentPage + 1 : numPages}" />
                                            <c:if test="${currentPage > 1}">
                                                <a class="btn btn-outline-purple" href="mktuserlist?page=${currentPage - 1}&userPerPage=${param.userPerPage}&active=${param.active}"><</a>    
                                            </c:if>
                                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <span class="btn btn-outline-purple active">${i}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="btn btn-outline-purple" href="mktuserlist?page=${i}&userPerPage=${param.userPerPage}&active=${param.active}">${i}</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${currentPage < numPages}">
                                                <a class="btn btn-outline-purple" href="mktuserlist?page=${currentPage + 1}&userPerPage=${param.userPerPage}&active=${param.active}">></a>
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

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

                    <div class="ec-content-wrapper">

                        <div class="content">

                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <th>Comment Id</th>
                                        <th>User Id</th>
                                        <th>Blog detail Id</th>
                                        <th>Rating</th>
                                        <th>Comment Date</th>
                                        <th>Content</th>
                                        <th>Like</th>
                                        <th>Report</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                        </thead>

                                        <tbody>

                                        <c:forEach items="${sessionScope.listAll}" var="comment">
                                            <tr>
                                                <td>${comment.commentId}</td>
                                                <td>${comment.user.fullName}</td>
                                                <td>${comment.blogDetail.blogDetailId}</td>
                                                <td>${comment.rating}</td>
                                                <td>${comment.commentDate}</td>
                                                <td>${comment.content}</td>
                                                <td>${comment.likes}</td>
                                                <td>${comment.report}</td>
                                                <td>
                                                    <c:if test="${comment.deleted == 0}">
                                                        <span>Pending</span>
                                                    </c:if> 

                                                    <c:if test="${comment.deleted == 2}">
                                                        <span>Success</span>
                                                    </c:if> 
                                                </td>
                                                <td>
                                                    <div class="btn-group">
                                                        <button type="button"
                                                                class="btn btn-outline-success">Info</button>
                                                        <button type="button"
                                                                class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                                                data-bs-toggle="dropdown" aria-haspopup="true"
                                                                aria-expanded="false" data-display="static">
                                                            <span class="sr-only">Info</span>
                                                        </button>

                                                        <div class="dropdown-menu">
                                                            <c:if test="${comment.deleted == 0}">
                                                                <a class="dropdown-item" href="javascript:void(0)">Accept</a>
                                                            </c:if>
                                                            <a class="dropdown-item" href="javascript:void(0)">Delete</a>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div> <!-- End Content -->
                </div> <!-- End Content Wrapper -->
            </div>
        </div>
        <jsp:include page="../../common/sale/footer.jsp"/>

        <!-- Common Javascript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/slick.min.js"></script>
        <script src='${pageContext.request.contextPath}/javascript/jquery.datatables.min.js'></script>
        <script src='${pageContext.request.contextPath}/javascript/datatables.bootstrap5.min.js'></script>
        <script src='${pageContext.request.contextPath}/javascript/datatables.responsive.min.js'></script>
        <script src="${pageContext.request.contextPath}/javascript/optionswitcher.js"></script>


    </body>
</html>

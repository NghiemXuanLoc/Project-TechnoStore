<%-- 
    Document   : userlist
    Created on : May 24, 2024, 4:53:48 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard HTML Template.">

        <title>Setting Filter </title>

        <!-- GOOGLE FONTS -->
        <!-- PLUGINS CSS STYLE -->

        <!-- Data Tables -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">

        <!--<link href='assets/plugins/data-tables/responsive.datatables.min.css' rel='stylesheet'>-->

        <!-- ekka CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/ekka.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/simplebar.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/userlist.css" />
        <!-- FAVICON -->
        <link href="assets/img/favicon.png" rel="shortcut icon" />
    </head>

    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-dark ec-header-light" id="body">
        <jsp:include page="../../common/admin/narvbar.jsp"></jsp:include>
        <jsp:include page="../../common/sale/header.jsp"></jsp:include>
            <!-- WRAPPER -->
            <div class="wrapper">

                <!-- LEFT MAIN SIDEBAR -->


                <!-- PAGE WRAPPER -->
                <div class="ec-page-wrapper">

                    <!-- Header -->
                <c:if test="${not empty sessionScope.Success}">
                    <div class="alert alert-success">
                        <strong>Success!</strong> ${Success}.
                    </div>
                    <% session.removeAttribute("Success"); %>
                </c:if>

                <!-- CONTENT WRAPPER ITER2 --> 
                <div class="ec-content-wrapper">

                    <div class="content">
                        <div class="breadcrumb-wrapper breadcrumb-contacts">

                            <div class="addUserButton">
                                <form action="addFilter" method="get">
                                    <button type="submit" class="btn btn-info">Add Permissions </button>
                                </form>
                            </div>
                        </div>

                        <div class="select_option row">
                            <!--khong fillter-->

                            <table class="col-md-6">                             
                                <tr>
                                    <td class="header_row" >Filter by role</td>
                                    <td class="header_row" >Filter by status</td>
                                </tr>

                                <!--filter theo gender-->
                                <tr>         
                                    <!--filter theo role-->
                                    <td>                                                                        
                                        <form action="settingFilter" method="post">
                                            <select name="filterByRole" onchange="this.form.submit()">
                                                <option value="9">None</option>
                                                <c:forEach items="${listRole}" var="i">
                                                    <c:if test="${i.roleId != 5}">
                                                        <option value="${i.roleId}" ${roleRes eq i.roleId ? "selected" : ""}  >${i.getRoleName()}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>   
                                        </form>
                                    </td>


                                    <!--filter theo status-->
                                    <td>                            
                                        <form action="settingFilter" method="post">
                                            <select name="filterByStatus" onchange="this.form.submit()">
                                                <option value="2">None</option>
                                                <option value="1" ${statusRes eq "1" ? "selected" : ""} >Inactive</option>
                                                <option value="0" ${statusRes eq "0" ? "selected" : ""}>Active</option>
                                            </select>
                                        </form>
                                    </td>                            
                                </tr>


                            </table>

                            <div class="col-md-6 ">
                                <form class="search_bySomeThing" action="settingFilter" method="post">
                                    <input class="search_input" name="search" placeholder="Search by URL"><!-- comment -->
                                    <input type="submit" value="Search" />
                                </form>
                            </div>



                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="ec-vendor-list card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table table-hover" >
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th>RoleName</th>
                                                        <th>URL</th>
                                                        <th>Feature</th>                                                       
                                                        <th>Status</th>
                                                        <th>Action</th>
                                                    </tr>                                                 
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${listAllAuthor}" var="u">
                                                        <tr>
                                                            <td>${u.role.roleName}</td>
                                                            <td>${u.url}</td>
                                                            <td>${u.feature}</td>
                                                            <td style="color: ${u.status eq '0' ? "blue" : "red"}">${u.status eq '0' ? "Active" : "Inactive"}</td>                                                            
                                                            <td><a href="updateFilter?id=${u.id}"><i class="mdi mdi-pencil"></i></a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Paging-->
                        <div class="text-center ">
                            <ul class="pagination">

                                <c:if test="${currenPage > 1}">
                                    <li class="page-item"><a class="page-link" href="settingFilter?page=${(currenPage-1)}">Previous</a></li>
                                    </c:if>


                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                    <li class="page-item"><a class="page-link ${i == currenPage ? "active-paging": ""}"  href="settingFilter?page=${i}">${i}</a></li>
                                    </c:forEach>

                                <c:if test="${currenPage < num}">
                                    <li class="page-item"><a class="page-link" href="settingFilter?page=${currenPage+1}">Next</a></li>
                                    </c:if>
                            </ul>
                        </div>
                        <!-- Add User Modal  -->

                    </div> <!-- End Content -->
                </div> <!-- End Content Wrapper -->

                <!-- Footer -->
                <jsp:include page="../../common/sale/footer.jsp"></jsp:include>



                </div> <!-- End Page Wrapper -->
            </div> <!-- End Wrapper -->

            <!-- Common Javascript -->
            <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>

</html>

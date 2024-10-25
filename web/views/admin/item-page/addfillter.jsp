<%-- 
    Document   : adduser
    Created on : Jun 6, 2024, 6:11:28 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Filter</title>
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
        <!--header-->
        <jsp:include page="../../common/admin/narvbar.jsp"></jsp:include>
        <jsp:include page="../../common/sale/header.jsp"></jsp:include>
        <div class="wrapper">
            <div class="ec-page-wrapper">
                <div class="container py-5 h-100">
                    <div class="row justify-content-center align-items-center h-100">
                        <div class="col-12 col-lg-9 col-xl-7">
                            <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                <div class="card-body p-4 p-md-5">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Add Permissions</h3>
                                <c:if test="${Err != null}">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: red">${Err}</h3>
                                </c:if>
                                    <c:if test="${Success != null}">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: green">${Success}</h3>
                                </c:if>
                                    <form action="addFilter" method="post">

                                        <div class="row">
                                            <div class="col-md-6 mb-4">

                                                <div data-mdb-input-init class="form-outline">
                                                    <input required type="text" id="firstName" value="${url ne null ? url :""}" class="form-control form-control-lg" name="url" />
                                                    <label class="form-label" for="firstName">URL</label>
                                                </div>

                                            </div>                                          
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4 d-flex align-items-center">

                                                <div data-mdb-input-init class="form-outline datepicker w-100">
                                                    <input required type="text" value="${feature ne null ? feature : ""}" class="form-control form-control-lg"  name="feature" />
                                                    <label for="birthdayDate" class="form-label">Feature</label>
                                                </div>

                                            </div>
                                            
                                        </div>

                                        <div class="row">
                                            <div class="col-6">

                                                <select required name="role" class="select form-control-lg">
                                                <c:forEach items="${listRole}" var="o">
                                                    <c:if test="${o.roleId != 5}">
                                                        <option value="${o.roleId}"  ${role eq o.roleId ? 'selected' : ""}>${o.roleName}</option>
                                                    </c:if>
                                                    </c:forEach>
                                                </select>
                                                <label class="form-label select-label">Role</label>

                                            </div>
                                            
                                            <div class="col-6">

                                                <select name="status" class="select form-control-lg">
<!--                                                    <option value="0">Active</option>-->
                                                    <option value="0" ${status eq '0' ? "selected" : ""}>Active</option>
                                                    <option value="1" ${status eq '1' ? "selected" : ""}>Inactive</option>
                                                </select>
                                                <label class="form-label select-label">Status</label>

                                            </div>
                                        </div>

                                        <div class="mt-4 pt-2">
                                            <input data-mdb-ripple-init class="btn btn-primary btn-lg" type="submit" value="Submit" />
                                            <input data-mdb-ripple-init class="btn btn-primary btn-lg submit" style="width: 88px;" onsubmit="false" onclick="location.href='settingFilter'" value="Back" />
                                        </div>
                                    </form>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Footer-->
                <jsp:include page="../../common/sale/footer.jsp"></jsp:include>
            </div>
        </div>
        
        
        
        
        
        
        
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>
</html>

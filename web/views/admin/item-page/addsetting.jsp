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
        <title>Add Setting</title>
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
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">ADD SETTING</h3>
                                <c:if test="${Err != null}">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: red">${Err}</h3>
                                </c:if>
                                    <c:if test="${Success != null}">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: green">${Success}</h3>
                                </c:if>
                                    <form action="addSetting" method="post">

                                        <div class="row">
                                            <div class="col-md-6 mb-4">

                                                <select required name="type" class="select form-control-lg">
                                                <c:forEach items="${listType}" var="o">
                                                    <option value="${o.type}" ${typeRes eq o.type ? "selected" : ""} >${o.type}</option>
                                                    </c:forEach>
                                                </select>
                                                <label class="form-label select-label">Type</label>

                                            </div>
                                            <div class="col-md-6 mb-4">

                                                <div data-mdb-input-init class="form-outline">
                                                    <input required type="number" value="${valueRes ne null ? valueRes : ""}" id="lastName" class="form-control form-control-lg" name="value" />
                                                    <label class="form-label" for="lastName">Value</label>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4 d-flex align-items-center">

                                                

                                            </div>
                                            <div class="col-md-6 mb-4">

                                                <div data-mdb-input-init class="form-outline datepicker w-100">
                                                    <input required type="text" value="${desRes ne null ? desRes : ""}" class="form-control form-control-lg" id="birthdayDate" name="description" />
                                                    <label for="birthdayDate" class="form-label">Description</label>
                                                </div>       

                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4 pb-2">

                                                

                                            </div>
                                            <div class="col-md-6 mb-4 pb-2">


                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-6">

                                                

                                            </div>
                                            
                                            <div class="col-6">

                                                <select name="status" class="select form-control-lg">
<!--                                                    <option value="0">Active</option>-->
                                                    <option value="0" ${statusRes eq "0" ? "selected" : ""}>Active</option>
                                                    <option value="1" ${statusRes eq "1" ? "selected" : ""}>Inactive</option>
                                                </select>
                                                <label class="form-label select-label">Status</label>

                                            </div>
                                        </div>

                                        <div class="mt-4 pt-2">
                                            <input data-mdb-ripple-init class="btn btn-primary btn-lg" type="submit" value="Submit" />
                                            <input data-mdb-ripple-init class="btn btn-primary btn-lg submit" style="width: 88px;" onsubmit="false" onclick="location.href='settingList'" value="Back" />
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

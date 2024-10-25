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
        <title>Update User</title>
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
                                    <c:if test="${updateSuccess != null}">
                                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: green">${updateSuccess}</h3>
                                    </c:if>
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Edit User Information</h3>
                                    <form id="updateUser" method="post" onsubmit="return confirmUpdate();">
                                        <c:set value="${User}" var="o"/>
                                        <!-- Avatar Upload Section -->
                                        <div class="row">
                                            <div class="col-md-12 mb-12">
                                                <div data-mdb-input-init class="form-outline" style="text-align: center">

                                                    <img id="avatarPreview" src="${not empty o.image ? o.image : 'images/users/Default_avatar_profile.jpg' }" alt="User Avatar" class="rounded-circle mb-3" style="width: 150px; height: 150px; object-fit: cover;"/>

                                                    <label class="form-label" for="firstName"></label>
                                                </div>
                                            </div>                                                
                                        </div>
                                        <input readonly type="text" id="firstName" style="display: none" name="idUser" class="form-control form-control-lg" value="${o.userId}" />
                                        <!-- Existing form fields -->
                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div data-mdb-input-init class="form-outline">
                                                    <input readonly type="text" id="firstName" class="form-control form-control-lg" value="${o.fullName}" />
                                                    <label class="form-label" for="firstName">Full Name</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div data-mdb-input-init class="form-outline">
                                                    <input readonly type="email" id="lastName" class="form-control form-control-lg" value="${o.email}" />
                                                    <label class="form-label" for="lastName">Email</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4 d-flex align-items-center">
                                                <div data-mdb-input-init class="form-outline datepicker w-100">
                                                    <input readonly type="date" class="form-control form-control-lg" id="birthdayDate" value="${o.dateOfBirth}" />
                                                    <label for="birthdayDate" class="form-label">Birthday</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <h6 class="mb-2 pb-1">Gender: </h6>
                                                <div class="form-check form-check-inline">
                                                    <input disabled class="form-check-input" type="radio" name="inlineRadioOptions" id="femaleGender" ${o.gender == 0 ? 'checked' : ""} />
                                                    <label class="form-check-label" for="femaleGender">Female</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input disabled class="form-check-input" type="radio" name="inlineRadioOptions" id="maleGender" ${o.gender == 1 ? 'checked' : ""}  />
                                                    <label class="form-check-label" for="maleGender">Male</label>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4 pb-2">
                                                <div data-mdb-input-init class="form-outline">
                                                    <input readonly type="text" id="emailAddress" class="form-control form-control-lg" value="${o.phoneNumber}" />
                                                    <label class="form-label" for="emailAddress">Phone Number</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4 pb-2">
                                                <div data-mdb-input-init class="form-outline">
                                                    <input readonly type="text" id="phoneNumber" class="form-control form-control-lg" value="${o.address}" />
                                                    <label class="form-label" for="phoneNumber">Address</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-6">

                                                <select ${o.getRole().getRoleName() eq "Admin" ? "disabled" : ""} required name="role" class="select form-control-lg">
                                                    <c:forEach items="${listRole}" var="i">
                                                        <c:if test="${i.roleId != 5}">
                                                            <option value="${i.roleId}" ${o.role.roleId eq i.roleId ? "selected" : ""} >${i.roleName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <label class="form-label select-label">Role</label>

                                            </div>

                                            <div class="col-6">

                                                <select ${o.getRole().getRoleName() eq "Admin" ? "disabled" : ""} name="status" class="select form-control-lg">
                                                    <option value="0" ${o.deleted == 0 ? 'selected' : ""} >Active</option>
                                                    <option value="1" ${o.deleted == 1 ? 'selected' : ""}>Inactive</option>
                                                </select>
                                                <label class="form-label select-label">Status</label>

                                            </div>
                                        </div>

                                        <div class="mt-4 pt-2">
                                            <input style="display: ${o.getRole().getRoleName() eq "Admin" ? "none" : ""}" data-mdb-ripple-init class="btn btn-primary btn-lg" type="submit" value="Update" />
                                            <input style="width: 100px" data-mdb-ripple-init class="btn btn-primary btn-lg" onsubmit="false" onclick="location.href = 'userList'" value="Back" />
                                        </div>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Footer-->
        <jsp:include page="../../common/sale/footer.jsp"></jsp:include>
        </div>
    </div>







    <script type="text/javascript">
        function confirmUpdate() {
            return confirm("Bạn có chắc chắn muốn cập nhật thông tin này không?");
        }
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
</body>
</html>

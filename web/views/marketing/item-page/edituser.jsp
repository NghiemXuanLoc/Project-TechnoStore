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
    <body class="ec-header-fixed ec-sidebar-fixed  ec-header-light" id="body">
        <!--header-->
        <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
        <jsp:include page="../../common/sale/header.jsp"></jsp:include>
            <div class="wrapper">
                <div class="ec-page-wrapper">
                    <div class=" py-5 h-100">
                        <div class="row justify-content-center align-items-center h-100">
                            <div class="col-12 col-lg-9 col-xl-7 w-100">
                                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                    <div class="card-body p-4 p-md-5">
                                    <c:if test="${updateSuccess != null}">
                                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: green">${updateSuccess}</h3>
                                    </c:if>
                                    <c:if test="${Err != null}">
                                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: red">${Err}</h3>
                                    </c:if>    
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Edit User Information</h3>
                                    <form method="post" onsubmit="return confirmUpdate();">
                                        <c:set value="${User}" var="o"/>
                                        <!-- Avatar Upload Section -->                                     
                                        <input readonly type="text" id="firstName" style="display: none" name="idUser" class="form-control form-control-lg" value="${o.userId}" />
                                        <!-- Existing form fields -->
                                        <div class="row">
                                            <div class="row col-5">
                                                <div class="row">
                                                    <div class="col-6">
                                                        <label class="form-label" for="firstName">Full Name</label>
                                                        <input type="text" name="fullName" required id="firstName" class="form-control form-control-lg" value="${o.fullName}" />
                                                    </div>
                                                    <div class="col-6">
                                                        <h6 class="mb-2 pb-1">Gender: </h6>
                                                        <div class="form-check form-check-inline">
                                                            <input  class="form-check-input" type="radio" name="gender" id="femaleGender" value="0" ${o.gender == 0 ? 'checked' : ""} />
                                                            <label class="form-check-label" for="femaleGender">Female</label>
                                                        </div>
                                                        <div class="form-check form-check-inline">
                                                            <input  class="form-check-input" type="radio" name="gender" value="1"  id="maleGender" ${o.gender == 1 ? 'checked' : ""}  />
                                                            <label class="form-check-label" for="maleGender">Male</label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">

                                                    <div class="col-6">
                                                        <label class="form-label" for="firstName">Email</label>
                                                        <input type="email" readonly="" name="email" id="email" class="form-control form-control-lg" value="${o.email}" />
                                                    </div>

                                                    <div class="col-6">
                                                        <label class="form-label" for="firstName">Phone</label>
                                                        <input type="number" required name="phone" id="phone" class="form-control form-control-lg" value="${o.phoneNumber}" />
                                                    </div>
                                                </div>

                                                <div class="row">

                                                    <div class="col-12">
                                                        <label class="form-label" for="firstName">Address</label>
                                                        <input type="text" name="address" id="email" class="form-control form-control-lg" value="${o.address}" />
                                                    </div>

                                                </div>

                                            </div>
                                            <div class="row col-7">
                                                <h3 style="text-align: center">Update History</h3>
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Email</th>
                                                            <th>Full-name</th>
                                                            <th>Gender</th>
                                                            <th>Mobile</th>
                                                            <th>Address</th>
                                                            <th>Updated by</th>
                                                            <th>Updated date</th>
                                                        </tr>      
                                                    </thead>

                                                    <tbody>
                                                        <c:forEach items="${listMkt}" var="o">  
                                                            <tr>
                                                                <td>${o.email}</td>
                                                                <td>${o.fullName}</td>
                                                                <td>${o.getGender() == 1 ? "Nam" : "Nu"}</td>
                                                                <td>${o.phoneNumber}</td>
                                                                <td>${o.address}</td>
                                                                <td>${o.updateBy}</td>
                                                                <td>${o.updateDate}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <div class="text-center ">

                                                    <ul class="pagination">

                                                        <c:if test="${currenPage > 1}">

                                                            <li class="page-item"><a class="page-link" href="mktuserdetail?page=${(currenPage-1)}&sId=${idUser}">Previous</a></li>
                                                            </c:if>


                                                        <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                            <li class="page-item"><a class="page-link ${i == currenPage ? "active-paging": ""}"  href="mktuserdetail?page=${i}&sId=${idUser}">${i}</a></li>
                                                            </c:forEach>

                                                        <c:if test="${currenPage < num}">
                                                            <li class="page-item"><a class="page-link" href="mktuserdetail?page=${currenPage+1}&sId=${idUser}">Next</a></li>
                                                            </c:if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mt-4 pt-2">
                                            <input style="display: ${o.getRole().getRoleName() eq "Admin" ? "none" : ""}" data-mdb-ripple-init class="btn btn-primary btn-lg" type="submit" value="Update" />
                                            <input style="width: 100px" data-mdb-ripple-init class="btn btn-primary btn-lg" onsubmit="false" onclick="location.href = 'mktuserlist'" value="Back" />
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

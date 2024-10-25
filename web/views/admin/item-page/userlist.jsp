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

        <title>User List</title>

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


                <c:if test="${not empty sessionScope.updateSuccess}">
                    <div class="alert alert-success">
                        <strong>Success!</strong> ${updateSuccess}.
                    </div>
                    <% session.removeAttribute("updateSuccess"); %>
                </c:if> 
                <!-- CONTENT WRAPPER ITER2 --> 
                <div class="ec-content-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper breadcrumb-contacts">

                            <div class="addUserButton">
                                <form action="addUser" method="get">
                                    <button type="submit" class="btn btn-info">Add User</button>
                                </form>
                            </div>
                        </div>

                        <div class="select_option row">
                            <!--khong fillter-->
                            <table class="col-md-6">                             
                                <tr>
                                    <td class="header_row" >Filter by gender</td>
                                    <td class="header_row" >Filter by role</td>
                                    <td class="header_row" >Filter by status</td>
                                    <td class="header_row" >Sort by</td>
                                    <td class="header_row" >Filter more</td>
                                </tr>

                                <!--filter theo gender-->
                                <tr>
                                    <td>

                                        <form action="userList" method="post">
                                            <select name="filterByGender" onchange="this.form.submit()">
                                                <option value="2">None</option> 
                                                <option value="1" ${Nam eq "1" ? "selected" : ""}> Nam</option> 
                                                <option value="0" ${Nu eq "0"? "selected" : ""}> Nu</option>   
                                            </select>    
                                        </form>
                                    </td>

                                    <!--filter theo role-->
                                    <td>                                                                        
                                        <form action="userList" method="post">
                                            <select name="filterByRole" onchange="this.form.submit()">
                                                <option value="All">None</option>
                                                <c:forEach items="${listRole}" var="i">
                                                    <c:if test="${i.getRoleName() != 'Anonymous'}">
                                                        <option value="${i.getRoleName()}" ${roleNameResponse eq i.getRoleName() ? "selected" : ""}>
                                                            ${i.getRoleName()}
                                                        </option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>   
                                        </form>
                                    </td>


                                    <!--filter theo status-->
                                    <td>                            
                                        <form action="userList" method="post">
                                            <select name="filterByStatus" onchange="this.form.submit()">
                                                <option value="2">None</option>
                                                <option value="1" ${statusResponse eq "1" ? "selected" : ""} >Inactive</option>
                                                <option value="0" ${statusResponse eq "0" ? "selected" : ""}>Active</option>
                                            </select>
                                        </form>
                                    </td>

                                    <td>
                                        <!--sort by something-->
                                        <form action="userList" method="post">
                                            <select name="sort" onchange="this.form.submit()">
                                                <option value="allsort" >None</option>
                                                <option value="idSort" ${sortResponse eq "1" ? "selected" : ""}>id</option>
                                                <option value="fullnameSort" ${sortResponse eq "2" ? "selected" : ""}>fullname</option>
                                                <option value="genderSort" ${sortResponse eq "3" ? "selected" : ""}>gender</option>
                                                <option value="emailSort" ${sortResponse eq "4" ? "selected" : ""}>email</option>
                                                <option value="mobileSort" ${sortResponse eq "5" ? "selected" : ""}>mobile</option>
                                                <option value="roleSort" ${sortResponse eq "6" ? "selected" : ""}>role</option>
                                                <option value="statusSort" ${sortResponse eq "7" ? "selected" : ""}>status</option>
                                            </select>
                                        </form>
                                    </td>

                                    <td>
                                        <!--filterMore-->
                                        <form action="userList" method="post">
                                            <select name="filterMore" onchange="this.form.submit()">
                                                <option value="allNewUser" >None</option>
                                                <option value="newUserThisMoth" ${newUserResponse eq "newUserResponse" ? "selected" : ""}>New user this month</option>
                                                <option value="newUserInactive" ${newUserResponse eq "newUserInactiveResponse" ? "selected" : ""}>New user Inactive</option>
                                                <option value="newUserNewUpdate" ${newUserResponse eq "newUserUpdate" ? "selected" : ""}>New user new update</option>

                                            </select>
                                        </form>
                                    </td>


                                </tr>


                            </table>

                            <div class="col-md-6 ">
                                <form class="search_bySomeThing" action="userList" method="post">
                                    <input class="search_input" name="search" placeholder="Search by Name, Email, Phone."><!-- comment -->
                                    <input type="submit" value="Search" />
                                </form>
                            </div>



                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="ec-vendor-list card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Name</th>
                                                        <th>Gender</th>
                                                        <th>Email</th>
                                                        <th>Mobile</th>
                                                        <th>Role</th>
                                                        <th>Create at</th>
                                                        <th>Update at</th>
                                                        <th>Status</th>
                                                        <th>Action</th>
                                                    </tr>

                                                    <c:forEach items="${listUser}" var="u">
                                                        <c:if test="${u.getRole().getRoleName() != 'Anonymous'}">
                                                            <tr>
                                                                <td>${u.getUserId()}</td>
                                                                <td>${u.getFullName()}</td>
                                                                <td>${u.getGender() == 1 ? "Nam" : "Nu"}</td>
                                                                <td>${u.getEmail()}</td>
                                                                <td>${u.getPhoneNumber()}</td>
                                                                <td>${u.getRole().getRoleName()}</td>
                                                                <td>${u.getCreatedAt()}</td>
                                                                <td>${u.getUpdatedAt() eq null ? "None" : u.getUpdatedAt()}</td>
                                                                <td>${u.getDeleted() eq "1" ? "Inactive" : "Active"}</td>
                                                                <td><a style="margin-right: 20px" href="updateUser?id=${u.getUserId()}">View and Edit</a></td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>

                                                </thead>

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
                                    <li class="page-item"><a class="page-link" href="userList?page=${(currenPage-1)}">Previous</a></li>
                                    </c:if>


                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                    <li class="page-item"><a class="page-link ${i == currenPage ? "active-paging": ""}"  href="userList?page=${i}">${i}</a></li>
                                    </c:forEach>

                                <c:if test="${currenPage < num}">
                                    <li class="page-item"><a class="page-link" href="userList?page=${currenPage+1}">Next</a></li>
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

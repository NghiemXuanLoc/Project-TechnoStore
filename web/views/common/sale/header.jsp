<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css">-->
        <!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style.css">-->

        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>

        <style>

            .rounded-input {
                border-radius: 10px;
                padding: 10px;
                width: 500px;
            }
        </style>
    </style>


</head>
<body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light sidebar-minified" id="body">
    <header class="ec-main-header" id="header">
        <nav class="navbar navbar-static-top navbar-expand-lg">
            <!-- Sidebar toggle button -->
            <button id="sidebar-toggler" class="sidebar-toggle"></button>
            <!-- search form -->
            <div class="search-form d-lg-inline-block">
                <!--                    <div class="input-group">
                                        <input type="text" name="query" id="search-input" class="form-control"
                                               placeholder="search.." autofocus autocomplete="off" />
                                        <button type="button" name="search" id="search-btn" class="btn btn-flat">
                                            <i class="mdi mdi-magnify"></i>
                                        </button>
                                    </div>
                                    <div id="search-results-container">
                                        <ul id="search-results"></ul>
                                    </div>-->
            </div>

            <!-- navbar right -->
            <div class="navbar-right">
                <ul class="nav navbar-nav">
                    <!-- User Account -->
                    <li class="dropdown user-menu">
                        <button class="dropdown-toggle nav-link ec-drop" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            <span>Profile</span>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-right ec-dropdown-menu">
                            <!-- User image -->
                            <li class="dropdown-header">
                                <div class="d-inline-block">


                                    <small class="pt-1" ><span style="font-weight:bold">FullName:</span >${userprofile.fullName}</small>
                                    <small class="pt-1"><span style="font-weight:bold">Email:</span >${userprofile.email}</small>
                                </div>
                            </li>
                            <li>
                                <!--<a href="#" style="cursor: pointer" id="openModalBtn" title="">Change Password</a>-->
                                <p   id="openModalBtn" class="Changepassword_text" >Change password</p>
                            </li>
                            <li>
                                <!--<a href="#" style="cursor: pointer" id="openModalBtn" title="">Change Password</a>-->
                                <p> <br></p>
                            </li>
                            <li>
                                <!--<a href="#" style="cursor: pointer" id="openModalBtn" title="">Change Password</a>-->
                                <p   id="openModalBtn-user" class="Changepassword_text" >My Profile</p>
                            </li>


                            <li class="dropdown-footer">
                                <a href="logout"> <i class="mdi mdi-logout"></i> Log Out </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

        </nav>
    </header>
    <div class="modal modal_all" id="password_modal">
        <div class="Form-changepassword">
            <!--thay doi style-->
            <div class="modal-header modal_headerr" > 
                <h3 >Change Password <span class="extra-title muted"></span></h3>
                <button id="close-changePassword" class="btn " data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>



            <form action="changepasswordotherrole" method="post">
                <div class="modal-body form-horizontal">
                    <p id="Error_changepassword" class="error_changepassword"></p>
                    <div class="control-group">
                        <label for="current_password" class="control-label">Current Password</label>   
                        <div class="controls">
                            <input style="width: 500px" type="password" required  name="current_password" class="box_password box1 rounded-input" id="box1" placeholder="Input your current password">                    
                            <input type="checkbox" class="showPassword ml-2"    id="showPassword1"/>
                            <label  class="showPass mt-2"  >Show</label>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="new_password" class="control-label" >New Password</label>   
                        <div class="controls">
                            <input type="password" style="width: 500px" required  name="new_password" class="box_password new_password rounded-input " id="box2" placeholder="Must be at least 6 and at most 14 characters">
                            <input type="checkbox" class="showPassword ml-2"  id="showPassword2"/>
                            <label class="showPass mt-2"  >Show</label>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="confirm_password" class="control-label">Confirm Password</label>                   
                        <div class="controls">
                            <input type="password" style="width: 500px" required  name="confirm_password" class="box_password rounded-input" id="box3" placeholder="Confirm password">
                            <input type="checkbox" class="showPassword ml-2" id="showPassword3"/>
                            <label class="showPass mt-2"  >Show</label>
                        </div>
                    </div>
                    <c:if test="${not empty sessionScope.errCurrentPassword}">
                        <h5 style="color: red">${sessionScope.errCurrentPassword}</h5>
                    </c:if>
                    <c:if test="${not empty sessionScope.errNewPasswordLength}">
                        <h5 style="color: red">${sessionScope.errNewPasswordLength}</h5>
                    </c:if>
                    <c:if test="${not empty sessionScope.errConfirmPass}">
                        <h5 style="color: red">${sessionScope.errConfirmPass}</h5>
                    </c:if>
                    <c:if test="${not empty sessionScope.mess}">
                        <h5 style="color: red">${sessionScope.mess}</h5>
                    </c:if>


                </div>
                <div class="modal-footer">               
                    <button class="btn btn-primary" id="password_modal_save">Save changes</button>
                </div>
            </form>
        </div>
    </div> 

    <div class="modal" id="user-profile">

        <div class="Form-userprofile">

            <h3>User Profile</h3>         
            <!-- Handle errors -->
            <c:if test="${not empty sessionScope.errName}">
                <h3 style="color: red">${sessionScope.errName}</h3>
                <script>
                    var modal = document.getElementById("user-profile");
                    modal.style.display = 'block';
                    
                </script>
                <% session.removeAttribute("errName"); %>
            </c:if>
            <c:if test="${not empty sessionScope.errPhone}">
                <h3 style="color: red">${sessionScope.errPhone}</h3>
                <script>
                    var modal = document.getElementById("user-profile");
                    modal.style.display = 'block';
                    
                </script>
                <% session.removeAttribute("errPhone"); %>
            </c:if>
            <c:if test="${not empty sessionScope.errAddress}">
                <h3 style="color: red">${sessionScope.errAddress}</h3>
                <script>
                    var modal = document.getElementById("user-profile");
                    modal.style.display = 'block';
                    
                </script>
                <% session.removeAttribute("errAddress"); %>
            </c:if>
            <c:if test="${not empty sessionScope.errDob}">
                <h3 style="color: red">${sessionScope.errDob}</h3>
                <script>
                    var modal = document.getElementById("user-profile");
                    modal.style.display = 'block';
                </script>
                <% session.removeAttribute("errDob"); %>
            </c:if>     
            <c:if test="${not empty sessionScope.Sucess}">
                <h3 style="color: green">${sessionScope.Sucess}</h3>
                <script>
                    var modal = document.getElementById("user-profile");
                    modal.style.display = 'block';
                </script>
                <% session.removeAttribute("Sucess"); %>
            </c:if>           
            <button  id="close-profile" style="margin-left: 90%" class="btn Close-Profile" data-dismiss="modal" aria-hidden="true">&times;</button>
            <div class="row">

                <form action="updateProfileForOtherRole" method="post" class="form_UserProfile" onsubmit="return confirmUpdateProfile();" enctype="multipart/form-data" >
                    <c:set var="o" value="${userProfileForAdmin}"/>
                    <div class="col-md-4 avatar_cover " >

                        <img  class="rounded-circle mb-3" style="width: 150px; height: 150px; object-fit: cover;"  src="${not empty o.image ? o.image : 'images/users/Default_avatar_profile.jpg' }" alt="Avatar"/>
                        <input style="width: 100px" type="file"   name="imageProfile" accept="image/jpeg ">
                    </div>

                    <div class="col-md-8">

                        <div class="form-group">
                            <label for="email">Full Name:</label>
                            <input required type="text"  class="form-control" id="email" name="fullName" value="${o.fullName}">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input required type="email" class="form-control" id="email" name="email" value="${o.email}" readonly>
                            <p>(You cannot change your email )</p>
                        </div>                    
                        <div class="form-group">
                            <label for="pwd">Phone:</label>
                            <input  type="text" class="form-control" id="pwd" name="Phone" value="${o.phoneNumber}">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Gender:</label>
                            <label for="male">Male</label>
                            <input type="radio" name="gender" id="male" value="male"  ${o.gender == 1 ? "checked" : ""} >  
                            <label>       </label>                   
                            <label for="female">Female</label>
                            <input type="radio" name="gender" id="female" value="female"  ${o.gender == 0 ? "checked" : ""}>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Address:</label>
                            <input  type="text" class="form-control" id="pwd" name="address" value="${o.address}">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Date of birth:</label>
                            <input  type="date" class="form-control" id="pwd" name="dateofbirth" value="${o.dateOfBirth}">
                        </div>
                        <button type="submit" style="background-color: blanchedalmond" class="btn btn-default submitprofile">Submit</button>
                    </div>
                </form>                                          
            </div>

        </div>
    </div>
    <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>

    <script>

        <c:if test="${not empty sessionScope.errCurrentPassword || not empty sessionScope.errNewPasswordLength || not empty sessionScope.errConfirmPass || not empty sessionScope.mess}">
                    document.getElementById('password_modal').style.display = 'block';
        </c:if>

                    document.querySelectorAll('.showPassword').forEach(function (checkbox) {
                        checkbox.addEventListener('click', function () {
                            const passwordField = this.previousElementSibling;
                            if (this.checked) {
                                passwordField.type = 'text';
                            } else {
                                passwordField.type = 'password';
                            }
                        });
                    });
    </script>
    <script type="text/javascript">
        function confirmUpdateProfile() {
            return confirm("Bạn có chắc chắn muốn cập nhật thông tin này không?");
        }
    </script> 

</body>
</html>

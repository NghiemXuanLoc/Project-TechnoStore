<% String token = request.getParameter("token"); %>
<%-- 
    Document   : productlist
    Created on : Jun 6, 2024, 7:53:42 AM
    Author     : PC
--%>

<%@page import="model.Memory"%>
<%@page import="model.Color"%>
<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <!-- Mirrored from creativelayers.net/themes/techno-html/shop.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:08:11 GMT -->
    <head>
        <!-- Basic Page Needs -->
        <meta charset="UTF-8">
            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>Techno Store - Shop Bar Left</title>

            <meta name="author" content="CreativeLayers">

                <!-- Mobile Specific Metas -->
                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

                    <!-- Boostrap style -->
                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap.min.css">

                        <!-- Theme style -->
                        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style.css">

                            <!-- Reponsive -->
                            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/responsive.css">

                                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">

                                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-list.css">

                                        <link
                                            href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.3/nouislider.min.css"
                                            rel="stylesheet"
                                            />

                                        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">
                                            <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>
                                            <style>
                                                .centered-container {
                                                    display: flex;
                                                    justify-content: center;
                                                    align-items: center;
                                                    flex-direction: column;
                                                }
                                            </style>
                                            </head>
                                            <body class="header_sticky">
                                                <div class="boxed">

                                                    <div class="overlay"></div>

                                                    <!-- Preloader -->
                                                    <div class="preloader">
                                                        <div class="clear-loading loading-effect-2">
                                                            <span></span>
                                                        </div>
                                                    </div><!-- /.preloader -->



                                                    <jsp:include page="../../common/user/header.jsp"></jsp:include>
                                                        <div class="centered-container">
                                                            <h2>Reset Password</h2>
                                                            <!--                                                            <form class="w-50" action="/TechStore/updatepassword" method="post" onsubmit="return validatePasswords()">
                                                                                                                            <input type="hidden" name="token" value="<%= token %>">
                                                                                                                            <div class="form-group">
                                                                                                                                <div class="form-group">
                                                                                                                                    <label for="password">New Password:</label>
                                                                                                                                    <input class="form-control" type="password" id="password" name="password" required>
                                                                                                                                </div>
                                                                                                                                <div class="form-group">
                                                                                                                                    <label for="confirmPassword">Confirm New Password:</label>
                                                                                                                                    <input class="form-control" type="password" id="confirmPassword" name="confirmPassword" required>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                            <div>
                                                                                                                                <p id="errorMessage" style="color: red;"><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %></p>
                                                                                                                            </div>
                                                                                                                            <div>
                                                                                                                                <button style="border-radius: 40px"  type="submit" class="btn btn-sm btn-danger">Reset Password</button>
                                                                                                                            </div>
                                                                                                                    </form>-->
                                                        <form class="w-50" action="/TechStore/updatepassword" method="post">
                                                            <input type="hidden" name="token" value="<%= token %>">
                                                                <div class="form-group">
                                                                    <label for="password">New Password:</label>
                                                                    <input class="form-control" type="password" id="password" name="password" required>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="confirmPassword">Confirm New Password:</label>
                                                                    <input class="form-control" type="password" id="confirmPassword" name="confirmPassword" required>
                                                                </div>
                                                                <div>
                                                                    <p id="errorMessage" style="color: red;">
                                                                        <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
                                                                    </p>
                                                                </div>
                                                                <div>
                                                                    <button style="border-radius: 40px" type="submit" class="btn btn-sm btn-danger">Reset Password</button>
                                                                </div>
                                                        </form>

                                                    </div>

                                                    <jsp:include page="../../common/user/footer.jsp"></jsp:include>
                                                        <!--                                                        <script>
                                                        //                                                            function validatePasswords() {
                                                        //                                                                var password = document.getElementById("password").value;
                                                        //                                                                var confirmPassword = document.getElementById("confirmPassword").value;
                                                        //                                                                console.log(password);
                                                        //                                                                console.log(confirmPassword);
                                                        //                                                                if (password !== confirmPassword) {
                                                        //                                                                    document.getElementById("errorMessage").innerText = "Passwords do not match";
                                                        //                                                                    return false;
                                                        //                                                                }
                                                        //                                                                return true;
                                                        //                                                            }
                                                                                                             
                                                                                                                </script>-->

                                                        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/tether.min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/waypoints.min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.circlechart.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/easing.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.zoom.min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.flexslider-min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/owl.carousel.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/smoothscroll.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-ui.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.mCustomScrollbar.js"></script>
                                                    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBtRmXKclfDp20TvfQnpgXSDPjut14x5wk&amp;region=GB"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gmap3.min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/waves.min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.countdown.js"></script>

                                                    <script src="https://kit.fontawesome.com/cbc13c02eb.js" crossorigin="anonymous"></script>
                                                    <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.3/nouislider.min.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/main.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>
                                                    <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-list.js"></script>
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/slider.js"></script>
                                                    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

                                            </body>
                                            </html>

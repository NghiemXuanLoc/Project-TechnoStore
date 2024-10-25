<%-- 
    Document   : homepage.jsp
    Created on : May 14, 2024, 10:55:13 PM
    Author     : PC
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <head>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            /* Move close button to the right within modal footer */
            .modal-header{
                justify-content: flex-end;
            }
            /* Style for smaller register modal */
            #registerModal .modal-dialog {
                max-width: 500px; /* Adjust this value to make the modal smaller or larger */
                width: 100%;
            }

            #registerModal .modal-body {
                padding: 20px; /* Adjust the padding as needed */
            }
        </style>
        <!-- Basic Page Needs -->
        <meta charset="UTF-8">
            <title>Techno Store - Home Page</title>

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

                                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/font-awesome.css">

                                        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-slider.css">

                                            <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>
                                            <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">

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

                                                        <div class="toast-container" id="toast-container"></div>


                                                        <%--<jsp:include page="../../common/user/popupwelcome.jsp"></jsp:include>--%>

                                                        <jsp:include page="../../common/user/header.jsp"></jsp:include>
                                                        <c:if test="${not empty sessionScope.failPath}">
                                                            <div class="alert alert-warning" style="text-align: center">
                                                                <strong>Warning!</strong> ${failPath}.
                                                            </div>
                                                            <% session.removeAttribute("failPath"); %>
                                                        </c:if>

                                                        <c:if test="${not empty sessionScope.failPath}">
                                                            <div class="alert alert-danger" style="text-align: center">
                                                                <strong>Warning!</strong> ${failPath}.
                                                            </div>
                                                            <% session.removeAttribute("failPath"); %>
                                                        </c:if>

                                                        <c:if test="${not empty sessionScope.VerifySuccess}">
                                                            <div class="alert alert-success" style="text-align: center">
                                                                <strong>Success</strong> ${VerifySuccess}.
                                                            </div>
                                                            <% session.removeAttribute("VerifySuccess"); %>
                                                        </c:if>

                                                        <jsp:include page="../../common/user/slider.jsp"></jsp:include>
                                                            <h2 style="text-align: center; padding: 10px 0px;"></h2>


                                                        <%--<jsp:include page="../../common/user/banner.jsp"></jsp:include>--%>

                                                        <jsp:include page="../../common/user/bestsellersfeaturedhotsale.jsp"></jsp:include>


                                                        <%--<jsp:include page="../../common/user/newarrivals.jsp"></jsp:include>--%>


                                                        <%--<jsp:include page="../../common/user/outproduct.jsp"></jsp:include>--%>

                                                        <h2 style="text-align: center; padding: 10px 0px;">New Product</h2>

                                                        <jsp:include page="../../common/user/newproduct.jsp"></jsp:include>


                                                        <%--<jsp:include page="../../common/user/sales.jsp"></jsp:include>--%>


                                                        <jsp:include page="../../common/user/lastpost.jsp"></jsp:include>




                                                        <%--<jsp:include page="../../common/user/service.jsp"></jsp:include>--%>
                                                        <jsp:include page="../../common/user/popupaddproductforcompare.jsp"></jsp:include>

                                                        <jsp:include page="../../common/user/popopcompare.jsp"></jsp:include>

                                                            <div id="pop-compare-detail" class="pop-compare-detail">

                                                            </div>
                                                        <jsp:include page="../../common/user/footer.jsp"></jsp:include>


                                                        </div><!-- /.boxed -->

                                                    <!-- Javascript -->
                                                 <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.min.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/tether.min.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.min.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/waypoints.min.js"></script>
                                                <!-- <script type="text/javascript" src="javascript/jquery.circlechart.js"></script> -->
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/easing.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.zoom.min.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.flexslider-min.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/owl.carousel.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/smoothscroll.js"></script>
                                                <!-- <script type="text/javascript" src="javascript/jquery-ui.js"></script> -->
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.mCustomScrollbar.js"></script>
                                                <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBtRmXKclfDp20TvfQnpgXSDPjut14x5wk&amp;region=GB"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gmap3.min.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/waves.min.js"></script> 
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.countdown.js"></script>
                                                <script src="https://kit.fontawesome.com/cbc13c02eb.js" crossorigin="anonymous"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/main.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>
                                                <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/slider.js"></script>
                                                <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>
                                            </body>	

                                                </html>
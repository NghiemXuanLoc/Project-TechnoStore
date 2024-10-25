<%-- 
    Document   : page404
    Created on : Jul 2, 2024, 8:49:57 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <!-- Mirrored from creativelayers.net/themes/techno-html/404.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:08:20 GMT -->
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



                                                        <jsp:include page="../../common/user/header.jsp"></jsp:include>


                                                            <section class="flat-error">
                                                                <div class="container">
                                                                    <div class="row">
                                                                        <div class="col-md-2">
                                                                        </div><!-- /.col-md-2 -->
                                                                        <div class="col-md-8">
                                                                            <div class="wrap-error center">
                                                                                <div class="header-error">
                                                                                    <img src="${pageContext.request.contextPath}/images/error/error.png" alt="">
                                                                                        <h1>Sorry but we couldn’t find the page you are looking for.</h1>
                                                                                </div><!-- /.header-error -->
                                                                            </div><!-- /.wrap-error -->
                                                                        </div><!-- /.col-md-8 -->
                                                                        <div class="col-md-2">
                                                                        </div><!-- /.col-md-2 -->
                                                                    </div><!-- /.row -->
                                                                </div><!-- /.container -->
                                                            </section><!-- /.flat-error -->

                                                        <jsp:include page="../../common/user/footer.jsp"></jsp:include>

                                                        </div><!-- /.boxed -->

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

                                                <!-- Mirrored from creativelayers.net/themes/techno-html/404.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:08:21 GMT -->
                                                </html>
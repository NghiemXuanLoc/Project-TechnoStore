
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
            <title>Techno Store - Register</title>

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
                                                            <h1>We have sent a verification code to your email.</h1>
                                                            <p id="countdown">Time remaining: </p>


                                                            <form class="w-50" action="/TechStore/VerifyCode" method="post" id="verifyForm">
                                                                <label for="authcode">Enter the verification code:</label>
                                                                <input type="text" name="authcode" id="authcodeInput" required>
                                                                    <input type="submit" value="Verify" id="verifyButton">
                                                                        </form>
                                                                        <div id="errorcode">
                                                                        <c:if test="${not empty errorMessage}">
                                                                            <h3 style="color: red">${errorMessage}</h3>
                                                                        </c:if>
                                                                    </div>
                                                                    <div id="backToHomepage" class="mt-3">
                                                                        <a href="/TechStore/invalidateSession" class="btn btn-danger">Back to Homepage</a>
                                                                    </div>
                                                                    </div>  
                                                                    <jsp:include page="../../common/user/footer.jsp"></jsp:include>
                                                                     </body>
                                                                        <script>
                                                                            // Thời gian hết hạn (trong milliseconds)
                                                                        var expirationTime = ${sessionScope.verificationTime} + 30000; // Thời gian hết hạn là 1 phút sau thời điểm gửi

                                                                            // Cập nhật hiển thị thời gian còn lại
                                                                            var countdownElement = document.getElementById("countdown");
                                                                            var verifyForm = document.getElementById("verifyForm");
                                                                            var backToHomepage = document.getElementById("backToHomepage");
                                                                            var errorcode = document.getElementById("errorcode");
                                                                            function updateCountdown() {
                                                                                var currentTime = new Date().getTime();
                                                                                var timeRemaining = expirationTime - currentTime;

                                                                                if (timeRemaining <= 0) {
                                                                                    // Thời gian đã hết, ngăn chặn nhập mã xác nhận và dừng đếm ngược
                                                                                    clearInterval(timer);
                                                                                    errorcode.style.display = "none";
                                                                                    verifyForm.style.display = "none"; // Ẩn form nhập mã xác nhận
                                                                                    countdownElement.textContent = "Time expired";
                                                                                    backToHomepage.style.display = "block"; // Hiển thị nút "Back to Homepage"
                                                                                    return;
                                                                                }

                                                                                var seconds = Math.floor((timeRemaining % (1000 * 60)) / 1000);
                                                                                countdownElement.textContent = "Time remaining: " + seconds + "s";
                                                                            }

                                                                            var timer = setInterval(updateCountdown, 1000); // Cập nhật mỗi giây
                                                                            updateCountdown(); // Cập nhật ban đầu
                                                                    </script>
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

                                                                   
                                                                    </html>

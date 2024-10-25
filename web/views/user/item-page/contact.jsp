<%-- 
    Document   : contact
    Created on : Jul 12, 2024, 2:15:13 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]-->

    <!-- Mirrored from creativelayers.net/themes/techno-html/contact-v2.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:10:06 GMT -->
    <head>
        <!-- Basic Page Needs -->
        <meta charset="UTF-8">
            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>Techno Store - Contact 02</title>

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

                                                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-feedback.css">

                                                    </head>
                                                    <body class="header_sticky">
                                                        <div class="boxed">

                                                            <div class="overlay"></div>

                                                            <jsp:include page="../../common/user/header.jsp"></jsp:include>

                                                                <!-- Preloader -->
                                                                <div class="preloader">
                                                                    <div class="clear-loading loading-effect-2">
                                                                        <span></span>
                                                                    </div>
                                                                </div><!-- /.preloader -->


                                                                <section class="flat-breadcrumb">
                                                                    <div class="container">
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <ul class="breadcrumbs">
                                                                                    <li class="trail-item">
                                                                                        <a href="#" title="">Home</a>
                                                                                        <span><img src="images/icons/arrow-right.png" alt=""></span>
                                                                                    </li>
                                                                                    
                                                                                    <li class="trail-end">
                                                                                        <a href="javascript:void(0)" title="">Contact</a>
                                                                                    </li>
                                                                                </ul><!-- /.breacrumbs -->
                                                                            </div><!-- /.col-md-12 -->
                                                                        </div><!-- /.row -->
                                                                    </div><!-- /.container -->
                                                                </section><!-- /.flat-breadcrumb -->

                                                                <section class="flat-map">
                                                                    <div class="container">
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <div id="flat-map" class="pdmap">
                                                                                    <div class="flat-maps" data-address="Quận Smith, Mississippi" data-height="444" data-images="images/icons/map.png" data-name="Themesflat Map"></div>
                                                                                    <div class="gm-map">                
                                                                                        <iframe style="border-radius: 15px" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d119184.2029266861!2d105.38521351640624!3d21.012416700000003!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2sFPT%20University!5e0!3m2!1sen!2s!4v1720725916878!5m2!1sen!2s" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>                       
                                                                                    </div>
                                                                                </div><!-- /#flat-map -->
                                                                            </div><!-- /.col-md-12 -->
                                                                        </div><!-- /.row -->
                                                                    </div><!-- /.container -->
                                                                </section><!-- /#flat-map -->

                                                                <section class="flat-contact style2">
                                                                    <div class="container">
                                                                        <div class="row">
                                                                            <div class="col-md-7">
                                                                            <c:if test="${requestScope.success == null}">
                                                                                <div class="form-contact left">
                                                                                    <div class="form-contact-header">
                                                                                        <h3>Leave us a Message</h3>

                                                                                    </div><!-- /.form-contact-header -->
                                                                                    <div class="form-contact-content">
                                                                                        <form onsubmit="handleSubmitFeedback(event)" action="${pageContext.request.contextPath}/contact" method="post" id="form-contact" accept-charset="utf-8">
                                                                                            <div class="form-box one-half name-contact">
                                                                                                <label for="name-contact">FullName</label>
                                                                                                <input type="text" id="fullName" name="fullName" value="${sessionScope.account != null ? sessionScope.account.fullName : ''}">
                                                                                            </div>
                                                                                            <div class="form-box one-half password-contact">
                                                                                                <label for="password-contact">PhoneNumber</label>
                                                                                                <input type="text" id="phoneNumber" name="phoneNumber" value="${sessionScope.account != null ? sessionScope.account.phoneNumber : ''}">
                                                                                            </div>

                                                                                            <div class="form-box one-half name-contact">
                                                                                                <label for="name-contact">Gender</label>
                                                                                                <div class="d-flex justify-content-around">

                                                                                                    <div class="d-flex form-group">
                                                                                                        <input id="male" type="radio" name="gender" value="0" ${sessionScope.account != null ? 'checked' : ''}/> 
                                                                                                        <label for="male">Male</label>
                                                                                                    </div>

                                                                                                    <div class="d-flex form-group">
                                                                                                        <input id="female" type="radio" name="gender" value="1" /> 
                                                                                                        <label for="female">Female</label>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-box one-half password-contact">
                                                                                                <label for="password-contact">Email</label>
                                                                                                <input type="text" id="emailFeedback" name="email" value="${sessionScope.account != null ? sessionScope.account.email : ''}">
                                                                                            </div>

                                                                                            <div>
                                                                                                <div class="star-rating" style="margin-left: 37%">
                                                                                                    <input type="radio" id="star5" name="rating" value="5"  /><label
                                                                                                        for="star5"
                                                                                                        title="5 stars"
                                                                                                        >★</label
                                                                                                    >
                                                                                                    <input type="radio" id="star4" name="rating" value="4" /><label
                                                                                                        for="star4"
                                                                                                        title="4 stars"
                                                                                                        >★</label
                                                                                                    >
                                                                                                    <input type="radio" id="star3" name="rating" value="3"  /><label
                                                                                                        for="star3"
                                                                                                        title="3 stars"
                                                                                                        >★</label
                                                                                                    >
                                                                                                    <input type="radio" id="star2" name="rating" value="2"  /><label
                                                                                                        for="star2"
                                                                                                        title="2 stars"
                                                                                                        >★</label
                                                                                                    >
                                                                                                    <input type="radio" id="star1" name="rating" value="1" /><label
                                                                                                        for="star1"
                                                                                                        title="1 star"
                                                                                                        >★</label
                                                                                                    >
                                                                                                </div>
                                                                                            </div>


                                                                                            <div class="form-box">
                                                                                                <label for="subject-contact">Subject</label>
                                                                                                <input type="text" id="subject" name="subject" >
                                                                                            </div>
                                                                                            <div class="form-box">
                                                                                                <label for="comment-contact">Comment</label>
                                                                                                <textarea id="content" name="content"></textarea>
                                                                                            </div>
                                                                                            <div class="form-box">
                                                                                                <button type="submit" class="contact">Send</button>
                                                                                            </div>
                                                                                        </form><!-- /#form-contact -->
                                                                                    </div><!-- /.form-contact-content -->
                                                                                </div><!-- /.form-contact left -->
                                                                            </c:if> 
                                                                                
                                                                            <c:if test="${requestScope.success != null}">
                                                                                <h3 class="text-success mt-5">${requestScope.success}</h3>
                                                                                
                                                                                <div>
                                                                                    <p>Subject: ${requestScope.subject}</p>
                                                                                    <p>Content: ${requestScope.content}</p>
                                                                                    <p>Rating: ${requestScope.rating}</p>
                                                                                </div>
                                                                            </c:if>
                                                                        </div><!-- /.col-md-7 -->
                                                                        <div class="col-md-5">
                                                                            <div class="box-contact">
                                                                                <ul>
                                                                                    <li class="address">
                                                                                        <h3>Address</h3>
                                                                                        <p>
                                                                                            Bình Yên Thạch Thất Hà Nội <br />Vệt Nam
                                                                                        </p>
                                                                                    </li>
                                                                                    <li class="phone">
                                                                                        <h3>Phone</h3>
                                                                                        <p>
                                                                                            0337783926
                                                                                        </p>
                                                                                    </li>
                                                                                    <li class="email">
                                                                                        <h3>Email</h3>
                                                                                        <p>
                                                                                            nghiemxuanloc02@gmail.com
                                                                                        </p>
                                                                                    </li>
                                                                                    <li class="address">
                                                                                        <h3>Opening Hours</h3>
                                                                                        <p>
                                                                                            Thứ 2 tới Thứ 6: 10 giờ sáng tới 6h chiều
                                                                                        </p>
                                                                                        <p>
                                                                                            Thứ bảy: 10 giờ sáng tới 4 giờ chiều
                                                                                        </p>
                                                                                        <p>
                                                                                            Chủ nhật: 11 giờ sáng tới 4 giờ chiều
                                                                                        </p>
                                                                                    </li>
                                                                                    <li>
                                                                                        <h3>Follow Us</h3>
                                                                                    </li>
                                                                                </ul>
                                                                            </div><!-- /.box-contact -->
                                                                        </div><!-- /.col-md-5 -->
                                                                    </div><!-- /.row -->
                                                                </div><!-- /.container -->
                                                            </section><!-- /.flat-contact style2 -->

                                                            <jsp:include page="../../common/user/footer.jsp"></jsp:include>
                                                            </div><!-- /.boxed -->


                                                            <script>
                                                                function validateEmail(email) {
                                                                    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                                                                    return emailRegex.test(String(email).toLowerCase());
                                                                }

                                                                function validateForm() {
                                                                    const fullName = document.getElementById('fullName').value.trim();
                                                                    const email = document.getElementById('emailFeedback').value.trim();
                                                                    const phoneNumber = document.getElementById('phoneNumber').value.trim();
                                                                    const subject = document.getElementById('subject').value.trim();
                                                                    const content = document.getElementById('content').value.trim();
                                                                    const rating = document.querySelector('input[name="rating"]:checked');
                                                                    let isValid = true;

                                                                    if (fullName === "") {
                                                                        alert("Full Name is required.");
                                                                        isValid = false;
                                                                    } else

                                                                    if (phoneNumber === "" || !/^0\d{9}$/.test(phoneNumber)) {
                                                                        alert("A valid Phone Number is required.");
                                                                        isValid = false;
                                                                    } else
                                                                    if (email === "" || !validateEmail(email)) {
                                                                        alert("A valid Email is required.");
                                                                        isValid = false;
                                                                    } else
                                                                    if (!rating) {
                                                                        alert("Rating is required.");
                                                                        isValid = false;
                                                                    } else
                                                                    if (subject === "") {
                                                                        alert("Subject is required.");
                                                                        isValid = false;
                                                                    } else
                                                                    if (content === "") {
                                                                        alert("Content is required.");
                                                                        isValid = false;
                                                                    }
                                                                    return isValid;
                                                                }


                                                                function handleSubmitFeedback(e) {

                                                                    e.preventDefault();

                                                                    if (window.confirm("Bạn có chắc chắn muốn gửi feedback này không?")) {
                                                                        if (validateForm()) {
                                                                            document.getElementById("form-contact").submit();
                                                                        }
                                                                    }
                                                                }
                                                            </script>

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

                                                    <!-- Mirrored from creativelayers.net/themes/techno-html/contact-v2.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 11 May 2024 18:10:06 GMT -->
                                                    </html>
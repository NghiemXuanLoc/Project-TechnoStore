<%-- 
    Document   : cartcompletion
    Created on : Jul 1, 2024, 10:48:17 AM
    Author     : PC
--%>

<%@page import="model.Orders"%>
<%@page import="java.util.List"%>
<%@page import="model.ProductDetail"%>
<%@page import="model.Cart"%>
<%@page import="constant.Iconstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Feedback Product</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap.min.css">

        <!-- Theme style -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style.css">

        <!-- Reponsive -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/responsive.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-list.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-feedback.css">

        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.3/nouislider.min.css"
            rel="stylesheet"
            />

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>
    </head>
    <body>


        <jsp:include page="../../common/user/header.jsp"></jsp:include>



            <section class="flat-breadcrumb">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="breadcrumbs">
                                <li class="trail-item">
                                    <a href="${pageContext.request.contextPath}/home" title="">Home</a>
                                <span><img src="images/icons/arrow-right.png" alt=""></span>
                            </li>

                            <li class="trail-item">
                                <a href="${pageContext.request.contextPath}/myorder" title="">My Order</a>
                                <span><img src="images/icons/arrow-right.png" alt=""></span>
                            </li>

                            <li class="trail-end">
                                <a href="javascript:void(0)" title="">Feedback Product</a>
                            </li>
                        </ul><!-- /.breacrumbs -->
                    </div><!-- /.col-md-12 -->
                </div><!-- /.row -->
            </div><!-- /.container -->
        </section><!-- /.flat-breadcrumb -->



        <section class="flat-breadcrumb">


            <div class="container">




                <div class="row">


                    <c:if test="${requestScope.error == null}">
                        <div class="col-lg-8 col-md-8">
                            <h1 class="mb-4">Feeback</h1>

                            <div class="row col-md-12 mb-4">
                                <div class="col-md-2">
                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${requestScope.productDetail.product.productId}">
                                        <img style="width: 150px" src="${requestScope.productDetail.product.gallery.get(0)}" alt="">
                                    </a>
                                </div>

                                <div class="col-md-10 d-flex justify-content-start align-items-center">
                                    <a href="${pageContext.request.contextPath}/productdetail?option=detail&productId=${productDetail.product.productId}&colorId=${color.colorId}&memoryId=${memory.memoryId}">
                                        <div>
                                            <c:if test="${requestScope.productDetail.product.productName != null}">
                                                <p>${requestScope.productDetail.product.productName}</p>
                                                <p>Phân loại hàng: ${requestScope.color.colorName} - ${requestScope.memory.memorySize} GB</p>
                                                <p>Số Lượng: x${requestScope.quantity}</p>
                                            </c:if>

                                        </div>
                                    </a>
                                </div>
                            </div>


                            <c:if test="${requestScope.addSuccess == null}">
                                <form action="${requestScope.fp == null ? 'addfeedback' : 'editfeedback'}" method="post" id="formFeedback">
                                    <div class="row">
                                        <div class="form-group col-md-6">
                                            <label>FullName</label>
                                            <input id="fullName" name="fullName" type="text" class="form-control" value="${requestScope.fp != null ? requestScope.fp.fullName : (sessionScope.account != null ? sessionScope.account.fullName : requestScope.order.fullName)}"/>
                                        </div>

                                        <div class="col-md-6">
                                            <label>Gender</label>
                                            <div class="d-flex justify-content-around">

                                                <div class="d-flex form-group">
                                                    <input id="male" type="radio" name="gender" value="0" ${requestScope.fp != null ? (requestScope.fp.gender == 0 ? 'checked' : '') : (sessionScope.account != null ? sessionScope.account.gender == 0 ? 'checked' : '' : 'checked')}/> 
                                                    <label for="male">Male</label>
                                                </div>

                                                <div class="d-flex form-group">
                                                    <input id="female" type="radio" name="gender" value="1" ${requestScope.fp != null ? (requestScope.fp.gender == 1 ? 'checked' : '') : (sessionScope.account != null ? sessionScope.account.gender == 1 ? 'checked' : '' : '')}/> 
                                                    <label for="female">Female</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group col-md-6">
                                            <label>Email</label>
                                            <input id="emailFeedback" name="email" type="text" class="form-control" value="${requestScope.fp != null ? (requestScope.fp.email) : (sessionScope.account != null ? sessionScope.account.email : requestScope.order.email)}"/>
                                        </div>

                                        <div class="form-group col-md-6">
                                            <label>Phone Number</label>
                                            <input id="phoneNumber" name="phoneNumber" type="text" class="form-control" value="${requestScope.fp != null ? (requestScope.fp.phoneNumber) : (sessionScope.account != null ? sessionScope.account.phoneNumber : requestScope.order.phoneNumber)}"/>
                                        </div>
                                    </div>

                                    <div class="star-rating d-flex justify-content-center mb-3">
                                        <input type="radio" id="star5" name="rating" value="5" ${requestScope.fp != null ? (requestScope.fp.rating == 5 ? 'checked' : '') : ''} /><label
                                            for="star5"
                                            title="5 stars"
                                            >★</label
                                        >
                                        <input type="radio" id="star4" name="rating" value="4" ${requestScope.fp != null ? (requestScope.fp.rating == 4 ? 'checked' : '') : ''}/><label
                                            for="star4"
                                            title="4 stars"
                                            >★</label
                                        >
                                        <input type="radio" id="star3" name="rating" value="3" ${requestScope.fp != null ? (requestScope.fp.rating == 3 ? 'checked' : '') : ''} /><label
                                            for="star3"
                                            title="3 stars"
                                            >★</label
                                        >
                                        <input type="radio" id="star2" name="rating" value="2" ${requestScope.fp != null ? (requestScope.fp.rating == 2 ? 'checked' : '') : ''} /><label
                                            for="star2"
                                            title="2 stars"
                                            >★</label
                                        >
                                        <input type="radio" id="star1" name="rating" value="1" ${requestScope.fp != null ? (requestScope.fp.rating == 1 ? 'checked' : '') : ''} /><label
                                            for="star1"
                                            title="1 star"
                                            >★</label
                                        >
                                    </div>
                                            
                                    <textarea id="description" name="description" placeholder="Please enter your comments!">${requestScope.fp != null ? requestScope.fp.content : ''}</textarea>
                                    <input type="hidden" name="orderId" value="${param.orderId != null ? param.orderId : requestScope.order.orderId}"/>
                                    <input type="hidden" name="productDetailId" value="${requestScope.productDetailId}"/>
                                    <c:if test="${requestScope.fp != null}">
                                        <input type="hidden" name="feedbackId" value="${param.feedbackId != null ? param.feedbackId : requestScope.fp.feedbackId}"/>
                                    </c:if>

                                    <c:if test="${requestScope.fp == null}">
                                        <button  onclick="handleSubmitFeedback('add')" type="button" style="background-color: orange" class="btn mt-3">Send feedback</button>
                                    </c:if>

                                    <c:if test="${requestScope.fp != null && requestScope.fp.editNumber == 0}">
                                        <button  onclick="handleSubmitFeedback('edit')" type="button" style="background-color: orange" class="btn mt-3">Save change</button>
                                    </c:if>
                                </form>
                            </c:if>

                            <c:if test="${requestScope.addSuccess != null}">
                                <h3 class="text-success">${requestScope.addSuccess}</h3>
                            </c:if>
                        </div>
                    </c:if>

                    <c:if test="${requestScope.error != null}">
                        <div class="col-lg-8 col-md-8">
                            <h3 class="text-danger mt-5">${requestScope.error}</h3>
                        </div>
                    </c:if>

                    <div class="col-lg-4 col-md-4">

                        <div class="sidebar ">

                            <div class="widget widget-categories">
                                <form action="productlist">
                                    <div class="widget-title">
                                        <h3>Product search box</h3>
                                        <input type="text" name="searchBox" value="${param.searchBox}" placeholder="Input your product search box!"/>
                                    </div>
                                </form>
                            </div> 

                            <div class="widget widget-categories">
                                <div class="widget-title">
                                    <h3>Contact</h3>

                                    <ul class="cat-list style1 widget-content">

                                        <li>
                                            <a href="mailto:nghiemxuanloc02@gmail.com?subject=Thắc mắc mua hàng by ${sessionScope.account == null ? 'anonymous' : sessionScope.account.fullName}"><i class="fa-solid fa-envelope"></i> nghiemxuanloc02@gmail.com</a>
                                        </li>
                                        <li>
                                            <a href="https://maps.app.goo.gl/drgj3LA8TMcdEmf8A" target="_blank"> <i class="fa-solid fa-globe"></i> Locate Our Shop</a>
                                        </li>

                                        <li>
                                            <a href="https://www.facebook.com/nghiemxuanloc211" target="_blank"> <i class="fa-brands fa-facebook"></i> Nghiem Xuan Loc</a>
                                        </li>

                                        <li>
                                            <a href="tel:0123456789"><i class="fa-solid fa-phone"></i> 0337783926</a>
                                        </li>
                                    </ul> 
                                </div>
                            </div> 


                            <div class="widget widget-categories">
                                <div class="widget-title">
                                    <h3>Categories<span></span></h3>
                                </div>

                                <ul class="cat-list style1 widget-content">

                                    <c:forEach items="${sessionScope.categoryAll}" var="category">
                                        <li>
                                            <span><a href="${pageContext.request.contextPath}/productlist?category=${category.categoryId}">${category.categoryName}</a></span>
                                        </li>
                                    </c:forEach>
                                </ul> 
                            </div> 


                            <div class="widget widget-products">

                                <div class="widget-title">
                                    <h3>Lastest Products<span></span></h3>
                                </div>

                                <ul class="product-list widget-content">

                                    <c:forEach items="${sessionScope.top2LastProduct}" var="product">
                                        <li>
                                            <div class="img-product">
                                                <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">
                                                    <img style="width: 100px; height: auto"  src="${pageContext.request.contextPath}/${product.gallery.get(0)}" alt="">
                                                </a>
                                            </div>
                                            <div class="info-product">
                                                <div class="name">
                                                    <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${product.productId}" title="">${product.productName}</a>
                                                    <img style="margin-left: 10px"  onclick="selectProductCompare('${pageContext.request.contextPath}/${product.gallery.get(0)}', '${product.productName}', '${product.productId}')"  src="images/icons/compare.png" alt="">
                                                    <img onclick="selectProductWishList(${product.productId})" src="images/icons/wishlist.png" alt="">
                                                </div>

                                                <div class="queue">
                                                    <c:set value="${product.avgRating}" var="star"/>

                                                    <c:set value="${product.avgRating % 1 > 0 ? product.avgRating -  product.avgRating % 1 : product.avgRating}" var="starFull"/>
                                                    <c:set value="${product.avgRating % 1 > 0 ? 1 : 0}" var="starHaft"/>
                                                    <c:set value="${5 - starFull - starHaft}" var="starEmpty"/>

                                                    <c:forEach begin="1" end="${starFull}">
                                                        <!--hien thi ngoi sao day du-->
                                                        <i class="fa fa-star" aria-hidden="true"></i> 
                                                    </c:forEach>

                                                    <c:forEach begin="1" end="${starHaft}">
                                                        <!--hien thi ngoi sao 1/2 rong-->
                                                        <i class="fa fa-star-half-empty" aria-hidden="true"></i>
                                                    </c:forEach>

                                                    <c:forEach begin="1" end="${starEmpty}">
                                                        <!--hien thi sao rong-->
                                                        <i class="fa fa-star-o" aria-hidden="true"></i>
                                                    </c:forEach>


                                                </div>
                                                <div class="price">
                                                    <span class="sale"><fmt:formatNumber value="${product.minPrice}" type="currency" /></span>
                                                    <span class="regular"><fmt:formatNumber value="${product.minPriceOrigin}" type="currency" /></span>
                                                </div>
                                                <c:set var="lengthBrief" value="${product.briefInfo.length()}"/>

                                                <c:if test="${lengthBrief > 30}">
                                                    <span>${product.briefInfo.substring(0, 30)}...</span>
                                                </c:if>

                                                <c:if test="${lengthBrief <= 30}">
                                                    <span>${product.briefInfo.substring(0, lengthBrief)}</span>
                                                </c:if>
                                            </div>
                                        </li>	 
                                    </c:forEach>
                                </ul>
                            </div> 

                        </div> 
                    </div> 
                </div>
            </div>
        </section> 


        <jsp:include page="../../common/user/footer.jsp"></jsp:include>

            <script>

                function validateEmail(email) {
                    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                    return emailRegex.test(String(email).toLowerCase());
                }

                function validateForm() {
                    const fullName = document.getElementById('fullName').value.trim();
                    const email = document.getElementById('emailFeedback').value.trim();
                    const phoneNumber = document.getElementById('phoneNumber').value.trim();
                    const rating = document.querySelector('input[name="rating"]:checked');
                    let isValid = true;

                    if (fullName === "") {
                        alert("Full Name is required.");
                        isValid = false;
                    }

                    if (email === "" || !validateEmail(email)) {
                        alert("A valid Email is required.");
                        isValid = false;
                    }

                    if (phoneNumber === "" || !/^0\d{9}$/.test(phoneNumber)) {
                        alert("A valid Phone Number is required.");
                        isValid = false;
                    }

                    if (!rating) {
                        alert("Rating is required.");
                        isValid = false;
                    }

                    return isValid;
                }


                function handleSubmitFeedback(option) {

                    if (validateForm()) {
                        console.log("submit feedback");
                        if (option == 'edit') {
                            if (window.confirm("Bạn chỉ có quyền sửa Feedback 1 lần, sau khi sửa bạn sẽ không thể thay đổi lựa chọn của mình. Bạn có chắc chắn muốn sửa feedback này không?")) {
                                document.getElementById("formFeedback").submit();
                            }
                        } else {
                            document.getElementById("formFeedback").submit();
                        }
                    }

                }
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

        <script src="${pageContext.request.contextPath}/tinymce/tinymce.min.js" ></script>

        <script>
                tinymce.init({
                    selector: 'textarea#description',
                    width: 750,
                    height: 400,
                    maxlength: -1,
                    plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage advtemplate ai mentions tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss markdown',
                    toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | forecolor | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                    tinycomments_mode: 'embedded',
                    tinycomments_author: 'Author name',
                    mergetags_list: [
                        {value: 'First.Name', title: 'First Name'},
                        {value: 'Email', title: 'Email'},
                    ],
                    menu: {
                        favs: {
                            title: 'menu', items: 'code visualaid | searchreplace | emoticons'
                        }
                    },
                    menubar: 'favs file edit view insert format tools table',
                    ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant")),
                });
        </script>


    </body>
</html>

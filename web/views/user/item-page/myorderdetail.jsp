
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page import="constant.Iconstant"%>
<%@page import="dal.FeedbackProductDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="controller.user.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="CreativeLayers">
        <!-- Mobile Specific Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <!-- Boostrap style -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style.css">
        <!-- Reponsive -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/responsive.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">
        <!--Style content-->
        <!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-bloglist.css">-->

        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>
<!--        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-blog-detail.css">-->

        <!--        <link
                    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                    rel="stylesheet"
                    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                    crossorigin="anonymous"
                    />-->

        <style>
            .cat-list a {
                color: black; /* Sets the text color to black */
                text-decoration: none; /* Removes the underline from the links */
                display: inline-block; /* Ensures padding and margin are applied properly */
                padding: 5px 0; /* Adds some padding for better appearance */
            }

            .cat-list a:hover {
                text-decoration: underline; /* Adds underline on hover for better user feedback */
            }
            /* Đảm bảo checkbox không bị ẩn */
            input[type="checkbox"] {
                visibility: visible;
                opacity: 1;
                z-index: 1;
            }

            /* Đảm bảo checkbox không bị phủ bởi các phần tử khác */
            td.text-center {
                position: relative;
                z-index: 1;
            }
        </style>
    </head>
    <body class="header_sticky">
        <jsp:include page="../../common/user/header.jsp"></jsp:include>

            <div class="card bg-white profile-content container">
                <div class="row">
                    <div class="col-lg-6 col-xl-3" style="padding-top: 40px">
                        <div class="sidebar">

                            <!-- Contact Widget -->
                            <div class="widget widget-categories">
                                <div class="widget-title">
                                    <h3>Contact</h3>
                                </div>
                                <ul class="cat-list style1 widget-content">
                                    <li>
                                        <a href="mailto:nghiemxuanloc02@gmail.com?subject=Thắc mắc mua hàng by ${sessionScope.account == null ? 'anonymous' : sessionScope.account.fullName}">
                                        <i class="fa-solid fa-envelope"></i> nghiemxuanloc02@gmail.com
                                    </a>
                                </li>
                                <li>
                                    <a href="https://maps.app.goo.gl/drgj3LA8TMcdEmf8A" target="_blank">
                                        <i class="fa-solid fa-globe"></i> Locate Our Shop
                                    </a>
                                </li>
                                <li>
                                    <a href="https://www.facebook.com/nghiemxuanloc211" target="_blank">
                                        <i class="fa-brands fa-facebook"></i> Nghiem Xuan Loc
                                    </a>
                                </li>
                                <li>
                                    <a href="tel:0337783926">
                                        <i class="fa-solid fa-phone"></i> 0337783926
                                    </a>
                                </li>
                            </ul><!-- /.cat-list -->
                        </div><!-- /.widget-categories -->

                        <!-- Search Box Widget -->
                        <div class="widget widget-categories">
                            <form action="myorder">
                                <div class="widget-title">
                                    <h3>Search box</h3>
                                    <input type="text" name="searchKeyword" value="${requestScope.keysearch}" class="form-control" placeholder="Input your product search box!"/>
                                </div>
                            </form>
                        </div>

                        <!-- Categories Widget -->
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
                            </ul><!-- /.cat-list -->
                        </div><!-- /.widget-categories -->


                        <div class="widget widget-products">

                            <div class="widget-title">
                                <h3>Lastest Products<span></span></h3>
                            </div>

                            <ul class="product-list widget-content cat-list">

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
                    </div><!-- /.sidebar -->
                </div>


                <div class="col-lg-6 col-xl-8">
                    <div class="profile-content-right profile-right-spacing py-5">
                        <ul class="nav nav-tabs px-3 px-xl-5 nav-style-border" id="myProfileTab" role="tablist">
                            <li class="nav-item d-flex " role="presentation">
                                <button class="nav-link active cat-list" id="profile-tab" data-bs-toggle="tab"
                                        data-bs-target="#profile" type="button" role="tab"
                                        aria-controls="profile" aria-selected="true">Detail Order</button>
                                <button style="margin-left: 13px;" class="nav-link " id="profile-tab" data-bs-toggle="tab"
                                        data-bs-target="#profile" type="button" role="tab"
                                        aria-controls="profile" aria-selected="true"><a style="color: #888" href="/TechStore/myorder">My Order</a></button>
                            </li>
                        </ul>
                        <div class="tab-content px-3 px-xl-5" id="myTabContent">

                            <div class="tab-pane fade show active" id="profile" role="tabpanel"
                                 aria-labelledby="profile-tab">
                                <div class="tab-widget mt-5">
                                    <div class="row">

                                        <div class="col-xl-6">
                                            <div class="media widget-media p-3 bg-white border">
                                                <div class="icon rounded-circle mr-3 bg-primary">
                                                    <i class="mdi mdi-account-outline text-white "></i>
                                                </div>

                                                <div class="media-body align-self-center">
                                                    <h4 class="text-primary mb-2">Customer Infomation</h4>
                                                    <p>Id:${orderdetail.CustomerName}</p>
                                                    <p>Order_date:${orderdetail.Order_date}</p>
                                                    <p>Total money:${orderdetail.Total_money} VND</p>
                                                    <p>Status:
                                                        <span class="badge ${orderdetail.Status == 2 ? 'badge-success' : orderdetail.Status == 1 ? 'badge-danger' : 'badge-warning'}">
                                                            ${orderdetail.Status == 2 ? 'Completed' : orderdetail.Status == 1 ? 'Cancelled' : 'Unpaid'}
                                                        </span>

                                                    </p>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xl-6">
                                            <div class="media widget-media p-3 bg-white border">
                                                <div class="icon rounded-circle bg-warning mr-3">
                                                    <i class="mdi mdi-cart-outline text-white "></i>
                                                </div>

                                                <div class="media-body align-self-center">
                                                    <h4 class="text-primary mb-2">Receiver Information</h4>
                                                    <p>Fullname:${orderdetail.Full_name}</p>
                                                    <p>Gender:${orderdetail.Gender==1?'Male':'Female'}</p>
                                                    <p>Email:${orderdetail.Email}</p>
                                                    <p>Address:${orderdetail.Address}</p>

                                                    <p>PhoneNumber:${orderdetail.Phone_number}</p>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                    <!--list order-->
                                    <div id="message-placeholder"></div>

                                    <div class="row">
                                        <div class="col-xl-12">

                                            <div class="row" id="hiddenTable2">
                                                <div class="col-12">
                                                    <div class="card card-default">

                                                        <div class="card-body">                                                                                                               
                                                            <div class="table-responsive">


                                                                <form id="rebuyForm" action="/TechStore/addorderinformation" method="get">
                                                                    <table class="table table-striped table-bordered o-tbl" id="table-data">
                                                                        <thead>
                                                                            <tr class="line">
                                                                                <td class="text-center"><strong>SELECT</strong></td>
                                                                                <td class="text-center"><strong>IMAGE</strong></td>
                                                                                <td class="text-center"><strong>ProductName</strong></td>
                                                                                <td class="text-center"><strong>Category</strong></td>
                                                                                <td class="text-center"><strong>Unit Price</strong></td>
                                                                                <td class="text-center"><strong>QUANTITY</strong></td>
                                                                                <td class="text-center"><strong>SUBTOTAL</strong></td>
                                                                                <td class="text-center"><strong>Actions</strong></td>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <c:forEach var="thong" items="${orderdetailist}">
                                                                                <tr>
                                                                                    <td class="text-center">
                                                                                        <input name="productId" value="${thong.Product_id}" onchange="selectProductPayment()" style="opacity: 1" type="checkbox" class="product-checkbox"/>
                                                                                        <input type="hidden" name="colorId" value="${thong.Color_id}" class="color-id">
                                                                                        <input type="hidden" name="memoryId" value="${thong.memory_id}" class="memory-id">
                                                                                        <input type="hidden" name="soLuong" value="${thong.Quantity}" class="quantity">
                                                                                    </td>
                                                                                    <td class="text-center">
                                                                                        <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${thong.Product_id}">
                                                                                            <img class="product-img" src="${thong.Thumbnail}" alt="" />
                                                                                        </a>
                                                                                    </td>
                                                                                    <td class="text-center">
                                                                                        <a href="${pageContext.request.contextPath}/productdetail?option=common&productId=${thong.Product_id}">
                                                                                            <span style="color: black">
                                                                                                ${thong.Product_name}, Color:
                                                                                                <c:choose>
                                                                                                    <c:when test="${thong.Color_id == 1}">Đen</c:when>
                                                                                                    <c:when test="${thong.Color_id == 2}">Trắng</c:when>
                                                                                                    <c:when test="${thong.Color_id == 3}">Xanh</c:when>
                                                                                                    <c:when test="${thong.Color_id == 4}">Hồng</c:when>
                                                                                                    <c:when test="${thong.Color_id == 5}">Vàng</c:when>
                                                                                                    <c:when test="${thong.Color_id == 6}">Tím</c:when>
                                                                                                    <c:otherwise>Không xác định</c:otherwise>
                                                                                                </c:choose>, Ram:
                                                                                                <c:choose>
                                                                                                    <c:when test="${thong.memory_id == 1}">128GB</c:when>
                                                                                                    <c:when test="${thong.memory_id == 2}">256GB</c:when>
                                                                                                    <c:when test="${thong.memory_id == 3}">512GB</c:when>
                                                                                                    <c:when test="${thong.memory_id == 4}">1TB</c:when>
                                                                                                    <c:otherwise>${thong.memory_id}GB</c:otherwise>
                                                                                                </c:choose>
                                                                                            </span>                                                                                            
                                                                                        </a>
                                                                                    </td>
                                                                                    <td class="text-center">${thong.Category_name}</td>
                                                                                    <td class="text-center">${thong.Price_sale}</td>
                                                                                    <td class="text-center">${thong.Quantity}</td>
                                                                                    <td class="text-center">
                                                                                        <c:set var="totalPrice" value="${thong.Price_sale * thong.Quantity}" />
                                                                                        <c:out value="${totalPrice}" />
                                                                                    </td>
                                                                                    <c:if test="${thong.Status==2 && thong.exitFeedback == null}">
                                                                                        <td class="text-center d-flex">
                                                                                            <a href="${pageContext.request.contextPath}/feedback?productId=${thong.Product_id}&colorId=${thong.Color_id}&memoryId=${thong.memory_id}&quantity=${thong.Quantity}&orderId=${param.orderId}" class="btn btn-sm btn-success text-center" style="padding-top: 14px">Send Feedback</a>
                                                                                        </td>
                                                                                    </c:if>
                                                                                    <c:if test="${thong.Status==2 && thong.exitFeedback != null && thong.exitFeedback.editNumber == 0}">
                                                                                        <td class="text-center d-flex">
                                                                                            <a href="${pageContext.request.contextPath}/editfeedback?feedbackId=${thong.exitFeedback.feedbackId}&quantity=${thong.Quantity}" class="btn btn-sm btn-warning text-center" style="padding-top: 14px">Edit Feedback</a>
                                                                                        </td>
                                                                                    </c:if>
                                                                                    <c:if test="${thong.Status==2 && thong.exitFeedback != null}">
                                                                                        <td class="text-center d-flex">
                                                                                            <button type="button" class="btn btn-sm btn-primary text-center" style="padding-top: 14px"
                                                                                                    onclick="popupViewFeedback(this)"
                                                                                                    data-feedback-date="${thong.exitFeedback.feedbackDate}"
                                                                                                    data-feedback-rating="${thong.exitFeedback.rating}"
                                                                                                    data-feedback-content="${fn:escapeXml(thong.exitFeedback.content)}"
                                                                                                    data-feedback-fullname="${thong.exitFeedback.fullName}"
                                                                                                    data-feedback-gender="${thong.exitFeedback.gender}"
                                                                                                    data-feedback-email="${thong.exitFeedback.email}"
                                                                                                    data-feedback-phonenumber="${thong.exitFeedback.phoneNumber}">
                                                                                                View Feedback
                                                                                            </button>
                                                                                        </td>
                                                                                    </c:if>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                    <input id="selectAll" onchange="selectAllProductPayment()" style="opacity: 1;margin-left: 30px" type="checkbox"/> 
                                                                    <label for="selectAll">Select all</label>
                                                                    <div class="text-center d-flex justify-content-center" style="gap: 10px; margin-top: 14px;">
                                                                        <c:if test="${requestScope.orderstatus == 2 || requestScope.orderstatus == 1}">
                                                                            <button type="submit" class="btn btn-primary">Rebuy Selected Products</button>
                                                                        </c:if>
                                                                        <c:if test="${requestScope.orderstatus == 0}">
                                                                            <button type="button" class="btn btn-danger" onclick="handleCanceling(${requestScope.orderCalcell})">Cancel Order</button>
                                                                        </c:if>
                                                                    </div>
                                                                </form>

                                                                <!-- Feedback Modal -->
                                                                <div class="modal fade" id="feedbackModal" tabindex="-1" role="dialog" aria-labelledby="feedbackModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="feedbackModalLabel">Feedback Details</h5>
                                                                                
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <p><strong>Date:</strong> <span id="feedbackDate"></span></p>
                                                                                <p><strong>Rating:</strong> <span id="feedbackRating"></span></p>
                                                                                <p><strong>Content:</strong> <span id="feedbackContent"></span></p>
                                                                                <p><strong>Full Name:</strong> <span id="feedbackFullName"></span></p>
                                                                                <p><strong>Gender:</strong> <span id="feedbackGender"></span></p>
                                                                                <p><strong>Email:</strong> <span id="feedbackEmail"></span></p>
                                                                                <p><strong>Phone Number:</strong> <span id="feedbackPhoneNumber"></span></p>
                                                                            </div>
                                                                            
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <script>
                                                                    function popupViewFeedback(button) {
                                                                        // Get feedback details from data attributes
                                                                        const feedbackDate = button.getAttribute('data-feedback-date');
                                                                        const feedbackRating = button.getAttribute('data-feedback-rating');
                                                                        const feedbackContent = button.getAttribute('data-feedback-content');
                                                                        const feedbackFullName = button.getAttribute('data-feedback-fullname');
                                                                        const feedbackGender = button.getAttribute('data-feedback-gender');
                                                                        const feedbackEmail = button.getAttribute('data-feedback-email');
                                                                        const feedbackPhoneNumber = button.getAttribute('data-feedback-phonenumber');

                                                                        // Set the feedback details in the modal
                                                                        document.getElementById('feedbackDate').textContent = feedbackDate;

                                                                        // Set rating with stars
                                                                        const ratingElement = document.getElementById('feedbackRating');
                                                                        ratingElement.innerHTML = '';
                                                                        for (let i = 1; i <= 5; i++) {
                                                                            if (i <= feedbackRating) {
                                                                                ratingElement.innerHTML += '&#9733;'; // filled star
                                                                            } else {
                                                                                ratingElement.innerHTML += '&#9734;'; // empty star
                                                                            }
                                                                        }

                                                                        // Set content
                                                                        document.getElementById('feedbackContent').innerHTML = feedbackContent;
                                                                        document.getElementById('feedbackFullName').textContent = feedbackFullName;

                                                                        // Set gender
                                                                        const genderText = feedbackGender === '1' ? 'Male' : 'Female';
                                                                        document.getElementById('feedbackGender').textContent = genderText;

                                                                        // Set email and phone number
                                                                        document.getElementById('feedbackEmail').textContent = feedbackEmail;
                                                                        document.getElementById('feedbackPhoneNumber').textContent = feedbackPhoneNumber;

                                                                        // Show the modal
                                                                        $('#feedbackModal').modal('show');
                                                                    }
                                                                </script>




                                                                <c:if test="${requestScope.orderstatus == 0}">

                                                                    <p class="text-danger mt-3">Please pay for your order before ${requestScope.DateCancel} After the above time, the order will be automatically canceled.</p>


                                                                    <form class="mt-3" action="vnpayrequest" method="post">
                                                                        <input type="hidden" name="orderId" value="${requestScope.orderId}"/>
                                                                        <input type="hidden" name="amount" value="${requestScope.amount}"/>
                                                                        <input type="hidden" name="bankCode" value="VNBANK"/>
                                                                        <div class="d-flex justify-content-center">
                                                                            <button type="submit" class="btn btn-success">Payment now</button>
                                                                        </div>
                                                                    </form>
                                                                </c:if>


                                                                <!-- Cancel Order Modal -->
                                                                <div class="modal fade" id="cancelOrderModal" tabindex="-1" aria-labelledby="cancelOrderModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="cancelOrderModalLabel">Cancel Order</h5>
                                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <form id="editOrderStatusForm" action="/TechStore/updateorderinformationstatus" method="post">
                                                                                    <input type="hidden" name="orderId" id="orderId" />
                                                                                    <input type="hidden" name="status" value="1" />
                                                                                    <div class="mb-3">
                                                                                        <label for="orderIdInput" class="form-label">Order ID</label>
                                                                                        <input type="text" class="form-control" id="orderIdInput" readonly />
                                                                                    </div>
                                                                                    <div class="modal-footer">
                                                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                                        <button type="button" class="btn btn-danger" onclick="confirmCanceling()">Confirm Cancel</button>
                                                                                    </div>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>


                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>



                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>                          
        <jsp:include page="../../common/user/footer.jsp"></jsp:include>



            <script>
                function handleCanceling(orderId) {
                    if (orderId) {
                        // Set the order ID in the hidden input field of the form
                        document.getElementById('orderId').value = orderId;

                        // Set the order ID in the read-only input field for display
                        document.getElementById('orderIdInput').value = orderId;

                        // Open the modal
                        var cancelOrderModal = new bootstrap.Modal(document.getElementById('cancelOrderModal'));
                        cancelOrderModal.show();
                    }
                }

                function confirmCanceling() {
                    if (confirm("Are you sure you want to cancel this order?")) {
                        document.getElementById('editOrderStatusForm').submit();
                    }
                }


            </script>
            <!--                    <script>
                                    
                                    
            
                                    function handleCanceling(orderId) {
                                        if (orderId) {
                                            // Set the order ID in the hidden input field of the form
                                            document.getElementById('orderId').value = orderId;
            
                                            // Set the order ID in the read-only input field for display
                                            document.getElementById('orderIdInput').value = orderId;
            
                                            // Open the modal
                                            var cancelOrderModal = new bootstrap.Modal(document.getElementById('cancelOrderModal'));
                                            cancelOrderModal.show();
                                        }
                                    }
            
                                    function confirmCanceling() {
                                        if (confirm("Are you sure you want to cancel this order?")) {
                                            document.getElementById('editOrderStatusForm').submit();
                                        }
                                    }
                                </script>-->
            <script>
                // Function to select or deselect all checkboxes
                function selectAllProductPayment() {
                    var selectAllCheckbox = document.getElementById('selectAll');
                    var checkboxes = document.getElementsByClassName('product-checkbox');
                    for (var i = 0; i < checkboxes.length; i++) {
                        checkboxes[i].checked = selectAllCheckbox.checked;
                    }
                }

                // Function to handle individual checkbox change
                function selectProductPayment() {
                    var selectAllCheckbox = document.getElementById('selectAll');
                    var checkboxes = document.getElementsByClassName('product-checkbox');
                    var allChecked = true;
                    for (var i = 0; i < checkboxes.length; i++) {
                        if (!checkboxes[i].checked) {
                            allChecked = false;
                            break;
                        }
                    }
                    selectAllCheckbox.checked = allChecked;
                }
            </script>

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
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/slider.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/js-blog-detail.js"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
    </body>
</html>




<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="controller.user.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
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
                        <!--                        <div class="widget widget-categories">
                                                    <form action="myorder">
                                   /                     <div class="widget-title">
                                                            <h3>Search box</h3>
                                                            <input type="text" name="searchKeyword" value="${requestScope.keysearch}" class="form-control" placeholder="Input your product search box!"/>
                                                        </div>
                                                    </form>
                                                </div>
                        
                                                 Categories Widget 
                                                <div class="widget widget-categories">
                                                    <div class="widget-title">
                                                        <h3>Categories<span></span></h3>
                                                    </div>
                        
                                                    <ul class="cat-list style1 widget-content">
                        
                        <c:forEach items="${categoryList}" var="category">
                            <li>
                                <span><a href="${pageContext.request.contextPath}/myorder?category=${category.Category_id}">${category.Category_name}</a></span>
                            </li>
                        </c:forEach>
                    </ul> /.cat-list 
                </div> /.widget-categories -->
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
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="profile-tab" data-bs-toggle="tab"
                                        data-bs-target="#profile" type="button" role="tab"
                                        aria-controls="profile" aria-selected="true">My Order</button>
                            </li>
                        </ul>
                        <div class="tab-content px-3 px-xl-5" id="myTabContent">

                            <div class="tab-pane fade show active" id="profile" role="tabpanel"
                                 aria-labelledby="profile-tab">
                                <div class="tab-widget mt-5">
                                    <div class="row">
                                        <div class="col-xl-4">
                                            <div class="media widget-media p-3 bg-white border">
                                                <div class="icon rounded-circle mr-3 bg-primary">
                                                    <i class="mdi mdi-account-outline text-white "></i>
                                                </div>

                                                <div class="media-body align-self-center">
                                                    <h4 class="text-primary mb-2">${total[0]}</h4>
                                                    <p>Bought</p>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xl-4">
                                            <div class="media widget-media p-3 bg-white border">
                                                <div class="icon rounded-circle bg-warning mr-3">
                                                    <i class="mdi mdi-cart-outline text-white "></i>
                                                </div>

                                                <div class="media-body align-self-center">
                                                    <h4 class="text-primary mb-2"> <fmt:formatNumber value="${total[1]}" type="currency" /></h4>
                                                    <p>Total Money</p>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xl-4">
                                            <div class="media widget-media p-3 bg-white border">
                                                <div class="icon rounded-circle mr-3 bg-success">
                                                    <i class="mdi mdi-ticket-percent text-white "></i>
                                                </div>

                                                <div class="media-body align-self-center">
                                                    <h4 class="text-primary mb-2">02</h4>
                                                    <p>Voucher</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--list order-->
                                    <div class="row">
                                        <div class="col-xl-12">

                                            <div class="row" id="hiddenTable2">
                                                <div class="col-12">
                                                    <div class="card card-default">
                                                        <div class="card-body">
                                                            <div class="table-responsive">
                                                               
                                                                <table id="responsive-data-table" class="table" style="width:100%">
                                                                    <thead>
                                                                        <tr>
                                                                            <th class="orderId">Id</th>
                                                                            <th class="orderDate sortable">OrderDate</th>
                                                                            <th class="productName">Product(Name/Quantity)</th>
                                                                            <th class="totalMoney sortable">TotalMoney</th>
                                                                            
                                                                            <th class="status sortable">StatusShipping</th>
                                                                            <th class="status sortable">PaymentStatus</th>
                                                                            <th>DetailOrder</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody id="orderTableBody">
                                                                        <c:forEach var="order" items="${orderlist}">
                                                                            <tr>
                                                                                <td>${order.Order_id}</td>
                                                                                <td>${order.Order_date}</td>
                                                                                <td><c:out value="${order.Product_details}" escapeXml="false" /></td>
                                                                                <td>${order.Total_money} đ</td>
                                                                                
                                                                                <td>${order.ShippingStatus==0?'Submited':'Shipping'}</td>
                                                                                <td>
                                                                                    <span class="badge ${order.Status == 2 ? 'badge-success' : order.Status == 1 ? 'badge-danger' : 'badge-warning'}">
                                                                                        ${order.Status == 2 ? 'Completed' : order.Status == 1 ? 'Cancelled' : 'Unpaid'}
                                                                                    </span>
                                                                                    
                                                                                        
                                                                                </td>
                                                                                <td><a class="btn btn-primary" href="/TechStore/myorderdetail?orderId=${order.Order_id}">Detail</a></td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>


                                                                <div class="d-flex justify-content-between">
                                                                    <form id="pageSizeFormBottom" method="get" action="/TechStore/myorder">
                                                                        <select id="pageSizeSelectBottom" name="pageSize" class="form-control w-auto" onchange="submitFormWithListType()">
                                                                            <option value="5" ${pageSize == 5 ? 'selected' : ''}>5</option>
                                                                            <option value="10" ${pageSize == 10 ? 'selected' : ''}>10</option>
                                                                            <option value="15" ${pageSize == 15 ? 'selected' : ''}>15</option>
                                                                        </select>
                                                                        <input type="hidden" id="searchKeywordHiddenBottom" name="searchKeyword" value="${requestScope.keysearch}">
                                                                        <input type="hidden" id="categoryHiddenBottom" name="category" value="${requestScope.category}">
                                                                    </form>

                                                                    <nav>
                                                                        <ul class="pagination">
                                                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                                                    <a class="page-link" href="myorder?page=${i}&pageSize=${pageSize}&searchKeyword=${requestScope.keysearch}&category=${requestScope.category}">${i}</a>
                                                                                </li>
                                                                            </c:forEach>
                                                                        </ul>
                                                                    </nav>
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
        <script>
            function submitFormWithListType() {
                document.getElementById("pageSizeFormBottom").submit();
            }

        </script>


        <script>

        </script>

        <jsp:include page="../../common/user/footer.jsp"></jsp:include>
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
        <script src="${pageContext.request.contextPath}/javascript/js-blog-detail.js"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
    </body>
</html>


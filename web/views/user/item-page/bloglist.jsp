<%-- 
    Document   : bloglist.jsp
    Created on : May 15, 2024, 9:59:38 PM
    Author     : quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="dal.*"%>
<%@page import="controller.user.*" %>
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
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">
        <!--Blog list style-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-bloglist.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>

    </head>
    <body class="header_sticky">
        <jsp:include page="../../common/user/header.jsp"></jsp:include>

            <div class="toast-container" id="toast-container"></div>
        <body class="header_sticky">
            <div class="boxed">
                <section class="flat-breadcrumb">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <ul class="breadcrumbs">
                                    <li class="trail-item">
                                        <a href="home" title="">Home</a>
                                        <span><img src="images/icons/arrow-right.png" alt=""></span>
                                    </li>
                                    <li class="trail-end">
                                        <a href="bloglist" title="">Bài viết</a>
                                    </li>
                                </ul><!-- /.breacrumbs -->
                            </div><!-- /.col-md-12 -->
                        </div><!-- /.row -->
                    </div><!-- /.container -->
                </section>
                <section class="main-blog">
                    <div class="container">
                        <div class="row">
                            <div class="col-2 col-md-2">
                                <div class="sidebar left">
                                    <!--SIDER: với tìm kiếm bài đăng sau khi nhập chuỗi vào thì hiển thị  -->
                                    <div class="widget widget-search">
                                        <form action="bloglist" method="get" accept-charset="utf-8">
                                            <input type="text" name="search" placeholder="Tìm kiếm">
                                        </form>
                                    </div><!-- /.widget widget-search -->
                                    <style>
                                        .active-category {
                                            display: inline-block;
                                            padding: 6px 12px;
                                            border: none;
                                            border-radius: 13px;
                                            background-color: #f7f7f7;
                                            color: #333;
                                            cursor: pointer;
                                        }

                                        .active-category.active {
                                            background-color: #F28B00;
                                            color: #fff;
                                        }
                                    </style>

                                    <nav class="widget ">
                                        <div class="widget-title">
                                            <h3>Thể loại bài viết</h3>
                                        </div>
                                        <ul class="tag-list">
                                        <c:forEach var="b" items="${bCategory}">
                                            <li>
                                                <a class="active-category ${param.bid == b.blogCategoryId ? 'active' : ''}" href="bloglist?bid=${b.blogCategoryId}" data-id="${b.blogCategoryId}">${b.blogCategoryName}</a>
                                            </li><br>
                                        </c:forEach>
                                        <li><a class="active-category ${empty param.bid ? 'active' : ''}" href="bloglist" data-id="all">Tất cả</a></li><br>
                                    </ul>
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
                                </nav>
                            </div>
                        </div>
                        <!--Nơi hiển thị ra danh sách các blog -->
                        <div class="col-7 col-md-7">
                            <h2><strong>TIN TỨC CẬP NHẬT</strong></h2>
                            <div class="post-wrap card-blockquote">
                                <c:forEach var="blog" items="${bDetail}">
                                    <article class="main-post card-img-bottom">
                                        <!--In ra ảnh bài viết và thông tin bài viết cạnh nhau-->
                                        <div class="listInfor">
                                            <div class="featured-post thumbnail" >
                                                <a href="blogdetail?id=${blog.blogDetailId}" title="">
                                                    <img class="img-thumbail " width="100%" height="auto" src="${pageContext.request.contextPath}/${blog.thumbnail}" alt="">
                                                </a>
                                            </div>
                                            <!-- In ra tiêu đề bài viết và thông tin bài viết -->
                                            <div class="content-post titleBloglist">
                                                <h4 class="title-post">
                                                    <a href="blogdetail?id=${blog.blogDetailId}" title="">${blog.title}</a>
                                                </h4>
                                                <ul class="meta-post blogInforUl">
                                                    <li class="comment blogauthor"><!-- In ra tên người đăng bài viết -->
                                                        <a href="" title="">Tác giả: ${blog.author}</a>
                                                    </li>
                                                    <li class="date blogdate"><!-- In ra ngày bài viết được đăng -->
                                                        <a href="" title=""><fmt:formatDate value="${blog.blogDate}" pattern="dd/MM/yyyy" /></a>
                                                    </li>
                                                </ul>
                                                <div class="entry-post"><!-- In ra giới thiệu ngắn gọn về bài viết -->
                                                    <p>${blog.shortDescription}</p>
                                                </div>
                                            </div><!-- /.content-post -->
                                        </div>
                                    </article>
                                </c:forEach>

                            </div><!-- /.post-wrap -->
                        </div>
                        <div class="col-3 col-md-3">
                            <div class="sidebar right"> 
                                <div class="widget widget-tags">
                                    <div class="widget-title">
                                        <h3>TIN TỨC MỚI NHẤT</h3>
                                    </div>
                                    <div class="row card-img-bottom">
                                        <c:forEach var="blog" items="${lastBlog}">
                                            <div class="col-6 lastTitle" >
                                                <a href="blogdetail?id=${blog.blogDetailId}">${blog.title}</a>
                                            </div>
                                            <div class="col-6 lastThumbnail">
                                                <a href="blogdetail?id=${blog.blogDetailId}">
                                                    <img src="${pageContext.request.contextPath}/${blog.thumbnail}" alt="${blog.title}">
                                                </a>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Phân trang  theo ngày đăng bài -->
                <div class="blog-pagination">
                    <ul class="flat-pagination">
                        <!--khai báo 2 biến trang trước và trang tiếp theo-->
                        <c:set var="prev" value="${index - 1 <= 0 ? 1 : index - 1}" /><!-- nếu ở trang hơn 1 thì -->
                        <c:set var="next" value="${index + 1 > page ? page : index + 1}" />
                        <c:if test="${index > 1}"> 
                            <li><a class="waves-effect waves-teal" href="bloglist?index=${prev}&bid=${bid}"><</a></li>
                            </c:if>
                        <!-- Hiển thị số trang hiện tại trang trước, sau hoặc 3 trang đầu  -->
                        <c:forEach var="i" begin="${prev}" end="${index == 1 ? (page < 3 ? page : 3) : next}">
                            <li class="${index==i?"active":""}"><a class="waves-effect waves-teal" href="bloglist?index=${i}&bid=${bid}">${i}</a></li>
                            </c:forEach>
                        <!-- nếu không phải đang ở trang 1 hoặc trang cuối thì hiển thị < > -->
                        <c:if test="${index > 1 && index < page}">
                            <li><a class="waves-effect waves-teal" href="bloglist?index=${next}&bid=${bid}">></a></li>
                            </c:if>
                    </ul><!-- /.flat-pagination -->
                </div>
            </section>
        </div>
        <jsp:include page="../../common/user/footer.jsp"></jsp:include>
            <script>
                // Lấy tất cả thẻ a có class active-category
                const activeCategories = document.querySelectorAll('.active-category');
                activeCategories.forEach((category) => {
                    category.addEventListener('click', () => {
                        activeCategories.forEach((c) => c.classList.remove('active'));
                        category.classList.add('active');
                    });
                });
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/js-product-detail.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/slider.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/changepasswordvsUserprofile.js"></script>

    </body>
</html>

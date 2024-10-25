

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
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon/favicon.png">
        <!--Style content-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-bloglist.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/changepasswordvsUserprofile.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-product-detail.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-blog-detail.css">
        <!--        <link
                    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                    rel="stylesheet"
                    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                    crossorigin="anonymous"
                    />-->

        <style>

            .choPheDuyet {
                opacity: 0.5;
                filter: blur(2px);
                pointer-events: none;
            }

            .nofitication {
                position: absolute;
                top: 50%;
                left: 50%;
                color: #ff9800;
                font-size: 25px;
                transform: translate(-50%, -50%);
            }
        </style>
    </head>
    <body class="header_sticky">
        <jsp:include page="../../common/user/header.jsp"></jsp:include>
            <div class="toast-container" id="toast-container"></div>
            <div class="boxed"> <c:set var="b1" value="${bd}" />
            <div class="overlay"></div>
            <section class="flat-breadcrumb">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="breadcrumbs">
                                <li class="trail-item">
                                    <a href="home" title="">Home</a>
                                    <span><img src="images/icons/arrow-right.png" alt=""></span>
                                </li>
                                <li class="trail-item">
                                    <a href="bloglist" title="">Bài viết</a>
                                    <span><img src="images/icons/arrow-right.png" alt=""><a> ${b1.blogCategory.blogCategoryName}</a></span>
                                </li>
                            </ul><!-- /.breacrumbs -->
                        </div><!-- /.col-md-12 -->
                    </div><!-- /.row -->
                </div><!-- /.container -->
            </section>
            <section class="main-blog">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8 col-lg-9">
                            <h1 class="titleh1">${b1.title}</h1></br> <!-- title -->
                            <div class="authordiv">
                                <div><span class="authorcolor">Tác giả: ${b1.author}</span></div> <!-- author -->
                                <div>Cập nhật: <span><fmt:formatDate value="${b1.blogDateUpdate}" pattern="dd/MM/yyyy" /></span></div><!-- blog updated -->
                            </div>
                            <div class="post-wrap">
                                ${b1.content}
                            </div><!-- /.post-wrap -->
                        </div>
                        <!-- chứa nội dung thanh bên thể hiện ra danh sách các thể loại bài viết -->
                        <div class="col-md-4 col-lg-3">
                            <div class="sidebar left">
                                <!--SIDER: với tìm kiếm bài đăng sau khi nhập chuỗi vào thì hiển thị  -->
                                <div class="widget widget-search">
                                    <form action="bloglist" method="get" accept-charset="utf-8">
                                        <input type="text" name="search" placeholder="Tìm kiếm">
                                    </form>
                                </div><!-- /.widget widget-search -->
                                <!--SIDER: Liệt kê ra danh sách cả thể loại bài viết-->
                                <nav class="widget widget-tags">
                                    <div class="widget-title">
                                        <h3>Thể loại bài viết</h3>
                                    </div>
                                    <ul class="tag-list">
                                        <c:forEach var="b" items="${bCategory}">
                                            <li><a class="waves-effect waves-teal" href="bloglist?bid=${b.blogCategoryId}">${b.blogCategoryName}</a></li></br>
                                            </c:forEach>
                                        <li><a href="bloglist">Tất cả</a></li>
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
                    </div>
<!--
                    <div class="comment-arean mt-3">
                        <h2 class="comment-title">${sessionScope.listCommentAll.size()} Comment</h2>

                        <c:if test="${sessionScope.account != null}">
                            <div class="comment-respond">
                                <div class="form-comment">
                                    <form accept-charset="utf-8">
                                        <div class="">
                                            <label>Rating</label>

                                            <select id="rating-blog-detail" class="form-select" aria-label="Default select example">
                                                <option selected value="5">5 Star</option>
                                                <option value="4">4 Star</option>
                                                <option value="3">3 Star</option>
                                                <option value="2">2 Star</option>
                                                <option value="1">1 Star</option>
                                            </select>

                                        </div> /.comment-form-comment 

                                        <div class="comment-form-comment">
                                            <textarea id="content-comment-blog-detail" name="comment-text"></textarea>
                                        </div> /.comment-form-comment 
                                        <div class="btn-submit">
                                            <button onclick="addCommentBlogDetail(${sessionScope.account.userId}, ${param.id})" type="button">Post Comment</button>
                                        </div> /.btn-submit 
                                    </form> /.form 
                                </div> /.form-comment 
                            </div> /.comment-respond 
                        </c:if>

                        <ol class="comment-list " id="list-comment-blog-detail">

                            <c:forEach items="${sessionScope.listComment}" var="comment">

                                <li style="position: relative" class="comment">
                                    <div class="comment-author ${comment.deleted == 0 ? "choPheDuyet" : ""}">
                                        <img style="width: 75px;height: auto" src="${comment.user.image}" alt="">
                                    </div>
                                    <div class="comment-text ${comment.deleted == 0 ? "choPheDuyet" : ""}">
                                        <div class="comment-metadata">
                                            <div class="name">
                                                ${comment.user.fullName} : <span class="ms-2">${comment.commentDate} |</span>


                                                <c:if test="${sessionScope.account.userId == comment.user.userId}">


                                                    <span                                                             
                                                        onclick="selectEditComment(${comment.commentId}, `${comment.content}`, ${param.id})"
                                                        class="btn btn-sm btn-warning"
                                                        data-bs-toggle="modal"
                                                        data-bs-target="#exampleModal"
                                                        >
                                                        Edit
                                                        <i style="margin-left: 10px;cursor: pointer;" class="fa-solid fa-pen-to-square"></i></span>


                                                    <span onclick="deleteComment(${comment.commentId}, ${param.id})" style="color: white" class="btn btn-sm btn-danger">Delete <i class="fa-solid fa-trash"></i></span>    

                                                </c:if>
                                                <span class="mx-5">${comment.likes}<i style="margin-left: 10px;cursor: pointer;" class="fa-solid fa-thumbs-up"></i></span>
                                                <span>${comment.report}<i style="margin-left: 10px;cursor: pointer;" class="fa-solid fa-flag"></i></span>
                                            </div>

                                            <div class="queue">

                                                <c:forEach begin="1" end="${comment.rating}">
                                                    <i class="fa fa-star" aria-hidden="true"></i>
                                                </c:forEach>

                                                <c:forEach begin="${comment.rating + 1}" end="${5}">
                                                    <i class="fa fa-star-o" aria-hidden="true"></i>
                                                </c:forEach>

                                            </div>
                                        </div>
                                        <div class="comment-content">
                                            <p>
                                                ${comment.content}
                                            </p> 
                                        </div>
                                        <div class="clearfix"></div>
                                    </div> 

                                    <span class="nofitication" style="display: ${comment.deleted == 0 ? 'block' : 'none'}">Đang chờ phê duyệt</span>
                                </li> /.comment 

                            </c:forEach>

                        </ol> /.comment-list 



                    </div> /.comment-area 

                    <div  class="d-flex justify-content-center">
                        <button id="loadMoree" onclick="loadMoreComment(${param.id}, ${sessionScope.listCommentAll.size()})" style="background-color: orange" class="btn mb-3">Load more</button>
                    </div>-->


                    <div class="toast-container" id="toast-container"></div>

            </section>
        </div>

        <div
            class="modal fade"
            id="exampleModal"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
            >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title" id="exampleModalLabel">Edit comment</h3>
                        <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                            style="color: black"
                            >
                            <i class="fa-solid fa-x"></i>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="abc">
                            <input type="hidden" id="commentId-change" value=""/>
                            <input type="hidden" id="blogDetailId-change" value=""/>
                            <div class="mb-3">
                                <label for="message-text" class="col-form-label"
                                       >Comment:</label
                                >
                                <textarea class="form-control" id="message-text"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal"
                            >
                            Close
                        </button>
                        <button onclick="saveChange()" type="button" class="btn btn-primary">Save change</button>
                    </div>
                </div>
            </div>
        </div>
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

<%-- 
    Document   : postdetail
    Created on : May 28, 2024, 8:59:18 AM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">
        <!-- PLUGINS CSS STYLE -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">
        <!-- Ekka CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <title>Techno Store - Post Detail</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <!-- Bắt đầu thanh narvbar -->
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
                <!-- Bắt đầu thanh header -->
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                    <c:set var="post" value="${postdetail}"/>
                    <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                        <div><h1><a href="javascript:void(0)">Post Detail</a></h1></div>
                        <div>
                            <a href="editpost?postID=${post.blogDetailId}" class="btn btn-primary">Edit Post</a>
                            <a href="addpost" class="btn btn-primary">Add Post</a>
                            <a href="/TechStore/postlist" class="btn btn-primary">View Post List</a>
                            <a href="javascript:void(0)" class="btn" id="toggleButton"> 
                                ${postdetail.deleted == 1 ? '🏴' : '🏳️'} 
                            </a>
                            <form id="statusForm" action="postdetail" method="post">
                                <input type="hidden" id="postID" name="postID" value="${postdetail.blogDetailId}">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card card-default">
                                <div class="card-body">
                                    <div class="row ec-vendor-uploads">
                                        <div class="col-md-4">
                                            <div class="ec-vendor-img-upload">
                                                <div class="ec-vendor-main-img">
                                                    <h5 class="text-center">Thumbnail</h5>
                                                    <div class="thumb-upload-set col-md-12">
                                                        <div class="thumb-upload">
                                                            <div class="thumb-preview ec-preview">
                                                                <div class="image-thumb-preview">
                                                                    <img class="image-thumb-preview ec-image-preview" src="${not empty post.thumbnail ? post.thumbnail : 'images/logos/originimg.jpg'}" alt="" />
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <div class="ec-vendor-upload-detail">
                                                <form class="row g-3">
                                                    <!-- Add post title in here -->
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Post Title</strong></label>
                                                        <textarea class="form-control" readonly >${post.title}</textarea>
                                                    </div>
                                                    <!--Add short description in here -->
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Brief Information</strong></label> 
                                                        <textarea class="form-control"readonly > ${post.shortDescription}</textarea>
                                                    </div>
                                                    <!--Chose Post Categories here -->
                                                    <div class="col-md-6">
                                                        <label class="form-label"><strong>Post Categories</strong></label>
                                                        <select name="categories" readonly class="form-select">
                                                            <option value="">${post.blogCategory.blogCategoryName}<option/>
                                                        </select>
                                                    </div>
                                                    <!--Add author name here -->
                                                    <div class="col-md-3">
                                                        <label class="form-label"><strong>Author</strong> </label>
                                                        <input type="text" class="form-control" readonly value="${post.author}">
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label"><strong>Day Upload</strong> </label>
                                                        <input type="datetime" class="form-control" readonly value="<fmt:formatDate value='${post.blogDate}' pattern='dd/MM/yyyy' />">
                                                    </div>
                                                    <!-- Add content in blog detail in here -->
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Description</strong></label>
                                                        <div class="form-control" style="height: fit-content; align-content: baseline; overflow: auto;">
                                                            ${post.content}
                                                        </div>
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
        <jsp:include page="../../common/sale/footer.jsp"/>
        <script>
            document.getElementById('toggleButton').addEventListener('click', function (event) {
                event.preventDefault();
                var flag = document.getElementById('toggleButton');
                var postID = document.getElementById('postID').value;

                // Confirm that the user wants to toggle the status
                if (confirm('Bạn có chắc chắn muốn chuyển đổi trạng thái không?')) {
                    fetch('postdetail', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: new URLSearchParams('postID=' + postID)
                    })
                            .then(response => {
                                if (response.ok) {
                                    // Update the flag symbol based on the current text content
                                    flag.textContent = flag.textContent.trim() === '🏴' ? '🏳️' : '🏴';
                                } else {
                                    console.error('Error: Failed to update status. Server responded with status', response.status);
                                }
                            })
                            .catch(error => console.error('Error:', error));
                }
            });
        </script>
        <!-- Common Javascript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>
</html>

<%-- 
    Document   : editpost
    Created on : Jun 4, 2024, 5:31:56 PM
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">
        <!-- Ekka CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <title>Techno Store - Edit Post</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <!-- Bắt đầu thanh narvbar -->
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
                <!-- Bắt đầu thanh header -->
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                            <div><h1><a href="javascript:void(0)">Edit Post</a></h1></div>
                            <div><a href="/TechStore/postlist" class="btn btn-primary">View Post List</a></div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                            <c:set var="post" value="${bdetail}"/>
                            <div class="card card-default">
                                <div class="card-body">
                                    <div class="row ec-vendor-uploads">
                                        <form class="row" action="editpost" method="post" enctype="multipart/form-data" id="editpost">
                                            <div class="col-md-4">
                                                <h5 class="text-center">Thumbnail</h5><br/>
                                                <div class="ec-vendor-img-upload">
                                                    <div class="ec-vendor-main-img">
                                                        <div class="thumb-upload-set colo-md-12">
                                                            <div class="thumb-upload">
                                                                <div class="thumb-edit">
                                                                    <input type='file' id="thumbnail" name="thumbnail" class="ec-image-upload" accept=".png, .jpg, .jpeg"  />
                                                                    <label for="thumbUpload">✎</label>
                                                                </div>
                                                                <div class="thumb-preview ec-preview">
                                                                    <div class="image-thumb-preview">
                                                                        <img class="image-thumb-preview ec-image-preview" src="${not empty post.thumbnail ? post.thumbnail : 'images/logos/originimg.jpg'}"  alt="" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <div class="ec-vendor-upload-detail">
                                                    <!-- Edit post title in here -->
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Post Title<strong/></label>
                                                        <input name="title" type="text" class="form-control slug-title" id="" value="${post.title}">
                                                    </div>
                                                    <!--Edit short description in here -->
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Short Description<strong/></label> 
                                                        <textarea name="shortdes" class="form-control" rows="2">${post.shortDescription}</textarea>
                                                    </div>
                                                    <!--Edit Post Categories here -->
                                                    <div class="col-md-6">
                                                        <label class="form-label">Post Categories</label>
                                                        <select name="category" id="category" class="form-select" onchange="checkForNewCategory(this)">
                                                            <optgroup><!-- Display all other categories -->
                                                                <c:forEach var="l" items="${lPostCategory}">
                                                                    <c:if test="${l.blogCategoryId != bdetail.blogCategory.blogCategoryId}">
                                                                        <option value="${l.blogCategoryId}">${l.blogCategoryName}</option>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <option value="new">Add new category....</option>
                                                            </optgroup>
                                                        </select>
                                                        <div id="newCategoryContainer" style="display: none;">
                                                            <label class="form-label">New Category</label>
                                                            <input type="text" class="form-control" name="newCategory" id="newCategory">
                                                        </div>
                                                    </div>
                                                    <!--Edit author name here -->
                                                    <div class="col-md-6">
                                                        <label class="form-label"><strong>Author<strong/></label>
                                                        <input name="author" type="text" class="form-control" id="" value="${post.author}">
                                                    </div>
                                                    <!-- Edit content in blog detail in here -->
                                                    <div class="col-md-12">
                                                        <label class="form-label"><strong>Content</strong></label>
                                                        <textarea name="content" id="contentEditor" class="form-control">${post.content}</textarea>
                                                    </div>
                                                    <div class="row">
                                                        <!--Edit day create blog here -->
                                                        <div class="col-md-6">
                                                            <label class="form-label"><strong>Day create<strong/></label>
                                                            <input name="blogcreated" type="date" class="form-control" id="" value="<fmt:formatDate value='${post.blogDate}' pattern='yyyy-MM-dd' />">
                                                        </div>
                                                        <!--Edit day update here -->
                                                        <div class="col-md-6">
                                                            <label class="form-label"><strong>Day update<strong/> </label>
                                                            <input name="blogdateupdate" type="date" class="form-control" id="" value="<fmt:formatDate value='${post.blogDateUpdate}' pattern='yyyy-MM-dd' />">
                                                        </div>
                                                    </div>
                                                    <!--Submit button in here-->
                                                    <div class="col-md-12">
                                                        <input type="hidden" name="postID" value="${post.blogDetailId}" />
                                                        <button type="button" class="btn btn-primary" onclick="saveChanges()">Save</button>
                                                    </div>
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
        <jsp:include page="../../common/sale/footer.jsp"/>
        <script>
            function checkForNewCategory(selectElement) {
                var newCategoryContainer = document.getElementById('newCategoryContainer');
                if (selectElement.value === "new") {
                    newCategoryContainer.style.display = 'block';
                } else {
                    newCategoryContainer.style.display = 'none';
                }
            }
            function saveChanges() {
                var formData = new FormData(document.getElementById('editpost'));
                var title = formData.get('title');
                var shortdes = formData.get('shortdes');
                var category = formData.get('category');
                var author = formData.get('author');
                var content = formData.get('content');
                var blogcreated = formData.get('blogcreated');

                // Validate form data
                if (!title || !shortdes || !category || !author || !content || !blogcreated) {
                    alert('Please fill in all fields');
                    return;
                }

                if (confirm("Are you sure to want update?")) {
                    $.ajax({
                        type: "POST",
                        url: "editpost", // Update the URL to match your server-side endpoint
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            alert("Post updated successfully!");
                            setTimeout(function () {
                                document.getElementById('successMessage').style.display = 'block'; // Show the success message
                                setTimeout(function () {
                                    document.getElementById('successMessage').style.display = 'none'; // Hide the success message after 3 seconds
                                }, 3000);
                            }, 0);
                        },
                        error: function (xhr, status, error) {
                            alert("Error updating posts: " + error);
                        }
                    });
                } else {
                    // User cancelled update
                }
            }
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.9.11/tinymce.min.js"></script>
        <script>
            function saveContent() {
                // Trigger the save button click
                document.getElementById("saveButton").click();
            }
            tinymce.init({
                selector: 'textarea#contentEditor',
                height: 500,
                menubar: false,
                plugins: [
                    'advlist autolink lists link image charmap print preview anchor',
                    'searchreplace visualblocks code fullscreen',
                    'insertdatetime media table paste code help wordcount'
                ],
                toolbar: 'undo redo | formatselect | ' +
                        'bold italic backcolor | alignleft aligncenter ' +
                        'alignright alignjustify | bullist numlist outdent indent | ' +
                        'removeformat'
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

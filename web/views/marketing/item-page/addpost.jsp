<%-- 
    Document   : addpost
    Created on : Jun 4, 2024, 9:32:02 AM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-post.css">
        <title>Techno Store - Add Post</title>
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
                            <div><h1><a href="addpost">Add Post</a></h1></div>
                            <div><a href="/TechStore/postlist" class="btn btn-primary">View Post List</a></div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="card card-default">
                                    <div class="card-body">
                                        <div class="row ec-vendor-uploads">
                                            <div class="ec-vendor-upload-detail">
                                                <form class="row" action="addpost" method="post" enctype="multipart/form-data" id="addpost">
                                                    <div class="col-md-6">
                                                        <h4 class="text-center">Add Image <br><span id="thumbnailError" style="color: red;"></span></h4>
                                                        <div class="ec-vendor-img-upload">
                                                            <div class="ec-vendor-main-img">
                                                                <div class="thumb-upload-set colo-md-12">
                                                                    <div><span id="thumbnailError" style="color: red;"></span></div>
                                                                    <div class="thumb-upload">
                                                                        <div class="thumb-edit">
                                                                            <input type='file' name="thumbnail" id="" class="ec-image-upload" accept=".png, .jpg, .jpeg" />
                                                                            <label for="imageUpload">+</label>
                                                                        </div>
                                                                        <div class="thumb-preview ec-preview">
                                                                            <div class="image-thumb-preview">
                                                                                <img class="image-thumb-preview ec-image-preview" src="images\logos\originimg.jpg" alt="" />
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="ec-vendor-upload-detail">
                                                            <div class="col-md-12">
                                                                <label class="form-label">Post Title</label>
                                                                <span id="titleError" style="color: red;"></span>
                                                                <input type="text" class="form-control" name="title" id="title">  </div>
                                                            <div class="col-md-12">
                                                                <label class="form-label">Short Description</label> 
                                                                <span id="shortDescriptionError" style="color: red;"></span>
                                                                <textarea class="form-control" rows="2" name="shortdescription" id="shortdescription" ></textarea>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <label class="form-label">Post Categories</label>
                                                                    <span id="categoryError" style="color: red;"></span>
                                                                    <select name="category" id="category"  class="form-select" onchange="checkForNewCategory(this)">
                                                                        <optgroup>
                                                                            <option value=""></option>
                                                                        <c:forEach var="l" items="${lPostCategory}">
                                                                            <option value="${l.blogCategoryId}">${l.blogCategoryName}</option>
                                                                        </c:forEach>
                                                                        <option value="new">Add new category....</option>
                                                                    </optgroup>
                                                                </select>
                                                                <div id="newCategoryContainer" style="display: none;">
                                                                    <label class="form-label">New Category</label>
                                                                    <input type="text" class="form-control" name="newCategory"  id="newCategory">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label class="form-label">Author </label>
                                                                <span id="authorError" style="color: red;"></span>
                                                                <input type="text" class="form-control" name="author" id="author" >
                                                            </div>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <label class="form-label">Content</label>
                                                            <span id="contentError" style="color: red;"></span>
                                                            <textarea class="form-control" name="content" id="content"></textarea>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <button type="submit" class="btn btn-primary">Create</button>
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
        </div>
        <jsp:include page="../../common/sale/footer.jsp"/>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.9.11/tinymce.min.js"></script>
        <script>
                                                                        tinymce.init({
                                                                            selector: 'textarea#content',
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
        <script>
            function checkForNewCategory(selectElement) {
                var newCategoryContainer = document.getElementById('newCategoryContainer');
                if (selectElement.value === "new") {
                    newCategoryContainer.style.display = 'block';
                } else {
                    newCategoryContainer.style.display = 'none';
                }
            }
            document.getElementById('addpost').addEventListener('submit', function (event) {
                event.preventDefault();
                var thumbnail = document.querySelector('input[name="thumbnail"]').files[0];
                var title = document.getElementById('title');
                var selectedCategory = document.getElementById('category');
                var author = document.getElementById('author');
                var shortdescription = document.getElementById('shortdescription');
                var contentEditor = tinymce.get('content');


                if (thumbnail) {
                    if (!/image\/(png|jpg|jpeg)/.test(thumbnail.type)) {
                        showError('thumbnailError', 'Please select a valid image file (PNG, JPG, JPEG)');
                    } else if (thumbnail.size > 5 * 1024 * 1024) {
                        showError('thumbnailError', 'Thumbnail size must not exceed 5MB');
                    }
                } else {
                    showError('thumbnailError', 'Please select a thumbnail');
                }

                if (title) {
                    var titleValue = title.value;
                    if (titleValue) {
                        titleValue = titleValue.trim();
                        if (titleValue === '') {
                            showError('titleError', 'No title found');
                        } else if (!/^[a-zA-Z0-9\u00C0-\u1EF9 ]+$/.test(titleValue)) {
                            showError('titleError', 'Invalid title format');
                        }
                    } else {
                        showError('titleError', 'No title found');
                    }
                } else {
                    console.error('title element not found');
                }

                if (selectedCategory) {
                    var selectedCategoryValue = selectedCategory.value;
                    if (selectedCategoryValue === '' || selectedCategoryValue === 'new') {
                        showError('categoryError', 'Please select a valid category');
                    }
                } else {
                    console.error('category element not found');
                }

                if (author) {
                    var authorValue = author.value;
                    if (authorValue) {
                        authorValue = authorValue.trim();
                        if (authorValue === '') {
                            showError('authorError', 'No author found');
                        } else if (!/^[\p{L}\d\s-]+$/u.test(authorValue)) {
                            showError('authorError', 'Invalid author format');
                        }
                    } else {
                        showError('authorError', 'No author found');
                    }
                } else {
                    console.error('author element not found');
                }

                if (shortdescription) {
                    var shortdescriptionValue = shortdescription.value;
                    if (shortdescriptionValue) {
                        shortdescriptionValue = shortdescriptionValue.trim();
                        if (shortdescriptionValue === '') {
                            showError('shortDescriptionError', 'Short description is empty!');
                        } else if (/^[0-9]+$/.test(shortdescriptionValue)) {
                            showError('shortDescriptionError', 'Short description must not contain only digits');
                        } else if (/^\s+$/.test(shortdescriptionValue)) {
                            showError('shortDescriptionError', 'Short description cannot consist of only whitespace');
                        } else if (/^[!@#$%^&*()_+{}\[\]:;<>,.?~\\/]+$/.test(shortdescriptionValue)) {
                            showError('shortDescriptionError', 'Short description must not contains special characters');
                        }
                    } else {
                        showError('shortDescriptionError', 'Short description is empty!');
                    }
                } else {
                    console.error('shortdescription element not found');
                }

                if (contentEditor) {
                    var contentValue = contentEditor.getContent();
                    if (contentValue) {
                        contentValue = contentValue.trim();
                        if (contentValue === '') {
                            showError('contentError', 'No content found');
                        } else if (contentValue.length < 10) {
                            showError('contentError', 'Content must be at least 10 characters long');
                        }
                    } else {
                        showError('contentError', 'No content found');
                    }
                } else {
                    console.error('content editor not found');
                }
                // if all fields are valid, submit the form using AJAX
                if (window.confirm("Are you sure you want to add this post?")) {
                    var formData = new FormData(this);
                    formData.append('thumbnail', thumbnail);

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', 'addpost', true);
                    xhr.onload = function () {
                        console.log('AJAX request sent');
                        if (xhr.status === 200) {
                             window.alert("Post added successfully!"); 
                            // Add a flag to indicate that the post has been added
                            document.getElementById('addpost').dataset.added = true;
                        } else {
                            showAlert('Error adding post: ' + xhr.statusText, 'error');
                        }
                    };
                    xhr.send(formData);
                }
            });
            function clearErrorMessages() {
                var errorElements = document.querySelectorAll('.error-message');
                errorElements.forEach(function (element) {
                    element.textContent = '';
                });
            }

            function showError(elementId, errorMessage) {
                if (elementId && elementId !== '') {
                    const errorElement = document.getElementById(elementId);
                    if (errorElement) {
                        errorElement.textContent = errorMessage;
                        setTimeout(() => {
                            errorElement.textContent = '';
                        }, 5000); // 5000 milliseconds = 5 seconds
                    } else {
                        console.error(`Error element with ID ${elementId} not found`);
                    }
                } else {
                    console.error('Invalid elementId parameter');
                }
            }

            function showAlert(message, type) {
                var alertElement = document.createElement("div");
                alertElement.className = "alert alert-" + type;
                alertElement.textContent = message;
                document.body.appendChild(alertElement);
                setTimeout(function () {
                    alertElement.remove();
                }, 3000);
            }

        </script>
        <!-- Common Javascript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>
</html>

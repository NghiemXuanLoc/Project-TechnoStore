<%-- 
    Document   : addproduct
    Created on : Jul 1, 2024, 8:22:05 AM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>Techno Store - Add Product</title>
    </head>
    <body  class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <style>
            .color-circle {
                display: inline-block;
                padding: 6px 12px;
                border: none;
                border-radius: 4px;
                background-color: #f7f7f7;
                color: #333;
                cursor: pointer;
            }

            .color-circle.active {
                background-color: #88aaf3;
                color: #fff;
            }

            .memory-circle {
                display: inline-block;
                padding: 6px 12px;
                border: none;
                border-radius: 4px;
                background-color: #f7f7f7;
                color: #333;
                cursor: pointer;
            }

            .memory-circle.active {
                background-color: #88aaf3;
                color: #fff;
            }
        </style>
        <div class="wrapper">
            <!-- Bắt đầu thanh narvbar -->
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
                <!-- Bắt đầu thanh header -->
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                            <div><h1><a href="addproduct">Add Product</a></h1></div>
                            <div><a href="/TechStore/productslist" class="btn btn-primary">View Product List</a></div>
                        </div>
                        <div id="successMessage" style="text-align: center; color: green"></div>
                        <div id="productExist"style="text-align: center; color: red">${productExist}</div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card card-default">
                                <div class="card-body">
                                    <div class="row ec-vendor-uploads">
                                        <div class="ec-vendor-upload-detail">
                                            <form class="row" action="addproduct" method="Post" enctype="multipart/form-data" id="addproduct">
                                                <div class="col-md-6">
                                                    <h4 class="text-center">Add Image</h4>
                                                    <span id="thumbnailError" style="color: red;"></span>
                                                    <div class="ec-vendor-img-upload">
                                                        <div class="ec-vendor-main-img">
                                                            <div class="thumb-upload-set colo-md-12">
                                                                <div class="thumb-upload">
                                                                    <div class="thumb-edit">
                                                                        <input type='file' name="thumbnail" id="" class="ec-image-upload" accept=".png, .jpg, .jpeg" required/>
                                                                        <label for="imageUpload">+</label>
                                                                    </div>
                                                                    <div class="thumb-preview ec-preview">
                                                                        <div class="image-thumb-preview">
                                                                            <img class="image-thumb-preview  ec-image-preview" src="images\logos\originimg.jpg" alt="" />
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div><br><br>

                                                    <div class="memory-options">
                                                        <h4>Memory:</h4>
                                                        <div id="memory">
                                                            <input type="hidden" name="memoryId" id="memoryId" value=""> <!-- Add a hidden input field for memory -->
                                                            <c:forEach items="${listMemory}" var="memory" varStatus="loop">
                                                                <div style="display: inline-block; margin-right: 5px">
                                                                    <span class="memory-circle" data-memory-id="${memory.memoryId}">
                                                                        <c:choose>
                                                                            <c:when test="${memory.memorySize % 1024 == 0}">
                                                                                <fmt:formatNumber value="${memory.memorySize/1024}" type="number" maxFractionDigits="0"/>&nbsp;TB
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                ${memory.memorySize}&nbsp;GB
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <br><br>
                                                    <div class="color-options">
                                                        <h4>Color:</h4>
                                                        <div class="option" id="color">
                                                            <input type="hidden" name="colorId" id="colorId" value=""> <!-- Add a hidden input field for color -->
                                                            <c:forEach items="${listColor}" var="color" varStatus="loop">
                                                                <div style="display: inline-block; margin-right: 5px">
                                                                    <span class="color-circle" data-color-id="${color.colorId}">${color.colorName}</span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="ec-vendor-upload-detail">
                                                        <div class="col-md-12"><!-- Add product name -->
                                                            <label class="form-label">Product Name</label>
                                                            <span id="productError" style="color: red;"></span>
                                                            <input type="text" class="form-control slug-title" name="productname" id="productname" required="Please input product name!">
                                                        </div>
                                                        <div class="col-md-12"><!-- Add Description information -->
                                                            <label class="form-label">Description</label> 
                                                            <span id="descriptionError" style="color: red;"></span>
                                                            <textarea class="form-control" rows="2" name="description" id="description" ></textarea>
                                                        </div>
                                                        <div class="col-md-12"><!-- Add Description information -->
                                                            <label class="form-label">Brief Information</label> 
                                                            <span id="briefError" style="color: red;"></span>
                                                            <textarea class="form-control" rows="2" name="brief" id="brief"></textarea>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <!-- Add category-->
                                                                <label class="form-label">Product Categories</label>
                                                                <span id="categoryError" style="color: red;"></span>
                                                                <select  name="category" id="category"  class="form-select" onchange="checkForNewCategory(this)">
                                                                    <optgroup>
                                                                        <option value="" disabled selected hidden>Please select a category</option>
                                                                        <c:forEach var="l" items="${listCategory}">
                                                                            <option value="${l.categoryId}">${l.categoryName}</option>
                                                                        </c:forEach>
                                                                        <option value="new">Add new category....</option>
                                                                    </optgroup>
                                                                </select>
                                                                <div id="newCategoryContainer" style="display: none;">
                                                                    <label class="form-label">New Category</label>
                                                                    <input type="text" class="form-control" name="newCategory" id="newCategory" >
                                                                </div>
                                                                <div id="validProduct"style="text-align: center; color: red">${validCategory}</div>
                                                            </div>
                                                            <!-- Add supplier -->
                                                            <div class="col-md-6">
                                                                <label class="form-label">Supplier</label>
                                                                <span id="supplierError" style="color: red;"></span>
                                                                <select  name="supplier" id="supplier"  class="form-select" onchange="checkForNewSupplier(this)">
                                                                    <optgroup>
                                                                        <option value="" disabled selected hidden>Please select a supplier</option>
                                                                        <c:forEach var="l" items="${listSupplier}">
                                                                            <option value="${l.supplierId}">${l.companyName}</option>
                                                                        </c:forEach>
                                                                        <option value="new">Add new supplier....</option>
                                                                    </optgroup>
                                                                </select>
                                                                <div id="newSupplierContainer" style="display: none;">
                                                                    <label class="form-label">New Supplier</label>
                                                                    <input type="text" class="form-control" name="newSupplier" id="newSupplier">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- Add origin price -->            
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label class="form-label">Origin Price</label><span id="priceError" style="color: red;"></span>
                                                                <input required type="text" class="form-control slug-title" name="originprice" id="originprice" placeholder="(VND)">
                                                            </div>
                                                            <!-- Add price sale -->
                                                            <div class="col-md-6">
                                                                <label class="form-label">Sale Price</label> <span id="priceSaleError" style="color: red;"></span>
                                                                <input required type="text" class="form-control slug-title" name="saleprice" id="saleprice" placeholder="(VND)">
                                                            </div>
                                                        </div>   
                                                        <div  class="row">
                                                            <div class="col-md-6">
                                                                <label class="form-label">Quantity</label>
                                                                <input required type="number" class="form-control" name="quantity" id="quantity" min="1">
                                                            </div>
<!--                                                            <div class="col-md-6">
                                                                <label class="form-label">Day create</label>
                                                                <input required type="date" class="form-control" name="datecreated" id="datecreated">
                                                            </div>-->
                                                        </div>
                                                        <div class="col-md-12">
                                                            <button type="submit" class="btn btn-primary">Add</button>
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
        <script>
//            var currentDate = new Date();
//            document.getElementById('datecreated').max = currentDate.toISOString().split('T')[0]; check date
            const quantityInput = document.getElementById('quantity');

            quantityInput.addEventListener('input', () => {
                const currentValue = parseInt(quantityInput.value, 10);
                if (currentValue > 30) {
                    alert('Quantity must be less than or equal to 30');
                    quantityInput.value = 30;
                }
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const colorCircles = document.querySelectorAll(".color-circle");
                const memoryCircles = document.querySelectorAll(".memory-circle");

                colorCircles.forEach((circle) => {
                    circle.addEventListener("click", function () {
                        const colorId = circle.getAttribute("data-color-id");
                        document.getElementById("colorId").value = colorId;
                        colorCircles.forEach((otherCircle) => {
                            otherCircle.classList.remove("active");
                        });
                        circle.classList.add("active");
                        // Add your logic to handle the color selection here
                    });
                });

                memoryCircles.forEach((circle) => {
                    circle.addEventListener("click", function () {
                        const memoryId = circle.getAttribute("data-memory-id");
                        document.getElementById("memoryId").value = memoryId;
                        memoryCircles.forEach((otherCircle) => {
                            otherCircle.classList.remove("active");
                        });
                        circle.classList.add("active");
                        // Add your logic to handle the memory selection here
                    });
                });
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
            function checkForNewSupplier(selectElement) {
                var newSupplierContainer = document.getElementById('newSupplierContainer');
                if (selectElement.value === "new") {
                    newSupplierContainer.style.display = 'block';
                } else {
                    newSupplierContainer.style.display = 'none';
                }
            }
            const form = document.getElementById("addproduct");
            form.addEventListener("submit", (e) => {
                e.preventDefault();
                var productname = document.getElementById('productname').value;
                var description = document.getElementById('description').value;
                var category = document.getElementById('category').value;
                var newCategory = document.getElementById('newCategory').value;
                var supplier = document.getElementById('supplier').value;
                var newSupplier = document.getElementById('newSupplier').value;
                var originprice = document.getElementById('originprice').value;
                var saleprice = document.getElementById('saleprice').value;
                clearErrorMessages();

                var hasError = false;

                // check nhập liệu tên sản phẩm
                if (productname.trim() === '') {
                    showError('productError', 'Product name must not have space!');
                    hasError = true;
                } else if (!/^[a-zA-Z0-9\s\-&]+$/u.test(productname)) {
                    showError('productError', 'Invalid product name format');
                    hasError = true;
                }
                if (/^[^a-zA-Z]/.test(productname.trim())) {
                    showError('productError', 'Product name must start with a letter');
                    hasError = true;
                }

                // check nhập liệu thông tin của sản phẩm
                if (description.trim() === '') {
                    showError('descriptionError', 'Description must not have space!');
                    hasError = true;
                } else if (/^[!@#$%^&*()_+{}\[\]:;<>,.?~\\/]+$/.test(description)) {
                    showError('descriptionError', 'Description cannot consist of only special characters');
                    hasError = true;
                } else if (/^\d+$/.test(description)) {
                    showError('descriptionError', 'Description cannot consist of only digits');
                    hasError = true;
                } else if (/^\d/.test(description)) {
                    showError('descriptionError', 'Description cannot start with a digit');
                    hasError = true;
                } else if (/^[!@#$%^&*()_+{}\[\]:;<>,.?~\\/]/.test(description)) {
                    showError('descriptionError', 'Description cannot start with a special character');
                    hasError = true;
                }

                // check nhập liệu thể loại sản phẩm    
                if (category === '' || category === 'new' && newCategory.trim() === '') {
                    showError('categoryError', 'Please select or enter a category');
                    hasError = true;
                } else if (category === 'new' && !/^[a-zA-Z]+$/.test(newCategory)) {
                    showError('categoryError', 'New category can only contain letters');
                    hasError = true;
                }

                // check nhập liệu tên nhà cung cấp sản phẩm
                if (supplier === '' || supplier === 'new' && newSupplier.trim() === '') {
                    showError('supplierError', 'Please select or enter a supplier');
                    hasError = true;
                } else if (supplier === 'new' && !/^[a-zA-Z ]+$/.test(newSupplier.trim())) {
                    showError('supplierError', 'New supplier can only contain letters and spaces');
                    hasError = true;
                }

                // check nhập liệu giá gốc và giá sau khi sale sản phẩm
                if (originprice.trim() === '' || saleprice.trim() === '') {
                    showError('priceError', 'Origin price are required');
                    showError('priceSaleError', 'Sale price are required');
                    hasError = true;
                } else if (isNaN(originprice) || isNaN(saleprice)) {
                    showError('priceError', 'Prices must be numbers');
                    showError('priceSaleError', 'Prices must be numbers');
                    hasError = true;
                } else if (parseFloat(originprice) <= 0 || parseFloat(saleprice) <= 0) {
                    showError('priceError', 'Prices must be positive and greater than 0');
                    showError('priceSaleError', 'Prices must be positive and greater than 0');
                    hasError = true;
                } else if (parseFloat(saleprice) >= parseFloat(originprice)) {
                    showError('priceSaleError', 'Sale price must be less than origin price');
                    hasError = true;
                }

                if (!hasError) {
                    const formData = new FormData(form);
                    fetch("addproduct", {
                        method: "POST",
                        body: formData,
                    })
                            .then((response) => response.json())
                            .then((jsonResponse) => {
                                if (jsonResponse.error) {
                                    alert(jsonResponse.error);
                                } else {
                                    alert("Product added successfully!");
                                }
                            })
                            .catch((error) => {
                                alert("Product added successfully!");
                            });
                }
            });

            function hasErrors() {
                var errorElements = document.querySelectorAll('.error-message');
                return Array.prototype.some.call(errorElements, function (element) {
                    return element.textContent !== '';
                });
            }

            function clearErrorMessages() {
                var errorElements = document.querySelectorAll('.error-message');
                errorElements.forEach(function (element) {
                    element.textContent = '';
                });
            }

            function showError(id, message) {
                var errorElement = document.getElementById(id);
                errorElement.textContent = message;
                setTimeout(function () {
                    errorElement.textContent = '';
                }, 3000);
            }

            function showAlert(message, type) {
                var alertElement = document.createElement("div");
                alertElement.className = "alert alert-" + type;
                alertElement.textContent = message;
                document.getElementById('successMessage').appendChild(alertElement);
                setTimeout(function () {
                    alertElement.remove();
                }, 3000);
                document.getElementById('successMessage').style.display = 'block';
            }
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
    </body>
</html>

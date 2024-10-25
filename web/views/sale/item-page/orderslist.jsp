
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="controller.user.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard eCommerce HTML Template.">

        <title>OrderList</title>

        <!-- GOOGLE FONTS -->
        <link rel="preconnect" href="https://fonts.googleapis.com/">
        <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;300;400;500;600;700;800&amp;family=Poppins:wght@300;400;500;600;700;800;900&amp;family=Roboto:wght@400;500;700;900&amp;display=swap" rel="stylesheet"> 

        <!--<link href="../../../../../cdn.jsdelivr.net/npm/%40mdi/font%404.4.95/css/materialdesignicons.min.css" rel="stylesheet" />-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">

        <!-- PLUGINS CSS STYLE -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/daterangepicker.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">

        <!-- Ekka CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <!-- FAVICON -->
        <link rel="images" href="${pageContext.request.contextPath}/images/icons/favicon.png">


    </head>

    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">

        <!--  WRAPPER  -->
        <div class="wrapper">

            <!-- begin sidebar scrollbar -->

            <jsp:include page="../../common/sale/narvbar.jsp"></jsp:include>
                <!-- Header -->
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <!--xử lí logic load từ database-->

            <c:set var="typeTable" value='${requestScope.listType}'/>
            <c:set var="currentStatus" value="${empty param.status ? '' : param.status}" />

            <div class="ec-page-wrapper">
                <div class="ec-content-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper breadcrumb-wrapper-2">
                            <h1>Orders History</h1>
                        </div>
                        <div class="breadcrumb-wrapper breadcrumb-wrapper-2">
                            <button onclick="ShortedList();">Switch to ${listType == 'shorted' ? 'all' : 'shorted'} list</button>
                        </div>
                        <div class="row">


                            <div class="col-md-12 d-flex">
                                <!-- Form để lọc theo ngày và tên đơn hàng -->
                                <form id="filterForm" action="/TechStore/saleorderlist" method="get">
                                    <div class="d-flex align-items-end">
                                        <div class="me-3">
                                            <label style="color: black" for="startDate" class="text-danger w-60">Start Date:</label>
                                            <input type="date" id="startDate" name="startDate" value="${requestScope.startDate}" class="form-control form-control-sm w-90">
                                        </div>
                                        <div class="me-3">
                                            <label style="color: black" for="endDate" class="text-danger w-60">End Date:</label>
                                            <input type="date" id="endDate" name="endDate" value="${requestScope.endDate}" class="form-control form-control-sm w-90">
                                        </div>
                                        <div class="me-3">
                                            <label style="color: black" for="productName" class="text-danger w-60">Order Name</label>
                                            <input type="text" id="productName" name="productname" value="${requestScope.productname}" class="form-control form-control-sm w-90">
                                        </div>
                                        <div class="me-3">
                                            <label style="color: black" for="searchKeyword" class="text-danger w-60">Search by Order ID or Customer Name</label>
                                            <input type="text" id="searchKeyword" name="searchKeyword" value="${requestScope.keysearch}" class="form-control form-control-sm w-90">
                                        </div>
                                        <div class="me-3">
                                            <label style="color: black; margin-left: 10px;" for="status" class="text-danger w-60">Status:</label>
                                            <select id="status" name="status" class="form-control form-control-sm w-90">
                                                <c:set var="currentStatus" value="${empty param.status ? '' : param.status}" />
                                                <option value="" ${empty param.status ? 'selected' : ''}>All</option>
                                                <option value="2" ${currentStatus == '2' ? 'selected' : ''}>Completed</option>
                                                <option value="1" ${currentStatus == '1' ? 'selected' : ''}>Cancelled</option>
                                                <option value="0" ${currentStatus == '0' ? 'selected' : ''}>Unpaid</option>
                                            </select>
                                        </div>
                                        <input type="hidden" name="pageSize" value="${pageSize}"/>
                                        <input type="hidden" name="page" value="${currentPage}"/>
                                        <input type="hidden" id="listTypeInput" name="listType" value="${listType}"/> <!-- Add this line -->
                                        <button type="submit" class="btn btn-primary btn-sm">Filter</button>

                                    </div>
                                </form>
                            </div>
                        </div>


                        <!-- Shorted list -->
                        <div class="row" id="hiddenTable1" style="${listType == 'shorted' ? '' : 'display:none;'}">
                            <div class="col-12">
                                <div class="card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table" style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th class="orderDate">Order_Date</th>
                                                        <th class="fullName">FullName</th>
                                                        <th class="totalMoney">Total Money</th>
                                                        <th class="status">Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="orderTableBody">
                                                    <c:forEach var="order" items="${orderlist}">
                                                        <tr>
                                                            <td>${order.Order_date}</td>
                                                            <td>${order.Full_name}</td>
                                                            <td>${order.Price}</td>
                                                            <td>
                                                                <span class="badge ${order.Status == 2 ? 'badge-success' : order.Status == 1 ? 'badge-danger' : 'badge-warning'}">
                                                                    ${order.Status == 2 ? 'Completed' : order.Status == 1 ? 'Cancelled' : 'Unpaid'}
                                                                </span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <div class="d-flex justify-content-between">
                                                <form id="pageSizeSelect" method="get" action="/TechStore/saleorderlist">
                                                    <select id="pageSizeSelectTop" name="pageSize" class="form-control w-auto" onchange="submitFormWithListType()">
                                                        <option value="5" ${pageSize == 5 ? 'selected' : ''}>5</option>
                                                        <option value="10" ${pageSize == 10 ? 'selected' : ''}>10</option>
                                                        <option value="15" ${pageSize == 15 ? 'selected' : ''}>15</option>
                                                    </select>
                                                    <input type="hidden" id="startDateHidden" name="startDate" value="${requestScope.startDate}">
                                                    <input type="hidden" id="endDateHidden" name="endDate" value="${requestScope.endDate}">
                                                    <input type="hidden" id="productNameHidden" name="productname" value="${requestScope.productname}">
                                                    <input type="hidden" id="statusHidden" name="status" value="${currentStatus}">
                                                    <input type="hidden" id="searchKeywordHidden" name="searchKeyword" value="${requestScope.keysearch}">
                                                    <input type="hidden" id="listTypeInput" name="listType" value="${listType}">
                                                </form>
                                                <nav>
                                                    <ul class="pagination">
                                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                                <a class="page-link" href="saleorderlist?page=${i}&pageSize=${pageSize}&listType=${listType}&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}&productname=${requestScope.productname}&searchKeyword=${requestScope.keysearch}&status=${currentStatus}">${i}</a>
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

                        <!-- All list -->
                        <div class="row" id="hiddenTable2" style="${listType == 'all' ? '' : 'display:none;'}">
                            <div class="col-12">
                                <div class="card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table" style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th class="orderId">Id</th>
                                                        <th class="orderDate sortable">Order Date</th>
                                                        <th class="fullName sortable">Full Name</th>
                                                        <th class="productName">Product Details</th>
                                                       
                                                        <th class="totalMoney sortable">Total Money</th>
                                                        <th class="status sortable">Status</th>
                                                        <th>DetailOrder</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="orderTableBody">
                                                    <c:forEach var="order" items="${orderlist}">
                                                        <tr>
                                                            <td>${order.Order_id}</td>
                                                            <td>${order.Order_date}</td>
                                                            <td>${order.Full_name}</td>
                                                            <td><c:out value="${order.Product_details}" escapeXml="false" /></td>

                                                            <td>${order.Total_money} VND</td>
                                                            <td>
                                                                <span class="badge ${order.Status == 2 ? 'badge-success' : order.Status == 1 ? 'badge-danger' : 'badge-warning'}">
                                                                    ${order.Status == 2 ? 'Completed' : order.Status == 1 ? 'Cancelled' : 'Unpaid'}
                                                                </span>
                                                            </td>
                                                            <td><a href="/TechStore/saleorderdetail?orderId=${order.Order_id}" class="btn btn-primary">Detail</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <div class="d-flex justify-content-between">
                                                <form id="pageSizeFormBottom" method="get" action="/TechStore/saleorderlist">
                                                    <select id="pageSizeSelectBottom" name="pageSize" class="form-control w-auto" onchange="submitFormWithListType()">
                                                        <option value="5" ${pageSize == 5 ? 'selected' : ''}>5</option>
                                                        <option value="10" ${pageSize == 10 ? 'selected' : ''}>10</option>
                                                        <option value="15" ${pageSize == 15 ? 'selected' : ''}>15</option>
                                                    </select>
                                                    <input type="hidden" id="startDateHiddenBottom" name="startDate" value="${requestScope.startDate}">
                                                    <input type="hidden" id="endDateHiddenBottom" name="endDate" value="${requestScope.endDate}">
                                                    <input type="hidden" id="productNameHiddenBottom" name="productname" value="${requestScope.productname}">
                                                    <input type="hidden" id="statusHiddenBottom" name="status" value="${requestScope.status}">
                                                    <input type="hidden" id="searchKeywordHiddenBottom" name="searchKeyword" value="${requestScope.keysearch}">
                                                    <input type="hidden" id="listTypeHiddenBottom" name="listType" value="${listType}">
                                                </form>
                                                <nav>
                                                    <ul class="pagination">
                                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                                <a class="page-link" href="saleorderlist?page=${i}&pageSize=${pageSize}&listType=${listType}&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}&productname=${requestScope.productname}&searchKeyword=${requestScope.keysearch}&status=${currentStatus}">${i}</a>
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

                    </div> <!-- End Page Wrapper -->
                </div> <!-- End Wrapper -->
            </div>
        </div>
        <jsp:include page="../../common/sale/footer.jsp"/>



        <script>
            document.addEventListener("DOMContentLoaded", () => {
                // Hàm chuyển đổi số khoa học sang dạng số thực
                function formatNumber(num) {
                    return parseFloat(num).toLocaleString('en-US', {maximumFractionDigits: 2});
                }

                // Lặp qua các ô dữ liệu Total Money và định dạng lại giá trị
                document.querySelectorAll('.totalMoney').forEach(td => {
                    let value = td.textContent.trim();
                    // Chuyển đổi giá trị
                    td.textContent = formatNumber(value);
                });
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", () => {
                const getCellValue = (tr, idx) => tr.children[idx].innerText || tr.children[idx].textContent;

                const comparer = (idx, asc) => (a, b) => ((v1, v2) =>
                            v1 !== '' && v2 !== '' && !isNaN(v1) && !isNaN(v2) ? v1 - v2 : v1.toString().localeCompare(v2)
                        )(getCellValue(asc ? a : b, idx), getCellValue(asc ? b : a, idx));

                document.querySelectorAll('.sortable').forEach(th => th.addEventListener('click', (() => {
                        const table = th.closest('table');
                        const tbody = table.querySelector('tbody');
                        Array.from(tbody.querySelectorAll('tr'))
                                .sort(comparer(Array.from(th.parentNode.children).indexOf(th), this.asc = !this.asc))
                                .forEach(tr => tbody.appendChild(tr));
                        table.querySelectorAll('.sortable').forEach(el => el.classList.remove('desc'));
                        th.classList.toggle('desc', !this.asc);
                    })));
            });
        </script>

        <script>
            function ShortedList() {
                var hiddenTable1 = document.getElementById('hiddenTable1');
                var hiddenTable2 = document.getElementById('hiddenTable2');
                var listTypeInput = document.getElementById('listTypeInput');
                var currentListType = listTypeInput.value;
                if (currentListType === 'shorted') {
                    hiddenTable1.style.display = 'none';
                    hiddenTable2.style.display = 'block';
                    listTypeInput.value = 'all';
                } else {
                    hiddenTable1.style.display = 'block';
                    hiddenTable2.style.display = 'none';
                    listTypeInput.value = 'shorted';
                }

                // Submit the form to persist the listType
                document.getElementById('filterForm').submit();
            }

            document.addEventListener("DOMContentLoaded", function () {
                var pageSizeSelectTop = document.getElementById("pageSizeSelectTop");
                var pageSizeSelectBottom = document.getElementById("pageSizeSelectBottom");
                if (pageSizeSelectTop && pageSizeSelectBottom) {
                    pageSizeSelectTop.addEventListener("change", function () {
                        pageSizeSelectBottom.value = pageSizeSelectTop.value;
                        document.getElementById('pageSizeFormBottom').submit();
                    });
                    pageSizeSelectBottom.addEventListener("change", function () {
                        pageSizeSelectTop.value = pageSizeSelectBottom.value;
                        document.getElementById('pageSizeSelect').submit();
                    });
                }
            });

            function submitFormWithListType() {
                var listType = document.getElementById('listTypeInput').value;
                document.getElementById('listType').value = listType;
                document.getElementById('pageSizeSelect').submit(); // Submit the correct form based on listType
            }
        </script>
        <!--        <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        var pageSizeSelectTop = document.getElementById("pageSizeSelect");
                        var pageSizeSelectBottom = document.getElementById("pageSizeSelectBottom");
        
                        if (pageSizeSelectTop && pageSizeSelectBottom) {
                            pageSizeSelectTop.addEventListener("change", function () {
                                pageSizeSelectBottom.value = pageSizeSelectTop.value;
                            });
        
                            pageSizeSelectBottom.addEventListener("change", function () {
                                pageSizeSelectTop.value = pageSizeSelectBottom.value;
                            });
                        }
                    });
        
                </script>-->
        <!--Common Javascript-->

<!--<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/simplebar.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.zoom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/slick.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Chart.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/chart.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/google-map-loader.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/google-map.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/optionswitcher.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/daterangepicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/date-range.js"></script>




    </body>
</html>
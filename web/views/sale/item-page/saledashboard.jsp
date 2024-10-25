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

        <title>Sale Dashbroads</title>

        <!-- GOOGLE FONTS -->
        <link rel="preconnect" href="https://fonts.googleapis.com/">
        <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;300;400;500;600;700;800&amp;family=Poppins:wght@300;400;500;600;700;800;900&amp;family=Roboto:wght@400;500;700;900&amp;display=swap" rel="stylesheet"> 

        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <!--<link href="../../../../../cdn.jsdelivr.net/npm/%40mdi/font%404.4.95/css/materialdesignicons.min.css" rel="stylesheet" />-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">

        <!-- PLUGINS CSS STYLE -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/daterangepicker.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">

        <!-- Ekka CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <!-- FAVICON -->
        <link rel="images" href="${pageContext.request.contextPath}/images/icons/favicon.png">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/dashboardsalestyles.css">
        <!-- Montserrat Font -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">

        <div class="wrapper">

            <!-- begin sidebar scrollbar -->

            <jsp:include page="../../common/sale/narvbar.jsp"></jsp:include>
                <!-- Header -->
            <%--<jsp:include page="../../common/sale/header.jsp"></jsp:include>--%>
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>

                <div class="ec-page-wrapper">
                <c:if test="${not empty sessionScope.failPath}">
                    <div class="alert alert-warning">
                        <strong>Warning!</strong> ${failPath}.
                    </div>
                    <% session.removeAttribute("failPath"); %>
                </c:if>
                <div class="content">

                    <!-- Top Statistics -->
                    <div class="row">
                        <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
                            <div class="card card-mini dash-card card-1">
                                <div class="card-body">
                                    <h2 class="mb-1">${requestScope.totalOrders}</h2>
                                    <p>Total Order</p>
                                    <span class="mdi mdi-account-arrow-left"></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
                            <div class="card card-mini dash-card card-2">
                                <div class="card-body">
                                    <h2 class="mb-1">$${requestScope.totalMoneySold}</h2>
                                    <p>Total Money</p>
                                    <span class="mdi mdi-account-clock"></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
                            <div class="card card-mini dash-card card-3">
                                <div class="card-body">
                                    <h2 class="mb-1">120</h2>
                                    <p>Total FeedBack</p>
                                    <span class="mdi mdi-package-variant"></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
                            <div class="card card-mini dash-card card-4">
                                <div class="card-body">
                                    <h2 class="mb-1">${requestScope.totalUsers}</h2>
                                    <p>Total User</p>
                                    <span class="mdi mdi-currency-usd"></span>
                                </div>
                            </div>
                        </div>
                    </div>






                    <div class="card-header">
                        <h3>ORDER SUCCESS TRENDS CHART</h3>
                    </div>
                    <div class="d-flex justify-content-between">
                        <form action="/TechStore/saledashboard" method="get">
                            <div class="d-flex align-items-end">
                                <div class="me-3">
                                    <label style="color: red" for="startDate" class="text-danger w-60">Start Date:</label>
                                    <input type="date" id="startDate" name="startDate" value="${defaultStartDate1}" class="form-control form-control-sm w-90">
                                </div>
                                <div class="me-3">
                                    <label style="color: red" for="endDate" class="text-danger w-60">End Date:</label>
                                    <input type="date" id="endDate" name="endDate" value="${defaultEndDate1}" class="form-control form-control-sm w-90">
                                </div>
                                <button type="submit" class="btn btn-primary btn-sm">Filter</button>
                            </div>
                        </form>
                    </div>

                    <div class="row">
                        <div class="col-lg-8 col-md-12">
                            <div class="charts-card">
                                <h2 class="chart-title">Total Orders and Success Orders</h2>
                                <div id="area-chart"></div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-12">
                            <!-- Doughnut Chart -->
                            <div class="card card-default">
                                <div class="card-header justify-content-center">
                                    <h2>Orders Overview</h2>
                                </div>
                                <div class="card-body">
                                    <canvas id="doChart"></canvas>
                                </div>

                                <div class="card-footer d-flex flex-wrap bg-white p-0">
                                    <div class="col-12">
                                        <div class="p-20">
                                            <ul class="d-flex flex-column justify-content-between">
                                                <li class="mb-2">
                                                    <i class="mdi mdi-checkbox-blank-circle-outline mr-2" style="color: #21D788"></i>Order Completed
                                                </li>
                                                <li class="mb-2">
                                                    <i class="mdi mdi-checkbox-blank-circle-outline mr-2" style="color: #4c84ff"></i>Order Unpaid
                                                </li>
                                                <li class="mb-2"><i class="mdi mdi-checkbox-blank-circle-outline mr-2"style="color: #DEAD27">
                                                    </i>Order Canceled
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--</main>-->
                </div>
                <div class="d-flex justify-content-between">
                    <form action="/TechStore/saledashboard" method="get">
                        <div class="d-flex align-items-end">
                            <div class="me-3">
                                <label style="color: red" for="startDate" class="text-danger w-60">Start Date:</label>
                                <input type="date" id="startDate" name="startDate2" value="${defaultStartDate}" class="form-control form-control-sm w-90">
                            </div>
                            <div class="me-3">
                                <label style="color: red" for="endDate" class="text-danger w-60">End Date:</label>
                                <input type="date" id="endDate" name="endDate2" value="${defaultEndDate}" class="form-control form-control-sm w-90">
                            </div>
                            <button type="submit" class="btn btn-primary btn-sm">Filter</button>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <div class="col-xl-8 col-md-12 p-b-15">
                        <!-- Sales Graph -->
                        <div id="user-acquisition" class="card card-default">
                            <div class="card-header">
                                <h2>Revenue Trends by Day</h2>
                            </div>
                            <div class="card-body">
                                <ul class="nav nav-tabs nav-style-border justify-content-between justify-content-lg-start border-bottom"
                                    role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" data-bs-toggle="tab" href="#todays" role="tab"
                                           aria-selected="true">Today's</a>
                                    </li>
                                    <!--                                        <li class="nav-item">
                                                                                <a class="nav-link" data-bs-toggle="tab" href="#monthly" role="tab"
                                                                                   aria-selected="false">Monthly </a>
                                                                            </li>
                                                                                                                    <li class="nav-item">
                                                                                                                        <a class="nav-link" data-bs-toggle="tab" href="#yearly" role="tab"
                                                                                                                           aria-selected="false">Yearly</a>
                                                                                                                    </li>-->
                                </ul>
                                <div class="tab-content pt-4" id="salesReport">
                                    <div class="tab-pane fade show active" id="source-medium" role="tabpanel">
                                        <div class="mb-6" style="max-height:247px">
                                            <canvas id="acquisition" class="chartjs2"></canvas>
                                            <div id="acqLegend" class="customLegend mb-2"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>






                    <div class="col-12 p-b-15">
                        <!-- Recent Order Table -->
                        <div class="card card-table-border-none card-default recent-orders" id="recent-orders">
                            <div class="card-header justify-content-between">
                                <h2>Recent Orders</h2>
                                <div class="date-range-report">
                                    <form id="dateRangeForm" method="get" action="saledashboard" class="form-inline mb-3">
                                        <div class="form-group d-flex">
                                            <label for="dateRange" class="mr-2">Select Date Range:</label>
                                            <select name="dateRange" id="dateRange" class="form-control mr-2" onchange="toggleCustomDateRange()">
                                                <option value="yesterday" ${param.dateRange == 'yesterday' ? 'selected' : ''}>Yesterday</option>
                                                <option value="today" ${param.dateRange == 'today' ? 'selected' : ''}>Today</option>
                                                <option value="custom" ${param.dateRange == 'custom' ? 'selected' : ''}>From-To</option>
                                            </select>
                                            <div id="customDateRange" style="display: none;">
                                                <input type="date" name="startDate3" id="startDate" class="form-control mr-2" value="${param.startDate3}">
                                                <input type="date" name="endDate3" id="endDate" class="form-control mr-2" value="${param.endDate3}">
                                            </div>
                                            <button type="submit" class="btn btn-primary">Apply</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="card-body pt-0 pb-5">
                                <table class="table card-table table-responsive table-responsive-large"
                                       style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Product Details</th>
                                            <th class="d-none d-lg-table-cell">Order Date</th>
                                            <th class="d-none d-lg-table-cell">Order Cost</th>
                                            <th>Status</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="order" items="${recentorders}">
                                            <tr>
                                                <td>${order.Order_id}</td>

                                                <td><c:out value="${order.Product_details}" escapeXml="false" /></td>
                                                <td class="d-none d-lg-table-cell">${order.Order_date}</td>
                                                <td>${order.Total_money} VND</td>
                                                <td>
                                                    <span class="badge ${order.Status == 2 ? 'badge-success' : order.Status == 1 ? 'badge-danger' : 'badge-warning'}">
                                                        ${order.Status == 2 ? 'Completed' : order.Status == 1 ? 'Cancelled' : 'Unpaid'}
                                                    </span>
                                                </td>
                                                <td class="text-right">
                                                    <div class="dropdown show d-inline-block widget-dropdown">
                                                        <a class="dropdown-toggle icon-burger-mini" href="#" role="button"
                                                           id="dropdown-recent-order${order.Order_id}"
                                                           data-bs-toggle="dropdown" aria-haspopup="true"
                                                           aria-expanded="false" data-display="static"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <li class="dropdown-item">
                                                                <a href="#">View</a>
                                                            </li>
                                                            <c:if test="${order.Status == 0 && (order.saleId == requestScope.userId || requestScope.userId == 11)}">
                                                                
                                                                <li class="dropdown-item">
                                                                    <a href="#" data-bs-toggle="modal" data-bs-target="#editOrderStatusModal${order.Order_id}">Edit</a>
                                                                </li>
                                                            </c:if>
                                                        </ul>
                                                    </div>
                                                </td>
                                        <div class="modal fade" id="editOrderStatusModal${order.Order_id}" tabindex="-1" aria-labelledby="editOrderStatusModalLabel${order.Order_id}" aria-hidden="true" data-backdrop="false">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="editOrderStatusModalLabel${order.Order_id}">Edit Order Status</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form id="editOrderStatusForm${order.Order_id}" action="updateorderstatus" method="post">
                                                            <input type="hidden" name="orderId" value="${order.Order_id}" />

                                                            <div class="mb-3">
                                                                <label for="orderStatus${order.Order_id}" class="form-label">Status</label>
                                                                <select class="form-select" id="orderStatus${order.Order_id}" name="status">
                                                                    <option value="2" ${order.Status == 2 ? 'selected' : ''}>Completed</option>
                                                                    <option value="1" ${order.Status == 1 ? 'selected' : ''}>Cancelled</option>
                                                                    <option value="0" ${order.Status == 0 ? 'selected' : ''}>Unpaid</option>
                                                                </select>
                                                            </div>
                                                            <c:if test="${not empty sessionScope.successUpdateStatus}">
                                                                <h5 style="color: red">${sessionScope.successUpdateStatus}</h5>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.errUpdateStatus}">
                                                                <h5 style="color: red">${sessionScope.errUpdateStatus}</h5>
                                                            </c:if>
                                                            <div class="row">
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                                                </div>
                                                        </form>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>       
                                </div>

                                </tr>
                            </c:forEach>


                            </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div> <!-- End Content -->
</div> <!-- End Content Wrapper -->

<!-- CONTENT WRAPPER -->


<jsp:include page="../../common/sale/footer.jsp"/>

<!-- Scripts -->
<!-- ApexCharts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.35.5/apexcharts.min.js"></script>

Custom JS 
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>




<script>
                                                document.addEventListener("DOMContentLoaded", function () {
                                                var dateRange = document.getElementById("dateRange");
                                                var customDateRange = document.getElementById("customDateRange");
                                                dateRange.addEventListener("change", function () {
                                                if (this.value === "custom") {
                                                customDateRange.style.display = "block"; // Hiển thị trường nhập ngày nếu người dùng chọn "From-To"
                                                } else {
                                                customDateRange.style.display = "none"; // Ẩn trường nhập ngày nếu người dùng chọn tùy chọn khác
                                                }
                                                });
                                                });</script>

<script>
    //BIỂU ĐỒ ĐẦU TIÊN
    // AREA CHART đã hoàn thành
    const areaChartOptions = {
    series: [
    {
    name: 'Total Orders',
            data: [<c:forEach var="map" items="${chart}">${map.totalOrders},</c:forEach>],
    },
    {
    name: 'Success Orders',
            data: [<c:forEach var="map" items="${chart}">${map.successOrders},</c:forEach>],
    },
//    {
//    name: 'Success Ratio',
//            data: [<c:forEach var="map" items="${chart}">${map.successRatio},</c:forEach>],
//    },
    ],
            chart: {
            type: 'area',
                    background: 'transparent',
                    height: 350,
                    stacked: false,
                    toolbar: {
                    show: false,
                    },
            },
            colors: ['#d50000', '#00ab57', '#1E90FF'], // Added color for Success Ratio
            labels: [<c:forEach var="map" items="${chart}">'${map.orderDate}',</c:forEach>],
            dataLabels: {
            enabled: false,
            },
            fill: {
            gradient: {
            opacityFrom: 0.4,
                    opacityTo: 0.1,
                    shadeIntensity: 1,
                    stops: [0, 100],
                    type: 'vertical',
            },
                    type: 'gradient',
            },
            grid: {
            borderColor: '#55596e',
                    yaxis: {
                    lines: {
                    show: true,
                    },
                    },
                    xaxis: {
                    lines: {
                    show: true,
                    },
                    },
            },
            legend: {
            labels: {
            colors: '#f5f7ff',
            },
                    show: true,
                    position: 'top',
            },
            markers: {
            size: 6,
                    strokeColors: '#1b2635',
                    strokeWidth: 3,
            },
            stroke: {
            curve: 'smooth',
            },
            xaxis: {
            axisBorder: {
            color: '#55596e',
                    show: true,
            },
                    axisTicks: {
                    color: '#55596e',
                            show: true,
                    },
                    labels: {
                    offsetY: 5,
                            style: {
                            colors: '#f5f7ff',
                            },
                    },
            },
            yaxis: [
            {
            title: {
            text: 'Total Orders',
                    style: {
                    color: '#f5f7ff',
                    },
            },
                    labels: {
                    style: {
                    colors: ['#f5f7ff'],
                    },
                    },
            },
            {
            opposite: true,
                    title: {
                    text: 'Success Orders',
                            style: {
                            color: '#f5f7ff',
                            },
                    },
                    labels: {
                    style: {
                    colors: ['#f5f7ff'],
                    },
                    },
            },
            ],
            tooltip: {
            shared: true,
                    intersect: false,
                    theme: 'dark',
            },
    };
    const areaChart = new ApexCharts(
            document.querySelector('#area-chart'),
            areaChartOptions
            );
    areaChart.render();
    </script>
    <script>
        function updateAreaChart(newData) {
        areaChart.updateSeries([
        { name: 'Total Orders', data: newData.totalOrders },
        { name: 'Success Orders', data: newData.successOrders },
        { name: 'Success Ratio', data: newData.successRatio }
        ]);
// Cập nhật nhãn trục x
        areaChart.updateOptions({
        labels: newData.orderDates
        });
// Xử lý sự kiện thay đổi ngày
        $('#startDate, #endDate').change(function() {
        var startDate = $('#startDate').val();
        var endDate = $('#endDate').val();
// Gửi yêu cầu AJAX để lấy dữ liệu mới từ servlet
        $.ajax({
        type: 'GET',
                url: '/TechStore/saledashboard',
                data: {
                startDate: startDate,
                        endDate: endDate
                },
                success: function(data) {
// Cập nhật biểu đồ với dữ liệu mới từ servlet
                updateAreaChart(data);
                },
                error: function(xhr, status, error) {
                console.error(error);
                }
        });
        });
        }
    </script>
    <!--Revenue list-->
    <script>
        $(document).ready(function () {
        "use strict";
        var acquisition = document.getElementById("acquisition");
        if (acquisition !== null) {
// Lấy dữ liệu doanh thu hàng ngày
        var revenueData = [<c:forEach var="map" items="${revenue}">${map.dailyRevenue},</c:forEach>];
        var labels = [<c:forEach var="map" items="${revenue}">'${map.orderDate}',</c:forEach>];
        var configAcq = {
// Loại biểu đồ
        type: "line",
// Dữ liệu cho biểu đồ
                data: {
                labels: labels,
                        datasets: [
                        {
                        label: "Daily Revenue",
                                backgroundColor: "rgba(52, 116, 212, .2)",
                                borderColor: "rgba(52, 116, 212, .7)",
                                data: revenueData,
                                lineTension: 0.3,
                                pointBackgroundColor: "rgba(52, 116, 212,0)",
                                pointHoverBackgroundColor: "rgba(52, 116, 212,1)",
                                pointHoverRadius: 3,
                                pointHitRadius: 30,
                                pointBorderWidth: 2,
                                pointStyle: "rectRounded"
                        }
                        ]
                },
// Các tùy chọn cấu hình khác
                options: {
                responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                        display: false
                        },
                        scales: {
                        xAxes: [
                        {
                        gridLines: {
                        display: false
                        }
                        }
                        ],
                                yAxes: [
                                {
                                gridLines: {
                                display: true,
                                        color: "#eee",
                                        zeroLineColor: "#eee"
                                },
                                        ticks: {
                                        beginAtZero: true
                                        }
                                }
                                ]
                        },
                        tooltips: {
                        mode: "index",
                                titleFontColor: "#888",
                                bodyFontColor: "#555",
                                titleFontSize: 12,
                                bodyFontSize: 15,
                                backgroundColor: "rgba(256,256,256,0.95)",
                                displayColors: true,
                                xPadding: 20,
                                yPadding: 10,
                                borderColor: "rgba(220, 220, 220, 0.9)",
                                borderWidth: 2,
                                caretSize: 10,
                                caretPadding: 15
                        }
                }
        };
        var ctx = document.getElementById("acquisition").getContext("2d");
        var lineAcq = new Chart(ctx, configAcq);
        document.getElementById("acqLegend").innerHTML = lineAcq.generateLegend();
        }
        });
    </script>
    <!--Orders Overview-->
    <script>
        var doughnut = document.getElementById("doChart");
        if (doughnut !== null) {
        var myDoughnutChart = new Chart(doughnut, {
        type: "doughnut",
                data: {
                labels: ["Cancelled", "Completed", "Unpaid"],
                        datasets: [{
                        data: [
    ${countStatus.get(0).getCancelledCount()},
    ${countStatus.get(0).getCompletedCount()},
    ${countStatus.get(0).getUnpaidCount()}
                        ],
                                backgroundColor: ["#f3d676", "#50d7ab", "#88aaf3"]
                        }]
                },
                options: {
                responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                        display: false
                        },
                        cutoutPercentage: 75,
                        tooltips: {
                        callbacks: {
                        title: function(tooltipItem, data) {
                        return "Order : " + data["labels"][tooltipItem[0]["index"]];
                        },
                                label: function(tooltipItem, data) {
                                var dataset = data.datasets[tooltipItem.datasetIndex];
                                var currentValue = dataset.data[tooltipItem.index];
                                return currentValue;
                                }
                        },
                                titleFontColor: "#888",
                                bodyFontColor: "#555",
                                titleFontSize: 12,
                                bodyFontSize: 14,
                                backgroundColor: "rgba(256,256,256,0.95)",
                                displayColors: false, // Turn off color boxes in tooltips
                                borderColor: "rgba(220, 220, 220, 0.9)",
                                borderWidth: 2
                        }
                }
        });
        }

</script>

<script>
    window.onload = function () {
    <% String successUpdateStatus = (String) session.getAttribute("successUpdateStatus"); %>
    <% String errUpdateStatus = (String) session.getAttribute("errUpdateStatus"); %>

    <c:if test="${not empty successUpdateStatus}">
    $('#editOrderStatusModal${param.orderId}').modal('show');
        <% session.removeAttribute("successUpdateStatus"); %>
    </c:if>

    <c:if test="${not empty errUpdateStatus}">
    $('#editOrderStatusModal${param.orderId}').modal('show');
        <% session.removeAttribute("errUpdateStatus"); %>
    </c:if>
    };</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/simplebar.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.zoom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/slick.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Chart.min.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/chart.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/google-map-loader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/google-map.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/optionswitcher.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/daterangepicker.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/date-range.js"></script>-->

</body>
</html>
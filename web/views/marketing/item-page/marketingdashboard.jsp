<%-- 
    Document   : marketingdashboard
    Created on : Jul 2, 2024, 1:08:27 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/simplebar.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/ekka.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/style-bloglist.css">
        <title>Techno Store -  Marketing DASHBOARD</title>
    </head>
    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">
        <div class="wrapper">
            <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
            <jsp:include page="../../common/sale/header.jsp"></jsp:include>
                <div class="ec-page-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                            <div><h1><a href="mktdashboard">Marketing DashBoard</a></h1></div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="card card-default">
                                    <div class="card-body">
                                        <div class="row justify-content-between top-information">
                                            <div class="col-3 p-b-15 lbl-card">
                                                <div class="card card-mini dash-card card-2">
                                                    <div class="card-body">
                                                        <h2 class="mb-1"></h2>
                                                        <p>Total Customers</p>
                                                        <span class="absolute-middle mdi mdi-account-badge-horizontal"></span>
                                                        <h2 class="mb-1">${numCustomer}</h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3 p-b-15 lbl-card">
                                            <div class="card card-mini dash-card card-3">
                                                <div class="card-body">
                                                    <p>Total Products</p>
                                                    <span class="absolute-middle mdi mdi-package-variant-closed"></span>                                                           
                                                    <h2 class="mb-1">${numProduct}</h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3 p-b-15 lbl-card">
                                            <div class="card card-mini dash-card card-4">
                                                <div class="card-body">
                                                    <h2 class="mb-1"></h2>
                                                    <p>Total Posts</p>
                                                    <span class="absolute-middle mdi mdi-post"></span>
                                                    <h2 class="mb-1">${numPost}</h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3 p-b-15 lbl-card">
                                            <div class="card card-mini dash-card card-1">
                                                <div class="card-body">
                                                    <h2 class="mb-1"></h2>
                                                    <p>Total Feeback</p>
                                                    <span class="absolute-middle mdi  mdi-account-question"></span>
                                                    <h2 class="mb-1">${numFeedback}</h2>
                                                </div>
                                            </div>
                                        </div>
                                    </div></br>
                                    <form action="mktdashboard" method="post" onsubmit="return validateDates();">
                                        <div class="row">
                                            <div class="col-md-2">
                                                Start Date: <input type="date" id="startDate" name="startDate" class="form-control" value="${startDate}" required>
                                            </div>
                                            <div class="col-md-2">
                                                End Date: <input type="date" id="endDate" name="endDate" class="form-control"  value="${currentDate}" required>
                                            </div>
                                        </div>
                                        <br>
                                        <button type="submit" class="btn btn-primary">View</button>
                                        <br>
                                    </form>
                                    <br>
                                    <div class="row ">
                                        <div class="col-8">
                                            <div id="user-acquisition" class="card card-default">
                                                <div class="card-header">
                                                    <h2>New Trend Customers In Lasts 7 days</h2>
                                                </div>
                                                <div class="card-body">
                                                    <div class="tab-content pt-4" id="salesReport">
                                                        <div class="tab-pane fade show active" id="source-medium" role="tabpanel">
                                                            <div class="mb-6" style="max-height:247px">
                                                                <canvas id="acquisition" class="chartjs2"></canvas>
                                                                <div id="acqLegend" class="customLegend mb-2">
                                                                    <div class="legend-item">
                                                                        <span class="legend-color" style="background-color: rgba(52, 116, 212, .7);"></span>
                                                                        <span class="legend-label">Customer under 18 age</span>
                                                                    </div>
                                                                    <div class="legend-item">
                                                                        <span class="legend-color" style="background-color: rgba(255, 192, 203, .7);"></span>
                                                                        <span class="legend-label">Customer 19-30 age</span>
                                                                    </div>
                                                                    <div class="legend-item">
                                                                        <span class="legend-color" style="background-color: rgba(178, 251, 212, .7);"></span>
                                                                        <span class="legend-label">Customer over 31 ages</span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="card card-default">
                                                <div class="card-header justify-content-center">
                                                    <h2>Customer Gender Ratio</h2>
                                                </div>
                                                <div class="card-body">
                                                    <canvas id="doChart"></canvas>
                                                </div>
                                                <div class="card-footer d-flex flex-wrap bg-white p-0">
                                                    <div class="col-6">
                                                        <div class="p-20">
                                                            <ul class="d-flex flex-column justify-content-between">
                                                                <li class="mb-2"><i class="mdi mdi-checkbox-blank-circle-outline mr-2"
                                                                                    style="color: #4c84ff"></i>Male Customer</li>
                                                                <li><i class="mdi mdi-checkbox-blank-circle-outline mr-2"
                                                                       style="color: #ff7b7b "></i>Female Customer</li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><br><br>
                                    <div class="col-10">
                                        <div class="card card-default">
                                            <div class="card-header justify-content-center">
                                                <h2>Number Customer By Gender In 7 days</h2>
                                            </div>
                                            <div class="card-body">
                                                <div class="tab-content" id="userActivityContent"> 
                                                    <div class="tab-pane fade show active" id="user" role="tabpanel">
                                                        <canvas id="currentUser" class="chartjs"></canvas>
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
        <jsp:include page="../../common/sale/footer.jsp"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Chart.min.js"></script>
        <script>
                                        function validateDates() {
                                            var startDate = new Date(document.getElementById('startDate').value);
                                            var endDate = new Date(document.getElementById('endDate').value);
                                            var timeDifference = Math.abs(endDate - startDate);
                                            var dayDifference = Math.ceil(timeDifference / (1000 * 3600 * 24));
                                            if (dayDifference !== 7) {
                                                alert('Start date and end date must be 7 days apart.');
                                                return false;
                                            }
                                            return true;
                                        }
        </script>
        <!-- linechart1 -->
        <script>
            // Check if acquisition element exists before initializing the chart
            if (acquisition !== null) {
                // Initialize arrays to store data
                var labels = [];
                var Group18 = [];
                var Group19_30 = [];
                var Group31 = [];

                // Populate arrays using JSP forEach loop
            <c:forEach var="entry" items="${customerTrend}">
                labels.push('${entry.Date}');
                Group18.push(${entry['18']});
                Group19_30.push(${entry['19-30']});
                Group31.push(${entry['31+']});
            </c:forEach>

                // Configuration object for Chart.js
                var configAcq = {
                    type: "line",
                    data: {
                        labels: labels,
                        datasets: [
                            {
                                label: "Customer under 18 age",
                                backgroundColor: "rgba(52, 116, 212, .2)",
                                borderColor: "rgba(52, 116, 212, .7)",
                                data: Group18,
                                lineTension: 0.3,
                                pointBackgroundColor: "rgba(52, 116, 212,0)",
                                pointHoverBackgroundColor: "rgba(52, 116, 212,1)",
                                pointHoverRadius: 3,
                                pointHitRadius: 30,
                                pointBorderWidth: 2,
                                pointStyle: "rectRounded"
                            },
                            {
                                label: "Customer 19-30 age",
                                backgroundColor: "rgba(255, 192, 203, .3)",
                                borderColor: "rgba(255, 192, 203, .7)",
                                data: Group19_30,
                                lineTension: 0.3,
                                pointBackgroundColor: "rgba(255, 192, 203, 0)",
                                pointHoverBackgroundColor: "rgba(255, 192, 203, 1)",
                                pointHoverRadius: 3,
                                pointHitRadius: 30,
                                pointBorderWidth: 2,
                                pointStyle: "rectRounded"
                            },
                            {
                                label: "Customer over 31 ages",
                                backgroundColor: "rgba(178, 251, 212, .3)",
                                borderColor: "rgba(178, 251, 212, .7)",
                                data: Group31,
                                lineTension: 0.3,
                                pointBackgroundColor: "rgba(178, 251, 212, 0)",
                                pointHoverBackgroundColor: "rgba(178, 251, 212, 1)",
                                pointHoverRadius: 3,
                                pointHitRadius: 30,
                                pointBorderWidth: 2,
                                pointStyle: "rectRounded"
                            }
                        ]
                    },
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
                                        beginAtZero: true,
                                        stepSize: 1
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
                var myChart = new Chart(ctx, configAcq);
            }
        </script>
        <!-- piechart -->
        <script>
            //Pie chart in here
            var doughnut = document.getElementById("doChart");
            if (doughnut !== null) {
                var myDoughnutChart = new Chart(doughnut, {
                    type: "doughnut",
                    data: {
                        labels: ["Male", "Female"],
                        datasets: [
                            {
                                label: ["Male", "Female"],
                                data: [${pieChart.MalePercentage}, ${pieChart.FemalePercentage}],
                                backgroundColor: ["#88aaf3", "#ed9090"],
                                borderWidth: 1
                            }
                        ]
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
                                title: function (tooltipItem, data) {
                                    return data["labels"][tooltipItem[0]["index"]];
                                },
                                label: function (tooltipItem, data) {
                                    // Modify label to include percentage sign
                                    var value = data["datasets"][0]["data"][tooltipItem["index"]];
                                    var label = value + "%";
                                    return label;
                                }
                            },
                            titleFontColor: "#888",
                            bodyFontColor: "#555",
                            titleFontSize: 12,
                            bodyFontSize: 14,
                            backgroundColor: "rgba(256,256,256,0.95)",
                            displayColors: true,
                            borderColor: "rgba(220, 220, 220, 0.9)",
                            borderWidth: 2
                        }
                    }
                });
            }
        </script>
        <!-- linechart2 -->
        <script>
            var cUser = document.getElementById("currentUser");
            if (cUser !== null) {
                // Retrieve data from servlet
                var labels = [];
                var femaleData = [];
                var maleData = [];

            <c:forEach var="entry" items="${lineChart2}">
                labels.push('${entry.Date}');
                femaleData.push(${entry.FemaleCount});
                maleData.push(${entry.MaleCount});
            </c:forEach>

                var myUChart = new Chart(cUser, {
                    type: "bar",
                    data: {
                        labels: labels,
                        datasets: [
                            {
                                label: "Female",
                                data: femaleData,
                                backgroundColor: "#ed9090"
                            },
                            {
                                label: "Male",
                                data: maleData,
                                backgroundColor: "#88aaf3"
                            }
                        ]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            display: true
                        },
                        scales: {
                            xAxes: [
                                {
                                    gridLines: {
                                        drawBorder: true,
                                        display: false,
                                    },
                                    ticks: {
                                        fontColor: "#8a909d",
                                        fontFamily: "Roboto, sans-serif",
                                        beginAtZero: true,
                                    },
                                    barPercentage: 0.8,
                                    categoryPercentage: 0.5
                                }
                            ],
                            yAxes: [
                                {
                                    gridLines: {
                                        drawBorder: true,
                                        display: true,
                                        color: "#eee",
                                        zeroLineColor: "#eee"
                                    },
                                    ticks: {
                                        fontColor: "#8a909d",
                                        fontFamily: "Roboto, sans-serif",
                                        beginAtZero: true,
                                        stepSize: 1 // Ensure the step size is 1
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
                            xPadding: 10,
                            yPadding: 7,
                            borderColor: "rgba(220, 220, 220, 0.9)",
                            borderWidth: 2,
                            caretSize: 6,
                            caretPadding: 5
                        }
                    }
                });
            }
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
    </body>
</html>

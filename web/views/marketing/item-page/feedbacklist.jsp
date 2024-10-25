s<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

        <title>FeedBack List</title>

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

        <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
            <!-- Header -->
        <jsp:include page="../../common/sale/header.jsp"></jsp:include>
            <!--  WRAPPER  -->
            <div class="wrapper">
                <div class="ec-page-wrapper">
                    <div class="ec-content-wrapper">
                        <div class="content">
                            <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
                                <div><h1>FeedBack List</h1></div>
                            </div>
                            <form id="filterForm" action="/TechStore/feedbacklist" method="get">
                                <div class="d-flex align-items-end">
                                    <div class="me-3">
                                        <label style="color: black" for="searchKeyword" class="text-danger w-60">Search By Customer Name, Feedback Content</label>
                                        <input type="text" id="searchKeyword" name="searchKeyword" value="${requestScope.keysearch}" class="form-control form-control-sm w-90">
                                </div>
                                <div class="me-3">
                                    <label style="color: black; margin-left: 10px;" for="status" class="text-danger w-60">StatusFeedBack:</label>
                                    <select id="status" name="deleted" class="form-control form-control-sm w-90">
                                        <c:set var="currentStatus" value="${empty param.deleted ? '' : param.deleted}" />
                                        <!--<option value="" ${currentStatus == '' ? 'selected' : ''}>All</option>-->
                                        <option value="">All</option>
                                        <option value="1" ${currentStatus == '1' ? 'selected' : ''}>Hidden</option>
                                        <option value="0" ${currentStatus == '0' ? 'selected' : ''}>Show</option>
                                    </select>
                                </div>
                                <div class="me-3">
                                    <label style="color: black; margin-left: 10px;" for="rating" class="text-danger w-60">Rating</label>
                                    <select id="rating" name="rating" class="form-control form-control-sm w-90">
                                        <c:set var="currentRating" value="${empty param.rating ? '' : param.rating}" />
                                        <option value="">All</option>

                                        <option value="1" ${currentRating == '1' ? 'selected' : ''}>1 Star</option>
                                        <option value="2" ${currentRating == '2' ? 'selected' : ''}>2 Stars</option>
                                        <option value="3" ${currentRating == '3' ? 'selected' : ''}>3 Stars</option>
                                        <option value="4" ${currentRating == '4' ? 'selected' : ''}>4 Stars</option>
                                        <option value="5" ${currentRating == '5' ? 'selected' : ''}>5 Stars</option>
                                    </select>
                                </div>
                                <input type="hidden" name="pageSize" value="${pageSize}" />
                                <input type="hidden" name="page" value="${currentPage}" />

                                <button type="submit" class="btn btn-primary btn-sm">Filter</button>
                            </div>
                        </form>

                        <div class="row">
                            <div class="col-12">
                                <div class="card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">

                                            <table id="responsive-data-table" class="table" style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th onclick="sortTableByColumn(0)">ProductName↑↓</th>
                                                        <th >UserName</th>
                                                        <th >Gender</th>
                                                        <th onclick="sortTableByColumn(0)">PhoneNumber↑↓</th>
                                                        <th onclick="sortTableByColumn(0)">FeedbackDate↑↓</th>
                                                        <th onclick="sortTableByColumn(0)">FeedbackUpDate↑↓</th>
                                                        <th onclick="sortTableByColumn(0)">Ratings↑↓</th>
                                                        <th onclick="sortTableByColumn(0)">Content↑↓</th>
                                                        <th >StatusOrder</th>
                                                        <th >StatusFeedBack</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="feedback" items="${listfeedback}">
                                                        <tr>
                                                            <td>${feedback.Product_name}</td>
                                                            <td>${feedback.Full_Name}</td>
                                                            <td>${feedback.Gender == 1 ? 'Male' : 'Female'}</td>
                                                            <td>${feedback.Phone_Number}</td>
                                                            <td>${feedback.Feedback_date}</td>
                                                            <td>${feedback.Feedback_update}</td>
                                                            <td>${feedback.Rating} star</td>
                                                            <td>${feedback.Content}</td>
                                                            <td>
                                                                <span class="badge ${feedback.Status == 2 ? 'badge-success' : feedback.Status == 1 ? 'badge-danger' : 'badge-warning'}">
                                                                    ${feedback.Status == 2 ? 'Completed' : feedback.Status == 1 ? 'Cancelled' : 'Unpaid'}
                                                                </span>
                                                            </td>
                                                            <td>
                                                                <span class="badge ${feedback.deleted == 0 ? 'badge-primary' : 'badge-danger'}">
                                                                    ${feedback.deleted == 0 ? 'Show' : 'Hidden'}
                                                                </span>
                                                            </td>
                                                            <td>
                                                                <div class="dropdown show d-inline-block widget-dropdown">
                                                                    <a class="dropdown-toggle icon-burger-mini" href="#" role="button"
                                                                       id="dropdown-feedback${feedback.Feedback_id}"
                                                                       data-bs-toggle="dropdown" aria-haspopup="true"
                                                                       aria-expanded="false" data-display="static"></a>
                                                                    <ul class="dropdown-menu dropdown-menu-right">
                                                                        <li class="dropdown-item">
                                                                            <a href="mktFeedbackDetail?idFeedback=${feedback.Feedback_id}" title="">View</a>
                                                                        </li>
                                                                        <li class="dropdown-item">
                                                                            <a href="#" data-bs-toggle="modal" data-bs-target="#editFeedbackStatusModal${feedback.Feedback_id}">Edit</a>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </td>
                                                        </tr>

                                                        <!-- Modal for Edit Feedback Status -->
                                                    <div class="modal fade" id="editFeedbackStatusModal${feedback.Feedback_id}" tabindex="-1" aria-labelledby="editFeedbackStatusModalLabel${feedback.Feedback_id}" aria-hidden="true" data-backdrop="false">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="editFeedbackStatusModalLabel${feedback.Feedback_id}">Edit Feedback Status</h5>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form id="editFeedbackStatusForm${feedback.Feedback_id}" action="${pageContext.request.contextPath}/updatefeedbackstatus" method="post">
                                                                        <input type="hidden" name="feedbackId" value="${feedback.Feedback_id}" />

                                                                        <div class="mb-3">
                                                                            <label for="feedbackStatus${feedback.Feedback_id}" class="form-label">Status Feedback</label>
                                                                            <select class="form-select" id="feedbackStatus${feedback.Feedback_id}" name="deleted">
                                                                                <option value="0" ${feedback.deleted == 0 ? 'selected' : ''}>Show</option>
                                                                                <option value="1" ${feedback.deleted == 1 ? 'selected' : ''}>Hidden</option>
                                                                            </select>
                                                                        </div>
                                                                        <c:if test="${not empty sessionScope.successUpdateStatus}">
                                                                            <h5 style="color: red">${sessionScope.successUpdateStatus}</h5>
                                                                        </c:if>
                                                                        <c:if test="${not empty sessionScope.errUpdateStatus}">
                                                                            <h5 style="color: red">${sessionScope.errUpdateStatus}</h5>
                                                                        </c:if>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- End Modal -->
                                                </c:forEach>
                                                </tbody>
                                            </table>





                                            <div class="d-flex justify-content-between">
                                                <form id="pageSizeSelect" method="get" action="/TechStore/feedbacklist">
                                                    <select id="pageSizeSelectTop" name="pageSize" class="form-control w-auto" onchange="submitFormWithListType()">
                                                        <option value="5" ${pageSize == 5 ? 'selected' : ''}>5</option>
                                                        <option value="10" ${pageSize == 10 ? 'selected' : ''}>10</option>
                                                        <option value="15" ${pageSize == 15 ? 'selected' : ''}>15</option>
                                                    </select>                     
                                                    <input type="hidden" id="statusHidden" name="status" value="${currentStatus}">
                                                    <input type="hidden" id="ratingHidden" name="rating" value="${currentRating}">
                                                    <input type="hidden" id="searchKeywordHidden" name="searchKeyword" value="${requestScope.keysearch}">

                                                </form>
                                                <nav>
                                                    <ul class="pagination">
                                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                                <a class="page-link" href="feedbacklist?page=${i}&pageSize=${pageSize}&searchKeyword=${requestScope.keysearch}&status=${currentStatus}&rating=${currentRating}">${i}</a>
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

        <jsp:include page="../../common/sale/footer.jsp"/>



        <script type="text/javascript">
            function submitFormWithListType() {
                document.getElementById("pageSizeSelect").submit();
            }
        </script>


        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>

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

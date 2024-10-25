<%-- 
    Document   : adduser
    Created on : Jun 6, 2024, 6:11:28 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/materialdesignicons.min.css">

        <!--<link href='assets/plugins/data-tables/responsive.datatables.min.css' rel='stylesheet'>-->

        <!-- ekka CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/ekka.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/simplebar.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/userlist.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/font-awesome.css" />
        <!-- FAVICON -->
        <link href="assets/img/favicon.png" rel="shortcut icon" />
        <style>
            .content_feedback img {
                width: 500px;
            }
        </style>
    </head>

    <body class="ec-header-fixed ec-sidebar-fixed  ec-header-light" id="body">
        <!--header-->
        <jsp:include page="../../common/marketing/narvbar.jsp"></jsp:include>
        <jsp:include page="../../common/sale/header.jsp"></jsp:include>
            <div class="wrapper">
                <div class="ec-page-wrapper">
                    <div class=" py-5 h-100">
                        <div class="row justify-content-center align-items-center h-100">
                            <div class="col-12 col-lg-9 col-xl-7 w-100">
                                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                    <div class="card-body p-4 p-md-5">
                                    <c:if test="${updateSuccess != null}">
                                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: green">${updateSuccess}</h3>
                                    </c:if>
                                    <c:if test="${Err != null}">
                                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5" style="color: red">${Err}</h3>
                                    </c:if>    
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Feedback Detail</h3>
                                    <form method="post" onsubmit="return confirmUpdate();">
                                        <c:set value="${FeedbackDetail}" var="o"/>
                                        <!-- Avatar Upload Section -->                                     
                                        <input readonly type="text" id="firstName" style="display: none" name="feedbackId" class="form-control form-control-lg" value="${o.feedbackId}"  />
                                        <!-- Existing form fields -->
                                        <div class="row">

                                        </div>
                                        <div class="row">
                                            <div class="row col-12">
                                                <div class="row">
                                                    <div class="col-3">
                                                        <label class="form-label" for="firstName">Full Name</label>
                                                        <input type="text" name="fullName" readonly required id="firstName" class="form-control form-control-lg" value="${o.fullName}" />
                                                    </div>

                                                    <div class="col-3">
                                                        <label class="form-label" for="firstName">Email</label>
                                                        <input type="email" readonly="" name="email" id="email" class="form-control form-control-lg" value="${o.email}" />
                                                    </div>

                                                    <div class="col-3">
                                                        <label class="form-label" for="firstName">Phone</label>
                                                        <input type="number" required readonly name="phone" id="phone" class="form-control form-control-lg" value="${o.mobile}" />
                                                    </div>

                                                    <div class="col-3">
                                                        <label class="form-label" for="firstName">Product</label>
                                                        <input type="text" required readonly name="text" id="phone" class="form-control form-control-lg" value="${o.product}" />
                                                    </div>

                                                </div>



                                                <div class="row content_feedback">
                                                    
                                                    <div class="col-8 form-control" style="height: fit-content; align-content: baseline; overflow: auto; width: 65%; margin-top: 30px">
                                                        <label class="form-label" for="firstName" style="font-size: xx-large; font-weight: bold">Content Feedback</label>
                                                        ${o.contentFeedback}
                                                    </div>
                                                    <div class="col-4">
                                                        <label class="form-label" for="firstName" style="font-size: xx-large; font-weight: bold">Rate star</label>
                                                        <div>
                                                            <c:forEach begin="1" end="${Star}" step="1">

                                                                <i class="fa fa-star" style="color: orange" aria-hidden="true"></i> 
                                                            </c:forEach>
                                                            <c:forEach begin="${Star}" end="4" step="1">
                                                                <i class="fa fa-star" aria-hidden="true"></i> 
                                                            </c:forEach>
                                                        </div>


                                                    </div>
                                                </div>
                                                    <div class="row">
                                                        <div class="col-3">
                                                        <label style="font-size: xx-large; font-weight: bold">Status</label>
                                                        <select name="feedbackStatus" class="form-control form-control-lg" style="margin-bottom: 20px">
                                                            <option ${o.status == 0 ? 'selected' : ''} value="0" >Active</option>
                                                            <option ${o.status == 1 ? 'selected' : ''} value="1" >Inactive</option>
                                                        </select>
                                                        </div>
                                                    </div>

                                            </div>

                                        </div>
                                            <input class="btn btn-primary btn-lg" type="submit" value="Update" />
                                            <input style="width: 100px" data-mdb-ripple-init class="btn btn-primary btn-lg" onsubmit="false" onclick="location.href = 'feedbacklist'" value="Back" />

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Footer-->
        <jsp:include page="../../common/sale/footer.jsp"></jsp:include>
        </div>
    </div>







    <script type="text/javascript">
        function confirmUpdate() {
            return confirm("Bạn có chắc chắn muốn cập nhật thông tin này không?");
        }
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/mkt-postlist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ekka.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/moment.min.js"></script>
</body>
</html>

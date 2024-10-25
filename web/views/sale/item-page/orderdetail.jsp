<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard eCommerce HTML Template.">

        <title>OrderDetail</title>

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
                <!--phần của quang-->
                <div class="ec-page-wrapper">
                    <div class="ec-content-wrapper">
                        <div class="content">
                            <div class="breadcrumb-wrapper breadcrumb-wrapper-2">
                                <h1>Order Detail</h1>

                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="ec-odr-dtl card card-default">

                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-xl-6 col-lg-6">
                                                    <address class="info-grid">
                                                        <div class="info-title"><strong>Order Information</strong></div><br>
                                                        <div class="info-content">
                                                            OrderId: ${orderdetail.Order_id}<br>
                                                        CustomerName: ${orderdetail2.UserName}<br>
                                                        Email: ${orderdetail2.Email}<br>
                                                        PhoneNumber: ${orderdetail2.Phone_number}<br>
                                                        OrderDate: ${orderdetail.Order_date}<br>
                                                        TotalMoney: ${orderdetail.Total_money}VND<br>
                                                        SalerName: ${orderdetail.SaleFull_name}<br>
                                                        <td>
                                                            StatusOrder:<span class="badge ${orderdetail.Status == 2 ? 'badge-success' : orderdetail.Status == 1 ? 'badge-danger' : 'badge-warning'}">
                                                                ${orderdetail.Status == 2 ? 'Completed' : orderdetail.Status == 1 ? 'Cancelled' : 'Unpaid'}
                                                            </span>
                                                        </td>                                                   
                                                    </div>
                                                </address>
                                            </div>
                                            <div class="col-xl-6 col-lg-6">
                                                <address class="info-grid">
                                                    <div class="info-title"><strong>Customer Information</strong></div><br>
                                                    <div class="info-content">
                                                        CustomerName: ${orderdetail.CustomerFull_name}<br>
                                                        Gender: ${orderdetail.Gender=='0'?'Female':'Male'}<br>
                                                        Email: ${orderdetail.Email}<br>
                                                        PhoneNumber: ${orderdetail.Phone_number}<br>
                                                        Address: ${orderdetail.Address}<br>
                                                    </div>
                                                </address>
                                            </div>

                                        </div>


                                                        <c:if test="${requestScope.statusOrder == 0 && (requestScope.saleId == sessionScope.account.userId || sessionScope.account.userId == 11)}">
                                            <div class="row">
                                                <div class="col-md-12 text-right">
                                                    <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editOrderStatusModal${orderdetail.Order_id}">Edit Order</a>
                                                </div>
                                            </div>
                                        </c:if>
                                        <!-- Edit OrderDetail Modal -->
                                        <div class="modal fade" id="editOrderStatusModal${orderdetail.Order_id}" tabindex="-1" aria-labelledby="editOrderStatusModalLabel${orderdetail.Order_id}" aria-hidden="true" data-backdrop="false">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="editOrderStatusModalLabel${orderdetail.Order_id}">Edit Order</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>

                                                    <div class="modal-body">
                                                        <form id="editOrderStatusForm${orderdetail.Order_id}" action="updatesaleorderdetail" method="post">
                                                            <input type="hidden" name="orderId" value="${orderdetail.Order_id}" />

                                                            <!-- Hiển thị ghi chú hiện tại -->
                                                            <div class="mb-3">
                                                                <label for="currentOrderNote${orderdetail.Order_id}" class="form-label">Current Note</label>
                                                                <textarea class="form-control" id="currentOrderNote${orderdetail.Order_id}" rows="3" readonly>${orderdetail.Note}</textarea>
                                                            </div>

                                                            <!-- Trường để người dùng chỉnh sửa ghi chú -->
                                                            <div class="mb-3">
                                                                <label for="orderStatus${orderdetail.Order_id}" class="form-label">Status</label>
                                                                <select class="form-select" id="orderStatus${orderdetail.Order_id}" name="status">
                                                                    <c:choose>
                                                                        <c:when test="${orderdetail.Status == 2}">
                                                                            <option value="2" selected>Completed</option>
                                                                        </c:when>
                                                                        <c:when test="${(orderdetail.Status == 1)||(orderdetail.Status == 0)}">
                                                                            <option value="1" selected>Cancelled</option>
                                                                            <option value="0">Unpaid</option>
                                                                            <option value="2">Completed</option>
                                                                        </c:when>


                                                                    </c:choose>
                                                                </select>
                                                            </div>



                                                            <div class="mb-3">
                                                                <label for="orderNote${orderdetail.Order_id}" class="form-label">New Note</label>
                                                                <textarea class="form-control" id="orderNote${orderdetail.Order_id}" name="note" rows="3">${orderdetail.Note}</textarea>
                                                            </div>


                                                            <c:if test="${currentUser.userId == salemanager.userId}">
                                                                <div class="mb-3">
                                                                    <label for="assignToSale${orderdetail.Order_id}" class="form-label">Assign Order To Other Sale</label>
                                                                    <select class="form-select" id="assignToSale${orderdetail.Order_id}" name="assignToSaleId">
                                                                        <option value="">Select Salesperson</option>
                                                                        <c:forEach var="salesperson" items="${salespersons}">
                                                                            <option value="${salesperson.userId}" ${orderdetail.Sale_id == salesperson.userId ? 'selected' : ''}>${salesperson.fullName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </c:if>



                                                            <c:if test="${not empty sessionScope.successupdatestasusandnote}">
                                                                <h5 style="color: red">${sessionScope.successupdatestasusandnote}</h5>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.errupdatestasusandnote}">
                                                                <h5 style="color: red">${sessionScope.errupdatestasusandnote}</h5>
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

                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="tbl-title">PRODUCT SUMMARY</h3>
                                                <div class="table-responsive">
                                                    <table class="table table-striped o-tbl">
                                                        <thead>
                                                            <tr class="line">
                                                                <td class="text-center"><strong>IMAGE</strong></td>
                                                                <td class="text-center"><strong>ProductName</strong></td>
                                                                <td class="text-center"><strong>Category</strong></td>
                                                                <td class="text-center"><strong>Unit Price</strong></td>
                                                                <td class="text-center"><strong>QUANTITY</strong></td>
                                                                <td class="text-center"><strong>SUBTOTAL</strong></td>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="thong" items="${orderdetailist}">
                                                                <tr>

                                                                    <td class="text-center"><img class="product-img"
                                                                                                 src="${thong.Thumbnail}" alt="" /></td>
                                                                    <td class="text-center">${thong.Product_name}</td>
                                                                    <td class="text-center">${thong.Category_name}</td>
                                                                    <td class="text-center">${thong.Price_sale}</td>
                                                                    <td class="text-center">${thong.Quantity}</td>
                                                                    <td class="text-center">
                                                                        <c:set var="totalPrice" value="${thong.Price_sale * thong.Quantity}" />
                                                                        <c:out value="${totalPrice}" />VND
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            <tr>
                                                                <td colspan="4">
                                                                </td>
                                                                <td class="text-right"><strong>Total</strong></td>
                                                                <td class="text-center">
                                                                    ${orderdetail.Total_money}VND
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td colspan="4">
                                                                </td>
                                                                <td class="text-right"><strong>Payment Status</strong></td>
                                                                <td class="text-center"><strong>${orderdetail2.Payment_name}</strong></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Tracking Detail -->

                            </div>
                        </div>
                    </div> <!-- End Content -->
                </div> <!-- End Content Wrapper -->
            </div> <!-- End Page Wrapper -->
        </div> <!-- End Wrapper -->
        <jsp:include page="../../common/sale/footer.jsp"/>
        <script>
            window.onload = function () {
            <% String successupdatestasusandnote = (String) session.getAttribute("successupdatestasusandnote"); %>
            <% String errupdatestasusandnote = (String) session.getAttribute("errupdatestasusandnote"); %>
            <c:if test="${not empty successupdatestasusandnote}">
                $('#editOrderStatusModal${orderdetail.Order_id}').modal('show');
                // Xóa thông báo thành công khỏi session
                <% session.removeAttribute("successupdatestasusandnote"); %>
            </c:if>

            <c:if test="${not empty errupdatestasusandnote}">
                $('#editOrderStatusModal${orderdetail.Order_id}').modal('show');
                // Xóa thông báo lỗi khỏi session
                <% session.removeAttribute("errupdatestasusandnote"); %>
            </c:if>

            };

        </script>
        <!-- Common Javascript -->
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
<%-- 
    Document   : productcontribution
    Created on : Jun 15, 2024, 5:43:23 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<div id="feedback-popup-overlay" class="feedback-popup-overlay" onclick="hideFeedbackPopup(event)">
    <div class="feedback-popup-content">
        <span class="feedback-close-btn" onclick="hideFeedbackPopup(event)">&times;</span>
        <h2>Leave Feedback</h2>
        <form id="feedback-form" onsubmit="handleSubmitFormContribution(this)">
                <div class="form-group">
                    <label for="name">Name
                        <c:if test="${sessionScope.account != null}">
                            <i class="fa-solid fa-lock"></i>
                        </c:if>
                    </label>
                    <input type="text" id="name-contribution" name="name" value="${sessionScope.account != null ? sessionScope.account.fullName : ''}" ${sessionScope.account != null ? 'readonly' : ''} placeholder="Your name" required>

                </div>
            <div class="form-group">
                <label for="email">Email
                    <c:if test="${sessionScope.account != null}">
                        <i class="fa-solid fa-lock"></i>
                    </c:if>
                </label>
                <input type="email" id="email-contribution" name="email" value="${sessionScope.account != null ? sessionScope.account.email : ''}" ${sessionScope.account != null ? 'readonly' : ''}  placeholder="Your email" required>
            </div>
            <div class="form-group">
                <label for="feedback">Feedback</label>
                <textarea id="feedback-contribution" name="feedback" placeholder="Write your feedback here..." required></textarea>
            </div>

            <input type="hidden" id="action-contribution" value="${sessionScope.account != null ? 'account' : 'anonymous'}"/>
            <input type="hidden" id="product-contribution" value=""/>
            <button type="submit" class="feedback-submit-btn">Submit</button>
        </form>
    </div>
</div>
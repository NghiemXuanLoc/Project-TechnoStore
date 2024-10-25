<%-- 
    Document   : popupaddproductforcompare
    Created on : May 20, 2024, 5:16:49 PM
    Author     : PC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div id="nen-add-compare">
    <div class="box-search-compare">
        <i id="close-popup" onclick="closePopupAddProductCompare()" style="color: white" class="fa-solid fa-xmark"></i>

        <input id="textSearchAddCompare" oninput="autoSearchAddCompare()" autocomplete="off" style="padding-left: 20px" type="text" name="search" placeholder="Searching for product names you want to compare?">

        <div class="search-suggestions" id="search-suggestions">
            <div class="box-suggestions">
                <ul id="list-search-product-add-compare-suggestions">
                </ul>
            </div> 
        </div>
    </div> 
</div>

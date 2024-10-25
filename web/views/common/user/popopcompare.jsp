<%-- 
    Document   : popopcompare
    Created on : May 19, 2024, 5:54:00 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="popup-compare row">
    <div class="col-md-5 text-center" id="product-1">
        <div class="row" >
            <img onclick="findProductAddPopupCompare(this)" src="images/popup/logo-add.png" alt="alt"/>
        </div>
        <div class="row" >
            <span></span>
        </div>
        <span class="buttonDelete" style="margin-right: 3%" class="close" onclick="deleteCompare('1')"><i class="fa-solid fa-xmark"></i></span>
        <input type="hidden" value=""/>
    </div>

    <div class="col-md-5 text-center" id="product-2">
        <div class="row">
            <img onclick="findProductAddPopupCompare(this)" src="images/popup/logo-add.png" alt="alt"/>
        </div>
        <div class="row">
            <span></span>
        </div>
        <span class="buttonDelete" style="margin-right: 3%" class="close" onclick="deleteCompare('2')"><i class="fa-solid fa-xmark"></i></span>
        <input type="hidden" value=""/>
    </div>

    <div id="compareDelete" class="col-md-2 text-center">
        <h3 id="compareNow"  onclick="compare()" style="margin-top: 10px"><i class="fa-solid fa-code-compare"></i>&nbsp; Compare now</h3>
        <h4 id="deleteAll" onclick="deleteCompare('all')"><i class="fa-solid fa-trash-can"></i>&nbsp;&nbsp;Delete All</h4>
    </div>
    
   
    
</div>
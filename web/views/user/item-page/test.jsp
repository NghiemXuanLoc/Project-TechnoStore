<%-- 
    Document   : test
    Created on : Jun 12, 2024, 12:31:50 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>




        <!--sider search box-->
        <div style="margin-bottom: 26px;" class="widget widget-categories">
            <div class="widget-title">
                <h3>Search box</h3>
                <input type="text" placeholder="Input your product search box!"/>
            </div>

        </div><!-- /.widget-categories -->

        <!--sider category-->
        <div class="widget widget-brands">
            <div class="widget-title">
                <h3>Categories<span></span></h3>
            </div>
            <div class="widget-content">

                <ul class="box-checkbox scroll">
                    <li class="check-box">
                        <input type="checkbox" id="checkbox1" name="checkbox1">
                        <label for="checkbox1">ALL</label>
                    </li>

                    <c:forEach items="${sessionScope.listCategory}" var="category">
                        <li class="check-box">
                            <input type="checkbox" id="checkbox1" name="checkbox1">
                            <label for="checkbox1">${category.categoryName}</label>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div><!-- /.widget widget-brands -->

        <!--sider price-->
        <div class="widget widget-price">
            <div class="widget-title">
                <h3>Price<span></span></h3>
            </div>
            <div class="widget-content">
                <p>Price</p>
                <div class="price search-filter-input">
                    <div id="slider-range" class="price-slider ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"><div class="ui-slider-range ui-corner-all ui-widget-header" ></div><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span></div>
                    <p class="amount">
                        <input type="text" id="amount" disabled="">
                    </p>
                </div>
            </div>
        </div><!-- /.widget widget-price -->

        <!--sider operating system-->
        <div class="widget">
            <div class="widget-title">
                <h3>Operating system<span></span></h3>
                <div style="height: 2px"></div>
            </div>
            <div class="widget-content">
                <ul class="box-checkbox scroll">

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">ALL</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">IOS(Phone)</label>
                    </li>


                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">Android</label>
                    </li>
                </ul>
            </div>
        </div><!-- /.widget widget-color -->

        <!--sider battery-->
        <div class="widget">
            <div class="widget-title">
                <h3>Battery capacity<span></span></h3>
                <div style="height: 2px"></div>
            </div>
            <div class="widget-content">
                <ul class="box-checkbox scroll">

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">ALL</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">Under 3000 mah</label>
                    </li>


                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">from 3000 to 4000mah</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">from 3000 to 4000mah</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">from 4000 to 5000mah</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">above 5000mah</label>
                    </li>
                </ul>
            </div>
        </div><!-- /.widget widget-color -->

        <!--sider screen-->
        <div class="widget">
            <div class="widget-title">
                <h3>Screen<span></span></h3>
                <div style="height: 2px"></div>
            </div>
            <div class="widget-content">
                <ul class="box-checkbox scroll">

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">ALL</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">OLED</label>
                    </li>


                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">AMOLED</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">HD+</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">FHD+</label>
                    </li>

                </ul>
            </div>
        </div><!-- /.widget widget-color -->

        <!--sider ram-->
        <div class="widget">
            <div class="widget-title">
                <h3>RAM<span></span></h3>
                <div style="height: 2px"></div>
            </div>
            <div class="widget-content">
                <ul class="box-checkbox scroll">

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">2 GB</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">3 GB</label>
                    </li>


                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">4 GB</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">6 GB</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">8 GB</label>
                    </li>

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">12 GB</label>
                    </li>
                </ul>
            </div>
        </div><!-- /.widget widget-color -->

        <!--sider color-->
        <div class="widget widget-color">
            <div class="widget-title">
                <h3>Color<span></span></h3>
                <div style="height: 2px"></div>
            </div>
            <div class="widget-content">

                <ul class="box-checkbox scroll">

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">ALL</label>
                    </li>
                    <c:forEach items="${sessionScope.listColor}" var="color">
                        <li class="check-box">
                            <input type="checkbox" id="check1" name="check1">
                            <label for="check1">${color.colorName}</label>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div><!-- /.widget widget-color -->

        <!--sider memory-->
        <div class="widget widget-color">
            <div class="widget-title">
                <h3>Memory<span></span></h3>
                <div style="height: 2px"></div>
            </div>
            <div class="widget-content">
                <ul class="box-checkbox scroll">

                    <li class="check-box">
                        <input type="checkbox" id="check1" name="check1">
                        <label for="check1">ALL</label>
                    </li>

                    <c:forEach items="${sessionScope.listMemory}" var="memory">
                        <li class="check-box">
                            <input type="checkbox" id="check1" name="check1">
                            <label for="check1">${memory.memorySize}</label>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div><!-- /.widget widget-color -->











    </body>
</html>

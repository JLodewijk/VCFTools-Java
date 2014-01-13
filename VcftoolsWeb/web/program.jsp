<%-- 
    Document   : program
    Created on : Jan 13, 2014, 11:25:29 AM
    Author     : Sergio Bondietti <sergio.bondietti@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A layout example with a side menu that hides on mobile, just like the Pure website.">
        <title>VcfTools Online</title>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
        <link rel="stylesheet" href="css/layouts/side-menu.css">
    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <a href="#menu" id="menuLink" class="menu-link">
                <!-- Hamburger icon -->
                <span></span>
            </a>

            <jsp:include page="includes/menubar.jsp"></jsp:include>

            <div id="main">
                <div class="header">
                    <h1>VcfTools Online</h1>
                </div>

                <div class="content">
                       
                </div>
            </div>
        </div>





        <script src="js/ui.js"></script>


    </body>
</html>



<%-- 
    Document   : index
    Created on : Dec 18, 2013, 1:33:27 PM
    Author     : mhroelfes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
    </head>
    <body>
        <h1>Hello World!</h1>
        <div class="pure-g">
            <div class="pure-u-1-3">
                <!--
                By default, grid units don't have any margin/padding.
                If you want to add these, put them in a child container.
                -->
                <p>Thirds</p>
            </div>

            <div class="pure-u-1-3">
                <p>Thirds</p>
            </div>
        </div>
        <div class="pure-menu pure-menu-open pure-menu-horizontal">
            <ul>
                <li><a href="#">Home</a></li>
                <li class="pure-menu-selected"><a href="#">Flickr</a></li>
                <li><a href="#">Messenger</a></li>
                <li><a href="#">Sports</a></li>
                <li><a href="#">Finance</a></li>
            </ul>
        </div>
    </body>
</html>

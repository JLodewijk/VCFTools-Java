<%-- 
    Document   : index
    Created on : Jan 9, 2014, 1:19:36 PM
    Author     : mhroelfes
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A layout example with a side menu that hides on mobile, just like the Pure website.">

        <title>Side Menu &ndash; Layout Examples &ndash; Pure</title>








        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">





        <link rel="stylesheet" href="css/site.css">








    </head>
    <body>






        <div id="layout">
            <!-- Menu toggle -->
            <a href="#menu" id="menuLink" class="menu-link">
                <!-- Hamburger icon -->
                <span></span>
            </a>
            <div class='content'>
                <jsp:include page="includes/menubar.jsp"></jsp:include>
                <form method="get" action='UserServlet?name='+ ${sessionScope.name} name="frmAddUser">

                        <p>New Password: <input
                                type="text" name="password"
                                value="<c:out value="${user.password}"/>"/><br /> </p>
                        <p>Confirm Password: <input
                                type="text" name="password"
                                value="<c:out value="${user.password}"/>"/><br /> </p>
                    <input type="submit" value="Submit"/>
                </form>    
            </div>
        </div>






        <script src="js/ui.js"></script>


    </body>
</html>


<%-- 
    Document   : index
    Created on : Jan 9, 2014, 1:19:36 PM
    Author     : mhroelfes
--%>

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

            <jsp:include page="includes/menubar.jsp"></jsp:include>

            <div id="main">
                <div class="header">
                    <h1>Page Title</h1>
                    <h2>A subtitle for your page goes here</h2>
                </div>

                <div class="content">
                    <h2 class="content-subhead">How to use this layout</h2>
                    <p>
                        To use this layout, you can just copy paste the HTML, along with the CSS in side-menu.css. and the JavaScript in. The JS file uses vanilla JavaScript to simply toggle an <code>active</code> class that makes the menu responsive.
                    </p>

                    <h2 class="content-subhead">Now Let's Speak Some Latin</h2>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>

                    <h2 class="content-subhead">Try Resizing your Browser</h2>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                </div>
            </div>
        </div>





        <script src="js/ui.js"></script>


    </body>
</html>


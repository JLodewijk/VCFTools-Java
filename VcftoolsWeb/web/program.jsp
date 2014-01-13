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
        <link rel="stylesheet" href="css/site.css">
        <script src="js/jquery-1.10.2.min.js"></script>
        <script src="js/jquery-ui-1.10.3.custom.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/jquery.iframe-transport.js"></script>
        <script src="js/jquery.fileupload.js"></script>
        <script src="js/jquery.fileupload-process.js"></script>
        <script src="js/jquery.fileupload-image.js"></script>
        <script src="js/jquery.fileupload-audio.js"></script>
        <script src="js/jquery.fileupload-video.js"></script>
        <script src="js/jquery.fileupload-validate.js"></script>
        <script src="js/jquery.fileupload-ui.js"></script>
        <script src="js/jquery.fileupload-jquery-ui.js"></script>
        <script src="js/ui.js"></script>
        <script src="js/program.js"></script>
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
                    <form class="vcftoolsform" id="vcftoolsform" method="get" action="">

                        <fieldset>
                            <legend>VcfTools</legend> 


                            <div class="content">
                                <form class="vcftoolsform" id="vcftoolsform" method="get" action="">
                                    <div id="fileuploader">
                                        <label for="file">Select VCF file</label>
                                        <input id="fileupload" type="file" name="files[]" multiple>
                                        <div id="progress">
                                            <div class="bar" style="width: 0%;"></div>
                                        </div>

                                    </div>


                                    <table>
                                        <tr>
                                            <th>Include Chr</th>
                                            <th>from Bp</th>
                                            <th>to Bp</th>
                                        </tr>
                                        <tr>
                                            <td><input id="chromosome" name="chromosome_1" type="text" /></td>
                                            <td><input id="frombp" name="frombp_1" type="text" /></td>
                                            <td><input id="tobp" name="tobp_1" type="text" /></td>
                                        </tr>
                                    </table>
                                    <table>
                                        <tr>
                                            <th>Exclude Chr</th>
                                            <th>from Bp</th>
                                            <th>to Bp</th>
                                        </tr>
                                        <tr>
                                            <td><input id="chromosome" name="chromosome_1" type="text" /></td>
                                            <td><input id="frombp" name="frombp_1" type="text" /></td>
                                            <td><input id="tobp" name="tobp_1" type="text" /></td>
                                        </tr>
                                    </table> 
                                    <p>
                                        <label for="snp">snp</label>
                                        <input id="snp" name="snp" type="text" />
                                    </p>

                                    <p>
                                        <input class="submit" type="submit" value="Submit" />
                                    </p>
                                    </fieldset>
                                </form>
                            </div>
                            </div>
                        </div>


                            </body>
                            </html>



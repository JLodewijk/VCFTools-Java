<%-- 
    Document   : login
    Created on : Dec 19, 2013, 2:45:17 PM
    Author     : jlodewijk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h2>Sign in please</h2>
        <h3>${requestScope.error}</h3>
        <form method="post" action="login.do">
            <input type="text" name="username" />
            <input type="password" name="password" />
            <input type="submit" name="userinput" value="Login"/>
            <input type="submit" name="userinput" value="Register"/>
        </form>     
    </body>
</html>

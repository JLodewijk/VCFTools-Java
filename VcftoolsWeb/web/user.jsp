<%-- 
    Document   : user
    Created on : 11-jan-2014, 21:26:53
    Author     : Jeroen
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>Add new user</title>
    </head>
    <body>

        <form method="POST" action='TableServlet' name="frmAddUser">
            Username : <input type="text"  name="name"
                              value="<c:out value="${user.name}" />" /> <br /> 
            Password: <input
                type="text" name="password"
                value="<c:out value="${user.password}" />" /> <br /> 
            Role : <input
                type="text" name="role"
                value="<c:out value="${user.role}" />" /> <br /> <input
                type="submit" value="Submit" />
        </form>
    </body>
</html>

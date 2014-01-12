<%-- 
    Document   : listUser
    Created on : 11-jan-2014, 21:26:20
    Author     : Jeroen
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>Show All Users</title>
    </head>
    <body>
        <table border=1>
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th colspan=2>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.name}" /></td>
                        <td><c:out value="${user.password}" /></td>
                        <td><c:out value="${user.role}" /></td>
                        <td><a href="TableServlet?action=edit&name=<c:out value="${user.name}"/>">Update</a></td>
                        <td><a href="TableServlet?action=delete&name=<c:out value="${user.name}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="TableServlet?action=insert&name=<c:out value="${user.name}"/>">Add User</a></p>
    </body>
</html>

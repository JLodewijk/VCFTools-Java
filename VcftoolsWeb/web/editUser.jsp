<%-- 
    Document   : editUser
    Created on : 11-jan-2014, 21:27:18
    Author     : Jeroen
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="js/jquery-1.10.2.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.validate.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="js/user.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>Edit a user</title>
	<link rel="stylesheet" href="css/site.css">

    </head>
    <body>
        <form method="POST" id="editForm" action='UserServlet?action=updateUser' name="frmAddUser">
            Username : <input type="text" readonly="readonly" name="name"
                              value="<c:out value="${user.name}" />" /> <br /> 
            Password: <input
                type="text" name="password" class="error" required
                value="<c:out value="${user.password}" />" /> <br /> 
            Role : <select
                type="text" name="role"
                value="<c:out value="${user.role}" />" /> <br /> 
	    <option value="1">Masteradmin</option>
	    <option value="2">Admin</option>
	    <option value="3">User</option>
	</select>
	<input type="submit" value="Submit" />
    </form>
</body>
</html>

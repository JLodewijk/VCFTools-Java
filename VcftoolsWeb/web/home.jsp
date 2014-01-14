<%-- 
    Document   : home
    Created on : Jan 14, 2014, 11:53:11 AM
    Author     : Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>Insert title here</title>
    </head>
    <body>
        <jsp:forward page="/UserServlet?action=listUser" />
    </body>
</html>

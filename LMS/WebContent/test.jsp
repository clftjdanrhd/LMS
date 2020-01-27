<%@page import="java.sql.Connection"%>
<%@page import="com.lms.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%
UserDAO userDao=UserDAO.getInstance();
Connection conn = userDao.getConnection();
out.println("DBCP 연동성공");

%>
</body>
</html>
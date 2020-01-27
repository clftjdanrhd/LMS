<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@page import="java.sql.Connection" %>
    <%@page import="javax.sql.DataSource" %>
    <%@page import="javax.naming.InitialContext" %>
    <%@page import="javax.naming.Context" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database Connection Pool</title>
</head>
<body>

Database Connection Pool 연동 테스트

<%



Context initContext = new InitialContext();
Context envContext  = (Context)initContext.lookup("java:/comp/env");
DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
Connection conn = ds.getConnection();
out.print("접속성공");

//etc.


%>
</body>
</html>
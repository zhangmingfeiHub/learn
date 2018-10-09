<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%= request.getContextPath() %>

<script type="text/javascript" src="static/jscomp/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>

<h2>Hello World!</h2>
<input type="text" id="sth" />
<input type="button" value="提交" id="sthBtn" />

<h2>customHM!</h2>
<a href="custom/handler-mapping">custom/handler-mapping</a> <br />
<a href="custom/handler-adapter">custom/handler-adapter</a>
</body>
</html>
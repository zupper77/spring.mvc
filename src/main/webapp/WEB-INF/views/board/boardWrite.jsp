<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String root = request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="oForm" method="post" action="<%=root%>/board/addBoard.do">
	- title: <input type="text" id="title" name="title"><br/>
	- writer: <input type="text" id="writer" name="writer"><br/>
	<input type="button" id="" value="submit" onclick="oForm.submit();">
	<a href="<%=root%>/board/getBoardList.do">list</a>
</form>
</body>
</html>
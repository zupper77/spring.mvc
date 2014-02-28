<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.wemakeprice.vo.board.BoardVO" %>
<%
	String root = request.getContextPath();
	BoardVO boardVO = (BoardVO) request.getAttribute("boardVO");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
- id : <%=boardVO.getId() %><br/>
- writer : <%=boardVO.getWriter() %><br/>

<a href="<%=root %>/board/getBoardList.do">list</a>
</body>
</html>
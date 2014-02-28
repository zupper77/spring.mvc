<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wemakeprice.vo.board.BoardVO" %>
<%@ page import="java.util.List" %>
<%
	String root = request.getContextPath();
	List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board List</title>
</head>
<body>

<div>
<h3><font color="green">:::게시판:::</font></h3>
#Total count: <%=boardList.size() %>
<table border="1" width="700px">
<tr>
	<th>edit</th>	
	<th>id</th>
	<th>title</th>
	<th>writer</th>
	<th>del</th>
</tr>
<% for(int i=0; i<boardList.size(); i++) { 
		BoardVO boardVO = (BoardVO)boardList.get(i);
%>
<tr>
	<td><a href="<%=root %>/board/editBoard.do">edit</a></td>
	<td><%=boardVO.getId() %></td>
	<td width="200px"><a href="<%=root %>/board/getBoardInfo.do?id=<%=boardVO.getId() %>"><%=boardVO.getTitle()%></a></td>
	<td><%=boardVO.getWriter() %></td>	
	<td>
		@삭제(type parameter 미지정)&nbsp;&nbsp;<a href="<%=root %>/board/removeBoard.do?id=<%=boardVO.getId() %>">del</a><br/>
		@삭제(type parameter admin)&nbsp;&nbsp;<a href="<%=root %>/board/removeBoard.do?id=<%=boardVO.getId() %>&type=admin">del</a><br/>
		@삭제(type parameter customer)&nbsp;&nbsp;<a href="<%=root %>/board/removeBoard.do?id=<%=boardVO.getId() %>&type=customer">del</a>
	</td>
</tr>
<% } %>
</table>
<br/>
<a href="<%=root %>/board/addBoard.do">write</a>
</div>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%
	String root = request.getContextPath();
%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<div>
	<table>
		<tr>
			<th>@RequestMapping</th>
			<th>Mapping URL</th>
			<th>Return Type</th>
		</tr>
		<tr>
			<td>/board/getBoardList</td>
			<td><a href="<%=root %>/board/getBoardList.do">http://localhost:8080/spring.mvc/board/getBoardList.do</a></td>
			<td>String</td>
		</tr>
		<tr>
			<td>/board/getBoardInfo</td>
			<td><a href="<%=root %>/board/getBoardInfo.do?id=2">http://localhost:8080/spring.mvc/board/getBoardInfo.do?id=2</a></td>
			<td>ModelAndView</td>
		</tr>
		<tr>
			<td>/board/dummy</td>
			<td><a href="<%=root %>/board/dummy.do">http://localhost:8080/spring.mvc/board/dummy.do</a></td>
			<td>void</td>
		</tr>
		
		<tr>
			<td>/board/getBoardInfo2/{id}</td>
			<td><a href="http://localhost:8080/spring.mvc/board/getBoardInfo2/2">http://localhost:8080/spring.mvc/getBoardInfo2/2</a></td>
			<td>String</td>
		</tr>	
	</table>
</div>
</body>
</html>

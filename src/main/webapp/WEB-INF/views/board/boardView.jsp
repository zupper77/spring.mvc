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
<div>
<h3><font color="green">:::@ModelAttribute:::</font></h3>
해당 모델 객체들을 세션에 저장하여 여러 요청에서 공통으로 사용<br/>
${searchTeamList[0]}<br/>
${searchTeamList[1]}<br/>
${searchTeamList[2]}<br/><br/>

<h3><font color="green">:::Board:::</font></h3>
- id : <%=boardVO.getId() %><br/>
- writer : <%=boardVO.getWriter() %><br/><br/>

<a href="<%=root %>/board/getBoardList.do">list</a>
</div>
</body>
</html>
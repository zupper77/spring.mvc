<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../include/ssi.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; no-cache; charset=utf-8" />
<meta name="description" content="WeMakePrice GOS">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>위메프 G.O.S</title>
	<tiles:insertAttribute name="include" />
	 <script type="text/javascript">
		$(function(){
			//공통 common 스크립트 작업시		
		});
	 </script>
</head>
<body id="super_area">
	<tiles:insertAttribute name="header" />
	 <div class="container-fluid">
      	<div class="row-fluid" id="master-row-fluid">
			<tiles:insertAttribute name="left" />
			<div class="span10">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>	
</body>
</html>
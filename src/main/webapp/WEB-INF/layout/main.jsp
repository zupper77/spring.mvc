<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="description" content="WEMAKEPRICE PARTNER">
<meta name="keywords" content="WEMAKEPRICE PARTNER">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.png">

<title>WEMAKEPRICE PARTNER</title>
<%@ include file="/WEB-INF/views/common/include.jsp"%>
<script type="text/javascript">
</script>
</head>
<body id="cr" class="cr31">
<div id="wmp_wrap" class="sub">
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="menu"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
    <iframe name="ifDownloadHidden" id="ifDownloadHidden" style="display: none;"></iframe>
	<iframe name="ifExcel" id="ifExcel" style="display: none;"></iframe>
</div>
</body>
</html>
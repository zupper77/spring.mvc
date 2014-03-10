<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*"%>
<%
	String root = request.getContextPath(); 
%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="description" content="WEMAKEPRICE PARTNER">
<meta name="keywords" content="WEMAKEPRICE PARTNER">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.png">

<link rel="stylesheet" media="screen" href="<%=root %>/css/ui.jqgrid.css">
<link rel="stylesheet" media="screen" href="<%=root %>/css/jquery-ui-1.10.3.custom.min.css">
<link rel="stylesheet" media="screen" href="<%=root %>/css/common.css">
<link rel="stylesheet" media="screen" href="<%=root %>/css/sub_itemmanage.css">
<link rel="stylesheet" media="screen" href="<%=root %>/css/jquery-te-1.4.0.css">

<script src="<%=root %>/js/lib/jquery-1.10.2.min.js"></script>
<script src="<%=root %>/js/lib/jquery-ui-1.10.3.custom.min.js"></script>
<script src="<%=root %>/js/lib/jquery.dg.fslide.js"></script>
<script src="<%=root %>/js/lib/jquery.dg.highlight.js"></script>
<script src="<%=root %>/js/lib/jquery.ajaxupload.3.5.js"></script>
<script src="<%=root %>/js/lib/jquery.jqGrid.locale.js"></script>
<script src="<%=root %>/js/lib/jquery.jqGrid.min.js"></script>
<script src="<%=root %>/js/lib/jquery.paging.min.js"></script>
<script src="<%=root %>/js/lib/jquery.blockUI.js"></script>
<script src="<%=root %>/js/lib/jquery-te-1.4.0.js"></script>

<script src="<%=root %>/js/common/header.js"></script>
<script src="<%=root %>/js/common/common.js"></script>
<script src="<%=root %>/js/common/ajax.js"></script>
<script src="<%=root %>/js/common/excel.js"></script>
<script src="<%=root %>/js/common/file.js"></script>
<script src="<%=root %>/js/common/json.js"></script>
<script src="<%=root %>/js/common/util.js"></script>
<script src="<%=root %>/js/common/gridPaging.js"></script>
<title>WEMAKEPRICE PARTNER</title>
<script type="text/javascript">
	var root = "<%=root%>";
</script>
</head>
<body id="popup mac cr31" class="cr">
	<tiles:insertAttribute name="body"/>
</body>
</html>
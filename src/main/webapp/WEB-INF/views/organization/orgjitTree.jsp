<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/resources/js/jit/Stylesheets/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/js/jit/Stylesheets/Spacetree.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jit/Scripts/jit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jit/Scripts/org.js"></script>
<script type="text/javascript">		
	url = "${pageContext.request.contextPath}/organization/organization_jitTreeList.json";		
</script>

<title>조직관리</title>
</head>
<body>
	
    <div id="container">
        <div id="center-container">
            
		    <div>
				<a href="${pageContext.request.contextPath}/organization/organization_fancytreeView">조직도관리화면이동</a>
			</div>
            
            <div id="orgchart" />
            
        </div>
		        

        
    </div>
    
	
</body>
</html>
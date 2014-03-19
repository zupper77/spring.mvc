<%@ page import="java.util.* , java.io.* , java.text.*"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<jsp:useBean id="map" class="java.util.HashMap"></jsp:useBean><!-- jstl 사용 -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}"   scope="page" />
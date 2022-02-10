<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet"
	href="<c:url value='/resources/Content/base/jquery-ui.css' />" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/resetLogin.css' />" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/styleLogin.css' />" /> 
<script type="text/javascript"
	src="<c:url value='/resources/Scripts/jquery-1.9.1.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/Scripts/jScripts/jquery-ui.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/Scripts/jquery-ui-1.8.11.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/Scripts/jquery-ui.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/Scripts/jquery-1.10.3.js' />"></script>
</head>
<body>
	<%-- 	<div>
		<tiles:insertAttribute name="header" />
	</div> --%>
	<div>
		<tiles:insertAttribute name="body" />
	</div>
	<%-- 	<div>
		<tiles:insertAttribute name="footer" />
	</div> --%>
</body>
</html>

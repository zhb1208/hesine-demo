<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
	<head>
        <%@ include file="/common/meta.jsp" %>
        <title><decorator:title/> Hesine frameworker </title>
        <%--<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css" />--%>
        <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.min.css" />
        <%--<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap-theme.css" />--%>
        <%--<link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css" />--%>
        <script src="${ctx}/static/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/bootstrap/js/bootstrap.js" type="text/javascript"></script>
        <decorator:head/>
	</head>
	<body>
		<sitemesh:body/>
	</body>
</html>

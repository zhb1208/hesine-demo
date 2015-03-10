<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
   	<%@ include file="/common/meta.jsp" %>
    <title><decorator:title/> Hesine frameworker </title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap-theme.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css" />
	<script src="${ctx}/static/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.js" type="text/javascript"></script>
	<decorator:head/>
    <script>
    	var ctxPath = "${ctx}";
	</script>
</head>
<body id="mainframe">
	<c:if test="${message!=null}">
		<script>
			feedBackMessage("show_feedBack_message","${message}","50%","30%",5000);
		</script>
	</c:if>
    <%@ include file="/common/header.jsp" %>
	<div class="container projects">
        <decorator:body/>
    </div>
    <%@ include file="/common/footer.jsp" %>
</body>
</html>

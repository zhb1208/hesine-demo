<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>Hesine frameworker </title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap-theme.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css" />
    <script src="${ctx}/static/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script>
        var ctxPath = "${ctx}";
    </script>
</head>
<body id="mainframe">
<%@ include file="/common/header.jsp" %>
<div class="container projects">
    <script>
        function redirect(){
            if(top!=this){
                window.top.location.href="${ctx}";
            }else{
                window.location.href="${ctx}";
            }
        }
    </script>
    <div class="errorPage">
        <div class="EPcon">
            <h3>500 </h3>
            <div class="msg"> Server Error!  <br/> <a href="${ctx}/" >返回首页</a></div>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>

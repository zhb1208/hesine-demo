<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>Welcome to Hesine framework!</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap-theme.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css" />
    <script src="${ctx}/static/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <%--<%@ include file="/common/plugin.jsp" %>--%>
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
    <div class="jumbotron">
        <h1>Hesine framework</h1>
        <p>Welcome to Hesine framework!</p>
        <p><a class="btn btn-primary btn-lg" role="button">Learn more</a></p>
    </div>

    <div class="row">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <div class="caption">
                    <h3>News</h3>
                    <p>news1</p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <div class="caption">
                    <h3>Todo</h3>
                    <p>todo1</p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <div class="caption">
                    <h3>Examples</h3>
                    <p>example1</p>
                </div>
            </div>
        </div>
    </div>

    <hr>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>

<%@ include file="/common/taglibs.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: zhanghongbing
  Date: 13-11-6
  Time: 下午6:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="decorator" content="portal"/>
    <title>Edit</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/js/jquery-validation/1.11.0/validate.css" />
    <script src="${ctx}/static/js/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jquery-validation/1.11.0/messages_bs_zh.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/user/add.js" type="text/javascript"></script>
</head>
<body>
<div class="panel-heading">User Edit</div>
<div class="panel-body">
    <form:form id="inputForm" modelAttribute="user" action="${ctx}/portal/user/save" method="post" class="form-horizontal">
        <input type="hidden" name="id" value="${user.id}"/>
        <div id="messageBox" class="alert alert-error input-large controls" style="display:none">输入有误，请先更正。</div>
        <div class="row">
            <div class="offset4">
                &nbsp;&nbsp;
                <label for="loginName" class="control-label">登录名:</label>
                <input type="text" id="loginName" name="loginName" value="${user.loginName}" class="input-large required"/>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="offset4">
                &nbsp;&nbsp;
                <label for="name" class="control-label">用户名:</label>
                <input type="text" id="name" name="name"  value="${user.name}" class="input-large required"/>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="offset4">
                &nbsp;&nbsp;
                <label for="plainPassword" class="control-label">密   码:</label>
                <input type="password" id="plainPassword" name="password" value="${user.password}" class="input-large" placeholder="...Leave it blank if no change"/>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="offset4">
                &nbsp;&nbsp;
                <label for="plainEmail" class="control-label">Email :</label>
                <input type="text" id="plainEmail" name="email" value="${user.email}" class="input-large required email" />
            </div>
        </div>
        <hr>
        <div class="form-actions">
            <input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
        </div>
    </form:form>
</div>
</body>
</html>
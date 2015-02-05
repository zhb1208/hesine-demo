<%@ include file="/common/taglibs.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: zhanghongbing
  Date: 13-11-4
  Time: 下午6:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="decorator" content="portal"/>
    <title>views</title>
</head>
<body>
<div class="panel-heading">User View</div>
<div class="panel-body">
    <table class="table">
        <thead>
        <tr>
            <th>名称</th>
            <th>内容</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>id</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>名称</td>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>
        </tbody>
    </table>
    <div class="form-actions">
        <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
    </div>
</div>
</body>
</html>
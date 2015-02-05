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
    <title>List</title>
</head>
<body>

<div class="panel-heading">User List</div>
<div class="panel-body">
    <c:if test="${not empty message}">
        <div id="message" class="alert alert-success">
            <button data-dismiss="alert" class="close">×</button>
                ${message}</div>
    </c:if>

    <hr>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>登录名</th>
            <th>姓名</th>
            <th>电邮</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}&nbsp;</td>
                <td>${user.loginName}&nbsp;</td>
                <td>${user.name}&nbsp;</td>
                <td>${user.email}&nbsp;</td>
                <td>
                    <a href="${ctx}/portal/user/edit/${user.id}" id="editLink-${user.loginName}">修改</a>
                    <a href="${ctx}/portal/user/view/${user.id}" id="viewLink-${user.loginName}">查看</a>
                    <a href="${ctx}/portal/user/remove/${user.id}" id="removeLink-${user.loginName}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
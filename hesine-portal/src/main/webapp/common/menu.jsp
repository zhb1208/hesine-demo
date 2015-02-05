<%--
  Created by IntelliJ IDEA.
  User: zhanghongbing
  Date: 13-11-7
  Time: 下午4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="list-group">
    <a href="#" class="list-group-item active">
        User
    </a>
    <a class="list-group-item" href="${ctx}/portal/user/list">List</a>
    <a class="list-group-item" href="${ctx}/portal/user/pagelist">pagelist</a>
    <a class="list-group-item" href="${ctx}/portal/user/add">Add</a>
    <!--
    <a class="list-group-item" href="${ctx}/user/edit/1">Edit</a>
    <a class="list-group-item" href="${ctx}/user/view/1">View</a>
    <a class="list-group-item" href="${ctx}/user/delete/1">Delete</a>
    <a class="list-group-item" href="${ctx}/user/remove/1">Remove</a>
    -->
    <a href="#" class="list-group-item active">
        Restfull User
    </a>
    <a class="list-group-item" target="_blank" href="${ctx}/api/user/1.xml">Rest xml</a>
    <a class="list-group-item" target="_blank" href="${ctx}/api/user/1.json">Rest json</a>
    <a class="list-group-item" target="_blank" href="${ctx}/api/user/1">Rest 1</a>
</div>
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

    <div class="row">
        <div class="offset4">
            <form class="form-search" action="${ctx}/portal/user/pagelist" method="post" id="query_fm">
                <label>登录名：</label> <input type="text" name="loginName" class="input-small"
                                           value="${fn:escapeXml(userModel.loginName)}">
                <label>邮件名：</label> <input type="text" name="email" class="input-small"
                                           value="${fn:escapeXml(userModel.email)}">
                <button type="submit" class="btn" id="search_btn">Search</button>
            </form>
        </div>
    </div>
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
        <c:forEach items="${pager.result}" var="user">
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
    <div class="page">
        <div class="page-right">
            <div class="m clearfix">
                <div style="float:right;">
                    共${pager.totalCount}条记录
                    <pg:pager
                            url=""
                            items="${pager.totalCount}"
                            index="center"
                            maxPageItems="${pager.pageSize}"
                            maxIndexPages="10"
                            export="offset,currentPageNumber=pageNumber"
                            scope="request">

                        <%-- keep track of preference --%>
                        <pg:index>
                            <jsp:include page="/pagination/post-form.jsp" flush="true"/>
                        </pg:index>
                    </pg:pager>
                </div>
            </div>
            <div class="clear">
            </div>
        </div>
    </div>
</div>
</body>
</html>
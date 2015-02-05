<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="decorator" content="portal"/>
    <title>List</title>
</head>
<body>
<div class="panel-heading">${ClassName} List</div>
<div class="panel-body">
    <c:if test="${r"${not empty message}"}">
        <div id="message" class="alert alert-success">
            <button data-dismiss="alert" class="close">×</button>
        ${r"${message}"}</div>
    </c:if>

    <div class="row">
        <div class="offset4">
            <form class="form-search" action="${r"${ctx}"}/portal/${className}/list" method="post" id="query_fm">
                <#--<label>名称：</label> <input type="text" name="name" class="input-small"-->
                                           <#--value="${r"${"}fn:escapeXml(${className}Model.name)}">-->
                <button type="submit" class="btn" id="search_btn">Search</button>
            </form>
        </div>
    </div>
    <hr>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <#list fileds as filed>
                <th>${filed.comment}</th>
            </#list>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${r"${"}pager.result}" var="${className}">
            <tr>
                <td>${user.id}&nbsp;</td>
                <td>${user.loginName}&nbsp;</td>
                <td>${user.name}&nbsp;</td>
                <td>${user.email}&nbsp;</td>
                <td>
                    <a href="${r"${ctx}"}/portal/user/edit/${r"${"}${className}.id}" id="editLink-${user.loginName}">修改</a>
                    <a href="${r"${ctx}"}/portal/user/view/${r"${"}${className}.id}" id="viewLink-${user.loginName}">查看</a>
                    <a href="${r"${ctx}"}/portal/user/remove/${r"${"}${className}.id}" id="removeLink-${user.loginName}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page">
        <div class="page-right">
            <div class="m clearfix">
                <div style="float:right;">
                    共${r"${"}pager.totalCount}条记录
                    <pg:pager
                            url=""
                            items="${r"${"}pager.totalCount}"
                            index="center"
                            maxPageItems="${r"${"}pager.pageSize}"
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
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
    <div>
        <button type="button" class="btn btn-primary" onclick="window.location.href='${r"${ctx}"}/${className}/list'">${ClassName}列表</button>
        <button type="button" class="btn btn-success" onclick="window.location.href='${r"${ctx}"}/${className}/add'">新增${ClassName}</button>
    </div>
    <br>
    <c:if test="${r"${not empty message}"}">
        <div id="message" class="alert alert-success">
            <button data-dismiss="alert" class="close">×</button>
        ${r"${message}"}</div>
    </c:if>

    <div class="row">
        <div class="offset4">
            <form class="form-search" action="${r"${ctx}"}/${className}/list" method="post" id="query_fm">
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
            <#list fileds as filed>
                <th>${filed.comment}</th>
            </#list>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${r"${"}pager.result}" var="${className}">
            <tr>
            <#list fileds as filed>
                <td>${r"${"}${className}.${filed.name}}&nbsp;</td>
            </#list>
                <td>
                    <a href="${r"${ctx}"}/${className}/edit/${r"${"}${className}.id}" id="editLink-${r"${"}${className}.id}">修改</a>
                    <a href="${r"${ctx}"}/${className}/${r"${"}${className}.id}" id="viewLink-${r"${"}${className}.id}">查看</a>
                    <a href="${r"${ctx}"}/${className}/delete/${r"${"}${className}.id}" id="removeLink-${r"${"}${className}.id}">删除</a>
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
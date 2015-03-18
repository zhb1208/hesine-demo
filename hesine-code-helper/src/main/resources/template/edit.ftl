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
    <link rel="stylesheet" type="text/css" href="${r"${"}ctx}/static/js/jquery-validation/1.11.0/validate.css" />
    <link rel="stylesheet" type="text/css" href="${r"${"}ctx}/html/css/style.css" />
    <script src="${r"${"}ctx}/static/js/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${r"${"}ctx}/static/js/jquery-validation/1.11.0/messages_bs_zh.js" type="text/javascript"></script>
    <script src="${r"${"}ctx}/static/js/${className}/add.js" type="text/javascript"></script>
</head>
</head>
<body>
<div class="panel-heading">${ClassName} Edit</div>
<div class="panel-body">
    <div>
        <button type="button" class="btn btn-primary" onclick="window.location.href='${r"${ctx}"}/${className}/list'">${ClassName}列表</button>
        <button type="button" class="btn btn-success" onclick="window.location.href='${r"${ctx}"}/${className}/add'">新增${ClassName}</button>
    </div>
    <br>
    <form:form id="inputForm" modelAttribute="${className}" action="${r"${"}ctx}/${className}/save" method="post" class="form-horizontal">
        <div>
        <input type="hidden" name="id" value="${r"${"}${className}.id}"/>

    <#list fileds as filed>
        <#if filed.name!='id' && filed.name!='createTime' && filed.name!='modifyTime' && filed.name!='status'>
            <div class="new" style="">
                &nbsp;&nbsp;
                <label for="${filed.name}" id="${className}_lable_${filed.name}" class="control-label">${filed.comment}:</label>

                <div class="ipt_box">
                    <div style="background: url('${r"${"}ctx}/static/images/ipt.png?v=10031') no-repeat scroll 0 0 transparent; border: 0 none; height: 35px; width: 300px;">
                        <input type="text" id="${filed.name}" name="${filed.name}" value="${r"${"}${className}.${filed.name}}"/>
                    </div>
                </div>
            </div>
            <br>
        </#if>
    </#list>

    </div>
    <div class="form-actions" style="margin:0 auto;text-align:center">
        <br>
        <input id="submit_btn" style="width: 100px;" class="btn btn-primary" type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="cancel_btn" style="width: 100px;" class="btn btn-primary" type="button" value="返回" onclick="history.back()"/>
    </div>
    </form:form>
</div>
</body>
</html>
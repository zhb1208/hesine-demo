<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ page session="false" %>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>
<pg:first export="pageNo=pageNumber">
    <a href="javascript:pageQuery(${pageNo});">首页</a>
</pg:first>
<pg:prev export="pageNo=pageNumber">
    <a href="javascript:pageQuery(${pageNo});">上一页</a>
</pg:prev>
<pg:pages export="pageNo=pageNumber"><%
    if (pageNo == currentPageNumber) {
%> <b>${pageNo}</b> <%
} else {
%>
    <a href="javascript:pageQuery(${pageNo});"><%= pageNo %>
    </a> <%
        }
    %></pg:pages>
<pg:next export="pageNo=pageNumber">
    <a href="javascript:pageQuery(${pageNo});">下一页</a>
</pg:next>
<pg:last export="pageNo=pageNumber">
    <a href="javascript:pageQuery(${pageNo});">末页</a>
</pg:last>
<pg:page>
    到第 <input name="page" id="nowPage" size="3" type="text"> 页
    <input id="goPage" class="btn-chaxun" value="" type="submit">
</pg:page>

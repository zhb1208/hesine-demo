<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page session="false" %>
<script>
    function checkNumeric(value){
        return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
    }
    function onSearchForm(uri) {
        $("form:first").attr("action", uri);
        $("form:first").submit();
    }
    function goPage(uri, totalCount, pageSize) {
        var input = $("#toPageNo").val();
        input = $.trim(input);
        if(input == null || input.length == 0 || !checkNumeric(input)){
            alert("请正确输入页码");
            return;
        }
        var pageNo = parseInt(input);
        if(pageNo<=0){
            alert("请正确输入页码");
            return;
        }
        if(totalCount <= ((pageNo-1) * pageSize)){
            alert("超过最大页码范围");
            return;
        }
        uri = uri + "&pageNo=" + pageNo;
        uri = uri.replace(/pager.offset=\d*/g, "pager.offset=" + (pageNo - 1) * pageSize);
        onSearchForm(uri);
    }
</script>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>
<pg:first export="firstPageUrl=pageUrl,pageNo=pageNumber">
    <a href="javascript:onSearchForm('${firstPageUrl}&pageNo=${pageNo}');">首页</a>
</pg:first>
<pg:prev export="prevPageUrl=pageUrl,pageNo=pageNumber">
    <a href="javascript:onSearchForm('${prevPageUrl}&pageNo=${pageNo}');">上一页</a>
</pg:prev>
<pg:pages export="pageUrl,pageNo=pageNumber">
    <c:choose>
        <c:when test="${currentPageNumber eq pageNo}">
            <b><font color="red">${pageNo}</font></b>
        </c:when>
        <c:otherwise>
            <%--<a href="${pageUrl}&pageNo=${pageNo}">${pageNo}</a>--%>
            <a href="javascript:onSearchForm('${pageUrl}&pageNo=${pageNo}');">${pageNo}</a>
        </c:otherwise>
    </c:choose>
</pg:pages>
<pg:next export="nextPageUrl=pageUrl,pageNo=pageNumber">
    <a href="javascript:onSearchForm('${nextPageUrl}&pageNo=${pageNo}');">下一页</a>
</pg:next>
<pg:last export="lastPageUrl=pageUrl,pageNo=pageNumber">
    <a href="javascript:onSearchForm('${lastPageUrl}&pageNo=${pageNo}');">末页</a>
</pg:last>
<pg:page export="goPageUrl=pageUrl,pageNo=pageNumber">到第 <input name="page" id="toPageNo" size="3" type="text" style="width: 15px;"> 页
    <input id="goPage" class="btn-chaxun" value="GO"
           onclick="javascript:goPage('${goPageUrl}',${pager.items},${pager.maxPageItems});" type="button">
</pg:page>

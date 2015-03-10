<%@page contentType="text/html;charset=UTF-8"%>
<c:set var="USE_JQUERY_DATEPICKER" scope="request"><decorator:getProperty property="meta.USE_JQUERY_DATEPICKER"/></c:set>
<c:set var="USE_JQUERY_VALIDATOR" scope="request"><decorator:getProperty property="meta.USE_JQUERY_VALIDATOR"/></c:set>
<c:set var="USE_JQUERY_UPLOADIFY" scope="request"><decorator:getProperty property="meta.USE_JQUERY_UPLOADIFY"/></c:set>
<c:set var="USE_JQUERY_JSTREE" scope="request"><decorator:getProperty property="meta.USE_JQUERY_JSTREE"/></c:set>
<c:set var="USE_JQUERY_UI" scope="request"><decorator:getProperty property="meta.USE_JQUERY_UI"/></c:set>
<c:if test="${USE_JQUERY_JSTREE=='true'}">
    <script src="${ctx}/resources/js/plugins/jstree-v.pre1.0/jquery.jstree.js" type="text/javascript"></script>
    <script src="${ctx}/resources/js/plugins/jstree-v.pre1.0/_lib/jquery.cookie.js" type="text/javascript"></script>
    <script src="${ctx}/resources/js/plugins/jstree-v.pre1.0/_lib/jquery.hotkeys.js" type="text/javascript"></script>
</c:if>
<c:if test="${USE_JQUERY_UI=='true'}">
    <script src="${ctx}/resources/js/jquery-ui-1.10.2.custom/js/jquery-ui-1.10.2.custom.min.js" type="text/javascript"></script>
    <link href="${ctx}/resources/js/jquery-ui-1.10.2.custom/css/ui-lightness/jquery-ui-1.10.2.custom.min.css" type="text/css" rel="stylesheet" />
</c:if>
<c:if test="${USE_JQUERY_DATEPICKER=='true'}">
    <script src="${ctx}/resources/js/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</c:if>
<c:if test="${USE_JQUERY_VALIDATOR=='true'}">
    <link href="${ctx}/resources/js/plugins/jqueryvalidate/jquery_validate.css" type="text/css" rel="stylesheet" />
    <script src="${ctx}/resources/js/plugins/jqueryvalidate/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/resources/js/plugins/jqueryvalidate/additional-methods.js" type="text/javascript"></script>
</c:if>
<c:if test="${USE_JQUERY_UPLOADIFY=='true'}">

</c:if>
	
	
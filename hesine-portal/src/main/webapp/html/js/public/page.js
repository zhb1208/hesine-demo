/**
 * Created by zhanghongbing on 14-12-13.
 */

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
        alert("输入页数不得大于总页数");
        return;
    }
    uri = uri + "&pageNo=" + pageNo;
    uri = uri.replace(/pager.offset=\d*/g, "pager.offset=" + (pageNo - 1) * pageSize);
    onSearchForm(uri);
}

// 创建分页
function createPage( totalCount, pageSize, maxIndexPages, currentPageNumber) {
//    var html = "共"+totalCount+"条记录";
    var html = $.i18n.prop('page_string_total_pre') + "&nbsp;"
        + totalCount + "&nbsp;" + $.i18n.prop('page_string_total_next') + "&nbsp;";
    var pages = Math.ceil(totalCount/pageSize);
    if (pages <= 1) {
        $("#pageShow").html(html);
        return;
    }
    var firstPageUrl = "?pager.offset=0";
    var prevPageUrl = "?pager.offset=0";
        html += "<a href='javascript:onSearchForm(\"?pager.offset=0&pageNo=1\");'>"
            +$.i18n.prop('page_string_first')+"</a>&nbsp;";
    var prePage = currentPageNumber - 1;
    if (prePage > 0) {
        html += "<a href='javascript:onSearchForm(\"?pager.offset=" + prePage*pageSize + "&pageNo=1\");'>"
            +$.i18n.prop('page_string_pre')+"</a>&nbsp;";
    }

    var beginIndex = 1;
    var endIndex = pages;
    if (currentPageNumber > maxIndexPages) {
        var tmpPage = currentPageNumber + (maxIndexPages/2 - 1);
        if (tmpPage < pages) {
            beginIndex = currentPageNumber - maxIndexPages/2;
            endIndex = tmpPage;
        } else {
            beginIndex = currentPageNumber - maxIndexPages/2 - (tmpPage - pages);
            endIndex = pages;
        }
    }
    var indexPage = 1;
    var count = 0;
    for(indexPage = beginIndex; indexPage <= endIndex; indexPage++) {
        if (indexPage == currentPageNumber) {
            html += "<b><font color=\"red\" style=\"color:red;\">" + currentPageNumber + "</font></b>&nbsp;";
            html += "<input type='hidden' name='currentUri' id='currentUri' value='?pager.offset=" +
                (currentPageNumber-1)*pageSize + "&pageNo=" + currentPageNumber + "' /> ";
        } else {
            html += "<a href='javascript:onSearchForm(\"?pager.offset=" +
                (indexPage-1)*pageSize + "&pageNo=" + indexPage + "\");'>" + indexPage + "</a>&nbsp;";
        }
        count++;
        if (count == maxIndexPages) {
            break;
        }
    }


    var nextPage = currentPageNumber + 1;
    if (nextPage <= pages) {
        html += "<a href='javascript:onSearchForm(\"?pager.offset=" + prePage*pageSize + "&pageNo=" + nextPage + "\");'>"
            +$.i18n.prop('page_string_next')+"</a>&nbsp;";
    }

    html += "<a href='javascript:onSearchForm(\"?pager.offset=" + (pages-1)*pageSize + "&pageNo=" + pages + "\");'>"
        +$.i18n.prop('page_string_end')+"</a>";
    $("#pageShow").html(html);
    $("#totalCount").val(totalCount);
    $("#pageSize").val(pageSize);
    $("#pageNo").val(currentPageNumber);
    return;

}

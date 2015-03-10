/**
 * Created by zhanghongbing on 14-12-20.
 */

// search list
function list(url, obj){
    $.ajax({
        url: url,
        type: 'post',
        data: obj,
        dataType: 'json',
        success: function (message) {
            if(message.status == 1){
                switch(message.errorCode){
                    case 10000:
                        alert($.i18n.prop('string_systemerror'));
                        break;
                    case 10001:
                        alert($.i18n.prop('string_systemerror'));
                        break;
                    case 20004:
                        alert($.i18n.prop('string_authfail'));
                        location.href = window.path + "/login.html";
                        return;
                    default:
                        alert($.i18n.prop('string_loginfail'));
                        location.href = window.path + "/login.html";
                        return;
                }
            }else{
                var users = message.pager.result;
                $("#tableData").html();
                var html = '';
                for(var i in users) {
                    var id = users[i].id;
                    html += "<tr><td class='r2'>"+ users[i].id+
                        "</td><td class='r2'>"+ users[i].loginName+"</td><td class='r2'>"
                        + users[i].name+"</td><td class='r2'>"+ users[i].email+"</td>"
                        + "<td class='r2'><a href='#' onclick='javascript:editObj("+ id +");'>"
                        + $.i18n.prop('public_string_edit') +"</a>&nbsp;"
                        + "<a href='#' onclick='javascript:removeObj("+ users[i].id+");'>"
                        + $.i18n.prop('public_string_del') + "</a></td></tr>"
                }
                $("#tableData").html(html);
            }

            // 创建分页
            createPage(message.pager.totalCount,
                message.pager.pageSize,10, message.pager.pageNo);
        },
        error: function (message) {
            alert(message);
        }
    });
}

// search list
function onSearchForm(uri) {
    var url =  window.path + "/user/list" + uri;
    var obj = $("#inputFormId").serializeObject();
    list(url, obj);
}

// edit by id
function editObj(id) {
    var url =  window.path + "/html/user/edit.html?id=" + id;
    window.location = url;
}

// remove by id
function removeObj(id) {
    var url =  window.path + "/user/remove/" + id;
    var obj = null;
    if (confirm($.i18n.prop('public_string_confirm_del'))) {
        $.ajax({
            url: url,
            type: 'get',
            data: obj,
            dataType: 'json',
            success: function (message) {
                if(message.status == 1){
                    switch(message.errorCode){
                        case 10000:
                            alert($.i18n.prop('string_systemerror'));
                            break;
                        case 10001:
                            alert($.i18n.prop('string_systemerror'));
                            break;
                        case 20004:
                            alert($.i18n.prop('string_authfail'));
                            location.href = window.path + "/login.html";
                            return;
                        default:
                            alert($.i18n.prop('string_loginfail'));
                            location.href = window.path + "/login.html";
                            return;
                    }
                }else{
                    alert($.i18n.prop('public_string_del_success'));
                    var currentUri = $('#currentUri').val();
                    var url =  window.path + "/user/list" + currentUri;
                    var obj = $("#inputFormId").serializeObject();
                    list(url, obj);
                }
            },
            error: function (message) {
                alert(message);
            }
        });
    }

}

// init js
$(document).ready(function () {

    // load page properties
    loadProperties();

    var url =  window.path + "/user/list";
    var obj = $("#inputFormId").serializeObject();
    list(url, obj);

});


// inti page value
function loadProperties(){
    jQuery.i18n.properties({//加载资浏览器语言对应的资源文件
        name:'strings', //资源文件名称
        path:window.path +'/html/i18n/', //资源文件路径
        mode:'map', //用Map的方式使用资源文件中的值
        callback: function() {//加载成功后设置显示内容
            $('#user_list_title').html($.i18n.prop('user_list_string_title'));

            // 初始化
            loadInitProperties();

            // user menu
            $('#menu_user_function').html($.i18n.prop('menu_user_string_function'));
            $('#menu_user_manage').html($.i18n.prop('menu_user_string_manage'));
            $('#menu_user_add').html($.i18n.prop('menu_user_string_add'));
            $('#menu_user_list').html($.i18n.prop('menu_user_string_list'));
            // user function
            $('#function_user_function').html($.i18n.prop('menu_user_string_function'));
            $('#function_user_add').html($.i18n.prop('function_user_string_add'));
            $('#function_user_list').html($.i18n.prop('function_user_string_list'));

        }
    });
}
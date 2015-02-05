/**
 * Created by zhanghongbing on 14-12-20.
 */

$(document).ready(function () {

    /**
     * 新增用户
     */
    $('#submit_btn').click(function () {
        var obj = $("#inputFormId").serializeObject();
        var url = window.path + '/user/save';
        $.ajax({
            url: url,
            type: 'post',
            data: obj,
            dataType: 'json',
            success: function (message) {
                var aa = JSON.stringify(message);
                if (message.status == 1) {
                    switch (message.errorCode) {
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
                } else {
                    alert($.i18n.prop('public_string_add_success'));
                    $("#name").val('');
                    $("#loginName").val('');
                    $("#plainEmail").val('');
                    $("#plainPassword").val('');
                }
            },
            error: function (message) {
                alert(message);
            }
        });
    });

    // load page properties
    loadProperties();

    /**
     * 检查是否登录
     */
    checkLogin();
});

// inti page value
function loadProperties(){
    jQuery.i18n.properties({//加载资浏览器语言对应的资源文件
        name:'strings', //资源文件名称
        path:window.path +'/html/i18n/', //资源文件路径
        mode:'map', //用Map的方式使用资源文件中的值
        callback: function() {//加载成功后设置显示内容
            $('#user_add_title').html($.i18n.prop('user_add_string_title'));

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

            // user add
            $('#user_lable_loginName').html($.i18n.prop('user_string_loginName'));
            $('#user_lable_name').html($.i18n.prop('user_string_name'));
            $('#user_lable_password').html($.i18n.prop('user_string_password'));
            $('#user_lable_email').html($.i18n.prop('user_string_email'));
            // user submit
            $('#submit_btn').val($.i18n.prop('public_string_submit'));
        }
    });
}
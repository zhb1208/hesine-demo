/**
 * Created by zhanghongbing on 14-12-20.
 */

window.path = '/demo';

/**
 * 检查登录用户
 */
function checkLogin() {
    var url = window.path + '/account/checkLogin';
    $.ajax({
        url: url,
        type: 'get',
        data: null,
        dataType: 'json',
        success: function (message) {
            var aa = JSON.stringify(message);
            if (message.status == 1) {
                switch (message.errorCode) {
                    case 10000:
                        alert("系统异常");
                        break;
                    case 20004:
                        alert("用户认证失败");
                        location.href= window.path + "/login.html";
                    default:
                        alert("登陆失败");
                        location.href = window.path + "/login.html";
                        return;
                }
            }
        },
        error: function (message) {
            alert(message);
        }
    });
}

function loadInitProperties(){
    // header
    $('#header_backend').html($.i18n.prop('header_string_backend'));
    $('#header_platform').html($.i18n.prop('header_string_platform'));
    $('#header_exit').html($.i18n.prop('header_string_exit'));
    $('#header_welcome').html($.i18n.prop('header_string_welcome'));

    // footer
    $('#footer_about').html($.i18n.prop('footer_string_about'));
    $('#footer_protocol').html($.i18n.prop('footer_string_protocol'));
    $('#footer_rule').html($.i18n.prop('footer_string_rule'));
    $('#footer_contact').html($.i18n.prop('footer_string_contact'));
    $('#footer_email').html($.i18n.prop('footer_string_email'));
    $('#footer_email').html($.i18n.prop('footer_string_copyright'));
}
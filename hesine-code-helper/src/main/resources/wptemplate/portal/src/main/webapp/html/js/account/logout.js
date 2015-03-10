/**
 * Created by zhanghongbing on 14-12-20.
 */
$(document).ready(function(e) {

    /**
     * 登录赋值
     */
    var user = getLoginUser();
    $('#loginUser').text(user);

    /**
     * 系统登出
     */
    $(".loginOut").click(function() {
        var url = window.path + '/account/logout';
        $.ajax({
            url: url,
            type: 'post',
            data: '',
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
                    }
                }else{
                    alert($.i18n.prop('header_string_logout_success'));
                    location.href= window.path + "/login.html";
                }
            },
            error: function (message) {
                alert("系统异常,请于管理员联系");
            }
        });
    });
});
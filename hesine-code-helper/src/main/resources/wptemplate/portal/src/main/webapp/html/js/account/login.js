/**
 * Created by zhanghongbing on 14-12-20.
 */

//设置Cookie保存时间
var time = 0;

// JavaScript Document
$(document).ready(function(e) {

    /**
     * 导入国际化文件
     */
    loadProperties();

	if(!$.support.leadingWhitespace){
	   $("#lowexploreTip").css("display","block");
	}
	
	// 获取Cookie保存的用户名和密码
	var username = getCookieValue("cookUser");
	var password = getCookieValue("cookPass");

	if (username != '' && password != '') {
		$(".account input[name=userName]").val(username);
		$(".account input[name=password]").val(password);
		$("#rememberMe").addClass("check");
	} else{
		$("#rememberMe").removeClass("check")
		$("#rememberMe").addClass("uncheck");
	}

	$("#rememberMe").click(function() {// 记住密码
		if ($(this).hasClass("uncheck")) {
			$(this).removeClass("uncheck")
			$(this).addClass("check");
			time = 60 * 60 * 60;
		}else{
			$(this).removeClass("check")
			$(this).addClass("uncheck");
			time = 0;
		}
	});
	
    /**
     * 登陆操作
     */
    $(".loginBtn").click(function() {
        var user = {username:$("#userName").val(), password:$("#password").val()};
        var url = window.path +'/account/login';
        if(user.username =="" || user.password == ""){
            $(".tip").html($.i18n.prop('login_string_notempty'));
        }else{
            $.ajax({
                url: url,
                type: 'post',
                data: user,
                dataType: 'json',
                success: function (message) {
                    if(message.status == 1){
                        switch(message.errorCode){
                            case 10000:
                                $(".tip").html($.i18n.prop('string_systemerror'));
                                break;
                            case 10001:
                                $(".tip").html($.i18n.prop('string_systemerror'));
                                break;
                            case 20001:
                                $(".tip").html($.i18n.prop('public_string_notempty'));
                            case 20005:
                                $(".tip").html($.i18n.prop('string_usernotexist'));
                                break;
                            case 20006:
                                $(".tip").html($.i18n.prop('string_wrongpassword'));
                                break;
                            default:
                                $(".tip").html($.i18n.prop('string_loginfail'));
                                break;
                        }
                    }else{
                        $(".tip").empty();
                        setCookie('cookUser', user.userName, time, '/');// set 获取用户名和密码 传给cookie
                        setCookie('cookPass', user.password, time, '/');
                        location.href= window.path + "/html/user/add.html";
                    }
                },
                error: function (message) {
                    alert(message);
                }
            });
        }
    });

	/**
	 * 输入密码回车
	 */
	$(".account input[name=password]").keypress(function(e) {
		if (e.which == 13) {
			$(".loginBtn").click();
		}
	});
});

function loadProperties(){
    jQuery.i18n.properties({//加载资浏览器语言对应的资源文件
        name:'strings', //资源文件名称
        path:window.path +'/html/i18n/', //资源文件路径
        mode:'map', //用Map的方式使用资源文件中的值
        callback: function() {//加载成功后设置显示内容
            //标题
            $('#login_title').html($.i18n.prop('login_string_title'));
            //标题内容
            $('#login_label_title').html($.i18n.prop('login_string_title'));
            //用户名
            $('#login_label_name').html($.i18n.prop('login_string_username'));
            //密码
            $('#login_label_password').html($.i18n.prop('login_string_password'));
            //登录
            $('#login_label_remenber').html($.i18n.prop('login_string_remenber'));
            //登录按钮
            $('#login_btn').val($.i18n.prop('login_string_btn'));
        }
    });
}
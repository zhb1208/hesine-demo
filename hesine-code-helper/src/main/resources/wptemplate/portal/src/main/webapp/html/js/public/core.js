function successMsg(msg, msgType) {
    if (!msgType) {
        msgType = "error";
    }
    noticeMessage(msg, msgType);
}

function successMessage(msg) {
    noticeMessage(msg, "success");
}

function errorMessage(msg) {
    noticeMessage(msg, "error");
}

function noticeMessage(msg, msgType) {
    $.globalMessenger().post({
        message: msg,
        hideAfter: 2,
        type: msgType,
        showCloseButton: true
    });
}

function WaitMessage(msg, msgType,hideTime) {
    $.globalMessenger().post({
        message: msg,
        hideAfter: hideTime,
        type: msgType,
        showCloseButton: true
    });
}

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[$.trim(this.name)]];
            }
            o[this.name].push($.trim(this.value) || '');
        } else {
            o[this.name] = $.trim(this.value) || '';
        }
    });
    return o;
};

//$.fn.tree.defaults.loadFilter = function (data, parent) {
//    var opt = $(this).data().tree.options;
//    var idField, textField, parentField;
//    if (opt.parentField) {
//        idField = opt.idField || 'id';
//        textField = opt.textField || 'text';
//        parentField = opt.parentField;
//        var i, l, treeData = [], tmpMap = [];
//        for (i = 0, l = data.length; i < l; i++) {
//            tmpMap[data[i][idField]] = data[i];
//        }
//        for (i = 0, l = data.length; i < l; i++) {
//            if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
//                if (!tmpMap[data[i][parentField]]['children'])
//                    tmpMap[data[i][parentField]]['children'] = [];
//                data[i]['text'] = data[i][textField];
//                tmpMap[data[i][parentField]]['children'].push(data[i]);
//            } else {
//                data[i]['text'] = data[i][textField];
//                treeData.push(data[i]);
//            }
//        }
//        return treeData;
//    }
//    return data;
//};
//
//$.fn.treegrid.defaults.loadFilter = function (data, parentId) {
//    var opt = $(this).data().treegrid.options;
//    var idField, textField, parentField;
//    if (opt.parentField) {
//        idField = opt.idField || 'id';
//        textField = opt.textField || 'text';
//        parentField = opt.parentField;
//        var i, l, treeData = [], tmpMap = [];
//        for (i = 0, l = data.length; i < l; i++) {
//            tmpMap[data[i][idField]] = data[i];
//        }
//        for (i = 0, l = data.length; i < l; i++) {
//            if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
//                if (!tmpMap[data[i][parentField]]['children'])
//                    tmpMap[data[i][parentField]]['children'] = [];
//                data[i]['text'] = data[i][textField];
//                tmpMap[data[i][parentField]]['children'].push(data[i]);
//            } else {
//                data[i]['text'] = data[i][textField];
//                treeData.push(data[i]);
//            }
//        }
//        return treeData;
//    }
//    return data;
//};
//
//$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

$(document).ready(function () {
    var elements = $("input,select,textarea[class='note']");
    for (var i = 0; i < elements.length; i++) {
        var className = elements[i].className;
        if (className.indexOf("notEmpty") != -1) {
            var divElement = document.createElement("div");
            divElement.style.display = "inline";
            elements[i].parentNode.appendChild(divElement);
            elements[i].onblur = function () {
                var k = strlen(this.value.trim());
                if (k == 0) {
                    this.parentNode.childNodes[2].innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>禁止为空,请输入！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                } else {
                    this.parentNode.childNodes[2].innerHTML = "";
                }
            }
        }
        if (className.indexOf("ContactName") != -1) {
            var divElement = document.createElement("div");
            divElement.style.display = "inline";
            elements[i].parentNode.appendChild(divElement);
            elements[i].onblur = function () {
                var k = strlen(this.value.trim());
                if (k > 30 && k > 0) {
                    this.parentNode.childNodes[1].innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>联系人不能超过30个字节！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                } else {
                    this.parentNode.childNodes[1].innerHTML = "";
                }
            }
        }
        if (className.indexOf("contactMobile") != -1) {
            var patrn = /^\d{11}$/;
            var divElement = document.createElement("div");
            divElement.style.display = "inline";
            elements[i].parentNode.appendChild(divElement);
            elements[i].onblur = function () {
                if (patrn.test(this.value.trim()) == false && this.value.trim() != "") {
                    this.parentNode.childNodes[1].innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>手机号码11位数！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                } else {
                    this.parentNode.childNodes[1].innerHTML = "";
                }
            }
        }
        if (className.indexOf("note") != -1) {
            var divElement = document.createElement("div");
            divElement.style.display = "inline";
            elements[i].parentNode.appendChild(divElement);
            elements[i].onblur = function () {
                var k = strlen(this.value.trim());
                if (k > 60) {
                    this.parentNode.childNodes[3].innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>备注不能超过60个字节！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                } else {
                    this.parentNode.childNodes[3].innerHTML = "";
                }
            }
        }
    }
});

function checkForm(formId) {
    var flag = true;
    var elements = $("input,select,textarea[class='note']");
    for (var i = 0; i < elements.length; i++) {
        var span = elements[i].parentNode.childNodes[2];
        var className = elements[i].className;
        var name = elements[i].name;
        if (name == 'name') {
            elements[i].value = elements[i].value.trim();
        }
        if (name == 'code') {
            elements[i].value = elements[i].value.trim();
        }
        if (className.indexOf("ContactName") != -1) {
            var k = strlen(elements[i].value.trim());
            if (k > 30 && k > 0) {
                span.innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>联系人不能超过30个字节！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                flag = false;
            } else {
                span.innerHTML = "";
            }
        }
        if (className.indexOf("notEmpty") != -1) {
            var k = strlen(elements[i].value.trim());
            if (k == 0) {
                span.innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>禁止为空,请输入！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                flag = false;
            } else {
                span.innerHTML = "";
            }
        }
        if (className.indexOf("note") != -1) {
            var k = strlen(elements[i].value.trim());
            if (k > 60 && k > 0) {
                span.innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>备注不能超过60个字节！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                flag = false;
            } else {
                span.innerHTML = "";
            }
        }
        if (className.indexOf("contactMobile") != -1) {
            var patrn = /^\d{11}$/;
            if (patrn.test(elements[i].value.trim()) == false && elements[i].value.trim() != "") {
                span.innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>手机号码11位数！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                flag = false;
            } else {
                span.innerHTML = "";
            }
            if ($("#contactMobile").val() == "" && $("#contactPhone").val() == "") {
                span.innerHTML = "<div class='validatebox-tip' style='display: inline; '><span class='validatebox-tip-content'>电话和手机二选一！</span><span class='validatebox-tip-pointer' style='top: 6px;'></span></div>";
                flag = false;
            }
        }
    }
    return flag;

}

function strlen(str) {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
            len++;
        }
        else {
            len += 2;
        }
    }
    return len;
}

function checkAll(id, checkName) {
    var isCheck = document.getElementById("" + id).checked;
    var allElement = document.getElementsByName("" + checkName);
    if (isCheck) {
        for (var i = 0; i < allElement.length; i++) {
            allElement[i].checked = true;
        }

    } else {
        for (var i = 0; i < allElement.length; i++) {
            allElement[i].checked = false;
        }
    }
}

function checkElements(checkName) {
    var total = 0;
    var allElement = document.getElementsByName("" + checkName);
    for (var i = 0; i < allElement.length; i++) {
        if (allElement[i].checked == true) {
            total++;
        }
    }
    return total;
}

function twoDecimal(value, rowData, rowIndex) {
    if (!value || isNaN(value)) {
        return 0;
    } else {
        return parseFloat(value).toFixed(2);
    }
}
function fourDecimal(value, rowData, rowIndex) {
    if (!value || isNaN(value)) {
        return 0;
    } else {
        return parseFloat(value).toFixed(4);
    }
}

function dateFormatter(value, rowData, rowIndex) {
    if (value) {
        var date = new Date(value);
        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
    }
}

function amountDecimal(value, rowData, rowIndex) {
    if (!value || isNaN(value)) {
        return 0;
    } else {
        return parseFloat(value).toFixed(6);
    }
}

function parseInteger(value, rowData, rowIndex) {
    if (!value || isNaN(value)) {
        return 0;
    } else {
        return parseInt(value);
    }
}

function closeDiv() {
    var elements = $("input,select,textarea[class='note']");
    for (var i = 0; i < elements.length; i++) {
        var className = elements[i].className;
        if (className.indexOf("notEmpty") != -1 || className.indexOf("ContactName") != -1 || className.indexOf("contactMobile") != -1 || className.indexOf("note") != -1) {
            var span = elements[i].parentNode.childNodes[1];
            span.innerHTML = "";
        }
    }
}

//CheckBox三状态
function checkboxInit(checkbox, initValue) {
    switch (initValue) {
        case 0:
            checkbox.data('checked', 0).prop('indeterminate', false).prop('checked', false);
            break;
        case 1:
            checkbox.data('checked', 1).prop('indeterminate', false).prop('checked', true);
            break;
        default :
            checkbox.data('checked', -1).prop('indeterminate', true).prop('checked', false);
            break;
    }
    checkbox.unbind("click").bind('click', function () {
        switch (checkbox.data('checked')) {
            // 不选中  转变为  选中
            case 0:
                checkbox.data('checked', 1);
                checkbox.prop('indeterminate', false);
                checkbox.prop('checked', true);
                break;
            // 半选中  转变为  不选中
            case -1:
                checkbox.data('checked', 0);
                checkbox.prop('indeterminate', false);
                checkbox.prop('checked', false);
                break;
            // 选中  转变为  半选中
            case 1:
                checkbox.data('checked', -1);
                checkbox.prop('indeterminate', true);
                checkbox.prop('checked', false);
        }
    });
}

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

$.extend($.fn.datagrid.defaults.editors, {
    datetimebox: {
        init: function (container, options) {
            var input = $('<input />').appendTo(container);
            return input.datetimebox();
        },
        getValue: function (target) {
            return $(target).datetimebox("getValue");
        },
        setValue: function (target, value) {
            $(target).datetimebox("setValue", value);
        },
        resize: function (target, width) {
            $(target).datetimebox('resize', width);
        },
        destroy: function (target) {
            $(target).datetimebox('destroy');
        }
    }
});

function dateTimeFormatter(value, row, index) {
    if (value == undefined) {
        return "";
    }
    return new Date(value).format("yyyy-MM-dd hh:mm:ss");
}

function compareDate(fromDate, thruDate, message) {
    if (fromDate > thruDate) {
        errorMessage(message);
        return false;
    }
    return true;
}

function openDialog(dialogName) {
    $("#" + dialogName).dialog("open");
}
function closeDialog(dialogName) {
    $("#" + dialogName).dialog("close");
}

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
})(jQuery);
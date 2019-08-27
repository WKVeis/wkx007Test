"use strict";
var root = "";//"http://192.168.3.61:8075"    http://192.168.3.254:8075
var api = {};

api.post = function (url, data) {
    if (typeof (data) == "object") {
        data = JSON.stringify(data);
    }
    return $.ajax({
        url: url,
        data: data,
        dataType: "json",
        method: "post",
        processData: false,
        // async:false,
        contentType: "application/json"
    });
}
//---------------------------------------------------------
//系统登录
api.login = function (data) {
    var url = root + "/v1/login";
    return api.post(url, data);
}

//注销登录
api.logout = function () {
    var url = root + "/v1/login/out?test=test";
    return api.post(url,{});
}

//-------------------账号管理--------------------------------//
//分页查询账号列表
api.findAccountByPage = function (index,userName) {
    var url = root + "/v1/account/list?test=test";
    var data = {
        pageNum:index,
        pageSize: 5,
        userName:userName
    }
    return api.post(url, data);
}
//新增管理员账户
api.addAccount = function (data) {
    var url = root + "/v1/account/add?test=test";
    return api.post(url, data);
}

//删除账户   这里后端接口必须用一个对象来接收id！！！
api.deleteAccount = function (id) {
    var url = root + "/v1/account/del?test=test";
    var data = {
        id: id
    }
    return api.post(url, data);
}
//根据id查找用户信息   这里后端接口必须用一个对象来接收id！！！
api.findByAccountId = function (id) {
    var url = root + "/v1/account/findById?test=test";
    var data = {
        id:id,
    }
    return api.post(url, data);
}
//修改账号信息
api.updateAccount = function (data) {
    var url = root + "/v1/account/update?test=test";
    return api.post(url, data);
}

//-------------------------日志------------------------
api.getlogs= function (index,userName) {
    var url = root + "/v1/sys/log/list?test=test";
    var data = {
        pageNum:index,
        pageSize: 5,
        userName: userName
    }
    return api.post(url, data);
}
//删除日志   这里后端接口必须用一个对象来接收id！！！
api.deleteLog = function (id) {
    var url = root + "/v1/sys/log/del?test=test";
    var data = {
        id: id
    }
    return api.post(url, data);
}
$.ajaxSetup({
    beforeSend: function (xhr) {
        xhr.setRequestHeader('token', sessionStorage.getItem("token"))
    }
});



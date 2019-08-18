$(function () {
    var page = function () {

        var v = new Vue({
            el: ".content-body",
            data: {
                "userName": "",
                "sex": "",
                "age": "",
                "password":"",
                "repassword":""//确认密码，后台不接收
            },
            updated : function(){
                this.$nextTick(function(){
                    $('#roleList').selectpicker(
                        'refresh'
                    );
                })
            }
        });

        var data = {
            pageNo : 0,
        }
        $(".content-body").show();
       //设置校验函数
        var notEmpty = {
            validators: {
                notEmpty: {
                    message: '请输入'
                }
            }

        }

        var identical = {
            validators: {
                identical: {
                    field: 'password',
                    message: '两次输入的密码须一致!'
                }
            }

        }

        $('.content-body').bootstrapValidator({
            message: 'This value is not valid',
            live: 'enabled',
            //submitButtons: 'button[type="button"]',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: notEmpty,
                password:notEmpty,
                repassword:notEmpty,
                repassword:identical
            }
        });

        //初始化bootstrap select 控件
        $(".btn-save").click(function () {
            //获取表单对象
            var bootstrapValidator = $(".content-body").data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                modal.loading(true);
                api.addAccount(v.$data).done(function (res) {
                    modal.loading(false);
                    if (res.result.code == "000000") {
                        modal.alert("提示","保存成功",function(){
                            load("./account/index");
                        });
                    }
                    else {
                        modal.alert("提示",res.result.msg);
                    }
                }).fail(function (res) {
                    modal.loading(false);
                    modal.alert("错误","网络超时");
                });
                return false;
            }

        });

        this.unload = function () {
            console.log("unload add");
        }
    }

    pages.push(new page());
})

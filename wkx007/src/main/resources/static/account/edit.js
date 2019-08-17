$(function () {
    var page = function () {
        var v;
        api.findByAccountId(cache.get("account-id", true)).done(function (res) {
            console.log(res);
            if (res.result.code == "000000") {
            v = new Vue({
                    el: ".content-body",
                    data: {
                        "account": res.result.data.data,
                    }
                });
                init();
            }
            else {
                modal.alert("提示",res.result.msg);
            }
        }).fail(function () {
            modal.alert("错误", "网络超时", function () {
                load("./account/index");
            });
        });


        function init() {
            $(".content-body").show();

            var validators = {
                validators: {
                    notEmpty: {
                        message: '请输入'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入正确的手机号码'
                    }
                }

            }

            var notEmpty = {

                validators: {
                    notEmpty: {
                        message: '请输入'
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
                    account: notEmpty,
                    name: notEmpty,
                    linkPhone:validators,
                    email:notEmpty,
                    password:notEmpty,
                }
            });

            $(".btn-save").click(function () {
                //获取表单对象
                var bootstrapValidator = $(".content-body").data('bootstrapValidator');
                //手动触发验证
                bootstrapValidator.validate();
                if (bootstrapValidator.isValid()) {
                    console.log("hahah");
                    console.log(v.$data);
                    modal.loading(true);
                    api.updateAccount(v.$data.account).done(function (res) {
                        modal.loading(false);
                        if (res.result.code == "000000") {
                            modal.alert("提示", "修改成功", function () {
                                load("./account/index");
                            });
                        }
                        else {
                            modal.alert("提示", res.result.msg);
                        }
                    }).fail(function (res) {
                        modal.loading(false);
                        modal.alert("错误", "网络超时");
                    });
                    return false;
                }
            });
        }

        this.unload = function () {
            console.log("unload add");
        }
    }

    pages.push(new page());
})

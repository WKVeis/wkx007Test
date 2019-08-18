$(function () {
    var page = function () {
        console.log("load role");
        var v = new Vue({
            el: ".content-body",
            data: {
                list: [],
                total: 1,
                account:"",
                userName:"",
                index: 1,
                size: 5
            },
            computed: {
                count: function () {
                    return Math.ceil(this.total / this.size);
                },
                pagination: pagination
            },
            methods: {
                edit: function (id) {
                    cache.set("account-id", id);
                    load("./account/edit");
                },
                del: function (id) {
                    modal.alert("提示","确定要删除吗?",function(){
                        modal.loading(true);
                        api.deleteAccount(id).done(function (res) {
                            if (res.result.code == "000000") {
                                modal.alert("提示", "删除成功");
                                v.find(1)
                            }
                        }).fail(function () {
                            modal.alert("错误", "网络超时");
                        }).always(function () {
                            modal.loading(false);
                        });
                    })
                },
                resetPwd:function(index,id) {
                    modal.alert("重置密码","您是确定要重置该用户的密码吗？",function() {
                        api.managerResetPassword(id).done(function (res) {
                            modal.alert("提示:",res.result.msg);
                            load("./manager/index");
                            if (res.code == "000000") {
                                console.info(v.list);
                            }
                            else {
                                console.info(res);
                            }
                        }).fail(function (e) {
                            console.error(res);
                        }).always(function(){
                            modal.loading(false);
                        });
                        $(".modal-backdrop.fade.in").hide();
                    });
                },
                find: find

            }
        });

        $(".content-body").show();
        modal.loading();

        v.find(1)
        function find(index, e) {
            e && e.preventDefault();


            modal.loading();
            v.list = [];
            api.findAccountByPage(index,v.userName).done(function (res) {
                if (res.result.code == "000000") {
                    v.list = res.result.data.data.list;
                    v.index = index;
                    v.total = res.result.data.data.total;
                }
                else {
                    console.info(res);
                    modal.alert("提示",res.result.msg)
                }
            }).fail(function (res) {
                console.error(res);
            }).always(function () {
                modal.loading(false);
            });
        }

        $(".btn-search").click(function () {
            v.find(1);
        });
        
        this.unload = function () {
            v = null;
        }

    }

    pages.push(new page());
})

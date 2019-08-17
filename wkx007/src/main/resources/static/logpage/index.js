$(function () {
    var page = function () {
        console.log("load role");
        var v = new Vue({
            el: ".content-body",
            data: {
                list: [],
                total: 1,
                userName:"",
                index: 1,
                size: 10
            },
            computed: {
                count: function () {
                    return Math.ceil(this.total / this.size);
                },
                pagination: pagination
            },
            methods: {
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
            api.getlogs(index,v.userName).done(function (res) {
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

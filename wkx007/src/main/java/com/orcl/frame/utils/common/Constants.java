package com.orcl.frame.utils.common;

import org.springframework.stereotype.Component;

/**
 * @author by weikaixiang
 * @date 2019/8/3 0003
 * @DESC:
 */
@Component
public class Constants {

    public enum Return {
        SUCCESS("000000", "操作成功"),
        ACCOUNT_ADD_ERROR("000001", "用户添加失败"),
        ACCOUNT_LIST_ERROR("000002", "用户列表查询失败"),
        ACCOUNT_FINDACCOUNT_ERROR("000003", "查询此用户信息失败"),
        ACCOUNT_UPDATE_ERROR("000004", "信息修改失败"),
        UNDEFINTY_ERROR_CODE("000015", "未定义的枚举编码"),
        MAIL_SEND_ERROR("000016","邮件发送错误"),
        LOGIN_PARAM_ISNULL("000017","参数为空"),
        LOGIN_USERNAME_ISNULL("000018","用户名为空"),
        LOGIN_PASSWORD_ISNULL("000019","密码为空"),
        LOGINUSER_INFO_ISNULL("000020","没有此用户信息"),
        LOGIN_ERROR("000021","登录失败"),
        SYSTEMLOG_LIST_ERROR("000022", "日志列表获取失败"),
        ACCOUNT_DELETE_ERROR("000023","账户信息删除失败"),
        LOGINOUT_ERROR("000024","用户退出失败"),
        ;
        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        Return(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static Return CreateReturn(String code) {
            for (Return e : Return.values()) {
                if (e.code.equals(code)) {
                    return e;
                }
            }
            return Return.UNDEFINTY_ERROR_CODE;
        }

    }
}

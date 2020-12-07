package com.orcl.frame.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC:
 */
@Data
public class LoginRequest {
    @ApiModelProperty(value = "账户", required = true, example = "魏微")
    private String userName;
    @ApiModelProperty(value = "密码", required = true, example = "!QAZ2wsx")
    private String password;
    @ApiModelProperty(value = "验证码", required = true)
    private String captcha;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}

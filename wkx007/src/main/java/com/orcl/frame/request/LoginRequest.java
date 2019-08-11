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
}

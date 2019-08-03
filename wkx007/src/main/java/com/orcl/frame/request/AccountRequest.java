package com.orcl.frame.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orcl.frame.bo.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by weikaixiang
 * @date 2019/8/3 0003
 * @DESC:
 */
@Data
public class AccountRequest extends PageParams {
    @ApiModelProperty(name = "账号",required=false,example="admin",dataType = "String")
    private String name;
    @ApiModelProperty(name= "性别",required=false,example="男",dataType = "String")
    private String sex;
    @ApiModelProperty(name = "年龄",required=false,example="20",dataType = "int")
    private Integer age;

}

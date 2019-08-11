package com.orcl.frame.request;

import com.orcl.frame.bo.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC:
 */
@Data
public class SysLogRequest extends PageParams {
    @ApiModelProperty(name = "操作用户",required=false,example="admin",dataType = "String")
    private String userName;
    @ApiModelProperty(name= "IP地址",required=false,example="127.0.0.1",dataType = "String")
    private String ip;
}

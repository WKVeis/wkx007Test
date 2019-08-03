package com.orcl.frame.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author by weikaixiang
 * @date 2019/8/3 0003
 * @DESC:分页入参类
 */
@Data
public class PageParams {
    @ApiModelProperty(value = "页", required = true, example = "1", dataType = "int")
    @NotNull
    private int pageNum=1;
    @ApiModelProperty(value = "条", required = true, example = "10", dataType = "int")
    @NotNull
    private int pageSize=10;
}



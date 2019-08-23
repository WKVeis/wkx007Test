package com.orcl.frame.request;

import com.orcl.frame.bo.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2019/8/23.
 */
@Data
public class AccountContractRoleRequest extends PageParams {
    @ApiModelProperty(value = "管理账户的id", required = true)
    private Long accountId;
    @ApiModelProperty(value = "角色的id", required = true)
    private Long roleId;
}

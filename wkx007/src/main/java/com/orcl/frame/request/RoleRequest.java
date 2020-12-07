package com.orcl.frame.request;

import com.orcl.frame.bo.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2019/8/23.
 */
@Data
public class RoleRequest extends PageParams {
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;
    @ApiModelProperty(value = "角色等级", required = true)
    private String level;
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

package com.orcl.frame.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/8/22.
 */
@Data
@TableName("ACCOUNT_ROLE")
public class AccountContactRole {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "account_id")
    private Long accountId;
    @TableField(value = "role_id")
    private Long roleId;
    @TableField(value = "create_Time")
    private Date createTime;
    @TableField(value = "update_Time")
    private Date updateTime;
}

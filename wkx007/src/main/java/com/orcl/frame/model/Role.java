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
@TableName("ROLE")
public class Role {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "role_name")
    private String roleName;
    @TableField(value = "role_level")
    private String level;
    @TableField(value = "description")
    private String description;
    @TableField(value = "create_Time")
    private Date createTime;
    @TableField(value = "update_Time")
    private Date updateTime;
}

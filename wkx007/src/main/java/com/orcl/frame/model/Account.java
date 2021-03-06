package com.orcl.frame.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by weikaixiang
 * @date 2019/8/1 0001
 * @DESC:
 */
@Data
@TableName("ACCOUNT")
public class Account implements Serializable {
    @TableId(type = IdType.AUTO)   //mybatisplus 主键自动增长
    private Long id;
    @TableField(value = "username")
    private String userName;
    @TableField(value = "password")
    private String password;
    @TableField(value = "sex")
    private String sex;
    @TableField(value = "age")
    private int age;
    @TableField(value = "Create_Time")
    private Date CreateTime;
    @TableField(value = "Update_Time")
    private Date UpdateTime;

}

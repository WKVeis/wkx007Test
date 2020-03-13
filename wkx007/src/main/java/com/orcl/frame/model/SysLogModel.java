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
 * @date 2019/8/11 0011
 * @DESC: 日志实体类
 */
@Data
@TableName("system_log")
public class SysLogModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.INPUT)
    private String id;
    //用户名
    @TableField(value = "username")
    private String userName;
    //用户操作
    @TableField(value = "operation")
    private String operation;
    //请求方法
    @TableField(value = "method")
    private String method;
    //请求参数
    @TableField(value = "params")
    private String params;
    //IP地址
    @TableField(value = "ip")
    private String ip;
    //信息描述
    @TableField(value = "description")
    private String description;
    //创建时间
    @TableField(value = "Create_Date")
    private Date createDate;

}

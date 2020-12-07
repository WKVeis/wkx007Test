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
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(name = "账号",required=false,example="admin",dataType = "String")
    private String userName;
    @ApiModelProperty(name= "性别",required=false,example="男",dataType = "String")
    private String sex;
    @ApiModelProperty(name = "年龄",required=false,example="20",dataType = "int")
    private Integer age;
    @ApiModelProperty(name = "id",required=false,dataType = "Long")
    private Long id;

}

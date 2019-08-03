package com.orcl.frame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orcl.frame.utils.JsonUtil;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by weikaixiang
 * @date 2019/8/3 0003
 * @DESC:返回对象
 */
public class Result {
    @JsonProperty("code")
    private String resCode;
    @JsonProperty("msg")
    private String resMsg;

    public Map<String, Object> getData() {
        return data;
    }

    //返回的业务数据
    private Map<String, Object> data = new HashMap();

    public Result() {
        //默认通信成功
        this(Constants.Return.SUCCESS);
    }
    public Result(Constants.Return res){
        resCode=res.getCode();
        resMsg=res.getMsg();
    }
    public Result setState(ProjectException e){
        String errMsg=e.getError().getMsg();
        if(StringUtils.isNotBlank(e.getErrorMsg())){
            errMsg=errMsg+e.getErrorMsg();
        }
        resMsg=errMsg;
        resCode=e.getError().getCode();
        return this;
    }
    public boolean verify(){
        if(resCode.equals(Constants.Return.SUCCESS.getCode())){
            return true;
        }
        return false;
    }

    public Result setState(Constants.Return ret){
        resMsg=ret.getMsg();
        resCode=ret.getCode();
        return this;
    }

    public Result setState(String code,String msg){
        resMsg=msg;
        resCode=code;
        return this;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    public String toJson(){
        return JsonUtil.writeJson(this);
    }
}

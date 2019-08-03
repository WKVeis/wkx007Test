package com.orcl.frame.vo;

import com.orcl.frame.utils.JsonUtil;

/**
 * @author by weikaixiang
 * @date 2019/8/3 0003
 * @DESC:
 */
public class Response {
    private Object result;
    private boolean isSuccess;
    public Response() {
    }
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Response(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    public String toJson(){
        return JsonUtil.writeJson(this);
    }

}

package com.orcl.frame.utils.exception;

import com.orcl.frame.utils.common.Constants;
import org.apache.commons.lang.StringUtils;

/**
 * @author by weikaixiang
 * @date 2019/8/3 0003
 * @DESC:项目的异常类
 */
public class ProjectException extends Exception {
    private Constants.Return error;
    private String errorMsg;
    public Constants.Return getError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ProjectException(Constants.Return error) {
        this.error = error;
    }

    public ProjectException(Constants.Return error, String errorMsg) {
        this.error = error;
        this.errorMsg = errorMsg;
    }
    public String getCodeAndMsg(){
        return "异常编码："+error.getCode()+"，异常信息："+error.getMsg()+(StringUtils.isNotBlank(errorMsg)?"，详细信息："+errorMsg+"。":"。");
    }

}

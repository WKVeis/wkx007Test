package com.orcl.frame.service;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.model.SysLogModel;
import com.orcl.frame.request.SysLogRequest;
import com.orcl.frame.utils.exception.ProjectException;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC:
 */
public interface SysLogServiceInterface {
    int insert(SysLogModel sysLog);

    PageInfo list(SysLogRequest request) throws ProjectException;
}

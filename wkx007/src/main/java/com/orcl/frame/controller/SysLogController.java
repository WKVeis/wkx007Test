package com.orcl.frame.controller;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.request.SysLogRequest;
import com.orcl.frame.service.SysLogServiceInterface;
import com.orcl.frame.utils.annotation.SysLog;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import com.orcl.frame.vo.Response;
import com.orcl.frame.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC:日志管理
 */
@Api(value = "SysLogController", description = "For System's log")
@RestController
@RequestMapping("/v1/sys/log")
public class SysLogController {
    @Autowired
    private SysLogServiceInterface serviceInterface;
    @PostMapping("/list")
    @ApiOperation(value = "PageList", notes = "Data pagination of system log")
    @SysLog("查看日志列表")
    public String list(@RequestBody SysLogRequest request) throws Exception{
        Response response = new Response();
        Result result = new Result();
        try {
            PageInfo data = serviceInterface.list(request);
            result.getData().put("data", data);
        } catch (ProjectException e) {
            result.setState(e.getError());
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.SYSTEMLOG_LIST_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }
}

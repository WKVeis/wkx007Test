package com.orcl.frame.controller;

import com.orcl.frame.model.AccountContactRole;
import com.orcl.frame.service.AccountContractRoleServiceInterface;
import com.orcl.frame.utils.annotation.SysLog;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import com.orcl.frame.vo.Response;
import com.orcl.frame.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019/8/24.
 */
@RestController
@RequestMapping(value = "/v1/manager")
@Api(value = "AccountContractRoleController", description = "for add account with role")
public class AccountContractRoleController {
    @Autowired
    private AccountContractRoleServiceInterface serviceInterface;

    @SysLog("添加用户角色")
    @PostMapping(value = "/add")
    @ApiOperation(value = "add", notes = "account with role")
    public String add(@RequestBody AccountContactRole account) {
        Response response = new Response();
        Result result = new Result();
        try {
            int res = serviceInterface.add(account);
            if (res == 0) {
                throw new ProjectException(Constants.Return.ACCOUNTCONTACTROLE_ADD_ERROR);
            }
        } catch (ProjectException e) {   //捕捉到刚才的异常并设置返回对象的状态
            result.setState(e.getError());
            response.setSuccess(false);
        } catch (Exception e) {//捕捉异常，返回添加失败状态，返回错误消息
            result.setState(new ProjectException(Constants.Return.ACCOUNTCONTACTROLE_ADD_ERROR, e.getMessage()));
            response.setSuccess(false);
        }
        response.setResult(result);
        return response.toJson();
    }
}

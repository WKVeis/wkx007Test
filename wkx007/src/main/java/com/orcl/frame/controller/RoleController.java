package com.orcl.frame.controller;

import com.orcl.frame.model.Role;
import com.orcl.frame.service.RoleServiceInterface;
import com.orcl.frame.utils.annotation.SysLog;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import com.orcl.frame.vo.Response;
import com.orcl.frame.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2019/8/23.
 */
@RestController
@Api(value = "RoleController", description = "about roles")
@RequestMapping(value = "/v1/role")
public class RoleController {
    @Autowired
    private RoleServiceInterface roleServiceInterface;

    @PostMapping("/add")
    @ApiOperation(value = "add", notes = "about add")
    @SysLog("添加角色")
    public String add(@RequestBody Role role) {
        Result result = new Result();
        Response response = new Response();
        try {
            int res = roleServiceInterface.add(role);
            if (res == 0) {
                throw new ProjectException(Constants.Return.ROLE_ADD_ERROR);//用户添加失败的时候抛给异常ProjectException
            }
        } catch (ProjectException e) {   //捕捉到刚才的异常并设置返回对象的状态
            result.setState(e.getError());
            response.setSuccess(false);
        } catch (Exception e) {//捕捉异常，返回添加失败状态，返回错误消息
            result.setState(new ProjectException(Constants.Return.ROLE_ADD_ERROR, e.getMessage()));
            response.setSuccess(false);
        }
        response.setResult(result);
        return response.toJson();
    }
    @SysLog("查找所有的角色")
    @GetMapping("/select")
    @ApiOperation(value = "selectAll", notes = "all roles")
    public List<String> selectAll() {
        List res = roleServiceInterface.select();
        return res;
    }

}

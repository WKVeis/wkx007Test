package com.orcl.frame.controller;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.model.Account;
import com.orcl.frame.request.AccountRequest;
import com.orcl.frame.service.AccountServiceInterface;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import com.orcl.frame.vo.Response;
import com.orcl.frame.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by weikaixiang
 * @date 2019/8/1 0001
 * @DESC:
 */
@RestController
@RequestMapping("/v1/account")
@Api(value = "Manage user", description = "For platform management")
public class AccountController {
    @Autowired
    private AccountServiceInterface accountServiceInterface;

    @PostMapping("/add")
    @ApiOperation(value = "User addition", notes = "Current class")
    public String add(@RequestBody Account account) throws Exception {
        Result result = new Result();
        Response response = new Response();
        try {
            int res = accountServiceInterface.add(account);
            if (res == 0) {
                throw new ProjectException(Constants.Return.ACCOUNT_ADD_ERROR);//用户添加失败的时候抛给异常ProjectException
            }
        } catch (ProjectException e) {   //捕捉到刚才的异常并设置返回对象的状态
            result.setState(e.getError());
        } catch (Exception e) {//捕捉异常，返回添加失败状态，返回错误消息
            result.setState(new ProjectException(Constants.Return.ACCOUNT_ADD_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }

    @PostMapping("/list")
    @ApiOperation(value = "PageList", notes = "Data pagination")
    public String list(@RequestBody AccountRequest accountRequest) throws Exception {
        Result result = new Result();
        Response response = new Response();
        try {
            PageInfo data = accountServiceInterface.list(accountRequest);
            result.getData().put("data", data);
        } catch (ProjectException e) {
            result.setState(e.getError());
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.ACCOUNT_LIST_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "Change information")
    public String update(@RequestBody Account account) throws Exception {
        Result result = new Result();
        Response response = new Response();
        try {
            int res = accountServiceInterface.update(account);
            if (res == 0) {
                throw new ProjectException(Constants.Return.ACCOUNT_UPDATE_ERROR);
            }
        } catch (ProjectException e) {
            result.setState(e.getError());
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.ACCOUNT_UPDATE_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "findById", notes = "find data by ID")
    public String findById(@PathVariable Long id) throws Exception{
        Result result = new Result();
        Response response = new Response();
        try {
            Account account = accountServiceInterface.findById(id);
            result.getData().put("data", account);
        } catch (ProjectException e) {
          result.setState(new ProjectException(e.getError()));
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.ACCOUNT_FINDACCOUNT_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }
}



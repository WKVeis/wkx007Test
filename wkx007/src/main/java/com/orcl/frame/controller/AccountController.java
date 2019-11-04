package com.orcl.frame.controller;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.model.Account;
import com.orcl.frame.request.AccountRequest;
import com.orcl.frame.service.AccountServiceInterface;
import com.orcl.frame.utils.MailUtil;
import com.orcl.frame.utils.annotation.SysLog;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import com.orcl.frame.vo.Response;
import com.orcl.frame.vo.Result;
import com.orcl.frame.vo.W;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * add account
     *
     * @param account
     * @return
     * @throws Exception
     */
    @SysLog("添加账户")
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

    /**
     * findByPage
     *
     * @param accountRequest
     * @return
     * @throws Exception
     */
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

    /**
     * update Object
     *
     * @param account
     * @return
     * @throws Exception
     */
    @SysLog("更改账户信息")
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
    @SysLog("删除账户信息")
    @PostMapping("/del")
    @ApiOperation(value = "update", notes = "Change information")
    public String delete(@RequestBody AccountRequest request) throws Exception {
        Result result = new Result();
        Response response = new Response();
        try {
            int res = accountServiceInterface.del(request.getId());
            if (res == 0) {
                throw new ProjectException(Constants.Return.ACCOUNT_DELETE_ERROR);
            }
        } catch (ProjectException e) {
            result.setState(e.getError());
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.ACCOUNT_DELETE_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }

    /**
     * find data by id
     *
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/findById")
    @ApiOperation(value = "findById", notes = "find data by ID")
    public String findById(@RequestBody AccountRequest request) throws Exception {
        Result result = new Result();
        Response response = new Response();
        try {
            Account account = accountServiceInterface.findById(request.getId());
            result.getData().put("data", account);
        } catch (ProjectException e) {
            result.setState(new ProjectException(e.getError()));
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.ACCOUNT_FINDACCOUNT_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }

    /**
     * the funtion of sendMail
     *
     * @param to
     * @param subject
     * @param code
     * @return
     * @throws Exception
     */
    @SysLog("发送邮件")
    @ApiOperation(value = "sendmail", notes = "send mail TO SomeBody")
    @GetMapping("/sendmail")
    public String sendMail(@RequestParam(value = "to", required = true, defaultValue = "1246653289@qq.com") String to,
                           @RequestParam(value = "subject", required = true, defaultValue = "邮箱验证") String subject,
                           @RequestParam(value = "code", required = true, defaultValue = "9636") String code) throws Exception {
        Response response = new Response();
        Result result = new Result();
        try {
            new MailUtil().sendMail(to, subject, code);
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.MAIL_SEND_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }
    @ApiModelProperty(value = "getMessage",notes = "getMessage for time")
    @GetMapping("getmessage")
    public W getMessage(@RequestParam String userName,@RequestParam String sex) {
        Account res = accountServiceInterface.findMessage(userName, sex);
        Map<String, Object> re = new HashMap<>();
        re.put("account", res);
        return W.ok(re);
    }
}




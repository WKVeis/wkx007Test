package com.orcl.frame.controller;

import com.orcl.frame.model.Account;
import com.orcl.frame.request.LoginRequest;
import com.orcl.frame.service.AccountServiceInterface;
import com.orcl.frame.utils.annotation.SysLog;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import com.orcl.frame.vo.Response;
import com.orcl.frame.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author by weikaixiang
 * @date 2019/8/10 0010
 * @DESC:
 */
@RestController
@RequestMapping("/v1")
@Api(value = "user login", description = "For login")
public class LoginController {
    @Autowired
    private AccountServiceInterface accountServiceInterface;
    @PostMapping("/login")
    @ApiOperation(value = "login", notes = "user login")
    public String login(@RequestBody LoginRequest request, HttpSession session) throws Exception {
        Response response = new Response();
        Result result = new Result();
        try {
            if (null == request) {//当登入信息为null时
                throw new ProjectException(Constants.Return.LOGIN_PARAM_ISNULL);
            }
            if (StringUtils.isBlank(request.getUserName())) {//当用户名为空时
                throw new ProjectException(Constants.Return.LOGIN_USERNAME_ISNULL);
            }
            if (StringUtils.isBlank(request.getPassword())) {//当密码为空时
                throw new ProjectException(Constants.Return.LOGIN_PASSWORD_ISNULL);
            }
            Account account = accountServiceInterface.findLoginAccount(request);
            if (null == account) {
                throw new ProjectException(Constants.Return.LOGINUSER_INFO_ISNULL);//数据库没有该用户的注册信息
            }
            String token = UUID.randomUUID().toString().replace("-", "") + "2019";
            result.getData().put("token", token);
            result.getData().put("loginUser", request.getUserName());
            session.setAttribute("loginUser", account.getUserName());
        } catch (ProjectException e) {
            result.setState(e.getError());
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.LOGIN_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }
}

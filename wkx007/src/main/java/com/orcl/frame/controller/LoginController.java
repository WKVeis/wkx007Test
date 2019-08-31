package com.orcl.frame.controller;

import com.google.code.kaptcha.Producer;
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

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    @Autowired
    private Producer producer;
    @GetMapping("/captcha.jpg")
    @ApiOperation(value = "verification code",notes = "get the verify code")
    @SysLog("生成验证码")
    public void captcha(HttpServletResponse response,HttpSession session)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到 session
        session.setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY,text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
    }
    @PostMapping("/login")
    @ApiOperation(value = "login", notes = "user login")
    @SysLog("用户登录")
    public String login(@RequestBody LoginRequest request, HttpSession session) throws Exception {
        Response response = new Response();
        Result result = new Result();
        try {
            String kaptcha = this.getKaptcha(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY,session);
            String code = request.getCaptcha();
            if (null == request) {//当登入信息为null时
                throw new ProjectException(Constants.Return.LOGIN_PARAM_ISNULL);
            }
            if (!code.equalsIgnoreCase(kaptcha)) {//当验证码输入不正确时
                throw new ProjectException(Constants.Return.LOGIN_KAPTCHA_ISNULL);
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
            String token = UUID.randomUUID().toString().replace("-", "") + "2019";//生成token
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

    private static String getKaptcha(String key,HttpSession session) throws Exception{
        Object kaptcha = session.getAttribute(key);//从session中得到我们想要的对象
        if(kaptcha == null){
            throw new ProjectException(Constants.Return.KAPTCHA_ISTIME);
        }
        session.removeAttribute(key);//每次登陆校验验证码的同时清除保存在session中的验证码
        return kaptcha.toString();//返回之前从seeion中得到的验证码
    }

    @PostMapping("/login/out")
    @ApiOperation(value = "login out", notes = "account loginout")
    public String logout(HttpSession session) throws Exception{
        Response response = new Response();
        Result result = new Result();
        try {
            session.removeAttribute("loginUser");
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.LOGINOUT_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }
}

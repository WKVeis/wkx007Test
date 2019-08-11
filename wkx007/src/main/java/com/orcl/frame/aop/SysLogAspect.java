package com.orcl.frame.aop;

import com.alibaba.fastjson.JSON;
import com.orcl.frame.model.SysLogModel;
import com.orcl.frame.request.LoginRequest;
import com.orcl.frame.service.SysLogServiceInterface;
import com.orcl.frame.utils.IPUtils;
import com.orcl.frame.utils.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC:系统日志生成切面，将生成的日志保存到数据库中
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogServiceInterface serviceInterface;
    //Pointcut的名称 就是simplePointcut，此方法不能有返回值，该方法只是一个标示
    //用@annotation指定我们定义的注解
    @Pointcut("@annotation(com.orcl.frame.utils.annotation.SysLog)")
    public void logPointCut() {
    }
     //JoinPoint我们所说的连接点，封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象
     @Before("logPointCut()")
    public void saveLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogModel sysLog = new SysLogModel();//日志实体类
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (null != syslog) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        sysLog.setParams(params);

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IPUtils.getIpAddr(request);
        sysLog.setIp(ip);

        //获取操作用户
//         LoginRequest o = (LoginRequest)request.getSession().getAttribute("loginUser");//从seeion中获取登录对象
        String userName ="007" ;//当前登录的用户名
        sysLog.setUserName(userName);
        sysLog.setCreateDate(new Date());
        serviceInterface.insert(sysLog);
    }

}

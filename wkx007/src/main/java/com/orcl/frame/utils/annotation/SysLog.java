package com.orcl.frame.utils.annotation;

import java.lang.annotation.*;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC: 系统日志生成注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}

package com.ny.annotation;

/**
 * @author yang
 * @version 1.0
 * @date 2021/4/12 13:59
 */

import java.lang.annotation.*;

/**
 * 注解类,通过自定义的注解传递指定时间和请求次数
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AccessLimit {
    int seconds();
    int maxCount();
}

package com.ny.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ny.annotation.AccessLimit;
import com.ny.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author yang
 * @version 1.0
 * @date 2021/4/12 14:38
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断请求是否属于方法的请求
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            //获取方法中的注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit==null){
                return true;//没有注解，就没有拦截，继续后面的执行
            }

            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();

            String ip = request.getRemoteAddr();//获得ip
            String key = request.getServletPath() + "--" + ip;
            Integer count = (Integer) redisTemplate.opsForValue().get(key);
            Boolean aBoolean = redisTemplate.hasKey(key);
            redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
            if(count==null || count==-1){
                //该ip第一次访问, 设置变量值的过期时间。
                redisTemplate.opsForValue().set(key,1);
                return true;
            }
            if(count < maxCount){
                //count ++
                count += 1;
                redisTemplate.opsForValue().set(key,count);
                return true;
            }
            if(count>= maxCount){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                Response<Object> result = new Response<>();
                result.setCode("9999");
                result.setMsg("操作过于频繁");
                //要将response对象进行网络传输与显示，必须序列化
                Object obj = JSONObject.toJSON(result);
                response.getWriter().write(JSONObject.toJSONString(obj));
                return false;
            }
        }
        return true;
    }
}

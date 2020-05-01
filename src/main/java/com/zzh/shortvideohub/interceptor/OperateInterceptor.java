package com.zzh.shortvideohub.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zzh.shortvideohub.pojo.Admin;
import com.zzh.shortvideohub.util.RedisService;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/17 9:56
 */
@Component
@Slf4j
public class OperateInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    /**
     * 处理器执行之前调用
     * @param request
     * @param response
     * @param handler
     * @return 如果返回false，就会中断处理流程，不会处理后续的拦截器和Controller。如果返回true，则会执行后续的拦截器和处理器
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //重header中取出token
        String token = request.getHeader("access_token");
        log.info("用户状态 {}", token);

        //如果有token，到redis查询，存在token，已登录，无token，false
        if(!StringUtils.isEmpty(token) && redisService.exists(token)) {
            log.info("校验成功 {}", redisService.get(token));
            return true;
        }
        response.sendError(403, "无效token");
        return false;
    }

    /**
     * 处理器执行之后调用,跳转到指定视图之前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器处理完毕");
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("请求处理结束");
    }
}

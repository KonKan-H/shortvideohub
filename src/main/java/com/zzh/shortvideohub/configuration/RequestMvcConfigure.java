package com.zzh.shortvideohub.configuration;

import com.zzh.shortvideohub.interceptor.OperateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/17 13:46
 */
@Component
public class RequestMvcConfigure implements WebMvcConfigurer {

    @Autowired
    private OperateInterceptor operateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(operateInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/v1/video/init/api")
                .excludePathPatterns("/v1/login/api")
                .excludePathPatterns("/v1/registration/api")
                .excludePathPatterns("/v1/password/api")
                .excludePathPatterns("/v1/video/api")
                .excludePathPatterns("/v1/following/video/api")
                .excludePathPatterns("/v1/admin/api");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/*.txt")
                .addResourceLocations("classpath:/");
    }
}

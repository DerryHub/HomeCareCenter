package edu.hust.config;

import edu.hust.interceptor.DangerInterceptor;
import edu.hust.interceptor.JWTInterceptor;
import edu.hust.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chain
 * @date 2020/9/3
 **/
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LogInterceptor logInterceptor=new LogInterceptor();
        JWTInterceptor jwtInterceptor=new JWTInterceptor();
        registry.addInterceptor(logInterceptor).order(1).addPathPatterns("/**");                //为日志增加traceId
//        registry.addInterceptor(jwtInterceptor).order(2).addPathPatterns("/**").excludePathPatterns("/**/login/**"); //jwt拦截
        registry.addInterceptor(new DangerInterceptor()).order(3).addPathPatterns("/**/delete/**"); //危险操作拦截
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

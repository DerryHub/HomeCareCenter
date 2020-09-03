package edu.hust.start.config;

import edu.hust.start.interceptor.LogInterceptor;
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
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");                //为日志增加traceId
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

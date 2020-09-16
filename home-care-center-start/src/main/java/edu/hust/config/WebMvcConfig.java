package edu.hust.config;

import edu.hust.interceptor.DangerInterceptor;
import edu.hust.interceptor.JWTInterceptor;
import edu.hust.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chain
 * @date 2020/9/3
 **/
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LogInterceptor logInterceptor=new LogInterceptor();

        registry.addInterceptor(logInterceptor).order(1).addPathPatterns("/**");                //为日志增加traceId
//        registry.addInterceptor(jwtInterceptor).order(2).addPathPatterns("/**").excludePathPatterns("/**/login/**")
  //              .excludePathPatterns("/**/swagger-resources/**", "/webjars/**", "/v2/**", "/**/swagger-ui.html/**","/error","/favicon.ico");
        registry.addInterceptor(new DangerInterceptor()).order(3).addPathPatterns("/**/delete/**"); //危险操作拦截
        WebMvcConfigurer.super.addInterceptors(registry);
    }
    
}

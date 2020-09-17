package edu.hust;

import edu.hust.common.exception.GlobalException;
import edu.hust.common.util.JWTUtil;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.util.TraceIdUtil;
import edu.hust.config.ThreadTaskExecutorMdcWrapper;
import edu.hust.dao.dto.Bed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Slf4j
@SpringBootApplication
public class HomeCareCenterStartApplication {


    @Value("${jwtKey:husthust}")
    private String jwtKey;


    public static void main(String[] args) {
        ApplicationContext a= SpringApplication.run(HomeCareCenterStartApplication.class, args);
        log.info("项目启动成功");
//        Arrays.stream(a.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Bean
    JWTUtil getJwtUtil(){
        JWTUtil jwtUtil=new JWTUtil();
        jwtUtil.setKey(jwtKey);
        jwtUtil.setTtl(7*24*60*60*1000);
        return jwtUtil;
    }

    @Bean("randomUUID")
    RandomUUID randomUUID(){
        return new RandomUUID();
    }

}

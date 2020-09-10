package edu.hust;

import edu.hust.common.util.JWTUtil;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.util.TraceIdUtil;
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

import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Slf4j
@SpringBootApplication
public class HomeCareCenterStartApplication {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Value("${jwtKey:husthust}")
    private String jwtKey;


    public static void main(String[] args) {
        ApplicationContext a= SpringApplication.run(HomeCareCenterStartApplication.class, args);
        log.info("项目启动成功");
    }

    @Bean
    JWTUtil getJwtUtil(){
        JWTUtil jwtUtil=new JWTUtil();
        jwtUtil.setKey(jwtKey);
        jwtUtil.setTtl(7*24*60*60);
        return jwtUtil;
    }

    @Bean("randomUUID")
    RandomUUID randomUUID(){
        return new RandomUUID();
    }


    @Bean("bed")
    Bed test(){
        System.out.println(threadPoolExecutor.getClass().getName());
        System.out.println(threadPoolExecutor.getActiveCount());
        threadPoolExecutor.execute(()->{
            log.info(":dasdasssssssssssssssssssssssssss");
        });
        return  new Bed();
    }
}

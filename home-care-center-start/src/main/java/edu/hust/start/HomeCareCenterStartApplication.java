package edu.hust.start;

import edu.hust.common.util.JWTUtil;
import edu.hust.common.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@SpringBootApplication
@MapperScan("edu.hust.dao.dao")
public class HomeCareCenterStartApplication {


    @Value("${jwtKey:husthust}")
    private String jwtKey;


    public static void main(String[] args) {
        SpringApplication.run(HomeCareCenterStartApplication.class, args);
        log.info("项目启动成功");
    }

    @Bean
    JWTUtil getJwtUtil(){
        JWTUtil jwtUtil=new JWTUtil();
        jwtUtil.setKey(jwtKey);
        jwtUtil.setTtl(7*24*60*60);
        return jwtUtil;
    }

    @Bean("mdcThreadPoolExecutor")
    ThreadPoolExecutor getThreadPoolExecutor(){
        return new TraceIdUtil().createThreadPoolExecutor();
    }


}

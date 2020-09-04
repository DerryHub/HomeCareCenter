package edu.hust.start;

import edu.hust.common.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@SpringBootApplication
@MapperScan("edu.hust.dao.dao")
public class HomeCareCenterStartApplication {



    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor=new TraceIdUtil().createThreadPoolExecutor();

        SpringApplication.run(HomeCareCenterStartApplication.class, args);
        MDC.put("traceId","testId");

        threadPoolExecutor.submit(()->{
            log.info("this is a thread 1");
        });
        threadPoolExecutor.submit(()->{
            log.info("this is a thread 2");
        });


        new Thread(()->{
            log.info("this is a thread 2");
        }).start();
        new Thread(()->{
            log.info("this is a thread 3");
        }).start();

    }


}

package edu.hust.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.hust.dao.dao")
public class HomeCareCenterStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeCareCenterStartApplication.class, args);
    }

}

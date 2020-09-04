package edu.hust.start.monitor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author chain
 * @date 2020/9/4
 **/
@Slf4j
@Component
@Aspect
public class MonitorAop {


    @Around("@annotation(monitor)")
    public Object around(Monitor monitor, ProceedingJoinPoint point){
        long start=System.currentTimeMillis();
        String value=monitor.value();
        Object result=null;
        String requestParam=null;

        try {
            Object[] args=point.getArgs();
            if (args==null||args.length==0){
                result=point.proceed();
            }else {
                requestParam= JSON.toJSONString(args);
                result=point.proceed(args);
            }
        } catch (Throwable throwable) {
            log.error("params:{}, errormsg:{}",requestParam,throwable.getMessage());
        }
         finally {
            long end=System.currentTimeMillis();
            log.info("params:{},result:{},time:{}",requestParam,JSON.toJSONString(result),end-start);
        }
        return "";
    }

}

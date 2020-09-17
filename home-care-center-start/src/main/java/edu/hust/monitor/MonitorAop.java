package edu.hust.monitor;

import com.alibaba.fastjson.JSON;
import edu.hust.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author chain
 * @date 2020/9/4
 **/
@Slf4j
@Component
@Aspect
public class MonitorAop {


    @Around(value = "@annotation(monitor)")
    public Object around(ProceedingJoinPoint point, Monitor monitor){
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

            log.error("key={}  params:{}, errormsg:{}",value,requestParam,throwable.getMessage());
            throwable.printStackTrace();
            throw  new GlobalException(throwable);
        }
         finally {

                long end=System.currentTimeMillis();
                log.info("key={} params:{},result:{},time:{}", value,requestParam,JSON.toJSONString(result),end-start);
        }
        return result;
    }

}

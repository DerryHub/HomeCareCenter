package edu.hust.config;

import edu.hust.common.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;

/**
 * @author chain
 * @date 2020/9/10
 **/
//@EnableAsync
@Slf4j
@Configuration
public class ThreadTaskExecutorMdcWrapper extends ThreadPoolTaskExecutor {


    @Bean
    public ThreadPoolTaskExecutor create(){
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("query-pool-%d")
                .uncaughtExceptionHandler(new TraceIdUtil.LogErrorExceptionHandler())
                .daemon(true).build();
        //查询使用的线程池，使用50个线程,最大100个线程，线程有效期为360秒，queue大小为200
        ThreadPoolTaskExecutor threadPoolExecutor;
        threadPoolExecutor = new ThreadTaskExecutorMdcWrapper();
        threadPoolExecutor.setThreadFactory(threadFactory);
        threadPoolExecutor.setMaxPoolSize(50);
        return threadPoolExecutor;
    }

    @Override
    public void execute(Runnable task) {
        super.execute(TraceIdUtil.wrapper(task, MDC.getCopyOfContextMap()));
    }


    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(TraceIdUtil.wrapper(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(TraceIdUtil.wrapper(task, MDC.getCopyOfContextMap()));
    }

}

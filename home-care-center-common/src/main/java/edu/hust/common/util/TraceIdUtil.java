package edu.hust.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

/**
 *
 * @author chain
 * @date 2020/9/2
 *
 * https://www.cnblogs.com/warking/p/5710303.html
 *
 **/
@Slf4j
public class TraceIdUtil {

    public static final String TRACE_ID="traceId";

    /**
     * 获取一个新的traceId
     */
    public static String getOneTraceId(){
        return UUID.randomUUID().toString();
    }

    /**
     * 在当前上下文中设置指定的traceId，如果参数中的traceId为空则会
     * 重新指定一个traceId
     */
    public static void setTraceId(String traceId){
        if (StringUtils.isBlank(traceId)){
            traceId=getOneTraceId();
        }
        MDC.put(TRACE_ID,traceId);
    }

    public static void  setTraceIdIfAbsent(){
        if (MDC.get(TRACE_ID)==null){
            MDC.put(TRACE_ID,getOneTraceId());
        }
    }

    /**
     * 在当前上下文中设置指定的traceId
     */
    public static void setTraceId(){
        MDC.put(TRACE_ID,getOneTraceId());
    }

    /**
     * 在当前上下文中获取traceId
     */
    public static String getTraceId(){
        return MDC.get(TRACE_ID);
    }

    /**
     * 在当前上下文中清除traceId
     */
    public static void clearTraceId(){
        MDC.remove(TRACE_ID);
    }


    public ThreadPoolExecutor createThreadPoolExecutor(){
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("query-pool-%d")
                .uncaughtExceptionHandler(new LogErrorExceptionHandler())
                .daemon(true).build();
        //查询使用的线程池，使用50个线程,最大100个线程，线程有效期为360秒，queue大小为200
        ThreadPoolExecutor threadPoolExecutor;
        threadPoolExecutor = new ThreadPoolTaskExecutorMdcWrapper(50, 100,
                360L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200), threadFactory,
                (r, executor) -> log.error("common pool list is full, activeCount{}, maxiMumSize{}",
                        executor.getActiveCount(), executor.getMaximumPoolSize()));
        return threadPoolExecutor;
    }


    public static <T>Callable<T> wrapper(Callable<T> callable, Map<String,String> context){
        return ()->{
            if (context==null){
                MDC.clear();
            }else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return  callable.call();
            }finally {
                clearTraceId();
            }
        };
    }

    public static Runnable wrapper(Runnable runnable, Map<String, String> context){
        return ()->{
            if (context==null){
                MDC.clear();
            }else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            }finally {
                clearTraceId();
            }
        };
    }

    private static class ThreadPoolTaskExecutorMdcWrapper extends ThreadPoolExecutor{

        public ThreadPoolTaskExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public ThreadPoolTaskExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public ThreadPoolTaskExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public ThreadPoolTaskExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }
        @Override
        public void execute(Runnable task) {
            super.execute(TraceIdUtil.wrapper(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            return super.submit(TraceIdUtil.wrapper(task, MDC.getCopyOfContextMap()), result);
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


    public static class LogErrorExceptionHandler implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.error(e.getMessage(),t);
        }
    }

}
    
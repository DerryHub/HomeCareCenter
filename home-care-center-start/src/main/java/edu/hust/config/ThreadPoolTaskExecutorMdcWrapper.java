package edu.hust.config;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import edu.hust.common.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author chain
 * @date 2020/9/10
 **/
@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolTaskExecutorMdcWrapper extends ThreadPoolExecutor {

    @Bean(name = "commonExecutorService")
    public ListeningExecutorService createListeningExecutorService() {
        ThreadPoolExecutor threadPoolExecutor = createThreadPoolExecutor();
        return MoreExecutors.listeningDecorator(threadPoolExecutor);
    }


    public ThreadPoolTaskExecutorMdcWrapper() {
        super(50,100,360L,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new BasicThreadFactory.Builder().namingPattern("query-pool-%d")
                        .uncaughtExceptionHandler(new TraceIdUtil.LogErrorExceptionHandler())
                        .daemon(true).build(),
                (r, executor) -> log.error("common pool list is full, activeCount{}, maxiMumSize{}",
                        executor.getActiveCount(), executor.getMaximumPoolSize()));
    }


    public ThreadPoolExecutor createThreadPoolExecutor(){
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("query-pool-%d")
                .uncaughtExceptionHandler(new TraceIdUtil.LogErrorExceptionHandler())
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


@Slf4j
 class LogErrorExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error(e.getMessage(),t);
    }
}
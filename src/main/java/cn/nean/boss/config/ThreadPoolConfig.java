package cn.nean.boss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("cancelOrder")
    public Executor asyncServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(5);
        // 设置最大线程数
        executor.setMaxPoolSize(10);
        // 配置队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        // 设置线程存活时间
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称前缀
        executor.setThreadNamePrefix("nean-boss-order");
        // 等待所有任务结束后在关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //执行初始化
        executor.initialize();
        return executor;
    }

    @Bean("sendEmailPool")
    public Executor EmailServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(10);
        // 设置最大线程数
        executor.setMaxPoolSize(20);
        // 配置队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        // 设置线程存活时间
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称前缀
        executor.setThreadNamePrefix("nean-boss-email");
        // 等待所有任务结束后在关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //执行初始化
        executor.initialize();
        return executor;
    }
}
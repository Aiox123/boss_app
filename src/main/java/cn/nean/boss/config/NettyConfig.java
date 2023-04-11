package cn.nean.boss.config;

import io.netty.util.HashedWheelTimer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class NettyConfig {

    @Bean
    public HashedWheelTimer hashedWheelTimer(){
        // 512个bucket的时间轮，每个时间轮的轮片时间间隔是100毫秒
        return new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 512);
    }
}

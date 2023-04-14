package cn.nean.boss.event.listener;

import cn.nean.boss.event.OpenVipEvent;
import cn.nean.boss.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/*
*  监听开通 vip 事件
* */
@Slf4j
@Component
public class OpenVipListener {

    @Autowired
    EmailUtil emailUtil;

    @EventListener(OpenVipEvent.class)
    @Async("sendEmailPool")
    public void handleMyEvent(OpenVipEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        emailUtil.sendEmail(event.getMessage());
        log.info("事件监听器 - 收到消息：{}", event.getMessage());
    }
}

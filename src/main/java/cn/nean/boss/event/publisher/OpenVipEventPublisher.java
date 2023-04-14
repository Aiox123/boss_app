package cn.nean.boss.event.publisher;

import cn.nean.boss.event.OpenVipEvent;
import cn.nean.boss.model.message.EmailMsg;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OpenVipEventPublisher {

    private ApplicationEventPublisher publisher;

    public void publish(EmailMsg message){
        OpenVipEvent event = new OpenVipEvent(this,message);
        publisher.publishEvent(event);
        log.info("事件发布成功 - 消息：{}", message);
    }
}

package cn.nean.boss.event;

import cn.nean.boss.model.message.EmailMsg;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


/*
*  开通 vip 事件
* */
@Getter
public class OpenVipEvent extends ApplicationEvent {

    private EmailMsg message;

    public OpenVipEvent(Object source, EmailMsg message) {
        super(source);
        this.message = message;
    }
}

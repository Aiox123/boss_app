package cn.nean.boss.api;

import cn.nean.boss.model.Order;
import cn.nean.boss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/vip")
public class VipApi {

    @Autowired
    OrderService orderService;

    // TODO 开通 vip 服务


    @GetMapping("/test")
    public String testOrder(){
        Order order = Order.builder()
                .uid(1L)
                .goodsId(2)
                .payMoney(188.00)
                .payType(1)
                .flag(0)
                .createTime(new Date())
                .build();
        orderService.applyOrder(order);
        return "success";
    }

}

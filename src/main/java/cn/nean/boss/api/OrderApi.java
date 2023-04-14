package cn.nean.boss.api;

import cn.nean.boss.model.dto.OrderDto;
import cn.nean.boss.model.pojo.Order;
import cn.nean.boss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    OrderService orderService;

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
    @GetMapping("/pay")
    public String payOrder(){
        OrderDto orderDto = OrderDto.builder()
                .orderId(6L)
                .payMoney(200.10)
                .payType(1)
                .flag(1)
                .build();
        orderService.payOrder(orderDto);
        return "success";
    }
}

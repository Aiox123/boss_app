package cn.nean.boss.service.impl;

import cn.nean.boss.common.RestResponse;
import cn.nean.boss.mapper.OrderMapper;
import cn.nean.boss.model.dto.OrderDto;
import cn.nean.boss.model.pojo.Order;
import cn.nean.boss.service.OrderService;
import cn.nean.boss.util.EmailUtil;
import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    HashedWheelTimer hashedWheelTimer;

    @Autowired
    EmailUtil emailUtil;

    /*
    * 提交订单
    * */
    @Override
    public RestResponse<String> applyOrder(Order order) {
        // 订单持久化数据库
        Integer isSave = orderMapper.insertOrder(order);
        Long orderId = order.getId();
        log.info("orderID: {}",orderId);
        if(isSave != 0){
            // 持久化成功，增加订单超时取消机制
            hashedWheelTimer.newTimeout(task -> {
                cancelOrder(orderId);
            },1, TimeUnit.MINUTES);
        }
        return null;
    }

    /*
    * 支付订单
    * */
    @Override
    public RestResponse<String> payOrder(OrderDto orderDto) {
        // dto -> po
        Order order = orderDtoToPo(orderDto);
        int isUpdate = orderMapper.updateOrder(order);
        if(isUpdate > 0){
            // 更新成功!
            Long userId = 2L;
            // 查询用户信息 获取用户邮箱
            // 1.发送邮件开通 Vip 成功! MQ
            emailUtil.sendEmail("","Boss Vip 服务开通","恭喜您开通 Vip 成功！");
            // 2.设置 Vip 有效期到 Redis
        }
        return null;
    }

    private Order orderDtoToPo(OrderDto orderDto){
          return Order.builder()
                .id(orderDto.getOrderId())
                .payMoney(orderDto.getPayMoney())
                .payType(orderDto.getPayType())
                .flag(orderDto.getFlag())
                .build();
    }

    /*
    *  订单超时 取消订单
    * */
    @Async
    public void cancelOrder(Long orderId){
        // 查询订单 判断是否被支付
        Order order = orderMapper.queryById(orderId);
        log.info("订单信息: {}",order);
        if(order.getFlag() == 0){
            // 30 分钟未支付 取消订单
            int isUpdate = orderMapper.updateFlagById(orderId);
            if(isUpdate == 0){
                log.error("订单ID {} 超时未支付,取消失败! ",orderId);
            }else {
                log.info("订单ID {} 超时未支付,取消成功! ",orderId);
            }
        }
    }

}

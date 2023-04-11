package cn.nean.boss.service.impl;

import cn.nean.boss.common.RestResponse;
import cn.nean.boss.mapper.OrderMapper;
import cn.nean.boss.model.Order;
import cn.nean.boss.service.OrderService;
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

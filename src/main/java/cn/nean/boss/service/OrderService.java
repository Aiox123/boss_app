package cn.nean.boss.service;

import cn.nean.boss.common.RestResponse;
import cn.nean.boss.model.dto.OrderDto;
import cn.nean.boss.model.pojo.Order;

public interface OrderService {

    RestResponse<String> applyOrder(Order order);

    RestResponse<String> payOrder(OrderDto orderDto);
}

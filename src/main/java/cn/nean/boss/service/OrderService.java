package cn.nean.boss.service;

import cn.nean.boss.common.RestResponse;
import cn.nean.boss.model.Order;

public interface OrderService {

    RestResponse<String> applyOrder(Order order);
}

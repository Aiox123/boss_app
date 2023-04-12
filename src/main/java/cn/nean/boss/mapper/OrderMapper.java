package cn.nean.boss.mapper;

import cn.nean.boss.model.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    /*
    * 新增订单
    * int ID 订单ID
    * */
    Integer insertOrder(@Param("order") Order order);

    /*
    *  根据 ID 查询订单
    * */
    Order queryById(@Param("orderId") Long orderId);

    /*
    * 更新订单状态为已取消
    * */
    int updateFlagById(@Param("orderId") Long orderId);

    /*
    * 更新订单信息
    * */
    int updateOrder(@Param("order") Order order);
}

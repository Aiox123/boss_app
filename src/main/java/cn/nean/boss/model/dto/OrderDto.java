package cn.nean.boss.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    /*
    * 订单ID
    * */
    private Long orderId;

    /*
     * 支付金额
     * */
    private Double payMoney;

    /*
     * 支付类型
     * */
    private Integer payType;

    /*
     * 订单状态
     * */
    private Integer flag;
}

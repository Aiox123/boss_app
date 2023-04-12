package cn.nean.boss.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
    * 订单ID
    * */
    private Long id;

    /*
    *  下单用户ID
    * */
    private Long uid;

    /*
     * 商品ID
     * */
    private Integer goodsId;

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

    /*
    * 创建时间
    * */
    private Date createTime;

    /*
    * 更新时间
    * */
    private Date updateTime;
}

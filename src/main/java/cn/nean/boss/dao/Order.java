package cn.nean.boss.dao;

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

    private Long id;

    private Long uid;

    private Double payMoney;

    private Integer payType;

    private Integer goodsType;

    private Integer flag;

    private Date createTime;

    private Date updateTime;
}

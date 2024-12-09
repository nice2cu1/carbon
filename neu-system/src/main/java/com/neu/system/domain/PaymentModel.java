package com.neu.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 支付宝支付实体
 * @date 2023/05/10
 */
@Data
@ApiModel(description = "支付宝支付实体")
public class PaymentModel {

    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    private Long orderId;

    /**
     * 付款金额
     */
    @ApiModelProperty("付款金额")
    private String totalAmount;

    /**
     * 支付方式： 1微信 2支付宝
     */
    @ApiModelProperty("支付方式： 1微信 2支付宝")
    private Integer paymentMethod;

}

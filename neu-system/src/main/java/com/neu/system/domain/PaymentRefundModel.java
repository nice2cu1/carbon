package com.neu.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 支付宝退款实体
 * @date 2023/05/10
 */
@Data
@ApiModel(description = "支付宝退款实体")
public class PaymentRefundModel {

    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    private Long orderId;

    /**
     * 退款金额
     */
    @ApiModelProperty("退款金额")
    private String refundAmount;

    /**
     * 退款原因
     */
    @ApiModelProperty("退款原因")
    private String refundReason;

}

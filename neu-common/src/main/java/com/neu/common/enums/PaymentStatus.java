package com.neu.common.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    /**
     * 待支付
     */
    NOT_PAY(0L, "待支付"),
    /**
     * 支付中
     */
    PAY(1L, "支付中"),
    /**
     * 已完成
     */
    SUCCESS(2L, "已完成"),
    /**
     * 支付失败
     */
    PAY_ERROR(3L, "支付失败"),

    /**
     * 已退款
     */
    REFUND(4L, "已退款"),

    /**
     * 退款审核
     */
    REFUND_AUDIT(5L, "退款审核"),
    ;

    /**
     * 编码
     */
    private final Long code;
    /**
     * 文字
     */
    public final String text;

    PaymentStatus(Long code, String text) {
        this.code = code;
        this.text = text;
    }


}

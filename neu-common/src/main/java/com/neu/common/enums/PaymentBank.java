package com.neu.common.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum PaymentBank {

    /**
     * 微信支付
     */
    WechatPay(1L, "微信支付"),
    /**
     * 支付宝支付
     */
    Alipay(2L, "支付宝支付"),
    ;

    /**
     * 银行编码
     */
    private final Long bankCode;
    /**
     * 银行名称
     */
    private final String bankName;

    PaymentBank(Long bankCode, String bankName) {
        this.bankCode = bankCode;
        this.bankName = bankName;
    }

    private final static Map<Long, String> map = new HashMap<>();

    static {
        for (PaymentBank paymentBank : values()) {
            map.put(paymentBank.getBankCode(), paymentBank.getBankName());
        }
    }

    public static Map<Long, String> getMap() {
        return map;
    }

}

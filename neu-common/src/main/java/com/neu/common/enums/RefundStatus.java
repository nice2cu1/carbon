package com.neu.common.enums;

import lombok.Getter;

@Getter
public enum RefundStatus {

    /**
     * 通过
     */
    SUCCESS(1L, "通过"),
    /**
     * 未通过
     */
    FAIL(0L, "未通过"),
    ;

    private Long code;
    private String text;

    RefundStatus(Long code, String text) {
        this.code = code;
        this.text = text;
    }

}

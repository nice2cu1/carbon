package com.neu.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 支付返回对象
 * 
 * @date 2023/05/10
 */
@ApiModel("支付返回对象")
public class PaymentResponse {

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "SUCCESS";
    public static final int ERROR_CODE = 500;

    @ApiModelProperty("返回码")
    private int code;
    @ApiModelProperty("提示消息文本")
    private String msg;
    @ApiModelProperty("账号流水号（付款、退款）")
    private String tradeNo;
    @ApiModelProperty("付款二维码")
    private String qrCode;

    public PaymentResponse success() {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
        return this;
    }

    public PaymentResponse success(String tradeNo, String qrCode) {
        this.tradeNo = tradeNo;
        this.qrCode = qrCode;
        return success();
    }

    public PaymentResponse error(String msg) {
        this.code = ERROR_CODE;
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

}

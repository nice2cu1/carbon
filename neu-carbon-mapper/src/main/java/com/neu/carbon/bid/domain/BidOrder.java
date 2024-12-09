package com.neu.carbon.bid.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息对象 bid_order
 *
 * @author neuedu
 * @date 2023-05-10
 */
@ApiModel("订单信息")
public class BidOrder extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long orderId;

    /**
     * 商品编号
     */
    @ApiModelProperty("商品编号")
    @Excel(name = "商品编号")
    private Long goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    @Excel(name = "商品名称")
    private String goodsName;

    /**
     * 企业编号
     */
    @ApiModelProperty("企业编号")
    @Excel(name = "企业编号")
    private Long enterpriseId;

    /**
     * 企业名称
     */
    @ApiModelProperty("企业名称")
    @Excel(name = "企业名称")
    private String enterpriseName;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    @Excel(name = "价格")
    private BigDecimal price;

    /**
     * 支付方式：1微信 2支付宝
     */
    @ApiModelProperty("支付方式：1微信 2支付宝")
    @Excel(name = "支付方式：1微信 2支付宝")
    private Long paymentMethod;

    /**
     * 支付状态：0已失效 1待支付 2已完成
     */
    @ApiModelProperty("支付状态：0已失效 1待支付 2已完成 ")
    @Excel(name = "支付状态：0已失效 1待支付 2已完成 ")
    private Long paymentStatus;

    /**
     * 支付时间（购买时间）
     */
    @ApiModelProperty("支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "支付时间", readConverterExp = "购=买时间")
    private Date paymentTime;

    /**
     * 期限：1年 2月
     */
    @ApiModelProperty("期限：1年 2月")
    @Excel(name = "期限：1年 2月")
    private Long endType;

    /**
     * 到期时间
     */
    @ApiModelProperty(value = "到期时间", example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty("退款金额")
    private BigDecimal refundPrice;

    @ApiModelProperty("退款原因")
    private Long refundReason;
    @ApiModelProperty("退款详细原因")
    private String refundDescription;

    public String getRefundDescription() {
        return refundDescription;
    }

    public void setRefundDescription(String refundDescription) {
        this.refundDescription = refundDescription;
    }

    public Long getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(Long refundReason) {
        this.refundReason = refundReason;
    }


    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }


    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPaymentMethod(Long paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentStatus(Long paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setEndType(Long endType) {
        this.endType = endType;
    }

    public Long getEndType() {
        return endType;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("goodsId", getGoodsId())
                .append("goodsName", getGoodsName())
                .append("enterpriseId", getEnterpriseId())
                .append("enterpriseName", getEnterpriseName())
                .append("price", getPrice())
                .append("paymentMethod", getPaymentMethod())
                .append("paymentStatus", getPaymentStatus())
                .append("paymentTime", getPaymentTime())
                .append("endType", getEndType())
                .append("endTime", getEndTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

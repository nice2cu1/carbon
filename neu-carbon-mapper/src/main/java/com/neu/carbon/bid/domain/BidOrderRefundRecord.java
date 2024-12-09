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
 * 订单退款记录对象 bid_order_refund_record
 *
 * @author neuedu
 * @date 2023-05-10
 */
@ApiModel("订单退款记录")
public class BidOrderRefundRecord extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = -4356347578930948113L;
    /** 主键 */
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private Long orderId;

    /** 网关流水 */
    @ApiModelProperty("网关流水")
    @Excel(name = "网关流水")
    private String gatewayId;

    /** 退款金额 */
    @ApiModelProperty("退款金额")
    @Excel(name = "退款金额")
    private BigDecimal refundPrice;

    /** 退款原因 */
    @ApiModelProperty("退款原因")
    @Excel(name = "退款原因")
    private Long refundReason;

    /** 详细描述退款原因 */
    @ApiModelProperty("详细描述退款原因")
    @Excel(name = "详细描述退款原因")
    private String refundDescription;

    /** 退款时间（审核时间） */
    @ApiModelProperty("退款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "退款时间", readConverterExp = "审=核时间")
    private Date refundTime;

    /** 审核人 */
    @ApiModelProperty("审核人")
    @Excel(name = "审核人")
    private Long checkUserId;

    /** 审核人名称 */
    @ApiModelProperty("审核人名称")
    @Excel(name = "审核人名称")
    private String checkUserName;

    /** 审核状态：1通过 0未通过 */
    @ApiModelProperty("审核状态：1通过 0未通过")
    @Excel(name = "审核状态：1通过 0未通过")
    private Long checkStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setGatewayId(String gatewayId)
    {
        this.gatewayId = gatewayId;
    }

    public String getGatewayId()
    {
        return gatewayId;
    }
    public void setRefundPrice(BigDecimal refundPrice)
    {
        this.refundPrice = refundPrice;
    }

    public BigDecimal getRefundPrice()
    {
        return refundPrice;
    }
    public void setRefundReason(Long refundReason)
    {
        this.refundReason = refundReason;
    }

    public Long getRefundReason()
    {
        return refundReason;
    }
    public void setRefundDescription(String refundDescription)
    {
        this.refundDescription = refundDescription;
    }

    public String getRefundDescription()
    {
        return refundDescription;
    }
    public void setRefundTime(Date refundTime)
    {
        this.refundTime = refundTime;
    }

    public Date getRefundTime()
    {
        return refundTime;
    }
    public void setCheckUserId(Long checkUserId)
    {
        this.checkUserId = checkUserId;
    }

    public Long getCheckUserId()
    {
        return checkUserId;
    }
    public void setCheckUserName(String checkUserName)
    {
        this.checkUserName = checkUserName;
    }

    public String getCheckUserName()
    {
        return checkUserName;
    }
    public void setCheckStatus(Long checkStatus)
    {
        this.checkStatus = checkStatus;
    }

    public Long getCheckStatus()
    {
        return checkStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("gatewayId", getGatewayId())
            .append("refundPrice", getRefundPrice())
            .append("refundReason", getRefundReason())
            .append("refundDescription", getRefundDescription())
            .append("refundTime", getRefundTime())
            .append("checkUserId", getCheckUserId())
            .append("checkUserName", getCheckUserName())
            .append("checkStatus", getCheckStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

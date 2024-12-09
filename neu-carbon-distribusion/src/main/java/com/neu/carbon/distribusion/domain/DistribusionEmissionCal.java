package com.neu.carbon.distribusion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.ws.BindingType;
import java.math.BigDecimal;

/**
 * 额度对象 distribusion_emission_cal
 *
 * @author neuedu
 * @date 2024-01-25
 */
@ApiModel("额度")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistribusionEmissionCal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 企业id */
    @ApiModelProperty("企业id")
    @Excel(name = "企业id")
    private Long enterpriseId;

    /** 企业id */
    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private String emissionNo;

    /** 类型 */
    @ApiModelProperty("类型（1 购买 2 申请）")
    @Excel(name = "类型")
    private String type;

    /** 金额 */
    @ApiModelProperty("金额")
    @Excel(name = "金额")
    private String money;

    /** 额度 */
    @ApiModelProperty("额度")
    @Excel(name = "额度")
    private String amount;

    /** 行业 */
    @ApiModelProperty("行业")
    @Excel(name = "行业")
    private String suitable;
    /** 行业 */
    @ApiModelProperty("行业名称")
    @Excel(name = "行业名称")
    private String suitableLabel;

    /** 状态 */
    @ApiModelProperty("状态（购买 1 待支付 2 支付成功 3 已关闭 申请 1 等待审核 2 审核通过 3 审核失败）")
    @Excel(name = "状态")
    private String status;



}

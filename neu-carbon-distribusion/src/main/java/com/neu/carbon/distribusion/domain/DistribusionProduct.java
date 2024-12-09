package com.neu.carbon.distribusion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * product对象 distribusion_product
 * 
 * @author neuedu
 * @date 2024-01-23
 */
@ApiModel("product")
public class DistribusionProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 适用行业 */
    @ApiModelProperty("适用行业")
    @Excel(name = "适用行业")
    private String suitable;

    /** 额度 */
    @ApiModelProperty("额度")
    @Excel(name = "额度")
    private String credit;

    /** 价格 */
    @ApiModelProperty("价格")
    @Excel(name = "价格")
    private String price;

    /** 详情 */
    @ApiModelProperty("详情")
    @Excel(name = "详情")
    private String detail;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSuitable(String suitable) 
    {
        this.suitable = suitable;
    }

    public String getSuitable() 
    {
        return suitable;
    }
    public void setCredit(String credit) 
    {
        this.credit = credit;
    }

    public String getCredit() 
    {
        return credit;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("suitable", getSuitable())
            .append("credit", getCredit())
            .append("price", getPrice())
            .append("detail", getDetail())
            .toString();
    }
}

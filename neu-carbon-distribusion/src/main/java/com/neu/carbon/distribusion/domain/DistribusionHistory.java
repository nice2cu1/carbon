package com.neu.carbon.distribusion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 过往数据对象 distribusion_history
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@ApiModel("过往数据")
public class DistribusionHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 企业 */
    @ApiModelProperty("企业")
    @Excel(name = "企业")
    private Long enterpriseId;

    /** 年份 */
    @ApiModelProperty("年份")
    @Excel(name = "年份")
    private String year;

    /** 排放总量 */
    @ApiModelProperty("排放总量")
    @Excel(name = "排放总量")
    private String totalEmission;

    /** 月平均排放 */
    @ApiModelProperty("月平均排放")
    @Excel(name = "月平均排放")
    private String avgMonthlyEmission;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }
    public void setYear(String year) 
    {
        this.year = year;
    }

    public String getYear() 
    {
        return year;
    }
    public void setTotalEmission(String totalEmission) 
    {
        this.totalEmission = totalEmission;
    }

    public String getTotalEmission() 
    {
        return totalEmission;
    }
    public void setAvgMonthlyEmission(String avgMonthlyEmission) 
    {
        this.avgMonthlyEmission = avgMonthlyEmission;
    }

    public String getAvgMonthlyEmission() 
    {
        return avgMonthlyEmission;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("enterpriseId", getEnterpriseId())
            .append("year", getYear())
            .append("totalEmission", getTotalEmission())
            .append("avgMonthlyEmission", getAvgMonthlyEmission())
            .toString();
    }
}

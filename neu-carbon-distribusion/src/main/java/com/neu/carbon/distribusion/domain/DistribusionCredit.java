package com.neu.carbon.distribusion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 额度对象 distribusion_credit
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@ApiModel("额度")
public class DistribusionCredit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 企业 */
    @ApiModelProperty("企业")
    @Excel(name = "企业")
    private Long enterpriseId;

    /** 获得额度 */
    @ApiModelProperty("获得额度")
    @Excel(name = "获得额度")
    private String credit;

    /** 使用的方法 */
    @ApiModelProperty("使用的方法")
    @Excel(name = "使用的方法")
    private String method;
    /** 使用的方法 */
    @ApiModelProperty("剩余额度")
    @Excel(name = "剩余额度")
    private String remainingCredit;


    public String getRemainingCredit() {
        return remainingCredit;
    }

    public void setRemainingCredit(String remainingCredit) {
        this.remainingCredit = remainingCredit;
    }

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
    public void setCredit(String credit) 
    {
        this.credit = credit;
    }

    public String getCredit() 
    {
        return credit;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("enterpriseId", getEnterpriseId())
            .append("credit", getCredit())
            .append("method", getMethod())
            .toString();
    }
}

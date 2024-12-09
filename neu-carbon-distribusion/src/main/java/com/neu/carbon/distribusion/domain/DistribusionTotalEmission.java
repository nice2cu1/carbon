package com.neu.carbon.distribusion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 配额管理对象 distribusion_total_emission
 * 
 * @author neuedu
 * @date 2024-01-24
 */
@ApiModel("配额管理")
public class DistribusionTotalEmission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 储备额度 */
    @ApiModelProperty("储备额度")
    @Excel(name = "储备额度")
    private String storageEmission;

    /** 付费额度 */
    @ApiModelProperty("付费额度")
    @Excel(name = "付费额度")
    private String payEmission;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStorageEmission(String storageEmission) 
    {
        this.storageEmission = storageEmission;
    }

    public String getStorageEmission() 
    {
        return storageEmission;
    }
    public void setPayEmission(String payEmission) 
    {
        this.payEmission = payEmission;
    }

    public String getPayEmission() 
    {
        return payEmission;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storageEmission", getStorageEmission())
            .append("payEmission", getPayEmission())
            .toString();
    }
}

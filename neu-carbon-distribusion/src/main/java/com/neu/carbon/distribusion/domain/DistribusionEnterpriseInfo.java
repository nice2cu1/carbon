package com.neu.carbon.distribusion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 企业信息对象 distribusion_enterprise_info
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@ApiModel("企业信息")
public class DistribusionEnterpriseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 行业 */
    @ApiModelProperty("行业")
    @Excel(name = "行业")
    private String industry;

    /** 企业 */
    @ApiModelProperty("企业")
    @Excel(name = "企业")
    private Long enterpriseId;

    /** 产品种类 */
    @ApiModelProperty("产品种类")
    @Excel(name = "产品种类")
    private String productCategory;

    /** 产量 */
    @ApiModelProperty("产量")
    @Excel(name = "产量")
    private String production;

    /** 公司规模 */
    @ApiModelProperty("公司规模")
    @Excel(name = "公司规模")
    private String enterpriseScale;

    /** 公司性质 */
    @ApiModelProperty("公司性质")
    @Excel(name = "公司性质")
    private String enterpriseNature;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }
    public void setProductCategory(String productCategory) 
    {
        this.productCategory = productCategory;
    }

    public String getProductCategory() 
    {
        return productCategory;
    }
    public void setProduction(String production) 
    {
        this.production = production;
    }

    public String getProduction() 
    {
        return production;
    }
    public void setEnterpriseScale(String enterpriseScale) 
    {
        this.enterpriseScale = enterpriseScale;
    }

    public String getEnterpriseScale() 
    {
        return enterpriseScale;
    }
    public void setEnterpriseNature(String enterpriseNature) 
    {
        this.enterpriseNature = enterpriseNature;
    }

    public String getEnterpriseNature() 
    {
        return enterpriseNature;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("industry", getIndustry())
            .append("enterpriseId", getEnterpriseId())
            .append("productCategory", getProductCategory())
            .append("production", getProduction())
            .append("enterpriseScale", getEnterpriseScale())
            .append("enterpriseNature", getEnterpriseNature())
            .toString();
    }
}

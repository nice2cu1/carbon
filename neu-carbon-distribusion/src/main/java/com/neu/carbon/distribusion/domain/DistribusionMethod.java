package com.neu.carbon.distribusion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * method对象 distribusion_method
 * 
 * @author neuedu
 * @date 2024-01-23
 */
@ApiModel("method")
public class DistribusionMethod extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 方法名称 */
    @ApiModelProperty("方法名称")
    @Excel(name = "方法名称")
    private String name;

    /** 比例 */
    @ApiModelProperty("比例")
    @Excel(name = "比例")
    private String ratio;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setRatio(String ratio) 
    {
        this.ratio = ratio;
    }

    public String getRatio() 
    {
        return ratio;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("ratio", getRatio())
            .toString();
    }
}

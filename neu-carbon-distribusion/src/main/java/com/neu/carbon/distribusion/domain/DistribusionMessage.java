package com.neu.carbon.distribusion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 消息中心对象 distribusion_message
 * 
 * @author neuedu
 * @date 2024-02-29
 */
@ApiModel("消息中心")
public class DistribusionMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty("主键id")
    private Long id;

    /** 是否读过（0 未读 1 已读） */
    @ApiModelProperty("是否读过")
    @Excel(name = "是否读过", readConverterExp = "0=,未=读,1=,已=读")
    private Integer status;

    /** 消息 */
    @ApiModelProperty("消息")
    @Excel(name = "消息")
    private String message;

    /** 类型（1 系统 2 订单） */
    @ApiModelProperty("类型")
    @Excel(name = "类型", readConverterExp = "1=,系=统,2=,订=单")
    private Integer type;

    @ApiModelProperty("企业id")
    @Excel(name = "企业id",readConverterExp = "企业id")
    private Long enterpriseId;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("status", getStatus())
            .append("message", getMessage())
            .append("type", getType())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}

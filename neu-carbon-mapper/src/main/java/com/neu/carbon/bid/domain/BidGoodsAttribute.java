package com.neu.carbon.bid.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel("商品权益信息")
public class BidGoodsAttribute implements Serializable {
    private static final long serialVersionUID = -3295181040101116813L;

    @ApiModelProperty("索引")
    private Integer id;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品")
    private String productName;

    /**
     * 一级分类
     */
    @ApiModelProperty("服务项目一级")
    private String categoryOne;

    /**
     * 二级分类
     */
    @ApiModelProperty("服务项目二级")
    private String categoryTwo;

    /**
     *
     */
    @ApiModelProperty("权益内容 0：无，前端展示X，1 有前端展示√ XXX|XXX|XXX前端分割多行展示，其他直接展示")
    private String productValue;

    /**
     * 权益内容扩展
     */
    @ApiModelProperty("权益内容 权益为1时 有值展示 √（produceValueExt内容）")
    private String produceValueExt;
}

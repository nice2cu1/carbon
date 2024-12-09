package com.neu.carbon.scm.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.scm.domain.*;
import com.neu.carbon.scm.service.IScmSaleContractService;
import com.neu.carbon.scm.service.IScmSaleDeliveryService;
import com.neu.carbon.scm.service.IScmSaleReturnService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.SecurityUtils;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售退货Controller
 *
 * @author neuedu
 * @date 2022-07-08
 */
@Api(tags = {"供应链SCM-销售管理-销售退货"})
@RestController
@RequestMapping("/sale/return")
public class ScmSaleReturnController extends BaseController {
    @Autowired
    private IScmSaleReturnService scmSaleReturnService;
    @Autowired
    private IScmSaleContractService scmSaleContractService;
    @Autowired
    private IScmSaleDeliveryService scmSaleDeliveryService;

    /**
     * 查询销售退货列表
     */
    @GetMapping("/list")
    @ApiOperation("查询销售退货列表")
    @DynamicResponseParameters(name = "saleReturnList",
            properties = {
                    @DynamicParameter(name = "total", value = "总记录数"),
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ScmSaleReturn.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    public TableDataInfo list(ScmSaleReturn scmSaleReturn) {
        Long userId = this.getUserId();
        if (!SecurityUtils.isAdmin(userId)) {
            scmSaleReturn.setCreateBy(String.valueOf(userId));
        }
        startPage();
        List<ScmSaleReturn> list = scmSaleReturnService.selectScmSaleReturnList(scmSaleReturn);
        return getDataTable(list);
    }

    /**
     * 导出销售退货列表
     */
    @ApiOperation("导出销售退货列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('sale:return:export')")
    @Log(title = "销售退货", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScmSaleReturn scmSaleReturn) {
        List<ScmSaleReturn> list = scmSaleReturnService.selectScmSaleReturnList(scmSaleReturn);
        ExcelUtil<ScmSaleReturn> util = new ExcelUtil<ScmSaleReturn>(ScmSaleReturn.class);
        return util.exportExcel(list, "return");
    }

    /**
     * 获取销售退货详细信息
     */
    @ApiOperation("获取销售退货详细信息")
    @DynamicResponseParameters(name = "saleReturnGet",
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ScmSaleReturn.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        ScmSaleReturn scmSaleReturn = scmSaleReturnService.selectScmSaleReturnById(id);
        List<ScmSaleReturnDetail> detailList = scmSaleReturn.getScmSaleReturnDetailList();
        return AjaxResult.success(scmSaleReturn);
    }

    /**
     * 新增销售退货
     */
    @ApiOperation("新增销售退货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('sale:return:add')")
    @Log(title = "销售退货", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScmSaleReturn scmSaleReturn) {
        return toAjax(scmSaleReturnService.insertScmSaleReturn(scmSaleReturn));
    }

    /**
     * 修改销售退货
     */
    @ApiOperation("修改销售退货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('sale:return:edit')")
    @Log(title = "销售退货", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScmSaleReturn scmSaleReturn) {
        return toAjax(scmSaleReturnService.updateScmSaleReturn(scmSaleReturn));
    }

    /**
     * 删除销售退货
     */
    @ApiOperation("删除销售退货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('sale:return:remove')")
    @Log(title = "销售退货", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(scmSaleReturnService.deleteScmSaleReturnByIds(ids));
    }

    @GetMapping("/detailList/{deliveryId}")
    @ApiOperation("根据发货单id查询发货单详情列表")
    @DynamicResponseParameters(name = "saleReturnDetailList",
            properties = {
                    @DynamicParameter(name = "total", value = "总记录数"),
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ScmSaleReturnDetail.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    public AjaxResult assembleDetailList(@PathVariable("deliveryId") Long deliveryId) {
        List<ScmSaleReturnDetail> saleReturnDetailList = new ArrayList<>();
        // 发货单信息
        ScmSaleDelivery delivery = scmSaleDeliveryService.selectScmSaleDeliveryById(deliveryId);
        List<ScmSaleDeliveryDetail> deliveryDetailList = delivery.getScmSaleDeliveryDetailList();
        // 合同信息
        ScmSaleContract contract = scmSaleContractService.selectScmSaleContractById(delivery.getContractId());
        List<ScmSaleContractDetail> contractDetailList = contract.getScmSaleContractDetailList();
        for (ScmSaleDeliveryDetail deliveryDetail : deliveryDetailList) {
            ScmSaleReturnDetail returnDetail = new ScmSaleReturnDetail();
            returnDetail.setMaterialId(deliveryDetail.getMaterialId());
            returnDetail.setDeliveryQuantity(deliveryDetail.getQuantity());
            for (ScmSaleContractDetail contractDetail : contractDetailList) {
                if (deliveryDetail.getMaterialId().equals(contractDetail.getMaterialId())) {
                    returnDetail.setBookQuantity(contractDetail.getQuantity());
                    returnDetail.setPrice(contractDetail.getPrice());
                    break;
                }
            }
            saleReturnDetailList.add(returnDetail);
        }
        return AjaxResult.success(saleReturnDetailList);
    }


}

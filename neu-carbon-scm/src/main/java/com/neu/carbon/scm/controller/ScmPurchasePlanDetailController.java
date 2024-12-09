package com.neu.carbon.scm.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.scm.domain.ScmPurchasePlanDetail;
import com.neu.carbon.scm.service.IScmPurchasePlanDetailService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采购计划详细Controller
 *
 * @author neuedu
 * @date 2022-06-28
 */
@Api(tags = {"供应链SCM-采购管理-采购计划-采购计划详细"})
@RestController
@RequestMapping("/purchase/planDetail")
public class ScmPurchasePlanDetailController extends BaseController {
    @Autowired
    private IScmPurchasePlanDetailService scmPurchasePlanDetailService;

    /**
     * 查询采购计划详细列表
     */
//    @PreAuthorize("@ss.hasPermi('purchase:planDetail:list')")
    @GetMapping("/list")
    @ApiOperation("查询采购计划详细列表")
    @DynamicResponseParameters(name = "purchasePlanDetailList",
            properties = {
                    @DynamicParameter(name = "total", value = "总记录数"),
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ScmPurchasePlanDetail.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    public TableDataInfo list(ScmPurchasePlanDetail scmPurchasePlanDetail) {
        startPage();
        List<ScmPurchasePlanDetail> list = scmPurchasePlanDetailService.selectScmPurchasePlanDetailList(scmPurchasePlanDetail);
        return getDataTable(list);
    }

    /**
     * 导出采购计划详细列表
     */
    @ApiOperation("导出采购计划详细列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('purchase:planDetail:export')")
    @Log(title = "采购计划详细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScmPurchasePlanDetail scmPurchasePlanDetail) {
        List<ScmPurchasePlanDetail> list = scmPurchasePlanDetailService.selectScmPurchasePlanDetailList(scmPurchasePlanDetail);
        ExcelUtil<ScmPurchasePlanDetail> util = new ExcelUtil<ScmPurchasePlanDetail>(ScmPurchasePlanDetail.class);
        return util.exportExcel(list, "planDetail");
    }

    /**
     * 获取采购计划详细详细信息
     */
    @ApiOperation("获取采购计划详细详细信息")
    @DynamicResponseParameters(name = "purchasePlanDetailGet",
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ScmPurchasePlanDetail.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
//    @PreAuthorize("@ss.hasPermi('purchase:planDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(scmPurchasePlanDetailService.selectScmPurchasePlanDetailById(id));
    }

    /**
     * 新增采购计划详细
     */
    @ApiOperation("新增采购计划详细")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('purchase:planDetail:add')")
    @Log(title = "采购计划详细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScmPurchasePlanDetail scmPurchasePlanDetail) {
        return toAjax(scmPurchasePlanDetailService.insertScmPurchasePlanDetail(scmPurchasePlanDetail));
    }

    /**
     * 修改采购计划详细
     */
    @ApiOperation("修改采购计划详细")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('purchase:planDetail:edit')")
    @Log(title = "采购计划详细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScmPurchasePlanDetail scmPurchasePlanDetail) {
        return toAjax(scmPurchasePlanDetailService.updateScmPurchasePlanDetail(scmPurchasePlanDetail));
    }

    /**
     * 删除采购计划详细
     */
    @ApiOperation("删除采购计划详细")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('purchase:planDetail:remove')")
    @Log(title = "采购计划详细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(scmPurchasePlanDetailService.deleteScmPurchasePlanDetailByIds(ids));
    }
}

package com.neu.carbon.scm.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.scm.domain.ScmPurchaseArrive;
import com.neu.carbon.scm.domain.ScmPurchaseArriveDetail;
import com.neu.carbon.scm.service.IScmPurchaseArriveService;
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

import java.util.List;

/**
 * 采购到货Controller
 *
 * @author neuedu
 * @date 2022-07-06
 */
@Api(tags = {"供应链SCM-采购管理-采购到货"})
@RestController
@RequestMapping("/purchase/arrive")
public class ScmPurchaseArriveController extends BaseController {
    @Autowired
    private IScmPurchaseArriveService scmPurchaseArriveService;

    /**
     * 查询采购到货列表
     */
    @GetMapping("/list")
    @ApiOperation("查询采购到货列表")
    @DynamicResponseParameters(name = "purchaseArriveList",
            properties = {
                    @DynamicParameter(name = "total", value = "总记录数"),
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ScmPurchaseArrive.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    public TableDataInfo list(ScmPurchaseArrive scmPurchaseArrive) {
        Long userId = this.getUserId();
        if (!SecurityUtils.isAdmin(userId)) {
            scmPurchaseArrive.setCreateBy(String.valueOf(userId));
        }
        startPage();
        List<ScmPurchaseArrive> list = scmPurchaseArriveService.selectScmPurchaseArriveList(scmPurchaseArrive);
        return getDataTable(list);
    }

    /**
     * 导出采购到货列表
     */
    @ApiOperation("导出采购到货列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('purchase:arrive:export')")
    @Log(title = "采购到货", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScmPurchaseArrive scmPurchaseArrive) {
        List<ScmPurchaseArrive> list = scmPurchaseArriveService.selectScmPurchaseArriveList(scmPurchaseArrive);
        ExcelUtil<ScmPurchaseArrive> util = new ExcelUtil<ScmPurchaseArrive>(ScmPurchaseArrive.class);
        return util.exportExcel(list, "arrive");
    }

    /**
     * 获取采购到货详细信息
     */
    @ApiOperation("获取采购到货详细信息")
    @DynamicResponseParameters(name = "purchaseArriveGet",
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ScmPurchaseArrive.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        ScmPurchaseArrive arrive = scmPurchaseArriveService.selectScmPurchaseArriveById(id);
        List<ScmPurchaseArriveDetail> detailList = arrive.getScmPurchaseArriveDetailList();

        return AjaxResult.success(arrive);
    }

    /**
     * 新增采购到货
     */
    @ApiOperation("新增采购到货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('purchase:arrive:add')")
    @Log(title = "采购到货", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScmPurchaseArrive scmPurchaseArrive) {
        return toAjax(scmPurchaseArriveService.insertScmPurchaseArrive(scmPurchaseArrive));
    }

    /**
     * 修改采购到货
     */
    @ApiOperation("修改采购到货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('purchase:arrive:edit')")
    @Log(title = "采购到货", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScmPurchaseArrive scmPurchaseArrive) {
        return toAjax(scmPurchaseArriveService.updateScmPurchaseArrive(scmPurchaseArrive));
    }

    /**
     * 删除采购到货
     */
    @ApiOperation("删除采购到货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('purchase:arrive:remove')")
    @Log(title = "采购到货", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(scmPurchaseArriveService.deleteScmPurchaseArriveByIds(ids));
    }
}

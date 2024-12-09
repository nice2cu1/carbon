package com.neu.carbon.bid.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.bid.domain.BidEnterpriseBidfile;
import com.neu.carbon.bid.service.IBidEnterpriseBidfileService;
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
 * 企业投标材料Controller
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"招投标-企业投标-企业投标材料"})
@RestController
@RequestMapping("/bid/bidfile")
public class BidEnterpriseBidfileController extends BaseController {
    @Autowired
    private IBidEnterpriseBidfileService bidEnterpriseBidfileService;

    /**
     * 查询企业投标材料列表
     */
    @GetMapping("/list")
    @ApiOperation("查询企业投标材料列表")
    @DynamicResponseParameters(name = "bidBidfileList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidEnterpriseBidfile.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidEnterpriseBidfile bidEnterpriseBidfile) {
        startPage();
        List<BidEnterpriseBidfile> list = bidEnterpriseBidfileService.selectBidEnterpriseBidfileList(bidEnterpriseBidfile);
        return getDataTable(list);
    }

    /**
     * 导出企业投标材料列表
     */
    @ApiOperation("导出企业投标材料列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:bidfile:export')")
    @Log(title = "企业投标材料", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidEnterpriseBidfile bidEnterpriseBidfile) {
        List<BidEnterpriseBidfile> list = bidEnterpriseBidfileService.selectBidEnterpriseBidfileList(bidEnterpriseBidfile);
        ExcelUtil<BidEnterpriseBidfile> util = new ExcelUtil<BidEnterpriseBidfile>(BidEnterpriseBidfile.class);
        return util.exportExcel(list, "bidfile");
    }

    /**
     * 获取企业投标材料详细信息
     */
    @ApiOperation("获取企业投标材料详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidEnterpriseBidfile.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(bidEnterpriseBidfileService.selectBidEnterpriseBidfileById(id));
    }

    /**
     * 新增企业投标材料
     */
    @ApiOperation("新增企业投标材料")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:bidfile:add')")
    @Log(title = "企业投标材料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidEnterpriseBidfile bidEnterpriseBidfile) {
        return toAjax(bidEnterpriseBidfileService.insertBidEnterpriseBidfile(bidEnterpriseBidfile));
    }

    /**
     * 修改企业投标材料
     */
    @ApiOperation("修改企业投标材料")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:bidfile:edit')")
    @Log(title = "企业投标材料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidEnterpriseBidfile bidEnterpriseBidfile) {
        return toAjax(bidEnterpriseBidfileService.updateBidEnterpriseBidfile(bidEnterpriseBidfile));
    }

    /**
     * 删除企业投标材料
     */
    @ApiOperation("删除企业投标材料")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:bidfile:remove')")
    @Log(title = "企业投标材料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(bidEnterpriseBidfileService.deleteBidEnterpriseBidfileByIds(ids));
    }
}

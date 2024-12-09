package com.neu.carbon.distribusion.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.enums.BusinessType;
import com.neu.carbon.distribusion.domain.DistribusionHistory;
import com.neu.carbon.distribusion.service.IDistribusionHistoryService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 过往数据Controller
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@Api(tags = {"过往数据"})
@RestController
@RequestMapping("/distribusion/history")
public class DistribusionHistoryController extends BaseController
{
    @Autowired
    private IDistribusionHistoryService distribusionHistoryService;

    /**
     * 查询过往数据列表
     */
    @GetMapping("/list")
    @ApiOperation("查询过往数据列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = DistribusionHistory.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(DistribusionHistory distribusionHistory)
    {
        startPage();
        List<DistribusionHistory> list = distribusionHistoryService.selectDistribusionHistoryList(distribusionHistory);
        return getDataTable(list);
    }

    /**
     * 导出过往数据列表
     */
    @ApiOperation("导出过往数据列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:history:export')")
    @Log(title = "过往数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DistribusionHistory distribusionHistory)
    {
        List<DistribusionHistory> list = distribusionHistoryService.selectDistribusionHistoryList(distribusionHistory);
        ExcelUtil<DistribusionHistory> util = new ExcelUtil<DistribusionHistory>(DistribusionHistory.class);
        return util.exportExcel(list, "history");
    }

    /**
     * 获取过往数据详细信息
     */
    @ApiOperation("获取过往数据详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = DistribusionHistory.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(distribusionHistoryService.selectDistribusionHistoryById(id));
    }

    /**
     * 新增过往数据
     */
    @ApiOperation("新增过往数据")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:history:add')")
    @Log(title = "过往数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistribusionHistory distribusionHistory)
    {
        return toAjax(distribusionHistoryService.insertDistribusionHistory(distribusionHistory));
    }

    /**
     * 修改过往数据
     */
    @ApiOperation("修改过往数据")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:history:edit')")
    @Log(title = "过往数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistribusionHistory distribusionHistory)
    {
        return toAjax(distribusionHistoryService.updateDistribusionHistory(distribusionHistory));
    }

    /**
     * 删除过往数据
     */
    @ApiOperation("删除过往数据")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:history:remove')")
    @Log(title = "过往数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distribusionHistoryService.deleteDistribusionHistoryByIds(ids));
    }
}

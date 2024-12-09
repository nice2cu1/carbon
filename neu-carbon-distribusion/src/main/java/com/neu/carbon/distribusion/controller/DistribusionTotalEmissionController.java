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
import com.neu.carbon.distribusion.domain.DistribusionTotalEmission;
import com.neu.carbon.distribusion.service.IDistribusionTotalEmissionService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 配额管理Controller
 * 
 * @author neuedu
 * @date 2024-01-24
 */
@Api(tags = {"配额管理"})
@RestController
@RequestMapping("/distribusion/totalEmission")
public class DistribusionTotalEmissionController extends BaseController
{
    @Autowired
    private IDistribusionTotalEmissionService distribusionTotalEmissionService;

    /**
     * 查询配额管理列表
     */
    @GetMapping("/list")
    @ApiOperation("查询配额管理列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = DistribusionTotalEmission.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(DistribusionTotalEmission distribusionTotalEmission)
    {
        startPage();
        List<DistribusionTotalEmission> list = distribusionTotalEmissionService.selectDistribusionTotalEmissionList(distribusionTotalEmission);
        return getDataTable(list);
    }

    /**
     * 导出配额管理列表
     */
    @ApiOperation("导出配额管理列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:totalEmission:export')")
    @Log(title = "配额管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DistribusionTotalEmission distribusionTotalEmission)
    {
        List<DistribusionTotalEmission> list = distribusionTotalEmissionService.selectDistribusionTotalEmissionList(distribusionTotalEmission);
        ExcelUtil<DistribusionTotalEmission> util = new ExcelUtil<DistribusionTotalEmission>(DistribusionTotalEmission.class);
        return util.exportExcel(list, "totalEmission");
    }

    /**
     * 获取配额管理详细信息
     */
    @ApiOperation("获取配额管理详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = DistribusionTotalEmission.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(distribusionTotalEmissionService.selectDistribusionTotalEmissionById(id));
    }

    /**
     * 新增配额管理
     */
    @ApiOperation("新增配额管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:totalEmission:add')")
    @Log(title = "配额管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistribusionTotalEmission distribusionTotalEmission)
    {
        return toAjax(distribusionTotalEmissionService.insertDistribusionTotalEmission(distribusionTotalEmission));
    }

    /**
     * 修改配额管理
     */
    @ApiOperation("修改配额管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:totalEmission:edit')")
    @Log(title = "配额管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistribusionTotalEmission distribusionTotalEmission)
    {
        return toAjax(distribusionTotalEmissionService.updateDistribusionTotalEmission(distribusionTotalEmission));
    }

    /**
     * 删除配额管理
     */
    @ApiOperation("删除配额管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:totalEmission:remove')")
    @Log(title = "配额管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distribusionTotalEmissionService.deleteDistribusionTotalEmissionByIds(ids));
    }
}

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
import com.neu.carbon.distribusion.domain.DistribusionMethod;
import com.neu.carbon.distribusion.service.IDistribusionMethodService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * methodController
 * 
 * @author neuedu
 * @date 2024-01-23
 */
@Api(tags = {"method"})
@RestController
@RequestMapping("/distribusion/method")
public class DistribusionMethodController extends BaseController
{
    @Autowired
    private IDistribusionMethodService distribusionMethodService;

    /**
     * 查询method列表
     */
    @GetMapping("/list")
    @ApiOperation("查询method列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = DistribusionMethod.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(DistribusionMethod distribusionMethod)
    {
        startPage();
        List<DistribusionMethod> list = distribusionMethodService.selectDistribusionMethodList(distribusionMethod);
        return getDataTable(list);
    }

    /**
     * 导出method列表
     */
    @ApiOperation("导出method列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:method:export')")
    @Log(title = "method", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DistribusionMethod distribusionMethod)
    {
        List<DistribusionMethod> list = distribusionMethodService.selectDistribusionMethodList(distribusionMethod);
        ExcelUtil<DistribusionMethod> util = new ExcelUtil<DistribusionMethod>(DistribusionMethod.class);
        return util.exportExcel(list, "method");
    }

    /**
     * 获取method详细信息
     */
    @ApiOperation("获取method详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = DistribusionMethod.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(distribusionMethodService.selectDistribusionMethodById(id));
    }

    /**
     * 新增method
     */
    @ApiOperation("新增method")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:method:add')")
    @Log(title = "method", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistribusionMethod distribusionMethod)
    {
        return toAjax(distribusionMethodService.insertDistribusionMethod(distribusionMethod));
    }

    /**
     * 修改method
     */
    @ApiOperation("修改method")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:method:edit')")
    @Log(title = "method", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistribusionMethod distribusionMethod)
    {
        return toAjax(distribusionMethodService.updateDistribusionMethod(distribusionMethod));
    }

    /**
     * 删除method
     */
    @ApiOperation("删除method")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:method:remove')")
    @Log(title = "method", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distribusionMethodService.deleteDistribusionMethodByIds(ids));
    }
}

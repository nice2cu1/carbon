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
import com.neu.carbon.distribusion.domain.DistribusionEmissionCal;
import com.neu.carbon.distribusion.service.IDistribusionEmissionCalService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 额度Controller
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@Api(tags = {"额度"})
@RestController
@RequestMapping("/distribusion/cal")
public class DistribusionEmissionCalController extends BaseController
{
    @Autowired
    private IDistribusionEmissionCalService distribusionEmissionCalService;

    /**
     * 查询额度列表
     */
    @GetMapping("/list")
    @ApiOperation("查询额度列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = DistribusionEmissionCal.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(DistribusionEmissionCal distribusionEmissionCal)
    {
        startPage();
        List<DistribusionEmissionCal> list = distribusionEmissionCalService.selectDistribusionEmissionCalList(distribusionEmissionCal);
        return getDataTable(list);
    }

    /**
     * 导出额度列表
     */
    @ApiOperation("导出额度列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:cal:export')")
    @Log(title = "额度", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DistribusionEmissionCal distribusionEmissionCal)
    {
        List<DistribusionEmissionCal> list = distribusionEmissionCalService.selectDistribusionEmissionCalList(distribusionEmissionCal);
        ExcelUtil<DistribusionEmissionCal> util = new ExcelUtil<DistribusionEmissionCal>(DistribusionEmissionCal.class);
        return util.exportExcel(list, "cal");
    }

    /**
     * 获取额度详细信息
     */
    @ApiOperation("获取额度详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = DistribusionEmissionCal.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(distribusionEmissionCalService.selectDistribusionEmissionCalById(id));
    }

    /**
     * 新增额度
     */
    @ApiOperation("新增额度")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:cal:add')")
    @Log(title = "额度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistribusionEmissionCal distribusionEmissionCal)
    {
        return toAjax(distribusionEmissionCalService.insertDistribusionEmissionCal(distribusionEmissionCal));
    }

    /**
     * 修改额度
     */
    @ApiOperation("修改额度")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:cal:edit')")
    @Log(title = "额度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistribusionEmissionCal distribusionEmissionCal)
    {
        return toAjax(distribusionEmissionCalService.updateDistribusionEmissionCal(distribusionEmissionCal));
    }

    /**
     * 删除额度
     */
    @ApiOperation("删除额度")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:cal:remove')")
    @Log(title = "额度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distribusionEmissionCalService.deleteDistribusionEmissionCalByIds(ids));
    }
}

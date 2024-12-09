package com.neu.carbon.footprint.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.footprint.domain.CfConfigEmissionFixed;
import com.neu.carbon.footprint.service.ICfConfigEmissionFixedService;
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
 * 固定碳排放Controller
 *
 * @author neuedu
 * @date 2022-07-22
 */
@Api(tags = {"智造碳足迹-碳足迹配置-固定碳排放"})
@RestController
@RequestMapping("/cfConfig/emissionFixed")
public class CfConfigEmissionFixedController extends BaseController {
    @Autowired
    private ICfConfigEmissionFixedService cfConfigEmissionFixedService;

    /**
     * 查询固定碳排放列表
     */
    @GetMapping("/list")
    @ApiOperation("查询固定碳排放列表")
    @DynamicResponseParameters(name = "cfConfigEmissionFixedList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = CfConfigEmissionFixed.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(CfConfigEmissionFixed cfConfigEmissionFixed) {
        startPage();
        List<CfConfigEmissionFixed> list = cfConfigEmissionFixedService.selectCfConfigEmissionFixedList(cfConfigEmissionFixed);
        return getDataTable(list);
    }

    /**
     * 导出固定碳排放列表
     */
    @ApiOperation("导出固定碳排放列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('cfConfig:emissionFixed:export')")
    @Log(title = "固定碳排放", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CfConfigEmissionFixed cfConfigEmissionFixed) {
        List<CfConfigEmissionFixed> list = cfConfigEmissionFixedService.selectCfConfigEmissionFixedList(cfConfigEmissionFixed);
        ExcelUtil<CfConfigEmissionFixed> util = new ExcelUtil<CfConfigEmissionFixed>(CfConfigEmissionFixed.class);
        return util.exportExcel(list, "emissionFixed");
    }

    /**
     * 获取固定碳排放详细信息
     */
    @ApiOperation("获取固定碳排放详细信息")
    @DynamicResponseParameters(name = "cfConfigEmissionFixedGet", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = CfConfigEmissionFixed.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(cfConfigEmissionFixedService.selectCfConfigEmissionFixedById(id));
    }

    /**
     * 新增固定碳排放
     */
    @ApiOperation("新增固定碳排放")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('cfConfig:emissionFixed:add')")
    @Log(title = "固定碳排放", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CfConfigEmissionFixed cfConfigEmissionFixed) {
        return toAjax(cfConfigEmissionFixedService.insertCfConfigEmissionFixed(cfConfigEmissionFixed));
    }

    /**
     * 修改固定碳排放
     */
    @ApiOperation("修改固定碳排放")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('cfConfig:emissionFixed:edit')")
    @Log(title = "固定碳排放", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CfConfigEmissionFixed cfConfigEmissionFixed) {
        return toAjax(cfConfigEmissionFixedService.updateCfConfigEmissionFixed(cfConfigEmissionFixed));
    }

    /**
     * 删除固定碳排放
     */
    @ApiOperation("删除固定碳排放")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('cfConfig:emissionFixed:remove')")
    @Log(title = "固定碳排放", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(cfConfigEmissionFixedService.deleteCfConfigEmissionFixedByIds(ids));
    }
}

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
import com.neu.carbon.distribusion.domain.DistribusionEnterpriseInfo;
import com.neu.carbon.distribusion.service.IDistribusionEnterpriseInfoService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 企业信息Controller
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@Api(tags = {"企业信息"})
@RestController
@RequestMapping("/distribusion/info")
public class DistribusionEnterpriseInfoController extends BaseController
{
    @Autowired
    private IDistribusionEnterpriseInfoService distribusionEnterpriseInfoService;

    /**
     * 查询企业信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询企业信息列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = DistribusionEnterpriseInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(DistribusionEnterpriseInfo distribusionEnterpriseInfo)
    {
        startPage();
        List<DistribusionEnterpriseInfo> list = distribusionEnterpriseInfoService.selectDistribusionEnterpriseInfoList(distribusionEnterpriseInfo);
        return getDataTable(list);
    }

    /**
     * 导出企业信息列表
     */
    @ApiOperation("导出企业信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:info:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DistribusionEnterpriseInfo distribusionEnterpriseInfo)
    {
        List<DistribusionEnterpriseInfo> list = distribusionEnterpriseInfoService.selectDistribusionEnterpriseInfoList(distribusionEnterpriseInfo);
        ExcelUtil<DistribusionEnterpriseInfo> util = new ExcelUtil<DistribusionEnterpriseInfo>(DistribusionEnterpriseInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取企业信息详细信息
     */
    @ApiOperation("获取企业信息详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = DistribusionEnterpriseInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(distribusionEnterpriseInfoService.selectDistribusionEnterpriseInfoById(id));
    }

    /**
     * 新增企业信息
     */
    @ApiOperation("新增企业信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:info:add')")
    @Log(title = "企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistribusionEnterpriseInfo distribusionEnterpriseInfo)
    {
        return toAjax(distribusionEnterpriseInfoService.insertDistribusionEnterpriseInfo(distribusionEnterpriseInfo));
    }

    /**
     * 修改企业信息
     */
    @ApiOperation("修改企业信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:info:edit')")
    @Log(title = "企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistribusionEnterpriseInfo distribusionEnterpriseInfo)
    {
        return toAjax(distribusionEnterpriseInfoService.updateDistribusionEnterpriseInfo(distribusionEnterpriseInfo));
    }

    /**
     * 删除企业信息
     */
    @ApiOperation("删除企业信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:info:remove')")
    @Log(title = "企业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distribusionEnterpriseInfoService.deleteDistribusionEnterpriseInfoByIds(ids));
    }
}

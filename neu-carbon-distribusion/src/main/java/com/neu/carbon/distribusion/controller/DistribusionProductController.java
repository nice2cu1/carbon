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
import com.neu.carbon.distribusion.domain.DistribusionProduct;
import com.neu.carbon.distribusion.service.IDistribusionProductService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * productController
 * 
 * @author neuedu
 * @date 2024-01-23
 */
@Api(tags = {"product"})
@RestController
@RequestMapping("/distribusion/product")
public class DistribusionProductController extends BaseController
{
    @Autowired
    private IDistribusionProductService distribusionProductService;

    /**
     * 查询product列表
     */
    @GetMapping("/list")
    @ApiOperation("查询product列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = DistribusionProduct.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(DistribusionProduct distribusionProduct)
    {
        startPage();
        List<DistribusionProduct> list = distribusionProductService.selectDistribusionProductList(distribusionProduct);
        return getDataTable(list);
    }

    /**
     * 导出product列表
     */
    @ApiOperation("导出product列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:product:export')")
    @Log(title = "product", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DistribusionProduct distribusionProduct)
    {
        List<DistribusionProduct> list = distribusionProductService.selectDistribusionProductList(distribusionProduct);
        ExcelUtil<DistribusionProduct> util = new ExcelUtil<DistribusionProduct>(DistribusionProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 获取product详细信息
     */
    @ApiOperation("获取product详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = DistribusionProduct.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(distribusionProductService.selectDistribusionProductById(id));
    }

    /**
     * 新增product
     */
    @ApiOperation("新增product")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:product:add')")
    @Log(title = "product", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistribusionProduct distribusionProduct)
    {
        return toAjax(distribusionProductService.insertDistribusionProduct(distribusionProduct));
    }

    /**
     * 修改product
     */
    @ApiOperation("修改product")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:product:edit')")
    @Log(title = "product", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistribusionProduct distribusionProduct)
    {
        return toAjax(distribusionProductService.updateDistribusionProduct(distribusionProduct));
    }

    /**
     * 删除product
     */
    @ApiOperation("删除product")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:product:remove')")
    @Log(title = "product", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distribusionProductService.deleteDistribusionProductByIds(ids));
    }
}

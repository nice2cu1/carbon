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
import com.neu.carbon.distribusion.domain.DistribusionMessage;
import com.neu.carbon.distribusion.service.IDistribusionMessageService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 消息中心Controller
 * 
 * @author neuedu
 * @date 2024-02-29
 */
@Api(tags = {"消息中心"})
@RestController
@RequestMapping("/distribusion/message")
public class DistribusionMessageController extends BaseController
{
    @Autowired
    private IDistribusionMessageService distribusionMessageService;

    /**
     * 查询消息中心列表
     */
    @GetMapping("/list")
    @ApiOperation("查询消息中心列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = DistribusionMessage.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(DistribusionMessage distribusionMessage)
    {
        startPage();
        List<DistribusionMessage> list = distribusionMessageService.selectDistribusionMessageList(distribusionMessage);
        return getDataTable(list);
    }

    /**
     * 导出消息中心列表
     */
    @ApiOperation("导出消息中心列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:message:export')")
    @Log(title = "消息中心", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DistribusionMessage distribusionMessage)
    {
        List<DistribusionMessage> list = distribusionMessageService.selectDistribusionMessageList(distribusionMessage);
        ExcelUtil<DistribusionMessage> util = new ExcelUtil<DistribusionMessage>(DistribusionMessage.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 获取消息中心详细信息
     */
    @ApiOperation("获取消息中心详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = DistribusionMessage.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(distribusionMessageService.selectDistribusionMessageById(id));
    }

    /**
     * 新增消息中心
     */
    @ApiOperation("新增消息中心")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:message:add')")
    @Log(title = "消息中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistribusionMessage distribusionMessage)
    {
        return toAjax(distribusionMessageService.insertDistribusionMessage(distribusionMessage));
    }

    /**
     * 修改消息中心
     */
    @ApiOperation("修改消息中心")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:message:edit')")
    @Log(title = "消息中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistribusionMessage distribusionMessage)
    {
        return toAjax(distribusionMessageService.updateDistribusionMessage(distribusionMessage));
    }

    /**
     * 删除消息中心
     */
    @ApiOperation("删除消息中心")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('distribusion:message:remove')")
    @Log(title = "消息中心", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distribusionMessageService.deleteDistribusionMessageByIds(ids));
    }
}

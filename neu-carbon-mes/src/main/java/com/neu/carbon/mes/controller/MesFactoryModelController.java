package com.neu.carbon.mes.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.mes.domain.MesFactoryModel;
import com.neu.carbon.mes.service.IMesFactoryModelService;
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
 * 工厂建模Controller
 *
 * @author neuedu
 * @date 2022-07-07
 */
@Api(tags = {"制造执行MES-工厂建模"})
@RestController
@RequestMapping("/mesModel/factoryModel")
public class MesFactoryModelController extends BaseController {
    @Autowired
    private IMesFactoryModelService mesFactoryModelService;

    /**
     * 查询工厂建模列表
     */
    @GetMapping("/list")
    @ApiOperation("查询工厂建模列表")
    @DynamicResponseParameters(name = "mesModelFactoryModelList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = MesFactoryModel.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(MesFactoryModel mesFactoryModel) {
        startPage();
        List<MesFactoryModel> list = mesFactoryModelService.selectMesFactoryModelList(mesFactoryModel);
        return getDataTable(list);
    }

    /**
     * 导出工厂建模列表
     */
    @ApiOperation("导出工厂建模列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('mesModel:factoryModel:export')")
    @Log(title = "工厂建模", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MesFactoryModel mesFactoryModel) {
        List<MesFactoryModel> list = mesFactoryModelService.selectMesFactoryModelList(mesFactoryModel);
        ExcelUtil<MesFactoryModel> util = new ExcelUtil<MesFactoryModel>(MesFactoryModel.class);
        return util.exportExcel(list, "factoryModel");
    }

    /**
     * 获取工厂建模详细信息
     */
    @ApiOperation("获取工厂建模详细信息")
    @DynamicResponseParameters(name = "mesModelFactoryModelGet", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = MesFactoryModel.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        MesFactoryModel model = mesFactoryModelService.selectMesFactoryModelById(id);
        return AjaxResult.success(model);
    }

    /**
     * 新增工厂建模
     */
    @ApiOperation("新增工厂建模")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesModel:factoryModel:add')")
    @Log(title = "工厂建模", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesFactoryModel mesFactoryModel) {
        return toAjax(mesFactoryModelService.insertMesFactoryModel(mesFactoryModel));
    }

    /**
     * 修改工厂建模
     */
    @ApiOperation("修改工厂建模")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesModel:factoryModel:edit')")
    @Log(title = "工厂建模", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesFactoryModel mesFactoryModel) {
        return toAjax(mesFactoryModelService.updateMesFactoryModel(mesFactoryModel));
    }

    /**
     * 删除工厂建模
     */
    @ApiOperation("删除工厂建模")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesModel:factoryModel:remove')")
    @Log(title = "工厂建模", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(mesFactoryModelService.deleteMesFactoryModelByIds(ids));
    }
}

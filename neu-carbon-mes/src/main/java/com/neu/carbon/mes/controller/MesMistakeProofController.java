package com.neu.carbon.mes.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.mes.domain.MesMistakeProof;
import com.neu.carbon.mes.service.IMesMistakeProofService;
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
 * 防错管理Controller
 *
 * @author neuedu
 * @date 2022-07-19
 */
@Api(tags = {"制造执行MES-防错管理"})
@RestController
@RequestMapping("/mesCheck/mistakeProof")
public class MesMistakeProofController extends BaseController {
    @Autowired
    private IMesMistakeProofService mesMistakeProofService;

    /**
     * 查询防错管理列表
     */
    @GetMapping("/list")
    @ApiOperation("查询防错管理列表")
    @DynamicResponseParameters(name = "mesCheckMistakeProofList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = MesMistakeProof.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(MesMistakeProof mesMistakeProof) {
        startPage();
        List<MesMistakeProof> list = mesMistakeProofService.selectMesMistakeProofList(mesMistakeProof);
        return getDataTable(list);
    }

    /**
     * 导出防错管理列表
     */
    @ApiOperation("导出防错管理列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('mesCheck:mistakeProof:export')")
    @Log(title = "防错管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MesMistakeProof mesMistakeProof) {
        List<MesMistakeProof> list = mesMistakeProofService.selectMesMistakeProofList(mesMistakeProof);
        ExcelUtil<MesMistakeProof> util = new ExcelUtil<MesMistakeProof>(MesMistakeProof.class);
        return util.exportExcel(list, "mistakeProof");
    }

    /**
     * 获取防错管理详细信息
     */
    @ApiOperation("获取防错管理详细信息")
    @DynamicResponseParameters(name = "mesCheckMistakeProofGet", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = MesMistakeProof.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(mesMistakeProofService.selectMesMistakeProofById(id));
    }

    /**
     * 新增防错管理
     */
    @ApiOperation("新增防错管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesCheck:mistakeProof:add')")
    @Log(title = "防错管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesMistakeProof mesMistakeProof) {
        return toAjax(mesMistakeProofService.insertMesMistakeProof(mesMistakeProof));
    }

    /**
     * 修改防错管理
     */
    @ApiOperation("修改防错管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesCheck:mistakeProof:edit')")
    @Log(title = "防错管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesMistakeProof mesMistakeProof) {
        return toAjax(mesMistakeProofService.updateMesMistakeProof(mesMistakeProof));
    }

    /**
     * 删除防错管理
     */
    @ApiOperation("删除防错管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesCheck:mistakeProof:remove')")
    @Log(title = "防错管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(mesMistakeProofService.deleteMesMistakeProofByIds(ids));
    }
}

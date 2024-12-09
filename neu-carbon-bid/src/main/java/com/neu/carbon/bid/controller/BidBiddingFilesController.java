package com.neu.carbon.bid.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.bid.domain.BidBiddingFiles;
import com.neu.carbon.bid.service.IBidBiddingFilesService;
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
 * 招标信息附件Controller
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"招投标-招投标-招标信息附件"})
@RestController
@RequestMapping("/bid/files")
public class BidBiddingFilesController extends BaseController {
    @Autowired
    private IBidBiddingFilesService bidBiddingFilesService;

    /**
     * 查询招标信息附件列表
     */
    @GetMapping("/list")
    @ApiOperation("查询招标信息附件列表")
    @DynamicResponseParameters(name = "/bid/files/list", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidBiddingFiles.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidBiddingFiles bidBiddingFiles) {
        startPage();
        List<BidBiddingFiles> list = bidBiddingFilesService.selectBidBiddingFilesList(bidBiddingFiles);
        return getDataTable(list);
    }

    /**
     * 导出招标信息附件列表
     */
    @ApiOperation("导出招标信息附件列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:export')")
    @Log(title = "招标信息附件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidBiddingFiles bidBiddingFiles) {
        List<BidBiddingFiles> list = bidBiddingFilesService.selectBidBiddingFilesList(bidBiddingFiles);
        ExcelUtil<BidBiddingFiles> util = new ExcelUtil<BidBiddingFiles>(BidBiddingFiles.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 获取招标信息附件详细信息
     */
    @ApiOperation("获取招标信息附件详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidBiddingFiles.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(bidBiddingFilesService.selectBidBiddingFilesById(id));
    }

    /**
     * 新增招标信息附件
     */
    @ApiOperation("新增招标信息附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:add')")
    @Log(title = "招标信息附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidBiddingFiles bidBiddingFiles) {
        return toAjax(bidBiddingFilesService.insertBidBiddingFiles(bidBiddingFiles));
    }

    /**
     * 修改招标信息附件
     */
    @ApiOperation("修改招标信息附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:edit')")
    @Log(title = "招标信息附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidBiddingFiles bidBiddingFiles) {
        return toAjax(bidBiddingFilesService.updateBidBiddingFiles(bidBiddingFiles));
    }

    /**
     * 删除招标信息附件
     */
    @ApiOperation("删除招标信息附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:remove')")
    @Log(title = "招标信息附件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(bidBiddingFilesService.deleteBidBiddingFilesByIds(ids));
    }
}

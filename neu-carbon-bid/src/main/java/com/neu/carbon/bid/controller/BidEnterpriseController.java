package com.neu.carbon.bid.controller;

import cn.hutool.core.lang.Assert;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.bid.domain.BidEnterprise;
import com.neu.carbon.bid.service.IBidEnterpriseService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.domain.entity.SysUser;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.ip.AddressUtils;
import com.neu.common.utils.ip.IpUtils;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 投标企业信息Controller
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"招投标-投标企业信息"})
@RestController
@RequestMapping("/bid/enterprise")
public class BidEnterpriseController extends BaseController {
    @Autowired
    private IBidEnterpriseService bidEnterpriseService;

    /**
     * 查询投标企业信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询投标企业信息列表")
    @DynamicResponseParameters(name = "bidEnterpriseList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidEnterprise.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidEnterprise bidEnterprise) {
        startPage();
        List<BidEnterprise> list = bidEnterpriseService.selectBidEnterpriseList(bidEnterprise);
        return getDataTable(list);
    }

    @GetMapping("/front/list")
    @ApiOperation("查询投标企业信息列表")
    @DynamicResponseParameters(name = "bidEnterpriseFrontList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidEnterprise.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo frontList(BidEnterprise bidEnterprise) {
        startPage();
        List<BidEnterprise> list = bidEnterpriseService.selectBidEnterpriseList(bidEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出投标企业信息列表
     */
    @ApiOperation("导出投标企业信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @Log(title = "投标企业信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidEnterprise bidEnterprise) {
        List<BidEnterprise> list = bidEnterpriseService.selectBidEnterpriseList(bidEnterprise);
        ExcelUtil<BidEnterprise> util = new ExcelUtil<BidEnterprise>(BidEnterprise.class);
        return util.exportExcel(list, "enterprise");
    }

    /**
     * 获取投标企业信息详细信息
     */
    @ApiOperation("获取投标企业信息详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidEnterprise.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id, HttpServletRequest request) {
        String ip = IpUtils.getIpAddr(request);
        String address = AddressUtils.getRealAddressByIP(ip);
        BidEnterprise bidEnterprise = bidEnterpriseService.selectBidEnterpriseById(id);
        if (bidEnterprise != null) bidEnterprise.setIpAddress(address);
        return AjaxResult.success(bidEnterprise);
    }

    @ApiOperation("获取投标企业信息详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidEnterprise.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/front")
    public AjaxResult frontGetInfo(HttpServletRequest request) {
        String ip = IpUtils.getIpAddr(request);
        String address = AddressUtils.getRealAddressByIP(ip);
        SysUser user = getUser();
        BidEnterprise bidEnterprise = bidEnterpriseService.getBidEnterpriseByUserName(user.getUserName());
        if (bidEnterprise != null) bidEnterprise.setIpAddress(address);
        return AjaxResult.success(bidEnterprise);
    }

    /**
     * 新增投标企业信息
     */
    @ApiOperation("新增投标企业信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @Log(title = "投标企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidEnterprise bidEnterprise) {
        //校验企业是否存在

        Assert.isNull(bidEnterpriseService.getBidEnterpriseByUserName(getUser().getUserName()),"已存在相同企业");
        return toAjax(bidEnterpriseService.insertBidEnterprise(bidEnterprise));
    }

    @ApiOperation("新增投标企业信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping(value = "/front/add")
    public AjaxResult frontAdd(@RequestBody BidEnterprise bidEnterprise) {
        return toAjax(bidEnterpriseService.insertBidEnterprise(bidEnterprise));
    }

    /**
     * 修改投标企业信息
     */
    @ApiOperation("修改投标企业信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @Log(title = "投标企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidEnterprise bidEnterprise) {
        return toAjax(bidEnterpriseService.updateBidEnterprise(bidEnterprise));
    }

    @ApiOperation("修改投标企业信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping(value = "/front/edit")
    public AjaxResult frontEdit(@RequestBody BidEnterprise bidEnterprise) {
        return toAjax(bidEnterpriseService.updateBidEnterprise(bidEnterprise));
    }

    /**
     * 删除投标企业信息
     */
    @ApiOperation("删除投标企业信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @Log(title = "投标企业信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(bidEnterpriseService.deleteBidEnterpriseByIds(ids));
    }
}

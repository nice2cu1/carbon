package com.neu.carbon.report.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.scm.domain.VScmFinanceReport;
import com.neu.carbon.scm.domain.VScmSaleContract;
import com.neu.carbon.scm.service.IVScmSaleContractService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 销售合同报表Controller
 *
 * @author neuedu
 * @date 2022-07-28
 */
@Api(tags = {"供应链SCM-财务管理-收支统计"})
@RestController
@RequestMapping("/saleReport/saleContractReport")
public class VScmSaleContractController extends BaseController {
    @Autowired
    private IVScmSaleContractService vScmSaleContractService;

    /**
     * 查询销售合同报表列表
     */
    @GetMapping("/byMonth")
    @ApiOperation("供应链SCM-财务管理-客户对账-月份统计")
    @DynamicResponseParameters(name = "saleReportSaleContractReportByMonth", properties = {@DynamicParameter(name = "total", value = "总记录数"), @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"), @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VScmSaleContract.class), @DynamicParameter(name = "msg", value = "返回消息内容")})
    public TableDataInfo listByMonth(VScmSaleContract vScmSaleContract) {
        startPage();
        List<VScmSaleContract> list = vScmSaleContractService.selectMonthSaleContractReport(vScmSaleContract);
        return getDataTable(list);
    }


    /**
     * 查询销售合同报表列表
     */
    @GetMapping("/byQuarter")
    @ApiOperation("供应链SCM-财务管理-客户对账-季度统计")
    @DynamicResponseParameters(name = "saleReportSaleContractReportByQuarter", properties = {@DynamicParameter(name = "total", value = "总记录数"), @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"), @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VScmSaleContract.class), @DynamicParameter(name = "msg", value = "返回消息内容")})
    public TableDataInfo listByQuarter(VScmSaleContract vScmSaleContract) {
        startPage();
        List<VScmSaleContract> list = vScmSaleContractService.selectQuarterSaleContractReport(vScmSaleContract);
        return getDataTable(list);
    }


    /**
     * 导出销售合同报表列表
     */
    @ApiOperation("导出销售合同报表列表")
    @DynamicResponseParameters(properties = {@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"), @DynamicParameter(name = "msg", value = "文件名")})
    @PreAuthorize("@ss.hasPermi('saleReport:saleContractReport:export')")
    @Log(title = "销售合同月报表", businessType = BusinessType.EXPORT)
    @GetMapping("/export/byMonth")
    public AjaxResult exportByMonth(VScmSaleContract vScmSaleContract) {
        List<VScmSaleContract> list = vScmSaleContractService.selectMonthSaleContractReport(vScmSaleContract);
        ExcelUtil<VScmSaleContract> util = new ExcelUtil<VScmSaleContract>(VScmSaleContract.class);
        return util.exportExcel(list, "saleContractReportByMonth");
    }

    /**
     * 导出销售合同报表列表
     */
    @ApiOperation("导出销售合同报表列表")
    @DynamicResponseParameters(properties = {@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"), @DynamicParameter(name = "msg", value = "文件名")})
    @Log(title = "销售合同季度报表", businessType = BusinessType.EXPORT)
    @GetMapping("/export/byQuarter")
    public AjaxResult exportByQuarter(VScmSaleContract vScmSaleContract) {
        List<VScmSaleContract> list = vScmSaleContractService.selectQuarterSaleContractReport(vScmSaleContract);
        ExcelUtil<VScmSaleContract> util = new ExcelUtil<VScmSaleContract>(VScmSaleContract.class);
        return util.exportExcel(list, "saleContractReportByQuarter");
    }


    @GetMapping("/orderStat/byMonth")
    @ApiOperation("供应链SCM-统计分析-订单收款统计-月份统计")
    @DynamicResponseParameters(name = "saleReportSaleContractReportOrderStatByMonth", properties = {@DynamicParameter(name = "total", value = "总记录数"), @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"), @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VScmSaleContract.class), @DynamicParameter(name = "msg", value = "返回消息内容")})
    public TableDataInfo orderStatByMonth(VScmSaleContract vScmSaleContract) {
        startPage();
        List<VScmSaleContract> list = vScmSaleContractService.selectMonthOrderStatReport(vScmSaleContract);
        return getDataTable(list);
    }

    @GetMapping("/orderStat/byQuarter")
    @ApiOperation("供应链SCM-统计分析-订单收款统计-季度统计")
    @DynamicResponseParameters(name = "saleReportSaleContractReportOrderStatByQuarter", properties = {@DynamicParameter(name = "total", value = "总记录数"), @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"), @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VScmSaleContract.class), @DynamicParameter(name = "msg", value = "返回消息内容")})
    public TableDataInfo orderStatByQuarter(VScmSaleContract vScmSaleContract) {
        startPage();
        List<VScmSaleContract> list = vScmSaleContractService.selectQuarterOrderStatReport(vScmSaleContract);
        return getDataTable(list);
    }


    @GetMapping("/financeReport/byMonth")
    @ApiOperation("财务收支月份统计")
    @DynamicResponseParameters(name = "saleReportSaleContractReportFinanceReportByMonth",
            properties = {
                    @DynamicParameter(name = "total", value = "总记录数"),
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VScmFinanceReport.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    public TableDataInfo financeByMonth(VScmFinanceReport vScmFinanceReport) {
        startPage();
        List<VScmFinanceReport> list = vScmSaleContractService.selectMonthFinanceReport(vScmFinanceReport);
        return getDataTable(list);
    }


    @GetMapping("/financeReport/byQuarter")
    @ApiOperation("财务收支季度统计")
    @DynamicResponseParameters(name = "saleReportSaleContractReportFinanceReportByQuarter", properties = {@DynamicParameter(name = "total", value = "总记录数"), @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"), @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VScmFinanceReport.class), @DynamicParameter(name = "msg", value = "返回消息内容")})
    public TableDataInfo financeByQuarter(VScmFinanceReport vScmFinanceReport) {
        startPage();
        List<VScmFinanceReport> list = vScmSaleContractService.selectQuarterFinanceReport(vScmFinanceReport);
        return getDataTable(list);
    }
}

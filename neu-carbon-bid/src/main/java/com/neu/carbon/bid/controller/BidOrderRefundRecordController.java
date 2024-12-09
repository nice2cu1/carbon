package com.neu.carbon.bid.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.bid.domain.BidOrder;
import com.neu.carbon.bid.domain.BidOrderRefundRecord;
import com.neu.carbon.bid.service.IBidOrderRefundRecordService;
import com.neu.carbon.bid.service.IBidOrderService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.enums.PaymentStatus;
import com.neu.common.enums.RefundStatus;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单退款记录Controller
 *
 * @author neuedu
 * @date 2023-05-10
 */
@Api(tags = {"招投标（ 采购管理）-订单退款记录"})
@RestController
@RequestMapping("/bid/refund/record")
public class BidOrderRefundRecordController extends BaseController {
    private final IBidOrderRefundRecordService bidOrderRefundRecordService;
    private final IBidOrderService bidOrderService;

    @Autowired
    public BidOrderRefundRecordController(IBidOrderRefundRecordService bidOrderRefundRecordService
            , IBidOrderService bidOrderService
            ) {
        this.bidOrderRefundRecordService = bidOrderRefundRecordService;
        this.bidOrderService = bidOrderService;
    }

    /**
     * 查询订单退款记录列表
     */
    @GetMapping("/list")
    @ApiOperation("查询订单退款记录列表")
    @DynamicResponseParameters(name = "bidRefundRecordList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidOrderRefundRecord.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidOrderRefundRecord bidOrderRefundRecord) {
        startPage();
        List<BidOrderRefundRecord> list = bidOrderRefundRecordService.selectBidOrderRefundRecordList(bidOrderRefundRecord);
        return getDataTable(list);
    }

    /**
     * 导出订单退款记录列表
     */
    @ApiOperation("导出订单退款记录列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:record:export')")
    @Log(title = "订单退款记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidOrderRefundRecord bidOrderRefundRecord) {
        List<BidOrderRefundRecord> list = bidOrderRefundRecordService.selectBidOrderRefundRecordList(bidOrderRefundRecord);
        ExcelUtil<BidOrderRefundRecord> util = new ExcelUtil<BidOrderRefundRecord>(BidOrderRefundRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取订单退款记录详细信息
     */
    @ApiOperation("获取订单退款记录详细信息")
    @DynamicResponseParameters(name = "bidRefundRecordGet", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidOrderRefundRecord.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(bidOrderRefundRecordService.selectBidOrderRefundRecordById(id));
    }

    /**
     * 新增订单退款记录
     */
    @ApiOperation("新增订单退款记录")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:record:add')")
    @Log(title = "订单退款记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidOrderRefundRecord bidOrderRefundRecord) {
        return toAjax(bidOrderRefundRecordService.insertBidOrderRefundRecord(bidOrderRefundRecord));
    }

    /**
     * 修改订单退款记录
     */
    @ApiOperation("修改订单退款记录")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:record:edit')")
    @Log(title = "订单退款记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidOrderRefundRecord bidOrderRefundRecord) {

        if (bidOrderRefundRecord.getOrderId() == null) {
            return AjaxResult.error("退款单数据错误");
        }

        BidOrderRefundRecord refundRecord = this.bidOrderRefundRecordService
                .selectBidOrderRefundRecordById(bidOrderRefundRecord.getId());

        if (refundRecord == null) {
            return AjaxResult.error("退款单数据错误");
        }
        if (!refundRecord.getOrderId().equals(bidOrderRefundRecord.getOrderId())) {
            return AjaxResult.error("退款单数据错误");
        }
        BidOrder order = this.bidOrderService.selectBidOrderById(bidOrderRefundRecord.getOrderId());

        if (order == null) {
            return AjaxResult.error("退款单数据错误");
        }



        if (RefundStatus.SUCCESS.getCode().equals(bidOrderRefundRecord.getCheckStatus())) {
            order.setPaymentStatus(PaymentStatus.REFUND.getCode());
            this.bidOrderService.updateBidOrder(order);
        }

        return toAjax(bidOrderRefundRecordService.updateBidOrderRefundRecord(bidOrderRefundRecord));
    }

    /**
     * 删除订单退款记录
     */
    @ApiOperation("删除订单退款记录")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:record:remove')")
    @Log(title = "订单退款记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(bidOrderRefundRecordService.deleteBidOrderRefundRecordByIds(ids));
    }
}

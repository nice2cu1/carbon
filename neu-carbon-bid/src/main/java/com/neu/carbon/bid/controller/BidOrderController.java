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
import com.neu.common.enums.PaymentBank;
import com.neu.common.enums.PaymentStatus;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.framework.client.PaymentClient;
import com.neu.system.domain.PaymentModel;
import com.neu.system.domain.PaymentRefundModel;
import com.neu.system.domain.PaymentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 订单信息Controller
 *
 * @author neuedu
 * @date 2023-05-10
 */
@Api(tags = {"招投标（ 采购管理）-订单信息"})
@RestController
@RequestMapping("/bid/order")
public class BidOrderController extends BaseController {

    private final IBidOrderService bidOrderService;

    private final IBidOrderRefundRecordService bidOrderRefundRecordService;

    @Autowired
    public BidOrderController(IBidOrderService bidOrderService
            , IBidOrderRefundRecordService bidOrderRefundRecordService) {
        this.bidOrderService = bidOrderService;
        this.bidOrderRefundRecordService = bidOrderRefundRecordService;
    }

    /**
     * 查询订单信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询订单信息列表")
    @DynamicResponseParameters(name = "BidOrderList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidOrder.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidOrder bidOrder) {
        startPage();
        List<BidOrder> list = bidOrderService.selectBidOrderList(bidOrder);
        return getDataTable(list);
    }

    @GetMapping("/front/list")
    @ApiOperation("用户端查询订单信息列表")
    @DynamicResponseParameters(name = "BidOrderList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidOrder.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo frontList(BidOrder bidOrder) {
        startPage();
        List<BidOrder> list = bidOrderService.selectBidOrderList(bidOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单信息列表
     */
    @ApiOperation("导出订单信息列表")
    @DynamicResponseParameters(name = "BidGoodsGet", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:order:export')")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidOrder bidOrder) {
        List<BidOrder> list = bidOrderService.selectBidOrderList(bidOrder);
        ExcelUtil<BidOrder> util = new ExcelUtil<BidOrder>(BidOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取订单信息详细信息
     */
    @ApiOperation("获取订单信息详细信息")
    @DynamicResponseParameters(name = "BidOrderGet", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidOrder.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId) {
        return AjaxResult.success(bidOrderService.selectBidOrderById(orderId));
    }

    @ApiOperation("用户端获取订单信息详细信息")
    @DynamicResponseParameters(name = "BidOrderGet", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidOrder.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/front/{orderId}")
    public AjaxResult frontGetInfo(@PathVariable("orderId") Long orderId) {
        return AjaxResult.success(bidOrderService.selectBidOrderById(orderId));
    }

    /**
     * 新增订单信息
     */
    @ApiOperation("新增订单信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:order:add')")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidOrder bidOrder) {
        return toAjax(bidOrderService.insertBidOrder(bidOrder));
    }

    @ApiOperation("用户端提交订单")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping("/submitOrder")
    public AjaxResult submitOrder(@RequestBody BidOrder bidOrder) {
        int result = bidOrderService.insertBidOrder(bidOrder);
        if (result > 0) {
            return AjaxResult.success("",bidOrder.getOrderId().toString());
        }
        return AjaxResult.error();
    }

    @ApiOperation("用户端用户提交支付")
    @DynamicResponseParameters(name = "orderPay", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidOrder.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping("/orderPay")
    public AjaxResult orderPay(@RequestBody BidOrder bidOrder) {
        PaymentModel model = new PaymentModel();
        if (bidOrder.getOrderId() == null) {
            return AjaxResult.error("订单信息错误");
        }
        BidOrder order = this.bidOrderService.selectBidOrderById(bidOrder.getOrderId());

        if (order == null) {
            return AjaxResult.error("订单信息错误");
        }

        if (bidOrder.getPaymentMethod() == null) {
            return AjaxResult.error("支付方式错误");
        }

        if (!PaymentBank.getMap().containsKey(bidOrder.getPaymentMethod())) {
            return AjaxResult.error("支付方式错误");
        }

        order.setPaymentMethod(bidOrder.getPaymentMethod());
        PaymentResponse response = PaymentClient.create(model);
        Date now = new Date();

        order.setPaymentTime(now);
        if (response.getCode() == 500) {
            order.setPaymentStatus(PaymentStatus.PAY_ERROR.getCode());
        } else {
            order.setPaymentStatus(PaymentStatus.SUCCESS.getCode());
        }
        bidOrderService.updateBidOrder(order);
        if (response.getCode() == 500) {
            return AjaxResult.error(PaymentStatus.PAY_ERROR.getText());
        }
        return AjaxResult.success(bidOrder);
    }

    @ApiOperation("前端用户提交退款")
    @DynamicResponseParameters(name = "orderPay", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidOrder.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping("/orderRefund")
    public AjaxResult orderRefund(@RequestBody BidOrder bidOrder) {
        if (bidOrder.getOrderId() == null) {
            return AjaxResult.error("订单信息错误");
        }
        BidOrder order = this.bidOrderService.selectBidOrderById(bidOrder.getOrderId());

        if (order == null) {
            return AjaxResult.error("订单信息错误");
        }



        if (bidOrder.getRefundPrice() != null) {
            if (order.getPrice().compareTo(bidOrder.getRefundPrice()) < 0) {
                return AjaxResult.error("退款金额错误");
            }

        }

        order.setPaymentStatus(PaymentStatus.REFUND_AUDIT.getCode());
        PaymentResponse paymentResponse = PaymentClient.refund(new PaymentRefundModel());

        if (500 == paymentResponse.getCode()) {
            return AjaxResult.error("退款失败，请重新发起退款");
        }

        BidOrderRefundRecord bidOrderRefundRecord = new BidOrderRefundRecord();
        bidOrderRefundRecord.setOrderId(order.getOrderId());
        bidOrderRefundRecord.setRefundPrice(bidOrder.getRefundPrice());
        bidOrderRefundRecord.setRefundReason(bidOrder.getRefundReason());
        bidOrderRefundRecord.setRefundDescription(bidOrder.getRefundDescription());
        bidOrderRefundRecord.setGatewayId(paymentResponse.getTradeNo());

        this.bidOrderService.updateBidOrder(order);
        bidOrderRefundRecordService.insertBidOrderRefundRecord(bidOrderRefundRecord);

        return AjaxResult.success(bidOrder);
    }

    /**
     * 修改订单信息
     */
    @ApiOperation("修改订单信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:order:edit')")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidOrder bidOrder) {
        return toAjax(bidOrderService.updateBidOrder(bidOrder));
    }

    /**
     * 删除订单信息
     */
    @ApiOperation("删除订单信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:order:remove')")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds) {
        return toAjax(bidOrderService.deleteBidOrderByIds(orderIds));
    }
}

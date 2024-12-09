package com.neu.carbon.distribusion.controller;


import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.bid.domain.BidPolicylaw;
import com.neu.carbon.distribusion.domain.DistribusionCredit;
import com.neu.carbon.distribusion.domain.DistribusionEmissionCal;
import com.neu.carbon.distribusion.domain.DistribusionMessage;
import com.neu.carbon.distribusion.domain.DistribusionProduct;
import com.neu.carbon.distribusion.service.DistribusionService;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.domain.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.xml.ws.Response;
import java.util.Date;

/**
 * @author LIUJUN
 * 提供自定义API逻辑 无代码生成
 * */
@RestController
@Api(tags = "碳额度分配整体业务接口")
@RequestMapping("/distribusion/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DistribusionApi extends BaseController {

    /**
     * 服务
     * */
    private final DistribusionService distribusionService;


    /**
     * 申请碳排放额度
     * @param emission 申请额度
     * */
    @PostMapping("/commit")
    @ApiOperation("申请碳排放额度")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "emission",value = "申请额度",required = true)
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data", value = "返回业务数据",dataTypeClass = DistribusionEmissionCal.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            }
    )
    public AjaxResult commit(@RequestParam String emission){
        //得到用户信息
        SysUser user = getUser();
        return distribusionService.commit(emission,user);
    }

    /**
     * 购买额度
     * @param id 购买的套餐id
     * */
    @PostMapping("/purchase")
    @ApiOperation("购买额度")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "id",value = "套餐id",required = true)
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data", value = "返回业务数据",dataTypeClass = DistribusionEmissionCal.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            }
    )
    public AjaxResult purchase (Long id){
        //得到用户信息
        SysUser user = getUser();
        return distribusionService.purchase(user,id);
    }

    /**
     * 查询套餐列表
     * @return 套餐列表
     */
    @GetMapping("/product")
    @ApiOperation("查询套餐列表")
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data", value = "返回业务数据",dataTypeClass = DistribusionProduct.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            }
    )
    public AjaxResult productList(){
        return distribusionService.productList();
    }

    @ApiOperation("查询行业可申请额度")
    @GetMapping("/commitAmount")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "industry",value = "行业",required = true)
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "amount", value = "可申请额度"),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            }
    )
    public AjaxResult commitAmount(String industry){
        //得到用户信息
        return distribusionService.commitAmount(industry);
    }

    /**
     * 排放额度详情
     * */
//    @GetMapping("/limitDetails")
//    @ApiOperation("排放额度详情")
//    @DynamicResponseParameters(
//            properties = {
//                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
//                    @DynamicParameter(name = "data", value = "返回业务数据",dataTypeClass = DistribusionEmissionCal.class),
//                    @DynamicParameter(name = "msg", value = "返回消息内容")
//            }
//    )
//    public AjaxResult limitDetails(){
//        //得到用户信息
//        SysUser user = getUser();
//        return distribusionService.limitDetails(user);
//    }

    /**
     * 我的额度
     * */
    @GetMapping("/myCredit")
    @ApiOperation("我的额度信息")
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data", value = "返回业务数据",dataTypeClass = DistribusionEmissionCal.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            }
    )
    public AjaxResult myCredit(){
        //得到用户信息
        SysUser user = getUser();
        return distribusionService.myCredit(user);
    }

    @GetMapping("/recordList")
    @ApiOperation("查询变动/购买/申请记录列表")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "type",value = "类型（1 购买 2 申请）"),
                    @DynamicParameter(name = "status",value = "状态（购买 1 待支付 2 支付成功 3 已关闭 申请 1 等待审核 2 审核通过 3 审核失败）"),
                    @DynamicParameter(name = "enterpriseId",value = "企业id",required = true)
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code",value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data",value = "返回业务数据",dataTypeClass = DistribusionEmissionCal.class),
                    @DynamicParameter(name = "msg",value = "返回消息内容")
            }
    )
    public AjaxResult recordList(DistribusionEmissionCal distribusionEmissionCal){
        return distribusionService.recordList(distribusionEmissionCal);
    }


    @PutMapping("/payOrder")
    @ApiOperation("支付订单")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "id",value = "订单id",required = true),
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code",value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "msg",value = "返回消息内容")
            }
    )
    public AjaxResult payOrder(@RequestParam Long id){
        SysUser user = getUser();
        return distribusionService.payOrder(id,user);
    }

    @PutMapping("/cancelOrder")
    @ApiOperation("取消订单")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "id",value = "订单id",required = true),
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code",value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "msg",value = "返回消息内容")
            }
    )
    public AjaxResult cancelOrder(@RequestParam Long id){
        return distribusionService.cancelOrder(id);
    }

    @PutMapping("/checkPass")
    @ApiOperation("额度审核通过")
    public AjaxResult checkPass(@RequestParam Long id){
        return distribusionService.checkPass(id);
    }

    @PutMapping("/checkNoPass")
    @ApiOperation("额度审核不通过")
    public AjaxResult checkNoPass(@RequestParam Long id){
        return distribusionService.checkNoPass(id);
    }

    @GetMapping("/messageList")
    @ApiOperation("获取消息列表")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "enterpriseId",value = "企业id",required = true),
                    @DynamicParameter(name = "status",value = "是否已读"),
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code",value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data",value = "返回业务数据",dataTypeClass = DistribusionMessage.class),
                    @DynamicParameter(name = "msg",value = "返回消息内容")
            }
    )
    private AjaxResult getMessageList(DistribusionMessage distribusionMessage){
        SysUser user = getUser();
        return distribusionService.getMessageList(distribusionMessage,user);
    }

    @PutMapping("/updateMessage")
    @ApiOperation("更新消息状态")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "id",value = "消息id",required = true),
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code",value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "msg",value = "返回消息内容")
            }
    )
    public AjaxResult updateMessage(@RequestBody DistribusionMessage distribusionMessage){
        distribusionMessage.setStatus(1);
        distribusionMessage.setUpdateTime(new Date());
        distribusionMessage.setUpdateBy(getUser().getUserName());
        return distribusionService.updateMessage(distribusionMessage);
    }

    @GetMapping("/message/{id}")
    @ApiOperation("获取消息详情")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "id",value = "消息id",required = true),
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code",value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data",value = "返回业务数据",dataTypeClass = DistribusionMessage.class),
                    @DynamicParameter(name = "msg",value = "返回消息内容")
            }
    )
    public AjaxResult getMessage(@PathVariable Long id){
        return distribusionService.selectDistribusionMessageById(id);
    }

    @GetMapping("/frontList")
    @ApiOperation("查询政策法规列表")
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code",value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data",value = "返回业务数据",dataTypeClass = BidPolicylaw.class),
                    @DynamicParameter(name = "msg",value = "返回消息内容")
            }
    )
    public AjaxResult frontList(BidPolicylaw bidPolicylaw){
        return distribusionService.frontList(bidPolicylaw);
    }

    @GetMapping("/front/{id}")
    @ApiOperation("查询政策法规详情")
    @DynamicParameters(
            properties = {
                    @DynamicParameter(name = "id",value = "政策法规id",required = true),
            }
    )
    @DynamicResponseParameters(
            properties = {
                    @DynamicParameter(name = "code",value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data",value = "返回业务数据",dataTypeClass = BidPolicylaw.class),
                    @DynamicParameter(name = "msg",value = "返回消息内容")
            }
    )
    public AjaxResult frontById(@PathVariable Long id){
        return distribusionService.frontById(id);
    }
}

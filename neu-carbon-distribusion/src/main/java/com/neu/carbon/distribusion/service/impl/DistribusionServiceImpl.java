package com.neu.carbon.distribusion.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.neu.carbon.bid.domain.BidEnterprise;
import com.neu.carbon.bid.domain.BidPolicylaw;
import com.neu.carbon.bid.service.IBidEnterpriseService;
import com.neu.carbon.bid.service.IBidPolicylawService;
import com.neu.carbon.distribusion.domain.*;
import com.neu.carbon.distribusion.service.*;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.domain.entity.SysUser;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DistribusionServiceImpl implements DistribusionService {

    //企业信息
    private final IBidEnterpriseService bidEnterpriseService;
    //总额度
    private final IDistribusionTotalEmissionService totalEmissionService;
    //额度变更记录
    private final IDistribusionEmissionCalService calService;
    //已分配额度
    private final IDistribusionCreditService creditService;
    //分配方法
    private final IDistribusionMethodService methodService;
    //套餐
    private final IDistribusionProductService productService;
    // 消息
    private final IDistribusionMessageService messageService;
    // 政策法规
    private final IBidPolicylawService bidPolicylawService;



    /**
     * @param emission 申请额度
     * @param user     用户信息
     *                 申请额度
     *                 总额度就是行业占比
     */
    @Override
    public AjaxResult commit(String emission, SysUser user) {
        if(!emission.matches("[0-9]*\\.?[0-9]+")){
            return AjaxResult.error(500,"申请额度必须是数字或者小数");
        }
        //得到企业关联信息
        BidEnterprise enterpriseInfo = bidEnterpriseService.getBidEnterpriseByUserName(user.getUserName());
        AjaxResult ajax = new AjaxResult();
        //判断是否已分配
        if (creditService.getInfoByEnterpriseId(enterpriseInfo.getId()) != null){
            List<DistribusionEmissionCal> emissionCals = calService.selectDistribusionEmissionCalList(new DistribusionEmissionCal() {{
                setEnterpriseId(enterpriseInfo.getId());
                setType("2");
            }});
            ajax.put("code",200);
            ajax.put("status",emissionCals.get(0).getStatus());
            return ajax;
        }
        //得到总额度
        DistribusionTotalEmission distribusionTotalEmission = totalEmissionService.selectDistribusionTotalEmissionById(1L);
        //根据企业 行业 历史排放额度计算 公式？
        // 储备额度
        BigDecimal storageAmount = new BigDecimal(distribusionTotalEmission.getPayEmission());
        BigDecimal emissionAmount = new BigDecimal(emission);
        if (storageAmount.compareTo(emissionAmount) < 0) {
            return AjaxResult.error(500,"储备额度不足");
        }
        BigDecimal total = storageAmount.subtract(emissionAmount);
        // 更新总额度
        distribusionTotalEmission.setStorageEmission(total.toString());
        totalEmissionService.updateDistribusionTotalEmission(distribusionTotalEmission);
        //更新企业额度
        DistribusionCredit insertBody = new DistribusionCredit();
        insertBody.setEnterpriseId(enterpriseInfo.getId());
        insertBody.setCredit(emission);
        insertBody.setRemainingCredit(emission);
        //TODO 设置变动记录
        creditService.insertDistribusionCredit(insertBody);

        // 更新记录
        DistribusionEmissionCal insertCal = new DistribusionEmissionCal();
        insertCal.setEmissionNo(getOrderNo());
        insertCal.setType("2");
        insertCal.setAmount(emission);
        insertCal.setStatus("1");
        insertCal.setCreateBy(String.valueOf(user.getUserId()));
        insertCal.setCreateTime(new Date());
        insertCal.setEnterpriseId(enterpriseInfo.getId());
        calService.insertDistribusionEmissionCal(insertCal);

        ajax.put("code",200);
        ajax.put("status","1");
        return ajax;
    }

    /**
     * 购买额度
     * @param user 用户信息
     * @param id   套餐ID
     */
    @Override
    public AjaxResult purchase(SysUser user, Long id) {
        //得到企业关联信息
        BidEnterprise enterpriseInfo = bidEnterpriseService.getBidEnterpriseByUserName(user.getUserName());
        //得到企业行业信息
        String industry = enterpriseInfo.getIndustry();
        //得到套餐详情
        DistribusionProduct product = productService.selectDistribusionProductById(id);
        // 查询是否是相同行业
        if (product.getSuitable().equals(industry)){
            // 查询企业是否申请过
            DistribusionCredit haveCredit = creditService.getInfoByEnterpriseId(enterpriseInfo.getId());
            if (ObjectUtil.isNotEmpty(haveCredit)){
                // 查询总额度
                DistribusionTotalEmission distribusionTotalEmission = totalEmissionService.selectDistribusionTotalEmissionById(1L);
                BigDecimal payEmission = new BigDecimal(distribusionTotalEmission.getPayEmission());
                BigDecimal credit = new BigDecimal(product.getCredit());
                if(payEmission.compareTo(credit) < 0){
                    return AjaxResult.error(500,"购买额度不足");
                }
                //插入表更记录表
                DistribusionEmissionCal insertCal = new DistribusionEmissionCal();
                insertCal.setEmissionNo(getOrderNo());
                insertCal.setType("1");
                insertCal.setMoney(product.getPrice());
                insertCal.setAmount(product.getCredit());
                insertCal.setSuitable(product.getSuitable());
                insertCal.setStatus("1");
                insertCal.setCreateBy(String.valueOf(user.getUserId()));
                insertCal.setCreateTime(new Date());
                insertCal.setEnterpriseId(enterpriseInfo.getId());
                BigDecimal enterMoney = new BigDecimal(enterpriseInfo.getMoney());
                BigDecimal productMoney = new BigDecimal(product.getPrice());
                // 查询用户金额是否足够
                if(enterMoney.compareTo(productMoney) >= 0){
                    insertCal.setStatus("2");
                    // 更新金额
                    BidEnterprise enterprise = new BidEnterprise();
                    enterprise.setId(enterpriseInfo.getId());
                    enterprise.setMoney(enterMoney.subtract(productMoney).toString());
                    bidEnterpriseService.updateBidEnterprise(enterprise);
                    // 更新总额度
                    distribusionTotalEmission.setPayEmission(payEmission.subtract(credit).toString());
                    totalEmissionService.updateDistribusionTotalEmission(distribusionTotalEmission);
                    // 更新企业额度
                    BigDecimal old = new BigDecimal(haveCredit.getCredit());
                    BigDecimal remainingCredit = new BigDecimal(haveCredit.getRemainingCredit());
                    haveCredit.setCredit(old.add(credit).toString());
                    haveCredit.setRemainingCredit(remainingCredit.add(credit).toString());
                    haveCredit.setEnterpriseId(enterpriseInfo.getId());
                    // 新增消息
                    messageService.insertDistribusionMessage(new DistribusionMessage(){{
                        setMessage(String.format("您已成功购买%s碳积分！请到”额度管理“查看最新碳积分额度。",product.getCredit()));
                        setType(2);
                        setCreateTime(new Date());
                        setCreateBy(user.getUserName());
                        setEnterpriseId(enterpriseInfo.getId());
                    }});
                    creditService.updateDistribusionCredit(haveCredit);
                }else{
                    // 创建订单
                    calService.insertDistribusionEmissionCal(insertCal);
                    return AjaxResult.error(500,"余额不足");
                }
                calService.insertDistribusionEmissionCal(insertCal);
                return AjaxResult.success();
            }else {
                return AjaxResult.error(500,"先申请额度");
            }
        }else {
            //不同行业
            return AjaxResult.error(500,"不能购买其他行业的额度");
        }
    }

    /**
     * 排放额度详情
     *
     * @param user 用户信息
     */
    @Override
    public AjaxResult limitDetails(SysUser user) {
        //得到企业关联信息
        BidEnterprise enterpriseInfo = bidEnterpriseService.getBidEnterpriseByUserName(user.getUserName());
        //得到剩余额度
        DistribusionEmissionCal info =  calService.getInfoByEnterpriceId(enterpriseInfo.getId());
        return AjaxResult.success(info);
    }


    @Override
    public AjaxResult myCredit(SysUser user) {
        //得到企业关联信息
        BidEnterprise enterpriseInfo = bidEnterpriseService.getBidEnterpriseByUserName(user.getUserName());

        DistribusionCredit distribusionCredit= creditService.getInfoByEnterpriseId(enterpriseInfo.getId());

        return AjaxResult.success(distribusionCredit);
    }

    @Override
    public AjaxResult recordList(DistribusionEmissionCal distribusionEmissionCal){
        return AjaxResult.success(calService.selectDistribusionEmissionCalAppList(distribusionEmissionCal));
    }

    private String getOrderNo(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomUtil.randomInt(0,9);
    }

    @Override
    public AjaxResult productList() {
        return AjaxResult.success(productService.selectDistribusionProductList(new DistribusionProduct()));
    }

    @Override
    public AjaxResult commitAmount(String industry) {
        // 获取行业比例
        DistribusionMethod distribusionMethod = methodService.selectDistribusionMethodByName(industry);
        if(ObjectUtil.isEmpty(distribusionMethod)){
            return AjaxResult.error("暂无此行业");
        }
        DistribusionTotalEmission distribusionTotalEmission = totalEmissionService.selectDistribusionTotalEmissionById(1L);
        BigDecimal storageEmission = new BigDecimal(distribusionTotalEmission.getStorageEmission());
        BigDecimal ratio = new BigDecimal(distribusionMethod.getRatio());
        BigDecimal radioDiv = ratio.divide(new BigDecimal(100));
        BigDecimal amount = storageEmission.multiply(radioDiv);
        if(storageEmission.compareTo(amount) < 0){
            return AjaxResult.error("额度不足");
        }
        AjaxResult ajax = new AjaxResult();
        ajax.put("code",200);
        ajax.put("msg","操作成功");
        ajax.put("amount",amount.toString());
        return ajax;
    }

    @Override
    public AjaxResult payOrder(Long id,SysUser user) {
        DistribusionEmissionCal cal = calService.selectDistribusionEmissionCalById(id);
        // 查询是否存在订单
        if(ObjectUtil.isEmpty(cal)){
            return AjaxResult.error("没有当前订单");
        }
        // 查询订单是否已支付
        if("2".equals(cal.getStatus())){
            return AjaxResult.error("订单已支付");
        }
        // 查询总额度
        DistribusionTotalEmission distribusionTotalEmission = totalEmissionService.selectDistribusionTotalEmissionById(1L);
        BigDecimal payEmission = new BigDecimal(distribusionTotalEmission.getPayEmission());
        BigDecimal amount = new BigDecimal(cal.getAmount());
        // 判断额度是否充足
        if(payEmission.compareTo(amount) < 0){
            return AjaxResult.error("购买额度不足");
        }
        //得到企业关联信息
        BidEnterprise enterpriseInfo = bidEnterpriseService.getBidEnterpriseByUserName(user.getUserName());
        BigDecimal money = new BigDecimal(enterpriseInfo.getMoney());
        BigDecimal price = new BigDecimal(cal.getMoney());
        // 查询企业金额是否充足
        if(price.compareTo(money) > 0){
            return AjaxResult.error("余额不足");
        }
        // 更新额度
        distribusionTotalEmission.setPayEmission(payEmission.subtract(amount).toString());
        totalEmissionService.updateDistribusionTotalEmission(distribusionTotalEmission);
        // 更新余额
        enterpriseInfo.setMoney(money.subtract(price).toString());
        bidEnterpriseService.updateBidEnterprise(enterpriseInfo);
        // 更新订单
        cal.setStatus("2");
        calService.updateDistribusionEmissionCal(cal);
        // 新增购买消息
        messageService.insertDistribusionMessage(new DistribusionMessage(){{
            setMessage(String.format("您已成功购买%s碳积分！请到”额度管理“查看最新碳积分额度。",cal.getAmount()));
            setType(2);
            setCreateTime(new Date());
            setCreateBy(user.getUserName());
            setEnterpriseId(enterpriseInfo.getId());
        }});
        return AjaxResult.success();
    }

    @Override
    public AjaxResult cancelOrder(Long id) {
        DistribusionEmissionCal cal = calService.selectDistribusionEmissionCalById(id);
        if(ObjectUtil.isEmpty(cal)){
            return AjaxResult.error("没有当前订单");
        }
        if("2".equals(cal.getStatus())){
            return AjaxResult.error("订单已支付");
        }
        DistribusionEmissionCal distribusionEmissionCal = new DistribusionEmissionCal();
        distribusionEmissionCal.setId(cal.getId());
        distribusionEmissionCal.setStatus("3");
        calService.updateDistribusionEmissionCal(distribusionEmissionCal);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult checkPass(Long id) {
        DistribusionEmissionCal cal = calService.selectDistribusionEmissionCalById(id);
        if(ObjectUtil.isEmpty(cal)){
            return AjaxResult.error("未找到当前申请额度订单");
        }
        // 新增企业额度
//        DistribusionCredit insertBody = new DistribusionCredit();
//        insertBody.setEnterpriseId(cal.getId());
//        insertBody.setCredit(cal.getAmount());
//        insertBody.setRemainingCredit(cal.getAmount());
//        creditService.insertDistribusionCredit(insertBody);
        // 更新订单
        cal.setStatus("2");
        calService.updateDistribusionEmissionCal(cal);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult checkNoPass(Long id) {
        DistribusionEmissionCal cal = calService.selectDistribusionEmissionCalById(id);
        if(ObjectUtil.isEmpty(cal)){
            return AjaxResult.error("未找到当前申请额度订单");
        }
        DistribusionTotalEmission distribusionTotalEmission = totalEmissionService.selectDistribusionTotalEmissionById(1L);
        BigDecimal storageEmission = new BigDecimal(distribusionTotalEmission.getStorageEmission());
        BigDecimal amount = new BigDecimal(cal.getAmount());
        distribusionTotalEmission.setStorageEmission(storageEmission.add(amount).toString());
        totalEmissionService.updateDistribusionTotalEmission(distribusionTotalEmission);
        cal.setStatus("3");
        calService.updateDistribusionEmissionCal(cal);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getMessageList(DistribusionMessage distribusionMessage,SysUser user) {
        BidEnterprise enterprise = bidEnterpriseService.selectBidEnterpriseByUserName(user.getUserName());
        distribusionMessage.setEnterpriseId(enterprise.getId());
        return AjaxResult.success(messageService.selectMessageList(distribusionMessage));
    }

    @Override
    public AjaxResult updateMessage(DistribusionMessage distribusionMessage) {
        messageService.updateDistribusionMessage(distribusionMessage);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult selectDistribusionMessageById(Long id) {
        return AjaxResult.success(messageService.selectDistribusionMessageById(id));
    }

    @Override
    public AjaxResult frontList(BidPolicylaw bidPolicylaw) {
        return AjaxResult.success(bidPolicylawService.selectBidPolicylawList(bidPolicylaw));
    }

    @Override
    public AjaxResult frontById(Long id) {
        return AjaxResult.success(bidPolicylawService.selectBidPolicylawById(id));
    }
}

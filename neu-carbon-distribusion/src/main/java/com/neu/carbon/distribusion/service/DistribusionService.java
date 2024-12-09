package com.neu.carbon.distribusion.service;

import com.neu.carbon.bid.domain.BidPolicylaw;
import com.neu.carbon.distribusion.domain.DistribusionEmissionCal;
import com.neu.carbon.distribusion.domain.DistribusionMessage;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.domain.entity.SysUser;
import com.neu.framework.web.domain.server.Sys;
import org.springframework.stereotype.Service;

@Service
public interface DistribusionService {

    /**
     * @param emission 申请额度
     * @param user 用户信息
     * 申请额度
     * */
    AjaxResult commit(String emission, SysUser user);
    /**
     * @param id 套餐ID
     * @param user 用户信息
     * 申请额度
     * */
    AjaxResult purchase(SysUser user, Long id);
    /**
     * 排放额度详情
     * */
    AjaxResult limitDetails(SysUser user);

    /**
     * 我的额度
     * */
    AjaxResult myCredit(SysUser user);

    AjaxResult recordList(DistribusionEmissionCal distribusionEmissionCal);

    AjaxResult productList();

    AjaxResult commitAmount(String industry);

    AjaxResult payOrder(Long id, SysUser user);

    AjaxResult cancelOrder(Long id);

    AjaxResult checkPass(Long id);

    AjaxResult checkNoPass(Long id);

    AjaxResult getMessageList(DistribusionMessage distribusionMessage,SysUser user);

    AjaxResult updateMessage(DistribusionMessage distribusionMessage);

    AjaxResult selectDistribusionMessageById(Long id);

    AjaxResult frontList(BidPolicylaw bidPolicylaw);

    AjaxResult frontById(Long id);
}

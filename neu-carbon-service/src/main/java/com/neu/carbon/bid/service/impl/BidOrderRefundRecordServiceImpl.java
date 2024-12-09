package com.neu.carbon.bid.service.impl;

import cn.hutool.core.util.IdUtil;
import com.neu.carbon.bid.domain.BidOrderRefundRecord;
import com.neu.carbon.bid.mapper.BidOrderRefundRecordMapper;
import com.neu.carbon.bid.service.IBidOrderRefundRecordService;
import com.neu.common.enums.RefundStatus;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单退款记录Service业务层处理
 *
 * @author neuedu
 * @date 2023-05-10
 */
@Service
public class BidOrderRefundRecordServiceImpl implements IBidOrderRefundRecordService {
    @Autowired
    private BidOrderRefundRecordMapper bidOrderRefundRecordMapper;

    /**
     * 查询订单退款记录
     *
     * @param id 订单退款记录ID
     * @return 订单退款记录
     */
    @Override
    public BidOrderRefundRecord selectBidOrderRefundRecordById(Long id) {
        return bidOrderRefundRecordMapper.selectBidOrderRefundRecordById(id);
    }

    /**
     * 查询订单退款记录列表
     *
     * @param bidOrderRefundRecord 订单退款记录
     * @return 订单退款记录
     */
    @Override
    public List<BidOrderRefundRecord> selectBidOrderRefundRecordList(BidOrderRefundRecord bidOrderRefundRecord) {
        return bidOrderRefundRecordMapper.selectBidOrderRefundRecordList(bidOrderRefundRecord);
    }

    /**
     * 新增订单退款记录
     *
     * @param bidOrderRefundRecord 订单退款记录
     * @return 结果
     */
    @Override
    public int insertBidOrderRefundRecord(BidOrderRefundRecord bidOrderRefundRecord) {
        bidOrderRefundRecord.setCreateTime(DateUtils.getNowDate());
        bidOrderRefundRecord.setId(IdUtil.getSnowflake(1, 1).nextId());
        return bidOrderRefundRecordMapper.insertBidOrderRefundRecord(bidOrderRefundRecord);
    }

    /**
     * 修改订单退款记录
     *
     * @param bidOrderRefundRecord 订单退款记录
     * @return 结果
     */
    @Override
    public int updateBidOrderRefundRecord(BidOrderRefundRecord bidOrderRefundRecord) {
        if (RefundStatus.SUCCESS.getCode().equals(bidOrderRefundRecord.getCheckStatus())) {
            bidOrderRefundRecord.setRefundTime(DateUtils.getNowDate());
            bidOrderRefundRecord.setUpdateTime(DateUtils.getNowDate());
        } else {
            bidOrderRefundRecord.setUpdateTime(DateUtils.getNowDate());
        }

        return bidOrderRefundRecordMapper.updateBidOrderRefundRecord(bidOrderRefundRecord);
    }

    /**
     * 批量删除订单退款记录
     *
     * @param ids 需要删除的订单退款记录ID
     * @return 结果
     */
    @Override
    public int deleteBidOrderRefundRecordByIds(Long[] ids) {
        return bidOrderRefundRecordMapper.deleteBidOrderRefundRecordByIds(ids);
    }

    /**
     * 删除订单退款记录信息
     *
     * @param id 订单退款记录ID
     * @return 结果
     */
    @Override
    public int deleteBidOrderRefundRecordById(Long id) {
        return bidOrderRefundRecordMapper.deleteBidOrderRefundRecordById(id);
    }
}

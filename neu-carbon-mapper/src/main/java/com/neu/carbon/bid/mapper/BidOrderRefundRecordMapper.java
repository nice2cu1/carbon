package com.neu.carbon.bid.mapper;

import com.neu.carbon.bid.domain.BidOrderRefundRecord;

import java.util.List;

/**
 * 订单退款记录Mapper接口
 *
 * @author neuedu
 * @date 2023-05-10
 */
public interface BidOrderRefundRecordMapper
{
    /**
     * 查询订单退款记录
     *
     * @param id 订单退款记录ID
     * @return 订单退款记录
     */
    public BidOrderRefundRecord selectBidOrderRefundRecordById(Long id);

    /**
     * 查询订单退款记录列表
     *
     * @param bidOrderRefundRecord 订单退款记录
     * @return 订单退款记录集合
     */
    public List<BidOrderRefundRecord> selectBidOrderRefundRecordList(BidOrderRefundRecord bidOrderRefundRecord);

    /**
     * 新增订单退款记录
     *
     * @param bidOrderRefundRecord 订单退款记录
     * @return 结果
     */
    public int insertBidOrderRefundRecord(BidOrderRefundRecord bidOrderRefundRecord);

    /**
     * 修改订单退款记录
     *
     * @param bidOrderRefundRecord 订单退款记录
     * @return 结果
     */
    public int updateBidOrderRefundRecord(BidOrderRefundRecord bidOrderRefundRecord);

    /**
     * 删除订单退款记录
     *
     * @param id 订单退款记录ID
     * @return 结果
     */
    public int deleteBidOrderRefundRecordById(Long id);

    /**
     * 批量删除订单退款记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBidOrderRefundRecordByIds(Long[] ids);
}

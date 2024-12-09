package com.neu.carbon.bid.service.impl;

import cn.hutool.core.util.IdUtil;
import com.neu.carbon.bid.domain.BidOrder;
import com.neu.carbon.bid.mapper.BidOrderMapper;
import com.neu.carbon.bid.service.IBidOrderService;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单信息Service业务层处理
 *
 * @author neuedu
 * @date 2023-05-10
 */
@Service
public class BidOrderServiceImpl implements IBidOrderService
{
    @Autowired
    private BidOrderMapper bidOrderMapper;

    /**
     * 查询订单信息
     *
     * @param orderId 订单信息ID
     * @return 订单信息
     */
    @Override
    public BidOrder selectBidOrderById(Long orderId)
    {
        return bidOrderMapper.selectBidOrderById(orderId);
    }

    /**
     * 查询订单信息列表
     *
     * @param bidOrder 订单信息
     * @return 订单信息
     */
    @Override
    public List<BidOrder> selectBidOrderList(BidOrder bidOrder)
    {
        return bidOrderMapper.selectBidOrderList(bidOrder);
    }

    /**
     * 新增订单信息
     *
     * @param bidOrder 订单信息
     * @return 结果
     */
    @Override
    public int insertBidOrder(BidOrder bidOrder)
    {
        bidOrder.setCreateTime(DateUtils.getNowDate());
        bidOrder.setOrderId(IdUtil.getSnowflake(1, 1).nextId());
        return bidOrderMapper.insertBidOrder(bidOrder);
    }

    /**
     * 修改订单信息
     *
     * @param bidOrder 订单信息
     * @return 结果
     */
    @Override
    public int updateBidOrder(BidOrder bidOrder)
    {
        bidOrder.setUpdateTime(DateUtils.getNowDate());
        return bidOrderMapper.updateBidOrder(bidOrder);
    }

    /**
     * 批量删除订单信息
     *
     * @param orderIds 需要删除的订单信息ID
     * @return 结果
     */
    @Override
    public int deleteBidOrderByIds(Long[] orderIds)
    {
        return bidOrderMapper.deleteBidOrderByIds(orderIds);
    }

    /**
     * 删除订单信息信息
     *
     * @param orderId 订单信息ID
     * @return 结果
     */
    @Override
    public int deleteBidOrderById(Long orderId)
    {
        return bidOrderMapper.deleteBidOrderById(orderId);
    }
}

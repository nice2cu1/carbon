package com.neu.carbon.bid.service;

import com.neu.carbon.bid.domain.BidOrder;

import java.util.List;

/**
 * 订单信息Service接口
 *
 * @author neuedu
 * @date 2023-05-10
 */
public interface IBidOrderService
{
    /**
     * 查询订单信息
     *
     * @param orderId 订单信息ID
     * @return 订单信息
     */
    public BidOrder selectBidOrderById(Long orderId);

    /**
     * 查询订单信息列表
     *
     * @param bidOrder 订单信息
     * @return 订单信息集合
     */
    public List<BidOrder> selectBidOrderList(BidOrder bidOrder);

    /**
     * 新增订单信息
     *
     * @param bidOrder 订单信息
     * @return 结果
     */
    public int insertBidOrder(BidOrder bidOrder);

    /**
     * 修改订单信息
     *
     * @param bidOrder 订单信息
     * @return 结果
     */
    public int updateBidOrder(BidOrder bidOrder);

    /**
     * 批量删除订单信息
     *
     * @param orderIds 需要删除的订单信息ID
     * @return 结果
     */
    public int deleteBidOrderByIds(Long[] orderIds);

    /**
     * 删除订单信息信息
     *
     * @param orderId 订单信息ID
     * @return 结果
     */
    public int deleteBidOrderById(Long orderId);
}

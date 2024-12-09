package com.neu.carbon.distribusion.mapper;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionHistory;

/**
 * 过往数据Mapper接口
 * 
 * @author neuedu
 * @date 2024-01-25
 */
public interface DistribusionHistoryMapper 
{
    /**
     * 查询过往数据
     * 
     * @param id 过往数据ID
     * @return 过往数据
     */
    public DistribusionHistory selectDistribusionHistoryById(Long id);

    /**
     * 查询过往数据列表
     * 
     * @param distribusionHistory 过往数据
     * @return 过往数据集合
     */
    public List<DistribusionHistory> selectDistribusionHistoryList(DistribusionHistory distribusionHistory);

    /**
     * 新增过往数据
     * 
     * @param distribusionHistory 过往数据
     * @return 结果
     */
    public int insertDistribusionHistory(DistribusionHistory distribusionHistory);

    /**
     * 修改过往数据
     * 
     * @param distribusionHistory 过往数据
     * @return 结果
     */
    public int updateDistribusionHistory(DistribusionHistory distribusionHistory);

    /**
     * 删除过往数据
     * 
     * @param id 过往数据ID
     * @return 结果
     */
    public int deleteDistribusionHistoryById(Long id);

    /**
     * 批量删除过往数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDistribusionHistoryByIds(Long[] ids);
}

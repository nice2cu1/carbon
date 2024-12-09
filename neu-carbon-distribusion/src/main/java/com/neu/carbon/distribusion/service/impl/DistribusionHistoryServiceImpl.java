package com.neu.carbon.distribusion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.distribusion.mapper.DistribusionHistoryMapper;
import com.neu.carbon.distribusion.domain.DistribusionHistory;
import com.neu.carbon.distribusion.service.IDistribusionHistoryService;

/**
 * 过往数据Service业务层处理
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@Service
public class DistribusionHistoryServiceImpl implements IDistribusionHistoryService 
{
    @Autowired
    private DistribusionHistoryMapper distribusionHistoryMapper;

    /**
     * 查询过往数据
     * 
     * @param id 过往数据ID
     * @return 过往数据
     */
    @Override
    public DistribusionHistory selectDistribusionHistoryById(Long id)
    {
        return distribusionHistoryMapper.selectDistribusionHistoryById(id);
    }

    /**
     * 查询过往数据列表
     * 
     * @param distribusionHistory 过往数据
     * @return 过往数据
     */
    @Override
    public List<DistribusionHistory> selectDistribusionHistoryList(DistribusionHistory distribusionHistory)
    {
        return distribusionHistoryMapper.selectDistribusionHistoryList(distribusionHistory);
    }

    /**
     * 新增过往数据
     * 
     * @param distribusionHistory 过往数据
     * @return 结果
     */
    @Override
    public int insertDistribusionHistory(DistribusionHistory distribusionHistory)
    {
        return distribusionHistoryMapper.insertDistribusionHistory(distribusionHistory);
    }

    /**
     * 修改过往数据
     * 
     * @param distribusionHistory 过往数据
     * @return 结果
     */
    @Override
    public int updateDistribusionHistory(DistribusionHistory distribusionHistory)
    {
        return distribusionHistoryMapper.updateDistribusionHistory(distribusionHistory);
    }

    /**
     * 批量删除过往数据
     * 
     * @param ids 需要删除的过往数据ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionHistoryByIds(Long[] ids)
    {
        return distribusionHistoryMapper.deleteDistribusionHistoryByIds(ids);
    }

    /**
     * 删除过往数据信息
     * 
     * @param id 过往数据ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionHistoryById(Long id)
    {
        return distribusionHistoryMapper.deleteDistribusionHistoryById(id);
    }
}

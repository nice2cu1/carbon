package com.neu.carbon.distribusion.service.impl;

import java.util.List;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.distribusion.mapper.DistribusionMessageMapper;
import com.neu.carbon.distribusion.domain.DistribusionMessage;
import com.neu.carbon.distribusion.service.IDistribusionMessageService;

/**
 * 消息中心Service业务层处理
 * 
 * @author neuedu
 * @date 2024-02-29
 */
@Service
public class DistribusionMessageServiceImpl implements IDistribusionMessageService 
{
    @Autowired
    private DistribusionMessageMapper distribusionMessageMapper;

    /**
     * 查询消息中心
     * 
     * @param id 消息中心ID
     * @return 消息中心
     */
    @Override
    public DistribusionMessage selectDistribusionMessageById(Long id)
    {
        return distribusionMessageMapper.selectDistribusionMessageById(id);
    }

    /**
     * 查询消息中心列表
     * 
     * @param distribusionMessage 消息中心
     * @return 消息中心
     */
    @Override
    public List<DistribusionMessage> selectDistribusionMessageList(DistribusionMessage distribusionMessage)
    {
        return distribusionMessageMapper.selectDistribusionMessageList(distribusionMessage);
    }

    @Override
    public List<DistribusionMessage> selectMessageList(DistribusionMessage distribusionMessage){
        return distribusionMessageMapper.selectMessageList(distribusionMessage);
    }

    /**
     * 新增消息中心
     * 
     * @param distribusionMessage 消息中心
     * @return 结果
     */
    @Override
    public int insertDistribusionMessage(DistribusionMessage distribusionMessage)
    {
        distribusionMessage.setCreateTime(DateUtils.getNowDate());
        return distribusionMessageMapper.insertDistribusionMessage(distribusionMessage);
    }

    /**
     * 修改消息中心
     * 
     * @param distribusionMessage 消息中心
     * @return 结果
     */
    @Override
    public int updateDistribusionMessage(DistribusionMessage distribusionMessage)
    {
        distribusionMessage.setUpdateTime(DateUtils.getNowDate());
        return distribusionMessageMapper.updateDistribusionMessage(distribusionMessage);
    }

    /**
     * 批量删除消息中心
     * 
     * @param ids 需要删除的消息中心ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionMessageByIds(Long[] ids)
    {
        return distribusionMessageMapper.deleteDistribusionMessageByIds(ids);
    }

    /**
     * 删除消息中心信息
     * 
     * @param id 消息中心ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionMessageById(Long id)
    {
        return distribusionMessageMapper.deleteDistribusionMessageById(id);
    }
}

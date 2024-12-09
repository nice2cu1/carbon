package com.neu.carbon.distribusion.mapper;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionMessage;

/**
 * 消息中心Mapper接口
 * 
 * @author neuedu
 * @date 2024-02-29
 */
public interface DistribusionMessageMapper 
{
    /**
     * 查询消息中心
     * 
     * @param id 消息中心ID
     * @return 消息中心
     */
    public DistribusionMessage selectDistribusionMessageById(Long id);

    /**
     * 查询消息中心列表
     * 
     * @param distribusionMessage 消息中心
     * @return 消息中心集合
     */
    public List<DistribusionMessage> selectDistribusionMessageList(DistribusionMessage distribusionMessage);

    public List<DistribusionMessage> selectMessageList(DistribusionMessage distribusionMessage);

    /**
     * 新增消息中心
     * 
     * @param distribusionMessage 消息中心
     * @return 结果
     */
    public int insertDistribusionMessage(DistribusionMessage distribusionMessage);

    /**
     * 修改消息中心
     * 
     * @param distribusionMessage 消息中心
     * @return 结果
     */
    public int updateDistribusionMessage(DistribusionMessage distribusionMessage);

    /**
     * 删除消息中心
     * 
     * @param id 消息中心ID
     * @return 结果
     */
    public int deleteDistribusionMessageById(Long id);

    /**
     * 批量删除消息中心
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDistribusionMessageByIds(Long[] ids);
}

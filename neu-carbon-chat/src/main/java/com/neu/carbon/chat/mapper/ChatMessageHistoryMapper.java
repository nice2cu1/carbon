package com.neu.carbon.chat.mapper;

import java.util.List;
import com.neu.carbon.chat.domain.ChatMessageHistory;

/**
 * 聊天记录Mapper接口
 * 
 * @author neuedu
 * @date 2023-12-11
 */
public interface ChatMessageHistoryMapper 
{
    /**
     * 查询聊天记录
     * 
     * @param messageid 聊天记录ID
     * @return 聊天记录
     */
    public ChatMessageHistory selectChatMessageHistoryById(Long messageid);

    /**
     * 查询聊天记录列表
     * 
     * @param chatMessageHistory 聊天记录
     * @return 聊天记录集合
     */
    public List<ChatMessageHistory> selectChatMessageHistoryList(ChatMessageHistory chatMessageHistory);

    /**
     * 新增聊天记录
     * 
     * @param chatMessageHistory 聊天记录
     * @return 结果
     */
    public int insertChatMessageHistory(ChatMessageHistory chatMessageHistory);

    /**
     * 修改聊天记录
     * 
     * @param chatMessageHistory 聊天记录
     * @return 结果
     */
    public int updateChatMessageHistory(ChatMessageHistory chatMessageHistory);

    /**
     * 删除聊天记录
     * 
     * @param messageid 聊天记录ID
     * @return 结果
     */
    public int deleteChatMessageHistoryById(Long messageid);

    /**
     * 批量删除聊天记录
     * 
     * @param messageids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChatMessageHistoryByIds(Long[] messageids);
}

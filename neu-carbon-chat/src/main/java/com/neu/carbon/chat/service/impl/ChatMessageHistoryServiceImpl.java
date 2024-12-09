package com.neu.carbon.chat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.chat.mapper.ChatMessageHistoryMapper;
import com.neu.carbon.chat.domain.ChatMessageHistory;
import com.neu.carbon.chat.service.IChatMessageHistoryService;

/**
 * 聊天记录Service业务层处理
 * 
 * @author neuedu
 * @date 2023-12-11
 */
@Service
public class ChatMessageHistoryServiceImpl implements IChatMessageHistoryService 
{
    @Autowired
    private ChatMessageHistoryMapper chatMessageHistoryMapper;

    /**
     * 查询聊天记录
     * 
     * @param messageid 聊天记录ID
     * @return 聊天记录
     */
    @Override
    public ChatMessageHistory selectChatMessageHistoryById(Long messageid)
    {
        return chatMessageHistoryMapper.selectChatMessageHistoryById(messageid);
    }

    /**
     * 查询聊天记录列表
     * 
     * @param chatMessageHistory 聊天记录
     * @return 聊天记录
     */
    @Override
    public List<ChatMessageHistory> selectChatMessageHistoryList(ChatMessageHistory chatMessageHistory)
    {
        return chatMessageHistoryMapper.selectChatMessageHistoryList(chatMessageHistory);
    }

    /**
     * 新增聊天记录
     * 
     * @param chatMessageHistory 聊天记录
     * @return 结果
     */
    @Override
    public int insertChatMessageHistory(ChatMessageHistory chatMessageHistory)
    {
        return chatMessageHistoryMapper.insertChatMessageHistory(chatMessageHistory);
    }

    /**
     * 修改聊天记录
     * 
     * @param chatMessageHistory 聊天记录
     * @return 结果
     */
    @Override
    public int updateChatMessageHistory(ChatMessageHistory chatMessageHistory)
    {
        return chatMessageHistoryMapper.updateChatMessageHistory(chatMessageHistory);
    }

    /**
     * 批量删除聊天记录
     * 
     * @param messageids 需要删除的聊天记录ID
     * @return 结果
     */
    @Override
    public int deleteChatMessageHistoryByIds(Long[] messageids)
    {
        return chatMessageHistoryMapper.deleteChatMessageHistoryByIds(messageids);
    }

    /**
     * 删除聊天记录信息
     * 
     * @param messageid 聊天记录ID
     * @return 结果
     */
    @Override
    public int deleteChatMessageHistoryById(Long messageid)
    {
        return chatMessageHistoryMapper.deleteChatMessageHistoryById(messageid);
    }
}

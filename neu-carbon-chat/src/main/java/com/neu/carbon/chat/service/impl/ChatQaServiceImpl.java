package com.neu.carbon.chat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.chat.mapper.ChatQaMapper;
import com.neu.carbon.chat.domain.ChatQa;
import com.neu.carbon.chat.service.IChatQaService;

/**
 * 问答管理Service业务层处理
 * 
 * @author neuedu
 * @date 2023-12-08
 */
@Service
public class ChatQaServiceImpl implements IChatQaService 
{
    @Autowired
    private ChatQaMapper chatQaMapper;

    /**
     * 查询问答管理
     * 
     * @param id 问答管理ID
     * @return 问答管理
     */
    @Override
    public ChatQa selectChatQaById(Long id)
    {
        return chatQaMapper.selectChatQaById(id);
    }

    /**
     * 查询问答管理列表
     * 
     * @param chatQa 问答管理
     * @return 问答管理
     */
    @Override
    public List<ChatQa> selectChatQaList(ChatQa chatQa)
    {
        return chatQaMapper.selectChatQaList(chatQa);
    }

    /**
     * 新增问答管理
     * 
     * @param chatQa 问答管理
     * @return 结果
     */
    @Override
    public int insertChatQa(ChatQa chatQa)
    {
        return chatQaMapper.insertChatQa(chatQa);
    }

    /**
     * 修改问答管理
     * 
     * @param chatQa 问答管理
     * @return 结果
     */
    @Override
    public int updateChatQa(ChatQa chatQa)
    {
        return chatQaMapper.updateChatQa(chatQa);
    }

    /**
     * 批量删除问答管理
     * 
     * @param ids 需要删除的问答管理ID
     * @return 结果
     */
    @Override
    public int deleteChatQaByIds(Long[] ids)
    {
        return chatQaMapper.deleteChatQaByIds(ids);
    }

    /**
     * 删除问答管理信息
     * 
     * @param id 问答管理ID
     * @return 结果
     */
    @Override
    public int deleteChatQaById(Long id)
    {
        return chatQaMapper.deleteChatQaById(id);
    }
}

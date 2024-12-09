package com.neu.carbon.chat.service;

import java.util.List;
import com.neu.carbon.chat.domain.ChatQa;

/**
 * 问答管理Service接口
 * 
 * @author neuedu
 * @date 2023-12-08
 */
public interface IChatQaService 
{
    /**
     * 查询问答管理
     * 
     * @param id 问答管理ID
     * @return 问答管理
     */
    public ChatQa selectChatQaById(Long id);

    /**
     * 查询问答管理列表
     * 
     * @param chatQa 问答管理
     * @return 问答管理集合
     */
    public List<ChatQa> selectChatQaList(ChatQa chatQa);

    /**
     * 新增问答管理
     * 
     * @param chatQa 问答管理
     * @return 结果
     */
    public int insertChatQa(ChatQa chatQa);

    /**
     * 修改问答管理
     * 
     * @param chatQa 问答管理
     * @return 结果
     */
    public int updateChatQa(ChatQa chatQa);

    /**
     * 批量删除问答管理
     * 
     * @param ids 需要删除的问答管理ID
     * @return 结果
     */
    public int deleteChatQaByIds(Long[] ids);

    /**
     * 删除问答管理信息
     * 
     * @param id 问答管理ID
     * @return 结果
     */
    public int deleteChatQaById(Long id);
}

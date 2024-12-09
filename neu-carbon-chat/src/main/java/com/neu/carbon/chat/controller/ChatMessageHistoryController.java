package com.neu.carbon.chat.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.enums.BusinessType;
import com.neu.carbon.chat.domain.ChatMessageHistory;
import com.neu.carbon.chat.service.IChatMessageHistoryService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 聊天记录Controller
 *
 * @author neuedu
 * @date 2023-12-11
 */
@Api(tags = {"聊天记录"})
@RestController
@RequestMapping("/chat/history")
public class ChatMessageHistoryController extends BaseController
{
    @Autowired
    private IChatMessageHistoryService chatMessageHistoryService;

    /**
     * 查询聊天记录列表
     */
    @GetMapping("/list")
    @ApiOperation("查询聊天记录列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ChatMessageHistory.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(ChatMessageHistory chatMessageHistory)
    {
        startPage();
        List<ChatMessageHistory> list = chatMessageHistoryService.selectChatMessageHistoryList(chatMessageHistory);
        return getDataTable(list);
    }

    /**
     * 导出聊天记录列表
     */
    @ApiOperation("导出聊天记录列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @Log(title = "聊天记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ChatMessageHistory chatMessageHistory)
    {
        List<ChatMessageHistory> list = chatMessageHistoryService.selectChatMessageHistoryList(chatMessageHistory);
        ExcelUtil<ChatMessageHistory> util = new ExcelUtil<ChatMessageHistory>(ChatMessageHistory.class);
        return util.exportExcel(list, "history");
    }

    /**
     * 获取聊天记录详细信息
     */
    @ApiOperation("获取聊天记录详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ChatMessageHistory.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public TableDataInfo getInfo(@PathVariable("id") Long userId)
    {
        List<ChatMessageHistory> list = chatMessageHistoryService.selectChatMessageHistoryList(new ChatMessageHistory(){{
            setId(userId);
        }});
        return getDataTable(list);
    }

    /**
     * 新增聊天记录
     */
    @ApiOperation("新增聊天记录")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @Log(title = "聊天记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatMessageHistory chatMessageHistory)
    {
        return toAjax(chatMessageHistoryService.insertChatMessageHistory(chatMessageHistory));
    }

    /**
     * 修改聊天记录
     */
    @ApiOperation("修改聊天记录")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @Log(title = "聊天记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatMessageHistory chatMessageHistory)
    {
        return toAjax(chatMessageHistoryService.updateChatMessageHistory(chatMessageHistory));
    }

    /**
     * 删除聊天记录
     */
    @ApiOperation("删除聊天记录")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @Log(title = "聊天记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{messageids}")
    public AjaxResult remove(@PathVariable Long[] messageids)
    {
        return toAjax(chatMessageHistoryService.deleteChatMessageHistoryByIds(messageids));
    }
}

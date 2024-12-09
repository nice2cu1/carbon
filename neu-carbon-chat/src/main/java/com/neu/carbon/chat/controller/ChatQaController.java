package com.neu.carbon.chat.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.chat.domain.ChatQa;
import com.neu.carbon.chat.mapper.ChatQaMapper;
import com.neu.carbon.chat.service.IChatQaService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 问答管理Controller
 *
 * @author neuedu
 * @date 2023-12-08
 */
@Api(tags = {"问答管理"})
@RestController
@RequestMapping("/chat/qa")
public class ChatQaController extends BaseController {
    @Autowired
    private IChatQaService chatQaService;

    @Autowired
    ChatQaMapper chatQaMapper;

    /**
     * 查询问答管理列表
     */
    @GetMapping("/list")
    @ApiOperation("查询问答管理列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ChatQa.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(ChatQa chatQa) {
        startPage();
        List<ChatQa> list = chatQaService.selectChatQaList(chatQa);
        return getDataTable(list);
    }

    /**
     * 导出问答管理列表
     */
    @ApiOperation("导出问答管理列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('chat:qa:export')")
    @Log(title = "问答管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ChatQa chatQa) {
        List<ChatQa> list = chatQaService.selectChatQaList(chatQa);
        ExcelUtil<ChatQa> util = new ExcelUtil<ChatQa>(ChatQa.class);
        return util.exportExcel(list, "qa");
    }

    /**
     * 获取问答管理详细信息
     */
    @ApiOperation("获取问答管理详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ChatQa.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(chatQaService.selectChatQaById(id));
    }

    /**
     * 新增问答管理
     */
    @ApiOperation("新增问答管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('chat:qa:add')")
    @Log(title = "问答管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatQa chatQa) {
        return toAjax(chatQaService.insertChatQa(chatQa));
    }

    /**
     * 修改问答管理
     */
    @ApiOperation("修改问答管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('chat:qa:edit')")
    @Log(title = "问答管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatQa chatQa) {
        return toAjax(chatQaService.updateChatQa(chatQa));
    }

    /**
     * 删除问答管理
     */
    @ApiOperation("删除问答管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('chat:qa:remove')")
    @Log(title = "问答管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(chatQaService.deleteChatQaByIds(ids));
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<ChatQa> util = new ExcelUtil<ChatQa>(ChatQa.class);
        return util.importTemplateExcel("问答模板");
    }

    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ChatQa> util = new ExcelUtil<ChatQa>(ChatQa.class);
        List<ChatQa> userList = util.importExcel(file.getInputStream());
        for (ChatQa chatQa : userList) {
            chatQaService.insertChatQa(chatQa);
        }
        return AjaxResult.success("操作成功");
    }

    @GetMapping("/getAnswer/{question}")
    public AjaxResult getAnswer(@PathVariable("question") String question) {
        List<ChatQa> list = chatQaService.selectChatQaList(new ChatQa());
        return AjaxResult.success(
                        list.stream().filter(it -> it.getQuestion().contains(question))
                                .collect(Collectors.toList())
                );

    }
}

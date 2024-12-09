package com.neu.carbon.chat.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 聊天记录对象 chat_message_history
 *
 * @author neuedu
 * @date 2023-12-11
 */
@ApiModel("聊天记录")
public class ChatMessageHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long messageid;

    /** 身份信息 */
    @ApiModelProperty("身份信息")
    @Excel(name = "身份信息")
    private String identity;

    /** 消息事件 */
    @ApiModelProperty("消息事件")
    @Excel(name = "消息事件")
    private String event;

    /** 收件人 */
    @ApiModelProperty("收件人")
    @Excel(name = "收件人")
    private String recipient;

    /** 消息内容 */
    @ApiModelProperty("消息内容")
    @Excel(name = "消息内容")
    private String content;

    /** 发送人名称 */
    @ApiModelProperty("发送人名称")
    @Excel(name = "发送人名称")
    private String sendname;

    /** 发送人头像 */
    @ApiModelProperty("发送人头像")
    @Excel(name = "发送人头像")
    private String senderavatar;

    /** 用户ID */
    @ApiModelProperty("用户ID")
    private Long id;

    /** 未读消息数量 */
    @ApiModelProperty("未读消息数量")
    @Excel(name = "未读消息数量")
    private Long number;

    /** 消息发送时间 */
    @ApiModelProperty(value="消息发送时间",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "消息发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date messageTime;

    public void setMessageid(Long messageid)
    {
        this.messageid = messageid;
    }

    public Long getMessageid()
    {
        return messageid;
    }
    public void setIdentity(String identity)
    {
        this.identity = identity;
    }

    public String getIdentity()
    {
        return identity;
    }
    public void setEvent(String event)
    {
        this.event = event;
    }

    public String getEvent()
    {
        return event;
    }
    public void setRecipient(String recipient)
    {
        this.recipient = recipient;
    }

    public String getRecipient()
    {
        return recipient;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setSendname(String sendname)
    {
        this.sendname = sendname;
    }

    public String getSendname()
    {
        return sendname;
    }
    public void setSenderavatar(String senderavatar)
    {
        this.senderavatar = senderavatar;
    }

    public String getSenderavatar()
    {
        return senderavatar;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Long getNumber()
    {
        return number;
    }
    public void setMessageTime(Date messageTime)
    {
        this.messageTime = messageTime;
    }

    public Date getMessageTime()
    {
        return messageTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageid", getMessageid())
            .append("identity", getIdentity())
            .append("event", getEvent())
            .append("recipient", getRecipient())
            .append("content", getContent())
            .append("sendname", getSendname())
            .append("senderavatar", getSenderavatar())
            .append("id", getId())
            .append("number", getNumber())
            .append("messageTime", getMessageTime())
            .toString();
    }
}

package com.neu.carbon.chat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 问答管理对象 chat_qa
 * 
 * @author neuedu
 * @date 2023-12-08
 */
@ApiModel("问答管理")
public class ChatQa extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 问题 */
    @ApiModelProperty("问题")
    @Excel(name = "问题")
    private String question;

    /** 答案 */
    @ApiModelProperty("答案")
    @Excel(name = "答案")
    private String answer;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQuestion(String question) 
    {
        this.question = question;
    }

    public String getQuestion() 
    {
        return question;
    }
    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("question", getQuestion())
            .append("answer", getAnswer())
            .toString();
    }
}

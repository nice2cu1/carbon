package com.neu.carbon.chat.domain;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.neu.common.enums.WSMessageEvent;
import com.neu.common.enums.WSMessageIdentity;
import lombok.Data;

@Data
public class WSMessage {
    private WSMessageIdentity identity;
    private WSMessageEvent event;
    private String recipient;
    private String content;
    private String sendName;
    private String senderAvatar;
    private Integer id;
    private Integer number;
    private DateTime messageTime;
    public String getMessageStr(){
        return JSONUtil.toJsonStr(this);
    }

}

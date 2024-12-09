package com.neu.carbon.chat.domain;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import cn.hutool.core.date.DateTime;
import javax.websocket.*;

@Data
public class WsUserInfo {
    private Integer userId;
    private String userName;
    private String userAvatar;
    private String message;
    private Integer number;
    private DateTime recentTime;
    private Session session;
    public String getMessageStr(){
        return JSONUtil.toJsonStr(this);
    }
}

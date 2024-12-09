package com.neu.carbon.chat.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.neu.carbon.chat.domain.WSMessage;
import com.neu.carbon.chat.domain.WsUserInfo;
import com.neu.common.enums.WSMessageEvent;
import com.neu.common.enums.WSMessageIdentity;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/websocket")
public class MyWebSocket {
    // 用户 Session
    private final static Map<String, Session> USER_SESSION_MAP = new ConcurrentHashMap<>();
    // 客服 Session
    private final static Map<String, Session> ADMIN_SESSION_MAP = new ConcurrentHashMap<>();
    private final static Map<Integer, WsUserInfo> ONLINE_MESSAGE = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        // 封装一条消息
        Date date = new Date();
        DateTime time = new DateTime(date);
        WSMessage wsMessage = new WSMessage() {{
            setIdentity(WSMessageIdentity.ROBOT);
            setEvent(WSMessageEvent.AUTHENTICATION);
            setSenderAvatar("机器人");
            setSendName("在线客服");
            setContent("成功连接服务器");
            setMessageTime(time);
            setRecipient("");
        }};

        RemoteEndpoint.Async remote = session.getAsyncRemote();

        // 发送消息
        remote.sendText(wsMessage.getMessageStr());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        //移除相对应的session
        removeTalkTarget(session);
        // TODO 通知后台更新用户列表

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        if (!JSONUtil.isJson(message)) return;

        // 将 JsonStr 转换成 WSMessage Bean
        WSMessage msg = msgStrToBean(message);
        if (msg == null || msg.getEvent() == null) return;

        // 解析消息类型 MessageEvent: Authentication or Talk
        if (msg.getEvent() == WSMessageEvent.AUTHENTICATION) {
            // 为Session列表新增用户
            addTalkTarget(session, msg);
            getUserOnlineList();
        } else if (msg.getEvent() == WSMessageEvent.GETUSERLIST) {
            getUserOnlineList();
        } else if (msg.getEvent() == WSMessageEvent.TALK) {
            // 处理时间问题
            if (msg.getMessageTime() == null) {
                String messageTime = JSONUtil.parseObj(message).getStr("messageTime");
                msg.setMessageTime(DateUtil.parse(messageTime));
            }
            // 转发消息
            relayMessage(msg, session);
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        USER_SESSION_MAP.remove(session.getId());
        ADMIN_SESSION_MAP.remove(session.getId());
        for (Map.Entry<Integer, WsUserInfo> entry : ONLINE_MESSAGE.entrySet()) {
            if (entry.getValue().getSession() == session) {
                ONLINE_MESSAGE.remove(entry.getKey());
            }
        }
        getUserOnlineList();
        System.out.println("发生错误 sessionID 用户退出" + session.getId());
    }

    /**
     * sendAll
     * 群发消息给客服
     *
     * @param message message
     */
    private void sendAll(String message) {
        for (Session s : ADMIN_SESSION_MAP.values()) {
            // 获取同步锁
            final RemoteEndpoint.Basic remote = s.getBasicRemote();
            // 发送消息
            try {
                remote.sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * sendOne
     * 私聊消息
     *
     * @param msg message
     */
    private void sendOne(Session recipientSession, WSMessage msg) {
        // 获取异步锁
        RemoteEndpoint.Async asyncRemote = recipientSession.getAsyncRemote();
        // 发送消息
        asyncRemote.sendText(msg.getMessageStr());
    }

    /**
     * sendOne
     * 转发消息
     *
     * @param msg message
     */
    private void relayMessage(WSMessage msg, Session session) {
        // 获取接收人
        String recipient = msg.getRecipient();
        if (msg.getContent() == null) return;
        // 如果是用户，就在后台客服列表中找，反之亦然
        if (msg.getIdentity() == WSMessageIdentity.USER) {
            // 更新在线用户最新消息列表
            updateUserOnlineList(msg, session);
            //将消息列表群发给客服
            sendAll(msg.getMessageStr());
        } else if (msg.getIdentity() == WSMessageIdentity.ADMIN) {
            sendOne(ONLINE_MESSAGE.get(Integer.parseInt(recipient)).getSession(), msg);
        }
        getUserOnlineList();
    }

    /**
     * addTalkTarget
     * 添加用户列表用户
     *
     * @param msg msg
     */
    private void addTalkTarget(Session session, WSMessage msg) {
        if (msg.getIdentity() == WSMessageIdentity.ADMIN) {
            ADMIN_SESSION_MAP.put(session.getId(), session);
            //   验证客服身份 发送用户列表
            getUserOnlineList();

            System.out.println("新增客服" + msg.getId() + " " + session.getId());
        } else if (msg.getIdentity() == WSMessageIdentity.USER) {
            // USER_SESSION_MAP 列表增加 session
            USER_SESSION_MAP.put(session.getId(), session);
            // 管理前端在线列表添加用户
            updateUserOnlineList(msg, session);
            // 新用户上线需要通知客服
            getUserOnlineList();
            System.out.println("新增用户" + msg.getId() + " " + session.getId());
        }
    }

    /**
     * getUserOnlineList
     * 获取用户列表
     */
    private void getUserOnlineList() {
        WSMessage wsMessage = new WSMessage() {{
            setEvent(WSMessageEvent.ADDUSER);
            setContent(JSONUtil.toJsonStr(ONLINE_MESSAGE.values()));
        }};
        sendAll(wsMessage.getMessageStr());
    }

    /**
     * updateUserOnlineList
     * 更新用户列表数据
     */
    private void updateUserOnlineList(WSMessage msg, Session session) {
        // 更新一条消息在线用户最近消息列表
        ONLINE_MESSAGE.put(msg.getId(), new WsUserInfo() {{
            setUserId(msg.getId());
            setUserName(msg.getSendName());
            setMessage(msg.getContent());
            setRecentTime(msg.getMessageTime());
            setUserAvatar(msg.getSenderAvatar());
            setSession(session);
            // TODO 查表获取未读消息数量
            setNumber(1);
        }});
        // 将新的表格发送给客服
        getUserOnlineList();
    }

    /**
     * removeTalkTarget
     * 移除用户列表用户
     *
     * @param session session
     */
    private void removeTalkTarget(Session session) {
        System.out.println("移除sessionID" + session.getId());
        ADMIN_SESSION_MAP.remove(session.getId());
        USER_SESSION_MAP.remove(session.getId());
        for (Map.Entry<Integer, WsUserInfo> entry : ONLINE_MESSAGE.entrySet()) {
            if (entry.getValue().getSession() == session) {
                ONLINE_MESSAGE.remove(entry.getKey());
            }
        }
        getUserOnlineList();
    }

    /**
     * removeTalkTarget
     * Json字符串转 WSMessage Bean对象
     *
     * @param msg msg
     */
    private WSMessage msgStrToBean(String msg) {
        if (JSONUtil.isJsonObj(msg)) {
            return JSONUtil.toBean(msg, WSMessage.class);
        }
        return null;
    }
}


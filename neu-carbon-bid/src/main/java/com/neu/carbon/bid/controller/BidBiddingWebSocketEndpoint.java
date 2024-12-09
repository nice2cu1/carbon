package com.neu.carbon.bid.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.neu.common.enums.WSMessageEvent;
import com.neu.common.enums.WSMessageIdentity;
import org.springframework.stereotype.Component;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/bid/news")
public class BidBiddingWebSocketEndpoint {
    private final static Set<Session> SESSION_SET = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        SESSION_SET.add(session);
    }

    public void sendAll(String message) {
        for (Session s : SESSION_SET) {
            final RemoteEndpoint.Basic remote = s.getBasicRemote();
            // 发送消息
            try {
                remote.sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        SESSION_SET.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        SESSION_SET.remove(session);
    }
}

package com.cucci.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * webSocket
 *
 * @author shenyw
 **/
@Slf4j
@Component
@ServerEndpoint("/web/socket/sever/{userId}")
public class WebSocketServer {

    public static ConcurrentHashMap<String, Session> cache = new ConcurrentHashMap<>();

    /**
     * 成功建立连接时调用的方法
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        // 将该客户端的唯一的标识保存起来，以便于向客户端推送消息
        log.info("成功与客户端建立连接");
        cache.put(userId, session);
    }

    /**
     * 客户端关闭连接时调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        // 将该客户端的唯一的标识删除
        log.info("客户端主动关闭了连接");
        cache.remove(userId);
    }

    /**
     * 收到客户端调用的方法
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        // 收到客户端发送的消息，回调事件
    }

    /**
     * 请求异常
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, @PathParam("userId") String userId, Throwable throwable) {
        // 将该客户端的唯一的标识删除
        log.error("通信异常 异常原因", throwable);
        cache.remove(userId);
    }

    /**
     * 推送给某一个人
     *
     * @param userId
     * @param message
     * @throws IOException
     */
    public static void inform(String userId, String message) {
        try {
            Session session = cache.get(userId);
            if (session != null) {
                session.getBasicRemote().sendText(message);
            } else {
                log.warn("发送消息失败，获取不到对应的session");
            }
        } catch (Exception e) {
            log.error("发送消息失败", e);
        }
    }

    /**
     * 给所有客户端发送消息
     *
     * @param message
     */
    public static void boardcast(String message) {
        log.debug("需要通知的客户端数：" + cache.size());
        for (Map.Entry<String, Session> entry : cache.entrySet()) {
            try {
                entry.getValue().getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("发送消息失败", e);
            }
        }
    }
}

package com.cucci.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket 配置
 *
 * @author shenyw@citycloud.com.cn
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter init() {
        return new ServerEndpointExporter();
    }
}

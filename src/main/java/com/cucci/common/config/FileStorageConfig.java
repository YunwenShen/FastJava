package com.cucci.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Author: shenyw
 * @Date: 2020/6/20 17:12
 */
@ConfigurationProperties(prefix = "file.storage")
@Data
@Component
public class FileStorageConfig {

    /**
     * 实现方式
     */
    private String type;

    /**
     * 主机名
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 账号
     */
    private String password;

    /**
     * 根目录
     */
    private String rootDir;

    /**
     * 其他配置信息
     */
    private Properties properties;
}

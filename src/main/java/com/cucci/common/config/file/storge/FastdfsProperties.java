package com.cucci.common.config.file.storge;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: shenyw
 * @Date: 2020/7/18 11:00
 */
@Data
@ApiModel("fastdfs配置")
@Component
@ConfigurationProperties(prefix = "file.storage.fastdfs")
public class FastdfsProperties {

    private String trackerServers;

    private int connectTimeoutInSeconds;

    private int networkTimeoutInSeconds;

    private String httpAntiStealToken;

    private String charset;

    private int httpTrackerHttpPort;
}

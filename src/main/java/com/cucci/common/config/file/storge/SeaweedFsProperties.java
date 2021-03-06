package com.cucci.common.config.file.storge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: shenyw
 * @Date: 2020/7/18 11:00
 */
@Data
@ApiModel("")
@Component
@ConfigurationProperties(prefix = "file.storage.seaweed")
@ConditionalOnProperty(value = "file.storage.strategy", havingValue = "seaweed")
public class SeaweedFsProperties {

    @ApiModelProperty("主机地址")
    private String host;

    @ApiModelProperty("主机端口")
    private int port;
}

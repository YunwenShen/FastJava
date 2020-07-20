package com.cucci.common.config.file.storge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * oss配置
 *
 * @Author: shenyw
 * @Date: 2020/7/18 10:59
 */
@Data
@Component
@ApiModel("oss配置")
@ConfigurationProperties(prefix = "file.storage.oss")
public class OssProperties {

    @ApiModelProperty("地址")
    private String server;

    @ApiModelProperty("账号")
    private String accessKey;

    @ApiModelProperty("密钥")
    private String accessSecret;

    @ApiModelProperty("bucket")
    private String bucket;
}

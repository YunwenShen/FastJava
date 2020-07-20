package com.cucci.common.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.cucci.common.config.file.storge.FastdfsProperties;
import com.cucci.common.config.file.storge.OssProperties;
import com.cucci.common.config.file.storge.SeaweedFsProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.anumbrella.seaweedfs.core.ConnectionProperties;
import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.FileTemplate;
import org.csource.fastdfs.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @Author: shenyw
 * @Date: 2020/6/20 17:12
 */
@Data
@Component
@Configuration
@Slf4j
public class FileStorageConfiguration {

    @ConditionalOnBean(OssProperties.class)
    @Bean
    public OSSClient initOSSClient(OssProperties ossProperties) {
        log.info("正在初始化oss");
        ClientConfiguration conf = new ClientConfiguration();
        List<String> cnameExcludeList = new ArrayList<>();
        String excludeItem = ossProperties.getServer();
        cnameExcludeList.add(excludeItem);
        conf.setCnameExcludeList(cnameExcludeList);
        CredentialsProvider credentials = new DefaultCredentialProvider(ossProperties.getAccessKey(), ossProperties.getAccessSecret());
        OSSClient ossClient = new OSSClient(ossProperties.getServer(), credentials, conf);
        log.info("oss初始化成功");
        return ossClient;
    }

    @ConditionalOnBean(FastdfsProperties.class)
    @Bean
    public StorageClient initFastdfs(FastdfsProperties fastdfsProperties) {
        log.info("fastdfs正在初始化");
        Properties properties = new Properties();
        properties.setProperty("fastdfs.tracker_servers", fastdfsProperties.getTrackerServers());
        properties.setProperty("fastdfs.connect_timeout_in_seconds", String.valueOf(fastdfsProperties.getConnectTimeoutInSeconds()));
        properties.setProperty("fastdfs.network_timeout_in_seconds", String.valueOf(fastdfsProperties.getNetworkTimeoutInSeconds()));
        properties.setProperty("fastdfs.http_anti_steal_token", String.valueOf(fastdfsProperties.getHttpAntiStealToken()));
        properties.setProperty("fastdfs.charset", fastdfsProperties.getCharset());
        properties.setProperty("fastdfs.http_tracker_http_port", String.valueOf(fastdfsProperties.getHttpTrackerHttpPort()));
        try {
            ClientGlobal.initByProperties(properties);
            TrackerClient client = new TrackerClient();
            TrackerServer server = Objects.requireNonNull(client.getTrackerServer());
            StorageServer storageServer = Objects.requireNonNull(client.getStoreStorage(server));
            StorageClient storageClient = new StorageClient(server, storageServer);
            log.info("fastdfs初始化成功");
            return storageClient;
        } catch (Exception e) {
            log.error("fastdfs初始化失败", e);
            return null;
        }
    }

    @ConditionalOnBean(SeaweedFsProperties.class)
    @Bean
    public FileTemplate initFileTemplate(SeaweedFsProperties seaweedFsProperties) {
        log.info("正在初始化seaweedfs客户端");
        FileSource fileSource = new FileSource();
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .host(seaweedFsProperties.getHost())
                .port(seaweedFsProperties.getPort())
                .maxConnection(100).build();
        fileSource.setProperties(connectionProperties);
        fileSource.startup();
        FileTemplate fileTemplate = new FileTemplate(fileSource.getConnection());
        log.info("seaweedfs初始化成功");
        return fileTemplate;
    }
}

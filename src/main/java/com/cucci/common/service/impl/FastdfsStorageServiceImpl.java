package com.cucci.common.service.impl;

import com.cucci.common.config.file.storge.FastdfsProperties;
import com.cucci.common.service.FileStorageService;
import com.cucci.common.vo.FileStorageEntity;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Fastdfs实现文件存储
 *
 * @Author: shenyw
 * @Date: 2020/6/20 21:48
 */
@ConditionalOnProperty(value = "file.storage.strategy", havingValue = "fastdfs")
@Service
public class FastdfsStorageServiceImpl implements FileStorageService {

    private final FastdfsProperties fastdfsProperties;
    private final StorageClient storageClient;

    public FastdfsStorageServiceImpl(FastdfsProperties fastdfsProperties, StorageClient storageClient) {
        this.fastdfsProperties = fastdfsProperties;
        this.storageClient = storageClient;
    }

    @Override
    public String save(FileStorageEntity entity) throws Exception {
        byte[] bytes = new byte[entity.getInputStream().available()];
        entity.getInputStream().read(bytes);
        String[] fileExt = entity.getFileName().split("\\.");
        NameValuePair[] pairs = new NameValuePair[]{
                new NameValuePair("a", "a")
        };
        String[] strings = storageClient.upload_file(bytes, fileExt[fileExt.length - 1], pairs);
        String host = fastdfsProperties.getTrackerServers().split(",")[0].split(":")[0];
        int port = fastdfsProperties.getHttpTrackerHttpPort();
        return "http://" + host.split(":")[0] + ":" + port + "/" + strings[0] + "/" + strings[1];
    }

    @Override
    public boolean delete(String url) {
        return false;
    }
}

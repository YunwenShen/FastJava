package com.cucci.common.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.cucci.common.config.file.storge.OssProperties;
import com.cucci.common.service.FileStorageService;
import com.cucci.common.vo.FileStorageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Oss实现文件存储
 *
 * @Author: shenyw
 * @Date: 2020/6/20 17:08
 */
@Service
@ConditionalOnProperty(value = "file.storage.strategy", havingValue = "oss")
@Slf4j
public class OssStorageServiceImpl implements FileStorageService {

    private OSSClient client;
    private OssProperties ossProperties;

    public OssStorageServiceImpl(OSSClient client, OssProperties ossProperties) {
        this.ossProperties = ossProperties;
        this.client = client;
    }

    @Override
    public String save(FileStorageEntity entity) throws Exception {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(entity.getInputStream().available());
        client.putObject(ossProperties.getBucket(), entity.getFileName(), entity.getInputStream(), meta);
        return "http://" + ossProperties.getBucket() + "." + ossProperties.getServer() + "/" + entity.getFileName();
    }

    @Override
    public boolean delete(String url) {
        String dir = url.replace("http://" + ossProperties.getBucket() + "." + ossProperties.getBucket() + "/", "");
        client.deleteObject(ossProperties.getBucket(), dir);
        return true;
    }
}

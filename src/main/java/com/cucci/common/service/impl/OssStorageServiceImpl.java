package com.cucci.common.service.impl;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.ObjectMetadata;
import com.cucci.common.config.FileStorageConfig;
import com.cucci.common.service.FileStorageService;
import com.cucci.common.vo.FileStorageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Oss实现文件存储
 *
 * @Author: shenyw
 * @Date: 2020/6/20 17:08
 */
@Service
@ConditionalOnExpression("'${file.storage.type}'.equals('oss')")
@Slf4j
public class OssStorageServiceImpl implements FileStorageService, CommandLineRunner {

    private final FileStorageConfig fileStorageConfig;
    private OSSClient client;

    public OssStorageServiceImpl(FileStorageConfig fileStorageConfig) {
        this.fileStorageConfig = fileStorageConfig;
    }

    @Override
    public String save(FileStorageEntity entity) throws Exception {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(entity.getInputStream().available());
        client.putObject(fileStorageConfig.getRootDir(), entity.getFileName(), entity.getInputStream(), meta);
        return "http://" + fileStorageConfig.getRootDir() + "." + fileStorageConfig.getHost() + "/" + entity.getFileName();
    }

    @Override
    public boolean delete(String url) {
        String dir = url.replace("http://" + fileStorageConfig.getRootDir() + "." + fileStorageConfig.getHost() + "/", "");
        client.deleteObject(fileStorageConfig.getRootDir(), dir);
        return true;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("正在初始化oss");
        ClientConfiguration conf = new ClientConfiguration();
        List<String> cnameExcludeList = new ArrayList<>();
        String excludeItem = fileStorageConfig.getHost();
        cnameExcludeList.add(excludeItem);
        conf.setCnameExcludeList(cnameExcludeList);
        CredentialsProvider credentials = new DefaultCredentialProvider(fileStorageConfig.getUsername(), fileStorageConfig.getPassword());
        this.client = new OSSClient(fileStorageConfig.getHost(), credentials, conf);
        log.debug("oss初始化成功");
    }
}

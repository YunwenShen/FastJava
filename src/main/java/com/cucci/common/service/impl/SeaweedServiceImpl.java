package com.cucci.common.service.impl;

import com.cucci.common.service.FileStorageService;
import com.cucci.common.vo.FileStorageEntity;
import net.anumbrella.seaweedfs.core.FileTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @Author: shenyw
 * @Date: 2020/7/20 13:33
 */
@Service
@ConditionalOnProperty(value = "file.storage.strategy", havingValue = "seaweed")
public class SeaweedServiceImpl implements FileStorageService {

    private FileTemplate fileTemplate;

    public SeaweedServiceImpl(FileTemplate fileTemplate) {
        this.fileTemplate = fileTemplate;
    }

    @Override
    public String save(FileStorageEntity entity) throws Exception {
        return fileTemplate.saveFileByStream(entity.getFileName(), entity.getInputStream()).getFileUrl();
    }

    @Override
    public boolean delete(String url) {
        return false;
    }
}

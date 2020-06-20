package com.cucci.common.service;

import com.cucci.common.vo.FileStorageEntity;

import java.io.IOException;

/**
 * 文件存储服务
 *
 * @Author: shenyw
 * @Description:
 * @Date: 2020/6/20 17:00
 * @Version: 1.0
 */
public interface FileStorageService {

    /**
     * 保存文件
     *
     * @param entity
     * @return url链接
     */
    String save(FileStorageEntity entity) throws IOException, Exception;

    /**
     * 删除文件
     *
     * @param url
     * @return
     */
    boolean delete(String url);
}

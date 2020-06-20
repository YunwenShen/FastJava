package com.cucci.common.vo;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 文件存储统一入参
 *
 * @author shenyw
 */
@Data
public class FileStorageEntity implements Serializable {

    private static final long serialVersionUID = 4915821460302429504L;

    /**
     * 文件流
     */
    private InputStream inputStream;

    /**
     * 文件名称
     */
    private String fileName;

}

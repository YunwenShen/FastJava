package com.cucci.common.caches;

import com.cucci.common.constants.CacheConstant;

import java.nio.charset.Charset;

/**
 * 缓存工具类
 *
 * @author shenyw
 **/
public class CacheUtils {

    /**
     * 拼接缓存key
     *
     * @param type 缓存类型
     * @param key  缓存key
     * @return 真正用于redis的缓存key
     */
    public static byte[] generateKey(String type, String key) {
        if (type == null) {
            throw new IllegalArgumentException("the type of key: " + key + "must not be null");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(type).append(CacheConstant.TYPE_KEY_SEPARATOR).append(key);
        return sb.toString().getBytes(Charset.forName("UTF-8"));
    }
}

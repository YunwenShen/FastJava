package com.cucci.common.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 缓存相关常量
 *
 * @author shenyw
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class CacheConstant {

    /**
     * redis默认过期时间
     */
    public static final int DEFAULT_EXPIRE = 3600;

    /**
     * 缓存类型和缓存key分隔符
     */
    public static final String TYPE_KEY_SEPARATOR = ":";
}

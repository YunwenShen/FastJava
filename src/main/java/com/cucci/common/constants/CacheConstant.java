package com.cucci.common.constants;

/**
 * 缓存相关常量
 *
 * @author shenyw
 **/
public final class CacheConstant {

    private CacheConstant() {
    }

    /**
     * redis默认过期时间
     */
    public static final int DEFAULT_EXPIRE = 3600;

    /**
     * 缓存类型和缓存key分隔符
     */
    public static final String TYPE_KEY_SEPARATOR = ":";
}

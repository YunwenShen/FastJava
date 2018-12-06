package com.cucci.common.caches;

/**
 * 缓存操作接口
 *
 * @author shenyw
 **/
public interface ICacheService {

    /**
     * 从缓存中获取对象
     *
     * @param type 缓存分类
     * @param key  缓存key
     * @param c    对象类型
     * @param <T>  对象类型
     * @return 缓存结果
     */
    <T> T get(final String type, final String key, final Class<T> c);

    /**
     * 缓存结果
     *
     * @param type  缓存分类
     * @param key   缓存key
     * @param value 内容
     */
    void set(final String type, final String key, final Object value);

    /**
     * 缓存结果
     *
     * @param type          缓存分类
     * @param key           缓存key
     * @param expireSeconds 缓存有效期（秒）
     * @param value         内容
     */
    void set(final String type, final String key, final int expireSeconds, final Object value);


    /**
     * 清除缓存
     *
     * @param type 缓存分类
     * @param key  缓存key
     * @return 删除成功返回大于0的整数
     */
    Long del(final String type, final String key);

    /**
     * 检测缓存是否存在
     *
     * @param type 缓存分类
     * @param key  缓存key
     * @return boolean
     */
    Boolean exists(final String type, final String key);

    /**
     * 自增缓存中的key
     *
     * @param type 缓存分类
     * @param key  缓存key
     * @return 进行自增之后的结果
     */
    Long incr(final String type, final String key);

    /**
     * 为缓存设置过期时间
     *
     * @param type    缓存分类
     * @param key     缓存key
     * @param seconds 过期时间
     * @return 返回1表示设置成功，返回0表示key不存在（低于 Redis2.1.3对于过期的Key不会更新过期时间）
     */
    Long expire(final String type, final String key, final int seconds);

}

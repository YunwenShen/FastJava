package com.cucci.common.caches.serializer;

/**
 * 自定义序列化接口
 *
 * @author shenyw@citycloud.com.cn
 **/
public interface ISerializer {

    /**
     * 序列化对象, 不包含class信息
     **/
    byte[] serialize(Object object);

    /**
     * 反序列化对象
     **/
    <T> T deserialize(byte[] byteArray, Class<T> clazz);
}

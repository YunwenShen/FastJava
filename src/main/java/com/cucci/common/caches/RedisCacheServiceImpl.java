package com.cucci.common.caches;

import com.cucci.common.caches.serializer.ISerializer;
import com.cucci.common.caches.serializer.JdkSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

import static com.cucci.common.caches.CacheUtils.generateKey;

/**
 * redis操作接口实现类
 *
 * @author shenyw
 **/
@SuppressWarnings("unchecked")
@Service
public class RedisCacheServiceImpl implements ICacheService {

    private ISerializer serializer = new JdkSerializer();

    @Resource
    private JedisPool jedisPool;

    @Override
    public <T> T get(String type, String key, Class<T> c) {
        return execute(jedis -> {
            byte[] bytes = jedis.get(generateKey(type, key));
            return serializer.deserialize(bytes, c);
        });
    }

    @Override
    public void set(String type, String key, Object value) {
        execute(jedis -> {
            jedis.set(generateKey(type, key), serializer.serialize(value));
        });
    }

    @Override
    public void set(String type, String key, int expireSeconds, Object value) {
        execute(jedis -> {
            jedis.setex(generateKey(type, key), expireSeconds, serializer.serialize(value));
        });
    }


    @Override
    public Long del(String type, String key) {
        return execute(jedis -> {
            return jedis.del(generateKey(type, key));
        });
    }

    @Override
    public Boolean exists(String type, String key) {
        return execute(jedis -> {
            return jedis.exists(generateKey(type, key));
        });
    }

    @Override
    public Long incr(String type, String key) {
        return execute(jedis -> {
            return jedis.incr(generateKey(type, key));
        });
    }

    @Override
    public Long expire(String type, String key, int seconds) {
        return execute(jedis -> {
            return jedis.expire(generateKey(type, key), seconds);
        });
    }

    /**
     * 有返回结果的回调接口定义。
     */
    private interface JedisAction<T> {
        T action(BinaryJedis jedis);
    }

    /**
     * 执行有返回结果的redisAction
     *
     * @param jedisAction 回调接口
     * @param <T>         返回类型
     * @return 返回结果
     */
    private <T> T execute(JedisAction<T> jedisAction) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedisAction.action(jedis);
        } catch (Exception e) {
            StringBuffer sb = new StringBuffer("redis连接池[");
            sb.append(jedisPool.getNumActive()).append(",");
            sb.append(jedisPool.getNumIdle()).append(",");
            sb.append(jedisPool.getNumWaiters()).append("]");
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }

    /**
     * 无返回结果的回调接口定义。
     */
    private interface JedisActionNoResult {
        void action(BinaryJedis jedis);
    }

    /**
     * 执行无返回结果的action。
     */
    private void execute(JedisActionNoResult jedisAction) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedisAction.action(jedis);
        } finally {
            jedis.close();
        }
    }
}

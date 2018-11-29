package com.cucci.common.caches;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheServiceImplTest {

    @Resource
    private ICacheService cacheService;

    @Test
    public void get() {
        Integer value = cacheService.get("系统设置", "系统参数", Integer.class);
        System.out.println(value);
    }

    @Test
    public void set() {
        cacheService.set("系统设置", "系统参数", "abc");
    }

    @Test
    public void set1() {
        cacheService.set("系统设置", "系统参数", 600, "123");
    }

    @Test
    public void del() {
        cacheService.del("系统设置", "系统参数");
    }

    @Test
    public void exists() {
        System.out.println(cacheService.exists("系统设置", "系统参数"));
    }

    @Test
    public void incr() {
        System.out.println(cacheService.incr("系统设置", "系统参数2"));
    }

    @Test
    public void expire() {
        cacheService.expire("系统设置", "系统参数", 1000);
    }
}
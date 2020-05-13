package com.cucci.common.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程池使用方式
 *
 * @author shenyw@citycloud.com.cn
 **/
public class ThreadPoolDemo {

    @Test
    public void buildPool() throws InterruptedException {
        ThreadFactory earlyWarning = new ThreadFactoryBuilder().setNameFormat("customTaxesSync-pool-%d").build();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 3, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024), earlyWarning, new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        poolExecutor.submit(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("hello world 1");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        poolExecutor.submit(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("hello world 2");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch1.countDown();
            }
        });


    }
}

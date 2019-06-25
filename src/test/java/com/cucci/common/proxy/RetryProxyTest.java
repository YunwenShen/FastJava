package com.cucci.common.proxy;

import org.junit.Test;

public class RetryProxyTest {

    @Test
    public void test() {
        IRetryProxy proxy = new RetryProxyImpl();
        IRetryProxy retryProxy = (IRetryProxy) RetryProxy.getProxy(proxy);
        retryProxy.div(3);
    }
}
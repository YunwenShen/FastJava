package com.cucci.common.proxy;

/**
 * @author shenyw
 **/
public class RetryProxyImpl implements IRetryProxy {


    @Override
    public int div(int value) {
        if (value % 2 != 0) {
            throw new RuntimeException("ddd");
        }
        return value;
    }
}

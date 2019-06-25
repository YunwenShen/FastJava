package com.cucci.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理重试
 *
 * @author shenyw
 **/
public class RetryProxy implements InvocationHandler {

    private Object subject;

    public RetryProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        int times = 0;
        while (true) {
            try {
                // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
                return method.invoke(subject, args);
            } catch (Exception e) {
                e.printStackTrace();
                times++;
                if (times >= 3) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 获取动态代理
     *
     * @param realSubject 代理对象
     */
    public static Object getProxy(Object realSubject) {
        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new RetryProxy(realSubject);
        return Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
    }

}

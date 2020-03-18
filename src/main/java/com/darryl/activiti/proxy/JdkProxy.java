package com.darryl.activiti.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: Darryl
 * @Description: jdk 动态代理
 * @Date: created in 2020/3/18 20:53
 */

public class JdkProxy implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK 动态代理来了");
        Object res = method.invoke(target, args);
        System.out.println("JDK 动态代理走了。。。");
        System.out.println("===============================");
        return res;
    }

    // 获取代理对象
    private Object getJdkProxy(Object o) {
        // 为目标对象赋值
        this.target = o;
        // 通过new proxy instance方法就可以看出是基于被代理类的接口去生产代理类的
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        Snow snow = (Snow) jdkProxy.getJdkProxy(new SnowImpl());
        snow.color("红色");
        snow.shape("八角形");
    }
}

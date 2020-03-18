package com.darryl.activiti.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: Darryl
 * @Description: CGLIB动态代理
 * @Date: created in 2020/3/18 21:11
 */

public class CglibProxy implements MethodInterceptor {

    private Object target;

    // 重写拦截方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB 动态代理开始了。。");
        Object res = method.invoke(target, objects);
        System.out.println("CGLIB 动态代理走了。。");
        return res;
    }

    // 定义获取代理对象
    public Object getCglibProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        // 设置父类，从这里就能看出CGLIB是生成指定类的子类，所以需要制定父类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        // 创建并返回对象
        Object res = enhancer.create();
        return res;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Snow snow = (Snow) cglibProxy.getCglibProxy(new SnowImpl());
        snow.shape("六角形");
    }
}

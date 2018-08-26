package com.keng.common.proxy.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public abstract class TransactionProxy implements MethodInterceptor {

    public abstract void preHandler(Method method);

    public abstract void afterHandler(Method method);

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Object thisReturn = methodProxy.invoke(o, args);

        return thisReturn;
    }
}

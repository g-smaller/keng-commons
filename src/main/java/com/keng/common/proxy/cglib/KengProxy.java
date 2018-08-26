package com.keng.common.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;

public interface KengProxy<T> {

    T createProxy();

    T createProxy(Class<T> clzz);

    void setSuperclass(Class<T> clazz);

    void registerCallback(Callback... callbacks);

    void registerCallbackFilter(CallbackFilter callbackFilter);

}

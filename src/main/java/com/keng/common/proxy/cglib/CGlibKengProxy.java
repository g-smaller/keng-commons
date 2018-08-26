package com.keng.common.proxy.cglib;


import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.List;

public abstract class CGlibKengProxy<T> implements KengProxy<T> {

    private List<Callback> callbackList = new ArrayList<Callback>(2);
    private CallbackFilter callbackFilter;
    private Class<T> superClass;

    @Override
    public T createProxy() {
        return createProxy(superClass);
    }

    @Override
    public T createProxy(Class<T> clzz) {
        Class<T> supportClass = clzz == null ? superClass : clzz;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(supportClass);
        enhancer.setCallbacks(callbackList.toArray(new Callback[callbackList.size()]));
        enhancer.setCallbackFilter(callbackFilter);
        return (T) enhancer.create();
    }

    @Override
    public void setSuperclass(Class<T> clazz) {
        this.superClass = clazz;
    }

    @Override
    public void registerCallback(Callback... callbacks) {
        if (callbacks != null && callbacks.length > 0) {
            for (Callback callback: callbacks) {
                callbackList.add(callback);
            }
        }
    }

    @Override
    public void registerCallbackFilter(CallbackFilter callbackFilter) {
        if (callbackFilter != null) {
            this.callbackFilter = callbackFilter;
        }
    }
}

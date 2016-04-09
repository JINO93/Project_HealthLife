package com.jino.zhbj.zhbj.net;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/4/6.
 */
public abstract class RequestCallBack<T> {


    public Type mType;

    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }


    public RequestCallBack()
    {
        mType = getSuperclassTypeParameter(getClass());
    }


    public abstract void onPreLoad();

    public abstract void onSuccess(T t);
    public abstract void onError(Throwable ex);
    public abstract void onFinish();
}

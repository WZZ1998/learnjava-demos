package com.wangzizhou.demo.manydemos.concurrent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VoidDemo {
    public static void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> clz = Void.class;
        Constructor<?> cs = clz.getDeclaredConstructor();
        cs.setAccessible(true);
        Void v = (Void)cs.newInstance();
        System.out.println(v);
    }
}

package com.rxcay.learnjava.demos.advanced;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @author wzz_714105382@icloud.com
 * @version 1.0
 * @date 2020/5/18 23:59
 * @description 用JVM的骚操作穿透类的继承关系，在子类上调用父类的方法；注意，这个方法已经warn了
 */
public class invokeDemo {
    public static class GrandFather {
        public void say() {
            System.out.println(
                    "This is grandFather!"
            );
        }
    }
    public static class Father extends GrandFather {
        @Override
        public void say() {
            System.out.println(
                    "This is father!"
            );
        }
    }
    public static class Son extends Father {
        @Override
        public void say() {
            System.out.println("This is son");
        }

        @Override
        public String toString() {
            return "??????";
        }
    }
    public static void test() throws Throwable {
        Son son = new Son();
        System.out.println("simple invoke:");
        son.say();
        System.out.println("handle invoke:");
        Field f = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
        f.setAccessible(true);
        MethodType methodType = MethodType.methodType(void.class);
        MethodHandles.Lookup lkp = (MethodHandles.Lookup) f.get(null);
        MethodHandle handle = lkp.findSpecial(GrandFather.class, "say",methodType, GrandFather.class);
        handle.invoke(son);
        System.out.println("invoke object toString: ");
        MethodType toStringType = MethodType.methodType(String.class);
        MethodHandle hd2 = lkp.findSpecial(Object.class, "toString",toStringType, Father.class);
        System.out.println(hd2.invoke(son));

    }

}


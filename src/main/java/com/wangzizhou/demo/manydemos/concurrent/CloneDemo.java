package com.wangzizhou.demo.manydemos.concurrent;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

public class CloneDemo {
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
    public static void test2() {
        class A implements Cloneable{
            private Integer i;
            public A(Integer i) {
                this.i = i;
            }

            public Integer getI() {
                return i;
            }

            @Override
            public final A clone() {
                A res = null;
                try {
                    res =  (A)super.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                return res;
            }

        }
        A[] as = new A[]{new A(1), new A(1), new A(1)};
        A[] aCopy = as.clone();
        // 数组对元素浅拷贝
        System.out.println(aCopy[0] == as[0]);
        System.out.println(as[0].clone() == as[0] );
    }
}

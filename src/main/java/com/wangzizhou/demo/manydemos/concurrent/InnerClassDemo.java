package com.wangzizhou.demo.manydemos.concurrent;

public class InnerClassDemo {
    public static void  test() {
        MyOuter.MyStaticInner.foo();

        MyOuter.bar();

    }
}

package com.wangzizhou.demo.manydemos.concurrent;

public class MyOuter {
    static {
        System.out.println("Initializing my outer class");
    }
    public static void bar() {

    }
    class MyInner {


    }
    public static class MyStaticInner {
        static {
            System.out.println("Initializing my static inner class");
        }
        public static void foo() {

        }
    }
}

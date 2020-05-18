package com.wangzizhou.demo.manydemos.concurrent;

import java.lang.ref.Cleaner;

public class ConstructDemo {
    private static class A {
        public A() {
            System.out.println("constructing A");
        }

    }
    private static class B extends A {
        int k;
        public B(int value) {
            this(check(value));
            System.out.println("check finish");
            this.k = value;

        }
        private B(Void checkValueProcess) {
            System.out.println("checking in private constructor");
        }
        private static Void check(int v) {
            if(v < 0) {
                throw new IllegalArgumentException();
            }
            return null;
        }
    }
    public static void test() {
        A a = new B(1);

    }
}

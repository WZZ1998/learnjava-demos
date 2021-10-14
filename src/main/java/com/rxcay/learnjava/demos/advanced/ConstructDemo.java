package com.rxcay.learnjava.demos.advanced;

import java.lang.ref.Cleaner;
// 应该是为了解决某种绕开构造器，创建无法回收的对象的防御机制

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

package com.wangzizhou.demo.manydemos.basic;

public class CloneDemo {
    public static void test() {
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

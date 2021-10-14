package com.rxcay.learnjava.demos.concurrent;

public enum MyEffectiveSingleton {
    INSTANCE(0);

    MyEffectiveSingleton(int s) {
        System.out.println("s == 0");
        System.out.println("creating enum object INSTANCE");
    }

    public void foo() {

    }
}

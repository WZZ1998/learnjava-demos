package com.wangzizhou.demo.manydemos.concurrent;

public class MySingleton {
    // necessary? necessary!
    private static volatile MySingleton instance;
    private int c = 0;
    private MySingleton() {
        c = 1;
    }

    public int getC() {
        return c;
    }

    public static MySingleton getInstance() {
        if(instance == null) {
            synchronized (MySingleton.class) {
                if(instance == null) {
                    instance = new MySingleton();
                }
            }
        }
        return instance;
    }
    public static void clean() {
        instance = null;
    }
}

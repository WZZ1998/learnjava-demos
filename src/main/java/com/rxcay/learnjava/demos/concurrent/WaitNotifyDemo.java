package com.rxcay.learnjava.demos.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 10/16/21 3:45 上午
 * @description
 */
public class WaitNotifyDemo {
    public static void test() {
        (new Thread(() -> {
            synchronized (WaitNotifyDemo.class) {
                try {
                    WaitNotifyDemo.class.wait();
                    System.out.println("thread 0 get WND lock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).start();
        (new Thread(() -> {
            synchronized (WaitNotifyDemo.class) {
                WaitNotifyDemo.class.notify();
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("thread 1 finish sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).start();

    }
}

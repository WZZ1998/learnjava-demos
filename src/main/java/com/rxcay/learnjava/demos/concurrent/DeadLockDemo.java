package com.rxcay.learnjava.demos.concurrent;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 10/16/21 2:54 上午
 * @description
 */
public class DeadLockDemo {
    // cause deadlock
    public static void test() {
         var t1   = new Thread(() -> {

             synchronized (DeadLockDemo.class) {
                 try {
                     TimeUnit.SECONDS.sleep(1);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 synchronized (Object.class){}
             }
        });
         var t2 = new Thread(() -> {

             synchronized (Object.class) {
                 synchronized (DeadLockDemo.class){}
             }
         });
         t1.start();
         t2.start();
    }


}

package com.wangzizhou.demo.manydemos.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private final static MyAQSLock lock = new MyAQSLock();
    private final static Condition notFull = lock.newCondition();
    private final static Condition notEmpty = lock.newCondition();
    private final static Queue<String> productQueue = new ArrayDeque<>(10);
    public static void test() {
        Runnable produce = () -> {
            lock.lock();
            try {
                while(productQueue.size() == 10) {
                    notFull.await();
                }
                System.out.println("size = " + productQueue.size());
                System.out.println("produce food  by" + Thread.currentThread().getName());
                productQueue.add("food");
                System.out.println("size = " + productQueue.size());
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Runnable consume = ()-> {
            lock.lock();
            try {
                while (productQueue.size() == 0) {
                    notEmpty.await();
                }
                String product = productQueue.poll();
                System.out.println("eat " + product + ' ' + Thread.currentThread().getName());
                notFull.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Thread p1  = new Thread(produce);
        p1.setName("p1");
        Thread p2 = new Thread(produce);
        p2.setName("p2");
        Thread c1 = new Thread(consume);
        c1.setName("c1");
        Thread c2 = new Thread(consume);
        c2.setName("c2");

        c1.start();
        c2.start();
        p1.start();
        p2.start();
    }
    public static void test2() {
        ReentrantLock l = new ReentrantLock(true);
    }
}

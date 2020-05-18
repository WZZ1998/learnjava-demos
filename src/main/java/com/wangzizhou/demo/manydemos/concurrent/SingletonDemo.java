package com.wangzizhou.demo.manydemos.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingletonDemo {
    public static void test() throws InterruptedException {
        var count = IntStream.range(0,100).parallel()
                .mapToObj(i -> MyEffectiveSingleton.INSTANCE)
                .distinct()
                .count();
        System.out.println(count);
        Runnable r = () ->   {
            var tmp = MySingleton.getInstance().getC();
            Object o = new Object();
            if(tmp != 1) {
                System.out.println("bad singleton! tmp = " + tmp);
            }
        };
        while(true) {
            var threadList = IntStream.range(0, 25)
                    .mapToObj(i -> new Thread(r))
                    .collect(Collectors.toList());
            threadList.forEach(Thread::start);
            threadList.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            MySingleton.clean();

        }
    }

}

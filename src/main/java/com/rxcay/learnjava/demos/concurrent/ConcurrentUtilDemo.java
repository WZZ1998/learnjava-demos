package com.rxcay.learnjava.demos.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class ConcurrentUtilDemo {
    public static void test() throws InterruptedException {
        int num = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(50);
        final CountDownLatch countDownLatch = new CountDownLatch(num);
        final AtomicLong  atomicLong = new AtomicLong(0);
        Runnable acc = () -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int count = random.nextInt(50);
            for (int i = 0; i < count; i++) {
                atomicLong.incrementAndGet();
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < num; i++) {
            executorService.submit(acc);
        }
        countDownLatch.await();
        System.out.println(atomicLong.get());
        executorService.shutdown();

    }
    public static void test2() {
        IntStream.iterate(1, i -> i + 1).limit(10)
                .forEach(i-> System.out.println(
                        "i = " + i + " size = " + testCap(i)
                ));

    }
    private static int testCap(int cnt) {
        var mapC = 100;
        AtomicInteger a = new AtomicInteger(mapC);
        final var map = new ConcurrentHashMap<String, String>();
        ExecutorService service = Executors.newFixedThreadPool(cnt);
        Runnable r = () -> {
            int myValue;
            while((myValue = a.getAndDecrement()) > 0) {
                map.put(String.valueOf(myValue),"");
            }
        };
        IntStream.range(0,cnt).forEach(i->service.submit(r));
        var capturedMiddleConKeySetSizes = new ArrayList<Integer>();
        while (true) {
            var keySet = new HashSet<>(map.keySet());
            var sz = keySet.size();
            if(sz == mapC)
                break;
            capturedMiddleConKeySetSizes.add(sz);
        }
        System.out.println(capturedMiddleConKeySetSizes);
        service.shutdown();
        return capturedMiddleConKeySetSizes.size();
    }
}

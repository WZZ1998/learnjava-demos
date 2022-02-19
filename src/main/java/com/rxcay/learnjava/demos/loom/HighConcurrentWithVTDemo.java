package com.rxcay.learnjava.demos.loom;

import java.util.concurrent.*;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/9/21 4:05 下午
 * @description
 */
public class HighConcurrentWithVTDemo {
    private static void blockRandomTime(int taskNo, long submitTime, boolean sample) {
        long startTime = 0;
        if(sample){
            startTime = System.currentTimeMillis();
        }
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        var millis = 20 + tr.nextInt(600);
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (sample) {
            long accomplishTime = System.currentTimeMillis();
            System.out.printf("task %d finish blocking,\n" +
                    "response time %d ms (submit -> exec).\nsleep time %d ms millis.\n" +
                    "task time %d ms.(submit -> finish) \n\n",
                    taskNo, startTime - submitTime, millis, accomplishTime - submitTime);
        }

    }

    private static void findIdealRandomNumber(int taskNo) {
        var tr = ThreadLocalRandom.current();
        while (true) {
            var n = tr.nextInt(100000);
            if (n == 777){
                //System.out.printf("task %d finished calculation.\n", taskNo);
                return;
            }
        }
    }
    public static void test() throws InterruptedException {
        final var taskCntPS = 200;
        try(
                //ExecutorService pool = Executors.newFixedThreadPool(1000);
                //ExecutorService pool = Executors.newCachedThreadPool();
                //ExecutorService pool = Executors.newVirtualThreadExecutor()
                //var mySchedulerForVT = Executors.newCachedThreadPool(); // DO not use this.
                // when the v threads burst out, it may take too many os threads to execute.
                // However, this is meaningless. Only # of cpu cores can be actually executed.
                var mySchedulerForVT = Executors.newFixedThreadPool(
                        8);
                var pool= Executors.newThreadPerTaskExecutor(
                        Thread.ofVirtual()
                                //.scheduler(mySchedulerForVT)
                                .name("vir-thread", 0)
                                .factory()) ) {
//            for (int i = 0; i < 4; i++) {
//                pool.execute(() ->{
//                    while(true) {}
//                } );
//            } // not support timely preempt schedule yet
            for (int j = 0; j < 10; j++) {
                System.out.println("iter: " + j);
                for (int i = 0; i < taskCntPS; i++) {
                    final int finalI = i;
                    var submitTime = System.currentTimeMillis();
                    pool.execute(()-> blockRandomTime(finalI, submitTime, finalI == 0));
                    //pool.invokeAll()
                }
                TimeUnit.SECONDS.sleep(1);

            }

            for (int i = 0; i < 3; i++) {
                mySchedulerForVT.execute(()-> {
                    throw new RuntimeException("deliberate");
                });
            }

            TimeUnit.SECONDS.sleep(5);
            System.out.println("over.");
        }
    }

}

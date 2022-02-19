package com.rxcay.learnjava.demos.concurrent;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/9/21 12:16 上午
 * @description
 */
public class ThreadPoolDemo {
    public static void test() throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i+" ");
//            System.err.println(i+" ");
//        } out of order
        Runnable r = ThreadPoolDemo::buggedFunc;
        var bareExc = new Thread(r);
        bareExc.setName("bare exception thread");
        bareExc.setUncaughtExceptionHandler((t,e) -> {
            System.out.printf("my UEH: thread %s has ueh %s\n", t.getName(), e);
        }); // first run ueh, then notify (JVM run logic)
        bareExc.start();
        bareExc.join(); // cannot control the order of stdout and stddrr since it is controlled by os.
        var wrappedExc = Thread.ofPlatform().unstarted(()-> {
            try {
                buggedFunc();
            } catch (Exception e){
                System.out.printf("Catch Exception in thread %s: %s. Exit.\n",
                        Thread.currentThread().getName(), e);
            }}); // os thread
        wrappedExc.start();
        wrappedExc.join();
        System.out.println(bareExc.getClass());
        System.out.println(wrappedExc.getClass());

        try(ExecutorService pool = Executors.newCachedThreadPool()) {
            FutureTask<?> futureTask =  new FutureTask<>(ThreadPoolDemo::buggedFunc, null);

            pool.execute(ThreadPoolDemo::buggedFunc);
            var x = pool.submit(ThreadPoolDemo::buggedFunc);
            try {
                var res = x.get(); //null
                System.out.printf("submit future res: %s \n",res);
            } catch(Exception e) {
                System.out.printf("submit exception: %s  \n",e);
            }

        }
        //System.out.printf("1s = %d us\n", TimeUnit.MICROSECONDS.convert(1, TimeUnit.SECONDS));

        try( var singleTPool = Executors.newSingleThreadExecutor(
                Thread.ofPlatform()
                    .name("[single-OS-thread-pool]-worker", 0)
                    .uncaughtExceptionHandler((t,e)-> System.out.printf("thread %s quit since %s .\n", t.getName(), e))
                    .factory());
             var vPool = Executors.newThreadPerTaskExecutor(
                     Thread.ofVirtual()
                             .name("[virtual-thread-pool]-worker", 0)
                             .uncaughtExceptionHandler((t,e)-> System.out.printf("thread %s quit since %s .\n", t.getName(), e))
                             .factory())
        ) {
            for (int i = 0; i < 6; i++) {
                var rr = vPool.submit(ThreadPoolDemo::buggedFunc);
                try {
                    var res = rr.get(); //null
                    System.out.printf("submit future res: %s \n",res);
                } catch(Exception e) {
                    System.out.printf("submit exception: %s  \n",e);
                }
            }
        }

    }


    private static void buggedFunc() {
        var s = 1;
        s = 2;
        s = s + 15;
        System.out.println(Thread.currentThread().getName() + " about to exception");
        s = 1/0;
        //throw new RuntimeException("deliberate exception");
    }
}

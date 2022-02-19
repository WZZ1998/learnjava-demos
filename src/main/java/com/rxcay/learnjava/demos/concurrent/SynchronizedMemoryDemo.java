package com.rxcay.learnjava.demos.concurrent;

import java.util.concurrent.TimeUnit;

public class SynchronizedMemoryDemo {
    public static int  v1 = 0;
    public static int v2 = 0;
    public static void test() throws InterruptedException {
        Runnable modifyVarInSyncBlock = () -> {
            //synchronized (Object.class) {
                while(true) {
//                    try {
//                        TimeUnit.SECONDS.sleep(3);
//                        break;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    v1 = 1;
                    v2 = 1;



                }
            //}

        };
        Runnable readVarOutSyncBlock = () -> {
            while(true) {
                //System.out.println("v1 = " + v1 + " v2 = " + v2);
                if(v1 == 1 && v2 == 1) {
                    break;
                }
            }
            System.out.printf("read thread: v1 = %d v2 = %d\n", v1,v2);
        };

        Thread readT = new Thread(readVarOutSyncBlock);

        Thread writeT = new Thread(modifyVarInSyncBlock);
        writeT.start();
        readT.start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

        while(true) {
            if (v1 == 1 && v2 == 1) {
                break;
            }
        }
    }
}

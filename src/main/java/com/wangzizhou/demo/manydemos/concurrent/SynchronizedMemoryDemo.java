package com.wangzizhou.demo.manydemos.concurrent;

import java.util.concurrent.TimeUnit;

public class SynchronizedMemoryDemo {
    public static int  v1 = 0;
    public static int v2 = 0;
    public static void test() throws InterruptedException {
        Runnable modifyVarInSyncBlock = () -> {
            int s = 0;
            //System.out.println("before enter synchronized block");
            synchronized (Object.class) {
                while(true) {
                    v1 = 1;
                    v2 = 1;
                    s = 1;

                    try {
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                //System.out.println("leaving enter synchronized block");
            }

        };
        Runnable readVarOutSyncBlock = () -> {
            while(true) {
                //System.out.println("v1 = " + v1 + " v2 = " + v2);
                if(v1 == 1 && v2 == 1) {
                    break;
                }
            }
        };

        Thread readT = new Thread(readVarOutSyncBlock);

        Thread writeT = new Thread(modifyVarInSyncBlock);
        readT.start();
        writeT.start();
        while(true) {
            if (v1 == 1 && v2 == 1) {
                break;
            }
        }
    }
}

package com.wangzizhou.demo.manydemos.concurrent;

import java.lang.ref.Cleaner;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReferenceDemo {
    public void foo() {}
    private static class R{
        int x = 0;
        final Cleaner.Cleanable manualCleanable;
        static Cleaner cleaner = Cleaner.create();
        static ReferenceQueue<R> gcMonitorQOfR = new ReferenceQueue<>();
        private static void cleanResource(String res){
            System.out.println("Simulating cleaning resource! This is " + res);
        }

        public R(){

            String fakeResource = "fake resource of " + this.toString();
            manualCleanable = cleaner.register(this, ()->R.cleanResource(fakeResource));
        }
        public void manualClean() {
            manualCleanable.clean();
        }
    }

    public static void test() throws InterruptedException {
        var rlist = Stream.generate(R::new).limit(10).collect(Collectors.toList());
        rlist.get(0).manualClean();
        var prList = rlist.stream()
                .map(r -> new PhantomReference<>(r,R.gcMonitorQOfR)).collect(Collectors.toList());
        System.out.println("after cons " + prList.stream().filter(PhantomReference::isEnqueued).count());
        TimeUnit.SECONDS.sleep(3);
        rlist = null;
        System.out.println( "after set null " + prList.stream().filter(PhantomReference::isEnqueued).count());
        TimeUnit.SECONDS.sleep(3);
        System.gc();
        System.out.println( "after gc " + prList.stream().filter(PhantomReference::isEnqueued).count());
    }



}

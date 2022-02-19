package com.rxcay.learnjava.demos.collection;

import com.rxcay.learnjava.demos.basic.TypeDemo;
import com.rxcay.learnjava.demos.util.ExecUtil;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/11/21 10:39 下午
 * @description
 */
public class Main {
    public static void main(String[] args) {

//    Runnable test = ClassDemo::test;
//    test.run();

        var demoClasses = new Class[] {
                HashMapDemo.class,
                CollectionDemo.class,
                QueueDemo.class,

        };
        ExecUtil.reflectTestMethodAndExecWithBench(demoClasses);

    }

}

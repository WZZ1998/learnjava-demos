package com.rxcay.learnjava.demos.basic;

import com.rxcay.learnjava.demos.collection.CollectionDemo;
import com.rxcay.learnjava.demos.util.ExecUtil;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/5/21 7:48 下午
 * @description
 */
public class Main {
    public static void main(String[] args) {

//    Runnable test = ClassDemo::test;
//    test.run();

        var demoClasses = new Class[] {
                CollectionDemo.class,
                StringDemo.class,
                BenchDemo.class,
                ClassDemo.class,
                JSONDemo.class,
                DateTimeDemo.class,
    //            ObjectDemo.class,
                TypeDemo.class,

        };
        ExecUtil.reflectTestMethodAndExecWithBench(demoClasses);

    }

}

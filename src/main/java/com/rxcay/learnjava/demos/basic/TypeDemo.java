package com.rxcay.learnjava.demos.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/21/21 9:04 下午
 * @description
 */
public class TypeDemo {
    public static void test(){
        var t1 = System.currentTimeMillis();
        List<Long> ls = new ArrayList<>();
        for( long i = 0;i < 1000000;i++){
            ls.add(i);
        }
        var t2 = System.currentTimeMillis();
        System.out.println(ls.stream().mapToLong(Long::longValue).sum());
        var t3 = System.currentTimeMillis();
        System.out.printf("box collect time %d ms unbox time %d ms\n", t2 -t1, t3 -t2);
    }
}

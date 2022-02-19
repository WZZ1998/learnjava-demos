package com.rxcay.learnjava.demos.concurrent;


import com.rxcay.learnjava.demos.util.ExecUtil;

public class Main {
    public static void main(String[] args) {
       var demoClasses = new Class[]{
               ThreadPoolDemo.class,
       };
        ExecUtil.reflectTestMethodAndExecWithBench(demoClasses);
//        var x = 1500;
//        int[] a  = new int[x];
//        System.out.println(a.length);
    }
}

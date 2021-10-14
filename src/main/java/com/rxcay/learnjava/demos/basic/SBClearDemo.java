package com.rxcay.learnjava.demos.basic;

import java.util.function.ToIntFunction;
import java.util.stream.LongStream;

/**
 * @author wzz_714105382@icloud.com
 * @version 1.0
 * @date 2020/5/19 13:25
 * @description 测试StringBuilder 的三种清空方法; 注意函数式接口的用法
 */
public class SBClearDemo {
    static int testTime = 500000;
    static String content = "......";
    public static int clearWithDelete(StringBuilder sb) {
        for(int i = 0;i < testTime; i++){
            sb.append(content);
            sb.delete(0, sb.length());
        }
        return sb.length();
    }
    public static int clearWithReplace(StringBuilder sb) {
        for(int i = 0;i < testTime; i++){
            sb.append(content);
            sb.replace(0, sb.length(),"");
        }
        return sb.length();
    }
    public static int clearWithSetLength(StringBuilder sb) {
        for(int i = 0;i < testTime; i++){
            sb.append(content);
            sb.setLength(0);
        }
        return sb.length();
    }

    public static long runClear(ToIntFunction<StringBuilder> clearFunc) {
        StringBuilder sb = new StringBuilder();
        long st = System.nanoTime();
        int l = clearFunc.applyAsInt(sb);
        long en = System.nanoTime();
        //System.out.println("current length:" +  l);
        return en - st;
    }
    public static void finishAvgTest(ToIntFunction<StringBuilder> clearFunc, String name) {
        System.out.println(name);
        double r = LongStream.range(0, 10)
                .map(i -> runClear(clearFunc))
                .average().orElse(0.0);
        System.out.println("avg ns = " + r);
    }
    public static void test() {
        finishAvgTest(SBClearDemo::clearWithDelete, "with delete");
        finishAvgTest(SBClearDemo::clearWithReplace, "with replace");
        finishAvgTest(SBClearDemo::clearWithSetLength, "with set length");
    }

    public static void main(String[] args) {
        test();
    }
}

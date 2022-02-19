package com.rxcay.learnjava.demos.util;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/9/21 4:21 下午
 * @description
 */
public class ExecUtil {
    public static void reflectTestMethodAndExecWithBench(Class[] demoClasses){
        try {
            for (Class c : demoClasses){
                System.out.printf("--------running %s.----------\n", c.getSimpleName()); // getName() returns name with package
                var testMethod = c.getDeclaredMethod("test");
                var st = System.currentTimeMillis();
                testMethod.invoke(null);// static method, first arg is null
                System.out.printf("--------Finish running. %d ms used.---\n\n\n", System.currentTimeMillis() - st);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}

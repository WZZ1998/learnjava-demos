package com.rxcay.learnjava.demos.basic;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/8/21 11:30 下午
 * @description
 */
public class ObjectDemo {
    public static void test(){
        System.out.println((1<< 31) - 1); // the priority of << is lower than -
        System.out.println(Integer.MAX_VALUE);
        var hs = new Object[Integer.MAX_VALUE - 2]; // max cnt of hotspot VM
        System.out.println(hs.length);
        var cnt = 0;
        while (true){
            cnt++;
            Object o = new Object();
            var h = o.hashCode();
            if (hs[h] != null) {
                System.out.printf("iter %d conflict hash %x object old %s new %s\n",cnt,  h,hs[h], o);
                System.out.printf("old == new %b\n",o == hs[h]);
                return;
            }
            hs[h] = o;
        }
    }
}

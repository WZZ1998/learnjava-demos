package com.rxcay.learnjava.demos.collection;

import java.util.HashMap;

/**
 * @author wzz_714105382@icloud.com
 * @version 1.0
 * @date 2020/3/30 21:38
 * @description hashMap元素转型测试, hashMap先比较哈希值(相同)，然后再equals(等价)，
 *  注意，包装类的对象，与任何异类对象都不等价
 */
public class HashMapDemo {
    public static void test() {
        HashMap<Integer,String> myMap = new HashMap<>();
        Integer tar = 1;
        System.out.println("Integer 1 hashcode: " + tar.hashCode());
        myMap.put(tar, "one");
        byte bb = 1;
        System.out.println("get with byte 1 :" + myMap.get(bb)); // 自动转型为Byte，然后Byte与Integer类对象一定不相等
        System.out.println(tar.equals(bb)); // 自动转型为Byte
        System.out.println(tar == bb); // 对tar拆箱比较
    }

}

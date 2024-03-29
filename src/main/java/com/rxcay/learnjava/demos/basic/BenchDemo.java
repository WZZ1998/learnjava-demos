package com.rxcay.learnjava.demos.basic;

/**
 * @author wzz_714105382@icloud.com
 * @version 1.0
 * @date 2020/7/15 03:32
 * @description
 */
public class BenchDemo {
    public static void test() {
        //记录开始时间
        long start = System.nanoTime();
        int num = 100000;
        for (int i = 0; i < num; i++) {
            BubbleSort(1, 2, 3, 4, 5, 6, 7, 8,9);
        }
        //打印耗时时间
        System.out.println(System.nanoTime() - start);
    }
    //排序
    public static void BubbleSort(int... arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}

package com.rxcay.learnjava.demos.collection;

import com.rxcay.learnjava.demos.model.Person;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/11/21 10:50 下午
 * @description
 */
public class QueueDemo {
    public static void test(){
        int[][] x = new int[10][];
        int t = 10, y = 20 ;
        Queue<Person> courseCntOrder = new PriorityQueue<>(Comparator.comparing(Person::getAge));
        for (int i = 0; i < 20; i++) {
            courseCntOrder.add(Person.generatePersonWithRandomInfo());
        }
        while (courseCntOrder.peek() != null){
            System.out.println(courseCntOrder.poll());
        }

    }
}

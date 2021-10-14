package com.rxcay.learnjava.demos.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wzz_714105382@icloud.com
 * @version 1.0
 * @date 2020/5/4 22:15
 * @description 输出学生信息
 */
public class PrintDemo {
    public static class Student {
        String name;
        int age;
        int courseCount;
        int balance;

        @Override
        public String toString() {
            return name + "," + age + "," + courseCount +
                    "," + balance;
        }

        public Student(String name, int age, int courseCount, int balance) {
            this.name = name;
            this.age = age;
            this.courseCount = courseCount;
            this.balance = balance;
        }
    }
    public static void main(String[] args) {
        Random r = new Random();
        List<Student> l = new ArrayList<>();

        for(int i = 0;i < 150; i++) {
            String s = "adkwa" + i;
            int age = r.nextInt(50);
            int course = r.nextInt(15);
            int balance = r.nextInt(1000);
            Student stu = new Student(s, age, course, balance);
            l.add(stu);
        }
        l.forEach(System.out::println);
    }
}

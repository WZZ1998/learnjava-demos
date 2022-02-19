package com.rxcay.learnjava.demos.model;

//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.RandomUtils;

import com.rxcay.learnjava.demos.util.CommonUtil;
import com.rxcay.learnjava.demos.util.RandomStringUtil;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 10/15/21 5:03 下午
 * @description
 */
public class Person {
    private String name;
    private Integer courseCount;
    private String address;
    private Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", courseCount=" + courseCount +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(String name, Integer courseCount, String address, Integer age) {
        this.name = name;
        this.courseCount = courseCount;
        this.address = address;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Person generatePersonWithRandomInfo() {
//        var name = RandomStringUtils.randomAlphabetic(6, 14);
//        var courseCount = RandomUtils.nextInt(18, 40);
//        var address  = RandomStringUtils.randomAlphanumeric(16);
        return new Person(
                RandomStringUtil.generateAlphabeticStr(4 + CommonUtil.commonGLocalRandom.nextInt(6)),
                1 + CommonUtil.commonGLocalRandom.nextInt(9),
                RandomStringUtil.generateAlphanumericStr(10 + CommonUtil.commonGLocalRandom.nextInt(24)),
                16 + CommonUtil.commonGLocalRandom.nextInt(60)
        );


    }


}

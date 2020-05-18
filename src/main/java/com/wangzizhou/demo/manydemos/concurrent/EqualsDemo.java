package com.wangzizhou.demo.manydemos.concurrent;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class EqualsDemo {
    public static void test() {
        double primitive0 = +0.0d;
        double primitiveMinus0 = -0.0d;
        Double d0 = 0.0d;
        Double anotherD0 = 0.0d;
        Double minusD0 = -0.0d;
        System.out.println("p0 == p-0 " + (primitive0 == primitiveMinus0));
        System.out.println("d0 == another d0 " + (d0 == anotherD0));
        System.out.println("d0 equals another d0 " + (d0.equals(anotherD0)));
        System.out.println("d0 == minus d0 " + (d0 == minusD0));
        System.out.println("do equals minus d0 " + (d0.equals(minusD0)));
        double elevenByStream  = DoubleStream.iterate(0.1d,d -> 0.1d).limit(11).sum();
        double elevenByMultiply  = 11 * 0.1d;
        double elevenByPlus = 0;
        for(int i = 0;i < 11;i++) {
            elevenByPlus += 0.1;
        }
        System.out.println("11 stream long bits " + Double.doubleToRawLongBits(elevenByStream));
        System.out.println("11 + long bits " + Double.doubleToRawLongBits(elevenByPlus));
        System.out.println("11 * long bits " + Double.doubleToRawLongBits(elevenByMultiply));
        System.out.println("1.1 by stream == 1.1 by * " + (elevenByStream == elevenByMultiply));
        System.out.println("1.1 by + == 1.1 by *" + (elevenByPlus == elevenByMultiply));
        System.out.println("+ * Double.compare " + Double.compare(elevenByPlus,elevenByMultiply));
        System.out.println("----------");

        System.out.println("primitive: NaN == NaN " + (Double.NaN == Double.NaN));
        // Double obj never cached.
        System.out.println("obj NaN == NaN " + (Double.valueOf(Double.NaN) == Double.valueOf(Double.NaN)));
        System.out.println("obj NaN equals NaN " +
                (Double.valueOf(Double.NaN).equals(Double.valueOf(Double.NaN))));

        System.out.println("Double compare nan nan " + (Double.compare(Double.NaN, Double.NaN)));
    }
}

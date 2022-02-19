package com.rxcay.learnjava.demos.basic;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/7/21 10:58 下午
 * @description
 */
public class DateTimeDemo {
    public static void test(){
        var goodWeekDaysSta = findGoodWeek()
                .stream()
                .collect(Collectors
                        .groupingBy(LocalDate::getYear, (Supplier<TreeMap<Integer, Long>>) TreeMap::new, Collectors.counting() ));
        goodWeekDaysSta.entrySet().removeIf(e->e.getKey() != 2021);
        System.out.println(goodWeekDaysSta);


    }
    private static List<LocalDate> findGoodWeek() {
        LocalDate st = LocalDate.of(1600, 1, 1);
//        List<LocalDate> r1 = new ArrayList<>();
//        for (LocalDate i = st; !i.isAfter(LocalDate.now()); i = i.plusDays(7)) {
//            if (isGoodWeekDay(i)) {
//                r1.add(i);
//            }
//        }
        return Stream.iterate(st, d->!d.isAfter(st.plusYears(600)), d -> d.plusDays(7))
                .filter(DateTimeDemo::isGoodWeekDay)
                .collect(Collectors.toList());
    }

    private static boolean isGoodWeekDay(LocalDate d) {
        return d.getDayOfMonth() == d.getDayOfWeek().getValue();
    }
}

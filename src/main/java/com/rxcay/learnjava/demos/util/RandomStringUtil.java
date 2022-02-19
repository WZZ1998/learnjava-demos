package com.rxcay.learnjava.demos.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 10/17/21 4:44 下午
 * @description
 */
public class RandomStringUtil {
    private final static List<Character> RANDOM_CHAR_TABLE = Stream
            .of(
                    IntStream.rangeClosed(0x30,0x39),//[0,9]
                    IntStream.rangeClosed(0x41, 0x5a), //[A,Z]
                    IntStream.rangeClosed(0x61,0x7a), // [a,z]
                    IntStream.rangeClosed(0x20,0x20) //space
            )
            .flatMapToInt(Function.identity())
            .mapToObj(u->(char)u)
            .collect(Collectors.toUnmodifiableList());

    public static String generateAlphanumericStr(int length){
        return buildStrWithTable(length, RANDOM_CHAR_TABLE);
    }

    public static String generateAlphabeticStr(int length) {
        var alphabeticTable =
                RANDOM_CHAR_TABLE.subList(10,RANDOM_CHAR_TABLE.size()); // purge numbers
        return buildStrWithTable(length, alphabeticTable);
    }
    private static String buildStrWithTable(int length, List<Character> table) {
        var sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(
                    table.get(
                            CommonUtil.commonGLocalRandom.nextInt(
                                    table.size()
                            )
                    )
            );
        }
        return sb.toString();
    }


}

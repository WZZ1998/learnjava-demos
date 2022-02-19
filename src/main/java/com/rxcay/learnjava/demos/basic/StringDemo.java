package com.rxcay.learnjava.demos.basic;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 10/10/21 6:30 上午
 * @description
 */
public class StringDemo {
    // attention: U+0000 \\u+0000 is the NUL character. It can not be printed visibly in console.
    public static void test() {
        var hello = "Hello from StringDemo";
        System.out.println(hello);
        final String s = "𐄷Hello༃㋣𐄷";
        System.out.println(s);
        System.out.println("length: "+ s.length());
        System.out.print("This is the code point indexing: ");
        for (int i = 0; i < s.length(); i++) {
            System.out.printf("0x%x ", s.codePointAt(i));
            // codePointAt called on low surrogates code unit will only return the char value of low surrogates itself.
            // Thus, this is not the correct way to iterate through the unicode code point separately.
        }
        System.out.println();
        var encodeCharsets = List.of(
                StandardCharsets.UTF_8,
                StandardCharsets.UTF_16,
                StandardCharsets.UTF_16BE,
                StandardCharsets.UTF_16LE);
        encodeCharsets.forEach(charset -> {
                var encodeRes = s.getBytes(charset);
                System.out.printf("write %s bytes[%s] to system out:", charset.name(), naiveBytesToHexString(encodeRes));
                //System.out.write(encodeRes);
                System.out.write(encodeRes,0, encodeRes.length);
                System.out.flush();
                System.out.println();

        });


        System.out.println();

        var wl = List.of(
                new PrintWriter(System.out,true, StandardCharsets.UTF_8 ),
                new PrintWriter(System.out,true, StandardCharsets.UTF_16LE));
        wl.forEach(w -> {w.write(s + '\n'); w.flush();});
//        for (char ci  = 0x5000; ci <0x7000; ci++) {
//            System.out.printf("%c ", ci);
//            if (ci % 100 == 0){
//                System.out.println();
//            }
//        }

        System.out.println("for char insert space: ");
        s.chars().forEach( c -> System.out.printf("%c-",c));
        System.out.println();
        s.codePoints().forEach( c -> System.out.printf("%c-",c));
        System.out.println();

        testIndexingString();

        var sss = "你好,欢迎!";
        String res = sss.chars().map(Character::toUpperCase).collect(
                StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append).toString();
        System.out.println(res);
    }

    private static String naiveBytesToHexString(byte[] bytes) {
        var sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x ", b));
        }
        return sb.toString();
    }

    private static String generateRandomUnicodeString(int length, boolean withSurrogate){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        var selectCodePoints = Stream.of(
                IntStream.range(0x250, 0x2af),
                IntStream.range(0x1d00, 0x1d7f),
                IntStream.range(0x2800, 0x28ff),
                //IntStream.range(0x4e00, 0x9fff),
                IntStream.range(0xA000, 0xA48F),
                IntStream.range(0x13000, 0x1342f),
                IntStream.range(0x1f700,0x1f77f),
                IntStream.range(0x1f900, 0x1f9ff)).flatMapToInt(Function.identity());
        var codePointsRAForm = withSurrogate ? selectCodePoints.toArray(): selectCodePoints.filter(x -> x <= 0xffff).toArray();
        for (int i = 0; i < length; i++) {
            var randomUnicodeCodePoint = codePointsRAForm[r.nextInt(codePointsRAForm.length)];
            sb.appendCodePoint(randomUnicodeCodePoint);
        }
        return sb.toString();
    }

    private static void testIndexingString() {

        final int TEST_TIME = 20_000;
        final int STRING_LEN =  2000;

        int cpInS = 0;
        char cInS = 0;
        var rm = new Random();
        var s = generateRandomUnicodeString(STRING_LEN, true);
        // pre-heat
        IntStream.range(0, 10000).forEach(i -> rm.nextInt(STRING_LEN));
        var codePointsLen = s.codePointCount(0, s.length());
        for (int i = 0; i < TEST_TIME; i++) {

            cpInS = s.codePointAt(s.offsetByCodePoints(0, rm.nextInt(codePointsLen)));
            cInS = s.charAt(rm.nextInt(STRING_LEN));
        }
        var st = System.currentTimeMillis();
        for (int i = 0; i < TEST_TIME; i++) {
            cpInS = s.codePointAt(s.offsetByCodePoints(0, rm.nextInt(codePointsLen)));
        }
        System.out.printf("cp codepoint indexing time used %d ms.\n" ,System.currentTimeMillis() - st);


        st = System.currentTimeMillis();
        var convertCodePoints = s.codePoints().toArray();
        for (int i = 0; i < TEST_TIME; i++) {
            cpInS =  convertCodePoints[rm.nextInt(codePointsLen)];
        }
        System.out.printf("cp codepoint convert time used %d ms.\n" ,System.currentTimeMillis() - st);
        st = System.currentTimeMillis();

        for (int i = 0; i < TEST_TIME; i++) {
            cInS = s.charAt(rm.nextInt(STRING_LEN));
        }
        System.out.printf("char indexing time used %d ms.\n" ,System.currentTimeMillis() - st);

    }
}

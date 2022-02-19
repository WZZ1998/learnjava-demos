package com.rxcay.learnjava.demos.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rxcay.learnjava.demos.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 10/15/21 5:42 下午
 * @description
 */
public class JSONDemo {
    public static void test() {
        ObjectMapper om = new ObjectMapper();
        final var groupSize = 300;
        for (int i = 0; i < 1000; i++) {
            var l =
                    IntStream.range(0,groupSize)
                            .mapToObj(ignored->Person.generatePersonWithRandomInfo())
                            .collect(Collectors.toList());
            List<String> jsonPersons = new ArrayList<>();
            try {
                for (var person : l) {
                    jsonPersons.add(om.writeValueAsString(person));
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            // If you want to stop or return once exception, write try outside loop.
            // If you want to simply catch the bad case and continue to process, write it inside as below.
//            for(var person : l){
//                String j;
//                try {
//                    j = om.writeValueAsString(person);
//                }catch (JsonProcessingException e) {
//                    j = "BAD JSON: "  + e.getMessage();
//                }
//                jsonPersons.add(j);
//            }

            if (i % 200 == 0) {
                System.out.println(jsonPersons.get(0));
            }
        }

    }
}

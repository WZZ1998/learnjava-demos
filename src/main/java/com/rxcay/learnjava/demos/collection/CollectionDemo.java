package com.rxcay.learnjava.demos.collection;

import com.rxcay.learnjava.demos.model.Person;

import java.util.*;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 10/18/21 12:18 上午
 * @description
 */
public class CollectionDemo {
    public static void test(){

//        Number[] nums = new Integer[10];
//        nums[0] = 1.1;
        //  java.lang.ArrayStoreException (co-variant array problem), no compiler error
        int[] arr = new int[] {9,8,7,6};
        var arr1 = Arrays.copyOfRange(arr, 1,6); //fill with 0
        System.out.println(Arrays.toString(arr1));

        var l = List.of(1,2,3);
        //l.set(1,1);
        System.out.println(l);
        List<Integer> la = new ArrayList<>(List.of(5, 2, 3, 4, 5));
        var lp = la.subList(2,5);
        //la.remove(4);
        lp.set(2, 99);
        //System.out.println(lp);
        Integer[] ir = new Integer[26];
        ir[0] = 2;
        SortedSet<Person> ps = null, ps2 = null;
        if (ps == ps2) {
            System.out.printf("ps == ps2, %x, %x\n" ,20, ps2); // auto turn null into "null"
        }
        ps = new TreeSet<>(
                Comparator.comparing(Person::getName)
                        .thenComparing(Person::getCourseCount)
                        .thenComparing(Person::getAddress));
        ps.add(Person.generatePersonWithRandomInfo());// without comparator, add will throw classCastException
        System.out.println(ps);
        Integer x = null;
        //System.out.println(x + 1);
        var s2 = "adawda";
        Map<Character, Integer> m = new HashMap<>();
        //m.put(null,null);
        for(char cc = 'a';cc <= 'h';cc++){
            m.put(cc, null);
        }
        //m.put('a', 1); // replace above mapping
        System.out.printf("m: %s, containsValue null %b\n",m, m.containsValue(null));
        System.out.printf("a = %d, b = %d\n" ,
                m.getOrDefault('a', -1),
                m.getOrDefault('b', -1));
        var vv = m.get('a');
        var ovv = Optional.ofNullable(vv);
        System.out.println(ovv.orElse(-1));
        //m.compute('a',     (k,oldV)->oldV); // will remove a
        //m.computeIfPresent('a',(k,oldV)->oldV); // will not remove a
        //m.computeIfAbsent('b',ignored->null); // will not put b

        var ks = m.keySet();
//       for(Character cc : ks) { ConcurrentModificationException
//            if (m.get(cc)== null) {
//                ks.remove(cc);
//            }
//        }
        var es = m.entrySet();
//        for(Map.Entry<Character, Integer> en: es) { ConcurrentModificationException
//            if (en.getValue() == null) {
//                es.remove(en);
//            }
//        }
        ks.removeIf(c->m.get(c)==null&&c%2==0);
        System.out.println(m);
        es.removeIf(en->en.getValue()== null);
        System.out.println(m);
        List<Integer> li = new ArrayList<>();
        var lia = li.toArray(new Integer[0]);
    }
}

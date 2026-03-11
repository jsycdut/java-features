package org.java.biz;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Java 9语法特性测试
 */
public class Java9GrammarTest {

    @Test
    public void testCollectionApi() {
        // List Set Map都加了of方法，但是得到的都是不可变集合，只能读取数据，不能添加、删除、替换、排序
        List<String> lists = List.of("first", "second", "third");
        lists.forEach(System.out::println);
        Set<String> sets = Set.of("first", "second", "third");
        sets.forEach(System.out::println);
        Map<String, String> map = Map.of("sku", "sku0", "spu", "spu0");
        map.forEach((k, v) -> System.out.println(String.join(":", k, v)));
    }

    @Test
    public void testStreamEnhance() {
        List<Integer> list = List.of(1,2,3,4,5,6);
        // takeWhile是逐个遍历，从头开始的满足条件的会被保留，一旦不满足条件了，后续的所有数据将会被忽略
        // 以下代码输出1
        list.stream().takeWhile(x -> x < 2).forEach(System.out::println);
        System.out.println("===================");
        // dropWhile是逐个遍历，满足条件的数据会被忽略，一旦有一个数据不满足条件了，它以及后续的所有数据将会被保留
        // 以下代码输出2 3 4 5 6
        list.stream().dropWhile(x -> x < 2).forEach(System.out::println);
        System.out.println("===================iterate老写法");
        // iterate增强
        Stream.iterate(1, i -> i+ 1).limit(10).forEach(System.out::println);
        System.out.println("===================iterate新写法");
        Stream.iterate(1, i -> i <= 10, i -> i + 1).forEach(System.out::println);
    }

    @Test
    public void testOptional() {
        //ifPresentOrElse方法，不为空则消费，为空则执行Runable逻辑
        Optional.empty()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Optional是空的，执行我"));
        //or方法，如果Optional为空，则返回默认的Supplier的结果
        Optional.empty()
                .or(() -> Optional.of("Optional默认值"))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Optional是空的，执行我"));
    }
}

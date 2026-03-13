package org.feature.java10;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java10FeatureTest {
    /**
     * 可以根据赋值推断类型。java仍旧是一个强类型语言，只是做了类型推断而已
     */
    @Test
    public void testVariableTypeInfer() {
        var a = 10;
        var b = 4.0d;
        var c = "str";
        var d = List.of();
        var e = Set.of();
        var f = Map.of();
        // null 不能推断
        // var g = null;

        // lambda里面还不能用var，到11才可以
//        Consumer<String> consumer = (var i) -> System.out.println(i);
    }

    @Test
    public void testCollection() {
        // copyOf方法，拷贝出一个不可变集合，不能删除 修改 增加 排序等动作，否则会出发UnsupportedOperationException，即所谓的UOE
        List<Integer> unmodifiableList = List.copyOf(List.of(1, 2, 3));
        Set<Integer> unmodifiableSet = Set.copyOf(Set.of(1, 2, 3));
        Map<String, String> unmodifiableMap = Map.copyOf(Map.of("key", "val"));

        // Collectors收集器增加到不可变集合(list set map)的途径
        var list = new ArrayList<>();
        List<Object> collect = list.stream().collect(Collectors.toUnmodifiableList());
        Set<Object> collect1 = list.stream().collect(Collectors.toUnmodifiableSet());
        Map<Object, Object> collect2 = list.stream().collect(Collectors.toUnmodifiableMap(Function.identity(), Function.identity()));

    }

    @Test
    public void testOptional() {
        Optional<Object> empty = Optional.empty();
        // 默认抛
        empty.orElseThrow();
        // 提供一个异常给它抛
        empty.orElseThrow(() -> new RuntimeException("我抛出一个空异常"));
    }
}

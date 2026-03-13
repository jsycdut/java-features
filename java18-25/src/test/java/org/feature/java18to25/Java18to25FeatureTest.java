package org.feature.java18to25;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadFactory;

public class Java18to25FeatureTest {

    /**
     * record类的instance of 匹配 + 解构赋值
     * 解构就是把值从一个包装起来的东西中拿出来
     */
    @Test
    public void test() {
        Discount discount = new Discount("001",  BigDecimal.valueOf(3.04d));
        // instance of判定类型，并且使用括号后面声明的变量名进行值的解构
        if (discount instanceof Discount(String id, BigDecimal amt)) {
            System.out.println(id + " " + amt);
        }

        switch(discount) {
            // switch的解构
            case Discount(String id, BigDecimal value) -> System.out.printf("id: " + id + " value: " + value);
        }
    }

    /**
     * 虚拟线程jdk 19提出，在21这个LTS版本中转正
     * 终于是来个提振精神的了
     */
    @Test
    public void testVirtualThread() {
        // 虚拟线程执行的任务，依然还是runnable
        Runnable  dbTask = () -> {
            System.out.println("执行数据库操作中...");
        };

        Runnable remoteTask = () -> {
            System.out.println("执行远程调用中...");
        };

        // 虚拟线程创建+启动方式1
        Thread.ofVirtual().start(dbTask);
        Thread.ofVirtual().start(remoteTask);

        // 虚拟线程创建+启动方式2
        Thread.startVirtualThread(dbTask);

        // 虚拟线程创建 + 启动方式3（线程池）
        Executors.newVirtualThreadPerTaskExecutor().submit(dbTask);

        // 虚拟线程创建 + 启动方式4
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread thread = factory.newThread(dbTask);
        thread.start();
    }

    @Test
    public void testStructuredTaskScope() {
    }

    @Test
    public void testSequenceCollection() {
        // 带顺序的列表，List，Deque接口实现了
        List<Integer> list = new ArrayList<>();
        list.addFirst(0);
        list.addLast(1);
        list.addFirst(-1);
        System.out.println(list.getFirst());

        // 带顺序的集合
        SequencedSet<Integer> set = new LinkedHashSet<>();
        set.addFirst(-1);
        set.addLast(0);
        set.addLast(1);
        System.out.println(set.getFirst());
        System.out.println(set.getLast());

        // 带顺序的映射
        SequencedMap<String, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.putFirst("-1", "-1");
        sequencedMap.putLast("0", "0");
        sequencedMap.putLast("1", "1");
        System.out.println(sequencedMap.pollFirstEntry());
        System.out.println(sequencedMap.pollLastEntry());
    }

    /**
     * switch可以推断变量的类型
     */
    @Test
    public void testSwitch() {
        Object obj = 3;
        switch (obj) {
            case Integer i -> System.out.println("数字" + i);
            default -> throw new IllegalStateException("Unexpected value: " + obj);
        }
    }

    @Test
    public void testUnnamed() {
        // 所谓的unnamed，就是无名变量，被抛弃的变量，声明了却不用的变量
        int _ = 3;

        try {
            // 捕获了异常，但是不用它
        } catch (RuntimeException _) {
            // 你要尝试去用它，就会报错
//            System.out.println(_);
        }
    }
}

record Discount(String id, BigDecimal amt) {}

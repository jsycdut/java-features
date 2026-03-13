package org.feature.java12to17;

import org.junit.Test;

/**
 * 没办法，12-15的特性，人才凋零啊，直接体现在代码层面的太少了
 */
public class Java12to17FeatureTest {
    /**
     * 12之前的Switch写法
     */
    @Test
    public void testOldSwitch() {
        String level = "1";
        switch (level) {
            case "1":
                System.out.println("current level is 1");
                break;
            case "2":
            case "3":
                System.out.println("current level is 2 or 3");
                break;
            default:
                break;
        }
    }

    /**
     * 预览在12的新Switch写法，具体到了14才转正
     * 可以不写break了
     */
    @Test
    public void testJava12Switch() {
        var level = "1";
        switch (level) {
            // 箭头表达式，后面的多个的话，可以用{}括起来，只有一行可以去掉{}
            case "1" -> {
                System.out.println("current level is 1");
            }
            // 同时匹配多个
            case "2", "3" -> System.out.println("current level is 2 or 3");
        }
    }

    /**
     * 多行文本块，在jdk15可用，长字符串终于是不用手动去+++了
     * jdk 12提出 15转正
     */
    @Test
    public void testTextBlock() {
        String sql = """
                select trans_amt, red_envelop_amt, merch_disc_amt, platform_disc_amt, credits_amt
                from transaction where order_id = "2026031200001451";
                """;
        System.out.println(sql);
    }


    /**
     * Record类就是用record修饰的类，默认一个构造器，N个get方法
     * jdk 14提出，16 转正
     */
    @Test
    public void testRecordClass() {
        Event event = new Event("123456", "click");
        System.out.println(event);
    }

    /**
     * 其实就是在后面加了个别名，默认替你做一下强制类型转换而已
     * jdk14提出，16转正
     */
    @Test
    public void testInstanceOf() {
        // old style
        Object o = "hello";
        if (o instanceof String) {
            String str = (String) o;
            System.out.println(str);
        }

        // new style
        if (o instanceof String str) {
            System.out.println(str);
        }
    }

    /**
     * 密封类。说实话我是真瞧不上这东西。。。 我特么写父类，要知道后面有哪些子类用我？
     * 我写个子类，还要去改父类代码？算了算了。。。
     * jdk 15提出，17转正
     * 具体看{@link Strategy}
     */
    @Test
    public void testSealedClass() {
    }
}

record Event(String eventId, String actionName) {
}

abstract sealed class Strategy permits StrategyA, StrategyB {

}

non-sealed class StrategyA extends Strategy {

}

final class StrategyB extends Strategy {
}

package org.java.biz;

import org.java.order.OrderManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // 业务模拟：在biz模块调用order模块的OrderManager获取订单详情
        // 调用方代码和被调用方代码处于不同的java9的模块中
        // 必须在调用方(biz-module)的module-info.java中，添加对order-module的依赖
        // 在被调用的order-module的module-info.java中，添加exports org.java.order将需要被其他模块使用的包暴露出来
        OrderManager orderManager = new OrderManager();
        String orderDetail = orderManager.getOrderDetail("123456");
        System.out.println("调用order模块的获取订单接口结果：" + orderDetail);
    }
}

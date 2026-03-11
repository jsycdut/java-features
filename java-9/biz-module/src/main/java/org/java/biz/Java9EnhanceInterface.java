package org.java.biz;

public interface Java9EnhanceInterface {
    /**
     * Java9可以在接口中定义private方法，然后可以被default方法调用
     */
    private void f() {
    }

    default void g() {
        f();
    }
}

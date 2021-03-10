package com.scma.gof23.singletonle;
/*
* 就是实例在用到的时候才去创建，“比较懒”，用的时候才去检查有没有实例，
* 如果有则返回，没有则新建。有线程安全和线程不安全两种写法，
* 区别就是synchronized关键字。
* */
public class LHan {
    private static LHan instance;

    private LHan() {}

    public static LHan getInstance() {
        if (instance == null) {
            instance = new LHan();
        }
        return instance;
    }
}

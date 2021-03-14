package com.scma.gof23.单例模式;
/*
* 效果类似双检锁，但实现更简单。
* 但这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用
* */
public class StaticInnerClassSingletonle {
    private static StaticInnerClassSingletonle instance;

    private StaticInnerClassSingletonle() {
    }

    private static StaticInnerClassSingletonle getInstance() {
        return SingletonleHolder.INSTANCE;
    }

    // 静态内部类
    private static class SingletonleHolder {

        private static final StaticInnerClassSingletonle INSTANCE = new StaticInnerClassSingletonle();
    }
}

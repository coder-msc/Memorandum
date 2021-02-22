package com.scma.singletonle;


/*
 * 双检锁  的懒汉模式 单例
 *
 *又叫双重校验锁，综合了懒汉式和饿汉式两者的优缺点整合而成。
 * 看上面代码实现中，特点是在synchronized关键字内外都加了一层 if 条件判断，
 * 这样既保证了线程安全，又比直接上锁提高了执行效率，还节省了内存空间
 *
 *
 * 隐患
 *  分配内存空间
    初始化对象
    将对象指向刚分配的内存空间
 *
 *  编译器优化 可能指令重排  因此 需要加上 volatile
 * */
public class DoubleCheckSingletonle {
    private volatile static DoubleCheckSingletonle instance;  // 禁止指令重排
//    private  static DoubleCheckSingletonle instance;

    private DoubleCheckSingletonle() {
    }

    public static DoubleCheckSingletonle getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingletonle.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingletonle();  //分为三步   1 分配内存空间 2初始化对象  3将对象指向内存空间
                                            //编译器优化后 可能将 2和3 进行反序 导致 第二个线程进来时 可能拿到 未分配好点对象
                }
            }
        }
        return instance;
    }
}

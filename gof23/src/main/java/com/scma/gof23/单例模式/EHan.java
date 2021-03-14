package com.scma.gof23.单例模式;
/*
* 从名字上也很好理解，就是“比较勤”，实例在初始化的时候就已经建好了，不
* 管你有没有用到，都先建好了再说。
* 好处是没有线程安全的问题，坏处是浪费内存空间。
* */
public class EHan {

    private static EHan instance= new EHan();

    private EHan(){}

    public static EHan getInstance(){
        return  instance;
    }
}

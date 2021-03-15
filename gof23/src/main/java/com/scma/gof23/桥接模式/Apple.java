package com.scma.gof23.桥接模式;

//苹果品牌
public class Apple implements Brand {
    @Override
    public void info() {
        System.out.print("苹果品牌->");
    }
}

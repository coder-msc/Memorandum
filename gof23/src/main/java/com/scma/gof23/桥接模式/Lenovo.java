package com.scma.gof23.桥接模式;

//联想品牌
public class Lenovo implements Brand {
    @Override
    public void info() {
        System.out.print("联想品牌->");
    }
}

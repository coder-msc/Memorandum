package com.scma.gof23.适配器模式;

//1、继承 （类适配器，单继承）
//2、组合（对象适配器，常用）
//真正的适配器2  连接网线  连接电脑
public class Adapter2 implements NetToUSB {
    private Adapee adapee;

    public Adapter2(Adapee adapee) {
        this.adapee = adapee;
    }

    @Override
    public void handlerRequest() {
        adapee.request();
    }
}

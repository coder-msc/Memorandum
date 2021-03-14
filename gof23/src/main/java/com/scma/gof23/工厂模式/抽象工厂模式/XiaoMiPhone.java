package com.scma.gof23.工厂模式.抽象工厂模式;
/*
* 小米手机*/
public class XiaoMiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("开启小米手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米手机");
    }

    @Override
    public void callup() {
        System.out.println("小米手机callup");
    }

    @Override
    public void sentSms() {
        System.out.println("米手机sentSms");
    }
}

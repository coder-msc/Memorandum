package com.scma.gof23.factory.抽象工厂模式;
/*华为手机*/
public class HuaWeiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("开启华为手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为手机");
    }

    @Override
    public void callup() {
        System.out.println("华为手机callup");
    }

    @Override
    public void sentSms() {
        System.out.println("华为手机sentSms");
    }
}

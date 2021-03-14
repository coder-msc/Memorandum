package com.scma.gof23.工厂模式.抽象工厂模式;
/*华为路由器*/
public class HuaWeiRouter implements IRouterProduct {
    @Override
    public void start() {
        System.out.println("开启华为路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("开启华为路由器");
    }

    @Override
    public void openwifi() {
        System.out.println("华为路由器openwifi");
    }

    @Override
    public void sentSms() {
        System.out.println("华为路由器sentSms");
    }
}

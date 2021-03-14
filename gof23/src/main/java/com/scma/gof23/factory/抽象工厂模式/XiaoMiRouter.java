package com.scma.gof23.factory.抽象工厂模式;
/*小米路由器*/
public class XiaoMiRouter implements IRouterProduct {
    @Override
    public void start() {
        System.out.println("开启小米路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("开启小米路由器");
    }

    @Override
    public void openwifi() {
        System.out.println("小米路由器openwifi");
    }

    @Override
    public void sentSms() {
        System.out.println("小米路由器sentSms");
    }
}

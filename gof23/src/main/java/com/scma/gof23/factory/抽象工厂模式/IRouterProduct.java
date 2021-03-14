package com.scma.gof23.factory.抽象工厂模式;
/*路由器产品接口*/
public interface IRouterProduct {
    void start();

    void shutdown();

    void openwifi();

    void sentSms();
}

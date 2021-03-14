package com.scma.gof23.工厂模式.抽象工厂模式;
/*路由器产品接口*/
public interface IRouterProduct {
    void start();

    void shutdown();

    void openwifi();

    void sentSms();
}

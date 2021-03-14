package com.scma.gof23.工厂模式.抽象工厂模式;

//抽象产品工厂 （抽象的 抽象）
public interface IProductFactory {
    //生产手机
    IPhoneProduct iphoneProduct();

    //生产路由器
    IRouterProduct routerProduct();
}

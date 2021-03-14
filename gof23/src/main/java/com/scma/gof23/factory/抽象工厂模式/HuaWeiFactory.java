package com.scma.gof23.factory.抽象工厂模式;

public class HuaWeiFactory implements IProductFactory {
    @Override
    public IPhoneProduct iphoneProduct() {

        return new HuaWeiPhone();
    }

    @Override
    public IRouterProduct routerProduct() {

        return new HuaWeiRouter();
    }
}

package com.scma.gof23.factory.工厂方法模式;

public class WuLingFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new WuLing();
    }
}

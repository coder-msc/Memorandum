package com.scma.gof23.工厂模式.工厂方法模式;

public class TesLaFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new TesLa();
    }
}

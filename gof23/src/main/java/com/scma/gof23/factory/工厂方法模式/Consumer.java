package com.scma.gof23.factory.工厂方法模式;

import com.scma.gof23.factory.简单工厂模式.CarFactory;

/*不改变原来的代码  新增一个车 直接新增一个工厂类  方便扩展*/
public class Consumer {
    public static void main(String[] args) {
        Car car = new WuLingFactory().getCar();
        Car car1 = new TesLaFactory().getCar();
        car.name();
        car1.name();
    }


}

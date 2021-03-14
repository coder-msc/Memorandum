package com.scma.gof23.factory.简单工厂模式;

public class Consumer {
    public static void main(String[] args) {
        //工厂模式之前做法
       /* Car wuLing = new WuLing();
        Car tes = new TesLa();
        wuLing.name();
        tes.name();*/


        //工厂模式之后做法
        Car car = CarFactory.getCar("五菱");
        car.name();
    }


}

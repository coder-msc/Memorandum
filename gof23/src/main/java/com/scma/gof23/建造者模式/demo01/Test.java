package com.scma.gof23.建造者模式.demo01;

public class Test {
    public static void main(String[] args) {
        //指挥（包工头）
        Director director = new Director();

        //指挥 具体的工人完成产品
        Product build = director.build(new Worker());
        build.toString();
    }
}

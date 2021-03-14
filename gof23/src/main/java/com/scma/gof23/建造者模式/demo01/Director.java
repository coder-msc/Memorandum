package com.scma.gof23.建造者模式.demo01;

//指挥：核心 负责指挥乳沟构建一个工程
public class Director {
    //worker是builder的具体实现。一个builder可能有多个不同的worker
    public Product build(Builder builder) {
        builder.buildA();
        builder.buildB();
        builder.buildC();
        builder.buildD();
        return builder.getProduct();
    }
}

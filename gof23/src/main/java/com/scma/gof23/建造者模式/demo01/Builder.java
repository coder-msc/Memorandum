package com.scma.gof23.建造者模式.demo01;
//抽象的建造者： 方法
public abstract class Builder {
    abstract void buildA();
    abstract void buildB();
    abstract void buildC();
    abstract void buildD();

    abstract Product getProduct();
}

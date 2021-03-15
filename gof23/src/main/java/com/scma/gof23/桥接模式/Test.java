package com.scma.gof23.桥接模式;

//多个维度属性的桥接

//苹果   笔记本
//联想    台式机
//一共四种组合啊 666
public class Test{


    public static void main(String[] args) {
        //苹果笔记本
        Laptop laptop = new Laptop(new Apple());
        laptop.info();
        //联想台式机
        Desktop desktop = new Desktop(new Apple());
        desktop.info();
    }
}

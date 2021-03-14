package com.scma.gof23.建造者模式.demo02;

public class Test {
    public static void main(String[] args) {
        //服务员
        Worker worker = new Worker();
        Product product = worker.getProduct();
        System.out.println( product.toString());
    }
}

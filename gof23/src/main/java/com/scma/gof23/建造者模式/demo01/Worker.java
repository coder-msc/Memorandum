package com.scma.gof23.建造者模式.demo01;

public class Worker extends Builder {
    private Product product;

    public Worker() {
        product = new Product();
    }

    @Override
    void buildA() {
        product.setBuildA("地基");
        System.out.println("地基");
    }

    @Override
    void buildB() {
        product.setBuildA("钢筋工程");
        System.out.println("钢筋工程");
    }

    @Override
    void buildC() {
        product.setBuildA("铺电线");
        System.out.println("铺电线");
    }

    @Override
    void buildD() {
        product.setBuildA("粉刷墙壁");
        System.out.println("粉刷墙壁");
    }

    @Override
    Product getProduct() {
        return product;
    }
}

package com.scma.gof23.桥接模式;

//抽象的电脑类类型
public  class Computer {
    //多使用组合
    protected Brand brand;

    public Computer(Brand brand){
        this.brand=brand;
    }

    public void info(){
        brand.info(); //自带的品牌
    }
}


//具体的电脑类型
class Desktop extends Computer{

    public Desktop(Brand brand) {
        super(brand);
    }
    @Override
    public void info(){
        super.info();
        System.out.println("台式机");
    }
}

class Laptop extends Computer{
    public Laptop(Brand brand) {
        super(brand);

    }

    @Override
    public void info(){
        super.info();
        System.out.println("笔记本电脑");
    }
}


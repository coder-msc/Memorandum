package com.scma.gof23.适配器模式;

//客户端 想上网，但是插不上网线
public class Computer {
    //电脑要上网 需要连接上转接器 才可以上网
    public void net(NetToUSB adapter) {
        //上网的具体实现 ，找一个转接头
        adapter.handlerRequest();
    }

    public static void main(String[] args) {
        //电脑  适配器  网线
        Computer computer = new Computer();//电脑
        Adapter adapter = new Adapter();//类适配器
        computer.net(adapter);

        //组合创建的适配器
        Adapee adapee = new Adapee();//网线
        Adapter2 adapter2 = new Adapter2(adapee); //组合适配器
        computer.net(adapter2);

    }
}

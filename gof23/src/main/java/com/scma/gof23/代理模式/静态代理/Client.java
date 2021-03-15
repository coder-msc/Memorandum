package com.scma.gof23.代理模式.静态代理;
//客户
public class Client {
    public static void main(String[] args) {
        //房东要出租房子
        Host host = new Host();
        // 代理  中介  还有一些附属操作
        Proxy proxy = new Proxy(host);
        // 客户通过中介 直接租房
        proxy.rent();
    }
}

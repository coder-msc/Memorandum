package com.scma.gof23.代理模式.静态代理;
//代理
public class Proxy implements Rent {
    private Host host;
    public Proxy(Host host){
        this.host=host;
    }
    @Override
    public void rent() {
        seeHouse();
        host.rent();
        hetong();
    }

    //看房
    public void seeHouse(){
        System.out.println("看房");
    }
    //签合同
    public void hetong(){
        System.out.println("签合同");
    }

}

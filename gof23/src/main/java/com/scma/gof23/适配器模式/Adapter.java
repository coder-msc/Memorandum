package com.scma.gof23.适配器模式;
//真正的适配器  连接网线  连接电脑
 public class Adapter extends Adapee implements NetToUSB{

    @Override
    public void handlerRequest() {
    super.request();
    }
}

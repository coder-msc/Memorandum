package com.scma.gof23.工厂模式.简单工厂模式;

/*简单车工厂 （静态工厂模式） 如果有新车品牌 会改变工厂代码

--不满足开闭原则*/
public class CarFactory {
    public static Car getCar(String car) {
        if (car.equals("五菱")) {
            return new WuLing();
        } else if (car.equals("特斯拉")) {
            return new TesLa();
        } else {
            return null;
        }
    }
}

package com.scma.gof23.原型模式.demo01;

import java.util.Date;

public class TestClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        //原型对象
        Date date = new Date(); //该对象 都共用了  这个是浅克隆
        Video video = new Video("Java设计模式", date);

        System.out.println(video.toString()+"--->"+video.hashCode());

        //克隆 但只克隆了引用  属于浅克隆
        Video clone = (Video)video.clone();
        System.out.println(clone.toString()+"--->"+clone.hashCode());

    }
}

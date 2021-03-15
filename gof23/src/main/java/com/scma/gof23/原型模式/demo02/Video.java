package com.scma.gof23.原型模式.demo02;

import java.util.Date;

/*

实现克隆
1、实现一个接口
2、重写一个方法

* */
//视频类
public class Video implements Cloneable {
    private String name;
    private Date createTime;

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        return  super.clone();//只能浅克隆
        //实现深克隆
        Object obj = super.clone();
        Video v = (Video) obj;
        v.createTime = (Date) this.createTime.clone(); //将对象的属性也进行克隆
        return v;
    }

    public Video() {
    }

    public Video(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

package com.scma.synchronized_test;

/*
 * 关键字synchronized 原理
 * */
public class synchronizedTest implements Runnable {
    public static int num = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            num++;
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(new synchronizedTest()).start();
        }
        try {
            Thread.sleep(2000);  //等所有线程执行++ 操作完成
        } catch ( Exception e) {

        }
        System.out.println(num);
    }
}
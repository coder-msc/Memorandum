package com.scma.juc;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest2 {
    public static int addnum=0;//原来写法
public static ThreadLocal<Integer> number=new ThreadLocal<Integer>();
    public static int add10(int num){
        addnum=num;//原来写法
        number.set(num);
        try {
            TimeUnit.SECONDS.sleep(1); //这里线程都在这里休眠 但是num的值都是同一个 这样 一组线程的结果是相同的
                                                // 因此需用 Threadlocal来处理  让这个值变成线程私有的  即可
        }catch (Exception e){
            e.printStackTrace();
        }
        int m=number.get()+10;
//        return addnum+10;  //原来写法
        return m;
    }



    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<20;i++){
            final int num=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(num + " " +  ThreadLocalTest2.add10(num)+"线程名称："+Thread.currentThread().getId());
//                    System.out.println(Thread.currentThread().getId());

                }
            });
//            executorService.shutdown(); //线程池用完需要关闭 提前关闭 会导致异常   因为线程还在执行  但是把线程池关了

        }
        executorService.shutdown(); //线程池用完需要关闭

    }
}

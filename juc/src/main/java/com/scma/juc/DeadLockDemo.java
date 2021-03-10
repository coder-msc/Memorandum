package com.scma.juc;


import java.util.concurrent.TimeUnit;
/*
* 产生死锁的主要原因
*
* 1、资源互斥
* 2、资源不可抢占
* 3、循环等待
* 4、占有且等待
*
* */
class HoldThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有锁" + lockA + "尝试获得" + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);  //休眠一会 让 B这个线程进来 占用B这个锁  这样 A 和B 锁 都被占用   A里等待 B释放锁   B里等待A释放锁  造成循环等待  形成死锁
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有锁" + lockB + "尝试获得" + lockA);
            }
        }
    }
}

/**
 *  * Description:
 *  * 死锁是指两个或者以上的进程在执行过程中,
 *  * 因争夺资源而造成的一种相互等待的现象,
 *  * 若无外力干涉那他们都将无法推进下去
 *  *
 *  
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldThread(lockA, lockB), "threadAAA").start();
        new Thread(new HoldThread(lockB, lockA), "threadBBB").start();
    }
}

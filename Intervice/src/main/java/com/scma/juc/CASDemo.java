package com.scma.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(6, 2019)
                + " current data = " + atomicInteger.get());
        atomicInteger.compareAndSet(6, 2019);
        System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(5, 2019)
                + " current data = " + atomicInteger.get());
    }

}

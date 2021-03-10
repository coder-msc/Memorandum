package com.scma.Hash;

import java.util.HashSet;

public class MyTest {
    public static void main(String[] args) {
        HashSet<User> set = new HashSet<>();
        User u1 = new User("zhangsan", 3);
        User u2 = new User("lisi", 7);
        set.add(u1);
        set.add(u2);
        System.out.println("1:" + set);
//        u1.setName("dahuang");
        u1.name="huang";
        System.out.println("2:" + set);
//        set.remove(u1);
        System.out.println("3:" + set);
        set.add(new User("zhangsan", 3));
        System.out.println("4:" + set);

//        System.out.println(set.contains(user));
    }
}

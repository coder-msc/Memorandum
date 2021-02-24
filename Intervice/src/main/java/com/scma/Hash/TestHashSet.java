package com.scma.Hash;

import java.util.HashSet;

public class TestHashSet {
    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<Person>();
        Person p1 = new Person("AA",1001);
        Person p2 = new Person("BB",1002);

        set.add(p1);
        set.add(p2);
        System.out.println("1:" +set);
        System.out.println("p1的Hash值:" +p1.hashCode());
        p1.setName("CC");
        System.out.println("p1的Hash值(修改后):" +p1.hashCode());
        set.remove(p1);// 此时p1对象已经被修改了，remove()是根据修改后的对象的hash值，找对应的位置，因此此时删除时对应的位置没有值，所以此时没有删除出任何数据。
        System.out.println("2:" +set);

        Person p3 = new Person("CC", 1001); //虽然此对象与p1对象值完全相等，但是添加时，添加到了该对象所对应的hash桶，而此时的位置与p1不是同一个位置，因为p1是根据修改之前的hash值放入对应的has桶内的，因此他们不冲突
        set.add(p3);
        System.out.println("3:" +set);

        set.add(new Person("AA",1001));// 值与未修改前的p1值完全相等，所以对应的hash桶也是同一个位置，添加时找到对应的桶位置后，发现已经有一个对象，然后用equals方法比较值，此时值不相等，因此不产生冲突，依然可以加入到HashSet中
        System.out.println("4:" +set);
    }
}
/*
[Person [name=AA, id=1001], Person [name=BB, id=1002]]
[Person [name=CC, id=1001], Person [name=BB, id=1002]]
[Person [name=CC, id=1001], Person [name=CC, id=1001], Person [name=BB, id=1002]]
[Person [name=CC, id=1001], Person [name=CC, id=1001], Person [name=AA, id=1001], Person [name=BB, id=1002]]




*/

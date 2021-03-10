package com.scma.Hash;

public class User {
     String name;
     Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) {
            return false;
        }
        User p = (User) obj;
        return this.age == p.age && this.name.equals(p.name);
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + age;
        result = result * 37 + name.hashCode();
        return result;
    }
}

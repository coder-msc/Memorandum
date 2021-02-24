package com.scma.Hash;

public class Person {
    String name;
    int id;
    public Person(String name, int id) {
        super();
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person() {
        super();
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", id=" + id + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Person)) {
            return false;
        }
        Person p = (Person) obj;
        return this.id == p.id && this.name.equals(p.name);
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + id;
        result = result * 37 + name.hashCode();
        return result;
    }
}

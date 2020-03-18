package com.darryl.activiti.jvm_class_load;

import javax.jnlp.PersistenceService;

/**
 * @Auther: Darryl
 * @Description: 人  equals相等，hashcode不等，没啥意义
 * @Date: created in 2020/3/16 23:48
 */

public class Person {

    private int age;
    private String name;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return this.age==((Person) obj).age;
        }
        return false;
    }

    public Person(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Person p1 = new Person(1);
        Person p2 = new Person(1);
        System.out.println(p1.equals(p2));
        System.out.println(p1 == p2);
        System.out.println("p1 hashcode " + p1.hashCode());
        System.out.println("p2 hashcode " + p2.hashCode());
    }
}

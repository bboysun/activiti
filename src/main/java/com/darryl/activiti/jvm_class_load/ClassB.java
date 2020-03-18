package com.darryl.activiti.jvm_class_load;

/**
 * @Auther: Darryl
 * @Description: B继承A
 * @Date: created in 2020/3/16 21:52
 */

public class ClassB extends ClassA {

    final String str;

    public ClassB() {
        str = "aaa";
        System.out.println("B constructor");
    }

    static {

        System.out.println("I am static B");
    }

    {
        System.out.println("I am B");
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
        ClassA b = new ClassB();
        /*System.out.println("A hashcode: " + new ClassA().hashCode());
        System.out.println("B hashcode: " + new ClassB().hashCode());
        System.out.println(new ClassA().equals(new ClassB()));*/
    }

    @Override
    public int hashCode() {
        return 5%3;
    }
}

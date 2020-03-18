package com.darryl.activiti.jvm_class_load;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: A父类
 * @Date: created in 2020/3/16 21:50
 */

public class ClassA {

    public ClassA() {
        System.out.println("A constructor");
    }

    static {
        String str = "ass";
        str.compareTo("bbb");
        List<Integer> list = new ArrayList<>(5);
        Integer integer = 4;
        System.out.println(integer.compareTo(new Integer(5)));
        System.out.println("I am A static");
    }

    {
        System.out.println("I am A");
    }

    @Override
    public int hashCode() {
        return 5%3;
    }
}

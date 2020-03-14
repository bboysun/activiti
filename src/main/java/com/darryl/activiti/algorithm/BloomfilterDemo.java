package com.darryl.activiti.algorithm;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.math.BigDecimal;

/**
 * @Auther: Darryl
 * @Description: 布隆过滤器，我们预计构建一个1000000的数据量，误差在0.01
 * @Date: created in 2020/3/11 18:53
 */
public class BloomfilterDemo {

    private final static long INIT=1000000;

    public static void main(String[] args) {
        // 用来统计布隆过滤器误判的次数
        int count=0;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),INIT,0.01);
        // 将一百万的数据放进布隆过滤器中
        for (int i=0; i<1000000; i++) {
            bloomFilter.put(i);
        }
        for (int i=1000000; i<2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
            }
        }
        System.out.println("误判次数："+count);
        System.out.println(new BigDecimal(count).divide(new BigDecimal(INIT)));
    }

}

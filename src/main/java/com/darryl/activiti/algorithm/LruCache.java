package com.darryl.activiti.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: Darryl
 * @Description: LRU（Least Recently Used）算法，移除最近最少使用的元素数据
 * @Date: created in 2020/2/24 12:29
 */
public class LruCache<K,V> extends LinkedHashMap<K,V> {

    private final int CACHE_SIZE;


    public LruCache(int cache_size) {
        // Math.ceil()返回大于入参的最小正整数，accessOrder参数会将最新的元素数据放到LinkedhashMap的尾部
        super((int) Math.ceil(cache_size/0.75) + 1, 0.75f, true);
        CACHE_SIZE = cache_size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldst) {
        //当map中的数据量大于指定的缓存个数的时候，就会自动删除最老的数据。
        return size() > CACHE_SIZE;
    }

    // 可以debug验证一下
    public static void main(String[] args) {
        System.out.println(Math.ceil(78788.3));

        LruCache<String, String> lruCache = new LruCache<>(4);

        String str1 = "1";
        String str2 = "2";
        String str3 = "3";
        String str4 = "4";
        String str5 = "5";

        lruCache.put("str1", str1);
        lruCache.put("str2", str2);
        lruCache.put("str3", str3);
        lruCache.put("str4", str4);

        System.out.println("LRU CACHE size is " + lruCache.size());
        System.out.println("the fourth of LRU CACHE is " + lruCache.get("str1"));

        lruCache.put("str5", str5);

        System.out.println("LRU CACHE size is " + lruCache.size());

    }
}

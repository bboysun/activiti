package com.darryl.activiti.redis;

import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @Auther: Darryl
 * @Description: redis做限流
 * @Date: created in 2020/3/12 20:30
 */

public class LimitFlowDemo {

    private static final String host="127.0.0.1";
    private static final int port=6379;
    private static JedisPool jedisPool = new JedisPool(host, port);
    private static String LIMIT = "LIMIT";


    private static Jedis getClient() {
        return jedisPool.getResource();
    }

    private boolean limitFlowOne(Jedis jedis){
        String res = jedis.get(LIMIT);
        if (res == null) {
            jedis.incr(LIMIT);
            jedis.expire(LIMIT, 1);
            return true;
        }
        if (Integer.valueOf(res) > 10) {
            return false;
        }
        jedis.incr(LIMIT);
        return true;
    }

    // 不停地向list中添加令牌
    @Scheduled(fixedDelay = 10_000,initialDelay = 0)
    private void limitFlowTwo(Jedis jedis) {
        Long llen = jedis.llen(LIMIT);
        if (llen < 10) {
            jedis.lpush(LIMIT, UUID.randomUUID().toString());
        }
    }

    public static void main(String[] args) {
        // one
        /*Jedis client = LimitFlowDemo.getClient();
        LimitFlowDemo demo = new LimitFlowDemo();
        for (int i=0; i<50; i++) {
            if (i%20 == 0){
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = demo.limitFlowOne(client);
            if (flag)
                System.out.println("请求" + i + " enable run...");
            else
                System.out.println("请求" + i + " limit run...");
        }*/

        Jedis client = LimitFlowDemo.getClient();
        String res = client.rpop(LIMIT);
        if (res == null)
            System.out.println("已被限流");
        else
            System.out.println("进行业务操作");
    }

}

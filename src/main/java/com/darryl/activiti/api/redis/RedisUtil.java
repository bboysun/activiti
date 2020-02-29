package com.darryl.activiti.api.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @Auther: Darryl
 * @Description: redis 工具类，TODO：后期要整理下，使得更高效的使用，补充配置信息
 * @Date: created in 2020/2/29 20:54
 */

public class RedisUtil {

    // 表示加锁成功
    private static final String LOCK_SUCCESS="OK";
    // 这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
    private static final String SET_IF_NOT_EXIST="NX";
    // 这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体的过期时间由用户来确定
    private static final String SET_WITH_EXPIRE_TIME="PX";
    // 表示解锁成功
    private static final Long UNLOCK_SUCCESS=1L;

    private static String host;
    private static int port;
    private static Jedis jedis = new Jedis(host, port);

    /**
     * 获取锁
     * @param lockKey  锁的KEY
     * @param requestId  客户端ID，用来确认这把锁是哪个客户端添加的，只要这个客户端才能解锁。
     *                   因为我们将value赋值为requestId，代表加锁的客户端请求标识，
     *                   那么在客户端在解锁的时候就可以进行校验是否是同一个客户端。
     * @param expireTime  超时时间
     * @return true--加锁成功，false--加锁失败
     */
    public static boolean tryGetdisLock(String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     * @param lockKey 锁
     * @param requestId 客户端ID
     * @return true--解锁成功，false--解锁失败
     */
    public static boolean tryUnlock(String lockKey, String requestId){
        String script = "if redis.call('get',KEYS[1])==ARGV[1]" +
                " then return redis.call('del',KEYS[1])" +
                " else return 0 end";
        // Collections.singletonList(lockKey) 会创建只有一个元素lockKey的list，做到内存的最大优化，因为arrayList会初始化数组的长度是10
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (UNLOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

}

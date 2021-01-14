package com.wsjkk.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDataSourceDemo {
    private static Logger logger = LoggerFactory.getLogger(RedisDataSourceDemo.class);

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(5);
        jedisPoolConfig.setMaxIdle(2);
        jedisPoolConfig.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.3.220",6379,3000,null);

        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //具体的命令
            jedis.get("");
        }catch (Exception e){
            logger.error("op key {} error: " + e.getMessage(), "key", e);
        }finally {
            //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池
            if(jedis!=null){
                jedis.close();
            }
        }
     }

}

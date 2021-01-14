package com.wsjkk.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * redis连接池预热
 */
public class RedisDataSourcePreHeat {
    private  static  Logger logger = LoggerFactory.getLogger(RedisDataSourcePreHeat.class);
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(5);
        jedisPoolConfig.setMaxIdle(2);
        jedisPoolConfig.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.3.220",6379,3000,null);
        List<Jedis> minIdleJedisList = new ArrayList<>(jedisPoolConfig.getMinIdle());
        for(int i = 0;i<jedisPoolConfig.getMinIdle();i++){
            Jedis jedis = null;
            try{
                jedis = jedisPool.getResource();
                minIdleJedisList.add(jedis);
                jedis.ping();
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }finally {
                //注意，这里不能马上close将连接还给连接池，否则最后连接池里只会建立一个连接
                //jedis.close
            }
        }
        //统一将预热的连接还回连接池
        for(int i = 0;i<jedisPoolConfig.getMinIdle();i++){
            Jedis jedis = null;
            try{
                jedis = minIdleJedisList.get(i);
                //将连接归还回连接池
                jedis.close();
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }finally {
                
            }
        }

    }
}

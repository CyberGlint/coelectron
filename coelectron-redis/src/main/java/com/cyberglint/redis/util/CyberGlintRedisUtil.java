package com.cyberglint.redis.util;

import com.cyberglint.redis.enums.CyberGlintLockStrategyEnum;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类，提供对Redis缓存的简单操作。
 */
@Component
@Slf4j
public class CyberGlintRedisUtil {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedissonClient redissonClient;
    
    /**
     * 向Redis中写入数据。
     *
     * @param key   Redis中的键。
     * @param value 需存入的值。
     * @return 存入操作是否成功，如果成功则返回true，否则返回false。
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("[REDIS-UTIL-ERROR-GET]", e);
        }
        return result;
    }
    
    /**
     * 从Redis中读取数据。
     *
     * @param key Redis中的键。
     * @return 根据键从Redis中取出的值，如果不存在则返回null。
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
    
    /**
     * 向Redis中写入数据并设置失效时间。
     *
     * @param key        Redis中的键。
     * @param value      需存入的值。
     * @param expireTime 值的失效时间，单位为秒。
     * @return 存入操作是否成功，如果成功则返回true，否则返回false。
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("[REDIS-UTIL-ERROR-SET]", e);
        }
        return result;
    }
    
    
    
    /**
     * 使用分布式锁执行一个操作。
     *
     * @param lockKey 锁的键。
     * @param action  要执行的操作，使用 Lambda 表达式。
     */
    public void executeWithLock(
            String lockKey, Runnable action,
            CyberGlintLockStrategyEnum cyberGlintLockStrategyEnum
    ) {
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLocked = false;
        
        try {
            log.info("尝试获取锁 {}", lockKey);
            isLocked = lock.tryLock(cyberGlintLockStrategyEnum.getLockTimeout(),
                    cyberGlintLockStrategyEnum.getLeaseTime(), TimeUnit.SECONDS
            );
            
            if (isLocked) {
                log.info("成功获取锁 {}", lockKey);
                action.run();
                log.info("操作成功执行 {}", lockKey);
            } else {
                log.warn("无法获取锁 {}", lockKey);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("获取锁时被中断 {}", lockKey, e);
        } catch (Exception e) {
            log.error("执行操作时出现异常 {}", lockKey, e);
            throw e;
        } finally {
            if (isLocked && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("释放锁 {}", lockKey);
            }
        }
    }
}
package com.cyberglint.redis.util;

import com.cyberglint.redis.enums.CyberGlintLockStrategyEnum;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CyberGlintLockSpace {
    @Resource
    private RedissonClient redissonClient;
    
    
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

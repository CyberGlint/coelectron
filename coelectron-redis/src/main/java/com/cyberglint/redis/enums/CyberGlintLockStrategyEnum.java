package com.cyberglint.redis.enums;

import lombok.Getter;

@Getter
public enum CyberGlintLockStrategyEnum {
    SHORT(5, 10, "短时间锁，适用于轻量级操作"),
    MEDIUM(10, 20, "中等时间锁，适用于标准操作"),
    LONG(30, 60, "长时间锁，适用于耗时较长的操作");

    private final long lockTimeout;
    private final long leaseTime;
    private final String desc; // 描述字段

    CyberGlintLockStrategyEnum(long lockTimeout, long leaseTime, String desc) {
        this.lockTimeout = lockTimeout;
        this.leaseTime = leaseTime;
        this.desc = desc;
    }
}

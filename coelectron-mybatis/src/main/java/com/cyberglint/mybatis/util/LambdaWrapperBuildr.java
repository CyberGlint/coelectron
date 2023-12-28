package com.cyberglint.mybatis.util;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.function.Consumer;


/**
 * 增强LambdaQueryWrapper工具
 */
public class LambdaWrapperBuildr {
    
    /**
     * 创建一个基本的 LambdaQueryWrapper 实例。
     *
     * @param <T> 实体类的类型
     * @param clazz 实体类的 Class 对象
     * @return LambdaQueryWrapper 实例
     */
    public static <T> LambdaQueryWrapper<T> build(Class<T> clazz) {
        return new LambdaQueryWrapper<>(clazz);
    }
}

package com.cyberglint.mybatis.util;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.Map;
import java.util.function.Consumer;


/**
 * 增强LambdaQueryWrapper工具
 */
public class AdvancedLambdaQueryWrapperUtil {
    
    /**
     * 创建一个基本的 LambdaQueryWrapper 实例。
     *
     * @param <T> 实体类的类型
     * @param clazz 实体类的 Class 对象
     * @return LambdaQueryWrapper 实例
     */
    public static <T> LambdaQueryWrapper<T> create(Class<T> clazz) {
        return new LambdaQueryWrapper<>(clazz);
    }
    
//    /**
//     * 根据条件映射创建 LambdaQueryWrapper。
//     *
//     * @param <T> 实体类的类型
//     * @param clazz 实体类的 Class 对象
//     * @param conditions 条件映射
//     * @return LambdaQueryWrapper 实例
//     */
//    public static <T> LambdaQueryWrapper<T> createWithConditions(Class<T> clazz, Map<String, Object> conditions) {
//        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>(clazz);
//        conditions.forEach((key, value) -> {
//            if (value != null) {
//                wrapper.eq(key, value);
//            }
//        });
//        return wrapper;
//    }
//
    /**
     * 根据自定义条件创建 LambdaQueryWrapper。
     *
     * @param <T> 实体类的类型
     * @param clazz 实体类的 Class 对象
     * @param conditionBuilder 条件构建器
     * @return LambdaQueryWrapper 实例
     */
    public static <T> LambdaQueryWrapper<T> createWithCustomConditions(Class<T> clazz, Consumer<LambdaQueryWrapper<T>> conditionBuilder) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>(clazz);
        conditionBuilder.accept(wrapper);
        return wrapper;
    }
}

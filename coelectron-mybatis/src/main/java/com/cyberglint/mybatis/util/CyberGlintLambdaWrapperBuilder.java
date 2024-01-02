package com.cyberglint.mybatis.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * LambdaQueryWrapper 建造者
 */
public class CyberGlintLambdaWrapperBuilder<T> {
    
    private final LambdaQueryWrapper<T> queryWrapper;
    
    private CyberGlintLambdaWrapperBuilder(Class<T> clazz) {
        queryWrapper = new LambdaQueryWrapper<>(clazz);
    }
    
    public static <T> CyberGlintLambdaWrapperBuilder<T> builder(Class<T> clazz) {
        return new CyberGlintLambdaWrapperBuilder<>(clazz);
    }
    
    public CyberGlintLambdaWrapperBuilder<T> eq(SFunction<T, ?> column, Object val) {
        queryWrapper.eq(column, val);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> ne(SFunction<T, ?> column, Object val) {
        queryWrapper.ne(column, val);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> gt(SFunction<T, ?> column, Object val) {
        queryWrapper.gt(column, val);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> ge(SFunction<T, ?> column, Object val) {
        queryWrapper.ge(column, val);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> lt(SFunction<T, ?> column, Object val) {
        queryWrapper.lt(column, val);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> le(SFunction<T, ?> column, Object val) {
        queryWrapper.le(column, val);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> between(SFunction<T, ?> column, Object val1, Object val2) {
        queryWrapper.between(column, val1, val2);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> notBetween(SFunction<T, ?> column, Object val1, Object val2) {
        queryWrapper.notBetween(column, val1, val2);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> like(SFunction<T, ?> column, Object val) {
        queryWrapper.like(column, val);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> notLike(SFunction<T, ?> column, Object val) {
        queryWrapper.notLike(column, val);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> in(SFunction<T, ?> column, Object... values) {
        queryWrapper.in(column, values);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> notIn(SFunction<T, ?> column, Object... values) {
        queryWrapper.notIn(column, values);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> orderByAsc(SFunction<T, ?> column) {
        queryWrapper.orderByAsc(column);
        return this;
    }
    
    public CyberGlintLambdaWrapperBuilder<T> orderByDesc(SFunction<T, ?> column) {
        queryWrapper.orderByDesc(column);
        return this;
    }
    
    public LambdaQueryWrapper<T> build() {
        return queryWrapper;
    }
}

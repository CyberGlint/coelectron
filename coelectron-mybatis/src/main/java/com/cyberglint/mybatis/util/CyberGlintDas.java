package com.cyberglint.mybatis.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyberglint.common.CyberGlintBaseEo;

/**
 * 基础数据层
 */
public class CyberGlintDas<M extends BaseMapper<T>, T extends CyberGlintBaseEo> extends ServiceImpl<M, T> {
    /**
     * 判断是否存在该条件数据
     */
    public boolean exist(LambdaQueryWrapper<T> queryWrapper) {
        queryWrapper.last("limit 1");
        queryWrapper.select(T::getId);
        return super.getOne(queryWrapper) != null;
    }
}
